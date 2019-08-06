package com.windmillsteward.jukutech.activity.home.family.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.customer.activity.CustomerDetailActivity;
import com.windmillsteward.jukutech.activity.home.commons.search.SearchItemActivity;
import com.windmillsteward.jukutech.activity.home.family.fragment.IntelligentFamilyListFragment;
import com.windmillsteward.jukutech.activity.home.family.presenter.IntelligentFamilyListActPresenter;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.AuthenResultBean;

/**
 * 描述：智慧家庭列表
 * 时间：2018/1/14/014
 * 作者：xjh
 */
public class IntelligentFamilyListActivity extends BaseActivity implements IntelligentFamilyListView, View.OnClickListener {

    public static final String CURR_CLASS = "CURR_CLASS";
    public static final String CURR_NAME = "CURR_NAME";
    public static final String CURR_KEYWORK = "CURR_KEYWORK";
    private ImageView iv_back;
    private TextView tv_searchHint;
    private LinearLayout linear_search;
    private TextView tv_postJob;
    private FrameLayout fl_content;
    private ImageView iv_place_an_order;
    private String keywork;
    private ImageView iv_kefu;
    private IntelligentFamilyListActPresenter presenter;

    private int curr_class;
    private String curr_name;
    private IntelligentFamilyListFragment fragment;

    public static void go(Activity activity,String keywork) {
        Intent intent = new Intent(activity, IntelligentFamilyListActivity.class);
        intent.putExtra(CURR_KEYWORK,keywork);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intelligent_family);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            curr_class = extras.getInt(CURR_CLASS);
            curr_name = extras.getString(CURR_NAME);
            keywork = extras.getString(CURR_KEYWORK);
        }

        initView();
        initToolbar();
        initFragment();
        presenter = new IntelligentFamilyListActPresenter(this);
    }

    private void initToolbar() {
        handleBackEvent(iv_back);
        tv_searchHint.setText("搜索");

        if (!TextUtils.isEmpty(keywork)) {
            tv_searchHint.setText(keywork);
        }

        linear_search.setOnClickListener(this);
    }

    /**
     * 该界面可能会重用，使用fragment方便重用
     */
    private void initFragment() {
        fragment = IntelligentFamilyListFragment.getInstance(keywork, curr_class, curr_name);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, fragment).commitNow();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_searchHint = (TextView) findViewById(R.id.tv_searchHint);
        linear_search = (LinearLayout) findViewById(R.id.linear_search);
        tv_postJob = (TextView) findViewById(R.id.tv_postJob);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        iv_place_an_order = (ImageView) findViewById(R.id.iv_place_an_order);
        iv_kefu = (ImageView) findViewById(R.id.iv_kefu);
        iv_kefu.setVisibility(View.VISIBLE);

        iv_kefu.setOnClickListener(this);

    }

    /**
     * 判断是否认证
     *
     * @param bean bean.getIs_authen()==1 已经认证过，0未认证
     */
    @Override
    public void isAuthen(AuthenResultBean bean) {
        if (bean != null) {
            if (bean.getIs_authen() == 1) {
                startAtvDonFinish(PublishRequireActivity.class);
                fragment.needRefresh = true;
            } else if (bean.getIs_authen() == 0) {
                startAtvDonFinish(PersonalAuthenticationActivity.class);
            } else if (bean.getIs_authen() == 2) {
                showTips("客服正在审核认证信息，审核完成后可发布信息", 1);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_search:
                SearchItemActivity.go(this, 3, curr_class);
                break;
            case R.id.iv_kefu:
                startAtvDonFinish(CustomerDetailActivity.class);
                break;
        }

    }
}
