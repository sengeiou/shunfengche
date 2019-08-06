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
import com.windmillsteward.jukutech.activity.home.carservice.presenter.BuyCarDetailActivityPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.BuyCarDetailBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.customview.ExpandTextView;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.GlideUtil;

/**
 *
 * Created by Administrator on 2018/4/2 0002.
 */

public class BuyCarDetailActivity extends BaseActivity implements BuyCarDetailActivityView, View.OnClickListener {

    public static final String DETAIL_ID = "DETAIL_ID";

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_title;
    private TextView tv_publish_time;
    private TextView tv_read_times;
    private TextView tv_contact;
    private TextView tv_price;
    private ExpandTextView et_detail;
    private TextView tv_hosted_id;
    private CircleImageView civ_header;
    private TextView tv_username;
    private ImageView iv_collection;
    private TextView tv_collection;
    private LinearLayout linear_collection;
    private LinearLayout linear_call;

    private BuyCarDetailActivityPresenter presenter;
    private BuyCarDetailBean detailBean;
    private int buy_car_id;
    private boolean collect_type;
    private boolean isCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buycar_detail);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            buy_car_id = extras.getInt(DETAIL_ID);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();

        presenter = new BuyCarDetailActivityPresenter(this);
        presenter.initData(getAccessToken(),buy_car_id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            isCall = false;
//            presenter.initData(getAccessToken(),buy_car_id);
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
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_publish_time = (TextView) findViewById(R.id.tv_publish_time);
        tv_read_times = (TextView) findViewById(R.id.tv_read_times);
        tv_contact = (TextView) findViewById(R.id.tv_contact);
        tv_price = (TextView) findViewById(R.id.tv_price);
        et_detail = (ExpandTextView) findViewById(R.id.et_detail);
        tv_hosted_id = (TextView) findViewById(R.id.tv_hosted_id);
        civ_header = (CircleImageView) findViewById(R.id.civ_header);
        tv_username = (TextView) findViewById(R.id.tv_username);
        iv_collection = (ImageView) findViewById(R.id.iv_collection);
        tv_collection = (TextView) findViewById(R.id.tv_collection);
        linear_collection = (LinearLayout) findViewById(R.id.linear_collection);
        linear_call = (LinearLayout) findViewById(R.id.linear_call);

        linear_collection.setOnClickListener(this);
        linear_call.setOnClickListener(this);
    }

    @Override
    public void initDataSuccess(BuyCarDetailBean bean) {
        detailBean = bean;
        tv_title.setText(bean.getTitle());
        tv_publish_time.setText("发布："+ DateUtil.StampTimeToDate(String.valueOf(bean.getUpdate_time()),"yyyy-MM-dd"));
        tv_read_times.setText("浏览：" + bean.getView_num()+"次");
        tv_contact.setText("联系人：" + bean.getContact_person());
        tv_price.setText("预算：" + bean.getPrice()+"万");
        et_detail.setContent(bean.getDetails());
        tv_hosted_id.setText(bean.getHosting_sn());
        GlideUtil.show(this,bean.getUser_avatar_url(),civ_header,R.mipmap.icon_big_head_male,R.mipmap.icon_big_head_male);
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
                    presenter.isCharge(getAccessToken(),105, buy_car_id);
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.linear_collection:
                if (isLogin()) {
                    if (detailBean!=null) {
                        if (collect_type){
                            presenter.cancelCollect( buy_car_id,getAccessToken());
                        } else {
                            presenter.collect( buy_car_id,getAccessToken());
                        }
                    }
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
        }
    }
}
