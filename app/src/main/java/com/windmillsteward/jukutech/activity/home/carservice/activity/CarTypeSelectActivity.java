package com.windmillsteward.jukutech.activity.home.carservice.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseActivity;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class CarTypeSelectActivity extends BaseActivity implements View.OnClickListener {

    public static final String TYPE = "TYPE";

    private ImageView mToolbarIvBack;
    private TextView mToolbarIvTitle;
    private ImageView mToolbarIvRight;
    private TextView mToolbarTvRight;
    private TextView mTvClass1;
    private LinearLayout mLinearClass1;
    private TextView mTvClass2;
    private LinearLayout mLinearClass2;

    private int type;//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartype_select);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            type = extras.getInt(TYPE);
        }
        initView();
        initToolbar();
    }

    private void initToolbar() {
        handleBackEvent(mToolbarIvBack);
        mToolbarIvTitle.setText("选择发布类别");
    }

    private void initView() {
        mToolbarIvBack = (ImageView) findViewById(R.id.toolbar_iv_back);
        mToolbarIvTitle = (TextView) findViewById(R.id.toolbar_iv_title);
        mToolbarIvRight = (ImageView) findViewById(R.id.toolbar_iv_right);
        mToolbarTvRight = (TextView) findViewById(R.id.toolbar_tv_right);
        mTvClass1 = (TextView) findViewById(R.id.tv_class1);
        mLinearClass1 = (LinearLayout) findViewById(R.id.linear_class1);
        mTvClass2 = (TextView) findViewById(R.id.tv_class2);
        mLinearClass2 = (LinearLayout) findViewById(R.id.linear_class2);

        if (type==1) {
            mTvClass1.setText("我要卖车");
            mTvClass2.setText("我要买车");
        } else if (type==2){
            mTvClass1.setText("我是车主");
            mTvClass2.setText("我是乘客");
        }

        mLinearClass1.setOnClickListener(this);
        mLinearClass2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_class1:
                if (type==1) {
                    startAtvAndFinish(PublishSellCarActivity.class);
                } else if (type==2){
                    startAtvAndFinish(PublishCarOwnerActivity.class);
                }
                break;
            case R.id.linear_class2:
                if (type==1) {
                    startAtvAndFinish(PublishBuyCarActivity.class);
                } else {
                    startAtvAndFinish(PublishCarPassengerActivity.class);
                }
                break;
        }
    }
}
