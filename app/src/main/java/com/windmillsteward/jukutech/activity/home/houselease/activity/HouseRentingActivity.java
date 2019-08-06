package com.windmillsteward.jukutech.activity.home.houselease.activity;

import android.content.Context;
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
import com.windmillsteward.jukutech.activity.home.houselease.fragment.BuyHouseFragment;
import com.windmillsteward.jukutech.activity.home.houselease.presenter.HouseRentingPresenter;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.AuthenResultBean;

/**
 * 描述：房屋租售
 * 时间：2018/1/17/017
 * 作者：xjh
 */
public class HouseRentingActivity extends BaseActivity implements View.OnClickListener, HouseRentingView {

    public static final String CURR_CLASS = "CURR_CLASS";
    public static final String CURR_SELECT = "CURR_SELECT";
    public static final String CURR_NAME = "CURR_NAME";
    public static final String KEYWORD = "KEYWORD";

    private ImageView iv_back;
    private TextView tv_searchHint;
    private LinearLayout linear_search;
    private TextView tv_postJob;
    private FrameLayout fl_content;

    private ImageView iv_kefu;

    private int currSelect;
    private int curr_class;
    private int curr_select;
    private String curr_name;
    private String keyword;
    private HouseRentingPresenter presenter;
    private BuyHouseFragment buyHouseFragment;
    private BuyHouseFragment buyHouseFragment1;

    /**
     * 跳转
     *
     * @param context
     */
    public static void go(Context context, String keyword, int curr_select, String curr_name) {
        Intent intent = new Intent(context, HouseRentingActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(CURR_SELECT, curr_select);
        bundle.putString(KEYWORD, keyword);
        bundle.putString(CURR_NAME, curr_name);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            curr_class = extras.getInt(CURR_CLASS);
            curr_select = extras.getInt(CURR_SELECT);
            keyword = extras.getString(KEYWORD, "");
            curr_name = extras.getString(CURR_NAME, "");
        }

        setContentView(R.layout.activity_houserenting);
        initView();
        initToolbar();
        presenter = new HouseRentingPresenter(this);
    }

    private void initToolbar() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_searchHint.setText(TextUtils.isEmpty(keyword) ? "搜索" : keyword);
        linear_search.setOnClickListener(this);
        iv_kefu.setVisibility(View.VISIBLE);
        tv_postJob.setOnClickListener(this);
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_searchHint = (TextView) findViewById(R.id.tv_searchHint);
        linear_search = (LinearLayout) findViewById(R.id.linear_search);
        tv_postJob = (TextView) findViewById(R.id.tv_postJob);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        iv_kefu = findViewById(R.id.iv_kefu);

        buyHouseFragment = BuyHouseFragment.getInstance(2, keyword, curr_select, curr_name);
        buyHouseFragment1 = BuyHouseFragment.getInstance(1, keyword, curr_select, curr_name);

//        buyHouseFragment = BuyHouseFragment.getInstance(2, keyword, (curr_select == 0) ? curr_class : 0, curr_name);
//        buyHouseFragment1 = BuyHouseFragment.getInstance(1, keyword, (curr_select == 1) ? curr_class : 0, curr_name);
        setParamInt(R.id.fl_content);
        setSelect(curr_select);
        currSelect = curr_select;
    }

    private void setSelect(int select) {
        tv_searchHint.setText(TextUtils.isEmpty(keyword) ? "搜索" : keyword);
        startFragment(null, buyHouseFragment1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_search:
                if (currSelect == 0) {
                    SearchItemActivity.go(this, 30, 0);
                } else if (currSelect == 1) {
                    SearchItemActivity.go(this, 31, 0);
                }
                break;
            case R.id.iv_kefu:
                startAtvDonFinish(CustomerDetailActivity.class);
                break;
        }
    }

    @Override
    public void isAuthen(AuthenResultBean bean) {
        if (bean.getIs_authen() == 1) {
            Bundle bundle = new Bundle();
            bundle.putInt(PublishActivity.TYPE, currSelect);
            startAtvDonFinish(PublishActivity.class, bundle);
            if (currSelect == 0) {
                buyHouseFragment.needRefresh = true;
            } else {
                buyHouseFragment1.needRefresh = true;
            }
        } else if (bean.getIs_authen() == 0) {
            startAtvDonFinish(PersonalAuthenticationActivity.class);
        } else if (bean.getIs_authen() == 2) {
            showTips("客服正在审核认证信息，审核完成后可发布信息", 1);
        }
    }
}
