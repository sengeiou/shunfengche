package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/3/6/006
 * 作者：xjh
 */
public class HotelPayBeforeBean extends BaseData {


    /**
     * total_coupon_fee : 0.0
     * prepay_order_sn :
     * total_pay_fee : 295.0
     * pay_fee : 195.0
     * prepay_fee : 100.0
     * pay_order_sn :
     * total_order_sn :
     */

    private String total_coupon_fee;
    private String prepay_order_sn;
    private String total_pay_fee;
    private String pay_fee;
    private String prepay_fee;
    private String pay_order_sn;
    private String total_order_sn;

    public String getTotal_coupon_fee() {
        return total_coupon_fee;
    }

    public void setTotal_coupon_fee(String total_coupon_fee) {
        this.total_coupon_fee = total_coupon_fee;
    }

    public String getPrepay_order_sn() {
        return prepay_order_sn;
    }

    public void setPrepay_order_sn(String prepay_order_sn) {
        this.prepay_order_sn = prepay_order_sn;
    }

    public String getTotal_pay_fee() {
        return total_pay_fee;
    }

    public void setTotal_pay_fee(String total_pay_fee) {
        this.total_pay_fee = total_pay_fee;
    }

    public String getPay_fee() {
        return pay_fee;
    }

    public void setPay_fee(String pay_fee) {
        this.pay_fee = pay_fee;
    }

    public String getPrepay_fee() {
        return prepay_fee;
    }

    public void setPrepay_fee(String prepay_fee) {
        this.prepay_fee = prepay_fee;
    }

    public String getPay_order_sn() {
        return pay_order_sn;
    }

    public void setPay_order_sn(String pay_order_sn) {
        this.pay_order_sn = pay_order_sn;
    }

    public String getTotal_order_sn() {
        return total_order_sn;
    }

    public void setTotal_order_sn(String total_order_sn) {
        this.total_order_sn = total_order_sn;
    }
}
