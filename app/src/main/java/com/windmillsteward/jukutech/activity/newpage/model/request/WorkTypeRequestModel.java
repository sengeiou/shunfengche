package com.windmillsteward.jukutech.activity.newpage.model.request;

/**
 * @date: on 2018/10/17
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class WorkTypeRequestModel {

    /**
     * work_type_id : 51
     * num : 3
     * other_work_type : 特级搬砖工
     * price : 300
     */

    private int work_type_id;
    private int num;
    private String other_work_type;
    private double price;

    public int getWork_type_id() {
        return work_type_id;
    }

    public void setWork_type_id(int work_type_id) {
        this.work_type_id = work_type_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getOther_work_type() {
        return other_work_type;
    }

    public void setOther_work_type(String other_work_type) {
        this.other_work_type = other_work_type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
