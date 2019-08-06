package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;

/**
 * 描述：申请提现回调
 * 时间：2018/3/6
 * 作者：cyq
 */
public interface ApplyWithdrawView extends BaseViewModel {



    /**
     * 申请提现成功
     */
    void applyWithdrawSuccess();

    /**
     * 申请提现失败
     * @param code
     * @param msg
     */
    void applyWithdrawFailed(int code, String msg);


}
