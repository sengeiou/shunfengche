package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/25
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class WorkYearModel {

    /**
     * work_year_name : 一年以下
     * work_year_id : 29
     */

    private String work_year_name;
    private int work_year_id;
    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getWork_year_name() {
        return work_year_name;
    }

    public void setWork_year_name(String work_year_name) {
        this.work_year_name = work_year_name;
    }

    public int getWork_year_id() {
        return work_year_id;
    }

    public void setWork_year_id(int work_year_id) {
        this.work_year_id = work_year_id;
    }

    @Override
    public String toString() {
        return work_year_name;
    }
}
