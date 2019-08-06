package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.stayandtravel.fragment.HotelHomeFragment;
import com.windmillsteward.jukutech.base.BaseActivity;

/**
 * 描述：
 * 时间：2018/1/27/027
 * 作者：xjh
 */
public class HotelAndHouseHomeActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG_HOTEL = "TAG_HOTEL";
    private static final String TAG_HOUSE = "TAG_HOUSE";
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_hotel;
    private View view_hotel;
    private FrameLayout fl_hotel;
    private TextView tv_house;
    private View view_house;
    private FrameLayout fl_house;
    private FrameLayout fl_content;
    private FragmentManager fragmentManager;
    private HotelHomeFragment hotelHomeFragment,houseHomeFragment;
    private Fragment currFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotelandhouse);
        initView();

        initToolbar();
        initTableLayout();
        initFragment();
    }

    private void initTableLayout() {
        tv_hotel.setTextColor(ContextCompat.getColor(this,R.color.color_them));
        view_hotel.setVisibility(View.VISIBLE);
        tv_house.setTextColor(ContextCompat.getColor(this,R.color.color_text_78));
        view_house.setVisibility(View.INVISIBLE);
        fl_hotel.setOnClickListener(this);
        fl_house.setOnClickListener(this);
    }

    private void initFragment() {
        hotelHomeFragment = HotelHomeFragment.getInstance(1);
        houseHomeFragment = HotelHomeFragment.getInstance(2);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_content, hotelHomeFragment,TAG_HOTEL);
        transaction.commit();

        currFragment = hotelHomeFragment;
    }

    private void select(Fragment fragment) {
        if (fragment.equals(currFragment)) {
            return;
        }
        if (!fragment.isAdded()) {
            fragmentManager.beginTransaction().add(R.id.fl_content,fragment).hide(currFragment).show(fragment).commitNow();
        } else {
            fragmentManager.beginTransaction().hide(currFragment).show(fragment).commitNow();
        }
        currFragment = fragment;
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("酒店");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_hotel:
                select(hotelHomeFragment);

                tv_hotel.setTextColor(ContextCompat.getColor(this,R.color.color_them));
                view_hotel.setVisibility(View.VISIBLE);
                tv_house.setTextColor(ContextCompat.getColor(this,R.color.color_text_78));
                view_house.setVisibility(View.INVISIBLE);
                break;
            case R.id.fl_house:
                select(houseHomeFragment);

                tv_hotel.setTextColor(ContextCompat.getColor(this,R.color.color_text_78));
                view_hotel.setVisibility(View.INVISIBLE);
                tv_house.setTextColor(ContextCompat.getColor(this,R.color.color_them));
                view_house.setVisibility(View.VISIBLE);
                break;
        }
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_hotel = (TextView) findViewById(R.id.tv_hotel);
        view_hotel = (View) findViewById(R.id.view_hotel);
        fl_hotel = (FrameLayout) findViewById(R.id.fl_hotel);
        tv_house = (TextView) findViewById(R.id.tv_house);
        view_house = (View) findViewById(R.id.view_house);
        fl_house = (FrameLayout) findViewById(R.id.fl_house);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
    }
}
