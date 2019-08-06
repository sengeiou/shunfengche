package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/5/3/003
 * 作者：xjh
 */
public class CountOrderNumberBean extends BaseData {

    private int pending_payment_number;
    private int pending_delivery_number;
    private int goods_received_number;
    private int cancel_number;

    public int getPending_payment_number() {
        return pending_payment_number;
    }

    public void setPending_payment_number(int pending_payment_number) {
        this.pending_payment_number = pending_payment_number;
    }

    public int getPending_delivery_number() {
        return pending_delivery_number;
    }

    public void setPending_delivery_number(int pending_delivery_number) {
        this.pending_delivery_number = pending_delivery_number;
    }

    public int getGoods_received_number() {
        return goods_received_number;
    }

    public void setGoods_received_number(int goods_received_number) {
        this.goods_received_number = goods_received_number;
    }

    public int getCancel_number() {
        return cancel_number;
    }

    public void setCancel_number(int cancel_number) {
        this.cancel_number = cancel_number;
    }
}
