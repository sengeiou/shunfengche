package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/3/22/022
 * 作者：xjh
 */
public class RecommendCarListBean extends BaseData {


    /**
     * cover_url :
     * license_time : 2017年12月
     * price : 50
     * name : 奔驰
     * car_id : 1
     * mileage : 67
     */

    private String cover_url;
    private String license_time;
    private String price;
    private String name;
    private int car_id;
    private String mileage;

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getLicense_time() {
        return license_time;
    }

    public void setLicense_time(String license_time) {
        this.license_time = license_time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
}
