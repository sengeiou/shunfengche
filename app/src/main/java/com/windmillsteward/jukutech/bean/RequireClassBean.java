package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/1/19
 * 作者：xjh
 */

public class RequireClassBean extends BaseData {


    /**
     * require_class_id : 21
     * class_name : 宠物寄养
     */

    private int require_class_id;
    private int index_type;//1订餐2酒店3门票
    private String class_name;
    private String image;
    private String price;
    private String info_fee;
    private int two_address;//	是否显示两个地址 0否 1是
    private int is_ad;//	是否是广告发布 0否 1是
    private boolean select;

    public int getIndex_type() {
        return index_type;
    }

    public void setIndex_type(int index_type) {
        this.index_type = index_type;
    }

    public int getIs_ad() {
        return is_ad;
    }

    public void setIs_ad(int is_ad) {
        this.is_ad = is_ad;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getTwo_address() {
        return two_address;
    }

    public void setTwo_address(int two_address) {
        this.two_address = two_address;
    }

    public String getInfo_fee() {
        return info_fee;
    }

    public void setInfo_fee(String info_fee) {
        this.info_fee = info_fee;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRequire_class_id() {
        return require_class_id;
    }

    public void setRequire_class_id(int require_class_id) {
        this.require_class_id = require_class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    @Override
    public String toString() {
        return class_name;
    }
}
