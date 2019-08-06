package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.presenter.RefundDeliveryActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.interfaces.Define;

/**
 * 描述：退货的发货
 * 时间：2018/4/22/022
 * 作者：xjh
 */
public class RefundDeliveryActivity extends BaseActivity implements RefundDeliveryActivityView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView tv_order_sn;
    private TextView tv_info;
    private TextView tv_address;
    private EditText et_logistics_single_number;
    private TextView tv_send;

    private RefundDeliveryActivityPresenter presenter;
    private int record_id;
    private String order_sn;
    private String address;
    private String info;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refund_delivery);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            record_id = extras.getInt(Define.INTENT_DATA);
            order_sn = extras.getString(Define.INTENT_DATA_TWO);
            address = extras.getString(Define.INTENT_DATA_THREE);
            info = extras.getString(Define.INTENT_DATA_FOUR);
            text = extras.getString(Define.INTENT_TYPE);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        presenter = new RefundDeliveryActivityPresenter(this);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("退货发货");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tv_order_sn = (TextView) findViewById(R.id.tv_order_sn);
        tv_info = (TextView) findViewById(R.id.tv_info);
        tv_address = (TextView) findViewById(R.id.tv_address);
        et_logistics_single_number = (EditText) findViewById(R.id.et_logistics_single_number);
        tv_send = (TextView) findViewById(R.id.tv_send);
        tv_order_sn.setText(order_sn);
        tv_info.setText(info);
        tv_address.setText(address);

        tv_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private void submit() {
        String number = et_logistics_single_number.getText().toString().trim();
        if (TextUtils.isEmpty(number)) {
            showTips("请填写物流编号",0);
            return;
        }
        presenter.send(getAccessToken(),record_id,number,order_sn);
    }

    @Override
    public void sendSuccess() {
        finish();
    }
}
