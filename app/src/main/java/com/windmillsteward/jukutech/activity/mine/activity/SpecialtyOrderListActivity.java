package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.adapter.MyFragmentPagerAdapter;
import com.windmillsteward.jukutech.activity.mine.fragment.SpecialtyOrderListFragment;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class SpecialtyOrderListActivity extends BaseActivity {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private MyFragmentPagerAdapter adapter;
    private String[] titles = new String[]{"全部","待付款","待发货","待收货","已取消"};
    private List<Fragment> fragments;

    private int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty_orderlist);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            type = extras.getInt(Define.INTENT_TYPE);
        }
        initView();
        initToolbar();
        initViewPager();
    }

    private void initViewPager() {
        fragments = new ArrayList<>();
        fragments.add(SpecialtyOrderListFragment.getInstance(0));
        fragments.add(SpecialtyOrderListFragment.getInstance(1));
        fragments.add(SpecialtyOrderListFragment.getInstance(2));
        fragments.add(SpecialtyOrderListFragment.getInstance(3));
        fragments.add(SpecialtyOrderListFragment.getInstance(6));
        adapter = new MyFragmentPagerAdapter(titles,fragments,getSupportFragmentManager());
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(type);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("特产订单");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
    }
}
