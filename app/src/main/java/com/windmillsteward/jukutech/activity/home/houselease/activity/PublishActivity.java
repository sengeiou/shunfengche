package com.windmillsteward.jukutech.activity.home.houselease.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseActivity;

/**
 * 描述：发布需求
 * 时间：2018/1/30/030
 * 作者：xjh
 */
public class PublishActivity extends BaseActivity implements View.OnClickListener {

    public static final String TYPE = "TYPE";

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private LinearLayout linear_sell;
    private LinearLayout linear_buy;
    private int type;
    private TextView tv_publish_out;
    private TextView tv_publish_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getInt(TYPE);
        }
        initView();
        initToolbar();
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("选择发布类别");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        linear_sell = (LinearLayout) findViewById(R.id.linear_sell);
        linear_buy = (LinearLayout) findViewById(R.id.linear_buy);
        tv_publish_out = (TextView) findViewById(R.id.tv_publish_out);
        tv_publish_in = (TextView) findViewById(R.id.tv_publish_in);

        if (type==0) {
            tv_publish_out.setText("我要卖房");
            tv_publish_in.setText("我要买房");
        } else {
            tv_publish_out.setText("我要出租");
            tv_publish_in.setText("我要租房");
        }

        linear_sell.setOnClickListener(this);
        linear_buy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_sell:
                if (type == 0) {
                    startAtvDonFinish(PublishSellHouseActivity.class);
                } else {
                    startAtvDonFinish(PublishRentOutActivity.class);
                }
                break;
            case R.id.linear_buy:
                if (type == 0) {
                    startAtvDonFinish(PublishBuyHouseActivity.class);
                } else {
                    startAtvDonFinish(PublishRentInActivity.class);
                }
                break;
        }
    }
}
