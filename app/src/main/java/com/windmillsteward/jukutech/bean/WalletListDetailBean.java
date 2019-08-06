package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：钱包明细详情实体
 * author:cyq
 * 2018-03-13
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class WalletListDetailBean extends BaseData {

    private int detail_id;//明细id
    private double price;//价格
    private int add_time;//时间
    private String status_name;//类型名称
    private String order_sn;//交易单号
    private String payment_name;//交易方式
    private double current;//交易余额
    private String title;//名称/标题
    private int detail_type;//类型：1:是其它模块， 2：是提现。注： 如果detail_type=2，金额显示负，detail_type=1的话看transaction_type字段
    private int transaction_type;//其它模块的类型：1.收入，2.支出，3.退款


    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getPayment_name() {
        return payment_name;
    }

    public void setPayment_name(String payment_name) {
        this.payment_name = payment_name;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDetail_type() {
        return detail_type;
    }

    public void setDetail_type(int detail_type) {
        this.detail_type = detail_type;
    }

    public int getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(int transaction_type) {
        this.transaction_type = transaction_type;
    }
}
