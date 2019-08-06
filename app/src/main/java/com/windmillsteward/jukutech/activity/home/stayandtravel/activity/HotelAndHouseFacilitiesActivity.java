package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.stayandtravel.adapter.FacilitiesViewPagerAdapter;
import com.windmillsteward.jukutech.activity.home.stayandtravel.fragment.DetailFragment;
import com.windmillsteward.jukutech.activity.home.stayandtravel.fragment.FactilitiesFragment;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.HotelAndHouseDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：酒店和房源的设备详情
 * 时间：2018/1/28/028
 * 作者：xjh
 */
public class HotelAndHouseFacilitiesActivity extends BaseActivity implements View.OnClickListener {

    public static final String DETAIL = "DETAIL";
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_detail;
    private View view_detail;
    private FrameLayout fl_detail;
    private TextView tv_facilities;
    private View view_facilities;
    private FrameLayout fl_facilities;
    private ViewPager viewPager;
    private HotelAndHouseDetailBean bean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotelandhouse_facilities);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            bean = (HotelAndHouseDetailBean) extras.getSerializable(DETAIL);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        initTable();
        initViewPager();
    }

    private void initTable() {
        tv_detail.setTextColor(ContextCompat.getColor(this,R.color.color_them));
        view_detail.setVisibility(View.VISIBLE);
        tv_facilities.setTextColor(ContextCompat.getColor(this,R.color.color_text_999));
        view_facilities.setVisibility(View.INVISIBLE);

        fl_detail.setOnClickListener(this);
        fl_facilities.setOnClickListener(this);
    }

    private void initViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(DetailFragment.getInstance(bean.getDescription()));
        fragments.add(FactilitiesFragment.getInstance(bean));
        viewPager.setAdapter(new FacilitiesViewPagerAdapter(getSupportFragmentManager(),fragments));
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                if (position==0) {
                    tv_detail.setTextColor(ContextCompat.getColor(HotelAndHouseFacilitiesActivity.this,R.color.color_them));
                    view_detail.setVisibility(View.VISIBLE);
                    tv_facilities.setTextColor(ContextCompat.getColor(HotelAndHouseFacilitiesActivity.this,R.color.color_text_999));
                    view_facilities.setVisibility(View.INVISIBLE);
                } else if (position==1) {
                    tv_detail.setTextColor(ContextCompat.getColor(HotelAndHouseFacilitiesActivity.this,R.color.color_text_999));
                    view_detail.setVisibility(View.INVISIBLE);
                    tv_facilities.setTextColor(ContextCompat.getColor(HotelAndHouseFacilitiesActivity.this,R.color.color_them));
                    view_facilities.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("详情与设施");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_detail = (TextView) findViewById(R.id.tv_detail);
        view_detail = (View) findViewById(R.id.view_detail);
        fl_detail = (FrameLayout) findViewById(R.id.fl_detail);
        tv_facilities = (TextView) findViewById(R.id.tv_facilities);
        view_facilities = (View) findViewById(R.id.view_facilities);
        fl_facilities = (FrameLayout) findViewById(R.id.fl_facilities);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fl_detail:
                viewPager.setCurrentItem(0);
                break;
            case R.id.fl_facilities:
                viewPager.setCurrentItem(1);
                break;
        }
    }
}
