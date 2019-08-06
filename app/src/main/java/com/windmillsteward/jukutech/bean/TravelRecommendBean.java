package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：旅游推荐实体
 * author:cyq
 * 2018-02-06
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class TravelRecommendBean extends BaseData {

    private List<ListBean> list;
    private String travel_type_left_name;//左边推荐旅游类型名字
    private String travel_type_right_name;//右边推荐旅游类型名字


    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public String getTravel_type_left_name() {
        return travel_type_left_name;
    }

    public void setTravel_type_left_name(String travel_type_left_name) {
        this.travel_type_left_name = travel_type_left_name;
    }

    public String getTravel_type_right_name() {
        return travel_type_right_name;
    }

    public void setTravel_type_right_name(String travel_type_right_name) {
        this.travel_type_right_name = travel_type_right_name;
    }

    public static class ListBean{


    private String cover_url;
    private int travel_id;
    private int type;
    private int resource_id;

    public int getResource_id() {
        return resource_id;
    }

    public void setResource_id(int resource_id) {
        this.resource_id = resource_id;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public int getTravel_id() {
        return travel_id;
    }

    public void setTravel_id(int travel_id) {
        this.travel_id = travel_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    }
}
