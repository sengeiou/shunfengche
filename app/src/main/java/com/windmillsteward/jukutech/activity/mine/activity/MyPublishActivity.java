package com.windmillsteward.jukutech.activity.mine.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.adapter.MyFragmentPagerAdapter;
import com.windmillsteward.jukutech.activity.mine.fragment.MyPublishFragment;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.customview.popup.EasyPopup;
import com.windmillsteward.jukutech.customview.popup.HorizontalGravity;
import com.windmillsteward.jukutech.customview.popup.VerticalGravity;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：我的发布界面
 * 时间：2018/2/20/020
 * 作者：xjh
 */
public class MyPublishActivity extends BaseActivity implements View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LinearLayout linear_content;

    private EasyPopup easyPopup;
    private TextView tv_all;
    private TextView tv_present;
    private TextView tv_idea;
    private TextView tv_family;
    private TextView tv_house;
    private TextView tv_travel;
    private List<TextView> textViews;

    private int type;
    private List<Fragment> fragments;
    private String[] titles = new String[]{"已发布", "审核中", "不通过"};
    private TextView tv_insurance;
    private TextView tv_legal;
    private TextView tv_capital;
    private TextView tv_car;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypublish);
        initView();
        initToolbar();
        initViewPager();
        initPopup();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if (fragments.get(mViewPager.getCurrentItem()) instanceof MyPublishFragment) {
            ((MyPublishFragment) fragments.get(mViewPager.getCurrentItem())).refreshData();
        }
    }

    private void initViewPager() {
        fragments = new ArrayList<>();
        fragments.add(MyPublishFragment.getInstence(1));
        fragments.add(MyPublishFragment.getInstence(0));
        fragments.add(MyPublishFragment.getInstence(2));
        mViewPager.setAdapter(new MyFragmentPagerAdapter(titles, fragments, getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initPopup() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.view_popup_mypublish, null);
        tv_all = (TextView) inflate.findViewById(R.id.tv_all);
        tv_present = (TextView) inflate.findViewById(R.id.tv_present);
        tv_idea = (TextView) inflate.findViewById(R.id.tv_idea);
        tv_family = (TextView) inflate.findViewById(R.id.tv_family);
        tv_house = (TextView) inflate.findViewById(R.id.tv_house);
        tv_travel = (TextView) inflate.findViewById(R.id.tv_travel);
        tv_insurance = (TextView) inflate.findViewById(R.id.tv_insurance);
        tv_legal = (TextView) inflate.findViewById(R.id.tv_legal);
        tv_capital = (TextView) inflate.findViewById(R.id.tv_capital);
        tv_car = (TextView) inflate.findViewById(R.id.tv_car);
        textViews = new ArrayList<>();
        textViews.add(tv_all);
        textViews.add(tv_present);
        textViews.add(tv_idea);
        textViews.add(tv_family);
        textViews.add(tv_house);
        textViews.add(tv_travel);
        textViews.add(tv_insurance);
        textViews.add(tv_legal);
        textViews.add(tv_capital);
        textViews.add(tv_car);
        tv_all.setOnClickListener(this);
        tv_present.setOnClickListener(this);
        tv_idea.setOnClickListener(this);
        tv_family.setOnClickListener(this);
        tv_house.setOnClickListener(this);
        tv_travel.setOnClickListener(this);
        tv_insurance.setOnClickListener(this);
        tv_legal.setOnClickListener(this);
        tv_capital.setOnClickListener(this);
        tv_car.setOnClickListener(this);
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
        toolbar_iv_title.setText("全部");
        toolbar_iv_right.setVisibility(View.VISIBLE);
        toolbar_iv_right.setImageResource(R.mipmap.icon_shengluehao);
        toolbar_iv_right.setOnClickListener(this);
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
                toolbar_iv_title.setText("全部");
                break;
            case R.id.tv_present:
                type = 1;
                chanageState(type);
                easyPopup.dismiss();
                chanageFragmentType();
                toolbar_iv_title.setText("人才驿站");
                break;
            case R.id.tv_idea:
                type = 2;
                chanageState(type);
                easyPopup.dismiss();
                chanageFragmentType();
                toolbar_iv_title.setText("思想智库");
                break;
            case R.id.tv_family:
                type = 3;
                chanageState(type);
                easyPopup.dismiss();
                chanageFragmentType();
                toolbar_iv_title.setText("智慧生活");
                break;
            case R.id.tv_house:
                type = 4;
                chanageState(type);
                easyPopup.dismiss();
                chanageFragmentType();
                toolbar_iv_title.setText("房屋租售");
                break;
            case R.id.tv_travel:
                type = 5;
                chanageState(type);
                easyPopup.dismiss();
                chanageFragmentType();
                toolbar_iv_title.setText("旅游住宿");
                break;
            case R.id.tv_insurance:  // 6.大健康，7.法律专家，8.资本管理，9.车辆管理
                type = 6;
                chanageState(type);
                easyPopup.dismiss();
                chanageFragmentType();
                toolbar_iv_title.setText("大健康");
                break;
            case R.id.tv_legal:
                type = 7;
                chanageState(type);
                easyPopup.dismiss();
                chanageFragmentType();
                toolbar_iv_title.setText("法律专家");
                break;
            case R.id.tv_capital:
                type = 8;
                chanageState(type);
                easyPopup.dismiss();
                chanageFragmentType();
                toolbar_iv_title.setText("资本管理");
                break;
            case R.id.tv_car:
                type = 9;
                chanageState(type);
                easyPopup.dismiss();
                chanageFragmentType();
                toolbar_iv_title.setText("车辆管理");
                break;
        }
    }

    private void chanageFragmentType() {
        for (Fragment fragment : fragments) {
            if (fragment instanceof MyPublishFragment) {
                ((MyPublishFragment) fragment).setType(type);
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
