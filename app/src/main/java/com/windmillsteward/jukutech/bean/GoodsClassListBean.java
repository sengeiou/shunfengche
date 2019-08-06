package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/4/23/023
 * 作者：xjh
 */
public class GoodsClassListBean extends BaseData {
    private int commodity_category_id;
    private String commodity_category_name;

    public int getCommodity_category_id() {
        return commodity_category_id;
    }

    public void setCommodity_category_id(int commodity_category_id) {
        this.commodity_category_id = commodity_category_id;
    }

    public String getCommodity_category_name() {
        return commodity_category_name;
    }

    public void setCommodity_category_name(String commodity_category_name) {
        this.commodity_category_name = commodity_category_name;
    }
}
