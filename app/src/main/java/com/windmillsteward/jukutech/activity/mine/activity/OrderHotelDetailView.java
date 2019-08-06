package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.OrderHotelDetailBean;

/**
 * 描述：
 * 时间：2018/3/3/003
 * 作者：xjh
 */
public interface OrderHotelDetailView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean
     */
    void initDataSuccess(OrderHotelDetailBean bean);

    /**
     * 取消酒店订单成功
     */
    void cancelHotelOrderSuccess();

    void deleteHotelOrderSuccess();
}
