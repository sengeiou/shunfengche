package com.windmillsteward.jukutech.activity.home.insurance.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.search.SearchItemActivity;
import com.windmillsteward.jukutech.activity.home.insurance.fragment.BigHealthListFragment;
import com.windmillsteward.jukutech.activity.home.insurance.fragment.InsuranceListFragment;
import com.windmillsteward.jukutech.activity.home.insurance.presenter.InsuranceListActivityPresenter;
import com.windmillsteward.jukutech.activity.home.personnel.activity.CretePositionActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PublishJobWantedActivity;
import com.windmillsteward.jukutech.activity.home.personnel.fragment.PositionFragment;
import com.windmillsteward.jukutech.activity.home.personnel.fragment.ResumeFragment;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.interfaces.Define;

/**
 * 描述：大健康模块
 * 时间：2018/3/18/018
 * 作者：xjh
 */
public class InsuranceListActivity extends BaseActivity implements View.OnClickListener, InsuranceListActivityView {

    private TextView tvPostJob;  // 标题栏发布
    private TextView tvSearchHint;  // 搜索提示
    private ImageView iv_back;
    private TextView tv_searchHint;
    private LinearLayout linear_search;
    private TextView tv_postJob;
    private FrameLayout fl_content;
    private ImageView iv_big_health;// 大健康
    private TextView tv_big_health;// 大健康
    private LinearLayout ll_big_health;// 大健康
    private ImageView iv_insurance;// 保险
    private TextView tv_insurance;// 保险
    private LinearLayout ll_insurance;// 保险

    // 当前选中的fragment
    private int curr_position;
    private int curr_class;

    private InsuranceListActivityPresenter presenter;
    private BigHealthListFragment bighealthListFragment;
    private InsuranceListFragment insuranceListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_list);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            curr_class = extras.getInt(Define.INTENT_DATA);
        }
        initView();
        initToolbar();
        presenter = new InsuranceListActivityPresenter(this);
        setParamInt(R.id.fl_content);

        bighealthListFragment = BigHealthListFragment.getInstance("", curr_class);
        insuranceListFragment = InsuranceListFragment.getInstance("", curr_class);
        // 设置当前选中的fragment
        if (curr_position==0) {
            startFragment(null,bighealthListFragment);
        } else if (curr_position==1){
            startFragment(null,insuranceListFragment);
        }
        setTabStatus();
    }

    private void initToolbar() {
        handleBackEvent(iv_back);
        tv_searchHint.setText("搜索");
        tv_postJob.setVisibility(View.VISIBLE);
        tv_postJob.setText("发布信息");
        linear_search.setOnClickListener(this);
        tv_postJob.setOnClickListener(this);
    }

    private void initView() {
        tvPostJob = (TextView) findViewById(R.id.tv_postJob);
        tvSearchHint = (TextView) findViewById(R.id.tv_searchHint);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_searchHint = (TextView) findViewById(R.id.tv_searchHint);
        linear_search = (LinearLayout) findViewById(R.id.linear_search);
        tv_postJob = (TextView) findViewById(R.id.tv_postJob);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        iv_big_health = (ImageView) findViewById(R.id.iv_big_health);
        tv_big_health = (TextView) findViewById(R.id.tv_big_health);
        ll_big_health = (LinearLayout) findViewById(R.id.ll_big_health);
        iv_insurance = (ImageView) findViewById(R.id.iv_insurance);
        tv_insurance = (TextView) findViewById(R.id.tv_insurance);
        ll_insurance = (LinearLayout) findViewById(R.id.ll_insurance);

        ll_big_health.setOnClickListener(this);
        ll_insurance.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_search:
                if (curr_position==0) {
                    SearchItemActivity.go(this, 82, curr_class);
                } else if (curr_position==1){
                    SearchItemActivity.go(this, 50, curr_class);
                }
                break;
            case R.id.tv_postJob:
                if (isLogin()) {
                    presenter.getAuthenState(getAccessToken());
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.ll_big_health: // 切换到大健康fragment
                if (curr_position == 0) {
                    return;
                }
                curr_position = 0;
                startFragment(null,bighealthListFragment);
                setTabStatus();
                break;
            case R.id.ll_insurance:  // 切换到保险fragment
                if (curr_position == 1) {
                    return;
                }
                curr_position = 1;
                startFragment(null,insuranceListFragment);
                setTabStatus();
                break;
        }
    }

    private void setTabStatus() {
        if (curr_position==0) {
            tvSearchHint.setText(getString(R.string.search));
            tvPostJob.setVisibility(View.GONE);
            iv_big_health.setImageResource(R.mipmap.icon_healthy);
            tv_big_health.setTextColor(ContextCompat.getColor(this,R.color.color_them));
            iv_insurance.setImageResource(R.mipmap.icon_insurance_n);
            tv_insurance.setTextColor(ContextCompat.getColor(this,R.color.text_color_black));
        } else if (curr_position==1) {
            tvSearchHint.setText(getString(R.string.search));
            tvPostJob.setText("发布信息");
            tvPostJob.setVisibility(View.VISIBLE);
            iv_big_health.setImageResource(R.mipmap.icon_healthy_n);
            tv_big_health.setTextColor(ContextCompat.getColor(this,R.color.text_color_black));
            iv_insurance.setImageResource(R.mipmap.icon_insurance);
            tv_insurance.setTextColor(ContextCompat.getColor(this,R.color.color_them));
        }

    }

    @Override
    public void isAuthen(AuthenResultBean bean) {
        if (bean != null) {
            if (bean.getIs_authen() == 1) {
                if (curr_position == 0) {
                    startAtvDonFinish(PublishJobWantedActivity.class);
//                    bighealthListFragment.needRefresh = true;
                } else if (curr_position == 1) {
                    startAtvDonFinish(PublishInsuranceActivity.class);
                    insuranceListFragment.needRefresh = true;
                }
            } else if (bean.getIs_authen() == 0) {
                startAtvDonFinish(PersonalAuthenticationActivity.class);
            } else if (bean.getIs_authen() == 2) {
                showTips("客服正在审核认证信息，审核完成后可发布信息", 1);
            }
        }
    }
}
