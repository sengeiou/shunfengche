package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/3/7/007
 * 作者：xjh
 */
public class StayAndTravelSearchResultBean extends BaseData {


    /**
     * hotel_num : 2
     * travel_num : 1
     */

    private int hotel_num;
    private int travel_num;

    public int getHotel_num() {
        return hotel_num;
    }

    public void setHotel_num(int hotel_num) {
        this.hotel_num = hotel_num;
    }

    public int getTravel_num() {
        return travel_num;
    }

    public void setTravel_num(int travel_num) {
        this.travel_num = travel_num;
    }
}
