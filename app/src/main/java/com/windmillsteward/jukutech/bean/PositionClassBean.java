package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/5/16/016
 * 作者：xjh
 */
public class PositionClassBean extends BaseData {


    private boolean isSelect;
    private int job_class_id_two;
    private String image;
    private int job_class_id;
    private String job_class_two_name;
    private int parent_id;
    private String job_class_one_name;
    private int job_class_id_one;
    private int type;
    private String class_name;


    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public int getJob_class_id_two() {
        return job_class_id_two;
    }

    public void setJob_class_id_two(int job_class_id_two) {
        this.job_class_id_two = job_class_id_two;
    }

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

    public String getJob_class_two_name() {
        return job_class_two_name;
    }

    public void setJob_class_two_name(String job_class_two_name) {
        this.job_class_two_name = job_class_two_name;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getJob_class_one_name() {
        return job_class_one_name;
    }

    public void setJob_class_one_name(String job_class_one_name) {
        this.job_class_one_name = job_class_one_name;
    }

    public int getJob_class_id_one() {
        return job_class_id_one;
    }

    public void setJob_class_id_one(int job_class_id_one) {
        this.job_class_id_one = job_class_id_one;
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
