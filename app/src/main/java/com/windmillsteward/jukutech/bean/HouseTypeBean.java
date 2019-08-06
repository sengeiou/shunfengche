package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/3/15/015
 * 作者：xjh
 */
public class HouseTypeBean extends BaseData {


    /**
     * house_type_id : 39
     * house_type_name : 住宅
     */

    private int house_type_id;
    private String house_type_name;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getHouse_type_id() {
        return house_type_id;
    }

    public void setHouse_type_id(int house_type_id) {
        this.house_type_id = house_type_id;
    }

    public String getHouse_type_name() {
        return house_type_name;
    }

    public void setHouse_type_name(String house_type_name) {
        this.house_type_name = house_type_name;
    }
}
