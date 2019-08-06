package com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi;

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
import com.windmillsteward.jukutech.activity.newpage.model.HasZhaopinPublishedDetailsModel;
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
 * @date: on 2018/10/28
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 求职招聘--已发布职位详情
 */
public class HasZhaopinPublishedDetailsFragment extends BaseInitFragment {
    public static final String TAG = "HasZhaopinPublishedDetailsFragment";
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout commonRefresh;

    private TextView mTv_bianhao;
    private TextView mTv_biaoti;
    private TextView mTv_gangwei;
    private TextView mTv_renshu;
    private TextView mTv_nianling;
    private TextView mTv_xueli;
    private TextView mTv_jingyan;
    private TextView mTv_address;
    private TextView mTv_daiyu;
    private TextView mTv_fuli;
    private TextView mTv_desc;
    private TextView mTv_feiyong;

    private HasZhaopinPublishedDetailsModel data;
    private LinearLayout lay_ll_address;

    public static HasZhaopinPublishedDetailsFragment newInstance(int job_id,int showAddress) {
        Bundle args = new Bundle();
        args.putInt("job_id", job_id);
        args.putInt("showAddress", showAddress);
        HasZhaopinPublishedDetailsFragment fragment = new HasZhaopinPublishedDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.layout_only_recyclerview_with_refresh;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        setMainTitle("查看信息");

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
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_PUBLISHED_JOB_DETAILS)
                .addParams("job_id", getArguments().getInt("job_id") + "")
                .setCallBackData(new BaseNewNetModelimpl<HasZhaopinPublishedDetailsModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        commonRefresh.refreshComplete();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<HasZhaopinPublishedDetailsModel> respnse, String source) {
                        showContentView();
                        commonRefresh.refreshComplete();
                        data = respnse.getData();
                        if (data != null) {
                            //设置数据
                            setHeaderData(data);

                            //设置listView数据
                            List<HasZhaopinPublishedDetailsModel.JobSeekerRecordListBean> labor_info_list = data.getJobSeekerRecordList();
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
                                    HasZhaopinPublishedDetailsModel.JobRecordBean jobRecord = data.getJobRecord();
                                    if (StringUtil.isAllNotEmpty(jobRecord.getLongitude(), jobRecord.getLatitude())) {
                                        MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(jobRecord.getLongitude()), Double.parseDouble(jobRecord.getLatitude()), "");
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<HasZhaopinPublishedDetailsModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    //设置header
    private void initHeader() {
        View headerView = View.inflate(getActivity(), R.layout.header_has_publish_details_zhaopin, null);
        mTv_bianhao = (TextView) headerView.findViewById(R.id.tv_bianhao);
        mTv_biaoti = (TextView) headerView.findViewById(R.id.tv_biaoti);
        mTv_gangwei = (TextView) headerView.findViewById(R.id.tv_gangwei);
        mTv_renshu = (TextView) headerView.findViewById(R.id.tv_renshu);
        mTv_nianling = (TextView) headerView.findViewById(R.id.tv_nianling);
        mTv_xueli = (TextView) headerView.findViewById(R.id.tv_xueli);
        mTv_jingyan = (TextView) headerView.findViewById(R.id.tv_jingyan);
        mTv_address = (TextView) headerView.findViewById(R.id.tv_address);
        mTv_daiyu = (TextView) headerView.findViewById(R.id.tv_daiyu);
        mTv_fuli = (TextView) headerView.findViewById(R.id.tv_fuli);
        mTv_desc = (TextView) headerView.findViewById(R.id.tv_desc);
        mTv_feiyong = (TextView) headerView.findViewById(R.id.tv_feiyong);
        lay_ll_address = (LinearLayout) headerView.findViewById(R.id.lay_ll_address);
        adapter.setHeaderView(headerView);
    }

    private void setHeaderData(HasZhaopinPublishedDetailsModel datas) {
        HasZhaopinPublishedDetailsModel.JobRecordBean data = datas.getJobRecord();
        mTv_bianhao.setText(data.getOrder_sn());
        mTv_biaoti.setText(data.getTitle());
        mTv_gangwei.setText(data.getJob_name());
        mTv_renshu.setText(data.getRecruitment_num() + "");
        mTv_nianling.setText(data.getAge_range_name());
        mTv_xueli.setText(data.getEducation_background_name());
        mTv_jingyan.setText(data.getWork_year_name());
        mTv_address.setText(data.getAddress());
        if (data.getSalary_type()==1){
            mTv_daiyu.setText("市场定价");
        }else if(data.getSalary_type() == 2){
            if (!TextUtils.isEmpty(data.getSalary_fee())&& !TextUtils.isEmpty(data.getEnd_salary_fee()) ){
                mTv_daiyu.setText(data.getSalary_fee()+"~"+data.getEnd_salary_fee()+"元");
            }
        }else if (data.getSalary_type()  == 3){
            mTv_daiyu.setText("面议");
        }
        List<String> benefit_name_list = data.getBenefit_ids();
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : benefit_name_list) {
            stringBuilder.append(s + "、");
        }
        String result =  stringBuilder.toString();
        if (!TextUtils.isEmpty(result)) {
            mTv_fuli.setText(result.substring(0,result.length()-1));
        }
        mTv_desc.setText(data.getDescription());
        mTv_feiyong.setText(data.getInfo_fee());
    }

    @Override
    protected void refreshPageData() {
        initData();
    }

    private RecyclerViewAdapter adapter;
    private List<HasZhaopinPublishedDetailsModel.JobSeekerRecordListBean> list;

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
                //求职招聘
                HasZhaopinPublishedDetailsModel.JobSeekerRecordListBean item = (HasZhaopinPublishedDetailsModel.JobSeekerRecordListBean) list.get(position);
                ((BackFragmentActivity) getActivity()).addFragment(ZhaopinInfoDetailsFragment.newInstance(item.getJob_resume_id()), true, true);
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

    class RecyclerViewAdapter extends BaseQuickAdapter<HasZhaopinPublishedDetailsModel.JobSeekerRecordListBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<HasZhaopinPublishedDetailsModel.JobSeekerRecordListBean> data) {
            super(R.layout.item_recycler_has_match_resume_baomu, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final HasZhaopinPublishedDetailsModel.JobSeekerRecordListBean item) {
            helper.setText(R.id.tv_name, item.getTrue_name() + "    " + (item.getSex() == 1 ? "男" : "女"))
                    .setText(R.id.tv_location, item.getWork_second_area_name() + item.getWork_third_area_name());

            TextView textView = helper.getView(R.id.tv_pipeidu);
            textView.setText(new SpanUtils().append("匹配度：")
                    .append(item.getMatch_value() + "%")
                    .setForegroundColor(ResUtils.getCommRed())
                    .create());

            //定位
            helper.getView(R.id.tv_location).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (StringUtil.isAllNotEmpty(item.getLongitude(), item.getLatitude()))
                        MapNaviUtils.showDaoHangDialog(getActivity(),Double.parseDouble(item.getLongitude())  , Double.parseDouble(item.getLatitude()),"");
                }
            });

            GlideUtil.show(getActivity(), item.getUser_avatar_url(), (ImageView) helper.getView(R.id.iv_avatar));
        }
    }

}
