package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/3/7/007
 * 作者：xjh
 */
public class RequirePayResultBean extends BaseData {

    /**
     * require_id : 30
     * issuse_order_sn : 2018030702224826798215
     * order_price : 500
     * commission_order_sn : 2018030702224800849481
     * order_sn : 2018030702224884937441
     * order_name : 智慧家庭必须是智慧的
     */

    private int require_id;
    private String issuse_order_sn;
    private String order_price;
    private String commission_order_sn;
    private String order_sn;
    private String order_name;

    public int getRequire_id() {
        return require_id;
    }

    public void setRequire_id(int require_id) {
        this.require_id = require_id;
    }

    public String getIssuse_order_sn() {
        return issuse_order_sn;
    }

    public void setIssuse_order_sn(String issuse_order_sn) {
        this.issuse_order_sn = issuse_order_sn;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public String getCommission_order_sn() {
        return commission_order_sn;
    }

    public void setCommission_order_sn(String commission_order_sn) {
        this.commission_order_sn = commission_order_sn;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }
}
