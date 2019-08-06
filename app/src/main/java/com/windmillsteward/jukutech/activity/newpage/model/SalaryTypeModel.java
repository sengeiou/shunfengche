package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/25
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class SalaryTypeModel {
    /**
     * salary_id : 79
     * salary_name : 10000-20000
     */

    private int salary_id;
    private String salary_name;
    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getSalary_id() {
        return salary_id;
    }

    public void setSalary_id(int salary_id) {
        this.salary_id = salary_id;
    }

    public String getSalary_name() {
        return salary_name;
    }

    public void setSalary_name(String salary_name) {
        this.salary_name = salary_name;
    }

    @Override
    public String toString() {
        return salary_name;
    }
}
