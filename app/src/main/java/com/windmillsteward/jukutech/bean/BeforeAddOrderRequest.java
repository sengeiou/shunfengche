package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class BeforeAddOrderRequest extends BaseData {

    private String access_token;
    private int cart_id;
    private int address_id;
    private List<CommodityListBean> commodity_list;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public List<CommodityListBean> getListBeans() {
        return commodity_list;
    }

    public void setListBeans(List<CommodityListBean> commodity_list) {
        this.commodity_list = commodity_list;
    }

    public static class CommodityListBean {
        private int store_id;
        private int commodity_id;
        private int commodity_num;
        private int commodity_model_id;
        private int cart_commodity_id;

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public int getCommodity_id() {
            return commodity_id;
        }

        public void setCommodity_id(int commodity_id) {
            this.commodity_id = commodity_id;
        }

        public int getCommodity_num() {
            return commodity_num;
        }

        public void setCommodity_num(int commodity_num) {
            this.commodity_num = commodity_num;
        }

        public int getCommodity_model_id() {
            return commodity_model_id;
        }

        public void setCommodity_model_id(int commodity_model_id) {
            this.commodity_model_id = commodity_model_id;
        }

        public int getCart_commodity_id() {
            return cart_commodity_id;
        }

        public void setCart_commodity_id(int cart_commodity_id) {
            this.cart_commodity_id = cart_commodity_id;
        }
    }

}
