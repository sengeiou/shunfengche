package com.windmillsteward.jukutech.activity.home.personnel.model;

/**
 * @date: on 2018/10/11
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 工种model
 */
public class GongzhongModel {

    /**
     * work_type_id : 51
     * price : 200
     * name : 水泥工
     */

    private int work_type_id;
    private double price;
    private String name;
    private String description;
    private int position;
    private boolean select;
    private double info_fee;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getInfo_fee() {
        return info_fee;
    }

    public void setInfo_fee(double info_fee) {
        this.info_fee = info_fee;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getWork_type_id() {
        return work_type_id;
    }

    public void setWork_type_id(int work_type_id) {
        this.work_type_id = work_type_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object obj) {
        return ((GongzhongModel) obj).getWork_type_id()
                == getWork_type_id();
    }

    @Override
    public String toString() {
        return getName();
    }
}
