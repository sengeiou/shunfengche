package com.windmillsteward.jukutech.activity.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.MainActivity;
import com.windmillsteward.jukutech.activity.chat.DemoHelper;
import com.windmillsteward.jukutech.activity.login.presenter.Registerlmpl;
import com.windmillsteward.jukutech.base.AppManager;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.LoginSuccessInfo;
import com.windmillsteward.jukutech.umeng.UmengAddAliasUtil;

/**
 * 描述：注册页面
 * author:cyq
 * 2018-02-09
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener, RegisterView {

    private ImageView iv_back;
    private EditText et_account;
    private TextView btn_code;
    private EditText et_code;
    private EditText et_password;
    private EditText et_password_again;
    private EditText et_recommend_mobile;
    private ImageView iv_select;
    private TextView btn_register;
    private TextView tv_user_agreement;

    private boolean isSelect = true;
    private String user_agreement = "";//会员协议

    private Registerlmpl registerlmpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        et_account = (EditText) findViewById(R.id.et_account);
        btn_code = (TextView) findViewById(R.id.btn_code);
        et_code = (EditText) findViewById(R.id.et_code);
        et_password = (EditText) findViewById(R.id.et_password);
        et_password_again = (EditText) findViewById(R.id.et_password_again);
        et_recommend_mobile = (EditText) findViewById(R.id.et_recommend_mobile);
        iv_select = (ImageView) findViewById(R.id.iv_select);
        btn_register = (TextView) findViewById(R.id.btn_register);
        tv_user_agreement = (TextView) findViewById(R.id.tv_user_agreement);

        iv_back.setOnClickListener(this);
        btn_code.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        tv_user_agreement.setOnClickListener(this);
        findViewById(R.id.rela_select).setOnClickListener(this);

        registerlmpl = new Registerlmpl(this);
        registerlmpl.getUserAgreement();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_code:
                if (TextUtils.isEmpty(et_account.getText().toString())) {
                    showTips("请输入手机号码", 1);
                    return;
                }
                registerlmpl.getRegisterCode(et_account.getText().toString(), btn_code);
                break;
            case R.id.btn_register:
                if (!isSelect) {
                    showTips("请先阅读并同意会员协议", 1);
                    return;
                }
                if (TextUtils.isEmpty(et_account.getText().toString())) {
                    showTips("请输入手机号码", 1);
                    return;
                }
                if (TextUtils.isEmpty(et_password.getText().toString())) {
                    showTips("请输入密码", 1);
                    return;
                }
                if (TextUtils.isEmpty(et_password_again.getText().toString())|| !et_password.getText().toString().equals(et_password_again.getText().toString())) {
                    showTips("两次输入的密码不一致", 1);
                    return;
                }
                registerlmpl.register(et_account.getText().toString(), et_password.getText().toString(), et_code.getText().toString(),et_recommend_mobile.getText().toString());
                break;
            case R.id.rela_select:
                if (isSelect) {
                    isSelect = false;
                    iv_select.setVisibility(View.VISIBLE);
                } else {
                    isSelect = true;
                    iv_select.setVisibility(View.GONE);
                }
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_user_agreement:
                if (TextUtils.isEmpty(user_agreement)){
                    return;
                }
                UserAgreementH5Activity.go(this,user_agreement,"会员协议");

                break;

        }
    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PayActivity.REQUEST_CODE && resultCode == PayActivity.RESULT_CODE_SUCCESS) {
//            registerlmpl.register(et_account.getText().toString(), et_password.getText().toString(), et_nick_name.getText().toString());
//        }
//    }

    @Override
    public void registerSuccess(LoginSuccessInfo loginSuccessInfo) {
        Hawk.put("account", et_account.getText().toString());
//        overridePendingTransition(R.anim.slide_left_in,R.anim.slide_right_out);
        overridePendingTransition(R.anim.slide_left_in,R.anim.fade_out);
        UmengAddAliasUtil.removeAndAddAlias(loginSuccessInfo.getUser_id() + "", "sfc_android");


//        hxLogin(loginSuccessInfo);

        showTips("登录成功", 1);
        AppManager.getAppManager().finishAllActivity();
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 环信登录
     * @param loginSuccessInfo
     */
    private void hxLogin(final LoginSuccessInfo loginSuccessInfo){
        final String hx_user = loginSuccessInfo.getHx_user();
        if (!TextUtils.isEmpty(hx_user)) {
            EMClient.getInstance().login(hx_user, "123456", new EMCallBack() {//回调
                @Override
                public void onSuccess() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showTips("登录成功", 1);
                            AppManager.getAppManager().finishAllActivity();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            Log.d("main", "登录聊天服务器成功！");
                        }
                    });
                }

                @Override
                public void onProgress(int progress, String status) {

                }

                @Override
                public void onError(int code, String message) {
                    Log.d("main", "登录聊天服务器失败！"+code+message);
                    if (code == 200){
                        hxLogout(loginSuccessInfo);
                    }
                }
            });
        }
    }

    /**
     * 环信登出
     * @param loginSuccessInfo
     */
    private void hxLogout(final LoginSuccessInfo loginSuccessInfo){
        DemoHelper.getInstance().logout(true,new EMCallBack() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                Log.d("main", "注销已经登录的账号，并重新登录！");
                hxLogin(loginSuccessInfo);
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {

            }
        });
    }



    @Override
    public void registerFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void beforeRegisterSuccess(int is_charge, String charges) {
//        if (is_charge == 0) {//不需要收费
//            if (TextUtils.isEmpty(et_nick_name.getText().toString())) {
//                showTips("请输入昵称", 1);
//                return;
//            }
//            if (TextUtils.isEmpty(et_password.getText().toString())) {
//                showTips("请输入密码", 1);
//                return;
//            }
//            registerlmpl.register(et_account.getText().toString(), et_password.getText().toString(), et_nick_name.getText().toString());
//
//        } else if (is_charge == 1) {//需要收费
//            PayActivity.go(this, 1, 0);
//        }
    }

    @Override
    public void beforeRegisterFailed(int code, String msg) {
        showTips(msg, 1);
    }

    @Override
    public void getUserAgreementSuccess(String register_rule) {
        if (TextUtils.isEmpty(register_rule)){
            return;
        }
        this.user_agreement = register_rule;
    }

}
