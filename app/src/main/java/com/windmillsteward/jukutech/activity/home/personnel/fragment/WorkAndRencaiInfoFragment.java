package com.windmillsteward.jukutech.activity.home.personnel.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.reflect.TypeToken;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.home.personnel.model.WorkRencaiModel;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.HasBmYsYesPublishedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.HasJiajiaoPublishedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.HasZhaopinPublishedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgUsePersonFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.HasLgTzgPublishedDetailsFragment;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseMultiItemQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.constant.AppConstant;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.manager.CommonActivityManager;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.TimeUtils;
import com.windmillsteward.jukutech.utils.http.DataCallBack;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkAndRencaiInfoFragment extends BaseInitFragment {
    public static final int TYPE_WORK = 1;
    public static final int TYPE_RENCAI = 2;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    private int type;
    private int roleType;

    private int page = 1;

    private List<WorkRencaiModel.ListBean> list;
    private RecyclerViewAdapter adapter;

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
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                           @Override
                                           public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                               if (type == TYPE_WORK) {//只有工作信息才可以点击进去
                                                   if (roleType == PageConfig.TYPE_ZHONGJIE || roleType == PageConfig.TYPE_TEZHONGGONG) {
                                                       //特种工劳务中介
                                                       Bundle bundle = new Bundle();
                                                       bundle.putInt("recruitment_id", list.get(position).getRecruitment_id());
                                                       bundle.putInt("roleType", roleType);
                                                       bundle.putInt("showAddress", 0);
                                                       startManagerActivity(CommonActivityManager.class, HasLgTzgPublishedDetailsFragment.TAG, bundle);
                                                   } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
                                                       //钟点工
                                                       Bundle bundle = new Bundle();
                                                       bundle.putInt("lookfor_bell_worker_id", list.get(position).getLookfor_bell_worker_id());
                                                       bundle.putInt("showAddress", 0);
                                                       startManagerActivity(CommonActivityManager.class, ZhongdgUsePersonFragment.TAG, bundle);
                                                   } else if (roleType == PageConfig.TYPE_BAOMU ||
                                                           roleType == PageConfig.TYPE_YUESAO ||
                                                           roleType == PageConfig.TYPE_YUERSAO) {
                                                       //保姆月嫂育儿嫂
                                                       Bundle bundle = new Bundle();
                                                       bundle.putInt("recruitment_id", list.get(position).getRecruitment_id());
                                                       bundle.putInt("roleType", roleType);
                                                       bundle.putInt("showAddress", 0);
                                                       startManagerActivity(CommonActivityManager.class, HasBmYsYesPublishedDetailsFragment.TAG, bundle);
                                                   } else if (roleType == PageConfig.TYPE_JIAJIAO) {
                                                       //家教
                                                       Bundle bundle = new Bundle();
                                                       bundle.putInt("look_for_tutor_id", list.get(position).getLook_for_tutor_id());
                                                       bundle.putInt("showAddress", 0);
                                                       startManagerActivity(CommonActivityManager.class, HasJiajiaoPublishedDetailsFragment.TAG, bundle);
                                                   }else if (roleType == PageConfig.TYPE_ZHAOPIN) {
                                                       //求职
                                                       Bundle bundle = new Bundle();
                                                       bundle.putInt("job_id", list.get(position).getJob_id());
                                                       bundle.putInt("showAddress", 0);
                                                       startManagerActivity(CommonActivityManager.class, HasZhaopinPublishedDetailsFragment.TAG, bundle);
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
            showTips(msg);
            adapter.loadMoreFail();
            showErrorView();
        }

        @Override
        protected void onSuccess(int code, BaseResultInfo<WorkRencaiModel> respnse, String source) {
            if (respnse.getData() != null && respnse.getData().getList() != null) {
                if (respnse.getData().isFirstPage()) {
                    list.clear();
                }
                for (WorkRencaiModel.ListBean listBean : respnse.getData().getList()) {
                    listBean.setType(type);
                    list.add(listBean);
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

    /**
     * 外部调用
     */
    public void refresh() {
        refreshPageData();
    }

    class RecyclerViewAdapter extends BaseMultiItemQuickAdapter<WorkRencaiModel.ListBean, BaseViewHolder> {
        public RecyclerViewAdapter(List<WorkRencaiModel.ListBean> data) {
            super(data);
            addItemType(TYPE_WORK, R.layout.item_recycler_talentinn_list);
            addItemType(TYPE_RENCAI, R.layout.item_recycler_fragment_rencai);
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
                    helper.setText(R.id.tv_time, TimeUtils.formatMillToYYYYMMDD(item.getAdd_time() * 1000l));
                    helper.setText(R.id.tv_address, item.getWork_second_area_name() + item.getWork_third_area_name());
                } else if (roleType == PageConfig.TYPE_BAOMU || roleType == PageConfig.TYPE_YUESAO || roleType == PageConfig.TYPE_YUERSAO) {
                    helper.setText(R.id.tv_title, item.getTitle());
                    helper.setText(R.id.tv_time, TimeUtils.formatMillToYYYYMMDD(item.getAdd_time() * 1000l));
                    helper.setText(R.id.tv_address, item.getArea_name());
                } else if (roleType == PageConfig.TYPE_JIAJIAO) {
                    helper.setText(R.id.tv_title, item.getTitle());
                    helper.setText(R.id.tv_time, TimeUtils.formatMillToYYYYMMDD(item.getAdd_time() * 1000l));
                    helper.setText(R.id.tv_address, item.getArea_name());
                } else if (roleType == PageConfig.TYPE_ZHAOPIN) {
                    helper.setText(R.id.tv_title, item.getTitle());
                    helper.setText(R.id.tv_time, item.getUpdate_time());
                    helper.setText(R.id.tv_address, item.getArea_name());
                } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
                    helper.setText(R.id.tv_title, item.getTitle());
                    helper.setText(R.id.tv_time, TimeUtils.formatMillToYYYYMMDD(item.getAdd_time() * 1000l));
                    helper.setText(R.id.tv_address, item.getWork_second_area_name() + item.getWork_third_area_name());
                }
            } else {
                GlideUtil.show(getActivity(), item.getUser_avatar_url(), (ImageView) helper.getView(R.id.iv_avatar));
                if (roleType == PageConfig.TYPE_ZHONGJIE || roleType == PageConfig.TYPE_TEZHONGGONG) {
                    helper.setText(R.id.tv_name, item.getName());
                    helper.setText(R.id.tv_info, (item.getSex() == 1 ? "男" : "女") + "  |  " + item.getAge() + "岁");
                    helper.setText(R.id.tv_location, item.getWork_second_area_name() + item.getWork_third_area_name());
                } else if (roleType == PageConfig.TYPE_BAOMU || roleType == PageConfig.TYPE_YUESAO || roleType == PageConfig.TYPE_YUERSAO) {
                    helper.setText(R.id.tv_name, item.getUser_name());
                    helper.setText(R.id.tv_info, (item.getSex() == 1 ? "男" : "女") + "  |  " + item.getAge() + "岁");
                    helper.setText(R.id.tv_location, item.getArea_name());
                } else if (roleType == PageConfig.TYPE_JIAJIAO) {
                    helper.setText(R.id.tv_name, item.getUser_name());
                    helper.setText(R.id.tv_info, item.getSex_name() + "  |  " + item.getAge() + "岁");
                    helper.setText(R.id.tv_location, item.getArea_name());
                } else if (roleType == PageConfig.TYPE_ZHAOPIN) {
                    helper.setText(R.id.tv_name, item.getTrue_name());
                    helper.setText(R.id.tv_info, (item.getSex() == 1 ? "男" : "女") + "  |  " + item.getAge() + "岁");
                    helper.setText(R.id.tv_location, item.getArea_name());
                } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
                    helper.setText(R.id.tv_name, item.getName());
                    helper.setText(R.id.tv_info, (item.getSex() == 1 ? "男" : "女") + "  |  " + item.getAge() + "岁");
                    helper.setText(R.id.tv_location, item.getWork_second_area_name() + item.getWork_third_area_name());
                }
            }
        }
    }
}
