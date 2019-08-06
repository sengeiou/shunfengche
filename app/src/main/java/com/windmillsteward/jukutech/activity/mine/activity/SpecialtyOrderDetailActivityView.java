package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.SpecialtyOrderDetailBean;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public interface SpecialtyOrderDetailActivityView extends BaseViewModel {

    /**
     * 初始化数据
     * @param bean
     */
    void initDataSuccess(SpecialtyOrderDetailBean bean);

    /**
     * 获取最后一个物流信息
     * @param msg
     * @param time
     */
    void loadLogisticsInfoSuccess(String msg,String time);

    /**
     * 删除订单成功
     */
    void deleteOrderSuccess();

    /**
     * 关闭订单
     */
    void closeOrderSuccess();

    /**
     * 确认收货
     */
    void confirmOrderSuccess();
}
