package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.fragment.FundsTrusteeshipFragment;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.BaseFragment;

/**
 * 描述：资金托管首页
 * author:cyq
 * 2018-03-07
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class FundsTrusteeshipHomeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView toolbar_tv_right;
    private RadioButton rb_check_pending;
    private RadioButton rb_hosting;
    private RadioButton rb_dispute_order;
    private RadioButton rb_completed;
    private RadioGroup rg_main;

    private FundsTrusteeshipFragment checkPendingFragment;
    private FundsTrusteeshipFragment hostingFragment;
    private FundsTrusteeshipFragment disputeOrderFragment;
    private FundsTrusteeshipFragment completedFragment;

    private BaseFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funds_trusteeship_home);
        setParamInt(R.id.lay_ll_content);
        initFragment();
        initView();
        initTitleBar();
        initData();
    }

    private void initFragment() {
        checkPendingFragment = FundsTrusteeshipFragment.getInstance(1);
        hostingFragment = FundsTrusteeshipFragment.getInstance(2);
        disputeOrderFragment = FundsTrusteeshipFragment.getInstance(3);
        completedFragment = FundsTrusteeshipFragment.getInstance(4);
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        rb_check_pending = (RadioButton) findViewById(R.id.rb_check_pending);
        rb_hosting = (RadioButton) findViewById(R.id.rb_hosting);
        rb_dispute_order = (RadioButton) findViewById(R.id.rb_dispute_order);
        rb_completed = (RadioButton) findViewById(R.id.rb_completed);
        rg_main = (RadioGroup) findViewById(R.id.rg_main);

        rg_main.setOnCheckedChangeListener(this);
        toolbar_tv_right.setOnClickListener(this);
        rb_check_pending.setChecked(true);

    }

    private void initTitleBar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("资金托管中心");
        toolbar_tv_right.setText("添加");
        toolbar_tv_right.setVisibility(View.VISIBLE);
    }

    private void initData() {

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (checkPendingFragment.isVisible()){
            checkPendingFragment.initData();
        }else if (hostingFragment.isVisible()){
            hostingFragment.initData();
        }else if (disputeOrderFragment.isVisible()){
            disputeOrderFragment.initData();
        }else if (completedFragment.isVisible()){
            completedFragment.initData();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_tv_right:
                startAtvDonFinish(AddFundsTrusteeshipActivity.class);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
        switch (checkId) {
            case R.id.rb_check_pending:
                startFragment(null, checkPendingFragment);
                mFragment = checkPendingFragment;
                break;
            case R.id.rb_hosting:
                startFragment(null, hostingFragment);
                mFragment = hostingFragment;
                break;
            case R.id.rb_dispute_order:
                startFragment(null, disputeOrderFragment);
                mFragment = disputeOrderFragment;
                break;
            case R.id.rb_completed:
                startFragment(null, completedFragment);
                mFragment = completedFragment;
                break;
        }
    }
}
