package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：人才驿站-职位模块-首页职业分类
 * author:cyq
 * 2018-07-31
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class HomePositionClassBean extends BaseData {

    private int job_class_id_three;//职业分类三级id
    private int type;//1劳务中心2其它
    private String class_name;//职业分类名字
    private String image;//职业分类图标

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getJob_class_id_three() {
        return job_class_id_three;
    }

    public void setJob_class_id_three(int job_class_id_three) {
        this.job_class_id_three = job_class_id_three;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }
}
