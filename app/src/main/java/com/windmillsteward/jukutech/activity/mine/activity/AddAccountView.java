package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;

/**
 * 描述：添加账户回调
 * 时间：2018/3/6
 * 作者：cyq
 */
public interface AddAccountView extends BaseViewModel {


    /**
     * 添加账户成功
     */
    void addAccountSuccess();

    /**
     * 添加账户失败
     * @param code
     * @param msg
     */
    void addAccountFailed(int code, String msg);
}
