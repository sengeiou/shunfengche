package com.windmillsteward.jukutech.activity.home.carservice.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.carservice.fragment.BuyCarListFragment;
import com.windmillsteward.jukutech.activity.home.carservice.fragment.RentCarListFragment;
import com.windmillsteward.jukutech.activity.home.carservice.fragment.UsedCarHomeFragment;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.interfaces.Define;

/**
 * 描述：
 * 时间：2018/3/20/020
 * 作者：xjh
 */
public class CarServiceActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout fl_content;
    private ImageView iv_used_car;
    private TextView tv_used_car;
    private ImageView iv_buy_car;
    private TextView tv_buy_car;
    private ImageView iv_manager_car;
    private TextView tv_manager_car;
    private LinearLayout linear_used_car;
    private LinearLayout linear_buy_car;
    private LinearLayout linear_manager_car;

    private UsedCarHomeFragment usedCarHomeFragment;
    private BuyCarListFragment buyCarListFragment;
    private RentCarListFragment rentCarListFragment;
    private int curr_fragment;
    private int curr_class_id=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_service);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            curr_fragment = extras.getInt(Define.INTENT_DATA);
            curr_class_id = extras.getInt(Define.INTENT_DATA_TWO,1);
        }
        initView();
        initFragment();
        initBottomBar();
    }

    private void initFragment() {
        setParamInt(R.id.fl_content);
        usedCarHomeFragment = UsedCarHomeFragment.getInstance();
        buyCarListFragment = BuyCarListFragment.getInstance("");
        rentCarListFragment = RentCarListFragment.getInstance(curr_class_id);
    }

    private void initBottomBar() {
        setSelect(curr_fragment);
    }

    private void initView() {
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        iv_used_car = (ImageView) findViewById(R.id.iv_used_car);
        tv_used_car = (TextView) findViewById(R.id.tv_used_car);
        iv_buy_car = (ImageView) findViewById(R.id.iv_buy_car);
        tv_buy_car = (TextView) findViewById(R.id.tv_buy_car);
        iv_manager_car = (ImageView) findViewById(R.id.iv_manager_car);
        tv_manager_car = (TextView) findViewById(R.id.tv_manager_car);
        linear_used_car = (LinearLayout) findViewById(R.id.linear_used_car);
        linear_buy_car = (LinearLayout) findViewById(R.id.linear_buy_car);
        linear_manager_car = (LinearLayout) findViewById(R.id.linear_manager_car);
        linear_used_car.setOnClickListener(this);
        linear_buy_car.setOnClickListener(this);
        linear_manager_car.setOnClickListener(this);
    }

    private void setSelect(int select) {
        if (select == 0) {
            iv_used_car.setImageResource(R.mipmap.icon_carlist);
            tv_used_car.setTextColor(ContextCompat.getColor(this, R.color.color_23abac));
            iv_buy_car.setImageResource(R.mipmap.icon_buycar_n);
            tv_buy_car.setTextColor(ContextCompat.getColor(this, R.color.color_text_78));
            iv_manager_car.setImageResource(R.mipmap.icon_carental_n);
            tv_manager_car.setTextColor(ContextCompat.getColor(this, R.color.color_text_78));
            startFragment(null,usedCarHomeFragment);
        } else if (select == 1) {
            iv_used_car.setImageResource(R.mipmap.icon_carlist_n);
            tv_used_car.setTextColor(ContextCompat.getColor(this, R.color.color_text_78));
            iv_buy_car.setImageResource(R.mipmap.icon_buycar);
            tv_buy_car.setTextColor(ContextCompat.getColor(this, R.color.color_23abac));
            iv_manager_car.setImageResource(R.mipmap.icon_carental_n);
            tv_manager_car.setTextColor(ContextCompat.getColor(this, R.color.color_text_78));
            startFragment(null,buyCarListFragment);
        } else if (select == 2) {
            iv_used_car.setImageResource(R.mipmap.icon_carlist_n);
            tv_used_car.setTextColor(ContextCompat.getColor(this, R.color.color_text_78));
            iv_buy_car.setImageResource(R.mipmap.icon_buycar_n);
            tv_buy_car.setTextColor(ContextCompat.getColor(this, R.color.color_text_78));
            iv_manager_car.setImageResource(R.mipmap.icon_carental);
            tv_manager_car.setTextColor(ContextCompat.getColor(this, R.color.color_23abac));
            startFragment(null,rentCarListFragment);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_used_car:
                if (curr_fragment==0) {
                    return;
                }
                curr_fragment = 0;
                setSelect(curr_fragment);
                break;
            case R.id.linear_buy_car:
                if (curr_fragment==1) {
                    return;
                }
                curr_fragment = 1;
                setSelect(curr_fragment);
                break;
            case R.id.linear_manager_car:
                if (curr_fragment==2) {
                    return;
                }
                curr_fragment = 2;
                setSelect(curr_fragment);
                break;
        }
    }
}
