package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/20
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 从业经验model
 */
public class WorkExperienceModel {
    /**
     * work_experience : 29
     * work_experience_name : 一年以下
     */

    private int work_experience;
    private String work_experience_name;
    private boolean select;

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getWork_experience() {
        return work_experience;
    }

    public void setWork_experience(int work_experience) {
        this.work_experience = work_experience;
    }

    public String getWork_experience_name() {
        return work_experience_name;
    }

    public void setWork_experience_name(String work_experience_name) {
        this.work_experience_name = work_experience_name;
    }

    @Override
    public String toString() {
        return work_experience_name;
    }
}
