package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/4/22/022
 * 作者：xjh
 */
public class AfterSalesDetailBean extends BaseData {


    /**
     * logistics_single_number :
     * processing_time : 0
     * commodity_price : 123.0
     * refund_way_name : 退到钱包
     * receiving_time : 0
     * commodity_model_name : 不知道
     * store_remark :
     * price : 123.0
     * consignee_tel :
     * commodity_num : 1
     * refund_way : 1
     * commodity_title : 正宗农家咸鸭蛋
     * consignee :
     * address :
     * commodity_cover_picture : http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/20180415153805-55046-ec37ce1556cbcc5dcdbeecb22a740d0e.jpg
     * credentials_urls : ["http://sfcgj.oss-cn-qingdao.aliyuncs.com/3030001524304251376IMG_20180421_093247.jpg","http://sfcgj.oss-cn-qingdao.aliyuncs.com/01963815243042575809fdabc67b16bd0584c1166e20c5a81a0.jpg"]
     * defeated_time : 0
     * refund_sn : 20180421055103369782
     * finish_time : 0
     * refused_time : 0
     * reason_id : 1
     * user_remark : 欢乐汇哦外婆
     * user_deliver_time : 0
     * reason_name : 销售
     * add_time : 1524304263
     * order_sn : 2018041803360353952289
     * status : 1
     */

    private String logistics_single_number;
    private int processing_time;
    private String commodity_price;
    private String refund_way_name;
    private int receiving_time;
    private String commodity_model_name;
    private String store_remark;
    private String price;
    private String consignee_tel;
    private int commodity_num;
    private int refund_way;
    private String commodity_title;
    private String consignee;
    private String address;
    private String commodity_cover_picture;
    private int defeated_time;
    private String refund_sn;
    private int finish_time;
    private int refused_time;
    private int reason_id;
    private String user_remark;
    private int user_deliver_time;
    private String reason_name;
    private int add_time;
    private String order_sn;
    private int status;
    private List<String> credentials_urls;

    public String getLogistics_single_number() {
        return logistics_single_number;
    }

    public void setLogistics_single_number(String logistics_single_number) {
        this.logistics_single_number = logistics_single_number;
    }

    public int getProcessing_time() {
        return processing_time;
    }

    public void setProcessing_time(int processing_time) {
        this.processing_time = processing_time;
    }

    public String getCommodity_price() {
        return commodity_price;
    }

    public void setCommodity_price(String commodity_price) {
        this.commodity_price = commodity_price;
    }

    public String getRefund_way_name() {
        return refund_way_name;
    }

    public void setRefund_way_name(String refund_way_name) {
        this.refund_way_name = refund_way_name;
    }

    public int getReceiving_time() {
        return receiving_time;
    }

    public void setReceiving_time(int receiving_time) {
        this.receiving_time = receiving_time;
    }

    public String getCommodity_model_name() {
        return commodity_model_name;
    }

    public void setCommodity_model_name(String commodity_model_name) {
        this.commodity_model_name = commodity_model_name;
    }

    public String getStore_remark() {
        return store_remark;
    }

    public void setStore_remark(String store_remark) {
        this.store_remark = store_remark;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getConsignee_tel() {
        return consignee_tel;
    }

    public void setConsignee_tel(String consignee_tel) {
        this.consignee_tel = consignee_tel;
    }

    public int getCommodity_num() {
        return commodity_num;
    }

    public void setCommodity_num(int commodity_num) {
        this.commodity_num = commodity_num;
    }

    public int getRefund_way() {
        return refund_way;
    }

    public void setRefund_way(int refund_way) {
        this.refund_way = refund_way;
    }

    public String getCommodity_title() {
        return commodity_title;
    }

    public void setCommodity_title(String commodity_title) {
        this.commodity_title = commodity_title;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCommodity_cover_picture() {
        return commodity_cover_picture;
    }

    public void setCommodity_cover_picture(String commodity_cover_picture) {
        this.commodity_cover_picture = commodity_cover_picture;
    }

    public int getDefeated_time() {
        return defeated_time;
    }

    public void setDefeated_time(int defeated_time) {
        this.defeated_time = defeated_time;
    }

    public String getRefund_sn() {
        return refund_sn;
    }

    public void setRefund_sn(String refund_sn) {
        this.refund_sn = refund_sn;
    }

    public int getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(int finish_time) {
        this.finish_time = finish_time;
    }

    public int getRefused_time() {
        return refused_time;
    }

    public void setRefused_time(int refused_time) {
        this.refused_time = refused_time;
    }

    public int getReason_id() {
        return reason_id;
    }

    public void setReason_id(int reason_id) {
        this.reason_id = reason_id;
    }

    public String getUser_remark() {
        return user_remark;
    }

    public void setUser_remark(String user_remark) {
        this.user_remark = user_remark;
    }

    public int getUser_deliver_time() {
        return user_deliver_time;
    }

    public void setUser_deliver_time(int user_deliver_time) {
        this.user_deliver_time = user_deliver_time;
    }

    public String getReason_name() {
        return reason_name;
    }

    public void setReason_name(String reason_name) {
        this.reason_name = reason_name;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getCredentials_urls() {
        return credentials_urls;
    }

    public void setCredentials_urls(List<String> credentials_urls) {
        this.credentials_urls = credentials_urls;
    }
}
