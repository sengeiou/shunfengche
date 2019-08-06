package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/17
 * 作者：xjh
 */

public class IntelligentFamilyDetailBean extends BaseData {

    /**
     * video_cover : http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/6032601519181866417video_cover.jpg
     * require_id : 20
     * distance : 1171
     * second_area_id : 289
     * description : 给听听特股民牛
     * o_user_name :
     * title : 哈哈哈哈哈哈
     * is_hosting : 0
     * require_area : 广州市海珠区
     * is_myorder : 0
     * is_collect : 1
     * update_time : 1519181868
     * price : 1254648
     * third_area_name : 海珠区
     * require_class_name : 看店
     * nickname : 大海
     * view_num : 22
     * second_area_name : 广州市
     * pic_urls : []
     * third_area_id : 3041
     * user_avatar_url :
     * o_mobile_phone :
     * hosting_show :
     * user_id : 11
     * require_class_id : 19
     * info_fee : 19
     * video_url : ["http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/5936101519181866751recording135794090.mp4"]
     */

    private String video_cover;
    private int require_id;
    private int distance;
    private int second_area_id;
    private String description;
    private String o_user_name;
    private String title;
    private int is_hosting;
    private String require_area;
    private int is_myorder;
    private int is_collect;
    private int update_time;
    private String price;
    private String info_fee;
    private String third_area_name;
    private String require_class_name;
    private String nickname;
    private int view_num;
    private String second_area_name;
    private int third_area_id;
    private String user_avatar_url;
    private String o_mobile_phone;
    private String hosting_show;
    private int user_id;
    private int require_class_id;
    private List<String> pic_urls;
    private String video_url;
    private int order_detail_status;
    private String class_name;
    private String longitude;
    private String latitude;
    private String t_longitude;
    private String t_latitude;
    private String t_address;
    private String address ="";
    private String share_url ="";
    private int is_ad;//是否是广告发布，0否1是

    public int getIs_ad() {
        return is_ad;
    }

    public void setIs_ad(int is_ad) {
        this.is_ad = is_ad;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getT_longitude() {
        return t_longitude;
    }

    public void setT_longitude(String t_longitude) {
        this.t_longitude = t_longitude;
    }

    public String getT_latitude() {
        return t_latitude;
    }

    public void setT_latitude(String t_latitude) {
        this.t_latitude = t_latitude;
    }

    public String getT_address() {
        return t_address;
    }

    public void setT_address(String t_address) {
        this.t_address = t_address;
    }

    public String getInfo_fee() {
        return info_fee;
    }

    public void setInfo_fee(String info_fee) {
        this.info_fee = info_fee;
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

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getOrder_detail_status() {
        return order_detail_status;
    }

    public void setOrder_detail_status(int order_detail_status) {
        this.order_detail_status = order_detail_status;
    }

    public String getVideo_cover() {
        return video_cover;
    }

    public void setVideo_cover(String video_cover) {
        this.video_cover = video_cover;
    }

    public int getRequire_id() {
        return require_id;
    }

    public void setRequire_id(int require_id) {
        this.require_id = require_id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getSecond_area_id() {
        return second_area_id;
    }

    public void setSecond_area_id(int second_area_id) {
        this.second_area_id = second_area_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getO_user_name() {
        return o_user_name;
    }

    public void setO_user_name(String o_user_name) {
        this.o_user_name = o_user_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIs_hosting() {
        return is_hosting;
    }

    public void setIs_hosting(int is_hosting) {
        this.is_hosting = is_hosting;
    }

    public String getRequire_area() {
        return require_area;
    }

    public void setRequire_area(String require_area) {
        this.require_area = require_area;
    }

    public int getIs_myorder() {
        return is_myorder;
    }

    public void setIs_myorder(int is_myorder) {
        this.is_myorder = is_myorder;
    }

    public int getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
    }

    public int getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(int update_time) {
        this.update_time = update_time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getThird_area_name() {
        return third_area_name;
    }

    public void setThird_area_name(String third_area_name) {
        this.third_area_name = third_area_name;
    }

    public String getRequire_class_name() {
        return require_class_name;
    }

    public void setRequire_class_name(String require_class_name) {
        this.require_class_name = require_class_name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getView_num() {
        return view_num;
    }

    public void setView_num(int view_num) {
        this.view_num = view_num;
    }

    public String getSecond_area_name() {
        return second_area_name;
    }

    public void setSecond_area_name(String second_area_name) {
        this.second_area_name = second_area_name;
    }

    public int getThird_area_id() {
        return third_area_id;
    }

    public void setThird_area_id(int third_area_id) {
        this.third_area_id = third_area_id;
    }

    public String getUser_avatar_url() {
        return user_avatar_url;
    }

    public void setUser_avatar_url(String user_avatar_url) {
        this.user_avatar_url = user_avatar_url;
    }

    public String getO_mobile_phone() {
        return o_mobile_phone;
    }

    public void setO_mobile_phone(String o_mobile_phone) {
        this.o_mobile_phone = o_mobile_phone;
    }

    public String getHosting_show() {
        return hosting_show;
    }

    public void setHosting_show(String hosting_show) {
        this.hosting_show = hosting_show;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getRequire_class_id() {
        return require_class_id;
    }

    public void setRequire_class_id(int require_class_id) {
        this.require_class_id = require_class_id;
    }

    public List<String> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(List<String> pic_urls) {
        this.pic_urls = pic_urls;
    }


    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }
}
