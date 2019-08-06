package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.fragment.MyPublishListFragment;
import com.windmillsteward.jukutech.base.BaseActivity;

/**
 * 描述：我的发布列表
 * 时间：2018/1/12
 * 作者：xjh
 */

public class MyPublishListActivity extends BaseActivity implements View.OnClickListener {


    private TextView tv_position;
    private TextView tv_resume;
    private FrameLayout fl_content;
    private View view_position;
    private View view_resume;
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private MyPublishListFragment positionFragment,resumeFragment;
    private FragmentManager fragmentManager;
    private int currType=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_perent);
        initView();
        initToolbar();
        initFragment();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        positionFragment.onActivityResult(requestCode,resultCode,data);
        resumeFragment.onActivityResult(requestCode,resultCode,data);
    }

    private void initFragment() {
        positionFragment = MyPublishListFragment.getInstance(1);
        resumeFragment = MyPublishListFragment.getInstance(2);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fl_content,positionFragment).add(R.id.fl_content,resumeFragment).hide(resumeFragment).show(positionFragment);
        transaction.commitNow();
        currType = 1;
        view_position.setVisibility(View.VISIBLE);
        view_resume.setVisibility(View.INVISIBLE);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("我的发布");
    }

    private void initView() {
        tv_position = (TextView) findViewById(R.id.tv_position);
        tv_position.setOnClickListener(this);
        tv_resume = (TextView) findViewById(R.id.tv_resume);
        tv_resume.setOnClickListener(this);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        view_position = (View) findViewById(R.id.view_position);
        view_resume = (View) findViewById(R.id.view_resume);
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_position:
                if (currType==1) {
                    return;
                }
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.hide(resumeFragment).show(positionFragment);
                transaction.commitNow();
                currType =1;
                view_position.setVisibility(View.VISIBLE);
                view_resume.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_resume:
                if (currType==2) {
                    return;
                }
                FragmentTransaction transaction1 = fragmentManager.beginTransaction();
                transaction1.hide(positionFragment).show(resumeFragment);
                transaction1.commitNow();
                currType = 2;
                view_position.setVisibility(View.INVISIBLE);
                view_resume.setVisibility(View.VISIBLE);
                break;
        }
    }
}
