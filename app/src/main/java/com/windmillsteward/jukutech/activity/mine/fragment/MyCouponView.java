package com.windmillsteward.jukutech.activity.mine.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.MyCouponBean;

/**
 * 描述：优惠券列表回调
 * author:cyq
 * 2018-03-05
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface MyCouponView extends BaseViewModel {

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
