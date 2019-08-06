package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/15
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 发布之后的数据类型
 */
public class PublicResultModel {

    /**
     * relate_id : 9
     * order_price : 0.01
     * order_name : 发布劳务工信息费用
     */

    private int relate_id;
    private double order_price;
    private String order_name;


    public int getRelate_id() {
        return relate_id;
    }

    public void setRelate_id(int relate_id) {
        this.relate_id = relate_id;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }
}
