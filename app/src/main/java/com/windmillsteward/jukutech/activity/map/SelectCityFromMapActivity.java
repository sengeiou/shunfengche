package com.windmillsteward.jukutech.activity.map;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.blankj.utilcode.util.BarUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

public class SelectCityFromMapActivity extends BaseInitActivity implements OnGetGeoCoderResultListener,
        BaiduMap.OnMapStatusChangeListener, BaiduMap.OnMapTouchListener , BaiduMap.OnMapClickListener {
    public static final int GET_CITY_REQUEST_CODE = 300;
    public static final int GET_CITY_RESULT_CODE = 301;
    public static final int SEARCH_REQUEST_CODE = 1002;
    @Bind(R.id.mmap)
    MapView mmap;
    @Bind(R.id.tv_position)
    ImageView tvPosition;
    @Bind(R.id.lay_ll_top)
    LinearLayout layLlTop;
    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.lay_ll_search)
    LinearLayout layLlSearch;
    @Bind(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private BaiduMap mBaiduMap;
    private BDLocation bdLocation;

    private LatLng currLatLng;
    private String address;
    private GeoCoder geoCoder;

    //定位
    private LocationClient mLocClient;
    private MyLocationListener locationListener;

    private boolean isFrist;

    private RecyclerViewAdapter adapter;
    private List<PoiInfo> list;
    private boolean isClickItem = false;


    //    private TextView tv_curr_location;

    private ReverseGeoCodeOption reverseGeoCodeOption;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_select_city2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).statusBarColor(R.color.bg_ededed).statusBarDarkFont(true, 0.2f).fitsSystemWindows(false).init();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    public static void go(Activity activity) {
        Intent intent = new Intent(activity, SelectCityFromMapActivity.class);
        activity.startActivityForResult(intent, GET_CITY_REQUEST_CODE);
    }

    public static void go(Fragment activity) {
        Intent intent = new Intent(activity.getActivity(), SelectCityFromMapActivity.class);
        activity.startActivityForResult(intent, GET_CITY_REQUEST_CODE);
    }

    @Override
    protected void initView(View view) {
        hidTitleView();
        showContentView();
        handlerPadding();
        initMap();
        //获取定位信息
        if (chekcLocationPermission()) {
            showDialog("正在定位...");
            initLocation();
        }
    }

    //设置头部padding
    private void handlerPadding() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            layLlTop.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0);
        }
    }

    @Override
    protected void initData() {
        initAdapter();
//        initHeader();
    }

    //初始化适配器
    public void initAdapter() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        adapter.setEnableLoadMore(false);

        //事件监听
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PoiInfo poiInfo = list.get(position);
                if (poiInfo != null) {
                    setChioseLatLng(poiInfo);
                    isClickItem = true;
                    SelectCityFromMapActivity.this.adapter.setClickPositon(position);
                    setPosition2Center(mBaiduMap, new LatLng(poiInfo.getLocation().latitude, poiInfo.getLocation().longitude));
                }
            }
        });
    }

    //初始化定位
    private void initLocation() {
        mLocClient = new LocationClient(this);

        locationListener = new MyLocationListener();
        mLocClient.registerLocationListener(locationListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setIsNeedAddress(true);//允许获取当前的位置信息，不开启则获取不了当前城市
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(30000); // 大于1000才循环
        option.setIsNeedAddress(true);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(final BDLocation location) {
            if (location == null) {
                return;
            }
            dismiss();
            bdLocation = location;

            if (!isFrist) {
                isFrist = true;
                mmap.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setPosition2Center(mBaiduMap, new LatLng(location.getLatitude(), location.getLongitude()));
                    }
                }, 500);
            }
        }
    }

    //初始化地图
    private void initMap() {
        mBaiduMap = mmap.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 不显示地图缩放控件（按钮控制栏）
        mmap.showZoomControls(false);
        geoCoder = GeoCoder.newInstance();
        geoCoder.setOnGetGeoCodeResultListener(this);
        reverseGeoCodeOption = new ReverseGeoCodeOption();
        mBaiduMap.setOnMapStatusChangeListener(this);
        mBaiduMap.setOnMapTouchListener(this);
        mBaiduMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if (latLng != null){
            setPosition2Center(mBaiduMap,latLng);
        }
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        return false;
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
        currLatLng = reverseGeoCodeResult.getLocation();
        List<PoiInfo> poiList = reverseGeoCodeResult.getPoiList();
        if (poiList != null) {
            if (poiList.size() != 0) {
                PoiInfo poiInfo = poiList.get(0);
                setChioseLatLng(poiInfo);
            }
            list.clear();
            list.addAll(poiList);
            adapter.notifyDataSetChanged();
        }

    }


    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus) {

    }

    @Override
    public void onMapStatusChangeStart(MapStatus mapStatus, int i) {

    }

    @Override
    public void onMapStatusChange(MapStatus mapStatus) {

    }

    @Override
    public void onMapStatusChangeFinish(MapStatus mapStatus) {
        LatLng target = mapStatus.target;
        if (target != null) {
            if (!isClickItem) {
                geoCoder.reverseGeoCode(reverseGeoCodeOption.location(target));
                mRecyclerView.scrollToPosition(0);
            }
        }
    }


    @Override
    public void onTouch(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case ACTION_DOWN:
                break;
            case ACTION_MOVE:
                break;
            case ACTION_UP:
                isClickItem = false;
                if (adapter!= null) {
                    SelectCityFromMapActivity.this.adapter.setClickPositon(0);
                }
                break;
        }
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }


    @Override
    protected void refreshPageData() {

    }

    /**
     * 设置中心点和添加marker * * @param map * @param bdLocation * @param isShowLoc
     */
    public void setPosition2Center(BaiduMap map, LatLng ll) {
        currLatLng = ll;
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(ll, 18.0f);
        map.animateMapStatus(mapStatusUpdate);
    }

    private void setChioseLatLng(PoiInfo poiInfo) {
        if (poiInfo != null) {
            address = poiInfo.name + "(" + poiInfo.address + ")";
            currLatLng = new LatLng(poiInfo.getLocation().latitude, poiInfo.getLocation().longitude);
        }
    }

    @OnClick({R.id.tv_position, R.id.lay_ll_search, R.id.tv_back, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_position:
                if (bdLocation != null) {
                    isClickItem = false;
                    if (adapter!= null){
                        SelectCityFromMapActivity.this.adapter.setClickPositon(0);
                    }
                    setPosition2Center(mBaiduMap, new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude()));
                }
                break;
            case R.id.lay_ll_search:
                Intent intent1 = new Intent(this, SearchBaiduCityActivity.class);
                startActivityForResult(intent1, SEARCH_REQUEST_CODE);
                break;
            case R.id.tv_back:
                finish();
                break;
            case R.id.tv_right:
                if (currLatLng != null) {
                    Intent intent = new Intent();
                    intent.putExtra("address", address);
                    intent.putExtra("lat", currLatLng.latitude);
                    intent.putExtra("lng", currLatLng.longitude);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SEARCH_REQUEST_CODE) {
            if (data == null)
                return;
            double lat = data.getDoubleExtra("lat", 0);
            double lng = data.getDoubleExtra("lng", 0);
            address = data.getStringExtra("address");
            isClickItem = false;
            setPosition2Center(mBaiduMap, new LatLng(lat, lng));
            geoCoder.reverseGeoCode(reverseGeoCodeOption.location(new LatLng(lat, lng)));
        }
    }

    public class RecyclerViewAdapter extends BaseQuickAdapter<PoiInfo, BaseViewHolder> {

        public int clickPositon = 0;

        public RecyclerViewAdapter(@Nullable List<PoiInfo> data) {
            super(R.layout.item_recycler_map, data);
        }

        public void setClickPositon(int clickPositon) {
            this.clickPositon = clickPositon;
            notifyDataSetChanged();
        }

        @Override
        protected void convert(BaseViewHolder helper, final PoiInfo item) {
            helper.setText(R.id.tv_01, item.name).
                    setText(R.id.tv_02, item.address);

            if (clickPositon == helper.getAdapterPosition()) {
                helper.getView(R.id.iv_select).setVisibility(View.VISIBLE);
            } else {
                helper.getView(R.id.iv_select).setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onPause() {
        mmap.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mmap.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mBaiduMap.setMyLocationEnabled(false);
        mmap.onDestroy();
        mmap = null;
        if (geoCoder != null)
            geoCoder.destroy();
        super.onDestroy();
    }
}
