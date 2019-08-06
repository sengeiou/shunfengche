package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseActivity;

/**
 * 描述：提交认证界面
 * 时间：2018/1/10
 * 作者：xjh
 */

public class SubmitLicenseActivity extends BaseActivity {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private EditText et_corporate_name;
    private EditText et_address;
    private EditText et_username;
    private EditText et_itcard;
    private ImageView iv_license;
    private ImageView iv_idcard;
    private ImageView iv_idcard_b;
    private TextView tv_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitlicense);
        initView();
        initToolbar();
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("填写企业资料");
        toolbar_tv_right.setVisibility(View.VISIBLE);
        toolbar_tv_right.setText("个人发布");
        toolbar_tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTips("个人认证",0);
            }
        });
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        et_corporate_name = (EditText) findViewById(R.id.et_corporate_name);
        et_address = (EditText) findViewById(R.id.et_address);
        et_username = (EditText) findViewById(R.id.et_username);
        et_itcard = (EditText) findViewById(R.id.et_itcard);
        iv_license = (ImageView) findViewById(R.id.iv_license);
        iv_idcard = (ImageView) findViewById(R.id.iv_idcard);
        iv_idcard_b = (ImageView) findViewById(R.id.iv_idcard_b);
        tv_submit = (TextView) findViewById(R.id.tv_submit);
    }

    private void submit() {
        // validate
        String name = et_corporate_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "输入", Toast.LENGTH_SHORT).show();
            return;
        }

        String address = et_address.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "输入地址", Toast.LENGTH_SHORT).show();
            return;
        }

        String username = et_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "输入", Toast.LENGTH_SHORT).show();
            return;
        }

        String itcard = et_itcard.getText().toString().trim();
        if (TextUtils.isEmpty(itcard)) {
            Toast.makeText(this, "输入法人身份证号", Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
