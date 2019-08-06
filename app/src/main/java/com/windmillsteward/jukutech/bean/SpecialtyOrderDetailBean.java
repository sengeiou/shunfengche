package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class SpecialtyOrderDetailBean extends BaseData {

    /**
     * logistics_single_number : 700357097585
     * deliver_time : 0
     * address :
     * user_name : 小六
     * total_orig_fee : 308.6
     * payment_time : 0
     * store_change_fee : 0
     * address_id : 1
     * total_pay_fee : 308.6
     * order_status : 4
     * commodity_list : [{"commodity_id":1,"commodity_cover_picture":"http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","commodity_price":188,"commodity_num":1,"order_commodity_id":5,"commodity_model_name":""},{"commodity_id":2,"commodity_cover_picture":"qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg","commodity_price":98,"commodity_num":1,"order_commodity_id":6,"commodity_model_name":""}]
     * freight_fee : 22.6
     * mobile_phone : 1379009763
     * total_commodity_num : 2
     * store_name : 啊强特产店
     * order_id : 5
     * add_time : 1522652659
     * order_sn : 2018040203041965062287
     */

    private String logistics_single_number;
    private int deliver_time;
    private String address;
    private String user_name;
    private String total_orig_fee;
    private String total_coupon_fee;
    private int payment_time;
    private String store_change_fee;
    private int address_id;
    private String total_pay_fee;
    private int order_status;
    private String freight_fee;
    private String mobile_phone;
    private int total_commodity_num;
    private String store_name;
    private int store_id;
    private int order_id;
    private int add_time;
    private String order_sn;
    private int close_day;
    private List<CommodityListBean> commodity_list;

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getClose_day() {
        return close_day;
    }

    public void setClose_day(int close_day) {
        this.close_day = close_day;
    }

    public String getTotal_coupon_fee() {
        return total_coupon_fee;
    }

    public void setTotal_coupon_fee(String total_coupon_fee) {
        this.total_coupon_fee = total_coupon_fee;
    }

    public String getLogistics_single_number() {
        return logistics_single_number;
    }

    public void setLogistics_single_number(String logistics_single_number) {
        this.logistics_single_number = logistics_single_number;
    }

    public int getDeliver_time() {
        return deliver_time;
    }

    public void setDeliver_time(int deliver_time) {
        this.deliver_time = deliver_time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTotal_orig_fee() {
        return total_orig_fee;
    }

    public void setTotal_orig_fee(String total_orig_fee) {
        this.total_orig_fee = total_orig_fee;
    }

    public int getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(int payment_time) {
        this.payment_time = payment_time;
    }

    public String getStore_change_fee() {
        return store_change_fee;
    }

    public void setStore_change_fee(String store_change_fee) {
        this.store_change_fee = store_change_fee;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getTotal_pay_fee() {
        return total_pay_fee;
    }

    public void setTotal_pay_fee(String total_pay_fee) {
        this.total_pay_fee = total_pay_fee;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getFreight_fee() {
        return freight_fee;
    }

    public void setFreight_fee(String freight_fee) {
        this.freight_fee = freight_fee;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public int getTotal_commodity_num() {
        return total_commodity_num;
    }

    public void setTotal_commodity_num(int total_commodity_num) {
        this.total_commodity_num = total_commodity_num;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
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

    public List<CommodityListBean> getCommodity_list() {
        return commodity_list;
    }

    public void setCommodity_list(List<CommodityListBean> commodity_list) {
        this.commodity_list = commodity_list;
    }

    public static class CommodityListBean {
        /**
         * commodity_id : 1
         * commodity_cover_picture : http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/1810131522139262399icon_closet.jpg
         * commodity_price : 188
         * commodity_num : 1
         * order_commodity_id : 5
         * commodity_model_name :
         */

        private int commodity_id;
        private String commodity_title;
        private String commodity_cover_picture;
        private String commodity_price;
        private int commodity_num;
        private int order_commodity_id;
        private String commodity_model_name;
        private String apply_status;
        private int is_apply_refund;

        public int getIs_apply_refund() {
            return is_apply_refund;
        }

        public String getCommodity_title() {
            return commodity_title;
        }

        public void setCommodity_title(String commodity_title) {
            this.commodity_title = commodity_title;
        }

        public void setIs_apply_refund(int is_apply_refund) {
            this.is_apply_refund = is_apply_refund;
        }

        public String getApply_status() {

            return apply_status;
        }

        public void setApply_status(String apply_status) {
            this.apply_status = apply_status;
        }

        public int getCommodity_id() {
            return commodity_id;
        }

        public void setCommodity_id(int commodity_id) {
            this.commodity_id = commodity_id;
        }

        public String getCommodity_cover_picture() {
            return commodity_cover_picture;
        }

        public void setCommodity_cover_picture(String commodity_cover_picture) {
            this.commodity_cover_picture = commodity_cover_picture;
        }

        public String getCommodity_price() {
            return commodity_price;
        }

        public void setCommodity_price(String commodity_price) {
            this.commodity_price = commodity_price;
        }

        public int getCommodity_num() {
            return commodity_num;
        }

        public void setCommodity_num(int commodity_num) {
            this.commodity_num = commodity_num;
        }

        public int getOrder_commodity_id() {
            return order_commodity_id;
        }

        public void setOrder_commodity_id(int order_commodity_id) {
            this.order_commodity_id = order_commodity_id;
        }

        public String getCommodity_model_name() {
            return commodity_model_name;
        }

        public void setCommodity_model_name(String commodity_model_name) {
            this.commodity_model_name = commodity_model_name;
        }
    }
}
