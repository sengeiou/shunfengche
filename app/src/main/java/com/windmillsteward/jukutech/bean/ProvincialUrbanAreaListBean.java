package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class ProvincialUrbanAreaListBean extends BaseData {


    /**
     * area_name : 江苏省
     * parent_id : 0
     * area_id : 10
     * area_level : 1
     */

    private String area_name;
    private int parent_id;
    private int area_id;
    private int area_level;

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public int getArea_level() {
        return area_level;
    }

    public void setArea_level(int area_level) {
        this.area_level = area_level;
    }
}
