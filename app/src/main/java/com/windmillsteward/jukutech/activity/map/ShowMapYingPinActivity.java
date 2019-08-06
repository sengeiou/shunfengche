package com.windmillsteward.jukutech.activity.map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.model.OtherLocationModel;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.KV;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.UserInfo;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.MapNaviUtils;
import com.windmillsteward.jukutech.utils.StringUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

public class ShowMapYingPinActivity extends BaseInitActivity {
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_ZHAOPIN = 1;
    public static final int TYPE_YINGPIN = 2;
    @Bind(R.id.mmap)
    MapView mmap;
    @Bind(R.id.tv_shishi)
    TextView tv_shishi;
    @Bind(R.id.tv_position)
    ImageView tv_position;
    private BaiduMap mBaiduMap;

    private String longitude;
    private String zp_longitude;
    private String latitude;
    private String zp_latitude;

    private int require_id;
    //当前类型
    private int module_type;//模块类型

    @Override
    protected void initView(View view) {
        setMainTitle("地图");
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    /**
     * @param activity
     * @param type
     * @param require_id
     */
    public static void showMap(Activity activity, int type, int require_id, String longitude, String latitude) {
        Intent intent = new Intent(activity, ShowMapYingPinActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putInt("require_id", require_id);
        bundle.putString("latitude", latitude);
        bundle.putString("longitude", longitude);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_show_map;
    }

    @Override
    protected void initData() {
        showContentView();

        if (getIntent() != null) {
            zp_longitude = getIntent().getStringExtra("longitude");
            zp_latitude = getIntent().getStringExtra("latitude");
            module_type = getIntent().getIntExtra("type", 0);
            require_id = getIntent().getIntExtra("require_id", 0);
        }

        mBaiduMap = mmap.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        //应聘者
        String longLat = KV.get(Define.CURR_LONGLAT_ADDRESS, "");
        if (!StringUtil.isEmpty(longLat)) {
            longitude = longLat.split(",")[0];
            latitude = longLat.split(",")[1];
        }
        updateMyLocation();

        tv_shishi.setVisibility(View.VISIBLE);

        // 构造定位数据
        MyLocationData locData = new MyLocationData.Builder()
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(0).latitude(Double.parseDouble(latitude))
                .longitude(Double.parseDouble(longitude)).build();
        // 设置定位数据
        mBaiduMap.setMyLocationData(locData);

        LatLng ll = new LatLng(Double.parseDouble(latitude),
                Double.parseDouble(longitude));
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(18.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        tv_position.setVisibility(View.GONE);

        tv_shishi.setText("导航到这里去");
        tv_shishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapNaviUtils.showDaoHangDialog(ShowMapYingPinActivity.this, Double.parseDouble(longitude), Double.parseDouble(latitude), "");
            }
        });

        tv_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateCurrMyLocation();

            }
        });
    }

    private View view, view1;

    /**
     * 显示两个点
     *
     * @param selfLatlng
     * @param otherLatlng
     * @param otherUrl
     */
    private void showTwoPoint(final LatLng selfLatlng, final LatLng otherLatlng, String otherUrl) {
        mBaiduMap.clear();
        //自己的位置
        if (view == null) {
            view = View.inflate(ShowMapYingPinActivity.this, R.layout.layout_qipao, null);
            UserInfo userInfo = KV.get(Define.USER_INFO);
            String userUrl;
            if (userInfo != null) {
                userUrl = userInfo.getUser_avatar_url();
                if (Util.isOnMainThread()) {
                    Glide.with(this).asBitmap().load(userUrl).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            ((ImageView) view.findViewById(R.id.iv_avatar)).setImageBitmap(resource);

                            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(view);

                            OverlayOptions options = new MarkerOptions()
                                    .position(selfLatlng)//设置位置
                                    .icon(bitmap)//设置图标样式
                                    .zIndex(9) // 设置marker所在层级
                                    .draggable(true); // 设置手势拖拽;

                            mBaiduMap.addOverlay(options);
                        }
                    }); //方法中设置asBitmap可以设置回调类型
                }
            }
        } else {
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(view);

            OverlayOptions options = new MarkerOptions()
                    .position(selfLatlng)//设置位置
                    .icon(bitmap)//设置图标样式
                    .zIndex(9) // 设置marker所在层级
                    .draggable(true); // 设置手势拖拽;

            mBaiduMap.addOverlay(options);
        }

        if (view1 == null)
            view1 = View.inflate(ShowMapYingPinActivity.this, R.layout.layout_qipao, null);
        if (Util.isOnMainThread()) {
            GlideUtil.show(this, otherUrl, new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable resource, Transition transition) {
                    ((ImageView) view1.findViewById(R.id.iv_avatar)).setImageDrawable(resource);

                    BitmapDescriptor bitmap1 = BitmapDescriptorFactory.fromView(view1);

                    OverlayOptions options1 = new MarkerOptions()
                            .position(otherLatlng)//设置位置
                            .icon(bitmap1)//设置图标样式
                            .zIndex(9) // 设置marker所在层级
                            .draggable(true); // 设置手势拖拽;

                    //添加marker
                    mBaiduMap.addOverlay(options1);
                }
            });
        }
        runnable1 = new Runnable() {
            @Override
            public void run() {
                List<LatLng> pois = new ArrayList<>();//可以将多点放到list集合中
                pois.add(selfLatlng);//起点坐标
                pois.add(otherLatlng);//终点坐标

                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (LatLng p : pois) {
                    builder = builder.include(p);
                }
                LatLngBounds latlngBounds = builder.build();
                if (mmap != null) {
                    MapStatusUpdate u = MapStatusUpdateFactory.newLatLngBounds(latlngBounds, mmap.getWidth(), mmap.getHeight());
                    mBaiduMap.animateMapStatus(u);

                    runnable2 = new Runnable() {
                        @Override
                        public void run() { //将起点终点显示到可见范围
                            double lat = selfLatlng.latitude < otherLatlng.latitude ? selfLatlng.latitude : otherLatlng.latitude;
                            double lng = (selfLatlng.longitude + otherLatlng.longitude) / 2;
                            LatLng center = new LatLng(lat, lng);
                            if (mBaiduMap != null){
                                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLngZoom(center, mBaiduMap.getMapStatus().zoom));
                            }
                        }
                    };
                    mHandler.postDelayed(runnable2, 500);
                }
            }
        };
        mHandler.postDelayed(runnable1, 1000);
    }

    private Runnable runnable1;
    private Runnable runnable2;

    private Subscription subscribe = null;


    private Subscription subscribeUpload = null;
    private String userAvatar;

    private void updateCurrMyLocation() {
        LatLng otherLatlng = null;
        if (StringUtil.isAllNotEmpty(zp_latitude, zp_longitude)) {
            otherLatlng = new LatLng(Double.parseDouble(zp_latitude), Double.parseDouble(zp_longitude));
        } else {
            return;
        }

        //获取到他人的定位信息了
        LatLng selfLatlng = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        showTwoPoint(selfLatlng, otherLatlng, userAvatar);
    }

    //上传我的位置信息
    private void updateMyLocation() {
        subscribeUpload = Observable.timer(0 * 1000, 30 * 1000, TimeUnit.MILLISECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(Long aLong) {
                String longLat = KV.get(Define.CURR_LONGLAT_ADDRESS, "");
                if (!StringUtil.isEmpty(longLat)) {
                    longitude = longLat.split(",")[0];
                    latitude = longLat.split(",")[1];
                }

                new NetUtil().setUrl(APIS.URL_TALENT_SEND_POSITION)
                        .addParams("require_id", require_id + "")
                        .addParams("latitude", latitude + "")
                        .addParams("longitude", longitude + "")
                        .addParams("type", module_type + "")
                        .setCallBackData(new BaseNewNetModelimpl<OtherLocationModel>() {
                            @Override
                            protected void onFail(int type, String msg) {
                                showTips(msg);
                            }

                            @Override
                            protected void onSuccess(int code, BaseResultInfo<OtherLocationModel> respnse, String source) {
                                //获取当前位置
                                if (respnse != null) {

                                    OtherLocationModel data = respnse.getData();
                                    if (data != null) {
                                        userAvatar = data.getUser_avatar_url();
                                    }
                                    updateCurrMyLocation();
                                }
                            }

                            @Override
                            protected Type getType() {
                                return new TypeToken<BaseResultInfo<OtherLocationModel>>() {
                                }.getType();
                            }
                        }).buildPost();
            }
        });
    }

    private android.os.Handler mHandler = new android.os.Handler();

    @Override
    protected void refreshPageData() {

    }

    @Override
    protected void onPause() {
        mmap.onPause();
        if (subscribe != null)
            subscribe.unsubscribe();
        if (subscribeUpload != null)
            subscribeUpload.unsubscribe();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mmap.onResume();
        //重新请求数据
        //应聘者
        updateMyLocation();

        tv_shishi.setVisibility(View.VISIBLE);

        super.onResume();
    }


    @Override
    protected void onDestroy() {
        mHandler.removeCallbacks(runnable1);
        mHandler.removeCallbacks(runnable2);
        if (subscribe != null)
            subscribe.unsubscribe();
        if (subscribeUpload != null)
            subscribeUpload.unsubscribe();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mmap.onDestroy();
        mmap = null;
        if (Util.isOnMainThread()){
            Glide.with(getApplicationContext()).pauseRequests();
        }
        super.onDestroy();
    }
}
