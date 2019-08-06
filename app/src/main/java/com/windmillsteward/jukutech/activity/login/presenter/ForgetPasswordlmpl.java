package com.windmillsteward.jukutech.activity.login.presenter;

import android.graphics.Color;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.login.activity.ForgetPasswordView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.StateButton;
import com.windmillsteward.jukutech.utils.TimerCountUtil;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 忘记密码操作类
 */

public class ForgetPasswordlmpl extends BaseNetModelImpl {
    private final int FORGET_PASSWORD = 1;
    private final int GET_FORGET_PASSWORD_CODE = 2;
    private ForgetPasswordView view;

    private TimerCountUtil myTimer;
    private TextView btn_code;//验证码按钮

    public ForgetPasswordlmpl(ForgetPasswordView view) {
        this.view = view;
    }

    public void getForgetPasswrodCode(String mobile_phone, TextView btn) {
        this.btn_code = btn;
        DataLoader dataLoader = new DataLoader(this, GET_FORGET_PASSWORD_CODE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("mobile_phone", mobile_phone);
        httpInfo.setUrl(APIS.URL_GET_RESET_PASSWORD_CODE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
        view.showDialog("");
    }

    TimerCountUtil.TimerChangeListner timerChangeListner = new TimerCountUtil.TimerChangeListner() {
        @Override
        public void onChange(long minis) {
            btn_code.setText("剩余" + minis / 1000 + "秒");
            btn_code.setTextColor(Color.BLACK);
        }

        @Override
        public void onChangeFinish(String state) {
            btn_code.setText("重新获取");
            btn_code.setTextColor(Color.GRAY);
        }
    };


    /**
     * 忘记密码
     */
    public void forgetPassword(String mobile_phone, String password, String verify_code) {
        DataLoader dataLoader = new DataLoader(this, FORGET_PASSWORD);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("mobile_phone", mobile_phone);
        map.put("password", password);
        map.put("verify_code", verify_code);
        httpInfo.setUrl(APIS.URL_RESET_PASSWORD);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);

    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case FORGET_PASSWORD:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case GET_FORGET_PASSWORD_CODE:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case FORGET_PASSWORD:
                view.forgetPasswordSuccess();
                break;
            case GET_FORGET_PASSWORD_CODE:
                if (myTimer == null) {
                    myTimer = new TimerCountUtil(30000, 1000, btn_code);
                    myTimer.setTimeSurListner(timerChangeListner);
                }
                myTimer.start();
                view.showTips("验证码已发送，请注意查收", 1);
                break;

        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case FORGET_PASSWORD:
                view.forgetPasswordFailed(code, msg);
                break;
            case GET_FORGET_PASSWORD_CODE:
                view.showTips(msg, 1);
                break;

        }
    }
}