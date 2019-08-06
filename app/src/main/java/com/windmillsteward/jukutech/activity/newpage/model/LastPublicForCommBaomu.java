package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/20
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 保姆月嫂育儿嫂 已发布信息的model
 */
public class LastPublicForCommBaomu {

    /**
     * user_name : 巧克力
     * mobile_phone : 13227264607
     * record : {"require_type":1,"birthday":"2018-10-20","user_name":"穆仙念","person_type":33,"person_type_name":"初级保姆","sex":1,"work_time_type_name":"白班","second_area_name":"深圳市","service_content":"42,43,44","second_area_id":291,"education_background_id":28,"third_area_id":3058,"salary_fee":1.1,"sex_name":"男","work_time_type":1,"mobile_phone":"13211111111","self_intro":"哈哈哈","third_area_name":"南山区","age":0,"work_experience":29,"work_experience_name":"一年以下","education_background_name":"本科"}
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
         * require_type : 1
         * birthday : 2018-10-20
         * user_name : 穆仙念
         * person_type : 33
         * person_type_name : 初级保姆
         * sex : 1
         * work_time_type_name : 白班
         * second_area_name : 深圳市
         * service_content : 42,43,44
         * second_area_id : 291
         * education_background_id : 28
         * third_area_id : 3058
         * salary_fee : 1.1
         * sex_name : 男
         * work_time_type : 1
         * mobile_phone : 13211111111
         * self_intro : 哈哈哈
         * third_area_name : 南山区
         * age : 0
         * work_experience : 29
         * work_experience_name : 一年以下
         * education_background_name : 本科
         */

        private int require_type;
        private String birthday;
        private String user_name;
        private int person_type;
        private String person_type_name;
        private double person_type_price;
        private int sex;
        private String work_time_type_name;
        private String second_area_name;
        private String user_avatar_url;
        private String service_content;
        private int second_area_id;
        private int education_background_id;
        private int third_area_id;
        private int salary_fee;
        private String sex_name;
        private int work_time_type;
        private String mobile_phone;
        private String self_intro;
        private String third_area_name;
        private int age;
        private int work_experience;
        private String work_experience_name;
        private String education_background_name;
        private int end_salary_fee;
        private int salary_type;

        public double getPerson_type_price() {
            return person_type_price;
        }

        public void setPerson_type_price(double person_type_price) {
            this.person_type_price = person_type_price;
        }

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

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }

        public int getRequire_type() {
            return require_type;
        }

        public void setRequire_type(int require_type) {
            this.require_type = require_type;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getWork_time_type_name() {
            return work_time_type_name;
        }

        public void setWork_time_type_name(String work_time_type_name) {
            this.work_time_type_name = work_time_type_name;
        }

        public String getSecond_area_name() {
            return second_area_name;
        }

        public void setSecond_area_name(String second_area_name) {
            this.second_area_name = second_area_name;
        }

        public String getService_content() {
            return service_content;
        }

        public void setService_content(String service_content) {
            this.service_content = service_content;
        }

        public int getSecond_area_id() {
            return second_area_id;
        }

        public void setSecond_area_id(int second_area_id) {
            this.second_area_id = second_area_id;
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

        public int getSalary_fee() {
            return salary_fee;
        }

        public void setSalary_fee(int salary_fee) {
            this.salary_fee = salary_fee;
        }

        public String getSex_name() {
            return sex_name;
        }

        public void setSex_name(String sex_name) {
            this.sex_name = sex_name;
        }

        public int getWork_time_type() {
            return work_time_type;
        }

        public void setWork_time_type(int work_time_type) {
            this.work_time_type = work_time_type;
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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
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

        public String getEducation_background_name() {
            return education_background_name;
        }

        public void setEducation_background_name(String education_background_name) {
            this.education_background_name = education_background_name;
        }
    }
}
