package com.windmillsteward.jukutech.activity.login.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.login.presenter.ForgetPasswordlmpl;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.utils.StateButton;

/**
 * 描述：忘记密码
 * author:cyq
 * 2018-02-09
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class ForgetPasswordActivity extends BaseActivity implements View.OnClickListener,ForgetPasswordView {

    private ImageView iv_back;
    private EditText et_account;
    private TextView btn_code;
    private EditText et_code;
    private EditText et_password;
    private TextView btn_commit;

    private ForgetPasswordlmpl forgetPasswordlmpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initView();
    }


    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        et_account = (EditText) findViewById(R.id.et_account);
        btn_code = (TextView) findViewById(R.id.btn_code);
        et_code = (EditText) findViewById(R.id.et_code);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_commit = (TextView) findViewById(R.id.btn_commit);

        btn_code.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        forgetPasswordlmpl = new ForgetPasswordlmpl(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_code:
                if (TextUtils.isEmpty(et_account.getText().toString())){
                    showTips("请输入手机号码",1);
                    return;
                }
                forgetPasswordlmpl.getForgetPasswrodCode(et_account.getText().toString(),btn_code);
                break;
            case R.id.btn_commit:
                if (TextUtils.isEmpty(et_account.getText().toString())){
                    showTips("请输入手机号码",1);
                    return;
                }
                if (TextUtils.isEmpty(et_code.getText().toString())){
                    showTips("请输入验证码",1);
                    return;
                }
                if (TextUtils.isEmpty(et_password.getText().toString())){
                    showTips("请输入密码",1);
                    return;
                }
                forgetPasswordlmpl.forgetPassword(et_account.getText().toString(),et_password.getText().toString(),et_code.getText().toString());
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void forgetPasswordSuccess() {
        showTips("重置成功",1);
        finish();
    }

    @Override
    public void forgetPasswordFailed(int code, String msg) {
        showTips(msg,1);
    }
}
