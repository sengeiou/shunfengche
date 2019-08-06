package com.windmillsteward.jukutech.activity.login.activity;


import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.LoginSuccessInfo;

/**
 * 登录回调
 */

public interface LoginView extends BaseViewModel {

    /**
     * 成功
     */
    void loginSuccess(LoginSuccessInfo loginSuccessInfo);

    /**
     * 失败
     */
    void loginFailed(int code, String msg);
}