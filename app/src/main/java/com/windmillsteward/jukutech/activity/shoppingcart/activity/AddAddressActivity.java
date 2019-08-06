package com.windmillsteward.jukutech.activity.shoppingcart.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.shoppingcart.presenter.AddAddressActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.AddressListBean;
import com.windmillsteward.jukutech.customview.popup.AddressSelectPopup;
import com.windmillsteward.jukutech.interfaces.Define;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class AddAddressActivity extends BaseActivity implements View.OnClickListener, AddAddressActivityView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private EditText et_username;
    private EditText et_phone;
    private TextView tv_address;
    private EditText et_address;
    private ImageView iv_default;
    private TextView tv_save;

    private int first_area_id;
    private int second_area_id;
    private int third_area_id;

    private AddAddressActivityPresenter presenter;
    private int type;
    private int address_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        initView();
        initToolbar();
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            String json = extras.getString(Define.INTENT_DATA);
            AddressListBean.ListBean listBean = JSON.parseObject(json, AddressListBean.ListBean.class);
            if (listBean!=null) {
                et_username.setText(listBean.getUser_name());
                et_phone.setText(listBean.getMobile_phone());
                tv_address.setText(listBean.getFirst_area_name()+" "+listBean.getSecond_area_name()+" "+listBean.getThird_area_name());
                if (listBean.getIs_default()==1) {
                    iv_default.setTag(1);
                    iv_default.setImageResource(R.mipmap.icon_select);
                }
                first_area_id = listBean.getFirst_area_id();
                second_area_id = listBean.getSecond_area_id();
                third_area_id = listBean.getThird_area_id();
                et_address.setText(listBean.getAddress());
                tv_save.setText("保存");
                type =1;
                address_id = listBean.getAddress_id();
            }
        }

        presenter = new AddAddressActivityPresenter(this);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("新建收货地址");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        et_username = (EditText) findViewById(R.id.et_username);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_address = (TextView) findViewById(R.id.tv_address);
        et_address = (EditText) findViewById(R.id.et_address);
        iv_default = (ImageView) findViewById(R.id.iv_default);
        tv_save = (TextView) findViewById(R.id.tv_save);

        tv_address.setOnClickListener(this);
        tv_save.setOnClickListener(this);
        iv_default.setTag(0);
        iv_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((int) iv_default.getTag())==0) {
                    iv_default.setTag(1);
                    iv_default.setImageResource(R.mipmap.icon_select);
                } else {
                    iv_default.setTag(0);
                    iv_default.setImageResource(R.mipmap.icon_select_n);
                }
            }
        });
    }

    private void submit() {
        String username = et_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            showTips("收货人不能为空",0);
            return;
        }

        String phone = et_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showTips("联系电话不能为空",0);
            return;
        }

        String address = et_address.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            showTips("请输入详细地址",0);
            return;
        }
        if (first_area_id==0) {
            showTips("请选择地区",0);
            return;
        }

        if (type==0) {
            presenter.addAddress(getAccessToken(),username,phone,first_area_id,second_area_id,third_area_id,address,(int) iv_default.getTag());
        } else {
            presenter.editAddress(address_id,getAccessToken(),username,phone,first_area_id,second_area_id,third_area_id,address,(int) iv_default.getTag());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_address:
                new AddressSelectPopup(this, new AddressSelectPopup.OnSelectAddressListener() {
                    @Override
                    public void onSelect(int first_area_id, String first_area_name, int second_area_id, String second_area_name, int third_area_id, String third_area_name) {
                        tv_address.setText(first_area_name+" "+second_area_name+" "+third_area_name);
                        AddAddressActivity.this.first_area_id = first_area_id;
                        AddAddressActivity.this.second_area_id = second_area_id;
                        AddAddressActivity.this.third_area_id = third_area_id;
                    }

                }).show(tv_save);
                break;
            case R.id.tv_save:
                submit();
                break;
        }
    }

    @Override
    public void addAddressSuccess() {
        finish();
    }
}
