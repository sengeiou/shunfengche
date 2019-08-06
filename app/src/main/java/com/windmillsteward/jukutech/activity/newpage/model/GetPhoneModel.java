package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/28
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class GetPhoneModel {

    /**
     * contact_person : 陈先生
     * contact_tel : 13846465656
     * uninterested_num : 0
     * resume_id : 152
     */

    private String contact_person;
    private String contact_tel;
    private int uninterested_num;
    private int resume_id;
    private String user_name;
    private String mobile_phone;
    private String true_name;

    public String getTrue_name() {
        return true_name;
    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getContact_tel() {
        return contact_tel;
    }

    public void setContact_tel(String contact_tel) {
        this.contact_tel = contact_tel;
    }

    public int getUninterested_num() {
        return uninterested_num;
    }

    public void setUninterested_num(int uninterested_num) {
        this.uninterested_num = uninterested_num;
    }

    public int getResume_id() {
        return resume_id;
    }

    public void setResume_id(int resume_id) {
        this.resume_id = resume_id;
    }
}
