package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/1/14/014
 * 作者：xjh
 */
public class WelfareBean extends BaseData {

    /**
     * benefit_name : 租房补贴
     * benefit_id : 2
     */

    private String benefit_name;
    private int benefit_id;

    public String getBenefit_name() {
        return benefit_name;
    }

    public void setBenefit_name(String benefit_name) {
        this.benefit_name = benefit_name;
    }

    public int getBenefit_id() {
        return benefit_id;
    }

    public void setBenefit_id(int benefit_id) {
        this.benefit_id = benefit_id;
    }
}
