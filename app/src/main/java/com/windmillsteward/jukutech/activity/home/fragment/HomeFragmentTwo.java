package com.windmillsteward.jukutech.activity.home.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.orhanobut.hawk.Hawk;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.MainActivity;
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.CapitalManagerActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.CarServiceActivity;
import com.windmillsteward.jukutech.activity.newpage.customerservice.activity.HomeCustomerActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.IntelligentFamilyListActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.HomeSearchActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.HousekeeperAlertsListActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.MessageListActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.ScanActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.SelectCityActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.SelectCityView;
import com.windmillsteward.jukutech.activity.home.fragment.adapter.HomeRecyclerViewAdapter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.HomeBannerPresenter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.HomeFunctionPresenter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.HomeRecommendPresenter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.HouseKeeperDataQuickListPresenter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.SelectCityPresenter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.TravelRecommendPresenter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.UnreadMessageCountPresenter;
import com.windmillsteward.jukutech.activity.home.houselease.activity.HouseRentingActivity;
import com.windmillsteward.jukutech.activity.home.insurance.activity.InsuranceListActivity;
import com.windmillsteward.jukutech.activity.home.legalexpert.activity.LegalExpertActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.TalentInnListNewActivity;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyDetailActivity;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyHomeActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.TravelDetailActivity;
import com.windmillsteward.jukutech.activity.home.think.activity.IdeaThinkListActivity;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.base.AppManager;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.base.KV;
import com.windmillsteward.jukutech.bean.CityBean;
import com.windmillsteward.jukutech.bean.GuessYouLikeBean;
import com.windmillsteward.jukutech.bean.HomeBean;
import com.windmillsteward.jukutech.bean.HouseKeeperDataQuickBean;
import com.windmillsteward.jukutech.bean.RecommendGridBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.bean.TravelRecommendBean;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.GraphicUtil;
import com.windmillsteward.jukutech.utils.StringUtil;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * 描述：
 * author:cyq
 * 2018-05-31
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class HomeFragmentTwo extends BaseFragment implements HouseKeeperDataQuickView, View.OnClickListener, SelectCityView,
        UnreadMessageCountView, HomeRecyclerViewAdapter.RecyclerViewDataCallBack, HomeBannerView, HomeRecommendView {

    private RecyclerView mRecyclerView;
    private SmartRefreshLayout common_refresh;
    private LinearLayout lay_ll_status;
    private TextView tv_city;
    private LinearLayout lay_ll_search;
    private ImageView iv_scan;
    private ImageView iv_message;
    private ImageView iv_scroll_to_top;
    private ImageView iv_grab_order;
    private TextView tv_message_count;
    private LinearLayout lay_ll_top;

    private HomeRecyclerViewAdapter adapter;

    private HomeFunctionPresenter homeFunctionPresenter;

    private HomeBannerPresenter homeBannerPresenter;
    private TravelRecommendPresenter travelRecommendPresenter;
    private HomeRecommendPresenter homeRecommendPresenter;
    private HouseKeeperDataQuickListPresenter houseKeeperDataQuickListPresenter;
    private SelectCityPresenter selectCityPresenter;
    private UnreadMessageCountPresenter unreadMessageCountPresenter;

    private LocationClient mLocClient;
    private MyLocationListener locationListener;

    private int statusBarHeight;//状态栏高度
    public static int mDistance = 0;//总的滑动距离
    private String latitude = "";
    private String longitude = "";
    public static String city = "";

    private static final int SELECT_CITY = 100;
    public static int msg_count;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateView(R.layout.fragment_home_two);
        initView(view);
        initStatusBar();
        MainActivity activity = (MainActivity) AppManager.getAppManager().getActivity(MainActivity.class);
        activity.initPermision();
        initLocal();
        initData();
        return view;
    }


    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        common_refresh = (SmartRefreshLayout) view.findViewById(R.id.common_refresh);
        lay_ll_status = (LinearLayout) view.findViewById(R.id.lay_ll_status);
        tv_city = (TextView) view.findViewById(R.id.tv_city);
        lay_ll_search = (LinearLayout) view.findViewById(R.id.lay_ll_search);
        iv_scan = (ImageView) view.findViewById(R.id.iv_scan);
        iv_message = (ImageView) view.findViewById(R.id.iv_message);
        tv_message_count = (TextView) view.findViewById(R.id.tv_message_count);
        lay_ll_top = (LinearLayout) view.findViewById(R.id.lay_ll_top);
        iv_scroll_to_top = (ImageView) view.findViewById(R.id.iv_scroll_to_top);
        iv_grab_order = (ImageView) view.findViewById(R.id.iv_grab_order);

        iv_scan.setOnClickListener(this);
        lay_ll_search.setOnClickListener(this);
        iv_message.setOnClickListener(this);
        tv_city.setOnClickListener(this);
        iv_scroll_to_top.setOnClickListener(this);
        iv_grab_order.setOnClickListener(this);
    }

    public void initLocal() {
        mLocClient = new LocationClient(getActivity());

        locationListener = new MyLocationListener();
        mLocClient.registerLocationListener(locationListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setIsNeedAddress(true);//允许获取当前的位置信息，不开启则获取不了当前城市
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1500); // 大于1000才循环
        option.setIsNeedAddress(true);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    private void initData() {
        //首页recycleview数据层级
        homeFunctionPresenter = new HomeFunctionPresenter(getActivity());
        List<HomeBean> homeList = homeFunctionPresenter.homeRecyclerViewData();

        adapter = new HomeRecyclerViewAdapter(getActivity(), homeList, this);

        common_refresh.setRefreshHeader(new ClassicsHeader(getActivity()));
        common_refresh.setEnableLoadMore(false);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);

        //轮播图请求
        homeBannerPresenter = new HomeBannerPresenter(getActivity(), this);
        homeBannerPresenter.getBannerTopList(1);
        homeBannerPresenter.getBannerMiddleList(2);

        //管家快讯跑马灯
        houseKeeperDataQuickListPresenter = new HouseKeeperDataQuickListPresenter(this);
        houseKeeperDataQuickListPresenter.getHouseKeeperData(1, 5);

        //推荐模块
        homeRecommendPresenter = new HomeRecommendPresenter(getActivity(), this);
        homeRecommendPresenter.getHomeRecommendList();

        //猜你喜欢
        homeRecommendPresenter.getGuessYouLikeList();

        //未读消息数
        if (!TextUtils.isEmpty(getAccessToken())) {
            unreadMessageCountPresenter = new UnreadMessageCountPresenter(this);
            unreadMessageCountPresenter.getUnreadMessageCount(getAccessToken());
        }

        common_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                homeBannerPresenter.getBannerTopList(1);
                homeBannerPresenter.getBannerMiddleList(2);
                houseKeeperDataQuickListPresenter.getHouseKeeperData(1, 5);
                homeRecommendPresenter.getHomeRecommendList();
                homeRecommendPresenter.getGuessYouLikeList();
            }
        });
    }

    private void initStatusBar() {
        //        //动态设置顶部高度
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        statusBarHeight = GraphicUtil.getStatusBarHeight(getActivity());
        params.setMargins(0, statusBarHeight, 0, 0);
        lay_ll_top.setLayoutParams(params);
        //动态设置顶部高度
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        lay_ll_status.setLayoutParams(params1);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mDistance += dy;
                //toolbar的高度
                int toolbarHeight = lay_ll_top.getBottom();
                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果
                if (mDistance <= 0) {
                    mDistance = 0;
                    lay_ll_top.setBackgroundColor(Color.TRANSPARENT);
                } else if (mDistance <= toolbarHeight) {//不超过高度
                    float scale = (float) mDistance / toolbarHeight;
                    float alpha = scale * 255;
                    lay_ll_top.setBackgroundColor(Color.argb((int) alpha, 35, 171, 172));
                    //动态设置顶部高度
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    statusBarHeight = GraphicUtil.getStatusBarHeight(getActivity());
                    params.setMargins(0, statusBarHeight, 0, 0);
                    lay_ll_top.setLayoutParams(params);
                    lay_ll_status.setVisibility(View.GONE);

                } else {//超过高度
                    //将标题栏的颜色设置为完全不透明状态
                    lay_ll_top.setBackgroundResource(R.color.color_23abac);
                    lay_ll_status.setVisibility(View.VISIBLE);
                    //动态设置顶部高度
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    statusBarHeight = GraphicUtil.getStatusBarHeight(getActivity());
                    params.setMargins(0, 0, 0, 0);
                    lay_ll_top.setLayoutParams(params);
                }
            }
        });
    }

    public void initUnReadMessage() {
        //未读消息数
        if (!TextUtils.isEmpty(getAccessToken())) {
            unreadMessageCountPresenter = new UnreadMessageCountPresenter(this);
            unreadMessageCountPresenter.getUnreadMessageCount(getAccessToken());
        }
    }


    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
            }
            latitude = String.valueOf(location.getLatitude());
            longitude = String.valueOf(location.getLongitude());
            city = location.getCity();
            MainActivity activity = (MainActivity) AppManager.getAppManager().getActivity(MainActivity.class);
//            activity.setCity(city);
            tv_city.setText(TextUtils.isEmpty(city) ? "地区" : city);

            mLocClient.unRegisterLocationListener(locationListener);//注销定位

            getCityList();   //城市列表，需要匹配定位的城市，赋予id

            if (StringUtil.isAllNotEmpty(longitude, latitude))
                KV.put(Define.CURR_LONGLAT_ADDRESS, longitude + "," + latitude);
        }
    }

    /**
     * 城市列表，需要匹配定位的城市，赋予id
     */
    private void getCityList() {

        selectCityPresenter = new SelectCityPresenter(this);
        selectCityPresenter.getCityList("");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lay_ll_search:
                startAtvDonFinish(HomeSearchActivity.class);
                break;
            case R.id.iv_scan:
                startAtvDonFinish(ScanActivity.class);
                break;
            case R.id.iv_message:
                if (TextUtils.isEmpty(getAccessToken())) {
                    startAtvDonFinish(LoginActivity.class);
                } else {
                    startAtvDonFinish(MessageListActivity.class);
                }
                break;
            case R.id.tv_city:
                if (TextUtils.isEmpty(city)) {
                    showTips("请确认是否开启了定位权限", 1);
                    return;
                }
                intent.putExtra(Define.INTENT_DATA, this.city);
                startAtvDonFinishForResult(SelectCityActivity.class, SELECT_CITY);
                break;
            case R.id.iv_scroll_to_top:
                mRecyclerView.smoothScrollToPosition(0);
                break;
            case R.id.iv_grab_order:
                startAtvDonFinish(IntelligentFamilyListActivity.class);
                break;
//            case R.id.tv_more:
//                startAtvDonFinish(HousekeeperAlertsListActivity.class);
//                break;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

            MainActivity activity = (MainActivity) AppManager.getAppManager().getActivity(MainActivity.class);
//            String city = activity.getCity();
            tv_city.setText(TextUtils.isEmpty(city) ? "地区" : city);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SELECT_CITY:
                if (resultCode == RESULT_OK) {
                    String city_name = data.getStringExtra(Define.INTENT_DATA);
                    MainActivity activity = (MainActivity) AppManager.getAppManager().getActivity(MainActivity.class);
//                    activity.setCity(city_name);
                    tv_city.setText(TextUtils.isEmpty(city_name) ? "" : city_name);
                }
                break;
        }
    }

    @Override
    public void setOnBannerClick(SliderPictureInfo data, int position) {
        homeBannerPresenter.bannerClick(data);
    }

    @Override
    public void setOnFunctionClick(int position) {
        position = position + 1;
        if (position == 1) {//人才驿站
//            startAtvDonFinish(TalentInnListActivity.class);F=
            startAtvDonFinish(TalentInnListNewActivity.class);
        } else if (position == 2) {//思想智库
            startAtvDonFinish(IdeaThinkListActivity.class);
        } else if (position == 3) {//智慧家庭
            startAtvDonFinish(IntelligentFamilyListActivity.class);
        } else if (position == 4) {//房屋出租
            startAtvDonFinish(HouseRentingActivity.class);
        } else if (position == 5) {//住宿旅行
//            startAtvDonFinish(StayAndTravelHomeActivity.class);
            startAtvDonFinish(HomeCustomerActivity.class);
        } else if (position == 6) {//车辆买卖
            startAtvDonFinish(CarServiceActivity.class);
        } else if (position == 7) {//大健康
            startAtvDonFinish(InsuranceListActivity.class);
        } else if (position == 8) {//名优特产
            startAtvDonFinish(SpecialtyHomeActivity.class);
        } else if (position == 9) {//资本管理
            startAtvDonFinish(CapitalManagerActivity.class);
        } else if (position == 10) {//法律专家
            startAtvDonFinish(LegalExpertActivity.class);
        }
    }

    @Override
    public void setOnMarqueeViewClick(int position) {
        startAtvDonFinish(HousekeeperAlertsListActivity.class);
    }

    @Override
    public void setOnMarqueeViewMoreClick() {
        startAtvDonFinish(HousekeeperAlertsListActivity.class);
    }

    @Override
    public void setOnRecommondClick(TravelRecommendBean.ListBean data, int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(TravelDetailActivity.DETAIL_ID, data.getTravel_id());
        startAtvDonFinish(TravelDetailActivity.class, bundle);
    }

    @Override
    public void setOnRecommendClick(RecommendGridBean.ListBean data) {
        if (data == null) {
            return;
        }
        homeRecommendPresenter.recommendClick(data);
    }

    @Override
    public void setOnRecommendMoreClick(RecommendGridBean data) {
        if (data == null) {
            return;
        }

        homeRecommendPresenter.recommendMoreClick(data);

    }

    @Override
    public void setOnGuessYouLikeClick(GuessYouLikeBean.ListBean bean) {
        if (bean == null) {
            return;
        }
        Bundle bundle = new Bundle();
        Intent intent = new Intent();
        bundle.putInt(Define.INTENT_DATA, bean.getCommodity_id());
        intent.putExtras(bundle);
        intent.setClass(getActivity(), SpecialtyDetailActivity.class);
        getActivity().startActivity(intent);
    }

    @Override
    public void getUnreadMessageCountSuccess(String count) {
        if (TextUtils.isEmpty(count)) {
            tv_message_count.setVisibility(View.GONE);
            return;
        }
        int msg_count = Integer.parseInt(count);
        this.msg_count = msg_count;
        if (msg_count == 0) {
            tv_message_count.setVisibility(View.GONE);
        } else {
            tv_message_count.setVisibility(View.VISIBLE);
            tv_message_count.setText(msg_count + "");
        }
        if (msg_count > 99) {
            tv_message_count.setText("99+");
        }


    }

    @Override
    public void getCityListSuccess(List<CityBean> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (TextUtils.isEmpty(city)) {
                return;
            }
            if (list.get(i).getArea_name().contains(city)) {
                int area_id = list.get(i).getArea_id();
                Hawk.put(Define.CURR_CITY_ID, area_id);
                Hawk.put(Define.CURR_CITY_NAME, city);
                break;
            }
        }
    }

    @Override
    public void getHouseKeeperDataQuickListSuccess(HouseKeeperDataQuickBean houseKeeperDataQuickBean) {
        common_refresh.finishRefresh();
        if (houseKeeperDataQuickBean == null) {
            return;
        }
        adapter.updateMarqueeData(houseKeeperDataQuickBean);
    }


    @Override
    public void getTopBannerListSuccess(List<SliderPictureInfo> list) {
        common_refresh.finishRefresh();
        if (list == null) {
            return;
        }
        adapter.updateTopBanner(list);
    }

    @Override
    public void getCityListFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void getUnreadMessageCountFailed(int code, String msg) {
        showTips(msg, 1);
    }


    @Override
    public void getTopBannerListFailed(int code, String msg) {
        common_refresh.finishRefresh();
        showTips(msg, 1);
    }

    @Override
    public void getHouseKeeperDataQuickListFailed(int code, String msg) {
        common_refresh.finishRefresh();
        showTips(msg, 1);
    }

    @Override
    public void getMiddleBannerListSuccess(List<SliderPictureInfo> list) {
        if (list == null) {
            return;
        }
        adapter.updateMiddleBanner(list);
    }

    @Override
    public void getHouseKeeperInitDataSuccess(HouseKeeperDataQuickBean houseKeeperDataQuickBean) {
        //没用到
    }

    @Override
    public void getMiddleBannerListFailed(int code, String msg) {
        common_refresh.finishRefresh();
        showTips(msg, 1);
    }

    @Override
    public void getHomeRecommendDataSuccess(List<RecommendGridBean> data) {
        common_refresh.finishRefresh();
        if (data == null) {
            return;
        }
        adapter.updateRecommendList(data);
    }

    @Override
    public void getHomeRecommendDataFailed(int code, String msg) {
        common_refresh.finishRefresh();
        showTips(msg, 1);
    }


    @Override
    public void getGuessYouLikeListSuccess(GuessYouLikeBean bean) {
        common_refresh.finishRefresh();
        if (bean == null) {
            return;
        }
        adapter.updateGuessYouLikeList(bean);
    }

    @Override
    public void getGuessYouLikeListFailed(int code, String msg) {
        common_refresh.finishRefresh();
        showTips(msg, 1);
    }


    @Override
    public void getHouseKeeperRefreshDataSuccess(HouseKeeperDataQuickBean bean) {
        //没用到
    }

    @Override
    public void getHouseKeeperRefreshDataFailure() {
        //没用到
    }

    @Override
    public void getHouseKeeperLoadNextDataSuccess(HouseKeeperDataQuickBean bean) {
        //没用到
    }

    @Override
    public void getHouseKeeperLoadNextFailure() {
        //没用到
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
