package com.windmillsteward.jukutech.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.IsHaveHongBaoBean;

/**
 * 描述：检查更新回调
 * author:cyq
 * 2018-04-11
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface MainView extends BaseViewModel {
    /**
     * 判断是否可以领取优惠券红包
     */
    void isGetCouponSuccess(int is_coupon);

    /**
     * 判断是否可以领取优惠券红包
     */
    void getCouponHongBaoSuccess();



    /**
     * 判断是否有现金红包抢
     * @param bean
     */
    void isHaveHongBao(IsHaveHongBaoBean bean);

    /**
     * 抢现金红包
     */
    void getMoneyHongBao(String msg,IsHaveHongBaoBean bean);

    /**
     * 获取正在进行中的订单数量
     * @param num
     * @param unReadNum
     */
    void getOnGoingOrderNum(int num,int unReadNum);

}
