package com.windmillsteward.jukutech.activity.home.commons.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.customer.activity.CustomerDetailActivity;
import com.windmillsteward.jukutech.activity.home.capitalmanager.fragment.FinancingListFragment;
import com.windmillsteward.jukutech.activity.home.capitalmanager.fragment.LoanListFragment;
import com.windmillsteward.jukutech.activity.home.carservice.fragment.CarListFragment;
import com.windmillsteward.jukutech.activity.home.carservice.fragment.SearchBuyCarListFragment;
import com.windmillsteward.jukutech.activity.home.family.fragment.IntelligentFamilyListFragment;
import com.windmillsteward.jukutech.activity.home.houselease.fragment.BuyHouseFragment;
import com.windmillsteward.jukutech.activity.home.insurance.fragment.BigHealthListFragment;
import com.windmillsteward.jukutech.activity.home.insurance.fragment.InsuranceListFragment;
import com.windmillsteward.jukutech.activity.home.personnel.fragment.LabourServiceCenterFragment;
import com.windmillsteward.jukutech.activity.home.personnel.fragment.ResumeFragment;
import com.windmillsteward.jukutech.activity.home.personnel.fragment.SearchPositionFragment;
import com.windmillsteward.jukutech.activity.home.specialty.fragment.SpecialtyListFragment;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.HotelAndHouseListActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.fragment.HotelAndHouseListFragment;
import com.windmillsteward.jukutech.activity.home.stayandtravel.fragment.TravelListFragment;
import com.windmillsteward.jukutech.activity.home.think.fragment.IdeaThinkListFragment;
import com.windmillsteward.jukutech.activity.home.think.fragment.WisdomFireControlListSearchResultFragment;
import com.windmillsteward.jukutech.activity.newpage.smartlife.DingcanServiceFragment;
import com.windmillsteward.jukutech.base.BaseActivity;

/**
 * 描述：搜索结果界面
 * 时间：2018/1/17
 * 作者：xjh
 */

public class SearchResultActivity extends BaseActivity implements View.OnClickListener {
    public static final String TYPE = "TYPE";
    public static final String KEY = "KEY";
    public static final String CLASS_ID = "CLASS_ID";
    private ImageView toolbar_iv_back;
    private TextView tv_search_key;
    private ImageView iv_kefu;
    private FrameLayout fl_content;
    private String keyword;
    private int type;
    private int class_id;
    private FragmentManager supportFragmentManager;

    /**
     * 跳转到搜索结果界面
     *
     * @param context  上下文
     * @param keyword  关键词
     * @param type     type=0：职位搜索；1：简历搜索；2：；3：智慧家庭；4：智慧生活-订餐服务
     *                 10：旅游全部 11：周边游 12：跟团游 13：自由行 14：一日游 15：酒店
     *                 20: 思想智库
     *                 30: 房屋租售
     *                 40: 车辆列表 41: 买车
     *                 50: 大健康
     *                 60：名优特产
     *                 70：资本管理-理财
     *                 71：资本管理-贷款
     *                 80: 劳务中心
     *                 81：消防
     * @param class_id 分类id
     */
    public static void go(Context context, String keyword, int type, int class_id) {
        Intent intent = new Intent(context, SearchResultActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(SearchResultActivity.KEY, keyword);
        bundle.putInt(SearchResultActivity.TYPE, type);
        bundle.putInt(SearchResultActivity.CLASS_ID, class_id);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            keyword = extras.getString(KEY);
            type = extras.getInt(TYPE);
            class_id = extras.getInt(CLASS_ID);
        }

        supportFragmentManager = getSupportFragmentManager();

        initView();
        initToolbar();

        initFragment();
    }

    private void initFragment() {
        setParamInt(R.id.fl_content);
        if (type == 0) {  // 职位
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.replace(R.id.fl_content, SearchPositionFragment.getInstance(keyword, class_id));
            transaction.commitNow();
        } else if (type == 1) {  // 简历
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.replace(R.id.fl_content, ResumeFragment.getInstance(1, keyword, class_id));
            transaction.commitNow();
        } else if (type == 3) {
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.replace(R.id.fl_content, IntelligentFamilyListFragment.getInstance(keyword, class_id, ""));
            transaction.commitNow();
        }  else if (type == 10) {
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.replace(R.id.fl_content, TravelListFragment.getInstance(0, keyword));
            transaction.commitNow();
        } else if (type == 11) {
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.replace(R.id.fl_content, TravelListFragment.getInstance(44, keyword));
            transaction.commitNow();
        } else if (type == 12) {
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.replace(R.id.fl_content, TravelListFragment.getInstance(45, keyword));
            transaction.commitNow();
        } else if (type == 13) {
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.replace(R.id.fl_content, TravelListFragment.getInstance(46, keyword));
            transaction.commitNow();
        } else if (type == 14) {
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.replace(R.id.fl_content, TravelListFragment.getInstance(47, keyword));
            transaction.commitNow();
        } else if (type == 15) {
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putInt(HotelAndHouseListActivity.HOTEL_BUSINESS_CLASS, 1);
            bundle.putString(HotelAndHouseListActivity.KEYWORD, keyword);
            bundle.putInt(HotelAndHouseListActivity.HOTEL_TYPE, 0);
            bundle.putString(HotelAndHouseListActivity.HOTEL_TYPE_NAME, "");
            bundle.putInt(HotelAndHouseListActivity.PRICE_ID, 0);
            bundle.putString(HotelAndHouseListActivity.PRICE_ID_NAME, "");
            bundle.putInt(HotelAndHouseListActivity.THIRD_AREA_ID, 0);
            bundle.putString(HotelAndHouseListActivity.START_TIME, "");
            bundle.putString(HotelAndHouseListActivity.END_TIME, "");
            bundle.putInt(HotelAndHouseListActivity.DAY_NUM, 1);
            transaction.replace(R.id.fl_content, HotelAndHouseListFragment.getInstance(bundle));
            transaction.commitNow();
        } else if (type == 16) {
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putInt(HotelAndHouseListActivity.HOTEL_BUSINESS_CLASS, 2);
            bundle.putString(HotelAndHouseListActivity.KEYWORD, keyword);
            bundle.putInt(HotelAndHouseListActivity.HOTEL_TYPE, 0);
            bundle.putString(HotelAndHouseListActivity.HOTEL_TYPE_NAME, "");
            bundle.putInt(HotelAndHouseListActivity.PRICE_ID, 0);
            bundle.putString(HotelAndHouseListActivity.PRICE_ID_NAME, "");
            bundle.putInt(HotelAndHouseListActivity.THIRD_AREA_ID, 0);
            bundle.putString(HotelAndHouseListActivity.START_TIME, "");
            bundle.putString(HotelAndHouseListActivity.END_TIME, "");
            bundle.putInt(HotelAndHouseListActivity.DAY_NUM, 1);
            transaction.replace(R.id.fl_content, HotelAndHouseListFragment.getInstance(bundle));
            transaction.commitNow();
        } else if (type == 20) {
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.replace(R.id.fl_content, IdeaThinkListFragment.getInstance(keyword, class_id));
            transaction.commitNow();
        } else if (type == 30) {
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.replace(R.id.fl_content, BuyHouseFragment.getInstance(2, keyword, class_id, ""));
            transaction.commitNow();
        } else if (type == 31) {
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            transaction.replace(R.id.fl_content, BuyHouseFragment.getInstance(1, keyword, class_id, ""));
            transaction.commitNow();
        } else if (type == 40) {
            startFragment(null, CarListFragment.getInstance(0, "", 0, "", keyword));
        } else if (type == 41) {
            startFragment(null, SearchBuyCarListFragment.getInstance(keyword));
        } else if (type == 50) {
            startFragment(null, InsuranceListFragment.getInstance(keyword, class_id));
        } else if (type == 60) {
            startFragment(null, SpecialtyListFragment.getInstance(keyword, 0, 0, ""));
        } else if (type == 70) {
            startFragment(null, FinancingListFragment.getInstance(keyword, class_id, ""));
        } else if (type == 71) {
            startFragment(null, LoanListFragment.getInstance(keyword, class_id));
        } else if (type == 80) {//劳务中心
            startFragment(null, LabourServiceCenterFragment.getInstance(keyword, class_id));
        } else if (type == 81) {
            startFragment(null, WisdomFireControlListSearchResultFragment.getInstance(keyword, class_id));
        } else if (type == 82) {
            startFragment(null, BigHealthListFragment.getInstance(keyword, class_id));
        }
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        iv_kefu = (ImageView) findViewById(R.id.iv_kefu);
        tv_search_key = (TextView) findViewById(R.id.tv_search_key);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);

        tv_search_key.setOnClickListener(this);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        tv_search_key.setText(keyword);
        iv_kefu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAtvDonFinish(CustomerDetailActivity.class);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_search_key:
                SearchItemActivity.go(this, type, class_id);
                break;
        }
    }
}
