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
import com.windmillsteward.jukutech.activity.home.stayandtravel.fragment.HotelAndHouseListFragment;
import com.windmillsteward.jukutech.activity.home.stayandtravel.presenter.HotelAndHouseActPresenter;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.activity.mine.activity.PersonalAuthenticationActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.AuthenResultBean;

/**
 * 描述：酒店或房源列表
 * 时间：2018/1/27/027
 * 作者：xjh
 */
public class HotelAndHouseListActivity extends BaseActivity implements View.OnClickListener,HotelAndHouseListView {

    public static final String HOTEL_BUSINESS_CLASS = "HOTEL_BUSINESS_CLASS";
    public static final String KEYWORD = "KEYWORD";
    public static final String HOTEL_TYPE = "HOTEL_TYPE";
    public static final String HOTEL_TYPE_NAME = "HOTEL_TYPE_NAME";
    public static final String PRICE_ID = "PRICE_ID";
    public static final String PRICE_ID_NAME = "PRICE_ID_NAME";
    public static final String THIRD_AREA_ID = "THIRD_AREA_ID";
    public static final String START_TIME = "START_TIME";
    public static final String END_TIME = "END_TIME";
    public static final String DAY_NUM = "DAY_NUM";
    private ImageView iv_back;
    private TextView tv_searchHint;
    private LinearLayout linear_search;
    private TextView tv_postJob;

    private int hotel_business_class;
    private String keyword;

    private HotelAndHouseActPresenter presenter;
    private HotelAndHouseListFragment hotelAndHouseListFragment;

    /**
     * 发车到此
     * @param context 上下文
     * @param hotel_business_class 分类 1酒店 2 房源
     * @param keyword 关键词
     * @param hotel_type 星级id
     * @param price_id 价格id
     * @param third_area_id 第三级区域id
     */
    public static void go(Context context,int hotel_business_class,String keyword,int hotel_type,String hotel_name,int price_id,String price_id_name,int third_area_id,String startTime,String endTime,int days) {
        Intent intent = new Intent(context,HotelAndHouseListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(HOTEL_BUSINESS_CLASS,hotel_business_class);
        bundle.putString(KEYWORD,keyword);
        bundle.putInt(HOTEL_TYPE,hotel_type);
        bundle.putString(HOTEL_TYPE_NAME,hotel_name);
        bundle.putInt(PRICE_ID,price_id);
        bundle.putString(PRICE_ID_NAME,price_id_name);
        bundle.putInt(THIRD_AREA_ID,third_area_id);
        bundle.putString(START_TIME,startTime);
        bundle.putString(END_TIME,endTime);
        bundle.putInt(DAY_NUM,days);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotelandhouse_list);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            hotel_business_class = extras.getInt(HOTEL_BUSINESS_CLASS);
            keyword = extras.getString(KEYWORD,"");
        }

        initView();
        initToolbar();

        hotelAndHouseListFragment = HotelAndHouseListFragment.getInstance(extras);
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, hotelAndHouseListFragment).commitNow();

        presenter = new HotelAndHouseActPresenter(this);
    }

    private void initToolbar() {
        handleBackEvent(iv_back);
        tv_searchHint.setText(TextUtils.isEmpty(keyword)?"搜索":keyword);
        tv_postJob.setVisibility(View.VISIBLE);
        if (hotel_business_class==1){
            tv_postJob.setText("酒店出租");
        } else {
            tv_postJob.setText("房源出租");
        }

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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_postJob:
                if (isLogin()) {
                    presenter.getAuthenState(getAccessToken());
                } else {
                    startAtvDonFinish(LoginActivity.class);
                }
                break;
            case R.id.linear_search:
                if(hotel_business_class==1) {
                    SearchItemActivity.go(this,15,0);
                } else {
                    SearchItemActivity.go(this,16,0);
                }
                break;
        }
    }

    @Override
    public void isAuthen(AuthenResultBean bean) {
        if (bean.getIs_authen()==1) {
            if (hotel_business_class==1) {
                startAtvDonFinish(PublishHotelActivity.class);
                hotelAndHouseListFragment.needRefresh = true;
            } else {
                startAtvDonFinish(PublishHouseActivity.class);
                hotelAndHouseListFragment.needRefresh = true;
            }
        } else if (bean.getIs_authen()==0){
            startAtvDonFinish(PersonalAuthenticationActivity.class);
        } else if (bean.getIs_authen()==2) {
            showTips("客服正在审核认证信息，审核完成后可发布信息",1);
        }
    }
}
