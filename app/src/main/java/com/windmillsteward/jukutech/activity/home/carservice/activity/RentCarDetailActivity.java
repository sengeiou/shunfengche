package com.windmillsteward.jukutech.activity.home.carservice.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.carservice.presenter.RentCarDetailActivityPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.RentCarDetailBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateTimeFormatUtil;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.GlideUtil;

/**
 * 描述：
 * 时间：2018/4/3/003
 * 作者：xjh
 */
public class RentCarDetailActivity extends BaseActivity implements  RentCarDetailActivityView, View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_go_off;
    private TextView tv_vehicle_module_name;
    private TextView tve_unoccupied_num;
    private LinearLayout linear_type;
    private TextView tv_departure_place_name;
    private TextView tv_departure_place_address;
    private TextView tv_destination_place_name;
    private TextView tv_destination_place_address;
    private TextView tv_price;
    private TextView tv_pass_place_one;
    private TextView tv_pass_place_two;
    private TextView tv_remark;
    private TextView tv_update_time;
    private TextView tv_view_num;
    private TextView tv_hosted_id;
    private CircleImageView civ_header;
    private TextView tv_username;
    private ImageView iv_collection;
    private TextView tv_collection;
    private LinearLayout linear_collection;
    private LinearLayout linear_call;
    private LinearLayout linear_car_owner;

    private RentCarDetailActivityPresenter presenter;
    private int car_rent_id;
    private RentCarDetailBean detailBean;
    private boolean collect_type;
    private boolean isCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentcar_detail);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            car_rent_id = extras.getInt(Define.INTENT_DATA);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        presenter = new RentCarDetailActivityPresenter(this);
        presenter.initData(getAccessToken(),car_rent_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            isCall = true;
//            presenter.initData(getAccessToken(),car_rent_id);
//        }
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("详情");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_go_off = (TextView) findViewById(R.id.tv_go_off);
        tv_vehicle_module_name = (TextView) findViewById(R.id.tv_vehicle_module_name);
        tve_unoccupied_num = (TextView) findViewById(R.id.tve_unoccupied_num);
        linear_type = (LinearLayout) findViewById(R.id.linear_type);
        tv_departure_place_name = (TextView) findViewById(R.id.tv_departure_place_name);
        tv_departure_place_address = (TextView) findViewById(R.id.tv_departure_place_address);
        tv_destination_place_name = (TextView) findViewById(R.id.tv_destination_place_name);
        tv_destination_place_address = (TextView) findViewById(R.id.tv_destination_place_address);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_pass_place_one = (TextView) findViewById(R.id.tv_pass_place_one);
        tv_pass_place_two = (TextView) findViewById(R.id.tv_pass_place_two);
        tv_remark = (TextView) findViewById(R.id.tv_remark);
        tv_update_time = (TextView) findViewById(R.id.tv_update_time);
        tv_view_num = (TextView) findViewById(R.id.tv_view_num);
        tv_hosted_id = (TextView) findViewById(R.id.tv_hosted_id);
        civ_header = (CircleImageView) findViewById(R.id.civ_header);
        tv_username = (TextView) findViewById(R.id.tv_username);
        iv_collection = (ImageView) findViewById(R.id.iv_collection);
        tv_collection = (TextView) findViewById(R.id.tv_collection);
        linear_collection = (LinearLayout) findViewById(R.id.linear_collection);
        linear_call = (LinearLayout) findViewById(R.id.linear_call);
        linear_car_owner = (LinearLayout) findViewById(R.id.linear_car_owner);

        linear_collection.setOnClickListener(this);
        linear_call.setOnClickListener(this);
    }

    @Override
    public void initDataSuccess(RentCarDetailBean bean) {
        detailBean = bean;
        int type = bean.getType();
        if (type==1) {  // 车主
            linear_car_owner.setVisibility(View.VISIBLE);
            tv_pass_place_one.setText(bean.getPass_place_one());
            tv_pass_place_two.setText(bean.getPass_place_two());
            tv_price.setText("￥"+bean.getPrice());
            tv_vehicle_module_name.setText(bean.getVehicle_module_name());
            tve_unoccupied_num.setText("空余"+bean.getUnoccupied_num()+"座位");
        } else if (type==2) {  // 乘客
            linear_car_owner.setVisibility(View.GONE);
            linear_type.setVisibility(View.GONE);
            tv_price.setVisibility(View.GONE);
        }
        tv_go_off.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getGo_off()),"yyyy-MM-dd HH:mm"));
        tv_departure_place_name.setText(bean.getDeparture_place_title());  // 出发地名
        tv_departure_place_address.setText(bean.getDeparture_place_name());  // 出发详细地主
        tv_destination_place_name.setText(bean.getDestination_place_title());
        tv_destination_place_address.setText(bean.getDestination_place_name());
        tv_remark.setText(bean.getRemark());
        tv_update_time.setText(DateTimeFormatUtil.dateTimeFormatString(bean.getUpdate_time()));
        tv_view_num.setText(String.valueOf(bean.getView_num()));
        tv_hosted_id.setText(bean.getHosting_sn());
        GlideUtil.show(this, bean.getUser_avatar_url(), civ_header, R.mipmap.icon_big_head_male,R.mipmap.icon_big_head_male);
        tv_username.setText(bean.getNickname());

        if (bean.getIs_collect()==1) {
            tv_collection.setText("已收藏");
            collect_type = false;
        } else {
            tv_collection.setText("收藏");
            collect_type = false;
        }
        if (isCall) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + detailBean.getContact_tel());
            intent.setData(uri);
            startActivity(intent);
            isCall = false;
        }
    }

    @Override
    public void collectSuccess() {
        tv_collection.setText("已收藏");
        collect_type = true;
    }

    @Override
    public void cancelCollectSuccess() {
        tv_collection.setText("收藏");
        collect_type = false;
    }

    @Override
    public void isChargeResult(ChargeResultBean bean) {
        if (bean.getIs_charge()==0) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + detailBean.getContact_tel());
            intent.setData(uri);
            startActivity(intent);
        } else {
            new AlertDialog(this).builder()
                    .setTitle("提示")
                    .setMsg("拨打电话需要支付费用，继续吗")
                    .setCancelable(true)
                    .setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    })
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int type = detailBean.getType();
                            if (type==1) { // 车主
//                                PayActivity.go(RentCarDetailActivity.this,106,car_rent_id);
                            } else if (type==2) { // 乘客
//                                PayActivity.go(RentCarDetailActivity.this,107,car_rent_id);
                            }
                        }
                    })
                    .show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_call:

                if (isLogin()) {
                    int type = detailBean.getType();
                    if (type==1) { // 车主
                        presenter.isCharge(getAccessToken(),106, car_rent_id);
                    } else if (type==2) { // 乘客
                        presenter.isCharge(getAccessToken(),107, car_rent_id);
                    }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.linear_collection:
                if (isLogin()) {
                    if (detailBean!=null) {
                        if (collect_type){
                            presenter.cancelCollect( car_rent_id,getAccessToken(),detailBean.getType());
                        } else {
                            presenter.collect( car_rent_id,getAccessToken(),detailBean.getType());
                        }
                    }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
        }
    }
}
