package com.windmillsteward.jukutech.activity.home.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.blankj.utilcode.util.BarUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.cpoopc.scrollablelayoutlib.ScrollableLayout;
import com.google.gson.reflect.TypeToken;
import com.gyf.barlibrary.ImmersionBar;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.MainActivity;
import com.windmillsteward.jukutech.activity.home.commons.web.CommonWebActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.IntelligentFamilyListActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.PublishRequireNewActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.HomeSearchActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.MessageListActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.ScanActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.SelectCityActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.SelectCityView;
import com.windmillsteward.jukutech.activity.home.fragment.model.HomeRecyclerViewModel;
import com.windmillsteward.jukutech.activity.home.fragment.model.HomeRootModel;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.SelectCityPresenter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.UnreadMessageCountPresenter;
import com.windmillsteward.jukutech.activity.home.houselease.activity.HouseRentingActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.TalentInnListNewActivity;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.activity.BusinessAuthenticationActivity;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.activity.newpage.common.app.PageConfig;
import com.windmillsteward.jukutech.activity.newpage.customerservice.activity.HomeCustomerActivity;
import com.windmillsteward.jukutech.activity.newpage.home.HomeBottomFragment;
import com.windmillsteward.jukutech.activity.newpage.model.HasPublicRectuitmentModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForCommBaomu;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForJiajiaoModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForLgAndTzg;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForYinyuanModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForZhaopinModel;
import com.windmillsteward.jukutech.activity.newpage.model.LastPublicForZhongdgModel;
import com.windmillsteward.jukutech.activity.newpage.newpublish.HomeCommonPublishActivity;
import com.windmillsteward.jukutech.activity.newpage.newpublish.WorkAndRencaiInfoActivity;
import com.windmillsteward.jukutech.activity.newpage.smartlife.DingCanServiceHomeActivity;
import com.windmillsteward.jukutech.base.AppManager;
import com.windmillsteward.jukutech.base.BaseDialog;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.KV;
import com.windmillsteward.jukutech.base.baseadapter.BaseMultiItemQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.constant.AppConstant;
import com.windmillsteward.jukutech.base.interfaces.OnUserAuthenListener;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.CityBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.MaxRecyclerView;
import com.windmillsteward.jukutech.customview.MyLinearLayoutManager;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.interfaces.PtrHeader;
import com.windmillsteward.jukutech.utils.GraphicUtil;
import com.windmillsteward.jukutech.utils.ImageUtils;
import com.windmillsteward.jukutech.utils.StringUtil;
import com.windmillsteward.jukutech.utils.view.ViewWrap;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import org.xutils.common.Callback;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * 首页
 */
public class HomeFragmentThree extends BaseInitFragment implements SelectCityView, UnreadMessageCountView, PtrHeader.UIUpdate {
    public static final String TAG = "HomeFragmentThree";
    @Bind(R.id.fly_top_anner)
    FlyBanner flyTopAnner;
    @Bind(R.id.header_recyclerview)
    MaxRecyclerView headerRecyclerview;
    @Bind(R.id.middle_recyclerview)
    MaxRecyclerView middleRecyclerview;
    @Bind(R.id.magic_indicator)
    MagicIndicator magic_indicator;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.scrollableLayout)
    ScrollableLayout scrollableLayout;
    @Bind(R.id.ptr_frame_refresh)
    PtrClassicFrameLayout ptrFrameRefresh;
    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.lay_ll_search)
    LinearLayout layLlSearch;
    @Bind(R.id.tv_scan)
    TextView tvScan;
    @Bind(R.id.tv_message)
    TextView tvMessage;
    @Bind(R.id.tv_message_count)
    TextView tvMessageCount;
    @Bind(R.id.lay_ll_top)
    LinearLayout layLlTop;
    @Bind(R.id.ll_header_container)
    LinearLayout llHeaderContainer;
    @Bind(R.id.rb_one)
    Button rbOne;
    @Bind(R.id.rb_two)
    Button rbTwo;
    @Bind(R.id.rb_three)
    Button rbThree;
    @Bind(R.id.rb_four)
    Button rbFour;
    @Bind(R.id.iv_hot)
    ImageView ivHot;
    @Bind(R.id.lay_ll_bottom)
    LinearLayout layLlBottom;
    @Bind(R.id.tv_hot)
    TextView tvHot;
    @Bind(R.id.lay_rl_moduel_type)
    RelativeLayout layRlModuelType;

    private SelectCityPresenter selectCityPresenter;
    private UnreadMessageCountPresenter unreadMessageCountPresenter;

    private RecyclerViewAdapter adapter;
    private List<HomeRecyclerViewModel> list;
    private MyLinearLayoutManager linearLayoutManager;

    //头部
    private List<HomeRootModel.ModuleTypeBean.ModuleTypeListBean> headerList;
    private HeaderRecyclerViewAdapter headerAdapter;

    //定位
    private LocationClient mLocClient;
    private MyLocationListener locationListener;
    private String latitude = "";
    private String longitude = "";
    public String city = "";
    private static final int SELECT_CITY = 100;
    public static int msg_count;
    private String district = "";
    private boolean firstLocation = true;
    private boolean isWhite = false;//状态栏是否是白色
    public boolean isTop = true;//状态栏是否在顶部

    private Handler handler1 = new Handler();
    private Handler handler2 = new Handler();

    public List<HeaderViewPagerFragment> fragments;
    private ContentAdapter contentAdapter;
    private List<String> tabList;
    private List<String> tabTwoList;
    private CommonNavigator commonNavigator;
    private List<Callback.Cancelable> calls = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home_fragment_three;
    }

    @Override
    protected void initView(View v, Bundle savedInstanceState) {
        hidTitleView();
        tvCity.setText((String) Hawk.get(Define.CURR_CITY_THIRD_NAME, "地区"));
    }

    @Override
    protected void initData() {

        //权限
        initPermision();
        //定位
        initLocal();
        //获取未读数
        initUnReadMessage();
        //处理头部 padding
        handlerPadding();
        //初始化底部fragment,
        initBottomFragment();
        //通过反射修改MyScrollableHelper，解决滑动冲突问题(未解决)
//        initScrollaBleLayout();
        //设置刷新控件头部以及头部UI更新操作，并做viewpager头部相应处理
        initRefeshHeader();
        //处理头部渐变
        handlerHeader();
        //初始化adapter
        initAdapter();
        //初始化底部viewpager的下划线
        initIndicator();
        //获取数据
        getMainData();
    }

    @Override
    protected void refreshPageData() {
        showDialog();
        getMainData();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            String district = Hawk.get(Define.CURR_CITY_THIRD_NAME, "");
            tvCity.setText(TextUtils.isEmpty(district) ? "地区" : district);
            if (msg_count == 0) {
                tvMessageCount.setVisibility(View.GONE);
            } else {
                tvMessageCount.setVisibility(View.VISIBLE);
                tvMessageCount.setText(msg_count + "");
            }
            if (msg_count > 99) {
                tvMessageCount.setText("99+");
            }
        }
    }

    @Override
    public void showHeader() {
        //完成刷新时显示头部
        if (llHeaderContainer != null) {
            llHeaderContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideHeader() {
        // 开始刷新时隐藏头部
        if (llHeaderContainer != null) {
            llHeaderContainer.setVisibility(View.GONE);
        }
    }

    private void initPermision() {
        MainActivity activity = (MainActivity) AppManager.getAppManager().getActivity(MainActivity.class);
        activity.initPermision();
    }

    private void handlerPadding() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getHoldingActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            llHeaderContainer.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0);
        }
    }

    private void initBottomFragment() {
        fragments = new ArrayList<>();
        if (fragments != null && fragments.size() == 0) {
            fragments.add(HomeBottomFragment.newInstance(HomeBottomFragment.TYPE_MEISHI));
            fragments.add(HomeBottomFragment.newInstance(HomeBottomFragment.TYPE_SMART));
            fragments.add(HomeBottomFragment.newInstance(HomeBottomFragment.TYPE_RENCAI));
            fragments.add(HomeBottomFragment.newInstance(HomeBottomFragment.TYPE_FANGWU));
        }
    }

    private void initRefeshHeader() {
        PtrHeader ptrHeader = new PtrHeader(getActivity());
        ptrHeader.setUiUpdate(this);
        ptrFrameRefresh.setHeaderView(ptrHeader);
        ptrFrameRefresh.addPtrUIHandler(ptrHeader);
        ptrFrameRefresh.disableWhenHorizontalMove(true);
        ptrFrameRefresh.setResistance(1.7f);
        ptrFrameRefresh.setRatioOfHeaderHeightToRefresh(1.2f);
        ptrFrameRefresh.setDurationToClose(200);
        ptrFrameRefresh.setDurationToCloseHeader(1000);
        // default is false
        ptrFrameRefresh.setPullToRefresh(false);
        // default is true
        ptrFrameRefresh.setKeepHeaderWhenRefresh(true);
//        ptrFrameRefresh.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ptrFrameRefresh.autoRefresh();
//            }
//        }, 100);
        ptrFrameRefresh.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return scrollableLayout.canPtr();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrFrameRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getMainData();
                    }
                }, 1500);

            }
        });

    }


    private void handlerHeader() {
        scrollableLayout.setOnScrollListener(new ScrollableLayout.OnScrollListener() {
            @Override
            public void onScroll(int currentY, int maxY) {
                Drawable drawableScan = null;
                Drawable drawableMsg = null;
                Drawable drawableArrow = null;
                //动态改变标题栏的透明度,注意转化为浮点型
                float alpha = 1.0f * currentY / maxY;
                alpha = alpha * 10000;
                if (alpha < 100) {
                    if (!isWhite) {
                        handler1.post(new Runnable() {
                            @Override
                            public void run() {
                                ImmersionBar.with(getActivity()).statusBarDarkFont(false, 0.2f).transparentStatusBar().init();
                            }
                        });
                        isWhite = true;
                    }
                    layLlTop.setBackgroundResource(R.mipmap.icon_home_search_bg_white_two);
                    llHeaderContainer.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));

                    drawableScan = getActivity().getResources().getDrawable(R.mipmap.icon_new_scan_code);
                    drawableScan.setBounds(0, 0, drawableScan.getMinimumWidth(), drawableScan.getMinimumHeight());

                    drawableArrow = getActivity().getResources().getDrawable(R.mipmap.icon_new_arrow_down_solid_white);
                    drawableArrow.setBounds(0, 0, drawableArrow.getMinimumWidth(), drawableArrow.getMinimumHeight());

                    tvScan.setCompoundDrawables(null, drawableScan, null, null);
                    tvCity.setCompoundDrawables(null, null, drawableArrow, null);
                    tvScan.setTextColor(getActivity().getResources().getColor(R.color.white));
                    tvCity.setTextColor(getActivity().getResources().getColor(R.color.white));

                    drawableMsg = getActivity().getResources().getDrawable(R.mipmap.icon_new_msg);
                    drawableMsg.setBounds(0, 0, drawableMsg.getMinimumWidth(), drawableMsg.getMinimumHeight());
                    tvMessage.setCompoundDrawables(null, drawableMsg, null, null);
                    tvMessage.setTextColor(getActivity().getResources().getColor(R.color.white));
                    isTop = true;
//                    Log.i("cyq", "白色:" + alpha);
                } else {//超过mHeight
                    if (isWhite) {
                        handler2.post(new Runnable() {
                            @Override
                            public void run() {
                                ImmersionBar.with(getActivity()).statusBarDarkFont(true, 0.2f).init();
                            }
                        });
                        isWhite = false;
                    }
                    layLlTop.setBackgroundResource(R.mipmap.icon_home_search_bg_gray);
                    drawableScan = getActivity().getResources().getDrawable(R.mipmap.icon_new_scan_code_black);
                    drawableScan.setBounds(0, 0, drawableScan.getMinimumWidth(), drawableScan.getMinimumHeight());
                    tvScan.setCompoundDrawables(null, drawableScan, null, null);
                    tvScan.setTextColor(getActivity().getResources().getColor(R.color.text_black));

                    tvCity.setTextColor(getActivity().getResources().getColor(R.color.text_black));
                    drawableArrow = getActivity().getResources().getDrawable(R.mipmap.icon_arrow_down_solid_black);
                    drawableArrow.setBounds(0, 0, drawableArrow.getMinimumWidth(), drawableArrow.getMinimumHeight());
                    tvCity.setCompoundDrawables(null, null, drawableArrow, null);

                    drawableMsg = getActivity().getResources().getDrawable(R.mipmap.icon_new_msg_black);
                    drawableMsg.setBounds(0, 0, drawableMsg.getMinimumWidth(), drawableMsg.getMinimumHeight());
                    tvMessage.setCompoundDrawables(null, drawableMsg, null, null);
                    tvMessage.setTextColor(getActivity().getResources().getColor(R.color.text_black));
//                    Log.i("cyq", "黑色:" + alpha);
                    llHeaderContainer.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    isTop = false;
                }
            }
        });

    }

    public void initAdapter() {
        tabList = new ArrayList<>();
        tabTwoList = new ArrayList<>();
        contentAdapter = new ContentAdapter(tabList, getChildFragmentManager());
        viewPager.setAdapter(contentAdapter);
        viewPager.setOffscreenPageLimit(4);
        scrollableLayout.getHelper().setCurrentScrollableContainer(fragments.get(0));
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (fragments != null && fragments.size() != 0) {
                    scrollableLayout.getHelper().setCurrentScrollableContainer(fragments.get(position));
                }
                setButtonStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        headerList = new ArrayList<>();
        headerAdapter = new HeaderRecyclerViewAdapter(headerList);
        headerRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        middleRecyclerview.setNestedScrollingEnabled(false);//禁止滑动
        middleRecyclerview.setHasFixedSize(true);
        headerRecyclerview.setAdapter(headerAdapter);
        //事件监听
        headerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {
                    //个人驿站
                    startAtvDonFinish(TalentInnListNewActivity.class);
                } else if (position == 1) {
                    //智慧生活
                    startAtvDonFinish(IntelligentFamilyListActivity.class);
                } else if (position == 2) {
                    //房屋信息
                    startAtvDonFinish(HouseRentingActivity.class);
                } else if (position == 3) {
                    //便民信息
                    startAtvDonFinish(HomeCustomerActivity.class);
                }
            }
        });

        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        linearLayoutManager = new MyLinearLayoutManager(getActivity());
        middleRecyclerview.setNestedScrollingEnabled(false);//禁止滑动
        middleRecyclerview.setHasFixedSize(true);
        middleRecyclerview.setLayoutManager(linearLayoutManager);
        middleRecyclerview.setAdapter(adapter);

        adapter.setEnableLoadMore(false);
        adapter.setLoadMoreEndText(AppConstant.TO_BOTTOM);
        //事件监听
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                HomeRootModel.ClassListBean module_class = list.get(position).getModule_class();
            }
        });
    }

    private void initIndicator() {
        magic_indicator.setBackgroundColor(Color.parseColor("#FFFFFF"));
        commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);

    }

    //请求首页数据
    public void getMainData() {
        new NetUtil().setUrl(APIS.URL_HOME_LIST)
                .setCallBackData(new BaseNewNetModelimpl<HomeRootModel>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        dismiss();
                        showErrorView();
                        if (ptrFrameRefresh != null) {
                            ptrFrameRefresh.refreshComplete();
                        }
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<HomeRootModel> respnse, String
                            source) {
                        showContentView();
                        dismiss();
                        if (ptrFrameRefresh != null) {
                            ptrFrameRefresh.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ptrFrameRefresh.refreshComplete();
                                }
                            }, 500);

                            //组装数据
                            handlerDatas(respnse.getData());
                        }

                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<HomeRootModel>>() {
                        }.getType();
                    }
                }).buildGet();
    }

    public HomeRootModel getData() {
        return this.data;
    }

    public HomeRootModel data;

    //处理数据
    private void handlerDatas(final HomeRootModel data) {
        //头部Banner
        List<String> images = new ArrayList<>();
        for (HomeRootModel.TopBannerBean topBannerBean : data.getTop_banner()) {
            images.add(topBannerBean.getPic_url());
        }
        flyTopAnner.setImagesUrl(images);
        flyTopAnner.setPointPadding(0, 10, 0, GraphicUtil.dp2px(getActivity(), 50));
        ViewWrap.handlerFlyBanner(flyTopAnner, 750, 438);
        flyTopAnner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString(Define.INTENT_DATA, data.getTop_banner().get(position).getHref_url());
                startAtvDonFinish(CommonWebActivity.class, bundle);
            }
        });

        //分类
        headerList.clear();
        this.data = data;
        if (data.getModuleRecord() != null) {
            //模块背景图
            String index_image = data.getModuleRecord().getIndex_image();
            if (!TextUtils.isEmpty(index_image)) {
                Glide.with(getActivity())
                        .load(index_image)
                        .into(new ViewTarget<View, Drawable>(layRlModuelType) {
                            @Override
                            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition transition) {
                                this.view.setBackground(resource.getCurrent());
                            }
                        });
            }
            //模块数据
            if (data.getModuleRecord().getModule_type() != null) {
                headerList.addAll(data.getModuleRecord().getModule_type());
                headerAdapter.notifyDataSetChanged();
            }
        }


        //刷新数据，先把list的内容clear，不然数据会重复
        list.clear();
        //人才驿站，智慧生活，房屋信息分类
        if (data.getClass_list() != null) {
            for (int i = 0; i < data.getClass_list().size(); i++) {
                HomeRootModel.ClassListBean classListBean = data.getClass_list().get(i);
                if (i == 0) {
                    HomeRecyclerViewModel homeRecyclerViewModel = new HomeRecyclerViewModel();
                    homeRecyclerViewModel.setType(HomeRecyclerViewModel.TYPE_RENCAI);
                    homeRecyclerViewModel.setModule_class(classListBean);
                    list.add(homeRecyclerViewModel);
                } else if (i == 1) {
                    HomeRecyclerViewModel homeRecyclerViewModel = new HomeRecyclerViewModel();
                    homeRecyclerViewModel.setType(HomeRecyclerViewModel.TYPE_SMART);
                    homeRecyclerViewModel.setModule_class(classListBean);
                    list.add(homeRecyclerViewModel);
                } else if (i == 2) {
                    HomeRecyclerViewModel homeRecyclerViewModel = new HomeRecyclerViewModel();
                    homeRecyclerViewModel.setType(HomeRecyclerViewModel.TYPE_FANGWU);
                    homeRecyclerViewModel.setModule_class(classListBean);
                    list.add(homeRecyclerViewModel);
                }
            }
        }
        adapter.notifyDataSetChanged();
        adapter.loadMoreEnd();

        //当地热门数据
        tabList.clear();
        tabTwoList.clear();
        if (data.getTitleRecord() != null) {
            List<HomeRootModel.TitleListBean.TitleChildListBean> titleList = data.getTitleRecord().getTitleList();
            if (titleList != null) {
                for (HomeRootModel.TitleListBean.TitleChildListBean listBean : titleList) {
                    tabList.add(listBean.getName());
                    tabTwoList.add(listBean.getLow_title());
                }
            }
            //小logo
            String image = data.getTitleRecord().getImage();
            x.image().bind(ivHot, image);
            //名称
            String name = data.getTitleRecord().getName();
            tvHot.setText(TextUtils.isEmpty(name) ? "当地热门" : name);
        }

        if (tabTwoList != null && tabTwoList.size() == 4) {
            rbOne.setText(tabTwoList.get(0));
            rbTwo.setText(tabTwoList.get(1));
            rbThree.setText(tabTwoList.get(2));
            rbFour.setText(tabTwoList.get(3));
        }
        contentAdapter.setTitles(tabList);

        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return tabList == null ? 0 : tabList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {

                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.parseColor("#333333"));
                colorTransitionPagerTitleView.setSelectedColor(Color.parseColor("#3ec4a5"));
                colorTransitionPagerTitleView.setText(tabList.get(index));
                colorTransitionPagerTitleView.setTextSize(15);
                colorTransitionPagerTitleView.getPaint().setFakeBoldText(true);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setCurrentItem(index);
                        setButtonStatus(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                return null;
            }
        });
        magic_indicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magic_indicator, viewPager);
        viewPager.setCurrentItem(0);
        if (fragments != null) {
            for (int i = 0 ; i <fragments.size();i++){
                HeaderViewPagerFragment fragment = fragments.get(i);
                if (fragment instanceof HomeBottomFragment) {
                    ((HomeBottomFragment) fragment).refreshData();
                }
            }
        }
    }


    //获取未读数
    public void initUnReadMessage() {
        //未读消息数
        if (!TextUtils.isEmpty(getAccessToken())) {
            if (unreadMessageCountPresenter == null)
                unreadMessageCountPresenter = new UnreadMessageCountPresenter(this);
            unreadMessageCountPresenter.getUnreadMessageCount(getAccessToken());
        }
    }

    //定位
    public void initLocal() {
        mLocClient = new LocationClient(getActivity());
        locationListener = new MyLocationListener();
        mLocClient.registerLocationListener(locationListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setIsNeedAddress(true);//允许获取当前的位置信息，不开启则获取不了当前城市
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(300000); // 大于1000才循环
        option.setIsNeedAddress(true);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    @OnClick({R.id.tv_city, R.id.lay_ll_search, R.id.tv_scan, R.id.tv_message, R.id.rb_one, R.id.rb_two, R.id.rb_three, R.id.rb_four})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_city:
                if (!chekcLocationPermission())
                    return;
                if (TextUtils.isEmpty(Hawk.get(Define.CURR_CITY_NAME, ""))) {
                    showTips("请确认是否开启了定位权限", 1);
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(Define.INTENT_DATA, Hawk.get(Define.CURR_CITY_NAME, ""));
                intent.putExtra("type", SelectCityActivity.TYPE_NORMAL);
                startAtvDonFinishForResult(SelectCityActivity.class, SELECT_CITY, intent);
                break;
            case R.id.lay_ll_search:
                startAtvDonFinish(HomeSearchActivity.class);
                break;
            case R.id.tv_scan:
                startAtvDonFinish(ScanActivity.class);
                break;
            case R.id.tv_message:
                if (TextUtils.isEmpty(getAccessToken())) {
                    startAtvDonFinish(LoginActivity.class);
                } else {
                    startAtvDonFinish(MessageListActivity.class);
                }
                break;
            case R.id.rb_one:
                viewPager.setCurrentItem(0);
                break;
            case R.id.rb_two:
                viewPager.setCurrentItem(1);
                break;
            case R.id.rb_three:
                viewPager.setCurrentItem(2);
                break;
            case R.id.rb_four:
                viewPager.setCurrentItem(3);
                break;
        }
    }

    //底部tab状态控制
    private void setButtonStatus(int position) {
        if (position == 0) {
            rbOne.setBackground(getActivity().getResources().getDrawable(R.mipmap.icon_home_bottom_green));
            rbTwo.setBackground(getActivity().getResources().getDrawable(R.mipmap.icon_home_bottom_transparent));
            rbThree.setBackground(getActivity().getResources().getDrawable(R.mipmap.icon_home_bottom_transparent));
            rbFour.setBackground(getActivity().getResources().getDrawable(R.mipmap.icon_home_bottom_transparent));

            rbOne.setTextColor(Color.parseColor("#ffffff"));
            rbTwo.setTextColor(Color.parseColor("#b3aaaa"));
            rbThree.setTextColor(Color.parseColor("#b3aaaa"));
            rbFour.setTextColor(Color.parseColor("#b3aaaa"));
        } else if (position == 1) {
            rbOne.setBackground(getActivity().getResources().getDrawable(R.mipmap.icon_home_bottom_transparent));
            rbTwo.setBackground(getActivity().getResources().getDrawable(R.mipmap.icon_home_bottom_green));
            rbThree.setBackground(getActivity().getResources().getDrawable(R.mipmap.icon_home_bottom_transparent));
            rbFour.setBackground(getActivity().getResources().getDrawable(R.mipmap.icon_home_bottom_transparent));

            rbOne.setTextColor(Color.parseColor("#b3aaaa"));
            rbTwo.setTextColor(Color.parseColor("#ffffff"));
            rbThree.setTextColor(Color.parseColor("#b3aaaa"));
            rbFour.setTextColor(Color.parseColor("#b3aaaa"));
        } else if (position == 2) {
            rbOne.setBackground(getActivity().getResources().getDrawable(R.mipmap.icon_home_bottom_transparent));
            rbTwo.setBackground(getActivity().getResources().getDrawable(R.mipmap.icon_home_bottom_transparent));
            rbThree.setBackground(getActivity().getResources().getDrawable(R.mipmap.icon_home_bottom_green));
            rbFour.setBackground(getActivity().getResources().getDrawable(R.mipmap.icon_home_bottom_transparent));

            rbOne.setTextColor(Color.parseColor("#b3aaaa"));
            rbTwo.setTextColor(Color.parseColor("#b3aaaa"));
            rbThree.setTextColor(Color.parseColor("#ffffff"));
            rbFour.setTextColor(Color.parseColor("#b3aaaa"));
        } else if (position == 3) {
            rbOne.setBackground(getActivity().getResources().getDrawable(R.mipmap.icon_home_bottom_transparent));
            rbTwo.setBackground(getActivity().getResources().getDrawable(R.mipmap.icon_home_bottom_transparent));
            rbThree.setBackground(getActivity().getResources().getDrawable(R.mipmap.icon_home_bottom_transparent));
            rbFour.setBackground(getActivity().getResources().getDrawable(R.mipmap.icon_home_bottom_green));

            rbOne.setTextColor(Color.parseColor("#b3aaaa"));
            rbTwo.setTextColor(Color.parseColor("#b3aaaa"));
            rbThree.setTextColor(Color.parseColor("#b3aaaa"));
            rbFour.setTextColor(Color.parseColor("#ffffff"));
        }
    }

    //人才驿站，房屋信息，智慧生活分类跳转，需先判断是否认证，然后判断是否发布过
    //没有发布过,add_or_edit_type传0，发布过传true
    //没有发布过,isOnly传false，发不过传true
    public void authenAndJump(final int roleType) {
        if (TextUtils.isEmpty(getAccessToken())) {
            startAtvDonFinish(LoginActivity.class);
        } else {
            getHoldingActivity().checkUserAuthen(new OnUserAuthenListener() {
                @Override
                public void isAuthen() {
                    if (roleType == PageConfig.TYPE_YINYUAN){
                        Intent intent = new Intent(getActivity(), HomeCommonPublishActivity.class);
                        intent.putExtra(HomeCommonPublishActivity.MODULE_TYPE, roleType);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(getActivity(), WorkAndRencaiInfoActivity.class);
                        intent.putExtra(WorkAndRencaiInfoActivity.MODULE_TYPE, roleType);
                        startActivity(intent);
                    }

                }

                @Override
                public void isNotAuthen() {
                    final BaseDialog baseDialog = new BaseDialog(getActivity());
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

    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
            }

            Log.e("HHHHHHHHHHHHH", "定位成功" + location.getCity());
            if (firstLocation) {
                city = location.getCity();
                district = location.getDistrict();
                getCityList();   //城市列表，需要匹配定位的城市，赋予id
            }

            latitude = String.valueOf(location.getLatitude());
            longitude = String.valueOf(location.getLongitude());
            if (StringUtil.isAllNotEmpty(longitude, latitude))
                KV.put(Define.CURR_LONGLAT_ADDRESS, longitude + "," + latitude);

            if (city == null) {
                firstLocation = true;
            } else {
                firstLocation = false;
            }

//
        }
    }

    /**
     * 城市列表，需要匹配定位的城市，赋予id
     */
    private void getCityList() {
        selectCityPresenter = new SelectCityPresenter(this);
        selectCityPresenter.getCityList("");
    }

    /**
     * 获取所选城市下的区列表
     */
    public void getAreaList() {
        selectCityPresenter.loadAreaData(getCurrCityId(), new BaseNewNetModelimpl<List<ThirdAreaBean>>() {
            @Override
            protected void onFail(int type, String msg) {
                dismiss();
                showTips(msg);
            }

            @Override
            protected void onSuccess(int code, BaseResultInfo<List<ThirdAreaBean>> respnse, String source) {
                dismiss();
                if (respnse.getData() != null) {
                    List<ThirdAreaBean> areaBeanList = respnse.getData();
                    for (ThirdAreaBean areaBean : areaBeanList) {
                        int third_area_id = areaBean.getThird_area_id();
                        String third_area_name = areaBean.getThird_area_name();
                        if (Hawk.get(Define.CURR_CITY_THIRD_NAME, "").equals(third_area_name)) {
                            Hawk.put(Define.CURR_CITY_THIRD_ID, third_area_id);
                            tvCity.setText(TextUtils.isEmpty(district) ? "地区" : district);
                            break;
                        }
                    }
                    refreshBottom();
                }
            }

            @Override
            protected Type getType() {
                return new TypeToken<BaseResultInfo<List<ThirdAreaBean>>>() {
                }.getType();
            }
        });
    }

    @Override
    public void getCityListSuccess(final List<CityBean> list) {
        if (list == null) {
            return;
        }
        //如果当前定位城市和上一次选择的城市不同，则需要弹框
        String selectCity = (String) Hawk.get(Define.CURR_CITY_NAME, "");
        String selectDistrict = (String) Hawk.get(Define.CURR_CITY_THIRD_NAME);
        if (!TextUtils.isEmpty(selectCity)) {//不为空才进行弹框，为空直接赋值
            if (!TextUtils.isEmpty(city) && !TextUtils.isEmpty(selectCity)) {//都不为空才进行弹框

                if (!city.equals(selectCity)) {

                    MainActivity activity = (MainActivity) getActivity();
                    if (activity != null) {
                        activity.showCityDialog(city, selectCity, list, district);
                    }
                }
            }
        } else {//如果为空，则表示第一次进来
            for (int i = 0; i < list.size(); i++) {
                if (TextUtils.isEmpty(city)) {
                    return;
                }
                if (list.get(i).getArea_name().contains(city)) {
                    int area_id = list.get(i).getArea_id();
                    Hawk.put(Define.CURR_CITY_ID, area_id);
                    Hawk.put(Define.CURR_CITY_NAME, city);
                    Hawk.put(Define.CURR_CITY_THIRD_NAME, district);
                    getAreaList();
                    break;
                }
            }
        }

    }

    @Override
    public void getCityListFailed(int code, String msg) {
        showTips(msg);
    }

    @Override
    public void getUnreadMessageCountSuccess(String count) {
        if (TextUtils.isEmpty(count)) {
            tvMessageCount.setVisibility(View.GONE);
            return;
        }
        int msg_count = Integer.parseInt(count);
        this.msg_count = msg_count;
        if (msg_count == 0) {
            tvMessageCount.setVisibility(View.GONE);
        } else {
            tvMessageCount.setVisibility(View.VISIBLE);
            tvMessageCount.setText(msg_count + "");
        }
        if (msg_count > 99) {
            tvMessageCount.setText("99+");
        }

    }

    @Override
    public void getUnreadMessageCountFailed(int code, String msg) {
        showTips(msg);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_CITY:
                if (resultCode == RESULT_OK) {
                    String city_name = data.getStringExtra(Define.INTENT_DATA);
                    tvCity.setText(TextUtils.isEmpty(city_name) ? "" : city_name);
                    refreshBottom();
                }
                break;
        }
    }


    public void refreshBottom() {
        for (BaseInitFragment fragment : fragments) {
            if (fragment instanceof HomeBottomFragment) {
                ((HomeBottomFragment) fragment).refreshData();
            }
        }
    }

    /**
     * 人才驿站，智慧生活，房屋信息分类适配器
     */
    class RecyclerViewAdapter extends BaseMultiItemQuickAdapter<HomeRecyclerViewModel, BaseViewHolder> {
        public RecyclerViewAdapter(List<HomeRecyclerViewModel> data) {
            super(data);
            addItemType(HomeRecyclerViewModel.TYPE_RENCAI, R.layout.item_recycler_home_middle);
            addItemType(HomeRecyclerViewModel.TYPE_SMART, R.layout.item_recycler_home_middle);
            addItemType(HomeRecyclerViewModel.TYPE_FANGWU, R.layout.item_recycler_home_middle);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeRecyclerViewModel item) {
            LinearLayout lay_ll_root = (LinearLayout) helper.getView(R.id.lay_ll_root);
            TextView tv_more = (TextView) helper.getView(R.id.tv_more);
            if (item.getItemType() == HomeRootModel.TYPE_RENCAI) {
                HomeRootModel.ClassListBean module_class = item.getModule_class();
                List<HomeRootModel.ClassListBean.ClassChildListBean> child_list = module_class.getChild_list();
                for (HomeRootModel.ClassListBean.ClassChildListBean listBean : child_list) {
                    listBean.setModule_type(HomeRootModel.TYPE_RENCAI);
                }
                RecyclerView recyclerView = (RecyclerView) helper.getView(R.id.mRecyclerView);
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setHasFixedSize(true);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }

                };
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(new MiddleRecyclerViewAdapter(child_list));
                helper.setText(R.id.tv_title, item.getModule_class().getName());
                tv_more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startAtvDonFinish(TalentInnListNewActivity.class);
                    }
                });
            } else if (item.getItemType() == HomeRootModel.TYPE_SMART) {
                HomeRootModel.ClassListBean module_class = item.getModule_class();
                List<HomeRootModel.ClassListBean.ClassChildListBean> child_list = module_class.getChild_list();
                for (HomeRootModel.ClassListBean.ClassChildListBean listBean : child_list) {
                    listBean.setModule_type(HomeRootModel.TYPE_SMART);
                }
                RecyclerView recyclerView = (RecyclerView) helper.getView(R.id.mRecyclerView);
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setHasFixedSize(true);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }

                };
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setAdapter(new MiddleRecyclerViewAdapter(child_list));
                helper.setText(R.id.tv_title, item.getModule_class().getName());
                tv_more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startAtvDonFinish(IntelligentFamilyListActivity.class);
                    }
                });
            } else if (item.getItemType() == HomeRootModel.TYPE_FANGWU) {
                HomeRootModel.ClassListBean module_class = item.getModule_class();
                List<HomeRootModel.ClassListBean.ClassChildListBean> child_list = module_class.getChild_list();
                for (HomeRootModel.ClassListBean.ClassChildListBean listBean : child_list) {
                    listBean.setModule_type(HomeRootModel.TYPE_FANGWU);
                }
                RecyclerView recyclerView = (RecyclerView) helper.getView(R.id.mRecyclerView);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
                    }

                };
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(new MiddleRecyclerViewAdapter(child_list));
                helper.setText(R.id.tv_title, item.getModule_class().getName());
                tv_more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startAtvDonFinish(HouseRentingActivity.class);
                    }
                });
            }
            x.image().bind((ImageView) helper.getView(R.id.iv_pic), item.getModule_class().getImage(), ImageUtils.defaulHeadOptionsTwo());
            Glide.with(getActivity())
                    .load(item.getModule_class().getIndex_image())
                    .into(new ViewTarget<View, Drawable>(lay_ll_root) {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition transition) {
                            this.view.setBackground(resource.getCurrent());
                        }
                    });
        }
    }


    /**
     * 人才驿站，智慧生活，房屋信息分类下的
     */
    class MiddleRecyclerViewAdapter extends BaseQuickAdapter<HomeRootModel.ClassListBean.ClassChildListBean, BaseViewHolder> {

        public MiddleRecyclerViewAdapter(@Nullable List<HomeRootModel.ClassListBean.ClassChildListBean> data) {
            super(R.layout.item_recycler_home_rencai, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, final HomeRootModel.ClassListBean.ClassChildListBean item) {
//            GlideUtil.show(getActivity(),item.getIndex_image(),(ImageView)helper.getView(R.id.iv_bg));
            x.image().bind((ImageView) helper.getView(R.id.iv_bg), item.getIndex_image(), ImageUtils.defaulHeadOptionsCenterCrop());
            if (item.getModule_type() == HomeRootModel.TYPE_RENCAI) {

                ViewWrap.handlerImageViewTwo(getActivity(), (ImageView) helper.getView(R.id.iv_bg), 3, 222, 186);

                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        authenAndJump(item.getType() - 1);
                    }
                });
            } else if (item.getModule_type() == HomeRootModel.TYPE_SMART) {

                ViewWrap.handlerImageViewTwo(getActivity(), (ImageView) helper.getView(R.id.iv_bg), 4, 166, 166);

                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //智慧生活
                        if (getHoldingActivity().isLogin()) {

                            if (item.getIndex_type() == 1){//订餐
                                DingCanServiceHomeActivity.go(getActivity(),DingCanServiceHomeActivity.DINGCAN);
                            }else if (item.getIndex_type() == 2){//酒店
                                DingCanServiceHomeActivity.go(getActivity(),DingCanServiceHomeActivity.HOTEL);
                            }else if (item.getIndex_type() == 3){//门票
                                DingCanServiceHomeActivity.go(getActivity(),DingCanServiceHomeActivity.MENPIAO);
                            }else{//默认为发单
                                Bundle bundle = new Bundle();
                                bundle.putString("name", item.getClass_name());
                                bundle.putInt("id", item.getClass_id());
                                bundle.putString("price", item.getPrice() + "");
                                bundle.putString("info_fee", item.getInfo_fee());
                                bundle.putInt("two_address", item.getTwo_address());
                                bundle.putInt("is_ad", item.getIs_ad());
                                startAtvDonFinish(PublishRequireNewActivity.class, bundle);
                            }
                        } else {
                            startAtvDonFinish(LoginActivity.class);
                        }

                    }
                });

            } else if (item.getModule_type() == HomeRootModel.TYPE_FANGWU) {

                ViewWrap.handlerImageViewTwo(getActivity(), (ImageView) helper.getView(R.id.iv_bg), 2, 335, 222);

                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //房屋信息
                        HouseRentingActivity.go(getActivity(), "", item.getClass_id(), item.getName());
                    }
                });
            }
//            GlideUtil.show(getActivity(),item.getIndex_image(),(ImageView)helper.getView(R.id.iv_bg));
//            x.image().bind((ImageView) helper.getView(R.id.iv_bg), item.getIndex_image(), ImageUtils.defaulHeadOptionsFixXY());
        }
    }

    /**
     * 大模块分类适配器
     */
    class HeaderRecyclerViewAdapter extends BaseQuickAdapter<HomeRootModel.ModuleTypeBean.ModuleTypeListBean, BaseViewHolder> {

        public HeaderRecyclerViewAdapter(@Nullable List<HomeRootModel.ModuleTypeBean.ModuleTypeListBean> data) {
            super(R.layout.item_recycler_header_home, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, HomeRootModel.ModuleTypeBean.ModuleTypeListBean item) {
            helper.setText(R.id.tv_title, item.getName());

            x.image().bind((ImageView) helper.getView(R.id.iv_avatar), item.getImage(), ImageUtils.defaulHeadOptionsTwo());
        }
    }

    /**
     * 底部viewpager内容页的适配器
     */
    private class ContentAdapter extends FragmentPagerAdapter {

        private List<String> titles;

        public ContentAdapter(List<String> titles, FragmentManager fm) {
            super(fm);
            this.titles = titles;
        }

        public void setTitles(List<String> titles) {
            this.titles = titles;
            notifyDataSetChanged();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    public void onDestroyView() {
        if (selectCityPresenter != null) {
            selectCityPresenter.onCancel();
        }
        super.onDestroyView();
        for (Callback.Cancelable call : calls) {
            call.cancel();
        }
        ButterKnife.unbind(this);
    }


    @Override
    public int registStartMode() {
        return singleTask;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLocClient != null) {
            mLocClient.stop();
        }
    }
}
