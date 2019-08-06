package com.windmillsteward.jukutech.activity.home.insurance.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.BigHealthDetailBuyBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.InsuranceDetailBean;

/**
 * 描述：
 * 时间：2018/1/25
 * 作者：xjh
 */

public interface BigHealthDetailView extends BaseViewModel{

    /**
     * 初始化数据成功
     * @param bean 返回数据
     */
    void initDataSuccess(InsuranceDetailBean bean);

    /**
     * 是否收费
     * @param bean
     */
    void isChargeResult(ChargeResultBean bean);

    /**
     * 购买
     * @param bean
     */
    void buy(BigHealthDetailBuyBean bean);
}
