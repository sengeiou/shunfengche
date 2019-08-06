package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2018/10/19
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 简历详情
 */
public class JianliDetailsModel {

    /**
     * work_third_area_name : 天河区
     * work_type_ids : ["水泥工","搬砖工","清洁工"]
     * work_date : 2018-10-08
     * contact_person :
     * self_intro : 自我介绍: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
     * name : 啊超
     * contact_tel : 13727574859
     * match_value : 80
     * salary_type : 1
     * add_time : 2018-10-08
     * age : 29
     * user_avatar_url : ""
     */

    private String work_third_area_name;
    private String work_date;
    private String contact_person;
    private String self_intro;
    private String name;
    private String contact_tel;
    private String match_value;
    private int salary_type;
    private String add_time;
    private int age;
    private List<String> work_type_ids;
    private int sex;
    private String user_avatar_url;

    public String getUser_avatar_url() {
        return user_avatar_url;
    }

    public void setUser_avatar_url(String user_avatar_url) {
        this.user_avatar_url = user_avatar_url;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getWork_third_area_name() {
        return work_third_area_name;
    }

    public void setWork_third_area_name(String work_third_area_name) {
        this.work_third_area_name = work_third_area_name;
    }

    public String getWork_date() {
        return work_date;
    }

    public void setWork_date(String work_date) {
        this.work_date = work_date;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getSelf_intro() {
        return self_intro;
    }

    public void setSelf_intro(String self_intro) {
        this.self_intro = self_intro;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_tel() {
        return contact_tel;
    }

    public void setContact_tel(String contact_tel) {
        this.contact_tel = contact_tel;
    }

    public String getMatch_value() {
        return match_value;
    }

    public void setMatch_value(String match_value) {
        this.match_value = match_value;
    }

    public int getSalary_type() {
        return salary_type;
    }

    public void setSalary_type(int salary_type) {
        this.salary_type = salary_type;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getWork_type_ids() {
        return work_type_ids;
    }

    public void setWork_type_ids(List<String> work_type_ids) {
        this.work_type_ids = work_type_ids;
    }
}
