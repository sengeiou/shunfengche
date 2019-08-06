package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * Created by Administrator on 2018/2/10.
 */

public class HotelAndHouseHomeBean extends BaseData {


    /**
     * cover_url : http://img0.imgtn.bdimg.com/it/u=603474267,1877676771&fm=27&gp=0.jpg
     * address : 广州 花都区 花都区机场大道北花安中路3号 ，近新白云国际机场、机场南地铁站
     * hotel_type_name : 五星/豪华
     * third_area_name : 天河区
     * hotel_id : 2
     * second_area_name : 广州市
     * start_price : 408.0
     * hotel_name : 碧桂园空港凤凰酒店(广州新白云机场店)
     */

    private String cover_url;
    private String address;
    private String hotel_type_name;
    private String third_area_name;
    private int hotel_id;
    private String second_area_name;
    private double start_price;
    private String hotel_name;

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHotel_type_name() {
        return hotel_type_name;
    }

    public void setHotel_type_name(String hotel_type_name) {
        this.hotel_type_name = hotel_type_name;
    }

    public String getThird_area_name() {
        return third_area_name;
    }

    public void setThird_area_name(String third_area_name) {
        this.third_area_name = third_area_name;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getSecond_area_name() {
        return second_area_name;
    }

    public void setSecond_area_name(String second_area_name) {
        this.second_area_name = second_area_name;
    }

    public double getStart_price() {
        return start_price;
    }

    public void setStart_price(double start_price) {
        this.start_price = start_price;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }
}
