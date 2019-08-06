package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/16
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 年龄范围
 */
public class AgeAreaModel {

    /**
     * name : 不限
     * age_id : 1
     */

    private String name;
    private int age_id;

    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge_id() {
        return age_id;
    }

    public void setAge_id(int age_id) {
        this.age_id = age_id;
    }

    @Override
    public String toString() {
        return name;
    }
}
