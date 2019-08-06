package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.customer.activity.CustomerDetailActivity;
import com.windmillsteward.jukutech.activity.home.personnel.model.TalentInnListClassicModel;
import com.windmillsteward.jukutech.activity.home.personnel.model.TalentInnListModel;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.TalentInnListNewPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.activity.BusinessAuthenticationActivity;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.common.model.CommonBannerModel;
import com.windmillsteward.jukutech.activity.newpage.common.presenter.CommonBannerPresenter;
import com.windmillsteward.jukutech.activity.newpage.model.HasPublicRectuitmentModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForCommBaomu;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForJiajiaoModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForLgAndTzg;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForYinyuanModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForZhaopinModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForZhongdgModel;
import com.windmillsteward.jukutech.activity.newpage.newpublish.HomeCommonPublishActivity;
import com.windmillsteward.jukutech.activity.newpage.newpublish.WorkAndRencaiInfoActivity;
import com.windmillsteward.jukutech.activity.newpage.yizhan.baomu.HasBmYsYesPublishedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.jiajiao.HasJiajiaoPublishedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.qiuzhi.HasZhaopinPublishedDetailsFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongdiangong.ZhongdgUsePersonFragment;
import com.windmillsteward.jukutech.activity.newpage.yizhan.zhongjie_tgz.HasLgTzgPublishedDetailsFragment;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.constant.AppConstant;
import com.windmillsteward.jukutech.base.interfaces.OnTitleAreaCliclkListener;
import com.windmillsteward.jukutech.base.interfaces.OnUserAuthenListener;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.manager.CommonActivityManager;
import com.windmillsteward.jukutech.utils.TimeUtils;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class TalentInnListNewActivity extends BaseInitActivity implements TalentInnListNewView {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.common_refresh)
    CommonRefreshLayout commonRefresh;

    private HeaderRecyclerViewAdapter headerAdapter;
    private List<TalentInnListClassicModel> headerList;

    private RecyclerView headerRecyclerView;

    private RecyclerViewAdapter adapter;
    private List<TalentInnListModel.ListBean> list;

    private FlyBanner flyBanner;

    private CommonBannerPresenter bannerPresenter;
    private HasPublicRectuitmentModel hasPublicRectuitmentModel;//右边上一次发布实体
    private LastPublicForLgAndTzg lastPublicForLgAndTzg;//劳务工-我要找工作
    private LastPublicForZhaopinModel lastPublicForZhaopinModel;//求职-我要求职
    private LastPublicForYinyuanModel lastPublicForYinyuanModel;//姻缘
    private LastPublicForJiajiaoModel lastPublicForJiajiaoModel;//家教-我要找家教
    private LastPublicForCommBaomu lastPublicForCommBaomu;//保姆月嫂育儿嫂--我要找工作
    private LastPublicForZhongdgModel lastPublicForZhongdgModel;//钟点工--我要找工作

    @Override
    protected void initView(View view) {
        setMainTitle("人才驿站");

//        setMainRightIvRes(R.mipmap.icon_service_gray);
        setOnTitleAreaCliclkListener(new OnTitleAreaCliclkListener() {
            @Override
            public void onTitleAreaClickListener(View view) {
                if (view.getId() == R.id.iv_right) {
                    startAtvDonFinish(CustomerDetailActivity.class);
                }
            }
        });

        commonRefresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshPageData();
            }
        });

        bannerPresenter = new CommonBannerPresenter(this);
    }

    @Override
    public BasePresenter getPresenter() {
        return new TalentInnListNewPresenter(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_talent_inn_list_new;
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        headerList = new ArrayList<>();

        adapter = new RecyclerViewAdapter(list);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                ((TalentInnListNewPresenter) presenter).getListData(currPage);
            }
        }, recyclerView);
        adapter.setLoadMoreEndText(AppConstant.TO_BOTTOM);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addHeaderView();
        recyclerView.setAdapter(adapter);

        //注释掉
        adapter.setEnableLoadMore(true);
        adapter.loadMoreEnd();
        adapter.notifyDataSetChanged();

        showDialog();
        addData();
    }

    /**
     * 添加HeaderView
     */
    private void addHeaderView() {
        View view = View.inflate(this, R.layout.header_recycler_talentinn_list, null);
        headerRecyclerView = view.findViewById(R.id.header_recyclerview);
        flyBanner = view.findViewById(R.id.flyBanner);

        flyBanner.setBackgroundColor(Color.LTGRAY);

        headerAdapter = new HeaderRecyclerViewAdapter(headerList);
        headerAdapter.isUseEmpty(false);
        headerRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        headerRecyclerView.setAdapter(headerAdapter);

        headerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                if (position == 0) {
                    //劳务中介
//                    bundle.putInt("type", CommonYizhanHomeActivity.TYPE_ZHONGJIE);

                    authenAndJump(PageConfig.TYPE_ZHONGJIE);
                } else if (position == 1) {
                    //求职招聘
                    authenAndJump(PageConfig.TYPE_ZHAOPIN);
//                    bundle.putInt("type", CommonYizhanHomeActivity.TYPE_ZHAOPIN);
                } else if (position == 2) {
                    //姻缘服务
                    authenAndJump(PageConfig.TYPE_YINYUAN);
//                    bundle.putInt("type", CommonYizhanHomeActivity.TYPE_YINYUAN);
                } else if (position == 3) {
                    //家教
                    authenAndJump(PageConfig.TYPE_JIAJIAO);
//                    bundle.putInt("type", CommonYizhanHomeActivity.TYPE_JIAJIAO);
                } else if (position == 4) {
                    //保姆
                    authenAndJump(PageConfig.TYPE_BAOMU);
//                    bundle.putInt("type", CommonYizhanHomeActivity.TYPE_BAOMU);
                } else if (position == 5) {
                    //月嫂
                    authenAndJump(PageConfig.TYPE_YUESAO);
//                    bundle.putInt("type", CommonYizhanHomeActivity.TYPE_YUESAO);
                } else if (position == 6) {
                    //育儿嫂
                    authenAndJump(PageConfig.TYPE_YUERSAO);
//                    bundle.putInt("type", CommonYizhanHomeActivity.TYPE_YUERSAO);
                } else if (position == 7) {
                    //钟点工
                    authenAndJump(PageConfig.TYPE_ZHONGDIANGONG);
//                    bundle.putInt("type", CommonYizhanHomeActivity.TYPE_ZHONGDIANGONG);
                } else if (position == 8) {
                    //特种工
                    authenAndJump(PageConfig.TYPE_TEZHONGGONG);
//                    bundle.putInt("type", CommonYizhanHomeActivity.TYPE_TEZHONGGONG);
                }
//                startAtvDonFinish(CommonYizhanHomeActivity.class, bundle);
            }
        });

        adapter.setHeaderView(view);
    }

    //添加数据
    private void addData() {
        ((TalentInnListNewPresenter) presenter).getHeaderViewData();
        ((TalentInnListNewPresenter) presenter).getListData(currPage);
        bannerPresenter.loadBannerData(CommonBannerPresenter.TYPE_RENCAIIZHAN, new CommonBannerPresenter.DataCallBack() {
            @Override
            public void onFail(int type, String msg) {
                showTips(msg);
            }

            @Override
            public void onSucess(int code, BaseResultInfo<List<CommonBannerModel>> respnse, String source) {
                List<String> urls = new ArrayList<>();
                List<String> pigs = new ArrayList<>();
                List<CommonBannerModel> data = respnse.getData();
                if (data != null) {
                    for (CommonBannerModel datum : data) {
                        urls.add(datum.getHref_url());
                        pigs.add(datum.getPic_url());
                    }
                }
                ViewWrap.setUpFlyBanner(TalentInnListNewActivity.this, pigs, urls, flyBanner);
            }
        });

    }

    private int currPage = 1;

    @Override
    protected void refreshPageData() {
        currPage = 1;
        addData();
    }

    //人才驿站，房屋信息，智慧生活分类跳转，需先判断是否认证，然后判断是否发布过
    //没有发布过,add_or_edit_type传0，发布过传1
    //没有发布过,isOnly传false，发不过传true
    public void authenAndJump(final int roleType) {
        if (TextUtils.isEmpty(getAccessToken())) {
            startAtvDonFinish(LoginActivity.class);
        } else {
            checkUserAuthen(new OnUserAuthenListener() {
                @Override
                public void isAuthen() {
                    if (roleType == PageConfig.TYPE_YINYUAN){
                        Intent intent = new Intent(TalentInnListNewActivity.this, HomeCommonPublishActivity.class);
                        intent.putExtra(HomeCommonPublishActivity.MODULE_TYPE, roleType);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(TalentInnListNewActivity.this, WorkAndRencaiInfoActivity.class);
                        intent.putExtra(WorkAndRencaiInfoActivity.MODULE_TYPE, roleType);
                        startActivity(intent);
                    }

                }

                @Override
                public void isNotAuthen() {
                    final BaseDialog baseDialog = new BaseDialog(TalentInnListNewActivity.this);
                    baseDialog.showThreeButton("认证信息", "请完善您的认证信息", "取消", "个人认证", "企业认证", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            baseDialog.dismiss();
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            baseDialog.dismiss();
                            startAtvDonFinish(PersonalAuthenticationActivity.class);
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            baseDialog.dismiss();
                            startAtvDonFinish(BusinessAuthenticationActivity.class);
                        }
                    });
                }
            });
        }

    }

    @Override
    public void loadHeaderViewData(List<TalentInnListClassicModel> list) {
        this.headerList.clear();
        this.headerList.addAll(list);
        headerAdapter.notifyDataSetChanged();
        headerAdapter.loadMoreEnd();
        commonRefresh.refreshComplete();
    }

    @Override
    public void loadListData(TalentInnListModel data) {
        List<TalentInnListModel.ListBean> datas = data.getList();
        if (datas != null) {
            if (currPage == 1) {
                list.clear();
            }
            list.addAll(datas);
            if (data.isLastPage()) {
                adapter.loadMoreEnd();
            } else {
                adapter.loadMoreComplete();
            }
        }
        currPage++;
        commonRefresh.refreshComplete();
        showContentView();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int type = list.get(position).getType();
                Bundle bundle;
                switch (type) {
                    case 1://劳务中介特种工
                        bundle = new Bundle();
                        bundle.putInt("recruitment_id", list.get(position).getRelate_id());
                        bundle.putInt("roleType", PageConfig.TYPE_ZHONGJIE);
                        bundle.putInt("showAddress", 0);
                        startManagerActivity(CommonActivityManager.class, HasLgTzgPublishedDetailsFragment.TAG, bundle);
                        break;
                    case 2://求职招聘
                        bundle = new Bundle();
                        bundle.putInt("job_id", list.get(position).getRelate_id());
                        bundle.putInt("showAddress", 0);
                        startManagerActivity(CommonActivityManager.class, HasZhaopinPublishedDetailsFragment.TAG, bundle);
                        break;
                    case 3://保姆月嫂育儿嫂
                        bundle = new Bundle();
                        int require_type = list.get(position).getRequire_type();
                        bundle.putInt("recruitment_id", list.get(position).getRelate_id());
                        if (require_type == 1) {//保姆
                            bundle.putInt("roleType", PageConfig.TYPE_BAOMU);
                        } else if (require_type == 2) {//育儿嫂
                            bundle.putInt("roleType", PageConfig.TYPE_YUERSAO);
                        } else if (require_type == 3) {//月嫂
                            bundle.putInt("roleType", PageConfig.TYPE_YUESAO);
                        }
                        bundle.putInt("showAddress", 0);
                        startManagerActivity(CommonActivityManager.class, HasBmYsYesPublishedDetailsFragment.TAG, bundle);
                        break;
                    case 4://家教
                        bundle = new Bundle();
                        bundle.putInt("look_for_tutor_id", list.get(position).getRelate_id());
                        bundle.putInt("showAddress", 0);
                        startManagerActivity(CommonActivityManager.class, HasJiajiaoPublishedDetailsFragment.TAG, bundle);
                        break;
                    case 5://钟点工
                        bundle = new Bundle();
                        bundle.putInt("lookfor_bell_worker_id", list.get(position).getRelate_id());
                        bundle.putInt("showAddress", 0);
                        startManagerActivity(CommonActivityManager.class, ZhongdgUsePersonFragment.TAG, bundle);
                        break;
                }
            }
        });
    }

    @Override
    public void loadFail() {
        if (commonRefresh != null) {
            commonRefresh.refreshComplete();
        }
        adapter.loadMoreFail();
    }

    class HeaderRecyclerViewAdapter extends BaseQuickAdapter<TalentInnListClassicModel, BaseViewHolder> {

        public HeaderRecyclerViewAdapter(@Nullable List<TalentInnListClassicModel> data) {
            super(R.layout.item_recycler_talentinn_list_classic, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, TalentInnListClassicModel item) {
            helper.setText(R.id.tv_title, item.getClass_name());

            Glide.with(TalentInnListNewActivity.this)
                    .load(item.getClass_image()).into((ImageView) helper.getView(R.id.iv_logo));
        }
    }


    class RecyclerViewAdapter extends BaseQuickAdapter<TalentInnListModel.ListBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<TalentInnListModel.ListBean> data) {
            super(R.layout.item_recycler_talentinn_list, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, TalentInnListModel.ListBean item) {
            helper.setText(R.id.tv_title, item.getTitle());
            helper.setText(R.id.tv_address, item.getArea_name());
            helper.setText(R.id.tv_time, TimeUtils.formatMillToYYYYMMDD(item.getAdd_time() * 1000));
        }
    }
}
