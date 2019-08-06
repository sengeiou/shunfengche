package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ShoppingPayBean;

/**
 * 描述：
 * 时间：2018/3/4/004
 * 作者：xjh
 */
public interface ShoppingContinuePayActivityView extends BaseViewModel {

    /**
     * 支付前调用
     * @param bean 返回结果 如果有优惠卷的再调该方法
     */
    void payBefore(ShoppingPayBean bean);

//    void payAfter();
}
