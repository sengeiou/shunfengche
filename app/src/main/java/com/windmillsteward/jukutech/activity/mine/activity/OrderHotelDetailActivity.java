package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.HotelAndHouseDetailActivity;
import com.windmillsteward.jukutech.activity.mine.presenter.OrderHotelDetailPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.OrderHotelDetailBean;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.utils.DateUtil;

import org.xutils.x;

import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.baidu.mapapi.BMapManager.getContext;

/**
 * 描述：
 * 时间：2018/3/2/002
 * 作者：xjh
 */
public class OrderHotelDetailActivity extends BaseActivity implements OrderHotelDetailView, View.OnClickListener {

    public static final String DETAIL_ID = "DETAIL_ID";
    public static final String POSITION = "POSITION";
    public static final int DELETE_CODE = 106;

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_status;
    private ImageView iv_qrcode;
    private TextView tv_time;
    private MapView mapView;
    private TextView tv_address;
    private TextView tv_title;
    private TextView tv_room;
    private TextView tv_start_time;
    private TextView tv_end_time;
    private LinearLayout linear_personals;
    private TextView tv_phone;
    private LinearLayout linear_room;
    private TextView tv_order;
    private TextView tv_discount;
    private TextView tv_money;
    private TextView tv_deposit;
    private TextView tv_total;
    private TextView tv_hosted_id;
    private TextView tv_cancel_order;
    private LinearLayout linear_cancel;

    private OrderHotelDetailPresenter presenter;
    private int order_id;
    private OrderHotelDetailBean bean;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhoteldetail);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            order_id = extras.getInt(DETAIL_ID);
            position = extras.getInt(POSITION);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();

        presenter = new OrderHotelDetailPresenter(this);
        presenter.initData(getAccessToken(),order_id);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("订单详情");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_status = (TextView) findViewById(R.id.tv_status);
        iv_qrcode = (ImageView) findViewById(R.id.iv_qrcode);
        tv_time = (TextView) findViewById(R.id.tv_time);
        mapView = (MapView) findViewById(R.id.mapView);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_room = (TextView) findViewById(R.id.tv_room);
        tv_start_time = (TextView) findViewById(R.id.tv_start_time);
        tv_end_time = (TextView) findViewById(R.id.tv_end_time);
        linear_personals = (LinearLayout) findViewById(R.id.linear_personals);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        linear_room = (LinearLayout) findViewById(R.id.linear_room);
        tv_order = (TextView) findViewById(R.id.tv_order);
        tv_discount = (TextView) findViewById(R.id.tv_discount);
        tv_money = (TextView) findViewById(R.id.tv_money);
        tv_deposit = (TextView) findViewById(R.id.tv_deposit);
        tv_total = (TextView) findViewById(R.id.tv_total);
        tv_hosted_id = (TextView) findViewById(R.id.tv_hosted_id);
        tv_cancel_order = (TextView) findViewById(R.id.tv_cancel_order);
        linear_cancel = (LinearLayout) findViewById(R.id.linear_cancel);

        tv_title.setOnClickListener(this);
    }

    @Override
    public void initDataSuccess(OrderHotelDetailBean bean) {
        this.bean = bean;
        tv_status.setText(bean.getStatus_name());
        x.image().bind(iv_qrcode,bean.getConfirm_qr());
        tv_time.setText(bean.getConfirm_code());
        tv_address.setText(bean.getArea_name());
        tv_title.setText(bean.getHotel_name());
        tv_room.setText(bean.getRoom_type_name());
        tv_start_time.setText(bean.getPlaned_start_time());
        String text = bean.getPlaned_end_time()+" 共" + bean.getNight_num() + "晚";
        tv_end_time.setText(text);
        LayoutInflater inflater = LayoutInflater.from(this);
        List<String> contact_person_list = bean.getContact_person_list();
        if (contact_person_list!=null) {
            for (String s : contact_person_list) {
                TextView textView = (TextView) inflater.inflate(R.layout.view_orderdetail_text, linear_personals, false);
                textView.setText(s);
                linear_personals.addView(textView);
            }
        }
        tv_phone.setText(bean.getContact_tel());
        List<String> room_name_list = bean.getRoom_name_list();
        if (room_name_list!=null) {
            for (String s : room_name_list) {
                TextView textView = (TextView) inflater.inflate(R.layout.view_orderdetail_text, linear_personals, false);
                textView.setText(s);
                linear_room.addView(textView);
            }
        }
        tv_order.setText(bean.getOrder_sn());
        tv_discount.setText("￥"+bean.getTotal_coupon_fee());
        tv_money.setText("￥"+bean.getTotal_discount_fee());
        tv_deposit.setText("￥"+bean.getPrepay_fee());
        tv_total.setText("￥"+bean.getTotal_pay_fee());
        tv_hosted_id.setText(bean.getHosting_sn());
        int order_detail_status = bean.getOrder_detail_status();
        if (order_detail_status==0) {  // 待入住
            linear_cancel.setVisibility(View.VISIBLE);
            tv_cancel_order.setOnClickListener(this);
            tv_cancel_order.setText("取消订单");
        } else if (order_detail_status==1){  // 已入住
            linear_cancel.setVisibility(View.GONE);
        } else if (order_detail_status==2) {  // 已完成
            linear_cancel.setVisibility(View.VISIBLE);
            tv_cancel_order.setText("删除");
            tv_cancel_order.setOnClickListener(this);
        } else if (order_detail_status==3) {  // 已取消
            linear_cancel.setVisibility(View.VISIBLE);
            tv_cancel_order.setText("删除");
        }

        initMap(bean.getLatitude(),bean.getLongitude());
        tv_address.setOnClickListener(this);
    }

    @Override
    public void cancelHotelOrderSuccess() {
        if (bean!=null) {
            bean.setOrder_detail_status(3);
            initDataSuccess(bean);
        }
    }

    @Override
    public void deleteHotelOrderSuccess() {
        Intent data = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, position);
        data.putExtras(bundle);
        setResult(DELETE_CODE, data);
        finish();
    }


    private void initMap(String latitude, String longitude) {
        //定义Maker坐标点
        LatLng point = new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_map_marker);

        MarkerOptions ooA = new MarkerOptions().position(point).icon(bitmap)
                .zIndex(5).draggable(false);

        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        BaiduMap map = mapView.getMap();

        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
        map.setMapStatus(msu);

        Marker marker = (Marker) (map.addOverlay(ooA));

        MapStatusUpdate u = MapStatusUpdateFactory
                .newLatLng(point);
        map.setMapStatus(u);

        map.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (bean!=null) {

                    new AlertDialog(OrderHotelDetailActivity.this).builder()
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
                }
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
                    +bean.getLatitude()+"&dlon="+bean.getLongitude()+"&dname="+bean.getHotel_name()+"&dev=0&m=0&t=1");
            if(isInstallByread("com.autonavi.minimap")){
                startActivity(intent);
            }else if (isInstallByread("com.baidu.BaiduMap")) {
                Intent intent1 = Intent.getIntent("intent://map/navi?location="+bean.getLatitude()+","+bean.getLongitude() +
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mapView.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel_order:
                if (bean!=null) {
                    int order_detail_status = bean.getOrder_detail_status();
                    if (order_detail_status==0) { // 取消订单
                        new AlertDialog(this).builder()
                                .setTitle("提示")
                                .setMsg("确定取消订单吗? \n 支付费用将返回原账户")
                                .setCancelable(true)
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                    }
                                })
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        presenter.cancelOrder(getAccessToken(),order_id);
                                    }
                                })
                                .show();
                    } else if (order_detail_status==3 || order_detail_status==2) {
                        new AlertDialog(this).builder()
                                .setTitle("提示")
                                .setMsg("确定删除订单吗")
                                .setCancelable(true)
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                    }
                                })
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        presenter.deleteOrder(getAccessToken(),order_id);
                                    }
                                })
                                .show();
                    }
                }
                break;
            case R.id.tv_address:
                if (bean!=null) {
                    new AlertDialog(OrderHotelDetailActivity.this).builder()
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
                }
                break;
            case R.id.tv_title:
                if (bean!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(HotelAndHouseDetailActivity.HOTEL_ID,bean.getHotel_id());
                    startAtvDonFinishForResult(HotelAndHouseDetailActivity.class,100,bundle);
                }
                break;
        }
    }
}
