package com.windmillsteward.jukutech.activity.home.carservice.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.carservice.fragment.CarListFragment;
import com.windmillsteward.jukutech.base.BaseActivity;

/**
 * 描述：
 * 时间：2018/3/28/028
 * 作者：xjh
 */
public class CarListActivity extends BaseActivity {

    public static final String BRAND_ID = "BRAND_ID";
    public static final String BRAND_NAME = "BRAND_NAME";
    public static final String PRICE_ID = "PRICE_ID";
    public static final String PRICE_NAME = "PRICE_NAME";
    public static final String KEYWORD = "KEYWORD";

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;

    // 价格id
    private int price_id;
    private String price_name;
    private String keyword;
    // 品牌id
    private int brand_id;
    private String brand_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);
        initView();
        initToolbar();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            brand_id = extras.getInt(BRAND_ID);
            brand_name = extras.getString(BRAND_NAME);
            price_id = extras.getInt(PRICE_ID);
            price_name = extras.getString(PRICE_NAME);
            keyword = extras.getString(KEYWORD);
        }

        setParamInt(R.id.fl_content);
        startFragment(null, CarListFragment.getInstance(brand_id, brand_name, price_id, price_name, keyword));
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
    }

    private void initToolbar(){
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("车辆");
    }
}
