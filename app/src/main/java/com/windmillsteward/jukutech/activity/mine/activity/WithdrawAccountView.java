package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.WithdrawBean;

import java.util.List;

/**
 * 描述：提现账户列表回调
 * 时间：2018/3/6
 * 作者：cyq
 */
public interface WithdrawAccountView extends BaseViewModel {


    /**
     * 获取提现账户列表成功
     */
    void getWithdrawAccountListSuccess(List<WithdrawBean> list);

    /**
     * 获取提现账户列表失败
     * @param msg
     */
    void getWithdrawAccountListFailed(String msg);


}
