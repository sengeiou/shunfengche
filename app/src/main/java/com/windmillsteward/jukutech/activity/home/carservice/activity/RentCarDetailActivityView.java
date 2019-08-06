package com.windmillsteward.jukutech.activity.home.carservice.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.RentCarDetailBean;

/**
 * 描述：
 * 时间：2018/3/29/029
 * 作者：xjh
 */
public interface RentCarDetailActivityView extends BaseViewModel {

    /**
     * 初始化数据成功
     */
    void initDataSuccess(RentCarDetailBean bean);

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
     */
    void isChargeResult(ChargeResultBean bean);
}
