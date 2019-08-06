package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * 订餐酒店门票推荐列表
 */
public class DingcanRecommendListModel {


    /**
     * attribute_ids : 215,216,217,218
     * score : 4
     * service_url : http://shunfengche.qishare.cn/windmillsteward/user_api/user_center/smarthome_content_url?service_id=2
     * star : 3
     * service_name : 好久不见博山菜
     * review : 502
     * price : 12
     * service_id : 2
     * attributeList : ["7折用餐","返现","预订打折","每日特惠"]
     * description : <p>山东顺风车网络科技有限公司，是益杰集团2017年着力打造的同城生活类服务平台。业务范围涵盖智能制造、金融地产、物业服务、装修建材、影视传媒等多个领域,集团资本金2亿元。其宗旨是：以满足用户需求为己任，借助互联网+1+N大数据、云计算等共享技术，构建信息速度快、精准度高、全领域覆盖的智能生活体验网络平台，通过360°全方位提供信息、生活服务，实现“平台共赢、联盟智胜”，为用户创造财富价值。</p>
     * pic_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/8904731558690363283640x1136%201.jpg
     * add_time : 1548578406
     */

    private String attribute_ids;
    private String score;
    private String service_url;
    private int star;
    private String service_name;
    private int review;
    private String price;
    private int service_id;
    private String description;
    private String pic_url;
    private int add_time;
    private List<String> attributeList;

    public String getAttribute_ids() {
        return attribute_ids;
    }

    public void setAttribute_ids(String attribute_ids) {
        this.attribute_ids = attribute_ids;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getService_url() {
        return service_url;
    }

    public void setService_url(String service_url) {
        this.service_url = service_url;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public List<String> getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List<String> attributeList) {
        this.attributeList = attributeList;
    }
}
