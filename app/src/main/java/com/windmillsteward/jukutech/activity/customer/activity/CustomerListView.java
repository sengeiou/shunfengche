package com.windmillsteward.jukutech.activity.customer.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;

/**
 * 描述：客服地址回调
 * 时间：2018/11/01
 * 作者：cyq
 */
public interface CustomerListView extends BaseViewModel{



    void getCustomerUrlSuccess(String url);
    void getCustomerUrlFailed(int code,String msg);
}
