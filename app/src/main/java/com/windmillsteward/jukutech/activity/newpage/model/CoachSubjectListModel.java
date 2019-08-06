package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/23
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 辅导科目列表model
 */
public class CoachSubjectListModel {

    /**
     * coach_subject_name : 语文
     * coach_subject_id : 54
     */

    private String coach_subject_name;
    private int coach_subject_id;
    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getCoach_subject_name() {
        return coach_subject_name;
    }

    public void setCoach_subject_name(String coach_subject_name) {
        this.coach_subject_name = coach_subject_name;
    }

    public int getCoach_subject_id() {
        return coach_subject_id;
    }

    public void setCoach_subject_id(int coach_subject_id) {
        this.coach_subject_id = coach_subject_id;
    }

    @Override
    public String toString() {
        return coach_subject_name;
    }
}
