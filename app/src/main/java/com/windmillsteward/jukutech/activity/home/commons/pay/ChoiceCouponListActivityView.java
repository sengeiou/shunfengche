package com.windmillsteward.jukutech.activity.home.commons.pay;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.MyCouponBean;

/**
 * 描述：
 * 时间：2018/3/26/026
 * 作者：xjh
 */
public interface ChoiceCouponListActivityView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean
     */
    void initDataSuccess(MyCouponBean bean);

    /**
     * 刷新数据成功
     * @param bean
     */
    void refreshDataSuccess(MyCouponBean bean);

    void refreshDataFailure();

    /**
     * 加载下一页数据成功
     * @param bean
     */
    void loadNextDataSuccess(MyCouponBean bean);

    void loadNextDataFailure();
}
