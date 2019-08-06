package com.windmillsteward.jukutech.activity.shoppingcart.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.shoppingcart.adapter.AddOrderListActivityAdapter;
import com.windmillsteward.jukutech.activity.shoppingcart.presenter.AddOrderListActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.AddOrderListBean;
import com.windmillsteward.jukutech.bean.AddressListBean;
import com.windmillsteward.jukutech.bean.BeforeAddOrderRequest;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class AddOrderListActivity extends BaseActivity implements AddOrderListActivityView, View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private RecyclerView mRecyclerView;
    private TextView tv_price;
    private TextView tv_submit;

    private TextView tv_ad_area;
    private TextView tv_user_info;
    private TextView tv_default;
    private TextView tv_address;
    private LinearLayout linear_default;

    private AddOrderListActivityPresenter presenter;
    private BeforeAddOrderRequest request;
    private AddOrderListActivityAdapter adapter;
    private List<AddOrderListBean.OrderListBean> list;
    private int address_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addorder_list);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String json = extras.getString(Define.INTENT_DATA);
            request = JSON.parseObject(json, BeforeAddOrderRequest.class);
        }
        if (request == null) {
            finish();
            return;
        }
        initView();
        initToolbar();
        initRecyclerView();
        presenter = new AddOrderListActivityPresenter(this);
        request.setAddress_id(address_id);
        presenter.initData(getAccessToken(), request.getCart_id(), request.getAddress_id(), request.getListBeans());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode == 200 ) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                String json  = extras.getString(Define.INTENT_DATA);
                AddressListBean.ListBean bean = JSON.parseObject(json, AddressListBean.ListBean.class);
                if (bean!=null) {
                    linear_default.setVisibility(View.VISIBLE);
                    tv_ad_area.setVisibility(View.GONE);
                    tv_user_info.setText("收货人: "+bean.getUser_name()+" "+bean.getMobile_phone());
                    tv_address.setText("收货地址: "+bean.getTotal_address_name());
                    if (bean.getIs_default()==1) {
                        tv_default.setVisibility(View.VISIBLE);
                    } else {
                        tv_default.setVisibility(View.GONE);
                    }
                    address_id = bean.getAddress_id();
                    request.setAddress_id(address_id);
                    presenter.initData(getAccessToken(), request.getCart_id(), request.getAddress_id(), request.getListBeans());
                }
            }
        } else if (requestCode==101 && resultCode == 200) {
            startAtvAndFinish(ShoppingSuccessActivity.class);
        }
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new AddOrderListActivityAdapter(list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        View header = LayoutInflater.from(this).inflate(R.layout.header_add_order, mRecyclerView, false);
        tv_ad_area = header.findViewById(R.id.tv_add_area);
        tv_user_info = header.findViewById(R.id.tv_user_info);
        tv_default = header.findViewById(R.id.tv_default);
        tv_address = header.findViewById(R.id.tv_address);
        linear_default = header.findViewById(R.id.linear_default);
        tv_ad_area.setOnClickListener(this);
        linear_default.setOnClickListener(this);
        adapter.addHeaderView(header);

        // 判断本地有没有默认的
        String default_address_json = Hawk.get(Define.DEFAULT_SHOPPING_ADDRESS);
        if (!TextUtils.isEmpty(default_address_json)) {
            AddressListBean.ListBean bean = JSON.parseObject(default_address_json, AddressListBean.ListBean.class);
            if (bean!=null) {
                linear_default.setVisibility(View.VISIBLE);
                tv_ad_area.setVisibility(View.GONE);
                tv_user_info.setText("收货人: "+bean.getUser_name()+" "+bean.getMobile_phone());
                tv_address.setText("收货地址: "+bean.getTotal_address_name());
                if (bean.getIs_default()==1) {
                    tv_default.setVisibility(View.VISIBLE);
                } else {
                    tv_default.setVisibility(View.GONE);
                }
                address_id = bean.getAddress_id();
            } else {
                linear_default.setVisibility(View.GONE);
                tv_ad_area.setVisibility(View.VISIBLE);
            }
        } else {
            linear_default.setVisibility(View.GONE);
            tv_ad_area.setVisibility(View.VISIBLE);
        }
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("填写订单");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_submit = (TextView) findViewById(R.id.tv_submit);

        tv_submit.setOnClickListener(this);
    }

    @Override
    public void initDataSuccess(AddOrderListBean bean) {
        list.clear();
        list.addAll(bean.getOrder_list());
        adapter.notifyDataSetChanged();
        tv_price.setText("￥"+bean.getTotal_pay_fee());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_default:
            case R.id.tv_add_area:
                Bundle bundle = new Bundle();
                bundle.putInt(Define.INTENT_DATA,address_id);
                startAtvDonFinishForResult(AddressListActivity.class,100, bundle);
                break;
            case R.id.tv_submit:
                if (address_id==0) {
                    showTips("请完善收货地址",0);
                    return;
                }
                Bundle bundle1 = new Bundle();
                bundle1.putString(Define.INTENT_DATA, JSON.toJSONString(request));
                startAtvDonFinishForResult(ShoppingPayActivity.class,101,bundle1);
                break;
        }
    }
}
