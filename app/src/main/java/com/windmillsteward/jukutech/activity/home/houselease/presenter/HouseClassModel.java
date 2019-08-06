package com.windmillsteward.jukutech.activity.home.houselease.presenter;

/**
 * @date: on 2018/10/9
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 房屋分类
 */
public class HouseClassModel {

    /**
     * image :
     * house_type_id : 6
     * house_type_name : 住宅
     */

    private String image;
    private int house_type_id;
    private String house_type_name;

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
