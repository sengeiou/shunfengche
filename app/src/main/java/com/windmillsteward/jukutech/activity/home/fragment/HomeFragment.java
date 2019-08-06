package com.windmillsteward.jukutech.activity.home.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.gongwen.marqueen.MarqueeView;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.MainActivity;
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.CapitalManagerActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.CarServiceActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.IntelligentFamilyListActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.HomeSearchActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.HousekeeperAlertsListActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.MessageListActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.ScanActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.SelectCityActivity;
import com.windmillsteward.jukutech.activity.home.fragment.activity.SelectCityView;
import com.windmillsteward.jukutech.activity.home.fragment.adapter.HomeFunctionAdapter;
import com.windmillsteward.jukutech.activity.home.fragment.adapter.TravelRecommendAdapter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.HomeBannerPresenter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.HomeFunctionPresenter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.HouseKeeperDataQuickListPresenter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.SelectCityPresenter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.TravelRecommendPresenter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.UnreadMessageCountPresenter;
import com.windmillsteward.jukutech.activity.home.houselease.activity.HouseRentingActivity;
import com.windmillsteward.jukutech.activity.home.insurance.activity.InsuranceListActivity;
import com.windmillsteward.jukutech.activity.home.legalexpert.activity.LegalExpertActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.TalentInnListActivity;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyListActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.StayAndTravelHomeActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.TravelDetailActivity;
import com.windmillsteward.jukutech.activity.home.think.activity.IdeaThinkListActivity;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.base.AppManager;
import com.windmillsteward.jukutech.base.BaseFragment;
import com.windmillsteward.jukutech.bean.CityBean;
import com.windmillsteward.jukutech.bean.HouseKeeperDataQuickBean;
import com.windmillsteward.jukutech.bean.PublicSelectInfo;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.bean.TravelRecommendBean;
import com.windmillsteward.jukutech.customview.CommonRefreshLayout;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.MyGridView;
import com.windmillsteward.jukutech.customview.MyScrollView;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

import static android.app.Activity.RESULT_OK;

/**
 * 描述：首页(已废弃，改用HomeFragmentTwo)
 * author:cyq
 * 2017-09-20
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class HomeFragment extends BaseFragment implements HomeBannerView, AdapterView.OnItemClickListener, HouseKeeperDataQuickView,
        TravelRecommendView, View.OnClickListener, SelectCityView, UnreadMessageCountView {

    private ImageView iv_scan;
    private ImageView iv_message;
    private ImageView iv_scroll_to_top;
    private ImageView iv_grab_order;
    private TextView tv_message_count;
    private TextView tv_city;
    private TextView tv_more;
    private TextView tv_left_travel_name;
    private TextView tv_right_travel_name;
    private LinearLayout lay_ll_status;
    private LinearLayout lay_ll_top;
    private LinearLayout lay_ll_search;
    private FlyBanner vp_top_banner;
    private FlyBanner vp_middle_banner;
    private MyGridView gv_functions;
    private MyGridView gv_chinese_style;
    private MyGridView gv_europe_and_america_style;
    private CommonRefreshLayout common_refresh;
    private MyScrollView sv_root;

    private MarqueeView<RelativeLayout, HouseKeeperDataQuickBean.ListBean> marqueeView;

    private List<SliderPictureInfo> bannerTopList = new ArrayList<>();

    private List<SliderPictureInfo> bannerMiddleList = new ArrayList<>();

    private HomeFunctionPresenter homeFunctionPresenter;
    private HomeBannerPresenter homeBannerPresenter;
    private TravelRecommendPresenter travelRecommendPresenter;
    private HouseKeeperDataQuickListPresenter houseKeeperDataQuickListPresenter;
    private SelectCityPresenter selectCityPresenter;
    private UnreadMessageCountPresenter unreadMessageCountPresenter;


    private TravelRecommendAdapter chineseTravelAdapter;
    private TravelRecommendAdapter otherTravelAdapter;
    private HomeFunctionAdapter homeFunctionAdapter;

    private LocationClient mLocClient;
    private MyLocationListener locationListener;

    private String latitude = "";
    private String longitude = "";
    public static String city = "";
    private int statusBarHeight;//状态栏高度

    private static final int SELECT_CITY = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflateView(R.layout.fragment_home);
        initView(view);
        initStatusBar();
        MainActivity activity = (MainActivity) AppManager.getAppManager().getActivity(MainActivity.class);
        activity.initPermision();
        initLocal();
        initData();
        return view;
    }


    private void initView(View view) {
        iv_scan = (ImageView) view.findViewById(R.id.iv_scan);
        sv_root = (MyScrollView) view.findViewById(R.id.sv_root);
        iv_message = (ImageView) view.findViewById(R.id.iv_message);
        iv_scroll_to_top = (ImageView) view.findViewById(R.id.iv_scroll_to_top);
        iv_grab_order = (ImageView) view.findViewById(R.id.iv_grab_order);
        tv_city = (TextView) view.findViewById(R.id.tv_city);
        tv_more = (TextView) view.findViewById(R.id.tv_more);
        tv_left_travel_name = (TextView) view.findViewById(R.id.tv_left_travel_name);
        tv_right_travel_name = (TextView) view.findViewById(R.id.tv_right_travel_name);
        tv_message_count = (TextView) view.findViewById(R.id.tv_message_count);
        lay_ll_top = (LinearLayout) view.findViewById(R.id.lay_ll_top);
        lay_ll_status = (LinearLayout) view.findViewById(R.id.lay_ll_status);
        lay_ll_search = (LinearLayout) view.findViewById(R.id.lay_ll_search);
        vp_top_banner = (FlyBanner) view.findViewById(R.id.vp_top_banner);
        vp_middle_banner = (FlyBanner) view.findViewById(R.id.vp_middle_banner);
        common_refresh = (CommonRefreshLayout) view.findViewById(R.id.common_refresh);
        marqueeView = (MarqueeView<RelativeLayout, HouseKeeperDataQuickBean.ListBean>) view.findViewById(R.id.marqueeView);
        gv_chinese_style = (MyGridView) view.findViewById(R.id.gv_chinese_style);
        gv_functions = (MyGridView) view.findViewById(R.id.gv_functions);
        gv_europe_and_america_style = (MyGridView) view.findViewById(R.id.gv_europe_and_america_style);

        gv_functions.setOnItemClickListener(this);
        iv_scan.setOnClickListener(this);
        lay_ll_search.setOnClickListener(this);
        iv_message.setOnClickListener(this);
        tv_city.setOnClickListener(this);
        iv_scroll_to_top.setOnClickListener(this);
        iv_grab_order.setOnClickListener(this);
        tv_more.setOnClickListener(this);

    }

    /**
     * 顶部标题栏渐变
     */
    private void initStatusBar() {
        //        //动态设置顶部高度
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        statusBarHeight = GraphicUtil.getStatusBarHeight(getActivity());
        params.setMargins(0, statusBarHeight, 0, 0);
        lay_ll_top.setLayoutParams(params);
        //动态设置顶部高度
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
        lay_ll_status.setLayoutParams(params1);
        sv_root.setScrollViewListener(new MyScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(int l, int y, int oldl, int oldt) {

                //toolbar的高度
                int toolbarHeight = lay_ll_top.getBottom();
                //当滑动的距离 <= toolbar高度的时候，改变Toolbar背景色的透明度，达到渐变的效果

                if (y <= toolbarHeight) {//不超过高度
                    float scale = (float) y / toolbarHeight;
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

    private void initData() {
        //轮播图请求
        homeBannerPresenter = new HomeBannerPresenter(getActivity(),this);
        homeBannerPresenter.getBannerTopList(1);
        homeBannerPresenter.getBannerMiddleList(2);
        initBannerClick();
        //功能模块
        homeFunctionPresenter = new HomeFunctionPresenter(getActivity());
        homeFunctionAdapter = new HomeFunctionAdapter(getActivity(), homeFunctionPresenter.creatFunctionsPart());
        gv_functions.setAdapter(homeFunctionAdapter);
        //管家快讯跑马灯
        houseKeeperDataQuickListPresenter = new HouseKeeperDataQuickListPresenter(this);
        houseKeeperDataQuickListPresenter.getHouseKeeperData(1, 5);
        //旅游推荐
        //中国风
        chineseTravelAdapter = new TravelRecommendAdapter(getActivity(), new ArrayList<TravelRecommendBean.ListBean>());
        gv_chinese_style.setAdapter(chineseTravelAdapter);
        gv_chinese_style.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TravelRecommendBean.ListBean bean = (TravelRecommendBean.ListBean) parent.getAdapter().getItem(position);
                Bundle bundle = new Bundle();
                bundle.putInt(TravelDetailActivity.DETAIL_ID,bean.getTravel_id());
                startAtvDonFinish(TravelDetailActivity.class,bundle);
            }
        });
        //欧美风
        otherTravelAdapter = new TravelRecommendAdapter(getActivity(), new ArrayList<TravelRecommendBean.ListBean>());
        gv_europe_and_america_style.setAdapter(otherTravelAdapter);
        gv_europe_and_america_style.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TravelRecommendBean.ListBean bean = (TravelRecommendBean.ListBean) parent.getAdapter().getItem(position);
                Bundle bundle = new Bundle();
                bundle.putInt(TravelDetailActivity.DETAIL_ID,bean.getTravel_id());
                startAtvDonFinish(TravelDetailActivity.class,bundle);
            }
        });

        travelRecommendPresenter = new TravelRecommendPresenter(this);
        travelRecommendPresenter.getTravelRecommendList();
        common_refresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                travelRecommendPresenter.getTravelRecommendList();
            }
        });

        //未读消息数
        if (!TextUtils.isEmpty(getAccessToken())) {
            unreadMessageCountPresenter = new UnreadMessageCountPresenter(this);
            unreadMessageCountPresenter.getUnreadMessageCount(getAccessToken());
        }
    }

    public void initUnReadMessage(){
        //未读消息数
        if (!TextUtils.isEmpty(getAccessToken())) {
            unreadMessageCountPresenter = new UnreadMessageCountPresenter(this);
            unreadMessageCountPresenter.getUnreadMessageCount(getAccessToken());
        }
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

    private void initBannerClick(){
        vp_top_banner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (bannerTopList.size()==0){
                    return;
                }
                if (homeBannerPresenter == null){
                    return;
                }
                SliderPictureInfo topSliderInfo = bannerTopList.get(position);
                homeBannerPresenter.bannerClick(topSliderInfo);

            }
        });

        vp_middle_banner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (bannerMiddleList.size()==0){
                    return;
                }
                if (homeBannerPresenter == null){
                    return;
                }
                SliderPictureInfo middleSliderInfo = bannerMiddleList.get(position);
                homeBannerPresenter.bannerClick(middleSliderInfo);

            }
        });

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
//            activity.setCity(TextUtils.isEmpty(city) ? "" : city);
            tv_city.setText(TextUtils.isEmpty(city) ? "地区" : city);

            mLocClient.unRegisterLocationListener(locationListener);//注销定位

            getCityList();   //城市列表，需要匹配定位的城市，赋予id
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
                if (TextUtils.isEmpty(city)){
                    showTips("请确认是否开启了定位权限",1);
                    return;
                }
                intent.putExtra(Define.INTENT_DATA, this.city);
                startAtvDonFinishForResult(SelectCityActivity.class, SELECT_CITY);
                break;
            case R.id.iv_scroll_to_top:
                sv_root.smoothScrollTo(0, 0);
                break;
            case R.id.iv_grab_order:
                startAtvDonFinish(IntelligentFamilyListActivity.class);
                break;
            case R.id.tv_more:
                startAtvDonFinish(HousekeeperAlertsListActivity.class);
                break;
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PublicSelectInfo publicSelectInfo = (PublicSelectInfo) parent.getAdapter().getItem(position);
        int click_id = publicSelectInfo.getId();
        if (click_id == 1) {//人才驿站
            startAtvDonFinish(TalentInnListActivity.class);
        } else if (click_id == 2) {//思想智库
            startAtvDonFinish(IdeaThinkListActivity.class);
        } else if (click_id == 3) {//智慧家庭
            startAtvDonFinish(IntelligentFamilyListActivity.class);
        } else if (click_id == 4) {//房屋出租
            startAtvDonFinish(HouseRentingActivity.class);
        } else if (click_id == 5) {//住宿旅行
            startAtvDonFinish(StayAndTravelHomeActivity.class);
        } else if (click_id == 6) {//车辆买卖
            startAtvDonFinish(CarServiceActivity.class);
        } else if (click_id == 7) {//大健康
            startAtvDonFinish(InsuranceListActivity.class);
        } else if (click_id == 8) {//名优特产
            startAtvDonFinish(SpecialtyListActivity.class);
        } else if (click_id == 9) {//资本管理
            startAtvDonFinish(CapitalManagerActivity.class);
        } else if (click_id == 10) {//法律专家
            startAtvDonFinish(LegalExpertActivity.class);
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
    public void getCityListSuccess(List<CityBean> list) {
        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (TextUtils.isEmpty(city)){
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
    public void getCityListFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void getUnreadMessageCountSuccess(String count) {
        if (TextUtils.isEmpty(count)) {
            tv_message_count.setVisibility(View.GONE);
            return;
        }
        int msg_count = Integer.parseInt(count);
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
    public void getUnreadMessageCountFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void getTopBannerListSuccess(List<SliderPictureInfo> list) {
        if (list == null) {
            return;
        }

        bannerTopList.clear();

        List<String> topList = new ArrayList<>();
        this.bannerTopList = list;

        for (SliderPictureInfo info:bannerTopList){
            String pic_url = info.getPic_url();
            topList.add(pic_url);
        }
        if (bannerTopList.size()>0){
            vp_top_banner.setImagesUrl(topList);
        }
    }

    @Override
    public void getTopBannerListFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void getMiddleBannerListSuccess(List<SliderPictureInfo> list) {
        if (list == null) {
            return;
        }
        bannerMiddleList.clear();
        List<String> middleList = new ArrayList<>();
        this.bannerMiddleList = list;
        for (SliderPictureInfo info:list){
            String pic_url = info.getPic_url();
            middleList.add(pic_url);
        }
        if (bannerMiddleList.size() > 0){
            vp_middle_banner.setImagesUrl(middleList);
        }

    }

    @Override
    public void getMiddleBannerListFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void getHouseKeeperDataQuickListSuccess(HouseKeeperDataQuickBean houseKeeperDataQuickBean) {
        if (houseKeeperDataQuickBean == null) {
            return;
        }
        List<HouseKeeperDataQuickBean.ListBean> list = houseKeeperDataQuickBean.getList();
//        MarqueeFactory<RelativeLayout, HouseKeeperDataQuickBean.ListBean> marqueeFactory = new MarqueeViewAdapter(getActivity());
//        marqueeFactory.setData(list);
//        marqueeView.setInAndOutAnim(R.anim.in_top, R.anim.out_bottom);
//        marqueeView.setMarqueeFactory(marqueeFactory);
//        marqueeView.startFlipping();
//        marqueeView.setOnItemClickListener(new OnItemClickListener<RelativeLayout, HouseKeeperDataQuickBean.ListBean>() {
//            @Override
//            public void onItemClickListener(RelativeLayout mView, HouseKeeperDataQuickBean.ListBean mData, int mPosition) {
////                showTips(mData.getTitle(), 1);
//                startAtvDonFinish(HousekeeperAlertsListActivity.class);
//            }
//        });

    }

    @Override
    public void getHouseKeeperDataQuickListFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void getHouseKeeperInitDataSuccess(HouseKeeperDataQuickBean houseKeeperDataQuickBean) {
        //没用到
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
    public void getTravelRecommendDataSuccess(TravelRecommendBean data) {
        common_refresh.refreshComplete();
        if (data == null) {
            return;
        }
//        //中国风
        List<TravelRecommendBean.ListBean> chineseStyleList = new ArrayList<>();
//        //欧美风
        List<TravelRecommendBean.ListBean> styleList = new ArrayList<>();
        List<TravelRecommendBean.ListBean> list = data.getList();
        for (int i = 0; i < list.size(); i++) {
            TravelRecommendBean.ListBean travelRecommendBean = list.get(i);
            int type = list.get(i).getType();
            String cover_url = list.get(i).getCover_url();
            int travel_id = list.get(i).getTravel_id();
            if (type == 1) {   //中国风
                chineseStyleList.add(travelRecommendBean);
            } else if (type == 2) {//欧美风
                styleList.add(travelRecommendBean);
            }
        }
        String travel_type_left_name = data.getTravel_type_left_name();
        String travel_type_right_name = data.getTravel_type_right_name();
        tv_left_travel_name.setText(TextUtils.isEmpty(travel_type_left_name)?"":travel_type_left_name);
        tv_right_travel_name.setText(TextUtils.isEmpty(travel_type_right_name)?"":travel_type_right_name);
        chineseTravelAdapter.updateList(chineseStyleList);
        otherTravelAdapter.updateList(styleList);
    }

    @Override
    public void getTravelRecommendDataFailed(int code, String msg) {
        common_refresh.refreshComplete();
        showTips(msg, 1);
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
