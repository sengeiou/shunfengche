package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.search.SearchResultActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.presenter.StayAndTravelSearchPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.StayAndTravelSearchResultBean;

/**
 * 描述：
 * 时间：2018/3/7/007
 * 作者：xjh
 */
public class StayAndTravelSearchActivity extends BaseActivity implements View.OnClickListener, StayAndTravelSearchView {


    private ImageView iv_back;
    private EditText et_search;
    private TextView tv_search;
    private TextView tv_travel_num;
    private LinearLayout linear_travel;
    private TextView tv_hotel_num;
    private LinearLayout linear_hotel;

    private StayAndTravelSearchPresenter presentr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stayandtravelsearch);
        initView();
        initToolbar();
        presentr = new StayAndTravelSearchPresenter(this);
    }

    private void initToolbar() {
        handleBackEvent(iv_back);
        tv_search.setOnClickListener(this);
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        et_search = (EditText) findViewById(R.id.et_search);
        tv_search = (TextView) findViewById(R.id.tv_search);
        tv_travel_num = (TextView) findViewById(R.id.tv_travel_num);
        linear_travel = (LinearLayout) findViewById(R.id.linear_travel);
        tv_hotel_num = (TextView) findViewById(R.id.tv_hotel_num);
        linear_hotel = (LinearLayout) findViewById(R.id.linear_hotel);

        linear_travel.setOnClickListener(this);
        linear_hotel.setOnClickListener(this);
    }

    private void submit() {
        String search = et_search.getText().toString().trim();
        if (TextUtils.isEmpty(search)) {
            showTips("请输入关键字",0);
            return;
        }

        presentr.search(search);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                submit();
                break;
            case R.id.linear_travel:
                SearchResultActivity.go(this,et_search.getText().toString().trim(),10,0);
                break;
            case R.id.linear_hotel:
                SearchResultActivity.go(this,et_search.getText().toString().trim(),15,0);
                break;
        }
    }

    @Override
    public void searchResult(StayAndTravelSearchResultBean bean) {
        tv_travel_num.setText(bean.getTravel_num()+"条信息");
        tv_hotel_num.setText(bean.getHotel_num()+"条信息");
    }
}
