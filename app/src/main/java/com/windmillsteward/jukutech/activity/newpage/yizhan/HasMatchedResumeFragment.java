package com.windmillsteward.jukutech.activity.newpage.yizhan;


import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.model.BmYsYesFindMatchModel;
import com.windmillsteward.jukutech.activity.newpage.model.CommMatchedResumeModel;
import com.windmillsteward.jukutech.activity.newpage.model.HasMatchResumeModel;
import com.windmillsteward.jukutech.activity.newpage.model.JiajiaoFindMatchModel;
import com.windmillsteward.jukutech.activity.newpage.model.QiuzhiFindMatchModel;
import com.windmillsteward.jukutech.activity.newpage.model.ZhongdgFindMatchModel;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.BaomuInfoDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.JiajiaoInfoDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.ZhaopinInfoDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgHasMatchedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.JianliDetailsFragment;
import com.windmillsteward.jukutech.base.BackFragmentActivity;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseMultiItemQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.constant.AppConstant;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.event.NotifyPageRefresh;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.MapNaviUtils;
import com.windmillsteward.jukutech.utils.ResUtils;

import org.greenrobot.eventbus.Subscribe;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * A simple {@link Fragment} subclass.
 * 已匹配到的简历
 */
public class HasMatchedResumeFragment extends BaseInitFragment {
    public static final String TAG = "HasMatchedResumeFragment";
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout commonRefresh;

    private int page = 1;
    private int roleType;

    public static HasMatchedResumeFragment newInstance(int roleType) {
        Bundle args = new Bundle();
        args.putInt("roleType", roleType);
        HasMatchedResumeFragment fragment = new HasMatchedResumeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.layout_only_recyclerview_with_refresh;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {

        if (getArguments() != null) {
            roleType = getArguments().getInt("roleType");
        }

        switch (roleType) {
            case PageConfig.TYPE_ZHONGJIE:
                setMainTitle("已匹配到的简历");
                break;
            case PageConfig.TYPE_TEZHONGGONG:
                setMainTitle("已匹配到的特种工");
                break;
            case PageConfig.TYPE_BAOMU:
                setMainTitle("已匹配到的保姆");
                break;
            case PageConfig.TYPE_YUESAO:
                setMainTitle("已匹配到的月嫂");
                break;
            case PageConfig.TYPE_YUERSAO:
                setMainTitle("已匹配到的育儿嫂");
                break;
            case PageConfig.TYPE_JIAJIAO:
                setMainTitle("已匹配到的家教");
                break;
            case PageConfig.TYPE_ZHONGDIANGONG:
                setMainTitle("已匹配到的钟点工");
                break;
            case PageConfig.TYPE_ZHAOPIN:
                setMainTitle("已匹配到的简历");
                break;
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

        getData(roleType);
    }

    @Override
    protected void refreshPageData() {
        page = 1;
        getData(roleType);
    }

    private void getData(int roleType) {
        if (roleType == PageConfig.TYPE_ZHONGJIE || roleType == PageConfig.TYPE_TEZHONGGONG) {
            //获取劳工 特种工 匹配信息
            getLgTzgInfo(roleType);
        } else if (roleType == PageConfig.TYPE_BAOMU ||
                roleType == PageConfig.TYPE_YUESAO ||
                roleType == PageConfig.TYPE_YUERSAO) {
            //获取 保姆 月嫂 育儿嫂 匹配信息
            getBmYsYesInfo(roleType);
        } else if (roleType == PageConfig.TYPE_JIAJIAO) {
            //家教
            getJiajiaoInfo();
        } else if (roleType == PageConfig.TYPE_ZHONGDIANGONG) {
            //钟点工
            getZhongdiangongInfo();
        } else if (roleType == PageConfig.TYPE_ZHAOPIN) {
            //求职招聘
            getQiuzhiInfo();
        }
    }

    /**
     * 获取求职招聘
     */
    private void getQiuzhiInfo() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_ALL_JOB_SEEKER_DETAILS)
                .setCallBackData(new BaseNewNetModelimpl<List<QiuzhiFindMatchModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        commonRefresh.refreshComplete();
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<QiuzhiFindMatchModel>> respnse, String source) {
                        showContentView();
                        commonRefresh.refreshComplete();
                        dismiss();

                        if (respnse.getData() != null) {
                            if (page == 1) {
                                list.clear();
                            }
                            for (QiuzhiFindMatchModel listBean : respnse.getData()) {
                                CommMatchedResumeModel model = new CommMatchedResumeModel();
                                model.setType(CommMatchedResumeModel.TYPE_ZHAOPIN);
                                model.setData(listBean);
                                list.add(model);
                            }
                            adapter.loadMoreEnd();
                            adapter.notifyDataSetChanged();
                        }

                        page++;

                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<QiuzhiFindMatchModel>>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /**
     * 获取钟点工信息
     */
    private void getZhongdiangongInfo() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_MATCH_RESUME_ZDG)
                .addParams("page", page + "")
                .addParams("page_count", "10")
                .setCallBackData(new BaseNewNetModelimpl<ZhongdgFindMatchModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        commonRefresh.refreshComplete();
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<ZhongdgFindMatchModel> respnse, String source) {
                        showContentView();
                        commonRefresh.refreshComplete();
                        dismiss();

                        if (respnse.getData() != null) {
                            if (respnse.getData().isFirstPage()) {
                                list.clear();
                            }
                            for (ZhongdgFindMatchModel.ListBean listBean : respnse.getData().getList()) {
                                CommMatchedResumeModel model = new CommMatchedResumeModel();
                                model.setType(CommMatchedResumeModel.TYPE_ZHONGDIANGONG);
                                model.setData(listBean);
                                list.add(model);
                            }
                            if (respnse.getData().isLastPage()) {
                                adapter.loadMoreEnd();
                            }
                            adapter.notifyDataSetChanged();
                        }

                        page++;
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<ZhongdgFindMatchModel>>() {
                        }.getType();
                    }
                })
                .buildPost()
        );
    }

    /**
     * 获取家教信息
     */
    private void getJiajiaoInfo() {
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_MATCH_WHEN_TUTOR_LIST)
                .setCallBackData(new BaseNewNetModelimpl<List<JiajiaoFindMatchModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        commonRefresh.refreshComplete();
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<JiajiaoFindMatchModel>> respnse, String source) {
                        commonRefresh.refreshComplete();
                        showContentView();
                        if (respnse.getData() != null) {
                            if (page == 1) {
                                list.clear();
                            }
                            for (JiajiaoFindMatchModel listBean : respnse.getData()) {
                                CommMatchedResumeModel model = new CommMatchedResumeModel();
                                model.setType(CommMatchedResumeModel.TYPE_JIAJIAO);
                                model.setData(listBean);
                                list.add(model);
                            }
                            adapter.loadMoreEnd();
                            adapter.notifyDataSetChanged();
                        } else {
                            adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<JiajiaoFindMatchModel>>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    /**
     * 获取 保姆 月嫂 育儿嫂 匹配信息
     *
     * @param roleType
     */
    private void getBmYsYesInfo(int roleType) {
        int require_type = 0;
        switch (roleType) {
            case PageConfig.TYPE_BAOMU:
                require_type = 1;
                break;
            case PageConfig.TYPE_YUESAO:
                require_type = 2;
                break;
            case PageConfig.TYPE_YUERSAO:
                require_type = 3;
                break;
        }
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_MATCH_REQUIRE_LIST)
                .addParams("require_type", require_type + "")
                .addParams("page", page + "")
                .addParams("page_count", "10")
                .setCallBackData(new BaseNewNetModelimpl<List<BmYsYesFindMatchModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showTips(msg);
                        commonRefresh.refreshComplete();
                        showErrorView();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<BmYsYesFindMatchModel>> respnse, String source) {
                        commonRefresh.refreshComplete();
                        showContentView();
                        if (respnse.getData() != null) {
                            if (page == 1) {
                                list.clear();
                            }
                            for (BmYsYesFindMatchModel listBean : respnse.getData()) {
                                CommMatchedResumeModel model = new CommMatchedResumeModel();
                                model.setType(CommMatchedResumeModel.TYPE_BM_YS_YES);
                                model.setData(listBean);
                                list.add(model);
                            }
                            adapter.loadMoreEnd();
                            adapter.notifyDataSetChanged();
                        } else {
                            adapter.notifyDataSetChanged();
                        }
                        page++;
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<BmYsYesFindMatchModel>>>() {
                        }.getType();
                    }
                }).buildPost());
    }

    /**
     * 获取劳工 特种工 匹配信息
     *
     * @param roleType
     */
    private void getLgTzgInfo(final int roleType) {
        int type = 0;
        if (roleType == PageConfig.TYPE_ZHONGJIE) {
            type = 1;
        } else if (roleType == PageConfig.TYPE_TEZHONGGONG) {
            type = 2;
        }
        addCall(new NetUtil().setUrl(APIS.URL_TALENT_MATCH_RESUME)
                .addParams("page", page + "")
                .addParams("page_count", "10")
                .addParams("type", type + "")
                .setCallBackData(new BaseNewNetModelimpl<HasMatchResumeModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        commonRefresh.refreshComplete();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<HasMatchResumeModel> respnse, String source) {
                        showContentView();
                        commonRefresh.refreshComplete();
                        dismiss();

                        if (respnse.getData() != null) {
                            if (respnse.getData().isFirstPage()) {
                                list.clear();
                            }
                            for (HasMatchResumeModel.ListBean listBean : respnse.getData().getList()) {
                                CommMatchedResumeModel model = new CommMatchedResumeModel();
                                model.setType(CommMatchedResumeModel.TYPE_LG_TZG);
                                model.setData(listBean);
                                model.setSmallType(roleType);
                                list.add(model);
                            }
                            if (respnse.getData().isLastPage()) {
                                adapter.loadMoreEnd();
                            }
                            adapter.notifyDataSetChanged();
                        }

                        page++;
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<HasMatchResumeModel>>() {
                        }.getType();
                    }
                }).buildPost()
        );

    }

    private RecyclerViewAdapter adapter;
    private List<CommMatchedResumeModel> list;

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
                getData(1);
            }
        }, recyclerView);

        //事件监听
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CommMatchedResumeModel model = list.get(position);
                if (model.getType() == CommMatchedResumeModel.TYPE_LG_TZG) {
                    HasMatchResumeModel.ListBean item = (HasMatchResumeModel.ListBean) model.getData();
                    ((BackFragmentActivity) getActivity()).addFragment(JianliDetailsFragment.newInstance(item.getRelate_id(),model.getSmallType()), true, true);
                } else if (model.getType() == CommMatchedResumeModel.TYPE_BM_YS_YES) {
                    BmYsYesFindMatchModel item = (BmYsYesFindMatchModel) model.getData();
                    ((BackFragmentActivity) getActivity()).addFragment(BaomuInfoDetailsFragment.newInstance(item.getRequire_id(),roleType), true, true);
                } else if (model.getType() == CommMatchedResumeModel.TYPE_JIAJIAO) {
                    //家教
                    JiajiaoFindMatchModel item = (JiajiaoFindMatchModel) model.getData();
                    ((BackFragmentActivity) getActivity()).addFragment(JiajiaoInfoDetailsFragment.newInstance(item.getWhen_tutor_id()), true, true);
                } else if (model.getType() == CommMatchedResumeModel.TYPE_ZHONGDIANGONG) {
                    //钟点工
                    ZhongdgFindMatchModel.ListBean item = (ZhongdgFindMatchModel.ListBean) model.getData();
                    ((BackFragmentActivity) getActivity()).addFragment(ZhongdgHasMatchedDetailsFragment.newInstance(item.getHour_matching_id()), true, true);
                } else if (model.getType() == CommMatchedResumeModel.TYPE_ZHAOPIN) {
                    //求职招聘
                    QiuzhiFindMatchModel item = (QiuzhiFindMatchModel) model.getData();
                    ((BackFragmentActivity) getActivity()).addFragment(ZhaopinInfoDetailsFragment.newInstance(item.getJob_resume_id()), true, true);
                }
            }
        });
    }

    class RecyclerViewAdapter extends BaseMultiItemQuickAdapter<CommMatchedResumeModel, BaseViewHolder> {
        public RecyclerViewAdapter(List<CommMatchedResumeModel> data) {
            super(data);
            addItemType(CommMatchedResumeModel.TYPE_LG_TZG, R.layout.item_recycler_has_match_resume);
            addItemType(CommMatchedResumeModel.TYPE_BM_YS_YES, R.layout.item_recycler_has_match_resume_baomu);
            addItemType(CommMatchedResumeModel.TYPE_JIAJIAO, R.layout.item_recycler_has_match_resume_baomu);
            addItemType(CommMatchedResumeModel.TYPE_ZHONGDIANGONG, R.layout.item_recycler_has_match_resume);
            addItemType(CommMatchedResumeModel.TYPE_ZHAOPIN, R.layout.item_recycler_has_match_resume_zhaopin);
        }

        @Override
        protected void convert(BaseViewHolder helper, final CommMatchedResumeModel items) {
            if (items.getType() == CommMatchedResumeModel.TYPE_LG_TZG) {
                final HasMatchResumeModel.ListBean item = (HasMatchResumeModel.ListBean) items.getData();
                helper.setText(R.id.tv_name, item.getName() + "  " + (item.getSex() == 1 ? "男" : "女"))
                        .setText(R.id.tv_position, "申请职位：" + item.getLabor_recruitment_info_name())
                        .setText(R.id.tv_phone, "联系电话：" + item.getContact_tel())
                        .setText(R.id.tv_location, item.getWork_second_area_name() + item.getWork_third_area_name());

                GlideUtil.show(mContext, item.getUser_avatar_url(), (ImageView) helper.getView(R.id.iv_avatar));

                helper.getView(R.id.tv_confirm).setVisibility(View.GONE);
                helper.getView(R.id.tv_tousu).setVisibility(View.GONE);

                helper.getView(R.id.iv_phone).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                        if (checkPermission(permissions)) {
                            PhoneUtils.dial(item.getContact_tel());
                        }
                    }
                });

                helper.getView(R.id.tv_location).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(item.getLongitude()), Double.parseDouble(item.getLatitude()), "");

//                        ActivityUtils.jumpToShowMap(getActivity(), item.getLongitude() + "," + item.getLatitude());
                    }
                });
            } else if (items.getType() == CommMatchedResumeModel.TYPE_BM_YS_YES) {
                final BmYsYesFindMatchModel item = (BmYsYesFindMatchModel) items.getData();
                helper.setText(R.id.tv_name, item.getUser_name() + "    " + item.getSex_name())
                        .setText(R.id.tv_location, item.getArea_name());

                GlideUtil.show(mContext, item.getUser_avatar_url(), (ImageView) helper.getView(R.id.iv_avatar));

                //设置匹配度
                ((TextView) helper.getView(R.id.tv_pipeidu)).setText(new SpanUtils().append("匹配度：")
                        .append(item.getMatch_value() + "%")
                        .setForegroundColor(ResUtils.getCommRed())
                        .create());
            } else if (items.getType() == CommMatchedResumeModel.TYPE_JIAJIAO) {
                final JiajiaoFindMatchModel item = (JiajiaoFindMatchModel) items.getData();
                helper.setText(R.id.tv_name, item.getUser_name() + "    " + item.getSex_name())
                        .setText(R.id.tv_location, item.getArea_name());

                GlideUtil.show(mContext, item.getUser_avatar_url(), (ImageView) helper.getView(R.id.iv_avatar));

                //设置匹配度
                ((TextView) helper.getView(R.id.tv_pipeidu)).setText(new SpanUtils().append("匹配度：")
                        .append(item.getMatch_value() + "%")
                        .setForegroundColor(ResUtils.getCommRed())
                        .create());
            } else if (items.getType() == CommMatchedResumeModel.TYPE_ZHONGDIANGONG) {
                final ZhongdgFindMatchModel.ListBean item = (ZhongdgFindMatchModel.ListBean) items.getData();
                helper.setText(R.id.tv_name, item.getName() + "  " + (item.getSex() == 1 ? "男" : "女"))
                        .setText(R.id.tv_phone, "联系电话：" + item.getContact_tel())
                        .setText(R.id.tv_location, item.getWork_second_area_name() + item.getWork_third_area_name());

                GlideUtil.show(mContext, item.getUser_avatar_url(), (ImageView) helper.getView(R.id.iv_avatar));

                helper.getView(R.id.tv_confirm).setVisibility(View.GONE);
                helper.getView(R.id.tv_tousu).setVisibility(View.GONE);
                helper.getView(R.id.tv_position).setVisibility(View.GONE);

                helper.getView(R.id.iv_phone).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String[] permissions = new String[]{Manifest.permission.CALL_PHONE};
                        if (checkPermission(permissions)) {
                            PhoneUtils.dial(item.getContact_tel());
                        }
                    }
                });

                helper.getView(R.id.tv_location).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MapNaviUtils.showDaoHangDialog(getActivity(), Double.parseDouble(item.getLongitude()), Double.parseDouble(item.getLatitude()), "");

//                        ActivityUtils.jumpToShowMap(getActivity(), item.getLongitude() + "," + item.getLatitude());
                    }
                });
            } else if (items.getType() == CommMatchedResumeModel.TYPE_ZHAOPIN) {
                final QiuzhiFindMatchModel item = (QiuzhiFindMatchModel) items.getData();
                helper.setText(R.id.tv_name, item.getTrue_name() + "  " + (item.getSex() == 1 ? "男" : "女"))
                        .setText(R.id.tv_location, item.getWork_second_area_name() + item.getWork_third_area_name());

                GlideUtil.show(mContext, item.getUser_avatar_url(), (ImageView) helper.getView(R.id.iv_avatar));

                //设置匹配度
                ((TextView) helper.getView(R.id.tv_pipeidu)).setText(new SpanUtils().append("匹配度：")
                        .append(item.getMatch_value() + "%")
                        .setForegroundColor(ResUtils.getCommRed()).create());
                //设置
                ((TextView) helper.getView(R.id.tv_zhiwei)).setText(new SpanUtils().append("申请职位：")
                        .append(item.getJob_name())
                        .setForegroundColor(ResUtils.getCommRed()).create());
            }
        }
    }

    @Subscribe
    public void notifyPageRefresh(NotifyPageRefresh event) {
        if (TAG.equals(event.getTag())) {
            refreshPageData();
        }
    }

    @Override
    public boolean isNeedEventBus() {
        return true;
    }
}
