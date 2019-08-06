package com.windmillsteward.jukutech.activity.newpage.smartlife;

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
import com.windmillsteward.jukutech.activity.MainActivity;
import com.windmillsteward.jukutech.activity.home.commons.search.SearchItemActivity;
import com.windmillsteward.jukutech.activity.home.family.fragment.IntelligentFamilyListFragment;
import com.windmillsteward.jukutech.activity.newpage.pay.NewPayActivity;
import com.windmillsteward.jukutech.base.AppManager;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 订餐服务,酒店预定和门票预定首页
 */
public class DingCanServiceHomeActivity extends BaseInitActivity implements View.OnClickListener {

    public static final int DINGCAN = 1;
    public static final int HOTEL = 2;
    public static final int MENPIAO = 3;

    public static final String TYPE = "type";

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.tv_searchHint)
    TextView tvSearchHint;
    @Bind(R.id.linear_search)
    LinearLayout linearSearch;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.line_divider)
    View lineDivider;
    @Bind(R.id.fl_content)
    FrameLayout flContent;

    private DingcanServiceFragment fragment;

    private int type;//1订餐2酒店3门票

    private String keyword;

    public static void go(Activity activity, int type) {
        Intent intent = new Intent(activity, DingCanServiceHomeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    @Override
    protected void initView(View view) {
        showContentView();
        hidTitleView();
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_dingcan_service_home;
    }

    @Override
    protected void initData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getInt(TYPE);
        }
        initToolbar();
        initFragment();
    }

    private void initToolbar() {
        handleBackEvent(ivBack);
        tvSearchHint.setText("搜索");
        linearSearch.setOnClickListener(this);
    }

    /**
     * 该界面可能会重用，使用fragment方便重用
     */
    private void initFragment() {
        fragment = DingcanServiceFragment.getInstance(type);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, fragment).commitNow();
    }


    @Override
    protected void refreshPageData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_search:
                DingcanSearchActivity.go(this, type);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DingcanSearchActivity.REQUEST_CODE && resultCode == DingcanSearchActivity.RESULT_CODE_SUCCESS) {
            if (data != null) {
                String yudingTime = data.getStringExtra(DingcanSearchActivity.SELECT_TIME);
                keyword = data.getStringExtra(DingcanSearchActivity.KEYWORD);
                tvSearchHint.setText(TextUtils.isEmpty(keyword)?"搜索":keyword);
                String people_num = data.getStringExtra(DingcanSearchActivity.PEOPLE_NUM);
                if (fragment != null) {
                    fragment.search(keyword);
                }
            }
        } else if (requestCode == DingcanWebActivity.REQUEST_CODE && resultCode == DingcanWebActivity.RESULT_CODE_SUCCESS) {
                MainActivity activity = (MainActivity) AppManager.getAppManager().getActivity(MainActivity.class);
                if (activity != null) {
                    activity.changeButtonStatus(2);
                }
                finish();

        }
    }
}
