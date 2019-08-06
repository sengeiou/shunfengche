package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/1/27/027
 * 作者：xjh
 */
public class PriceBean extends BaseData {


    /**
     * price_name : ¥500以上
     * price_id : 50
     */

    private String price_name;
    private int price_id;

    public String getPrice_name() {
        return price_name;
    }

    public void setPrice_name(String price_name) {
        this.price_name = price_name;
    }

    public int getPrice_id() {
        return price_id;
    }

    public void setPrice_id(int price_id) {
        this.price_id = price_id;
    }
}
