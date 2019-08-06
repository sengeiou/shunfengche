package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class ShoppingPayBean extends BaseData {


    /**
     * order_title : 牛6
     * total_pay_fee : 281.0
     * order_sn : 2018041803132069622406
     */

    private String order_title;
    private String total_pay_fee;
    private String order_sn;

    public String getOrder_title() {
        return order_title;
    }

    public void setOrder_title(String order_title) {
        this.order_title = order_title;
    }

    public String getTotal_pay_fee() {
        return total_pay_fee;
    }

    public void setTotal_pay_fee(String total_pay_fee) {
        this.total_pay_fee = total_pay_fee;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }
}
