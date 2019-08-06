package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.presenter.AfterSalesDetailActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.AfterSalesDetailBean;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.GlideUtil;

import java.util.List;

/**
 * 描述：
 * 时间：2018/4/22/022
 * 作者：xjh
 */
public class AfterSalesDetailActivity extends BaseActivity implements AfterSalesDetailActivityView{

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_refund_sn;
    private TextView tv_status;
    private ImageView iv_pic;
    private TextView tv_title;
    private TextView tv_model;
    private TextView tv_price;
    private TextView tv_number;
    private TextView tv_order_sn;
    private TextView tv_reason_name;
    private TextView tv_commodity_num;
    private TextView tv_refund_price;
    private TextView tv_refund_way;
    private TextView tv_user_remark;
    private TextView tv_store_remark;
    private ImageView iv_pic_1;
    private ImageView iv_iv_pic_2;
    private ImageView iv_iv_pic_3;
    private ImageView iv_close;
    private LinearLayout linear_step;
    private TextView tv_send;
    private TextView tv_look;
    private TextView tv_edit;
    private LinearLayout linear_pic;

    private AfterSalesDetailActivityPresenter presenter;
    private int record_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_sales_detail);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            record_id = extras.getInt(Define.INTENT_DATA);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();

        presenter = new AfterSalesDetailActivityPresenter(this);
        presenter.initData(getAccessToken(),record_id);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("退款详情");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_refund_sn = (TextView) findViewById(R.id.tv_refund_sn);
        tv_status = (TextView) findViewById(R.id.tv_status);
        iv_pic = (ImageView) findViewById(R.id.iv_pic);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_model = (TextView) findViewById(R.id.tv_model);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_number = (TextView) findViewById(R.id.tv_number);
        tv_order_sn = (TextView) findViewById(R.id.tv_order_sn);
        tv_reason_name = (TextView) findViewById(R.id.tv_reason_name);
        tv_commodity_num = (TextView) findViewById(R.id.tv_commodity_num);
        tv_refund_price = (TextView) findViewById(R.id.tv_refund_price);
        tv_refund_way = (TextView) findViewById(R.id.tv_refund_way);
        tv_user_remark = (TextView) findViewById(R.id.tv_user_remark);
        tv_store_remark = (TextView) findViewById(R.id.tv_store_remark);
        iv_pic_1 = (ImageView) findViewById(R.id.iv_pic_1);
        iv_iv_pic_2 = (ImageView) findViewById(R.id.iv_iv_pic_2);
        iv_iv_pic_3 = (ImageView) findViewById(R.id.iv_iv_pic_3);
        iv_close = (ImageView) findViewById(R.id.iv_close);
        linear_step = (LinearLayout) findViewById(R.id.linear_step);
        tv_send = (TextView) findViewById(R.id.tv_send);
        tv_look = (TextView) findViewById(R.id.tv_look);
        tv_edit = (TextView) findViewById(R.id.tv_edit);
        linear_pic = (LinearLayout) findViewById(R.id.linear_pic);
    }

    @Override
    public void initDataSuccess(final AfterSalesDetailBean bean) {
        tv_refund_sn.setText(bean.getRefund_sn());
        GlideUtil.show(this,bean.getCommodity_cover_picture(),iv_pic);
        tv_title.setText(bean.getCommodity_title());
        tv_model.setText("型号："+bean.getCommodity_model_name());
        tv_price.setText("￥"+bean.getCommodity_price());
        tv_number.setText("X"+bean.getCommodity_num());
        tv_order_sn.setText("订单编号："+bean.getOrder_sn());
        tv_reason_name.setText("退款原因："+bean.getReason_name());
        tv_commodity_num.setText("退货数量："+bean.getCommodity_num());
        tv_refund_price.setText("退款金额："+bean.getPrice());
        tv_refund_way.setText("退款方式："+((bean.getRefund_way()==1)?"退到钱包":"原路退回"));
        tv_store_remark.setText(bean.getStore_remark());
        tv_user_remark.setText(bean.getUser_remark());

        List<String> credentials_urls = bean.getCredentials_urls();
        if (credentials_urls!=null) {
            if (credentials_urls.size()>0) {
                GlideUtil.show(this,credentials_urls.get(0),iv_pic_1);
            } else if (credentials_urls.size()>1) {
                GlideUtil.show(this,credentials_urls.get(0),iv_pic_1);
                GlideUtil.show(this,credentials_urls.get(1),iv_iv_pic_2);
            } else if (credentials_urls.size()>2) {
                GlideUtil.show(this,credentials_urls.get(0),iv_pic_1);
                GlideUtil.show(this,credentials_urls.get(1),iv_iv_pic_2);
                GlideUtil.show(this,credentials_urls.get(2),iv_iv_pic_3);
            }
        } else {
            linear_pic.setVisibility(View.GONE);
        }
        // 售后状态 1：退款中，2：退款成功，3：拒绝退款，4：退款完成，5：申请退货中，6：待用户发货，7：用户已发货，8：退货完成，9：拒绝退货，10：取消
        switch (bean.getStatus()) {
            case 1:
                tv_status.setText("退款中");
                View view_price_end_5 = LayoutInflater.from(this).inflate(R.layout.view_after_sales_detail_price_apply, linear_step, false);
                TextView tv_time_5 = view_price_end_5.findViewById(R.id.tv_time);
                tv_time_5.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getAdd_time()),"yyyy-MM-dd HH:mm:ss"));
                linear_step.removeAllViews();
                linear_step.addView(view_price_end_5);
                tv_send.setVisibility(View.GONE);
                tv_look.setVisibility(View.GONE);
                tv_edit.setVisibility(View.GONE);
                tv_commodity_num.setVisibility(View.GONE);
                linear_pic.setVisibility(View.GONE);
                break;
            case 2:
                tv_status.setText("退款成功");
                View view_price_end = LayoutInflater.from(this).inflate(R.layout.view_after_sales_detail_price_end, linear_step, false);
                TextView tv_time_6 = view_price_end.findViewById(R.id.tv_time);
                TextView tv_time_6_1 = view_price_end.findViewById(R.id.tv_time_1);
                TextView tv_time_6_2 = view_price_end.findViewById(R.id.tv_time_2);
                tv_time_6.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getAdd_time()),"yyyy-MM-dd HH:mm:ss"));
                tv_time_6_1.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getProcessing_time()),"yyyy-MM-dd HH:mm:ss"));
                tv_time_6_2.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getFinish_time()),"yyyy-MM-dd HH:mm:ss"));
                linear_step.removeAllViews();
                linear_step.addView(view_price_end);
                tv_send.setVisibility(View.GONE);
                tv_look.setVisibility(View.GONE);
                tv_edit.setVisibility(View.GONE);
                tv_commodity_num.setVisibility(View.GONE);
                linear_pic.setVisibility(View.GONE);
                break;
            case 3:
                tv_status.setText("拒绝退款");
                View view_price_end_7 = LayoutInflater.from(this).inflate(R.layout.view_after_sales_detail_price_not, linear_step, false);
                TextView tv_time_7 = view_price_end_7.findViewById(R.id.tv_time);
                TextView tv_time_7_1 = view_price_end_7.findViewById(R.id.tv_time_1);
                TextView tv_time_7_2 = view_price_end_7.findViewById(R.id.tv_time_2);
                tv_time_7.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getAdd_time()),"yyyy-MM-dd HH:mm:ss"));
                tv_time_7_1.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getRefused_time()),"yyyy-MM-dd HH:mm:ss"));
                tv_time_7_2.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getFinish_time()),"yyyy-MM-dd HH:mm:ss"));
                linear_step.removeAllViews();
                linear_step.addView(view_price_end_7);
                tv_send.setVisibility(View.GONE);
                tv_look.setVisibility(View.GONE);
                tv_edit.setVisibility(View.GONE);
                tv_commodity_num.setVisibility(View.GONE);
                linear_pic.setVisibility(View.GONE);
                break;
            case 4:
                tv_status.setText("退款完成");
                View view_price_end_ = LayoutInflater.from(this).inflate(R.layout.view_after_sales_detail_price_end, linear_step, false);
                TextView tv_time_6_ = view_price_end_.findViewById(R.id.tv_time);
                TextView tv_time_6_1_ = view_price_end_.findViewById(R.id.tv_time_1);
                TextView tv_time_6_2_ = view_price_end_.findViewById(R.id.tv_time_2);
                tv_time_6_.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getAdd_time()),"yyyy-MM-dd HH:mm:ss"));
                tv_time_6_1_.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getProcessing_time()),"yyyy-MM-dd HH:mm:ss"));
                tv_time_6_2_.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getFinish_time()),"yyyy-MM-dd HH:mm:ss"));
                linear_step.removeAllViews();
                linear_step.addView(view_price_end_);
                tv_send.setVisibility(View.GONE);
                tv_look.setVisibility(View.GONE);
                tv_edit.setVisibility(View.GONE);
                tv_commodity_num.setVisibility(View.GONE);
                linear_pic.setVisibility(View.GONE);
            case 5:
                tv_status.setText("申请退货中");
                View view_apply = LayoutInflater.from(this).inflate(R.layout.view_after_sales_detail_apply, linear_step, false);
                TextView tv_time = view_apply.findViewById(R.id.tv_time);
                tv_time.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getAdd_time()),"yyyy-MM-dd HH:mm:ss"));
                linear_step.removeAllViews();
                linear_step.addView(view_apply);
                tv_send.setVisibility(View.GONE);
                tv_look.setVisibility(View.GONE);
                tv_edit.setVisibility(View.GONE);
                break;
            case 6:
                tv_status.setText("待用户发货");
                View view_send = LayoutInflater.from(this).inflate(R.layout.view_after_sales_detail_send, linear_step, false);
                TextView tv_time_1_1 = view_send.findViewById(R.id.tv_time);
                TextView tv_time_1_2 = view_send.findViewById(R.id.tv_time_1);
                tv_time_1_1.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getAdd_time()),"yyyy-MM-dd HH:mm:ss"));
                if (bean.getProcessing_time()!=0) {
                    tv_time_1_2.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getProcessing_time()),"yyyy-MM-dd HH:mm:ss"));
                }
                linear_step.removeAllViews();
                linear_step.addView(view_send);
                tv_send.setVisibility(View.VISIBLE);
                tv_look.setVisibility(View.GONE);
                tv_edit.setVisibility(View.GONE);
                break;
            case 7:
                tv_status.setText("用户已发货");
                View view_sended = LayoutInflater.from(this).inflate(R.layout.view_after_sales_detail_sended, linear_step, false);
                TextView tv_time_2_1 = view_sended.findViewById(R.id.tv_time);
                TextView tv_time_2_2 = view_sended.findViewById(R.id.tv_time_1);
                TextView tv_time_2_3 = view_sended.findViewById(R.id.tv_time_2);
                tv_time_2_1.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getAdd_time()),"yyyy-MM-dd HH:mm:ss"));
                tv_time_2_2.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getProcessing_time()),"yyyy-MM-dd HH:mm:ss"));
                tv_time_2_3.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getUser_deliver_time()),"yyyy-MM-dd HH:mm:ss"));
                linear_step.removeAllViews();
                linear_step.addView(view_sended);
                tv_send.setVisibility(View.GONE);
                tv_look.setVisibility(View.VISIBLE);
                tv_edit.setVisibility(View.VISIBLE);
                break;
            case 8:
                tv_status.setText("退货完成");
                View view_sended_4 = LayoutInflater.from(this).inflate(R.layout.view_after_sales_detail_return_end, linear_step, false);
                TextView tv_time_4_1 = view_sended_4.findViewById(R.id.tv_time);
                TextView tv_time_4_2 = view_sended_4.findViewById(R.id.tv_time_1);
                TextView tv_time_4_3 = view_sended_4.findViewById(R.id.tv_time_2);
                TextView tv_time_4_4 = view_sended_4.findViewById(R.id.tv_time_3);
                TextView tv_time_4_5 = view_sended_4.findViewById(R.id.tv_time_4);
                tv_time_4_1.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getAdd_time()),"yyyy-MM-dd HH:mm:ss"));
                tv_time_4_2.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getProcessing_time()),"yyyy-MM-dd HH:mm:ss"));
                tv_time_4_3.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getUser_deliver_time()),"yyyy-MM-dd HH:mm:ss"));
                tv_time_4_4.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getReceiving_time()),"yyyy-MM-dd HH:mm:ss"));
                tv_time_4_5.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getFinish_time()),"yyyy-MM-dd HH:mm:ss"));
                linear_step.removeAllViews();
                linear_step.addView(view_sended_4);
                tv_send.setVisibility(View.GONE);
                tv_look.setVisibility(View.VISIBLE);
                tv_edit.setVisibility(View.GONE);
                break;
            case 9:
                tv_status.setText("拒绝退货");
                View view_sended_9 = LayoutInflater.from(this).inflate(R.layout.view_after_sales_detail_return_not, linear_step, false);
                TextView tv_time_9_1 = view_sended_9.findViewById(R.id.tv_time);
                TextView tv_time_9_2 = view_sended_9.findViewById(R.id.tv_time_1);
                TextView tv_time_9_3 = view_sended_9.findViewById(R.id.tv_time_2);
                tv_time_9_1.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getAdd_time()),"yyyy-MM-dd HH:mm:ss"));
                tv_time_9_2.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getRefused_time()),"yyyy-MM-dd HH:mm:ss"));
                tv_time_9_3.setText(DateUtil.StampTimeToDate(String.valueOf(bean.getFinish_time()),"yyyy-MM-dd HH:mm:ss"));
                linear_step.removeAllViews();
                linear_step.addView(view_sended_9);
                tv_send.setVisibility(View.GONE);
                tv_look.setVisibility(View.GONE);
                tv_edit.setVisibility(View.GONE);
                tv_commodity_num.setVisibility(View.GONE);
                linear_pic.setVisibility(View.GONE);
                break;
            case 10:
                tv_status.setText("已取消");
                linear_step.removeAllViews();
                tv_send.setVisibility(View.GONE);
                tv_look.setVisibility(View.GONE);
                tv_edit.setVisibility(View.GONE);
                tv_commodity_num.setVisibility(View.GONE);
                linear_pic.setVisibility(View.GONE);
                break;
        }

        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(Define.INTENT_DATA,record_id);
                bundle.putString(Define.INTENT_DATA_TWO,bean.getOrder_sn());
                bundle.putString(Define.INTENT_DATA_THREE,bean.getAddress());
                bundle.putString(Define.INTENT_DATA_FOUR,bean.getCommodity_model_name());
                bundle.putString(Define.INTENT_TYPE,"发货");
                startAtvDonFinish(RefundDeliveryActivity.class, bundle);
            }
        });
        tv_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(Define.INTENT_DATA,bean.getOrder_sn());
                bundle.putString(Define.INTENT_DATA_TWO,bean.getLogistics_single_number());
                startAtvDonFinish(LogisticsActivity.class, bundle);
            }
        });
        tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(Define.INTENT_DATA,record_id);
                bundle.putString(Define.INTENT_DATA_TWO,bean.getOrder_sn());
                bundle.putString(Define.INTENT_DATA_THREE,bean.getAddress());
                bundle.putString(Define.INTENT_DATA_FOUR,bean.getCommodity_model_name());
                bundle.putString(Define.INTENT_TYPE,"修改发货信息");
                startAtvDonFinish(RefundDeliveryActivity.class, bundle);
            }
        });

    }
}
