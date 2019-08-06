package com.windmillsteward.jukutech.activity.newpage.yizhan;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.model.PublishedPositionModel;
import com.windmillsteward.jukutech.activity.newpage.model.ZhaoPinHasPublicModel;
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
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.manager.CommonActivityManager;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.MapNaviUtils;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.http.DataCallBack;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 * 已发布职位的具体页面
 */
public class HasPublishedPositionChildFragment extends BaseInitFragment {
    public static final String TAG = "HasPublishedPositionChildFragment";
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout commonRefresh;

    private int page = 1;

    private int roleType;

    public static HasPublishedPositionChildFragment newInstance(int roleType, int status) {
        Bundle args = new Bundle();
        args.putInt("type", roleType);
        args.putInt("status", status);
        HasPublishedPositionChildFragment fragment = new HasPublishedPositionChildFragment();
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
            roleType = getArguments().getInt("type");
        }

        commonRefresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshPageData();
            }
        });
    }

    @Override
    protected void initData() {
        initAdapter();

        page = 1;
        getData(roleType, getArguments().getInt("status"));
    }

    @Override
    protected void refreshPageData() {
        page = 1;
        getData(getArguments().getInt("type"), getArguments().getInt("status"));
    }

    private RecyclerViewAdapter adapter;
    private List<PublishedPositionModel.ListBean> list;

    public void initAdapter() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        adapter.setEnableLoadMore(true);
        adapter.setLoadMoreEndText(AppConstant.TO_BOTTOM);

        //加载更多
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getData(getArguments().getInt("type"), getArguments().getInt("status"));
            }
        }, recyclerView);

        //事件监听
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (roleType == PageConfig.TYPE_ZHONGJIE || roleType == PageConfig.TYPE_TEZHONGGONG) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("recruitment_id", list.get(position).getRecruitment_id());
                    bundle.putInt("roleType", roleType);
                    bundle.putInt("showAddress", 1);
                    startManagerActivity(CommonActivityManager.class, HasLgTzgPublishedDetailsFragment.TAG, bundle);
                } else if (roleType == PageConfig.TYPE_BAOMU || roleType == PageConfig.TYPE_YUESAO || roleType == PageConfig.TYPE_YUERSAO) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("recruitment_id", list.get(position).getRecruitment_id());
                    bundle.putInt("roleType",roleType);
                    bundle.putInt("showAddress", 1);
                    startManagerActivity(CommonActivityManager.class, HasBmYsYesPublishedDetailsFragment.TAG, bundle);
                } else if (roleType == PageConfig.TYPE_JIAJIAO) {
                    //家教
                    Bundle bundle = new Bundle();
                    bundle.putInt("look_for_tutor_id", list.get(position).getLook_for_tutor_id());
                    bundle.putInt("showAddress", 1);
                    startManagerActivity(CommonActivityManager.class, HasJiajiaoPublishedDetailsFragment.TAG, bundle);
                } else if (roleType == PageConfig.TYPE_ZHAOPIN) {
                    //招聘
                    Bundle bundle = new Bundle();
                    bundle.putInt("job_id", list.get(position).getJob_id());
                    bundle.putInt("showAddress", 1);
                    startManagerActivity(CommonActivityManager.class, HasZhaopinPublishedDetailsFragment.TAG, bundle);
                } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
                    //钟点工
                    Bundle bundle = new Bundle();
                    bundle.putInt("lookfor_bell_worker_id", list.get(position).getLookfor_bell_worker_id());
                    bundle.putInt("roleType",roleType);
                    bundle.putInt("showAddress", 1);
                    startManagerActivity(CommonActivityManager.class, ZhongdgUsePersonFragment.TAG, bundle);
                }
            }
        });
    }

    class RecyclerViewAdapter extends BaseMultiItemQuickAdapter<PublishedPositionModel.ListBean, BaseViewHolder> {

        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param data A new list is created out of this one to avoid mutable list
         */
        public RecyclerViewAdapter(List<PublishedPositionModel.ListBean> data) {
            super(data);
            if (roleType == PageConfig.TYPE_ZHONGJIE ||
                    roleType == PageConfig.TYPE_TEZHONGGONG ||
                    roleType == PageConfig.TYPE_BAOMU ||
                    roleType == PageConfig.TYPE_YUESAO ||
                    roleType == PageConfig.TYPE_YUERSAO ||
                    roleType == PageConfig.TYPE_JIAJIAO ||
                    roleType == PageConfig.TYPE_ZHAOPIN ||
                    roleType == PageConfig.TYPE_ZHONGDIANGONG) {
                addItemType(roleType, R.layout.item_recycler_publish_position);
            }
        }

        @Override
        protected void convert(BaseViewHolder helper, final PublishedPositionModel.ListBean item) {
            if (item.getType() == PageConfig.TYPE_ZHONGJIE ||
                    item.getType() == PageConfig.TYPE_TEZHONGGONG) {
                helper.setText(R.id.tv_title, item.getTitle())
                        .setText(R.id.tv_date, item.getUpdate_time())
                        .setText(R.id.tv_location, item.getWork_second_area_name() + item.getWork_third_area_name())
                        .setText(R.id.tv_tips, "还差" + item.getNum() + "人");

                helper.getView(R.id.tv_tips).setVisibility(View.VISIBLE);
            } else if (item.getType() == PageConfig.TYPE_BAOMU ||
                    item.getType() == PageConfig.TYPE_YUESAO ||
                    item.getType() == PageConfig.TYPE_YUERSAO) {
                helper.setText(R.id.tv_title, item.getTitle())
                        .setText(R.id.tv_date, DateUtil.toYYYYMMDD(item.getAdd_time() * 1000))
                        .setText(R.id.tv_location, item.getArea_name());

                helper.getView(R.id.tv_tips).setVisibility(View.GONE);

                helper.getView(R.id.tv_location).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            } else if (item.getType() == PageConfig.TYPE_JIAJIAO) {
                //家教
                helper.setText(R.id.tv_title, item.getTitle())
                        .setText(R.id.tv_date, DateUtil.toYYYYMMDD(item.getAdd_time() * 1000))
                        .setText(R.id.tv_location, item.getArea_name());

                helper.getView(R.id.tv_tips).setVisibility(View.GONE);

                helper.getView(R.id.tv_location).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (StringUtil.isAllNotEmpty(item.getLongitude(), item.getLatitude())) {
                            MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(item.getLongitude()), Double.parseDouble(item.getLatitude()), "");
                        }
                    }
                });
            } else if (item.getType() == PageConfig.TYPE_ZHONGDIANGONG) {
                //钟点工
                helper.setText(R.id.tv_title, item.getTitle())
                        .setText(R.id.tv_date, DateUtil.toYYYYMMDD(item.getAdd_time() * 1000))
                        .setText(R.id.tv_location, item.getWork_second_area_name() + item.getWork_third_area_name())
                        .setText(R.id.tv_tips, "还差" + item.getNum() + "人");

                helper.getView(R.id.tv_tips).setVisibility(View.VISIBLE);
            } else if (item.getType() == PageConfig.TYPE_ZHAOPIN) {
                //求职招聘
                helper.setText(R.id.tv_title, item.getTitle())
                        .setText(R.id.tv_date, item.getUpdate_time())
                        .setText(R.id.tv_location, item.getWork_second_area_name() + item.getWork_third_area_name())
                        .setText(R.id.tv_tips, "还差" + item.getDifference_num()+ "人");

                helper.getView(R.id.tv_tips).setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 获取数据
     */
    private void getData(final int roleType, int status) {
        if (roleType == PageConfig.TYPE_ZHONGJIE ||
                roleType == PageConfig.TYPE_TEZHONGGONG) {
            //模块类型：1：劳工 2 特种工
            int type = 0;
            switch (roleType) {
                case PageConfig.TYPE_ZHONGJIE:
                    type = 1;
                    break;
                case PageConfig.TYPE_TEZHONGGONG:
                    type = 2;
                    break;
            }
            //劳工 特种工 模块
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_PUBLISHED_POSITION)
                    .addParams("page", page + "")
                    .addParams("page_count", 10 + "")
                    .addParams("type", type + "")
                    .addParams("status", status + "")
                    .setCallBackData(laogongBaomuJiajiaoZdgCallBack).buildPost()
            );
        } else if (roleType == PageConfig.TYPE_BAOMU ||
                roleType == PageConfig.TYPE_YUESAO ||
                roleType == PageConfig.TYPE_YUERSAO) {
            //模块类型：1：保姆，2：月嫂，3：育儿嫂
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
            //保姆 月嫂 育儿嫂模块
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_POSTED_RECRUITMENT_LIST)
                    .addParams("page", page + "")
                    .addParams("page_count", 10 + "")
                    .addParams("require_type", type + "")
                    .addParams("type", status + "")
                    .setCallBackData(laogongBaomuJiajiaoZdgCallBack)
                    .buildPost()
            );
        } else if (roleType == PageConfig.TYPE_JIAJIAO) {
            //家教
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_POSTED_LOOKFOR_TUTOR_LIST)
                    .addParams("page", page + "")
                    .addParams("page_count", 10 + "")
                    .addParams("type", status + "")
                    .setCallBackData(laogongBaomuJiajiaoZdgCallBack)
                    .buildPost()
            );
        } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
            //钟点工
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_PUBLISHED_POSITION_ZDG)
                    .addParams("page", page + "")
                    .addParams("page_count", 10 + "")
                    .addParams("status", status + "")
                    .setCallBackData(laogongBaomuJiajiaoZdgCallBack)
                    .buildPost()
            );
        } else if (roleType == PageConfig.TYPE_ZHAOPIN) {
            //求职招聘
            addCall(new NetUtil().setUrl(APIS.URL_TALENT_PUBLISHED_JOB_LIST)
                    .addParams("status", status + "")
                    .setCallBackData(new BaseNewNetModelimpl<List<ZhaoPinHasPublicModel>>() {
                        @Override
                        protected void onFail(int type, String msg) {
                            showErrorView();
                            commonRefresh.refreshComplete();
                        }

                        @Override
                        protected void onSuccess(int code, BaseResultInfo<List<ZhaoPinHasPublicModel>> respnse, String source) {
                            commonRefresh.refreshComplete();
                            showContentView();
                            if (respnse.getData() != null) {
                                List<ZhaoPinHasPublicModel> data = respnse.getData();
                                if (page == 1) {
                                    list.clear();
                                }
                                for (ZhaoPinHasPublicModel bean : data) {
                                    PublishedPositionModel.ListBean listBean = new PublishedPositionModel.ListBean();
                                    listBean.setType(roleType);
                                    listBean.setTitle(bean.getTitle());
                                    listBean.setJob_id(bean.getJob_id());
                                    listBean.setJob_name(bean.getJob_name());
                                    listBean.setRecruitment_num(bean.getRecruitment_num());
                                    listBean.setUpdate_time(bean.getUpdate_time());
                                    listBean.setWork_second_area_id(bean.getWork_second_area_id());
                                    listBean.setWork_second_area_name(bean.getWork_second_area_name());
                                    listBean.setWork_third_area_id(bean.getWork_third_area_id());
                                    listBean.setWork_third_area_name(bean.getWork_third_area_name());
                                    listBean.setDifference_num(bean.getDifference_num());
                                    list.add(listBean);
                                }
                                adapter.loadMoreEnd();
                                adapter.notifyDataSetChanged();
                            }
                            page++;

                        }

                        @Override
                        protected Type getType() {
                            return new TypeToken<BaseResultInfo<List<ZhaoPinHasPublicModel>>>() {
                            }.getType();
                        }
                    })
                    .buildPost()
            );
        }
    }

    //仅供 劳工 特种 保姆 月嫂 育儿嫂使用
    private DataCallBack laogongBaomuJiajiaoZdgCallBack = new BaseNewNetModelimpl<PublishedPositionModel>() {
        @Override
        protected void onFail(int type, String msg) {
            showErrorView();
            commonRefresh.refreshComplete();
        }

        @Override
        protected void onSuccess(int code, BaseResultInfo<PublishedPositionModel> respnse, String source) {
            commonRefresh.refreshComplete();
            showContentView();
            if (respnse.getData() != null) {
                PublishedPositionModel data = respnse.getData();
                if (data.isFirstPage()) {
                    list.clear();
                }
                for (PublishedPositionModel.ListBean listBean : data.getList()) {
                    listBean.setType(roleType);
                    list.add(listBean);
                }
                if (data.isLastPage()) {
                    adapter.loadMoreEnd();
                } else {
                    adapter.loadMoreComplete();
                }
                adapter.notifyDataSetChanged();
            }
            page++;
        }

        @Override
        protected Type getType() {
            return new TypeToken<BaseResultInfo<PublishedPositionModel>>() {
            }.getType();
        }
    };
}
