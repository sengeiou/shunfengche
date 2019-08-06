package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2018/10/23
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 家教上次发布的信息
 */
public class LastPublicForJiajiaoModel {

    /**
     * user_name : 巧克力
     * mobile_phone : 13227264607
     * record : {"birthday":"2018-10-23","coach_time":"1,2","user_name":"穆仙念","sex":1,"second_area_name":"深圳市","second_area_id":291,"coach_subject_id":"语文","salary":0.01,"education_background_id":24,"third_area_id":3058,"coach_grade_list":["小学"],"sex_name":"男","mobile_phone":"13211111111","self_intro":"哈哈","third_area_name":"南山区","coach_grade_ids":"57","coach_time_list":["周末","工作日"],"education_background_name":"初中"}
     * is_posted : 1
     */

    private String user_name;
    private String mobile_phone;
    private RecordBean record;
    private int is_posted;

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

    public RecordBean getRecord() {
        return record;
    }

    public void setRecord(RecordBean record) {
        this.record = record;
    }

    public int getIs_posted() {
        return is_posted;
    }

    public void setIs_posted(int is_posted) {
        this.is_posted = is_posted;
    }

    public static class RecordBean {
        /**
         * birthday : 2018-10-23
         * coach_time : 1,2
         * user_name : 穆仙念
         * sex : 1
         * second_area_name : 深圳市
         * second_area_id : 291
         * coach_subject_id : 语文
         * salary : 0.01
         * education_background_id : 24
         * third_area_id : 3058
         * coach_grade_list : ["小学"]
         * sex_name : 男
         * mobile_phone : 13211111111
         * self_intro : 哈哈
         * third_area_name : 南山区
         * coach_grade_ids : 57
         * coach_time_list : ["周末","工作日"]
         * education_background_name : 初中
         */

        private String birthday;
        private String coach_time;
        private String user_name;
        private int sex;
        private String second_area_name;
        private int second_area_id;
        private int coach_subject_id;
        private int salary;
        private int education_background_id;
        private int third_area_id;
        private String sex_name;
        private String mobile_phone;
        private String self_intro;
        private String third_area_name;
        private String coach_grade_ids;
        private String education_background_name;
        private List<String> coach_grade_list;
        private List<String> coach_time_list;
        private String user_avatar_url;
        private int age;
        private String coach_subject_name;
        private int end_salary_fee;
        private int salary_type;

        public int getEnd_salary_fee() {
            return end_salary_fee;
        }

        public void setEnd_salary_fee(int end_salary_fee) {
            this.end_salary_fee = end_salary_fee;
        }

        public int getSalary_type() {
            return salary_type;
        }

        public void setSalary_type(int salary_type) {
            this.salary_type = salary_type;
        }

        public String getCoach_subject_name() {
            return coach_subject_name;
        }

        public void setCoach_subject_name(String coach_subject_name) {
            this.coach_subject_name = coach_subject_name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getCoach_time() {
            return coach_time;
        }

        public void setCoach_time(String coach_time) {
            this.coach_time = coach_time;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getSecond_area_name() {
            return second_area_name;
        }

        public void setSecond_area_name(String second_area_name) {
            this.second_area_name = second_area_name;
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

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public int getEducation_background_id() {
            return education_background_id;
        }

        public void setEducation_background_id(int education_background_id) {
            this.education_background_id = education_background_id;
        }

        public int getThird_area_id() {
            return third_area_id;
        }

        public void setThird_area_id(int third_area_id) {
            this.third_area_id = third_area_id;
        }

        public String getSex_name() {
            return sex_name;
        }

        public void setSex_name(String sex_name) {
            this.sex_name = sex_name;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }

        public String getSelf_intro() {
            return self_intro;
        }

        public void setSelf_intro(String self_intro) {
            this.self_intro = self_intro;
        }

        public String getThird_area_name() {
            return third_area_name;
        }

        public void setThird_area_name(String third_area_name) {
            this.third_area_name = third_area_name;
        }

        public String getCoach_grade_ids() {
            return coach_grade_ids;
        }

        public void setCoach_grade_ids(String coach_grade_ids) {
            this.coach_grade_ids = coach_grade_ids;
        }

        public String getEducation_background_name() {
            return education_background_name;
        }

        public void setEducation_background_name(String education_background_name) {
            this.education_background_name = education_background_name;
        }

        public List<String> getCoach_grade_list() {
            return coach_grade_list;
        }

        public void setCoach_grade_list(List<String> coach_grade_list) {
            this.coach_grade_list = coach_grade_list;
        }

        public List<String> getCoach_time_list() {
            return coach_time_list;
        }

        public void setCoach_time_list(List<String> coach_time_list) {
            this.coach_time_list = coach_time_list;
        }
    }
}
