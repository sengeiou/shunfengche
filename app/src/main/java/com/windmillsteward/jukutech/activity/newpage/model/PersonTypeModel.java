package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/20
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 保姆 月嫂 育儿嫂 任务类型
 */
public class PersonTypeModel {

    /**
     * person_type : 33
     * person_type_name : 初级保姆
     */

    private int person_type;
    private String person_type_name;
    private String description;
    private double price;
    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPerson_type() {
        return person_type;
    }

    public void setPerson_type(int person_type) {
        this.person_type = person_type;
    }

    public String getPerson_type_name() {
        return person_type_name;
    }

    public void setPerson_type_name(String person_type_name) {
        this.person_type_name = person_type_name;
    }

    @Override
    public String toString() {
        return person_type_name;
    }
}
