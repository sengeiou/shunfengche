package com.windmillsteward.jukutech.activity.login.activity;


import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.LoginSuccessInfo;

/**
 * 注册回调
 */

public interface RegisterView extends BaseViewModel {

    /**
     * 成功
     */
    void registerSuccess(LoginSuccessInfo loginSuccessInfo);

    /**
     * 失败
     */
    void registerFailed(int code, String msg);

    /**
     * 验证成功
     */
    void beforeRegisterSuccess(int is_charge,String charges);

    /**
     * 验证失败
     */
    void beforeRegisterFailed(int code, String msg);

    /**
     * 获取会员协议成功
     * @param register_rule
     */
    void getUserAgreementSuccess(String register_rule);
}