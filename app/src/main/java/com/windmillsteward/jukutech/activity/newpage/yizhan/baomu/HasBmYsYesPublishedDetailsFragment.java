package com.windmillsteward.jukutech.activity.newpage.yizhan.baomu;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.model.HasBmYsYesPublishedDetailsModel;
import com.windmillsteward.jukutech.base.BackFragmentActivity;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.constant.AppConstant;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.MapNaviUtils;
import com.windmillsteward.jukutech.utils.ResUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 * 已发布职位的详情 劳工 特种工
 */
public class HasBmYsYesPublishedDetailsFragment extends BaseInitFragment {
    public static final String TAG = "HasBmYsYesPublishedDetailsFragment";
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout commonRefresh;

    private TextView mTv_dingdan;
    private TextView mTv_jingyan;
    private TextView mTv_leixing;
    private TextView mTv_shijian;
    private TextView mTv_ydshijian;
    private TextView mTv_daiyu;
    private TextView mTv_diqu;
    private TextView mTv_address;
    private TextView mTv_neirong;
    private TextView mTv_feiyong;

    private HasBmYsYesPublishedDetailsModel data;
    private LinearLayout lay_ll_address;

    @Override
    protected int getContentViewId() {
        return R.layout.layout_only_recyclerview_with_refresh;
    }

    public static HasBmYsYesPublishedDetailsFragment newInstance(int recruitment_id,int roleType,int showAddress) {
        Bundle args = new Bundle();
        args.putInt("recruitment_id", recruitment_id);
        args.putInt("roleType", roleType);
        args.putInt("showAddress", showAddress);
        HasBmYsYesPublishedDetailsFragment fragment = new HasBmYsYesPublishedDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("用工明细");

        initAdapter();

        commonRefresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshPageData();
            }
        });
    }

    @Override
    protected void initData() {
        addCall(new NetUtil()
                .setUrl(APIS.URL_TALENT_RECRUITMENT_DETAIL)
                .addParams("recruitment_id", "" + getArguments().getInt("recruitment_id"))
                .setCallBackData(new BaseNewNetModelimpl<HasBmYsYesPublishedDetailsModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        commonRefresh.refreshComplete();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<HasBmYsYesPublishedDetailsModel> respnse, String source) {
                        showContentView();
                        commonRefresh.refreshComplete();

                        data = respnse.getData();
                        if (data != null) {
                            //设置数据
                            setHeaderData(data);

                            //设置listView数据
                            List<HasBmYsYesPublishedDetailsModel.RequireListBean> labor_info_list = data.getRequire_list();
                            if (labor_info_list != null) {
                                list.clear();
                                list.addAll(labor_info_list);
                                adapter.notifyDataSetChanged();
                                adapter.loadMoreEnd();
                                if (getArguments().getInt("showAddress",1) == 0 ){
                                    lay_ll_address.setVisibility(View.GONE);
                                    list.clear();
                                    adapter.notifyDataSetChanged();
                                    adapter.loadMoreEnd();
                                }
                            }
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<HasBmYsYesPublishedDetailsModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }


    //设置头部数据
    private void setHeaderData(final HasBmYsYesPublishedDetailsModel data) {
        mTv_dingdan.setText(data.getOrder_sn());
        mTv_jingyan.setText(data.getWork_experience_name());
        mTv_leixing.setText(data.getPerson_type_name());
        mTv_shijian.setText(data.getWork_time_type_name());
        mTv_ydshijian.setText(data.getBooking_time());
        mTv_daiyu.setText(data.getSalary());
        mTv_diqu.setText(data.getArea_name());
        mTv_address.setText(data.getAddress());
        List<String> service_contents = data.getService_contents();
        StringBuilder stringBuilder = new StringBuilder();
        if (service_contents != null) {
            for (String service_content : service_contents) {
                stringBuilder.append(service_content + "、");
            }
            if (TextUtils.isEmpty(stringBuilder)) {
                mTv_neirong.setText("");
            } else {
                mTv_neirong.setText(stringBuilder.subSequence(0, stringBuilder.length() - 1));
            }
        } else {
            mTv_neirong.setText("");
        }
        mTv_feiyong.setText(data.getFee());

        mTv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(data.getLongitude()), Double.parseDouble(data.getLatitude()), "");

            }
        });
    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    private RecyclerViewAdapter adapter;
    private List<HasBmYsYesPublishedDetailsModel.RequireListBean> list;

    public void initAdapter() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        initHeader();

        adapter.setEnableLoadMore(true);
        adapter.setLoadMoreEndText(AppConstant.TO_BOTTOM);

        adapter.isUseEmpty(false);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                HasBmYsYesPublishedDetailsModel.RequireListBean item = list.get(position);
                ((BackFragmentActivity) getActivity()).addFragment(BaomuInfoDetailsFragment.newInstance(item.getRequire_id(),getArguments().getInt("roleType")), true, true);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy == 0) {
                    commonRefresh.setEnabled(true);
                } else {
                    commonRefresh.setEnabled(false);
                }
            }
        });
    }

    //设置header
    private void initHeader() {
        View headerView = View.inflate(getActivity(), R.layout.header_has_publish_details_bm, null);
        mTv_dingdan = (TextView) headerView.findViewById(R.id.tv_dingdan);
        mTv_jingyan = (TextView) headerView.findViewById(R.id.tv_jingyan);
        mTv_leixing = (TextView) headerView.findViewById(R.id.tv_leixing);
        mTv_shijian = (TextView) headerView.findViewById(R.id.tv_shijian);
        mTv_ydshijian = (TextView) headerView.findViewById(R.id.tv_ydshijian);
        mTv_daiyu = (TextView) headerView.findViewById(R.id.tv_daiyu);
        mTv_diqu = (TextView) headerView.findViewById(R.id.tv_diqu);
        mTv_address = (TextView) headerView.findViewById(R.id.tv_address);
        mTv_neirong = (TextView) headerView.findViewById(R.id.tv_neirong);
        mTv_feiyong = (TextView) headerView.findViewById(R.id.tv_feiyong);
        lay_ll_address = (LinearLayout) headerView.findViewById(R.id.lay_ll_address);
        TextView tv_type = headerView.findViewById(R.id.tv_type);

        if (getArguments() != null) {
            int roleType = getArguments().getInt("roleType");
            if (roleType == PageConfig.TYPE_BAOMU) {
                tv_type.setText("保姆类型");
            } else if (roleType == PageConfig.TYPE_YUESAO) {
                tv_type.setText("月嫂类型");
            } else if (roleType == PageConfig.TYPE_YUERSAO) {
                tv_type.setText("育儿嫂类型");
            }
        }

        adapter.setHeaderView(headerView);
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<HasBmYsYesPublishedDetailsModel.RequireListBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<HasBmYsYesPublishedDetailsModel.RequireListBean> data) {
            super(R.layout.item_recycler_has_match_resume_baomu, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final HasBmYsYesPublishedDetailsModel.RequireListBean item) {
            helper.setText(R.id.tv_name, item.getUser_name() + "    " + (item.getSex() == 1 ? "男" : "女"))
                    .setText(R.id.tv_location, item.getArea_name());

            TextView textView = helper.getView(R.id.tv_pipeidu);
            textView.setText(new SpanUtils().append("匹配度：")
                    .append(item.getMatch_value() + "%")
                    .setForegroundColor(ResUtils.getCommRed())
                    .create());

            //定位
            helper.getView(R.id.tv_location).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MapNaviUtils.showDaoHangDialog(getActivity(),Double.parseDouble(item.getLongitude())  , Double.parseDouble(item.getLatitude()),"");
                }
            });

            GlideUtil.show(getActivity(), item.getUser_avatar_url(), (ImageView) helper.getView(R.id.iv_avatar));
        }
    }
}
