package com.windmillsteward.jukutech.activity.newpage.newpublish;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.model.WorkRencaiModel;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.BmYsYesRencaiListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.BmYsYesWorkListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiaJiaoRencaiListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiaJiaoWorkListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.QiuZhiRenCaiListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.QiuZhiWorkListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZdgRencaiListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZdgWorkListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.LwTzRencaiListDetailFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.LwTzWorkListDetailFragment;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseMultiItemQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.constant.AppConstant;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.manager.CommonActivityManager;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.http.DataCallBack;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkAndRencaiInfoFragment extends BaseInitFragment {
    public static final int TYPE_WORK = 1;
    public static final int TYPE_RENCAI = 2;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
//    @Bind(R.id.common_refresh)
//    CommonRefreshLayout commonRefresh;

    private int type;//1工作列表2人才列表
    private int roleType;

    private int page = 1;

    private List<WorkRencaiModel.ListBean> list;
    private RecyclerViewAdapter adapter;
    private LinearLayout lay_ll_tips;

    public static WorkAndRencaiInfoFragment newInstance(int type, int roleType) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        args.putInt("roleType", roleType);
        WorkAndRencaiInfoFragment fragment = new WorkAndRencaiInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.layout_only_recyclerview;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        hidTitleView();

        if (getArguments() != null) {
            type = getArguments().getInt("type");
            roleType = getArguments().getInt("roleType");
        }
    }

    private void initHeader() {
        View headerView = View.inflate(getActivity(), R.layout.header_work_and_rencai_fragment, null);
        lay_ll_tips = (LinearLayout) headerView.findViewById(R.id.lay_ll_tips);
        adapter.addHeaderView(headerView);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        initHeader();
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

//        commonRefresh.setPtrHandler(new PtrDefaultHandler() {
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                refreshPageData();
//            }
//        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                           @Override
                                           public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                               if (type == TYPE_WORK) {
                                                   if (roleType == PageConfig.TYPE_ZHONGJIE || roleType == PageConfig.TYPE_TEZHONGGONG) {
                                                       //特种工劳务中介
                                                       Bundle bundle = new Bundle();
                                                       bundle.putInt("recruitment_id", list.get(position).getRecruitment_id());
                                                       bundle.putInt("roleType", roleType);
                                                       startManagerActivity(CommonActivityManager.class, LwTzWorkListDetailFragment.TAG, bundle);
                                                   } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
                                                       //钟点工
                                                       Bundle bundle = new Bundle();
                                                       bundle.putInt("lookfor_bell_worker_id", list.get(position).getLookfor_bell_worker_id());
                                                       startManagerActivity(CommonActivityManager.class, ZdgWorkListDetailFragment.TAG, bundle);
                                                   } else if (roleType == PageConfig.TYPE_BAOMU ||
                                                           roleType == PageConfig.TYPE_YUESAO ||
                                                           roleType == PageConfig.TYPE_YUERSAO) {
                                                       //保姆月嫂育儿嫂
                                                       Bundle bundle = new Bundle();
                                                       bundle.putInt("recruitment_id", list.get(position).getRecruitment_id());
                                                       bundle.putInt("roleType", roleType);
                                                       startManagerActivity(CommonActivityManager.class, BmYsYesWorkListDetailFragment.TAG, bundle);
                                                   } else if (roleType == PageConfig.TYPE_JIAJIAO) {
                                                       //家教
                                                       Bundle bundle = new Bundle();
                                                       bundle.putInt("look_for_tutor_id", list.get(position).getLook_for_tutor_id());
                                                       startManagerActivity(CommonActivityManager.class, JiaJiaoWorkListDetailFragment.TAG, bundle);
                                                   } else if (roleType == PageConfig.TYPE_ZHAOPIN) {
                                                       //求职
                                                       Bundle bundle = new Bundle();
                                                       bundle.putInt("job_id", list.get(position).getJob_id());
                                                       startManagerActivity(CommonActivityManager.class, QiuZhiWorkListDetailFragment.TAG, bundle);
                                                   }
                                               } else if (type == TYPE_RENCAI) {
                                                   if (roleType == PageConfig.TYPE_ZHONGJIE || roleType == PageConfig.TYPE_TEZHONGGONG) {
                                                       //特种工劳务中介
                                                       Bundle bundle = new Bundle();
                                                       bundle.putInt("info_id", list.get(position).getInfo_id());
                                                       bundle.putInt("roleType", roleType);
                                                       startManagerActivity(CommonActivityManager.class, LwTzRencaiListDetailFragment.TAG, bundle);

                                                   } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
                                                       //钟点工
                                                       Bundle bundle = new Bundle();
                                                       bundle.putInt("when_bell_worker_id", list.get(position).getWhen_bell_worker_id());
                                                       startManagerActivity(CommonActivityManager.class, ZdgRencaiListDetailFragment.TAG, bundle);
                                                   } else if (roleType == PageConfig.TYPE_BAOMU ||
                                                           roleType == PageConfig.TYPE_YUESAO ||
                                                           roleType == PageConfig.TYPE_YUERSAO) {
                                                       //保姆月嫂育儿嫂
                                                       Bundle bundle = new Bundle();
                                                       bundle.putInt("require_id", list.get(position).getRequire_id());
                                                       bundle.putInt("roleType", roleType);
                                                       startManagerActivity(CommonActivityManager.class, BmYsYesRencaiListDetailFragment.TAG, bundle);
                                                   } else if (roleType == PageConfig.TYPE_JIAJIAO) {
                                                       //家教
                                                       Bundle bundle = new Bundle();
                                                       bundle.putInt("when_tutor_id", list.get(position).getWhen_tutor_id());
                                                       startManagerActivity(CommonActivityManager.class, JiaJiaoRencaiListDetailFragment.TAG, bundle);
                                                   } else if (roleType == PageConfig.TYPE_ZHAOPIN) {
                                                       //求职
                                                       Bundle bundle = new Bundle();
                                                       bundle.putInt("resume_id", list.get(position).getResume_id());
                                                       startManagerActivity(CommonActivityManager.class, QiuZhiRenCaiListDetailFragment.TAG, bundle);
                                                   }
                                               }
                                           }
                                       }
        );
        getData();
    }

    /**
     * 获取数据
     */
    private void getData() {
        if (roleType == PageConfig.TYPE_ZHONGJIE || roleType == PageConfig.TYPE_TEZHONGGONG) {
            int query_type = 0;
            if (roleType == PageConfig.TYPE_ZHONGJIE) {
                query_type = 1;
            } else {
                query_type = 2;
            }
            new NetUtil().setUrl(APIS.URL_TALENT_LAOWU_HOME_LIST)
                    .addParams("page", page + "")
                    .addParams("page_count", 10 + "")
                    .addParams("type", type + "")
                    .addParams("query_type", query_type + "")
                    .addParams("second_area_id", Hawk.get(Define.CURR_CITY_ID, 0) + "")
                    .addParams("third_area_id", Hawk.get(Define.CURR_CITY_THIRD_ID, 0) + "")
                    .setCallBackData(callBack).buildPost();
        } else if (roleType == PageConfig.TYPE_BAOMU || roleType == PageConfig.TYPE_YUESAO || roleType == PageConfig.TYPE_YUERSAO) {
            if (type == TYPE_WORK) {
                //工作信息
                getBaomuWorkData();
            } else {
                //人才信息列表
                getBaomuRencaiData();
            }
        } else if (roleType == PageConfig.TYPE_JIAJIAO) {
            //家教
            if (type == TYPE_WORK) {
                //工作信息
                getJiajiaoWorkData();
            } else {
                //人才信息列表
                getJiajiaoRencaiData();
            }
        } else if (roleType == PageConfig.TYPE_ZHAOPIN) {
            //求职招聘
            if (type == TYPE_WORK) {
                //工作信息
                getZhaopinWorkData();
            } else {
                //人才信息列表
                getZhaopinRencaiData();
            }
        } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
            //钟点工 1.工作信息列表 2.人才信息列表
            int type1 = 0;
            if (type == TYPE_WORK) {
                //工作信息
                type1 = 1;
            } else {
                //人才信息列表
                type1 = 2;
            }
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_LIST)
                    .addParams("page", page + "")
                    .addParams("page_count", 10 + "")
                    .addParams("type", type1 + "")
                    .addParams("second_area_id", Hawk.get(Define.CURR_CITY_ID, 0) + "")
                    .addParams("third_area_id", Hawk.get(Define.CURR_CITY_THIRD_ID, 0) + "")
                    .setCallBackData(callBack).buildPost()
            );
        }
    }

    //获取求职招聘 人才信息
    private void getZhaopinRencaiData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_INDEX_PERSONNEL_INFORMATION_LIST)
                .addParams("page", page + "")
                .addParams("page_count", 10 + "")
                .addParams("second_area_id", Hawk.get(Define.CURR_CITY_ID, 0) + "")
                .addParams("third_area_id", Hawk.get(Define.CURR_CITY_THIRD_ID, 0) + "")
                .setCallBackData(callBack).buildPost()
        );
    }

    //获取求职招聘 工作信息
    private void getZhaopinWorkData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_INDEX_JOP_INFORMATION_LIST)
                .addParams("page", page + "")
                .addParams("page_count", 10 + "")
                .addParams("second_area_id", Hawk.get(Define.CURR_CITY_ID, 0) + "")
                .addParams("third_area_id", Hawk.get(Define.CURR_CITY_THIRD_ID, 0) + "")
                .setCallBackData(callBack).buildPost()
        );
    }

    //获取家教人才信息
    private void getJiajiaoRencaiData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_WHEN_TUTOR_LIST)
                .addParams("page", page + "")
                .addParams("page_count", 10 + "")
                .addParams("second_area_id", Hawk.get(Define.CURR_CITY_ID, 0) + "")
                .addParams("third_area_id", Hawk.get(Define.CURR_CITY_THIRD_ID, 0) + "")
                .setCallBackData(callBack).buildPost()
        );
    }

    //获取家教工作信息
    private void getJiajiaoWorkData() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_LOOK_FOR_TUTOR_LIST)
                .addParams("page", page + "")
                .addParams("page_count", 10 + "")
                .addParams("second_area_id", Hawk.get(Define.CURR_CITY_ID, 0) + "")
                .addParams("third_area_id", Hawk.get(Define.CURR_CITY_THIRD_ID, 0) + "")
                .setCallBackData(callBack).buildPost()
        );
    }

    private DataCallBack callBack = new BaseNewNetModelimpl<WorkRencaiModel>() {
        @Override
        protected void onFail(int type, String msg) {
//            commonRefresh.refreshComplete();
            showTips(msg);
            adapter.loadMoreFail();
            showErrorView();
        }

        @Override
        protected void onSuccess(int code, BaseResultInfo<WorkRencaiModel> respnse, String source) {
            if (respnse.getData() != null && respnse.getData().getList() != null) {
//                commonRefresh.refreshComplete();

                if (respnse.getData().isFirstPage()) {
                    list.clear();
                }
                for (WorkRencaiModel.ListBean listBean : respnse.getData().getList()) {
                    listBean.setType(type);
                    list.add(listBean);
                }
                if (list != null) {
                    if (list.size() > 0) {
                        lay_ll_tips.setVisibility(View.VISIBLE);
                    } else {
                        lay_ll_tips.setVisibility(View.GONE);
                    }
                }
                if (respnse.getData().isLastPage()) {
                    adapter.loadMoreEnd();
                } else {
                    adapter.loadMoreComplete();
                }
                adapter.notifyDataSetChanged();
            }
            page++;
            showContentView();
        }

        @Override
        protected Type getType() {
            return new TypeToken<BaseResultInfo<WorkRencaiModel>>() {
            }.getType();
        }
    };

    //保姆 月嫂 育儿嫂 工作信息
    private void getBaomuWorkData() {
        int type = 0;
        switch (roleType) {
            case PageConfig.TYPE_BAOMU:
                type = 1;
                break;
            case PageConfig.TYPE_YUESAO:
                type = 2;
                break;
            case PageConfig.TYPE_YUERSAO:
                type = 3;
                break;
        }
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_WORK_REQUIRE_LIST)
                .addParams("page", page + "")
                .addParams("page_count", 10 + "")
                .addParams("require_type", type + "")
                .addParams("second_area_id", Hawk.get(Define.CURR_CITY_ID, 0) + "")
                .addParams("third_area_id", Hawk.get(Define.CURR_CITY_THIRD_ID, 0) + "")
                .setCallBackData(callBack).buildPost()
        );
    }

    //保姆 月嫂 育儿嫂 人才信息
    private void getBaomuRencaiData() {
        //	分类 1.保姆 2.月嫂，3.育儿嫂
        int type = 0;
        switch (roleType) {
            case PageConfig.TYPE_BAOMU:
                type = 1;
                break;
            case PageConfig.TYPE_YUESAO:
                type = 2;
                break;
            case PageConfig.TYPE_YUERSAO:
                type = 3;
                break;
        }
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_REQUIRE_LIST)
                .addParams("page", page + "")
                .addParams("page_count", 10 + "")
                .addParams("require_type", type + "")
                .addParams("second_area_id", Hawk.get(Define.CURR_CITY_ID, 0) + "")
                .addParams("third_area_id", Hawk.get(Define.CURR_CITY_THIRD_ID, 0) + "")
                .setCallBackData(callBack).buildPost()
        );
    }

    @Override
    protected void refreshPageData() {
        list.clear();
        page = 1;
        getData();
    }


    private void addClick(TextView tv, final int type, final int roleType, final int qz_id) {
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
                final Intent intent = new Intent(getActivity(), HomeCommonPublishActivity.class);
                intent.putExtra(HomeCommonPublishActivity.MODULE_TYPE, roleType);
                intent.putExtra(HomeCommonPublishActivity.QZ_ID, qz_id);
                if (type == TYPE_WORK) {
                    intent.putExtra(HomeCommonPublishActivity.SHOW_PAGE, HomeCommonPublishActivity.YINGPIN);
                } else {
                    intent.putExtra(HomeCommonPublishActivity.SHOW_PAGE, HomeCommonPublishActivity.ZHAOPIN);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                        startActivity(intent);
                    }
                }, 500);
            }
        });
    }

    /**
     * 外部调用
     */
    public void refresh() {
        refreshPageData();
    }


    class RecyclerViewAdapter extends BaseMultiItemQuickAdapter<WorkRencaiModel.ListBean, BaseViewHolder> {
        public RecyclerViewAdapter(List<WorkRencaiModel.ListBean> data) {
            super(data);
            addItemType(TYPE_WORK, R.layout.item_recycler_work_list);
            addItemType(TYPE_RENCAI, R.layout.item_recycler_rencai_list);
        }

        @Override
        protected void convert(BaseViewHolder helper, WorkRencaiModel.ListBean item) {
            if (item.getItemType() == TYPE_WORK) {
                if (helper.getPosition() == 0) {
                    helper.getView(R.id.view_line).setVisibility(View.GONE);
                } else {
                    helper.getView(R.id.view_line).setVisibility(View.VISIBLE);
                }
                if (roleType == PageConfig.TYPE_ZHONGJIE || roleType == PageConfig.TYPE_TEZHONGGONG) {
                    helper.setText(R.id.tv_title, item.getTitle());
                    helper.setText(R.id.tv_work_type, item.getWork_type_name());
                    if (StringUtil.isAllNotEmpty(item.getWork_second_area_name(), item.getWork_third_area_name())) {
                        helper.setText(R.id.tv_diqu, item.getWork_second_area_name() + item.getWork_third_area_name());
                    }
                    helper.setText(R.id.tv_company, item.getEnterprise_name());
                    int salary_type = item.getSalary_type();
                    if (salary_type == 1) {
                        helper.setText(R.id.tv_xinzi, "市场定价");
                    } else if (salary_type == 2) {
                        if (StringUtil.isAllNotEmpty(item.getSalary_fee(), item.getEnd_salary_fee())) {
                            helper.setText(R.id.tv_xinzi, item.getSalary_fee() + "-" + item.getEnd_salary_fee() + "元/月");
                        }
                    } else if (salary_type == 3) {
                        helper.setText(R.id.tv_xinzi, "面议");
                    }
                    RecyclerView mRecyclerView = (RecyclerView) helper.getView(R.id.mRecyclerView);
                    mRecyclerView.setVisibility(View.GONE);
//                    addClick((TextView) helper.getView(R.id.tv_apply), TYPE_WORK, roleType == 0 ? PageConfig.TYPE_ZHONGJIE : PageConfig.TYPE_TEZHONGGONG, item.getRecruitment_id());
                } else if (roleType == PageConfig.TYPE_BAOMU || roleType == PageConfig.TYPE_YUESAO || roleType == PageConfig.TYPE_YUERSAO) {
                    helper.setText(R.id.tv_title, item.getTitle());
                    StringBuffer sb = new StringBuffer();
                    if (item.getService_contents() != null) {
                        for (String s : item.getService_contents()) {
                            sb.append(s + ",");
                        }
                    }
                    String result = sb.toString();
                    if (!TextUtils.isEmpty(result)) {
                        helper.setText(R.id.tv_work_type, result.substring(0, result.length() - 1));
                    }
                    helper.setText(R.id.tv_diqu, item.getArea_name());
                    helper.setText(R.id.tv_company, item.getEnterprise_name());
                    helper.setText(R.id.tv_xinzi, item.getSalary());
                    RecyclerView mRecyclerView = (RecyclerView) helper.getView(R.id.mRecyclerView);
                    mRecyclerView.setVisibility(View.GONE);
//                    addClick((TextView) helper.getView(R.id.tv_apply), TYPE_WORK, roleType, item.getRecruitment_id());
                } else if (roleType == PageConfig.TYPE_JIAJIAO) {
                    helper.setText(R.id.tv_title, item.getTitle());
                    helper.setText(R.id.tv_work_type, item.getCoach_subject_name());
                    helper.setText(R.id.tv_diqu, item.getArea_name());
                    helper.setText(R.id.tv_company, item.getEnterprise_name());
                    helper.setText(R.id.tv_xinzi, item.getSalary());
                    RecyclerView mRecyclerView = (RecyclerView) helper.getView(R.id.mRecyclerView);
                    mRecyclerView.setVisibility(View.GONE);
//                    addClick((TextView) helper.getView(R.id.tv_apply), TYPE_WORK, roleType, item.getLook_for_tutor_id());
                } else if (roleType == PageConfig.TYPE_ZHAOPIN) {
                    helper.setText(R.id.tv_title, item.getTitle());
                    helper.setText(R.id.tv_work_type, item.getJob_name());
                    helper.setText(R.id.tv_diqu, item.getArea_name());
                    String company_name = item.getCompany_name();
                    if (TextUtils.isEmpty(company_name)) {
                        helper.setText(R.id.tv_company, "个人");
                    } else {
                        helper.setText(R.id.tv_company, company_name);
                    }
                    int salary_type = item.getSalary_type();
                    if (salary_type == 1) {
                        helper.setText(R.id.tv_xinzi, "市场定价");
                    } else if (salary_type == 2) {
                        if (StringUtil.isAllNotEmpty(item.getSalary_fee(), item.getEnd_salary_fee())) {
                            helper.setText(R.id.tv_xinzi, item.getSalary_fee() + "-" + item.getEnd_salary_fee() + "元/月");
                        }
                    } else if (salary_type == 3) {
                        helper.setText(R.id.tv_xinzi, "面议");
                    }
                    RecyclerView mRecyclerView = (RecyclerView) helper.getView(R.id.mRecyclerView);
                    String benefit_name = item.getBenefit_name();
                    if (!TextUtils.isEmpty(benefit_name)) {
                        List<String> benefit_list = Arrays.asList(benefit_name.split(","));
                        mRecyclerView.setVisibility(View.VISIBLE);
                        if (benefit_list != null) {
                            FuliRecyclerViewAdapter adapter = new FuliRecyclerViewAdapter(benefit_list);
                            LinearLayoutManager llManager = new LinearLayoutManager(getActivity());
                            llManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                            mRecyclerView.setLayoutManager(llManager);
                            mRecyclerView.setAdapter(adapter);
                        }
                    } else {
                        mRecyclerView.setVisibility(View.GONE);
                    }
//                    addClick((TextView) helper.getView(R.id.tv_apply), TYPE_WORK, roleType, item.getJob_id());
                } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
                    helper.setText(R.id.tv_title, item.getTitle())
                            .setText(R.id.tv_work_type, item.getService_name())
                            .setText(R.id.tv_xinzi, item.getSalary())
                            .setText(R.id.tv_company, item.getEnterprise_name());
                    if (StringUtil.isAllNotEmpty(item.getWork_second_area_name(), item.getWork_third_area_name())) {
                        helper.setText(R.id.tv_diqu, item.getWork_second_area_name() + item.getWork_third_area_name());
                    }

//                    addClick((TextView) helper.getView(R.id.tv_apply), TYPE_WORK, roleType, item.getLookfor_bell_worker_id());
                }
            } else {//人才信息列表
                GlideUtil.show(getActivity(), item.getUser_avatar_url(), (ImageView) helper.getView(R.id.iv_avatar));
                if (roleType == PageConfig.TYPE_ZHONGJIE || roleType == PageConfig.TYPE_TEZHONGGONG) {
                    helper.setText(R.id.tv_name, item.getName());
                    helper.setText(R.id.tv_sex_and_age, (item.getSex() == 1 ? "男" : "女") + "  |  " + item.getAge() + "岁");
                    helper.setText(R.id.tv_time, item.getTime());
                    helper.setText(R.id.tv_diqu, item.getWork_second_area_name() + item.getWork_third_area_name());
                    helper.setText(R.id.tv_edu, item.getEducation_background_name());
                    List<String> work_type_ids = item.getWork_type_ids();
                    if (work_type_ids != null) {
                        StringBuffer sb = new StringBuffer();
                        for (String name : work_type_ids) {
                            sb.append(name + ",");
                        }
                        String result = sb.toString();
                        if (!TextUtils.isEmpty(result)) {
                            helper.setText(R.id.tv_work_type, result.substring(0, result.length() - 1));
                        }
                    }
                    int salary_type = item.getSalary_type();
                    if (salary_type == 1) {
                        helper.setText(R.id.tv_xinzi, "市场定价");
                    } else if (salary_type == 2) {
                        if (StringUtil.isAllNotEmpty(item.getSalary_fee(), item.getEnd_salary_fee())) {
                            helper.setText(R.id.tv_xinzi, item.getSalary_fee() + "-" + item.getEnd_salary_fee() + "元/月");
                        }
                    } else if (salary_type == 3) {
                        helper.setText(R.id.tv_xinzi, "面议");
                    }
//                    addClick((TextView) helper.getView(R.id.tv_apply), TYPE_RENCAI, roleType == 0 ? PageConfig.TYPE_ZHONGJIE : PageConfig.TYPE_TEZHONGGONG, item.getInfo_id());
                } else if (roleType == PageConfig.TYPE_BAOMU || roleType == PageConfig.TYPE_YUESAO || roleType == PageConfig.TYPE_YUERSAO) {
                    helper.setText(R.id.tv_name, item.getUser_name());
                    helper.setText(R.id.tv_sex_and_age, (item.getSex() == 1 ? "男" : "女") + "  |  " + item.getAge() + "岁");
                    helper.setText(R.id.tv_time, item.getTime());
                    helper.setText(R.id.tv_diqu, item.getArea_name());
                    helper.setText(R.id.tv_edu, item.getEducation_background_name());
                    helper.setText(R.id.tv_work_type, item.getPerson_name());
                    helper.setText(R.id.tv_xinzi, item.getSalary());
//                    addClick((TextView) helper.getView(R.id.tv_apply), TYPE_RENCAI, roleType, item.getRequire_id());
                } else if (roleType == PageConfig.TYPE_JIAJIAO) {
                    helper.setText(R.id.tv_name, item.getUser_name());
                    helper.setText(R.id.tv_sex_and_age, (item.getSex() == 1 ? "男" : "女") + "  |  " + item.getAge() + "岁");
                    helper.setText(R.id.tv_time, item.getTime());
                    helper.setText(R.id.tv_diqu, item.getArea_name());
                    helper.setText(R.id.tv_edu, item.getEducation_background_name());
                    helper.setText(R.id.tv_work_type, item.getCoach_subject_name());
                    helper.setText(R.id.tv_xinzi, item.getSalary());
//                    addClick((TextView) helper.getView(R.id.tv_apply), TYPE_RENCAI, roleType, item.getWhen_tutor_id());
                } else if (roleType == PageConfig.TYPE_ZHAOPIN) {
                    helper.setText(R.id.tv_name, item.getTrue_name());
                    helper.setText(R.id.tv_sex_and_age, (item.getSex() == 1 ? "男" : "女") + "  |  " + item.getAge() + "岁");
                    helper.setText(R.id.tv_time, item.getTime());
                    helper.setText(R.id.tv_diqu, item.getArea_name());
                    helper.setText(R.id.tv_edu, item.getEducation_background_name());
                    helper.setText(R.id.tv_work_type, item.getJob_name());
                    int salary_type = item.getSalary_type();
                    if (salary_type == 1) {
                        helper.setText(R.id.tv_xinzi, "市场定价");
                    } else if (salary_type == 2) {
                        if (StringUtil.isAllNotEmpty(item.getSalary_fee(), item.getEnd_salary_fee())) {
                            helper.setText(R.id.tv_xinzi, item.getSalary_fee() + "-" + item.getEnd_salary_fee() + "元/月");
                        }
                    } else if (salary_type == 3) {
                        helper.setText(R.id.tv_xinzi, "面议");
                    }
//                    addClick((TextView) helper.getView(R.id.tv_apply), TYPE_RENCAI, roleType, item.getResume_id());
                } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
                    helper.setText(R.id.tv_name, item.getName());
                    helper.setText(R.id.tv_sex_and_age, item.getSex_name() + "  |  " + item.getAge() + "岁");
                    helper.setText(R.id.tv_time, item.getTime());
                    helper.setText(R.id.tv_xinzi, item.getSalary());
                    helper.setText(R.id.tv_edu, "");
                    helper.setText(R.id.tv_work_type, item.getService_name());
                    if (StringUtil.isAllNotEmpty(item.getWork_second_area_name(), item.getWork_third_area_name())) {
                        helper.setText(R.id.tv_diqu, item.getWork_second_area_name() + item.getWork_third_area_name());
                    }
//                    addClick((TextView) helper.getView(R.id.tv_apply), TYPE_RENCAI, roleType, item.getWhen_bell_worker_id());
                }
            }
        }
    }

    class FuliRecyclerViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public FuliRecyclerViewAdapter(@Nullable List<String> data) {
            super(R.layout.item_recycler_work_fuli, data, false);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final String item) {
            helper.setText(R.id.tv_name, StringUtil.notEmptyBackValue(item));
        }
    }
}
