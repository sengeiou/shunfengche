package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/24
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class LastPublicForZhongdgModel {

    /**
     * record : {"birthday":"1995-10-25","work_third_area_id":3040,"work_date":"2018-10-08","sex":1,"contact_tel":"13727574858","match_time":"18:00","work_third_area_name":"天河区","self_intro":"自我介绍: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx","service_id":"60,61,62,63","name":"钟点工-啊超","work_second_area_id":289,"work_first_area_id":19,"work_first_area_name":"广东省","work_second_area_name":"广州市"}
     * is_posted : 1
     */

    private RecordBean record;
    private int is_posted;

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
         * birthday : 1995-10-25
         * work_third_area_id : 3040
         * work_date : 2018-10-08
         * sex : 1
         * contact_tel : 13727574858
         * match_time : 18:00
         * work_third_area_name : 天河区
         * self_intro : 自我介绍: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
         * service_id : 60,61,62,63
         * name : 钟点工-啊超
         * work_second_area_id : 289
         * work_first_area_id : 19
         * work_first_area_name : 广东省
         * work_second_area_name : 广州市
         */

        private String birthday;
        private int work_third_area_id;
        private String work_date;
        private int sex;
        private String contact_tel;
        private String match_time;
        private String work_third_area_name;
        private String self_intro;
        private String service_id;
        private String name;
        private int work_second_area_id;
        private int work_first_area_id;
        private String work_first_area_name;
        private String work_second_area_name;
        private String user_avatar_url;
        private int age;
        private int salary_type;
        private int salary_fee;
        private int end_salary_fee;

        public int getSalary_type() {
            return salary_type;
        }

        public void setSalary_type(int salary_type) {
            this.salary_type = salary_type;
        }

        public int getSalary_fee() {
            return salary_fee;
        }

        public void setSalary_fee(int salary_fee) {
            this.salary_fee = salary_fee;
        }

        public int getEnd_salary_fee() {
            return end_salary_fee;
        }

        public void setEnd_salary_fee(int end_salary_fee) {
            this.end_salary_fee = end_salary_fee;
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

        public int getWork_third_area_id() {
            return work_third_area_id;
        }

        public void setWork_third_area_id(int work_third_area_id) {
            this.work_third_area_id = work_third_area_id;
        }

        public String getWork_date() {
            return work_date;
        }

        public void setWork_date(String work_date) {
            this.work_date = work_date;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getContact_tel() {
            return contact_tel;
        }

        public void setContact_tel(String contact_tel) {
            this.contact_tel = contact_tel;
        }

        public String getMatch_time() {
            return match_time;
        }

        public void setMatch_time(String match_time) {
            this.match_time = match_time;
        }

        public String getWork_third_area_name() {
            return work_third_area_name;
        }

        public void setWork_third_area_name(String work_third_area_name) {
            this.work_third_area_name = work_third_area_name;
        }

        public String getSelf_intro() {
            return self_intro;
        }

        public void setSelf_intro(String self_intro) {
            this.self_intro = self_intro;
        }

        public String getService_id() {
            return service_id;
        }

        public void setService_id(String service_id) {
            this.service_id = service_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getWork_second_area_id() {
            return work_second_area_id;
        }

        public void setWork_second_area_id(int work_second_area_id) {
            this.work_second_area_id = work_second_area_id;
        }

        public int getWork_first_area_id() {
            return work_first_area_id;
        }

        public void setWork_first_area_id(int work_first_area_id) {
            this.work_first_area_id = work_first_area_id;
        }

        public String getWork_first_area_name() {
            return work_first_area_name;
        }

        public void setWork_first_area_name(String work_first_area_name) {
            this.work_first_area_name = work_first_area_name;
        }

        public String getWork_second_area_name() {
            return work_second_area_name;
        }

        public void setWork_second_area_name(String work_second_area_name) {
            this.work_second_area_name = work_second_area_name;
        }
    }
}
