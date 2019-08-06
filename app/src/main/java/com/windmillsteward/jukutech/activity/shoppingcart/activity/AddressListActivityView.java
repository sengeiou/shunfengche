package com.windmillsteward.jukutech.activity.shoppingcart.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.AddressListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public interface AddressListActivityView extends BaseViewModel {

    /**
     * 初始化数据
     */
    void initDataSuccess(AddressListBean bean);

    /**
     * 刷新
     */
    void refreshDataSuccess(AddressListBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    void loadNextDataSuccess(AddressListBean bean);

    void loadNextDataFailure();
}
