package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/23
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 辅导年级列表model
 */
public class CoachGradeListModel {

    /**
     * coach_grade_name : 小学
     * coach_grade_id : 57
     */

    private String coach_grade_name;
    private int coach_grade_id;
    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getCoach_grade_name() {
        return coach_grade_name;
    }

    public void setCoach_grade_name(String coach_grade_name) {
        this.coach_grade_name = coach_grade_name;
    }

    public int getCoach_grade_id() {
        return coach_grade_id;
    }

    public void setCoach_grade_id(int coach_grade_id) {
        this.coach_grade_id = coach_grade_id;
    }

    @Override
    public String toString() {
        return coach_grade_name;
    }
}
