package com.windmillsteward.jukutech.activity.login.presenter;

import com.alibaba.fastjson.TypeReference;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.activity.login.activity.LoginView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.FileUploadResultBean;
import com.windmillsteward.jukutech.bean.LoginSuccessInfo;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 登录操作类
 */

public class Loginlmpl extends BaseNetModelImpl {
    private final int LOGIN = 1;
    private LoginView loginView;

    public Loginlmpl(LoginView loginView) {
        this.loginView = loginView;
    }

    /**
     * 登录接口
     */
    public void login(String phone,String password) {
        DataLoader dataLoader = new DataLoader(this, LOGIN);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("mobile_phone", phone);  //    13790097063
        map.put("password", password);
        map.put("push_identifying", 1);
        httpInfo.setUrl(APIS.LOGIN);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
//        loginView.showDialog("登录中..");
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case LOGIN:
                type = new TypeReference<BaseResultInfo<LoginSuccessInfo>>() {
                }.getType();
                break;
            case 111:
                type = new TypeReference<BaseResultInfo<FileUploadResultBean>>() {
                }.getType();
                break;
            case 10:
                type = new TypeReference<BaseResultInfo<FileUploadResultBean>>(){
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case LOGIN:
                LoginSuccessInfo data = (LoginSuccessInfo) result.getData();
                Hawk.put(Define.LOGIN_SUCCESS, data);
                Hawk.put(Define.USER_ID, data.getUser_id());
                Hawk.put(Define.ACCESS_TOKEN, data.getAccess_token());
                loginView.loginSuccess(data);
                break;
            case 111:
                loginView.dismiss();
                loginView.showTips(sourceData,0);
                break;
            case 10:
                loginView.dismiss();
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case LOGIN:
                loginView.dismiss();
                loginView.loginFailed(code, msg);
                break;
            case 111:
                loginView.dismiss();
                loginView.showTips("失败",0);
                break;
        }
    }
}