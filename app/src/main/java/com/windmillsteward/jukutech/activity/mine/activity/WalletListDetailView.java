package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.WalletListDetailBean;

/**
 * 描述：钱包明细详情回调
 * author:cyq
 * 2018-03-13
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface WalletListDetailView extends BaseViewModel {

    /**
     * 获取钱包明细详情成功
     */
    void getWalletListDetailSuccess(WalletListDetailBean walletListDetailBean);
    /**
     * 获取钱包明细详情失败
     */
    void getWalletListDetailFailed(int code, String msg);
}
