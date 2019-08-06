package com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.windmillsteward.jukutech.activity.newpage.model.HasJiajiaoPublishedDetailsModel;
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
import com.windmillsteward.jukutech.utils.StringUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * @date: on 2018/10/24
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 已发布职位的详情 家教
 */
public class HasJiajiaoPublishedDetailsFragment extends BaseInitFragment {
    public static final String TAG = "HasJiajiaoPublishedDetailsFragment";
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout commonRefresh;

    private TextView mTv_dingdan;
    private TextView mTv_kemu;
    private TextView mTv_nianji;
    private TextView mTv_gongzhong;
    private TextView mTv_address;
    private TextView mTv_yujishijian;
    private TextView mTv_daiyu;
    private TextView mTv_zhoumo;
    private TextView mTv_gongzhongri;
    private TextView mTv_feiyong;

    private HasJiajiaoPublishedDetailsModel data;
    private LinearLayout lay_ll_address;

    public static HasJiajiaoPublishedDetailsFragment newInstance(int look_for_tutor_id,int showAddress) {
        Bundle args = new Bundle();
        args.putInt("look_for_tutor_id", look_for_tutor_id);
        args.putInt("showAddress", showAddress);
        HasJiajiaoPublishedDetailsFragment fragment = new HasJiajiaoPublishedDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.layout_only_recyclerview_with_refresh;
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
                .setUrl(APIS.URL_TALENT_LOOKFOR_TUTOR_DETAIL)
                .addParams("look_for_tutor_id", "" + getArguments().getInt("look_for_tutor_id"))
                .setCallBackData(new BaseNewNetModelimpl<HasJiajiaoPublishedDetailsModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        commonRefresh.refreshComplete();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<HasJiajiaoPublishedDetailsModel> respnse, String source) {
                        showContentView();
                        commonRefresh.refreshComplete();

                        data = respnse.getData();
                        if (data != null) {
                            //设置数据
                            setHeaderData(data);

                            //设置listView数据
                            List<HasJiajiaoPublishedDetailsModel.WhenTutorListBean> labor_info_list = data.getWhen_tutor_list();
                            if (labor_info_list != null) {
                                list.clear();
                                list.addAll(labor_info_list);
                                adapter.notifyDataSetChanged();
                                adapter.loadMoreEnd();
                                if (getArguments().getInt("showAddress", 1) == 0) {
                                    lay_ll_address.setVisibility(View.GONE);
                                    list.clear();
                                    adapter.notifyDataSetChanged();
                                    adapter.loadMoreEnd();
                                }
                            }

                            mTv_address.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //地图
                                    if (StringUtil.isAllNotEmpty(data.getLongitude(), data.getLatitude())) {
                                        MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(data.getLongitude()), Double.parseDouble(data.getLatitude()), "");
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<HasJiajiaoPublishedDetailsModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    //设置头部数据
    private void setHeaderData(HasJiajiaoPublishedDetailsModel data) {
        mTv_dingdan.setText(data.getOrder_sn());
        mTv_kemu.setText(data.getCoach_subject_name());
        mTv_nianji.setText(data.getCoach_grade_name());
        mTv_address.setText(data.getAddress());
        mTv_yujishijian.setText(data.getBooking_time());
        mTv_daiyu.setText(data.getSalary());
        if (!TextUtils.isEmpty(data.getCoach_time())) {
            if (data.getCoach_time().contains("1"))
                mTv_zhoumo.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.select), null, null, null);
            if (data.getCoach_time().contains("2"))
                mTv_gongzhongri.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.select), null, null, null);
        } else {
            mTv_zhoumo.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.unselect), null, null, null);
            mTv_gongzhongri.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(R.mipmap.unselect), null, null, null);
        }
        mTv_gongzhong.setText(data.getArea_name());
        mTv_feiyong.setText(data.getFee());
    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    private RecyclerViewAdapter adapter;
    private List<HasJiajiaoPublishedDetailsModel.WhenTutorListBean> list;

    public void initAdapter() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        initHeader();

        adapter.setEnableLoadMore(true);
        adapter.setLoadMoreEndText(AppConstant.TO_BOTTOM);
        adapter.isUseEmpty(false);

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
        View headerView = View.inflate(getActivity(), R.layout.header_has_publish_details_jiajiao, null);
        mTv_dingdan = (TextView) headerView.findViewById(R.id.tv_dingdan);
        mTv_kemu = (TextView) headerView.findViewById(R.id.tv_kemu);
        mTv_nianji = (TextView) headerView.findViewById(R.id.tv_nianji);
        mTv_gongzhong = (TextView) headerView.findViewById(R.id.tv_gongzhong);
        mTv_address = (TextView) headerView.findViewById(R.id.tv_address);
        mTv_yujishijian = (TextView) headerView.findViewById(R.id.tv_yujishijian);
        mTv_daiyu = (TextView) headerView.findViewById(R.id.tv_daiyu);
        mTv_zhoumo = (TextView) headerView.findViewById(R.id.tv_zhoumo);
        mTv_gongzhongri = (TextView) headerView.findViewById(R.id.tv_gongzhongri);
        mTv_feiyong = (TextView) headerView.findViewById(R.id.tv_feiyong);
        lay_ll_address = (LinearLayout) headerView.findViewById(R.id.lay_ll_address);
        adapter.setHeaderView(headerView);
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<HasJiajiaoPublishedDetailsModel.WhenTutorListBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<HasJiajiaoPublishedDetailsModel.WhenTutorListBean> data) {
            super(R.layout.item_recycler_has_match_resume_baomu, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final HasJiajiaoPublishedDetailsModel.WhenTutorListBean item) {
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
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((BackFragmentActivity) getActivity()).addFragment(JiajiaoInfoDetailsFragment.newInstance(item.getWhen_tutor_id()), true, true);

                }
            });

        }
    }
}
