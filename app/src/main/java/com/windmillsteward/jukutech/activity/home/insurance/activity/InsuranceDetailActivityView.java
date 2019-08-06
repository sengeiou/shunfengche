package com.windmillsteward.jukutech.activity.home.insurance.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.InsuranceDetailBean;
import com.windmillsteward.jukutech.bean.TravelDetailBean;

/**
 * 描述：
 * 时间：2018/1/25
 * 作者：xjh
 */

public interface InsuranceDetailActivityView extends BaseViewModel{

    /**
     * 初始化数据成功
     * @param bean 返回数据
     */
    void initDataSuccess(InsuranceDetailBean bean);

    /**
     * 收藏成功
     */
    void collectSuccess();

    /**
     * 取消收藏成功
     */
    void cancelCollectSuccess();

    /**
     * 是否收费
     * @param bean
     */
    void isChargeResult(ChargeResultBean bean);
}
