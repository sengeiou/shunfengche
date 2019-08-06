package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.adapter.MyFragmentPagerAdapter;
import com.windmillsteward.jukutech.activity.mine.fragment.MyOrderFragment;
import com.windmillsteward.jukutech.activity.mine.fragment.MyPublishFragment;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：我的订单
 * 时间：2018/2/21/021
 * 作者：xjh
 */
public class MyOrderActivity extends BaseActivity implements View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;

    private EasyPopup easyPopup;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LinearLayout linear_content;
    private List<Fragment> frameLayouts;
    private List<TextView> textViews;
    private TextView tv_all;
    private TextView tv_travel;
    private TextView tv_family;

    private String[] titles = new String[]{"进行中", "已完成", "已取消"};
    private int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorider);
        initView();
        initToolbar();
        initViewPager();
        initPopup();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (frameLayouts.get(mViewPager.getCurrentItem()) instanceof MyOrderFragment) {
            ((MyOrderFragment)frameLayouts.get(mViewPager.getCurrentItem())).refreshData();
        }
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        linear_content = (LinearLayout) findViewById(R.id.linear_content);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("全部订单");
        toolbar_iv_right.setVisibility(View.VISIBLE);
        toolbar_iv_right.setImageResource(R.mipmap.icon_shengluehao);
        toolbar_iv_right.setOnClickListener(this);
    }

    private void initViewPager() {
        frameLayouts = new ArrayList<>();
        frameLayouts.add(MyOrderFragment.getInstance(1));
        frameLayouts.add(MyOrderFragment.getInstance(2));
        frameLayouts.add(MyOrderFragment.getInstance(3));
        mViewPager.setAdapter(new MyFragmentPagerAdapter(titles, frameLayouts, getSupportFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);
    }

    private void initPopup() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.view_popup_myorder, null);
        tv_all = (TextView) inflate.findViewById(R.id.tv_all);
        tv_all.setOnClickListener(this);
        tv_travel = (TextView) inflate.findViewById(R.id.tv_travel);
        tv_travel.setOnClickListener(this);
        tv_family = (TextView) inflate.findViewById(R.id.tv_family);
        tv_family.setOnClickListener(this);
        textViews = new ArrayList<>();
        textViews.add(tv_all);
        textViews.add(tv_travel);
        textViews.add(tv_family);
        easyPopup = new EasyPopup(this)
                .setContentView(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                //是否允许点击PopupWindow之外的地方消失
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.4f)
                //变暗的背景颜色
                .setDimColor(Color.BLACK)
                //指定任意 ViewGroup 背景变暗
                .setDimView(linear_content)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                    }
                })
                .createPopup();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_iv_right:
                easyPopup.getPopupWindow().setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                easyPopup.showAtAnchorView(toolbar_iv_right, VerticalGravity.BELOW, HorizontalGravity.CENTER, 0, 0);
                chanageState(type);
                break;
            case R.id.tv_all:
                type = 0;
                chanageState(type);
                easyPopup.dismiss();
                chanageFragmentType();
                toolbar_iv_title.setText("全部订单");
                break;
            case R.id.tv_travel:
                type = 1;
                chanageState(type);
                easyPopup.dismiss();
                chanageFragmentType();
                toolbar_iv_title.setText("住宿旅游");
                break;
            case R.id.tv_family:
                type = 2;
                chanageState(type);
                easyPopup.dismiss();
                chanageFragmentType();
                toolbar_iv_title.setText("智慧生活");
                break;
        }
    }

    private void chanageFragmentType(){
        for (Fragment fragment : frameLayouts) {
            if (fragment instanceof MyOrderFragment) {
                ((MyOrderFragment) fragment).setClassType(type);
            }
        }
    }

    private void chanageState(int type) {
        for (int i = 0; i < textViews.size(); i++) {
            if (type == i) {
                TextView textView = textViews.get(i);
                textView.setBackground(ContextCompat.getDrawable(this, R.drawable.shape_popup_more_on));
                textView.setTextColor(ContextCompat.getColor(this, R.color.color_white));
            } else {
                TextView textView = textViews.get(i);
                textView.setBackground(ContextCompat.getDrawable(this, R.drawable.shape_buyhouse_popup_bg));
                textView.setTextColor(ContextCompat.getColor(this, R.color.text_color_black));
            }
        }
    }
}
