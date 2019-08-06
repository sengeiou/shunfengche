package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/1/17
 * 作者：xjh
 */

public class BigHealthDetailBuyBean extends BaseData {


    /**
     * sign : b136d50f223ff4e7887bc536486638ff
     * order_price : 8000
     * order_sn : 2018080310572863404471
     * order_name : 购买大健康
     */

    private String sign;
    private float order_price;
    private String order_sn;
    private String order_name;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public float getOrder_price() {
        return order_price;
    }

    public void setOrder_price(float order_price) {
        this.order_price = order_price;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }
}
