package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class AddOrderListBean extends BaseData {

    private String total_pay_fee;
    private List<OrderListBean> order_list;

    public String getTotal_pay_fee() {
        return total_pay_fee;
    }

    public void setTotal_pay_fee(String total_pay_fee) {
        this.total_pay_fee = total_pay_fee;
    }

    public List<OrderListBean> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<OrderListBean> order_list) {
        this.order_list = order_list;
    }

    public static class OrderListBean {
        /**
         * store_id : 2
         * commodity_list : [{"commodity_title":"正宗农家咸鸡蛋","commodity_id":2,"commodity_cover_picture":null,"commodity_price":89,"freight_stencil_id":2,"commodity_weight":1000,"commodity_num":2,"commodity_model_name":"正宗农家咸鸡蛋"}]
         * freight_fee : 12
         * store_name : 牛6
         * total_pay_fee : 190
         */

        private int store_id;
        private String freight_fee;
        private String store_name;
        private String total_pay_fee;
        private List<CommodityListBean> commodity_list;

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getFreight_fee() {
            return freight_fee;
        }

        public void setFreight_fee(String freight_fee) {
            this.freight_fee = freight_fee;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getTotal_pay_fee() {
            return total_pay_fee;
        }

        public void setTotal_pay_fee(String total_pay_fee) {
            this.total_pay_fee = total_pay_fee;
        }

        public List<CommodityListBean> getCommodity_list() {
            return commodity_list;
        }

        public void setCommodity_list(List<CommodityListBean> commodity_list) {
            this.commodity_list = commodity_list;
        }

        public static class CommodityListBean {
            /**
             * commodity_title : 正宗农家咸鸡蛋
             * commodity_id : 2
             * commodity_cover_picture : null
             * commodity_price : 89
             * freight_stencil_id : 2
             * commodity_weight : 1000
             * commodity_num : 2
             * commodity_model_name : 正宗农家咸鸡蛋
             */

            private String commodity_title;
            private int commodity_id;
            private Object commodity_cover_picture;
            private int commodity_price;
            private int freight_stencil_id;
            private int commodity_weight;
            private int commodity_num;
            private String commodity_model_name;

            public String getCommodity_title() {
                return commodity_title;
            }

            public void setCommodity_title(String commodity_title) {
                this.commodity_title = commodity_title;
            }

            public int getCommodity_id() {
                return commodity_id;
            }

            public void setCommodity_id(int commodity_id) {
                this.commodity_id = commodity_id;
            }

            public Object getCommodity_cover_picture() {
                return commodity_cover_picture;
            }

            public void setCommodity_cover_picture(Object commodity_cover_picture) {
                this.commodity_cover_picture = commodity_cover_picture;
            }

            public int getCommodity_price() {
                return commodity_price;
            }

            public void setCommodity_price(int commodity_price) {
                this.commodity_price = commodity_price;
            }

            public int getFreight_stencil_id() {
                return freight_stencil_id;
            }

            public void setFreight_stencil_id(int freight_stencil_id) {
                this.freight_stencil_id = freight_stencil_id;
            }

            public int getCommodity_weight() {
                return commodity_weight;
            }

            public void setCommodity_weight(int commodity_weight) {
                this.commodity_weight = commodity_weight;
            }

            public int getCommodity_num() {
                return commodity_num;
            }

            public void setCommodity_num(int commodity_num) {
                this.commodity_num = commodity_num;
            }

            public String getCommodity_model_name() {
                return commodity_model_name;
            }

            public void setCommodity_model_name(String commodity_model_name) {
                this.commodity_model_name = commodity_model_name;
            }
        }
    }
}
