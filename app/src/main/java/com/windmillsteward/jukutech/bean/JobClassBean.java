package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/1/16
 * 作者：xjh
 */

public class JobClassBean extends BaseData {


    /**
     * job_class_id : 5
     * class_name : 客服
     */

    private int job_class_id;
    private String class_name;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getJob_class_id() {
        return job_class_id;
    }

    public void setJob_class_id(int job_class_id) {
        this.job_class_id = job_class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
}
