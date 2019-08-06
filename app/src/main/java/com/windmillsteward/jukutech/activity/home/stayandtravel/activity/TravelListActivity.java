package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.search.SearchItemActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.fragment.TravelListFragment;
import com.windmillsteward.jukutech.activity.home.stayandtravel.presenter.TravelListActPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.AuthenResultBean;


/**
 * 描述：旅游列表
 * 时间：2018/1/25
 * 作者：xjh
 */

public class TravelListActivity extends BaseActivity implements View.OnClickListener, TravelListView {

    public static final String TYPE = "CLASS_TYPE";
    public static final String KEYWORD = "KEYWORD";
    private ImageView iv_back;
    private TextView tv_searchHint;
    private LinearLayout linear_search;
    private TextView tv_postJob;

    private int travel_class_id;
    private String keyword;

    private TravelListActPresenter presenter;
    private TravelListFragment travelListFragment;

    /**
     * 发车到此
     *
     * @param context 上下文
     * @param type    type 0：全部；44：周边游；45：跟团游；46：自由行；47：一日游
     */
    public static void go(Context context, int type, String keyword) {
        Intent intent = new Intent(context, TravelListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(TravelListActivity.TYPE, type);
        bundle.putString(TravelListActivity.KEYWORD, keyword);
        intent.putExtras(bundle);
        context.startActivity(intent, bundle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travellist);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            travel_class_id = extras.getInt(TYPE);
            keyword = extras.getString(KEYWORD, "");
        }
        initView();
        initToolbar();

        travelListFragment = TravelListFragment.getInstance(travel_class_id, keyword);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, travelListFragment).commitNow();

        presenter = new TravelListActPresenter(this);
    }


    private void initToolbar() {
        handleBackEvent(iv_back);
        tv_searchHint.setText(TextUtils.isEmpty(keyword) ? "搜索" : keyword);
        tv_postJob.setText("发布旅游");
        tv_postJob.setVisibility(View.VISIBLE);
        tv_postJob.setOnClickListener(this);
        linear_search.setOnClickListener(this);
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_searchHint = (TextView) findViewById(R.id.tv_searchHint);
        linear_search = (LinearLayout) findViewById(R.id.linear_search);
        tv_postJob = (TextView) findViewById(R.id.tv_postJob);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_search:  // 0：全部；44：周边游；45：跟团游；46：自由行；47：一日游
                switch (travel_class_id) {
                    case 0:
                        SearchItemActivity.go(this, 10, 0);
                        break;
                    case 44:
                        SearchItemActivity.go(this, 11, 0);
                        break;
                    case 45:
                        SearchItemActivity.go(this, 12, 0);
                        break;
                    case 46:
                        SearchItemActivity.go(this, 13, 0);
                        break;
                    case 47:
                        SearchItemActivity.go(this, 14, 0);
                        break;
                }
                break;
            case R.id.tv_postJob:
                if (isLogin()) {
                    presenter.getAuthenState(getAccessToken());
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
        }
    }

    @Override
    public void isAuthen(AuthenResultBean bean) {
        if (bean.getIs_authen() == 1) {
            startAtvDonFinish(PublishTravelActivity.class);
            travelListFragment.needRefresh = true;
        } else if (bean.getIs_authen() == 0) {
            startAtvDonFinish(PersonalAuthenticationActivity.class);
        } else if (bean.getIs_authen() == 2) {
            showTips("客服正在审核认证信息，审核完成后可发布信息", 1);
        }
    }
}
