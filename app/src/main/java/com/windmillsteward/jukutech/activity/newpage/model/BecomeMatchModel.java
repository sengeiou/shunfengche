package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/16
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 劳工 特种工 保姆 月嫂 育儿嫂 职位匹配model
 */
public class BecomeMatchModel {

    /**
     * recruitment_id : 29
     * work_third_area_name : 天河区
     * update_time : 2018-10-10
     * latitude : 113.4064504678
     * title : 招聘水泥工3名,搬砖工4名,特级搬砖工4名,
     * work_second_area_name : 广州市
     * longitude : 23.1199587650
     */

    private int recruitment_id;
    private String work_third_area_name;
    private String update_time;
    private String latitude;
    private String title;
    private String work_second_area_name;
    private String longitude;
    private int relate_id;
    private String area_name;
    private long add_time;
    /**
     * second_area_id : 289
     * coach_subject_id : 55
     * add_time : 1523456453
     * third_area_id : 3040
     * coach_grade_id : 58
     */

    private int second_area_id;
    private int coach_subject_id;
    private int third_area_id;
    private int coach_grade_id;

    private int look_for_tutor_id;
    //求职招聘
    /**
     * job_class_id_two : 81
     * work_third_area_id : 3040
     * work_area_name : 广州市天河区
     * job_class_id_two_name : 人力资源
     * work_second_area_id : 289
     * job_resume_id : 41
     */

    private int job_class_id_two;
    private int work_third_area_id;
    private String work_area_name;
    private String job_class_id_two_name;
    private int work_second_area_id;
    private int job_resume_id;

    //钟点工
    /**
     * hour_matching_id : 1
     * recruit_number : 3
     */

    private int hour_matching_id;
    private int recruit_number;


    public int getLook_for_tutor_id() {
        return look_for_tutor_id;
    }

    public void setLook_for_tutor_id(int look_for_tutor_id) {
        this.look_for_tutor_id = look_for_tutor_id;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public int getRelate_id() {
        return relate_id;
    }

    public void setRelate_id(int relate_id) {
        this.relate_id = relate_id;
    }

    public int getRecruitment_id() {
        return recruitment_id;
    }

    public void setRecruitment_id(int recruitment_id) {
        this.recruitment_id = recruitment_id;
    }

    public String getWork_third_area_name() {
        return work_third_area_name;
    }

    public void setWork_third_area_name(String work_third_area_name) {
        this.work_third_area_name = work_third_area_name;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWork_second_area_name() {
        return work_second_area_name;
    }

    public void setWork_second_area_name(String work_second_area_name) {
        this.work_second_area_name = work_second_area_name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getSecond_area_id() {
        return second_area_id;
    }

    public void setSecond_area_id(int second_area_id) {
        this.second_area_id = second_area_id;
    }

    public int getCoach_subject_id() {
        return coach_subject_id;
    }

    public void setCoach_subject_id(int coach_subject_id) {
        this.coach_subject_id = coach_subject_id;
    }

    public int getThird_area_id() {
        return third_area_id;
    }

    public void setThird_area_id(int third_area_id) {
        this.third_area_id = third_area_id;
    }

    public int getCoach_grade_id() {
        return coach_grade_id;
    }

    public void setCoach_grade_id(int coach_grade_id) {
        this.coach_grade_id = coach_grade_id;
    }

    public int getJob_class_id_two() {
        return job_class_id_two;
    }

    public void setJob_class_id_two(int job_class_id_two) {
        this.job_class_id_two = job_class_id_two;
    }

    public int getWork_third_area_id() {
        return work_third_area_id;
    }

    public void setWork_third_area_id(int work_third_area_id) {
        this.work_third_area_id = work_third_area_id;
    }

    public String getWork_area_name() {
        return work_area_name;
    }

    public void setWork_area_name(String work_area_name) {
        this.work_area_name = work_area_name;
    }

    public String getJob_class_id_two_name() {
        return job_class_id_two_name;
    }

    public void setJob_class_id_two_name(String job_class_id_two_name) {
        this.job_class_id_two_name = job_class_id_two_name;
    }

    public int getWork_second_area_id() {
        return work_second_area_id;
    }

    public void setWork_second_area_id(int work_second_area_id) {
        this.work_second_area_id = work_second_area_id;
    }

    public int getJob_resume_id() {
        return job_resume_id;
    }

    public void setJob_resume_id(int job_resume_id) {
        this.job_resume_id = job_resume_id;
    }

    public int getHour_matching_id() {
        return hour_matching_id;
    }

    public void setHour_matching_id(int hour_matching_id) {
        this.hour_matching_id = hour_matching_id;
    }

    public int getRecruit_number() {
        return recruit_number;
    }

    public void setRecruit_number(int recruit_number) {
        this.recruit_number = recruit_number;
    }
}
