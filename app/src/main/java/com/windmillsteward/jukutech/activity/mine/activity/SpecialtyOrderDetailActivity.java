package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyDetailActivity;
import com.windmillsteward.jukutech.activity.home.specialty.activity.StoreGoodsListActivity;
import com.windmillsteward.jukutech.activity.mine.adapter.SpecialtyOrderDetailGridViewAdapter;
import com.windmillsteward.jukutech.activity.mine.presenter.SpecialtyOrderDetailActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.SpecialtyOrderDetailBean;
import com.windmillsteward.jukutech.customview.dialog.AlertDialog;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.CountDownUtils;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.TimeUtils;

import java.util.List;
import java.util.Locale;

/**
 *
 * Created by Administrator on 2018/4/19 0019.
 */

public class SpecialtyOrderDetailActivity extends BaseActivity implements SpecialtyOrderDetailActivityView, CountDownUtils.OnCountDownUpdateListener, View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_wait_send;
    private TextView tv_wait_pay;
    private TextView tv_deliver_name;
    private TextView tv_deliver_time;
    private LinearLayout linear_logistics;
    private LinearLayout linear_bottom;
    private TextView tv_user_info;
    private TextView tv_address;
    private TextView tv_title;
    private RecyclerView mRecyclerView;
    private TextView tv_delete;
    private TextView tv_close;
    private TextView tv_look;
    private TextView tv_continue;
    private TextView tv_sure;
    private TextView tv_total_orig_fee;
    private TextView tv_store_change_fee;
    private TextView tv_total_coupon_fee;
    private TextView tv_freight_fee;
    private TextView tv_total_pay_fee;
    private TextView tv_order_sn;
    private TextView tv_add_time;
    private TextView tv_payment_time;
    private TextView tv_deliver_time_;

    private SpecialtyOrderDetailActivityPresenter presenter;
    private int order_id;
    private boolean timeOut;
    private CountDownUtils countDownUtils;
    private long leftTime;
    private SpecialtyOrderDetailBean detailBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty_order_detail);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            order_id = extras.getInt(Define.INTENT_DATA);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        presenter = new SpecialtyOrderDetailActivityPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.initData(getAccessToken(),order_id);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("订单详情");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_wait_send = (TextView) findViewById(R.id.tv_wait_send);
        tv_user_info = (TextView) findViewById(R.id.tv_user_info);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_title = (TextView) findViewById(R.id.tv_title);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        tv_delete = (TextView) findViewById(R.id.tv_delete);
        tv_close = (TextView) findViewById(R.id.tv_close);
        tv_look = (TextView) findViewById(R.id.tv_look);
        tv_continue = (TextView) findViewById(R.id.tv_continue);
        tv_sure = (TextView) findViewById(R.id.tv_sure);
        tv_total_orig_fee = (TextView) findViewById(R.id.tv_total_orig_fee);
        tv_store_change_fee = (TextView) findViewById(R.id.tv_store_change_fee);
        tv_total_coupon_fee = (TextView) findViewById(R.id.tv_total_coupon_fee);
        tv_freight_fee = (TextView) findViewById(R.id.tv_freight_fee);
        tv_total_pay_fee = (TextView) findViewById(R.id.tv_total_pay_fee);
        tv_order_sn = (TextView) findViewById(R.id.tv_order_sn);
        tv_add_time = (TextView) findViewById(R.id.tv_add_time);
        tv_payment_time = (TextView) findViewById(R.id.tv_payment_time);
        tv_deliver_time_ = (TextView) findViewById(R.id.tv_deliver_time_);
        tv_wait_pay = (TextView) findViewById(R.id.tv_wait_pay);
        tv_deliver_name = (TextView) findViewById(R.id.tv_deliver_name);
        tv_deliver_time = (TextView) findViewById(R.id.tv_deliver_time);
        linear_logistics = (LinearLayout) findViewById(R.id.linear_logistics);
        linear_bottom = (LinearLayout) findViewById(R.id.linear_bottom);

        tv_title.setOnClickListener(this);
    }

    @Override
    public void initDataSuccess(final SpecialtyOrderDetailBean bean) {
        detailBean = bean;
        final int order_status = bean.getOrder_status();
        tv_user_info.setText("收货人："+bean.getUser_name()+bean.getMobile_phone());
        tv_address.setText("收货地址："+bean.getAddress());
        tv_title.setText(bean.getStore_name());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SpecialtyOrderDetailGridViewAdapter adapter = new SpecialtyOrderDetailGridViewAdapter(bean.getCommodity_list(), bean.getOrder_status());
        mRecyclerView.setAdapter(adapter);
        tv_total_orig_fee.setText("￥"+bean.getTotal_orig_fee());
        tv_store_change_fee.setText("-￥"+bean.getStore_change_fee());
        tv_total_coupon_fee.setText("-￥"+bean.getTotal_coupon_fee());
        tv_freight_fee.setText("￥"+bean.getFreight_fee());
        tv_total_pay_fee.setText("￥"+bean.getTotal_pay_fee());
        tv_order_sn.setText("订单号："+bean.getOrder_sn());
        tv_add_time.setText("下单时间："+ DateUtil.StampTimeToDate(String.valueOf(bean.getAdd_time()),"yyyy-MM-dd HH:mm:ss"));
        if (bean.getPayment_time()==0) {
            tv_payment_time.setVisibility(View.GONE);
        } else {
            tv_payment_time.setText("付款时间："+ DateUtil.StampTimeToDate(String.valueOf(bean.getPayment_time()),"yyyy-MM-dd HH:mm:ss"));
        }
        if (bean.getDeliver_time()==0) {
            tv_deliver_time_.setVisibility(View.GONE);
        } else {
            tv_deliver_time_.setText("发货时间："+DateUtil.StampTimeToDate(String.valueOf(bean.getDeliver_time()),"yyyy-MM-dd HH:mm:ss"));
        }

        // 【1：待付款，2：待发货，3：待收货，4：已完成，5：已完成，不能申请售后，6：已取消】
        switch (order_status) {
            case 1:
                tv_wait_send.setVisibility(View.VISIBLE);
                tv_wait_send.setText("等待买家付款，需付款￥"+bean.getTotal_pay_fee());
                tv_wait_pay.setVisibility(View.VISIBLE);
                linear_logistics.setVisibility(View.GONE);
                // 等待付款显示关闭和继续支付
                linear_bottom.setVisibility(View.VISIBLE);
                tv_delete.setVisibility(View.GONE);
                tv_close.setVisibility(View.VISIBLE);
                tv_look.setVisibility(View.GONE);
                tv_continue.setVisibility(View.VISIBLE);
                tv_sure.setVisibility(View.GONE);

                // 处理付款时间
                leftTime = bean.getAdd_time()*1000L + bean.getClose_day()*60*1000L;
                if (leftTime - System.currentTimeMillis() <= 0) {
                    tv_wait_pay.setText("支付时间超时，请重新下单！");
                    timeOut = true;
                } else {
                    tv_wait_pay.setText(String.format(Locale.getDefault(), "剩余支付时间：%s，超时将取消订单", TimeUtils.formatDuring(leftTime, TimeUtils.FORMAT_DURING_TYPE_ALL)));
                    startCountDown();
                }
                break;
            case 2:
                tv_wait_send.setVisibility(View.VISIBLE);
                tv_wait_send.setText("等待商家发货");
                linear_logistics.setVisibility(View.GONE);
                tv_wait_pay.setVisibility(View.GONE);
                // 代发货不能处理
                linear_bottom.setVisibility(View.GONE);
                break;
            case 3:
                tv_wait_send.setVisibility(View.VISIBLE);
                tv_wait_send.setText("商家已发货");
                tv_wait_pay.setVisibility(View.GONE);
                linear_logistics.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(bean.getLogistics_single_number())) {
                    linear_logistics.setVisibility(View.GONE);
                } else {
                    linear_logistics.setVisibility(View.VISIBLE);
                    presenter.initLogisticsInfo(getAccessToken(),bean.getOrder_sn(),bean.getLogistics_single_number());
                }
                // 待收货显示显示查看物流和确认收货
                linear_bottom.setVisibility(View.VISIBLE);
                tv_delete.setVisibility(View.GONE);
                tv_close.setVisibility(View.GONE);
                tv_look.setVisibility(View.VISIBLE);
                tv_continue.setVisibility(View.GONE);
                tv_sure.setVisibility(View.VISIBLE);
                break;
            case 4:
                tv_wait_send.setVisibility(View.VISIBLE);
                tv_wait_send.setText("订单已完成");
                tv_wait_pay.setVisibility(View.GONE);
                linear_logistics.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(bean.getLogistics_single_number())) {
                    linear_logistics.setVisibility(View.GONE);
                } else {
                    linear_logistics.setVisibility(View.VISIBLE);
                    presenter.initLogisticsInfo(getAccessToken(),bean.getOrder_sn(),bean.getLogistics_single_number());
                }
                // 显示删除订单
                linear_bottom.setVisibility(View.VISIBLE);
                tv_delete.setVisibility(View.VISIBLE);
                tv_close.setVisibility(View.GONE);
                tv_look.setVisibility(View.GONE);
                tv_continue.setVisibility(View.GONE);
                tv_sure.setVisibility(View.GONE);
                break;
            case 5:
                tv_wait_send.setVisibility(View.VISIBLE);
                tv_wait_send.setText("订单已完成");
                tv_wait_pay.setVisibility(View.GONE);
                if (TextUtils.isEmpty(bean.getLogistics_single_number())) {
                    linear_logistics.setVisibility(View.GONE);
                } else {
                    linear_logistics.setVisibility(View.VISIBLE);
                    presenter.initLogisticsInfo(getAccessToken(),bean.getOrder_sn(),bean.getLogistics_single_number());
                }
                // 显示删除订单
                linear_bottom.setVisibility(View.VISIBLE);
                tv_delete.setVisibility(View.VISIBLE);
                tv_close.setVisibility(View.GONE);
                tv_look.setVisibility(View.GONE);
                tv_continue.setVisibility(View.GONE);
                tv_sure.setVisibility(View.GONE);
                break;
            case 6:
                tv_wait_send.setVisibility(View.VISIBLE);
                tv_wait_send.setText("订单已取消");
                tv_wait_pay.setVisibility(View.GONE);
                if (TextUtils.isEmpty(bean.getLogistics_single_number())) {
                    linear_logistics.setVisibility(View.GONE);
                } else {
                    linear_logistics.setVisibility(View.VISIBLE);
                    presenter.initLogisticsInfo(getAccessToken(),bean.getOrder_sn(),bean.getLogistics_single_number());
                }
                // 显示删除订单
                linear_bottom.setVisibility(View.VISIBLE);
                tv_delete.setVisibility(View.VISIBLE);
                tv_close.setVisibility(View.GONE);
                tv_look.setVisibility(View.GONE);
                tv_continue.setVisibility(View.GONE);
                tv_sure.setVisibility(View.GONE);
                break;
        }
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog(SpecialtyOrderDetailActivity.this).builder()
                        .setTitle("提示")
                        .setMsg("确定删除该订单？")
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        })
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                presenter.deleteOrder(getAccessToken(), bean.getOrder_id());
                            }
                        }).show();
            }
        });
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog(SpecialtyOrderDetailActivity.this).builder()
                        .setTitle("提示")
                        .setMsg("确定关闭该订单？")
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        })
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                presenter.deleteOrder(getAccessToken(), bean.getOrder_id());
                            }
                        }).show();
            }
        });
        tv_look.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(Define.INTENT_DATA,bean.getOrder_sn());
                bundle.putString(Define.INTENT_DATA_TWO,bean.getLogistics_single_number());
                startAtvDonFinish(LogisticsActivity.class, bundle);
            }
        });
        tv_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timeOut) {
                    showTips("支付时间超时，订单已取消!",0);
                    return;
                }
                String total_pay_fee = bean.getTotal_pay_fee();
                String order_sn = bean.getOrder_sn();
                String store_name = bean.getStore_name();
                Bundle bundle = new Bundle();
                bundle.putString(Define.INTENT_DATA,total_pay_fee);
                bundle.putString(Define.INTENT_DATA_TWO,order_sn);
                bundle.putString(Define.INTENT_DATA_THREE,store_name);
                startAtvDonFinish(ShoppingContinuePayActivity.class, bundle);
            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog(SpecialtyOrderDetailActivity.this).builder()
                        .setTitle("提示")
                        .setMsg("确定收货吗？")
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        })
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                presenter.deleteOrder(getAccessToken(), bean.getOrder_id());
                            }
                        }).show();
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                List<SpecialtyOrderDetailBean.CommodityListBean> commodity_list = bean.getCommodity_list();
                switch (view.getId()) {
                    case R.id.tv_refund:
                        if (order_status==2) {
                            Bundle bundle = new Bundle();
                            SpecialtyOrderDetailBean.CommodityListBean listBean = commodity_list.get(position);
                            bundle.putString(Define.INTENT_DATA, JSON.toJSONString(listBean));
                            bundle.putString(Define.INTENT_DATA_TWO, bean.getOrder_sn());
                            bundle.putInt(Define.INTENT_DATA_THREE, bean.getOrder_id());
                            startAtvDonFinish(ApplyRefundActivity.class, bundle);
                        } else {
                            Bundle bundle = new Bundle();
                            SpecialtyOrderDetailBean.CommodityListBean listBean = commodity_list.get(position);
                            bundle.putString(Define.INTENT_DATA, JSON.toJSONString(listBean));
                            bundle.putString(Define.INTENT_DATA_TWO, bean.getOrder_sn());
                            bundle.putInt(Define.INTENT_DATA_THREE, bean.getOrder_id());
                            startAtvDonFinish(ApplySaleRefundActivity.class, bundle);
                        }
                        break;
                    case R.id.tv_title:
                        Bundle bundle = new Bundle();
                        bundle.putInt(Define.INTENT_DATA,commodity_list.get(position).getCommodity_id());
                        startAtvDonFinish(SpecialtyDetailActivity.class, bundle);
                        break;
                }
            }
        });
    }

    private void startCountDown() {
        if (countDownUtils == null) {
            Long aLong = (leftTime - System.currentTimeMillis()) / 1000;
            int count = Integer.valueOf(aLong + "");
            countDownUtils = new CountDownUtils(count, 1, 1);
            countDownUtils.setOnCountDownUpdateListener(this);
        }
        countDownUtils.startCountDown();
    }

    @Override
    public void loadLogisticsInfoSuccess(String msg, String time) {
        tv_deliver_name.setText(msg);
        tv_deliver_time.setText(time);
    }

    @Override
    public void deleteOrderSuccess() {
        presenter.initData(getAccessToken(),order_id);
    }

    @Override
    public void closeOrderSuccess() {
        presenter.initData(getAccessToken(),order_id);
    }

    @Override
    public void confirmOrderSuccess() {
        presenter.initData(getAccessToken(),order_id);
    }

    @Override
    public void countDownUpdate(final boolean isEnd, final int number) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_wait_pay.setText(String.format(Locale.getDefault(), "剩余支付时间：%s，超时将取消订单", TimeUtils.getLeftTime(number)));
                if (isEnd) {
                    countDownUtils.stopCountDown();
                    timeOut = true;
                    tv_wait_pay.setText("支付时间超时，订单已取消!");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownUtils!=null) {
            countDownUtils.stopCountDown();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title:
                Bundle bundle = new Bundle();
                bundle.putInt(Define.INTENT_DATA,detailBean.getStore_id());
                bundle.putString(Define.INTENT_DATA_TWO,detailBean.getStore_name());
                startAtvDonFinish(StoreGoodsListActivity.class,bundle);
                break;
        }
    }
}
