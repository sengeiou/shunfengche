package com.windmillsteward.jukutech.activity.home.insurance.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.AuthenResultBean;

/**
 * 描述：
 * 时间：2018/3/18/018
 * 作者：xjh
 */
public interface InsuranceListActivityView extends BaseViewModel {

    /**
     * 判断是否认证
     * @param bean
     */
    void isAuthen(AuthenResultBean bean);
}
