package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;

/**
 * 描述：退出登录回调
 * author:cyq
 * 2018-05-17
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface LogoutView extends BaseViewModel {

    /**
     * 退出登录成功
     */
    void logoutSuccess();
    /**
     * 退出登录失败
     */
    void logoutFailed(int code, String msg);
}
