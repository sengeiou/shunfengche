package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/22
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 点击我要找对象 model
 */
public class LastPublicForYinyuanModel {

    /**
     * record : {"birthday":"2017-01-01","first_area_id":0,"salary_id":0,"user_name":"陈先生","sex":1,"weight":65,"live_third_area_id":0,"job_class_id_one":0,"pic_urls":"","education_background_id":0,"job_class_id_two":0,"live_second_area_id":0,"marital_status":1,"video_url":"","mobile_phone":"13334561234","self_intro":"自我介绍XXX","child_status":2,"height":175}
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
         * birthday : 2017-01-01
         * first_area_id : 0
         * salary_id : 0
         * user_name : 陈先生
         * sex : 1
         * weight : 65
         * live_third_area_id : 0
         * job_class_id_one : 0
         * pic_urls :
         * education_background_id : 0
         * job_class_id_two : 0
         * live_second_area_id : 0
         * marital_status : 1
         * video_url :
         * mobile_phone : 13334561234
         * self_intro : 自我介绍XXX
         * child_status : 2
         * height : 175
         */

        private String birthday;
        private int first_area_id;
        private int salary_id;
        private String user_name;
        private int sex;
        private double weight;
        private int live_third_area_id;
        private int job_class_id_one;
        private String pic_urls;
        private int education_background_id;
        private int job_class_id_two;
        private int live_second_area_id;
        private int marital_status;
        private String video_url;
        private String mobile_phone;
        private String self_intro;
        private int child_status;
        private double height;
        private String user_avatar_url;
        private int age;

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

        public int getFirst_area_id() {
            return first_area_id;
        }

        public void setFirst_area_id(int first_area_id) {
            this.first_area_id = first_area_id;
        }

        public int getSalary_id() {
            return salary_id;
        }

        public void setSalary_id(int salary_id) {
            this.salary_id = salary_id;
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

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public int getLive_third_area_id() {
            return live_third_area_id;
        }

        public void setLive_third_area_id(int live_third_area_id) {
            this.live_third_area_id = live_third_area_id;
        }

        public int getJob_class_id_one() {
            return job_class_id_one;
        }

        public void setJob_class_id_one(int job_class_id_one) {
            this.job_class_id_one = job_class_id_one;
        }

        public String getPic_urls() {
            return pic_urls;
        }

        public void setPic_urls(String pic_urls) {
            this.pic_urls = pic_urls;
        }

        public int getEducation_background_id() {
            return education_background_id;
        }

        public void setEducation_background_id(int education_background_id) {
            this.education_background_id = education_background_id;
        }

        public int getJob_class_id_two() {
            return job_class_id_two;
        }

        public void setJob_class_id_two(int job_class_id_two) {
            this.job_class_id_two = job_class_id_two;
        }

        public int getLive_second_area_id() {
            return live_second_area_id;
        }

        public void setLive_second_area_id(int live_second_area_id) {
            this.live_second_area_id = live_second_area_id;
        }

        public int getMarital_status() {
            return marital_status;
        }

        public void setMarital_status(int marital_status) {
            this.marital_status = marital_status;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
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

        public int getChild_status() {
            return child_status;
        }

        public void setChild_status(int child_status) {
            this.child_status = child_status;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }
    }
}
