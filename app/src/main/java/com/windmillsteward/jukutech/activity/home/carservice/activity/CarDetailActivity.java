package com.windmillsteward.jukutech.activity.home.carservice.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.carservice.presenter.CarDetailActivityPresenter;
import com.windmillsteward.jukutech.activity.home.family.activity.PhotoViewActivity;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.CarDetailBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.ExpandTextView;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：卖车详情
 * 时间：2018/3/29/029
 * 作者：xjh
 */
public class CarDetailActivity extends BaseActivity implements CarDetailActivityView, View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private FlyBanner flyBanner;
    private TextView tv_title;
    private TextView tv_price;
    private TextView tv_publish_time;
    private TextView tv_read_times;
    private TextView tv_license_time;
    private TextView tv_mileage;
    private ExpandTextView expand_desc;
    private ExpandTextView expand_detail_info;
    private TextView tv_hosted_id;
    private CircleImageView civ_header;
    private TextView tv_username;
    private ImageView iv_collection;
    private TextView tv_collection;
    private LinearLayout linear_collection;
    private LinearLayout linear_call;

    private CarDetailActivityPresenter presenter;
    private CarDetailBean bean;
    private int car_id;
    private boolean collect_type;
    private boolean isCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardtail);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            car_id = extras.getInt(Define.INTENT_DATA);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        initFlyBanner();
        presenter = new CarDetailActivityPresenter(this);
        presenter.initData(getAccessToken(), car_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            isCall = true;
//            presenter.initData(getAccessToken(), car_id);
//        }
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("详情");
    }

    private void initFlyBanner() {
        ViewGroup.LayoutParams layoutParams = flyBanner.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH(this, GraphicUtil.TAG_WIDTH);
        layoutParams.width = screenWH - GraphicUtil.dp2px(this, 0);
        layoutParams.height = 575 * layoutParams.width / 1080;
        flyBanner.setLayoutParams(layoutParams);

        flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (bean != null) {
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList(PhotoViewActivity.PIC_URLS, (ArrayList<String>) bean.getPic_urls());
                    bundle.putInt(PhotoViewActivity.CURR_POSITION, position);
                    startAtvDonFinish(PhotoViewActivity.class, bundle);
                }
            }
        });
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        flyBanner = (FlyBanner) findViewById(R.id.flyBanner);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_publish_time = (TextView) findViewById(R.id.tv_publish_time);
        tv_read_times = (TextView) findViewById(R.id.tv_read_times);
        tv_license_time = (TextView) findViewById(R.id.tv_license_time);
        tv_mileage = (TextView) findViewById(R.id.tv_mileage);
        expand_desc = (ExpandTextView) findViewById(R.id.expand_desc);
        expand_detail_info = (ExpandTextView) findViewById(R.id.expand_detail_info);
        tv_hosted_id = (TextView) findViewById(R.id.tv_hosted_id);
        civ_header = (CircleImageView) findViewById(R.id.civ_header);
        tv_username = (TextView) findViewById(R.id.tv_username);
        iv_collection = (ImageView) findViewById(R.id.iv_collection);
        tv_collection = (TextView) findViewById(R.id.tv_collection);
        linear_collection = (LinearLayout) findViewById(R.id.linear_collection);
        linear_call = (LinearLayout) findViewById(R.id.linear_call);
        linear_call.setOnClickListener(this);
        linear_collection.setOnClickListener(this);
    }

    @Override
    public void initDataSuccess(CarDetailBean bean) {
        this.bean = bean;
        List<String> pic_urls = bean.getPic_urls();
        if (pic_urls != null && pic_urls.size() > 0) {
            flyBanner.setImagesUrl(pic_urls);
        }
        tv_title.setText(bean.getName());
        tv_price.setText(bean.getPrice());
        tv_publish_time.setText("发布:" + DateUtil.StampTimeToDate(String.valueOf(bean.getUpdate_time()), "yyyy-MM-dd"));
        tv_read_times.setText("浏览:" + bean.getView_num() + "次");
        tv_license_time.setText(bean.getLicense_time());
        tv_mileage.setText(bean.getMileage() + "万公里");
        expand_desc.setContent("品牌：" + bean.getBrand_name() + "\n\n" + "车系列：" + bean.getSeries_name() + "\n\n" + "车型：" + bean.getVehicle_module_name()
                + "\n\n" + "下次验车：" + bean.getNext_validate_time() + "\n\n" + "交强险到期：" + bean.getCompulsory_insurance_time() + "\n\n" + "商业险到期：" + bean.getCommercial_insurance_time()
                + "\n\n" + "过户费：" + bean.getTransfer_fee_name() + "\n\n" + "车辆颜色：" + bean.getCar_color() + "\n\n" + "联系人：" + bean.getContact_person() + "\n\n" + "交易地点：" + bean.getDeal_area_name());
        expand_detail_info.setContent(bean.getDetails());
        tv_hosted_id.setText(bean.getHosting_sn());
        GlideUtil.show(this,bean.getUser_avatar_url(),civ_header,R.mipmap.icon_big_head_male,R.mipmap.icon_big_head_male);
        tv_username.setText(bean.getNickname());
        if (bean.getIs_collect() == 0) {
            tv_collection.setText("收藏");
            collect_type = false;
        } else {
            tv_collection.setText("已收藏");
            collect_type = true;
        }
        if (isCall) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + bean.getContact_tel());
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
        if (bean.getIs_charge() == 0) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri uri = Uri.parse("tel:" + this.bean.getContact_tel());
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
//                            PayActivity.go(CarDetailActivity.this, 104, car_id);
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
                    presenter.isCharge(getAccessToken(), 104, car_id);
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.linear_collection:
                if (isLogin()) {
                    if (bean != null) {
                        if (collect_type) {
                            presenter.cancelCollect(car_id, getAccessToken());
                        } else {
                            presenter.collect(car_id, getAccessToken());
                        }
                    }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
        }
    }
}
