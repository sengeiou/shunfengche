package com.windmillsteward.jukutech.activity.login.activity;


import com.windmillsteward.jukutech.base.BaseViewModel;

/**
 * 忘记密码回调
 */

public interface ForgetPasswordView extends BaseViewModel {

    /**
     * 成功
     */
    void forgetPasswordSuccess();

    /**
     * 失败
     */
    void forgetPasswordFailed(int code, String msg);
}