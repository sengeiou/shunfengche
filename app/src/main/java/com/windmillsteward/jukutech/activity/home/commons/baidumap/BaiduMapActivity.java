package com.windmillsteward.jukutech.activity.home.commons.baidumap;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;

import java.io.File;
import java.net.URISyntaxException;

/**
 * 描述：
 * 时间：2018/1/29
 * 作者：xjh
 */

public class BaiduMapActivity extends BaseActivity {

    public static final String HOTEL_NAME = "HOTEL_NAME";
    public static final String LATITUDE = "LATITUDE";  // 纬度
    public static final String LONGITUDE = "LONGITUDE";  // 精度

    private MapView mapView;
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;

    private BaiduMap mBaiduMap;

    private String hotel_name;
    private String longitude;
    private String latitude;

    private View marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidumap);
        initView();
        initToolbar();
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            longitude = extras.getString(LONGITUDE);
            latitude = extras.getString(LATITUDE);
            hotel_name = extras.getString(HOTEL_NAME);
        }
        marker = LayoutInflater.from(this).inflate(R.layout.view_baidumap_macker, ((ViewGroup) mapView.getParent()),false);
        TextView tv_hotel_name = marker.findViewById(R.id.tv_hotel_name);
        tv_hotel_name.setText(hotel_name);

        initBaiduMap();
    }

    private void initBaiduMap() {
        mBaiduMap = mapView.getMap();

        //定义Maker坐标点
        LatLng point = new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(marker);
        MarkerOptions ooA = new MarkerOptions().position(point).icon(bitmap)
                .zIndex(5).draggable(false);

        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
        mBaiduMap.setMapStatus(msu);

        Marker marker = (Marker) (mapView.getMap().addOverlay(ooA));

        MapStatusUpdate u = MapStatusUpdateFactory
                .newLatLng(point);
        mBaiduMap.setMapStatus(u);

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                new AlertDialog(BaiduMapActivity.this).builder()
                        .setTitle("导航")
                        .setMsg("将使用到外部导航应用")
                        .setCancelable(true)
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setUpGaodeAppByMine();
                            }
                        })
                        .show();
                return true;
            }
        });
    }

    /**
     * 使用高德地图导航
     */
    void setUpGaodeAppByMine(){
        try {
            Intent intent = Intent.getIntent("androidamap://route?sourceApplication=softname&sname=我的位置&dlat="
                    +latitude+"&dlon="+longitude+"&dname="+hotel_name+"&dev=0&m=0&t=1");
            if(isInstallByread("com.autonavi.minimap")){
                startActivity(intent);
            }else if (isInstallByread("com.baidu.BaiduMap")) {
                Intent intent1 = Intent.getIntent("intent://map/navi?location="+latitude+","+longitude +
                        "&type=TIME&src=thirdapp.navi.hndist.sydt#Intent;scheme=bdapp;" +
                        "package=com.baidu.BaiduMap;end");
                startActivity(intent1);
            } else {
                showTips("只能使用高德地图或百度地图导航",0);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否安装目标应用
     * @param packageName 目标应用安装后的包名
     * @return 是否已安装目标应用
     */
    private boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        mapView = (MapView) findViewById(R.id.mapView);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("导航");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


}
