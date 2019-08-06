package com.windmillsteward.jukutech.activity.home.commons.pay;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.RequirePayResultBean;

/**
 * 描述：
 * 时间：2018/3/7/007
 * 作者：xjh
 */
public interface RequirePayView extends BaseViewModel {

    /**
     * 获取发布需求需要的费用
     * @param bean
     */
    void initDataSuccess(RequirePayResultBean bean);
}
