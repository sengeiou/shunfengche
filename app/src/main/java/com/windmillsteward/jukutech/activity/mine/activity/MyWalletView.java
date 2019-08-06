package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.MyWalletBean;

/**
 * 描述：我的钱包回调
 * 时间：2018/3/5
 * 作者：cyq
 */
public interface MyWalletView extends BaseViewModel {

    /**
     * 获取余额数据成功
     * @param bean
     */
    void getDataSuccess(MyWalletBean bean);

    /**
     * 获取余额数据失败
     * @param code
     * @param msg
     */
    void getDataFailed(int code,String msg);


}
