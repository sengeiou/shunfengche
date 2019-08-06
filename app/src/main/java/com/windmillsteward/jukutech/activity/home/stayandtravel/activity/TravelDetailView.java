package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.TravelDetailBean;

/**
 * 描述：
 * 时间：2018/1/25
 * 作者：xjh
 */

public interface TravelDetailView extends BaseViewModel{

    /**
     * 初始化数据成功
     * @param bean 返回数据
     */
    void initDataSuccess(TravelDetailBean bean);

    /**
     * 收藏旅游成功
     */
    void collectTravelSuccess();

    /**
     * 取消收藏旅游成功
     */
    void cancelCollectTravelSuccess();

    /**
     * 是否收费
     * @param bean
     */
    void isChargeResult(ChargeResultBean bean);
}
