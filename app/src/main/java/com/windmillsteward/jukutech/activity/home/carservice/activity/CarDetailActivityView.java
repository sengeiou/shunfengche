package com.windmillsteward.jukutech.activity.home.carservice.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.CarDetailBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;

/**
 * 描述：
 * 时间：2018/3/29/029
 * 作者：xjh
 */
public interface CarDetailActivityView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean
     */
    void initDataSuccess(CarDetailBean bean);

    /**
     * 收藏
     */
    void collectSuccess();

    /**
     * 取消收藏
     */
    void cancelCollectSuccess();

    /**
     * 判断是否收费
     * @param bean
     */
    void isChargeResult(ChargeResultBean bean);
}
