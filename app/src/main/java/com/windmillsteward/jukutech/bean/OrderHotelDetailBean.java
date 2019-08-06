package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/3/003
 * 作者：xjh
 */
public class OrderHotelDetailBean extends BaseData {


    /**
     * area_name : 广州 番禺区 汉溪长隆地铁站C出口，奥园城市天地6栋1109
     * room_type_name : HelloKitty主题房
     * total_coupon_fee : 0
     * contact_person_list : ["达文西","凌凌漆"]
     * order_detail_status : 0
     * status_name : 未入住
     * latitude : 22.9930180000
     * contact_tel : 13790097065
     * total_pay_fee : 390
     * prepay_fee : 200
     * room_name_list : ["HelloKitty主题房","HelloKitty主题房"]
     * hotel_name : 钧玺主题公寓(广州汉溪长隆地铁站店)
     * confirm_qr : http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/9577261520046896687qr.jpg
     * total_discount_fee : 390
     * planed_start_time : 2018-03-04
     * planed_end_time : 2018-03-05
     * order_id : 18
     * order_sn : f5db9e05eb5942589bb5e79f30d389f1
     * longitude : 113.3301310000
     */

    private String area_name;
    private String room_type_name;
    private String total_coupon_fee;
    private int order_detail_status;
    private String status_name;
    private String latitude;
    private String contact_tel;
    private String total_pay_fee;
    private String prepay_fee;
    private String hotel_name;
    private String confirm_qr;
    private String total_discount_fee;
    private String planed_start_time;
    private String planed_end_time;
    private int order_id;
    private int night_num;
    private int hotel_id;

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getNight_num() {
        return night_num;
    }

    public void setNight_num(int night_num) {
        this.night_num = night_num;
    }

    private String order_sn;
    private String total_order_sn;
    private String longitude;
    private String confirm_code;
    private String hosting_sn;
    private List<String> contact_person_list;
    private List<String> room_name_list;

    public String getHosting_sn() {
        return hosting_sn;
    }

    public void setHosting_sn(String hosting_sn) {
        this.hosting_sn = hosting_sn;
    }

    public String getConfirm_code() {
        return confirm_code;
    }

    public void setConfirm_code(String confirm_code) {
        this.confirm_code = confirm_code;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public String getRoom_type_name() {
        return room_type_name;
    }

    public void setRoom_type_name(String room_type_name) {
        this.room_type_name = room_type_name;
    }

    public String getTotal_coupon_fee() {
        return total_coupon_fee;
    }

    public void setTotal_coupon_fee(String total_coupon_fee) {
        this.total_coupon_fee = total_coupon_fee;
    }

    public String getTotal_order_sn() {
        return total_order_sn;
    }

    public void setTotal_order_sn(String total_order_sn) {
        this.total_order_sn = total_order_sn;
    }

    public int getOrder_detail_status() {
        return order_detail_status;
    }

    public void setOrder_detail_status(int order_detail_status) {
        this.order_detail_status = order_detail_status;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getContact_tel() {
        return contact_tel;
    }

    public void setContact_tel(String contact_tel) {
        this.contact_tel = contact_tel;
    }

    public String getTotal_pay_fee() {
        return total_pay_fee;
    }

    public void setTotal_pay_fee(String total_pay_fee) {
        this.total_pay_fee = total_pay_fee;
    }

    public String getPrepay_fee() {
        return prepay_fee;
    }

    public void setPrepay_fee(String prepay_fee) {
        this.prepay_fee = prepay_fee;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getConfirm_qr() {
        return confirm_qr;
    }

    public void setConfirm_qr(String confirm_qr) {
        this.confirm_qr = confirm_qr;
    }

    public String getTotal_discount_fee() {
        return total_discount_fee;
    }

    public void setTotal_discount_fee(String total_discount_fee) {
        this.total_discount_fee = total_discount_fee;
    }

    public String getPlaned_start_time() {
        return planed_start_time;
    }

    public void setPlaned_start_time(String planed_start_time) {
        this.planed_start_time = planed_start_time;
    }

    public String getPlaned_end_time() {
        return planed_end_time;
    }

    public void setPlaned_end_time(String planed_end_time) {
        this.planed_end_time = planed_end_time;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<String> getContact_person_list() {
        return contact_person_list;
    }

    public void setContact_person_list(List<String> contact_person_list) {
        this.contact_person_list = contact_person_list;
    }

    public List<String> getRoom_name_list() {
        return room_name_list;
    }

    public void setRoom_name_list(List<String> room_name_list) {
        this.room_name_list = room_name_list;
    }
}
