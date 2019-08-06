package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/20
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 学历model
 */
public class EducationModel {

    /**
     * education_background_id : 24
     * education_background_name : 初中
     */

    private int education_background_id;
    private String education_background_name;
    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getEducation_background_id() {
        return education_background_id;
    }

    public void setEducation_background_id(int education_background_id) {
        this.education_background_id = education_background_id;
    }

    public String getEducation_background_name() {
        return education_background_name;
    }

    public void setEducation_background_name(String education_background_name) {
        this.education_background_name = education_background_name;
    }

    @Override
    public String toString() {
        return education_background_name;
    }
}
