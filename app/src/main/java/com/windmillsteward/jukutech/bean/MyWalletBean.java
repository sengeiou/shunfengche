package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：我的钱包实体
 * author:cyq
 * 2018-03-05
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MyWalletBean extends BaseData {

    private double current_fee;//钱包余额

    public double getCurrent_fee() {
        return current_fee;
    }

    public void setCurrent_fee(double current_fee) {
        this.current_fee = current_fee;
    }
}
