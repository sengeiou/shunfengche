package com.windmillsteward.jukutech.activity.newpage.common.model;

/**
 * @date: on 2018/10/13
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 信息费model
 */
public class PayInfoFeeModel {
    /**
     * charge_fee : 0.01
     * is_charge : 1
     * order_name : 房屋租售联系信息费用
     */

    private String charge_fee;
    private int is_charge;
    private String order_name;

    public String getCharge_fee() {
        return charge_fee;
    }

    public void setCharge_fee(String charge_fee) {
        this.charge_fee = charge_fee;
    }

    public int getIs_charge() {
        return is_charge;
    }

    public void setIs_charge(int is_charge) {
        this.is_charge = is_charge;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }
}
