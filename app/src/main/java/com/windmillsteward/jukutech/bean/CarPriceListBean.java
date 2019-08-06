package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/3/22/022
 * 作者：xjh
 */
public class CarPriceListBean extends BaseData {

    /**
     * price_id : 51
     * name : 3万以下
     */

    private int price_id;
    private String name;

    public int getPrice_id() {
        return price_id;
    }

    public void setPrice_id(int price_id) {
        this.price_id = price_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
