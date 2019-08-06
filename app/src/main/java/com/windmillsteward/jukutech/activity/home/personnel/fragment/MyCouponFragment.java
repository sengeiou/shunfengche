package com.windmillsteward.jukutech.activity.home.personnel.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.constant.AppConstant;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.MyCouponBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.DateUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCouponFragment extends BaseInitFragment {
    public static final int TYPE_WORK = 1;
    public static final int TYPE_RENCAI = 2;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout commonRefresh;

    private int type;
    private int use_coupon;

    private int page = 1;

    private List<MyCouponBean.ListBean> list;
    private RecyclerViewAdapter adapter;

    public static MyCouponFragment newInstance(int type,int use_coupon) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putInt("use_coupon", use_coupon);
        MyCouponFragment fragment = new MyCouponFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.layout_only_recyclerview_with_refresh;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        hidTitleView();

        if (getArguments() != null) {
            type = getArguments().getInt("type");
            use_coupon = getArguments().getInt("use_coupon");
        }
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        adapter.setLoadMoreEndText(AppConstant.TO_BOTTOM);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData();
            }
        }, recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        commonRefresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshPageData();
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MyCouponBean.ListBean listBean = list.get(position);
                if (use_coupon == 1 && listBean.getCoupon_status() == 1){
                    Intent intent = new Intent();
                    intent.putExtra("coupon_id",listBean.getReceive_id());
                    getActivity().setResult(NewPayActivity.RESULT_CODE_SUCCESS,intent);
                    getActivity().finish();
                }
            }
        });

        commonRefresh.setBackgroundColor(getActivity().getResources().getColor(R.color.transparent_db));
        getData();
    }


    //获取优惠券信息
    private void getData() {
        addCall(new NetUtil().setUrl(APIS.URL_MY_COUPON)
                .addParams("page", page + "")
                .addParams("page_count", 10 + "")
                .addParams("coupon_status", type + "")
                .setCallBackData(new BaseNewNetModelimpl<MyCouponBean>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        adapter.loadMoreFail();
                        showErrorView();
                        if (commonRefresh != null)
                            commonRefresh.refreshComplete();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<MyCouponBean> respnse, String source) {
                        MyCouponBean couponBean = respnse.getData();
                        if (respnse.getData() != null && respnse.getData().getList() != null) {
                            if (respnse.getData().isFirstPage()) {
                                list.clear();
                            }
                            for (MyCouponBean.ListBean listBean : respnse.getData().getList()) {
                                listBean.setType(type);
                                list.add(listBean);
                            }
                            if (couponBean.isLastPage()) {
                                adapter.loadMoreEnd();
                            } else {
                                adapter.loadMoreComplete();
                            }
                            adapter.notifyDataSetChanged();
                        }
                        page++;
                        showContentView();
                        if (commonRefresh != null)
                            commonRefresh.refreshComplete();
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<MyCouponBean>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    @Override
    protected void refreshPageData() {
        list.clear();
        page = 1;
        getData();
    }

    /**
     * 外部调用
     */
    public void refresh() {
        refreshPageData();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<MyCouponBean.ListBean, BaseViewHolder> {
        public RecyclerViewAdapter(List<MyCouponBean.ListBean> data) {
            super(R.layout.item_my_coupon, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final MyCouponBean.ListBean item) {

            if (item != null) {
                helper.setText(R.id.tv_ticket_money, item.getMoney() + "");
                helper.setText(R.id.tv_ticket_date, "有效期：" +
                        DateUtil.StampTimeToDate(item.getEnd_time() + "", "yyyy-MM-dd") + "到期"
                );
                helper.setText(R.id.tv_ticket_name, item.getCoupon_name());
                int coupon_status = item.getCoupon_status();
                helper.getView(R.id.tv_ticket_status).setVisibility(View.VISIBLE);
                if (coupon_status == 2) {
                    helper.setText(R.id.tv_ticket_status, "已使用");
                    helper.setText(R.id.tv_ticket_money, item.getCoupon_money() + "");
                } else if (coupon_status == 3) {
                    helper.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            final BaseDialog baseDialog = new BaseDialog(getActivity());
                            baseDialog.showTwoButton("提示","是否删除已失效的优惠券","确定","取消",new View.OnClickListener(){
                                @Override
                                public void onClick(View view) {
                                    baseDialog.dismiss();
                                    deleteCoupon(item.getReceive_id());
                                }
                            },new View.OnClickListener(){
                                @Override
                                public void onClick(View view) {
                                    baseDialog.dismiss();
                                }
                            });
                            return true;
                        }
                    });
                    helper.setText(R.id.tv_ticket_status, "已过期");
                } else {
                    helper.getView(R.id.tv_ticket_status).setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * 删除已失效的优惠券
     */
    private void deleteCoupon(int receive_id){
        showDialog();
        addCall(new NetUtil().setUrl(APIS.URL_DELETE_FINISHED_COUPON)
                .addParams("receive_id", receive_id + "")
                .setCallBackData(new BaseNewNetModelimpl() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo respnse, String source) {
                        dismiss();
                        if (respnse != null && respnse.getCode() == 0) {
                            showTips("删除成功");
                            refresh();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo>() {
                        }.getType();
                    }
                }).buildPost());
    }


}
