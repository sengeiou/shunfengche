package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.OrderFamilyDetailBean;

/**
 * 描述：
 * 时间：2018/3/3/003
 * 作者：xjh
 */
public interface OrderFamilyDetailView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean
     */
    void initDataSuccess(OrderFamilyDetailBean bean);

    /**
     * 取消订单
     */
    void cancelOrderSuccess();

    /**
     * 完成订单
     */
    void finishOrderSuccess();

    void deleteOrderSuccess();
}
