package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/3/3/003
 * 作者：xjh
 */
public class OrderFamilyDetailBean extends BaseData {


    /**
     * total_coupon_fee : 100
     * order_detail_status : 4
     * status_name : 已完成
     * hosting_sn :
     * total_pay_fee : 100
     * order_name : 智慧家庭 溜猫
     * order_status : 4
     * mobile_phone : 13790097063
     * total_discount_fee : 100
     * nickname : 小铭
     * order_id : 1
     * add_time : 1520042770
     * order_sn : 20180106992837400001
     */

    private String total_coupon_fee;
    private int order_detail_status;
    private String status_name;
    private String hosting_sn;
    private String total_pay_fee;
    private String order_name;
    private int order_status;
    private String mobile_phone;
    private String total_discount_fee;
    private String nickname;
    private int order_id;
    private int add_time;
    private String order_sn;
    private int require_id;

    public int getRequire_id() {
        return require_id;
    }

    public void setRequire_id(int require_id) {
        this.require_id = require_id;
    }

    public String getTotal_coupon_fee() {
        return total_coupon_fee;
    }

    public void setTotal_coupon_fee(String total_coupon_fee) {
        this.total_coupon_fee = total_coupon_fee;
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

    public String getHosting_sn() {
        return hosting_sn;
    }

    public void setHosting_sn(String hosting_sn) {
        this.hosting_sn = hosting_sn;
    }

    public String getTotal_pay_fee() {
        return total_pay_fee;
    }

    public void setTotal_pay_fee(String total_pay_fee) {
        this.total_pay_fee = total_pay_fee;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getTotal_discount_fee() {
        return total_discount_fee;
    }

    public void setTotal_discount_fee(String total_discount_fee) {
        this.total_discount_fee = total_discount_fee;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }
}
