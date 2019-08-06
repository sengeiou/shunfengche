package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：城市实体
 * author:cyq
 * 2018-03-02
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class CityBean extends BaseData {

    private int area_id;//城市id
    private String area_name = "";//城市名字

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }
}
