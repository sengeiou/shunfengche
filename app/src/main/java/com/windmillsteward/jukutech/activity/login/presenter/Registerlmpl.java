package com.windmillsteward.jukutech.activity.login.presenter;

import android.graphics.Color;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.activity.login.activity.RegisterView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.LoginSuccessInfo;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.TimerCountUtil;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 注册操作类
 */

public class Registerlmpl extends BaseNetModelImpl {
    private final int REGISTER = 1;
    private final int BEFORE_REGISTER = 2;
    private final int GET_REGISTER_CODE = 3;
    private final int USER_AGREEMENT = 4;
    private RegisterView view;

    private TimerCountUtil myTimer;
    private TextView btn_code;//验证码按钮

    public Registerlmpl(RegisterView view) {
        this.view = view;
    }

    public void getRegisterCode(String mobile_phone, TextView btn) {
        this.btn_code = btn;
        DataLoader dataLoader = new DataLoader(this, GET_REGISTER_CODE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("mobile_phone", mobile_phone);
        httpInfo.setUrl(APIS.URL_GET_REGISTER_CODE);
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
     * 注册前，需要验证-验证码是否正确，先请求这个接口，再请求注册接口
     *
     * @param mobile_phone
     * @param verify_code
     */
    public void beforeRegister(String mobile_phone, String verify_code) {
        DataLoader dataLoader = new DataLoader(this, BEFORE_REGISTER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("mobile_phone", mobile_phone);
        map.put("verify_code", verify_code);
        httpInfo.setUrl(APIS.URL_BEFORE_REGISTER);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 注册接口
     */
    public void register(String phone, String password, String verify_code, String referrer_phone) {
        DataLoader dataLoader = new DataLoader(this, REGISTER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("mobile_phone", phone);  //    13790097063
        map.put("password", password);
        map.put("verify_code", verify_code);
        map.put("referrer_phone", referrer_phone);
        httpInfo.setUrl(APIS.URL_REGISTER);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);

    }

    /**
     * 注册接口
     */
    public void getUserAgreement() {
        DataLoader dataLoader = new DataLoader(this, USER_AGREEMENT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        httpInfo.setUrl(APIS.URL_USER_AGREEMENT);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);

    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case REGISTER:
                type = new TypeReference<BaseResultInfo<LoginSuccessInfo>>() {
                }.getType();
                break;
            case BEFORE_REGISTER:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case GET_REGISTER_CODE:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case USER_AGREEMENT:
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
            case REGISTER:
                LoginSuccessInfo loginSuccessInfo = (LoginSuccessInfo) result.getData();
                if (loginSuccessInfo != null){
                    Hawk.put(Define.LOGIN_SUCCESS, loginSuccessInfo);
                    Hawk.put(Define.USER_ID, loginSuccessInfo.getUser_id());
                    Hawk.put(Define.ACCESS_TOKEN, loginSuccessInfo.getAccess_token());
                    view.registerSuccess(loginSuccessInfo);
                }
                break;
            case BEFORE_REGISTER:
                String data = (String) result.getData();
                try {
                    JSONObject object = new JSONObject(data);
                    int is_charge = (int) object.get("is_charge");
                    String charges = (String) object.get("charge_fee");
                    view.beforeRegisterSuccess(is_charge, charges);
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.beforeRegisterSuccess(0, "");
                }
                break;
            case GET_REGISTER_CODE:
                if (myTimer == null) {
                    myTimer = new TimerCountUtil(30000, 1000, btn_code);
                    myTimer.setTimeSurListner(timerChangeListner);
                }
                myTimer.start();
                view.showTips("验证码已发送，请注意查收", 1);
                break;
            case USER_AGREEMENT:
                String user_agreement = result.getData().toString();
                try {
                    JSONObject object = new JSONObject(user_agreement);
                    String charges = (String) object.get("register_rule");
                    view.getUserAgreementSuccess(charges);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case REGISTER:
                view.registerFailed(code, msg);
                break;
            case BEFORE_REGISTER:
                view.beforeRegisterFailed(code, msg);
                break;
            case GET_REGISTER_CODE:
                view.showTips(msg, 1);
                break;
            case USER_AGREEMENT:
                view.showTips(msg, 1);
                break;

        }
    }
}