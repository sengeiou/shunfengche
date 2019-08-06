package com.windmillsteward.jukutech.activity.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.windmillsteward.jukutech.activity.chat.db.DemoDBManager;
import com.windmillsteward.jukutech.activity.login.presenter.Loginlmpl;
import com.windmillsteward.jukutech.base.AppManager;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.LoginSuccessInfo;
import com.windmillsteward.jukutech.umeng.UmengAddAliasUtil;
import com.windmillsteward.jukutech.utils.StringUtil;

/**
 * 描述：登录页面
 * author:cyq
 * 2018-02-09
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginView, TextWatcher {

    protected static final String TAG = "LoginActivity";

    private ImageView iv_back;
    private EditText et_account;
    private ImageView iv_show_password;
    private EditText et_password;
    private TextView btn_login;
    private TextView tv_register;
    private TextView tv_forget_password;
    private TextView tv_visitor_mode;
//    private CircleImageView iv_logo;

    private Loginlmpl loginlmpl;
    private boolean isShow = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        et_account = (EditText) findViewById(R.id.et_account);
        iv_show_password = (ImageView) findViewById(R.id.iv_show_password);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (TextView) findViewById(R.id.btn_login);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_forget_password = (TextView) findViewById(R.id.tv_forget_password);
        tv_visitor_mode = (TextView) findViewById(R.id.tv_visitor_mode);
//        iv_logo = (CircleImageView) findViewById(R.id.iv_logo);

        iv_back.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        iv_show_password.setOnClickListener(this);
        tv_visitor_mode.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_forget_password.setOnClickListener(this);
        et_password.addTextChangedListener(this);

        String account = Hawk.get("account", "");
        et_account.setText(TextUtils.isEmpty(account) ? "" : account);

        if (!TextUtils.isEmpty(et_account.getText().toString())) {
            et_password.setFocusable(true);
            et_password.setFocusableInTouchMode(true);
            et_password.requestFocus();
        }

        et_account.addTextChangedListener(textWatcher);
        et_password.addTextChangedListener(textWatcher);

//        GlideUtil.show(this,"http://shunfengche.qishare.cn/windmillsteward/user_api/user_center/test22?mobile_phone=123123123",iv_logo);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (StringUtil.isAllNotEmpty(et_account.getText().toString().trim(), et_password.getText().toString().trim())) {
                btn_login.setEnabled(true);
            } else {
                btn_login.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_login:
                showDialog();
                if (loginlmpl == null) {
                    loginlmpl = new Loginlmpl(this);
                }
                if (TextUtils.isEmpty(et_account.getText().toString())) {
                    showTips("请输入手机号", 1);
                    return;
                }
                if (TextUtils.isEmpty(et_password.getText().toString())) {
                    showTips("请输入密码", 1);
                    return;
                }
                loginlmpl.login(et_account.getText().toString(), et_password.getText().toString());
                break;
            case R.id.iv_show_password:
                if (isShow) {
                    isShow = false;
                    iv_show_password.setImageResource(R.mipmap.icon_expose_n);
                    et_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    isShow = true;
                    iv_show_password.setImageResource(R.mipmap.icon_expose);
                    et_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                break;
            case R.id.tv_visitor_mode:
                AppManager.getAppManager().finishAllActivity();
                startAtvAndFinish(MainActivity.class);
                break;
            case R.id.tv_register:
                startAtvDonFinish(RegisterActivity.class);
                break;
            case R.id.tv_forget_password:
                startAtvDonFinish(ForgetPasswordActivity.class);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String word = editable.toString();
        if (word.length() > 0) {
            iv_show_password.setVisibility(View.VISIBLE);
        } else {
            iv_show_password.setVisibility(View.GONE);
        }
    }

    @Override
    public void loginSuccess(final LoginSuccessInfo loginSuccessInfo) {
        if (loginSuccessInfo == null) {
            return;
        }
        Hawk.put("account", et_account.getText().toString());
//        overridePendingTransition(R.anim.slide_left_in,R.anim.slide_right_out);
        overridePendingTransition(R.anim.slide_left_in, R.anim.fade_out);
        UmengAddAliasUtil.removeAndAddAlias(loginSuccessInfo.getUser_id() + "", "sfc_android");

        String hx_user = loginSuccessInfo.getHx_user();

//        showTips("登录成功", 1);
//        AppManager.getAppManager().finishAllActivity();
//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        startActivity(intent);
//        finish();

        hxLogin(loginSuccessInfo);

    }

    @Override
    public void loginFailed(int code, String msg) {
        showTips(msg, 1);
    }

    /**
     * 环信登录
     *
     * @param loginSuccessInfo
     */
    private void hxLogin(final LoginSuccessInfo loginSuccessInfo) {
        final String hx_user = loginSuccessInfo.getHx_user();
        if (!TextUtils.isEmpty(hx_user)) {
            DemoDBManager.getInstance().closeDB();
            EMClient.getInstance().login(hx_user, "123456", new EMCallBack() {//回调
                @Override
                public void onSuccess() {
                    DemoHelper.getInstance().setCurrentUserName(loginSuccessInfo.getHx_user());
                    DemoHelper.getInstance().getUserProfileManager().setCurrentUserNick(loginSuccessInfo.getNickname());
                    DemoHelper.getInstance().getUserProfileManager().setCurrentUserAvatar(loginSuccessInfo.getUser_avatar_url());
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dismiss();
                            showTips("登录成功", 1);
                            AppManager.getAppManager().finishAllActivity();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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
                    Log.d("main", "登录聊天服务器失败！" + code + message);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            dismiss();
                            showTips("登录成功", 1);
                            AppManager.getAppManager().finishAllActivity();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    if (code == 200) {
//                        hxLogout(loginSuccessInfo);
                    }
                }
            });

        }else{
            dismiss();
            showTips("登录成功", 1);
            AppManager.getAppManager().finishAllActivity();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    /**
     * 环信登出
     *
     * @param loginSuccessInfo
     */
    private void hxLogout(final LoginSuccessInfo loginSuccessInfo) {
        DemoHelper.getInstance().logout(false, new EMCallBack() {

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
                Log.d("main", "注销失败" + message);
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
    }
}
