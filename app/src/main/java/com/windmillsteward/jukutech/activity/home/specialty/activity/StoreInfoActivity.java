package com.windmillsteward.jukutech.activity.home.specialty.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.StoreInfoBean;
import com.windmillsteward.jukutech.interfaces.Define;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class StoreInfoActivity extends BaseActivity {


    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private ImageView iv_store_thumbnail;
    private TextView tv_store_name;
    private LinearLayout linear_header;
    private TextView tv_address;
    private TextView tv_phone;

    private StoreInfoBean infoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_info);
        initView();
        initToolbar();
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            infoBean = (StoreInfoBean) extras.getSerializable(Define.INTENT_DATA);
            initData();
        } else {
            finish();
        }
    }

    private void initData() {
        Glide.with(this).load(infoBean.getStore_thumbnail()).into(iv_store_thumbnail);
        tv_store_name.setText(infoBean.getStore_name());
        tv_address.setText(infoBean.getAddress());
        tv_phone.setText(infoBean.getService_tel());
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("店铺信息");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        iv_store_thumbnail = (ImageView) findViewById(R.id.iv_store_thumbnail);
        tv_store_name = (TextView) findViewById(R.id.tv_store_name);
        linear_header = (LinearLayout) findViewById(R.id.linear_header);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
    }
}
