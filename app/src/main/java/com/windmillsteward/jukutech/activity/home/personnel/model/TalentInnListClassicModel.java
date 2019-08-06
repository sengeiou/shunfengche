package com.windmillsteward.jukutech.activity.home.personnel.model;

/**
 * @date: on 2018/10/5
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 人才驿站头部分类
 */
public class TalentInnListClassicModel {

    /**
     * class_image : http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png
     * require_class_id : 15
     * class_name : 劳务中介
     */

    private String class_image;
    private int require_class_id;
    private String class_name;

    public String getClass_image() {
        return class_image;
    }

    public void setClass_image(String class_image) {
        this.class_image = class_image;
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
}
