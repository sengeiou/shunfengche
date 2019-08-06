package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/7/007
 * 作者：xjh
 */
public class PublishRequireModelBean extends BaseData {

    private String access_token;
    private int require_class_id;
    private String title;
    private String price;
    private String description;
    private int second_area_id;
    private int third_area_id;
    private String longitude;
    private String latitude;
    private List<String> pic_urls;
    private List<String> video_urls;
    private String video_cover;
    private int coupon_receive_id;
    private String order_sn;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getRequire_class_id() {
        return require_class_id;
    }

    public void setRequire_class_id(int require_class_id) {
        this.require_class_id = require_class_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSecond_area_id() {
        return second_area_id;
    }

    public void setSecond_area_id(int second_area_id) {
        this.second_area_id = second_area_id;
    }

    public int getThird_area_id() {
        return third_area_id;
    }

    public void setThird_area_id(int third_area_id) {
        this.third_area_id = third_area_id;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public List<String> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(List<String> pic_urls) {
        this.pic_urls = pic_urls;
    }

    public List<String> getVideo_urls() {
        return video_urls;
    }

    public void setVideo_urls(List<String> video_urls) {
        this.video_urls = video_urls;
    }

    public String getVideo_cover() {
        return video_cover;
    }

    public void setVideo_cover(String video_cover) {
        this.video_cover = video_cover;
    }

    public int getCoupon_receive_id() {
        return coupon_receive_id;
    }

    public void setCoupon_receive_id(int coupon_receive_id) {
        this.coupon_receive_id = coupon_receive_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }
}
