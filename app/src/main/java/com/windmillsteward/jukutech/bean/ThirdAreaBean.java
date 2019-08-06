package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/2/2
 * 作者：xjh
 */

public class ThirdAreaBean extends BaseData {

    /**
     * third_area_name : 萝岗区
     * third_area_id : 3036
     */

    private String third_area_name;
    private int third_area_id;
    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getThird_area_name() {
        return third_area_name;
    }

    public void setThird_area_name(String third_area_name) {
        this.third_area_name = third_area_name;
    }

    public int getThird_area_id() {
        return third_area_id;
    }

    public void setThird_area_id(int third_area_id) {
        this.third_area_id = third_area_id;
    }

    @Override
    public String toString() {
        return third_area_name;
    }
}
