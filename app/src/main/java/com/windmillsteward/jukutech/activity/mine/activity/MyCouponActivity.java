package com.windmillsteward.jukutech.activity.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.fragment.MyCouponFragment;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BaseInitFragment;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.adapter.TabAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 描述：我的优惠券
 * author:cyq
 * 2018-03-05
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MyCouponActivity extends BaseInitActivity {

    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.ll_indicator_container)
    LinearLayout llIndicatorContainer;
    @Bind(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    private TabAdapter tabAdapter;
    private List<BaseInitFragment> fragments;

    int use_coupon;//1能使用0不能使用

    @Override
    protected void initView(View view) {
        showContentView();
        setMainTitle("优惠券");
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    tvLeft.setTextColor(Color.parseColor("#3172f4"));
                    tvRight.setTextColor(Color.parseColor("#9399a5"));
                } else {
                    tvLeft.setTextColor(Color.parseColor("#9399a5"));
                    tvRight.setTextColor(Color.parseColor("#3172f4"));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_my_coupon;
    }

    @Override
    protected void initData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            use_coupon = extras.getInt(NewPayActivity.USE_COUPON, 0);
        }

        fragments = new ArrayList<>();
        fragments.add(MyCouponFragment.newInstance(1,use_coupon));
        fragments.add(MyCouponFragment.newInstance(2,use_coupon));

        tabAdapter = new TabAdapter(getSupportFragmentManager(), fragments);
        viewpager.setAdapter(tabAdapter);
    }

    @Override
    protected void refreshPageData() {
        for (BaseInitFragment fragment : fragments) {
            if (fragment instanceof MyCouponFragment) {
                ((MyCouponFragment) fragment).refresh();
            }
        }
    }

    @OnClick({R.id.tv_left, R.id.tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_left:
                if (viewpager.getCurrentItem() != 0) {
                    viewpager.setCurrentItem(0);
                }
                break;
            case R.id.tv_right:
                if (viewpager.getCurrentItem() != 1) {
                    viewpager.setCurrentItem(1);
                }
                break;
        }
    }
}
