package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.PublishSuccessActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.interfaces.Define;

/**
 * 描述：发布成功界面
 * 时间：2018/1/15
 * 作者：xjh
 */

public class PublishSuccessActivity extends BaseActivity implements View.OnClickListener, PublishSuccessActivityView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private TextView toolbar_tv_right;
    private TextView tv_return;
    private ImageView toolbar_iv_right;
    private TextView tv_title;
    private TextView tv_msg;

    private PublishSuccessActivityPresenter presenter;

    /**
     *
     * @param type  type
     *              1.job_check 职位
     *              2.resume_check 简历
     *              3.thought_require_check 思想智库
     *              4.smart_require_check 智慧家庭
     *              5.house_sell_check 卖房
     *              6.hotel_check 酒店
     *              7.travel_check 旅游
     *              8.insurance_check 大健康
     *              9.capital_check 资本管理-理财
     *              10.sale_car_check 卖车
     *              11.buy_car_check 买车
     *              12.rent_car_check 租车管理
     *              13.loan_check 资本管理-放贷
     *              14.house_buy_check 买房
     *              15.house_rent_check 出租
     *              16.house_renting_check 求租
     */
    public static void go(Activity activity, int type) {
        Intent intent = new Intent(activity,PublishSuccessActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(Define.INTENT_DATA,type);
        intent.putExtras(bundle);
        activity.startActivity(intent);
        activity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_releasesuccess);
        initView();
        initToolbar();

        presenter = new PublishSuccessActivityPresenter(this);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            presenter.initData(extras.getInt(Define.INTENT_DATA));
        }
    }

    private void initToolbar() {
        toolbar_iv_back.setVisibility(View.GONE);
        toolbar_iv_title.setVisibility(View.GONE);
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_return = (TextView) findViewById(R.id.tv_return);

        tv_return.setOnClickListener(this);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_msg = (TextView) findViewById(R.id.tv_msg);

        tv_title.setText("已发布成功！");
        tv_msg.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_return:
                finish();
                break;
        }
    }

    @Override
    public void initDataSuccess(boolean is_check) {
        if (!is_check) {
            tv_title.setText("已发布成功！");
            tv_msg.setVisibility(View.INVISIBLE);
        } else {
            tv_title.setText("已发布成功！请等待审核");
            tv_msg.setVisibility(View.VISIBLE);
        }
    }
}
