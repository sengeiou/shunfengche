package com.windmillsteward.jukutech.activity.home.think.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.search.SearchItemActivity;
import com.windmillsteward.jukutech.activity.home.think.fragment.IdeaThinkListFragment;
import com.windmillsteward.jukutech.activity.home.think.presenter.IdeaThinkListActPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.AuthenResultBean;

/**
 * 描述：思想智库列表
 * 时间：2018/2/4/004
 * 作者：xjh
 */
public class IdeaThinkListActivity extends BaseActivity implements View.OnClickListener,IdeaThinkListView {

    public static final String CURR_CLASS = "CURR_CLASS";
    private ImageView iv_back;
    private TextView tv_searchHint;
    private LinearLayout linear_search;
    private TextView tv_postJob;
    private FrameLayout fl_content;
    private IdeaThinkListActPresenter presenter;

    private int curr_type;
    private IdeaThinkListFragment ideaThinkListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideathink);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            curr_type = extras.getInt(CURR_CLASS);
        }
        initView();
        initToolbar();

        initFragment();

        presenter = new IdeaThinkListActPresenter(this);
    }

    private void initFragment() {
        ideaThinkListFragment = IdeaThinkListFragment.getInstance("", curr_type);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, ideaThinkListFragment).commitNow();
    }

    private void initToolbar() {
        handleBackEvent(iv_back);
        tv_searchHint.setText("搜索需求");
        tv_postJob.setVisibility(View.VISIBLE);
        tv_postJob.setText("发布需求");
        tv_postJob.setOnClickListener(this);
        linear_search.setOnClickListener(this);
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_searchHint = (TextView) findViewById(R.id.tv_searchHint);
        linear_search = (LinearLayout) findViewById(R.id.linear_search);
        tv_postJob = (TextView) findViewById(R.id.tv_postJob);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_postJob:
                if (isLogin()) {
                    presenter.getAuthenState(getAccessToken());
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.linear_search:
                SearchItemActivity.go(this,20,0);
                break;
        }
    }

    @Override
    public void isAuthen(AuthenResultBean bean) {
        if (bean!=null) {
            if (bean.getIs_authen()==1) {
                startAtvDonFinish(PublishIdeaThinkActivity.class);
                ideaThinkListFragment.needRefresh = true;
            } else if (bean.getIs_authen()==0){
                startAtvDonFinish(PersonalAuthenticationActivity.class);
            } else if (bean.getIs_authen()==2) {
                showTips("客服正在审核认证信息，审核完成后可发布信息",1);
            }
        }
    }
}
