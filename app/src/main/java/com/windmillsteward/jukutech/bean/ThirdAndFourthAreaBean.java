package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/2/2
 * 作者：xjh
 */

public class ThirdAndFourthAreaBean extends BaseData {


    /**
     * third_area_name : 萝岗区
     * fourth_area_list : [{"fourth_area_id":28980,"fourth_area_name":"夏港街道"},{"fourth_area_id":28981,"fourth_area_name":"东区街道"},{"fourth_area_id":28982,"fourth_area_name":"联和街道"},{"fourth_area_id":28983,"fourth_area_name":"萝岗街道"},{"fourth_area_id":28984,"fourth_area_name":"九佛片区"},{"fourth_area_id":28985,"fourth_area_name":"镇龙片区"},{"fourth_area_id":28986,"fourth_area_name":"永和片区"}]
     * third_area_id : 3036
     */

    private String third_area_name;
    private int third_area_id;
    private List<FourthAreaBean> fourth_area_list;

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

    public List<FourthAreaBean> getFourth_area_list() {
        return fourth_area_list;
    }

    public void setFourth_area_list(List<FourthAreaBean> fourth_area_list) {
        this.fourth_area_list = fourth_area_list;
    }
}
