package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class EditShoppingCartListBean extends BaseData {
    private int cart_commodity_id;
    private int commodity_num;
    private int commodity_model_id;

    public int getCart_commodity_id() {
        return cart_commodity_id;
    }

    public void setCart_commodity_id(int cart_commodity_id) {
        this.cart_commodity_id = cart_commodity_id;
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
}
