package com.windmillsteward.jukutech.activity.home.specialty.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.specialty.fragment.GoodsListFragment;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.interfaces.Define;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public class GoodsListActivity extends BaseActivity {

    private ImageView iv_back;
    private TextView tv_searchHint;
    private LinearLayout linear_search;
    private TextView tv_postJob;
    private FrameLayout fl_content;

    private int commodity_category_id;
    private int store_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            commodity_category_id = extras.getInt(Define.INTENT_DATA);
            store_id = extras.getInt(Define.INTENT_DATA_TWO);
        }
        initView();
        initToolbar();

        setParamInt(R.id.fl_content);
        startFragment(null, GoodsListFragment.getInstance(commodity_category_id,store_id));
    }

    private void initToolbar() {
        handleBackEvent(iv_back);
        tv_searchHint.setText("搜索商品");
    }


    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_searchHint = (TextView) findViewById(R.id.tv_searchHint);
        linear_search = (LinearLayout) findViewById(R.id.linear_search);
        tv_postJob = (TextView) findViewById(R.id.tv_postJob);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
    }
}
