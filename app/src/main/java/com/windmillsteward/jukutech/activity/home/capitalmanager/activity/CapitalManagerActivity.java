package com.windmillsteward.jukutech.activity.home.capitalmanager.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.capitalmanager.fragment.FinancingListFragment;
import com.windmillsteward.jukutech.activity.home.capitalmanager.fragment.LoanListFragment;
import com.windmillsteward.jukutech.activity.home.capitalmanager.presenter.CapitalManagerActivityPresenter;
import com.windmillsteward.jukutech.activity.home.commons.search.SearchItemActivity;
import com.windmillsteward.jukutech.activity.home.houselease.activity.HouseRentingActivity;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.interfaces.Define;

/**
 *资金管理
 * Created by Administrator on 2018/4/8 0008.
 */

public class CapitalManagerActivity extends BaseActivity implements View.OnClickListener,CapitalManagerActivityView {

    private ImageView iv_back;
    private TextView tv_searchHint;
    private LinearLayout linear_search;
    private TextView tv_postJob;
    private FrameLayout fl_content;
    private ImageView iv_position;
    private TextView tv_position;
    private LinearLayout linear_position;
    private ImageView iv_resume;
    private TextView tv_resume;
    private LinearLayout linear_resume;

    private int currSelect;
    private int curr_class;
    private int curr_select;
    private String select_text;
    private String keyword;
    private CapitalManagerActivityPresenter presenter;
    private FinancingListFragment financingListFragment;
    private LoanListFragment loanListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            curr_class = extras.getInt(Define.INTENT_DATA);
            curr_select = extras.getInt(Define.INTENT_DATA_TWO);
            select_text = extras.getString(Define.INTENT_DATA_THREE);
        }

        setContentView(R.layout.activity_capital_manager);
        initView();
        initToolbar();
        presenter = new CapitalManagerActivityPresenter(this);

        setParamInt(R.id.fl_content);
        financingListFragment = FinancingListFragment.getInstance(keyword, (curr_select == 0) ? curr_class : 0,select_text);
        loanListFragment = LoanListFragment.getInstance(keyword, (curr_select == 1) ? curr_class : 0);
        setSelect(curr_select);
    }

    private void initToolbar() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_searchHint.setText(TextUtils.isEmpty(keyword)?"搜索":keyword);
        linear_search.setOnClickListener(this);
        tv_postJob.setVisibility(View.VISIBLE);
        tv_postJob.setText("发布产品");
        tv_postJob.setOnClickListener(this);
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_searchHint = (TextView) findViewById(R.id.tv_searchHint);
        linear_search = (LinearLayout) findViewById(R.id.linear_search);
        tv_postJob = (TextView) findViewById(R.id.tv_postJob);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        iv_position = (ImageView) findViewById(R.id.iv_position);
        tv_position = (TextView) findViewById(R.id.tv_position);
        linear_position = (LinearLayout) findViewById(R.id.linear_position);
        iv_resume = (ImageView) findViewById(R.id.iv_resume);
        tv_resume = (TextView) findViewById(R.id.tv_resume);
        linear_resume = (LinearLayout) findViewById(R.id.linear_resume);

        currSelect = curr_select;
        linear_position.setOnClickListener(this);
        linear_resume.setOnClickListener(this);
        linear_search.setOnClickListener(this);
    }

    private void setSelect(int select) {
        if (select == 0){
            iv_position.setImageResource(R.mipmap.icon_financing);
            tv_position.setTextColor(ContextCompat.getColor(this,R.color.color_23abac));
            iv_resume.setImageResource(R.mipmap.icon_loan_n);
            tv_resume.setTextColor(ContextCompat.getColor(this,R.color.color_text_78));
            tv_searchHint.setText(TextUtils.isEmpty(keyword)?"搜索":keyword);
            startFragment(null, financingListFragment);
            tv_postJob.setText("发布产品");
        } else if (select == 1) {
            iv_position.setImageResource(R.mipmap.icon_financing_n);
            tv_position.setTextColor(ContextCompat.getColor(this,R.color.color_text_78));
            iv_resume.setImageResource(R.mipmap.icon_loan);
            tv_resume.setTextColor(ContextCompat.getColor(this,R.color.color_23abac));
            tv_searchHint.setText(TextUtils.isEmpty(keyword)?"搜索":keyword);
            startFragment(null, loanListFragment);
            tv_postJob.setText("资金放贷");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_search:
                if (currSelect==0) {
                    SearchItemActivity.go(this,70,0);
                } else if (currSelect==1) {
                    SearchItemActivity.go(this,71,0);
                }
                break;
            case R.id.tv_postJob:
                if (isLogin()) {
                    presenter.getAuthenState(getAccessToken());
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.linear_position:
                if (currSelect!=0) {
                    currSelect = 0;
                    setSelect(0);
                }
                break;
            case R.id.linear_resume:
                if (currSelect!=1) {
                    currSelect = 1;
                    setSelect(1);
                }
                break;
        }
    }

    @Override
    public void isAuthen(AuthenResultBean bean) {
        if (bean.getIs_authen()==1) {
            if (currSelect==0) {
                startAtvDonFinish(PublishFinancingActivity.class);
                financingListFragment.needRefresh = true;
            } else if (currSelect==1) {
                startAtvDonFinish(PublishLoanActivity.class);
                loanListFragment.needRefresh = true;
            }
        } else if (bean.getIs_authen()==0){
            startAtvDonFinish(PersonalAuthenticationActivity.class);
        } else if (bean.getIs_authen()==2) {
            showTips("客服正在审核认证信息，审核完成后可发布信息",1);
        }
    }
}
