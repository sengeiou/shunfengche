package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/1/30
 * 作者：xjh
 */

public class HousePriceBean extends BaseData {


    /**
     * house_price_name : 1500-2000元
     * house_price_id : 19
     */

    private String house_price_name;
    private int house_price_id;

    public String getHouse_price_name() {
        return house_price_name;
    }

    public void setHouse_price_name(String house_price_name) {
        this.house_price_name = house_price_name;
    }

    public int getHouse_price_id() {
        return house_price_id;
    }

    public void setHouse_price_id(int house_price_id) {
        this.house_price_id = house_price_id;
    }
}
