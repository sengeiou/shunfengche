package com.windmillsteward.jukutech.activity.mine.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.activity.IntelligentFamilyDetailActivity;
import com.windmillsteward.jukutech.activity.mine.presenter.OrderFamilyDetailPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.OrderFamilyDetailBean;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.utils.DateUtil;

/**
 * 描述：
 * 时间：2018/3/2/002
 * 作者：xjh
 */
public class OrderFamilyDetailActivity extends BaseActivity implements OrderFamilyDetailView, View.OnClickListener {

    public static final String DETAIL_ID = "DETAIL_ID";
    public static final String POSITION = "POSITION";
    private static final int DELETE_CODE = 104;

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_status;
    private TextView tv_reminder;
    private TextView tv_title;
    private TextView tv_publish_personal;
    private TextView tv_phone;
    private TextView tv_order;
    private TextView tv_time;
    private TextView tv_price;
    private TextView tv_discount;
    private TextView tv_total;
    private TextView tv_hosted_id;
    private TextView tv_end_order;
    private LinearLayout linear_end;

    private OrderFamilyDetailPresenter presenter;
    private int detail_id;
    private int position;
    private OrderFamilyDetailBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderfamilydetaill);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            detail_id = extras.getInt(DETAIL_ID);
            position = extras.getInt(POSITION);
        } else {
            finish();
            return;
        }

        initView();
        initToolbar();
        presenter = new OrderFamilyDetailPresenter(this);
        presenter.initData(getAccessToken(), detail_id);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("订单详情");
    }


    @Override
    public void initDataSuccess(OrderFamilyDetailBean bean) {
        this.bean = bean;
        tv_status.setText(bean.getStatus_name());
        tv_title.setText(bean.getOrder_name());
        tv_publish_personal.setText(bean.getNickname());
        tv_phone.setText(bean.getMobile_phone());
        tv_order.setText(bean.getOrder_sn());
        tv_time.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getAdd_time()),"yyyy-MM-dd HH:mm:ss"));
        tv_price.setText("￥"+bean.getTotal_discount_fee());
        tv_discount.setText("￥"+bean.getTotal_coupon_fee());
        tv_total.setText("￥"+bean.getTotal_pay_fee());
        tv_hosted_id.setText(bean.getHosting_sn());

        tv_reminder.setVisibility(View.GONE);
        linear_end.setVisibility(View.GONE);

        int order_detail_status = bean.getOrder_detail_status();
        // 智慧家庭状态 1待确认,2已确认,3待检查,4已完成,5.已取消
        switch (order_detail_status) {
            case 1:  // 待确认显示取消订单
                linear_end.setVisibility(View.VISIBLE);
                tv_end_order.setText("取消订单");
                tv_end_order.setTextColor(ContextCompat.getColor(this,R.color.text_color_black));
                tv_end_order.setBackground(ContextCompat.getDrawable(this,R.drawable.shape_order_cancel_bg));
                tv_end_order.setOnClickListener(this);
                break;
            case 2: // 已经确认显示完成需求
                linear_end.setVisibility(View.VISIBLE);
                tv_end_order.setOnClickListener(this);
                break;
            case 3: // 待检测 显示催单
                tv_reminder.setVisibility(View.VISIBLE);
                tv_reminder.setOnClickListener(this);
                break;
            case 4:  // 已完成 显示删除
            case 5:  // 已取消 显示删除
                linear_end.setVisibility(View.VISIBLE);
                tv_end_order.setText("删除订单");
                tv_end_order.setTextColor(ContextCompat.getColor(this,R.color.text_color_black));
                tv_end_order.setBackground(ContextCompat.getDrawable(this,R.drawable.shape_order_cancel_bg));
                tv_end_order.setOnClickListener(this);
                break;
        }
    }

    @Override
    public void cancelOrderSuccess() {
//        presenter.initData(getAccessToken(), detail_id);
        finish();
    }

    @Override
    public void finishOrderSuccess() {
        presenter.initData(getAccessToken(), detail_id);
    }

    @Override
    public void deleteOrderSuccess() {
        Intent data = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(POSITION, position);
        data.putExtras(bundle);
        setResult(DELETE_CODE, data);
        finish();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_status = (TextView) findViewById(R.id.tv_status);
        tv_reminder = (TextView) findViewById(R.id.tv_reminder);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_publish_personal = (TextView) findViewById(R.id.tv_publish_personal);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_order = (TextView) findViewById(R.id.tv_order);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_discount = (TextView) findViewById(R.id.tv_discount);
        tv_total = (TextView) findViewById(R.id.tv_total);
        tv_hosted_id = (TextView) findViewById(R.id.tv_hosted_id);
        tv_end_order = (TextView) findViewById(R.id.tv_end_order);
        linear_end = (LinearLayout) findViewById(R.id.linear_end);
        tv_title.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_reminder:
                if (bean!=null) {
                    presenter.urgeSmartHomeOrder(getAccessToken(),detail_id);
                }
                break;
            case R.id.tv_end_order:
                if (bean!=null) {
                    int order_detail_status = bean.getOrder_detail_status();
                    if (order_detail_status==1) {  // 取消订单
                        new AlertDialog(this).builder()
                                .setTitle("提示")
                                .setMsg("您确定取消订单吗？")
                                .setCancelable(true)
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                    }
                                })
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        presenter.cancelOrder(getAccessToken(),detail_id);
                                    }
                                })
                                .show();
                    } else if (order_detail_status==2){  // 完成订单
                        new AlertDialog(this).builder()
                                .setTitle("提示")
                                .setMsg("您确定已完成该需求吗？")
                                .setCancelable(true)
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                    }
                                })
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        presenter.finishOrder(getAccessToken(),detail_id);
                                    }
                                })
                                .show();
                    } else if (order_detail_status==5) { // 删除
                        new AlertDialog(this).builder()
                                .setTitle("提示")
                                .setMsg("您确定删除订单吗")
                                .setCancelable(true)
                                .setNegativeButton("取消", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                    }
                                })
                                .setPositiveButton("确定", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        presenter.deleteOrder(getAccessToken(),detail_id);
                                    }
                                })
                                .show();
                    }
                }
                break;
            case R.id.tv_title:
                if (bean!=null) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(IntelligentFamilyDetailActivity.DETAIL_ID,bean.getRequire_id());
                    startAtvDonFinish(IntelligentFamilyDetailActivity.class, bundle);
                }
                break;
        }
    }
}
