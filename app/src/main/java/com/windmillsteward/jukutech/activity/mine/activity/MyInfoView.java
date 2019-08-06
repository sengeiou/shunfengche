package com.windmillsteward.jukutech.activity.mine.activity;


import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.CountOrderNumberBean;
import com.windmillsteward.jukutech.bean.UserInfo;

/**
 * 我的个人信息回调
 */

public interface MyInfoView extends BaseViewModel {

    /**
     * 成功
     */
    void getMyInfoSuccess(UserInfo userInfo);

    /**
     * 失败
     */
    void getMyInfoFailed(int code, String msg);

    /**
     * 获取订单数
     */
    void getCountOrderNumber(CountOrderNumberBean bean);
}