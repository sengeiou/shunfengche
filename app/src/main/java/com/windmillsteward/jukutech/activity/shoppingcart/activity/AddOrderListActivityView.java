package com.windmillsteward.jukutech.activity.shoppingcart.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.AddOrderListBean;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public interface AddOrderListActivityView extends BaseViewModel {

    /**
     * 初始化数据成功
     */
    void initDataSuccess(AddOrderListBean bean);
}
