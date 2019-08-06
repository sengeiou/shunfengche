package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2018/10/28
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class HasZhaopinPublishedDetailsModel {

    /**
     * jobRecord : {"work_year_name":"1-2年","address":"详细地址XXX","salary_id":76,"latitude":"11.1111","description":"职位描述XXX","benefit_ids":["五险一金","旅游福利","加班费"],"education_background_id":64,"age_range_name":"18-28","salary_name":"2000-3000","job_name":"移动开发","age_range_id":2,"work_year_id":30,"recruitment_num":5,"order_sn":"0","publish_status":0,"longitude":"22.1111","education_background_name":"研究生"}
     * jobSeekerRecordList : [{"work_third_area_id":3040,"work_third_area_name":"天河区","true_name":"小黑","is_view":0,"sex":1,"latitude":"","work_second_area_id":289,"job_resume_id":47,"match_value":"90","work_second_area_name":"广州市","user_avatar_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/0105051527761755997%E5%A3%81%E7%BA%B81.jpg","longitude":""},{"work_third_area_id":3040,"work_third_area_name":"天河区","true_name":"小红","is_view":0,"sex":2,"latitude":"","work_second_area_id":289,"job_resume_id":48,"match_value":"85","work_second_area_name":"广州市","user_avatar_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/0105051527761755997%E5%A3%81%E7%BA%B81.jpg","longitude":""}]
     */

    private JobRecordBean jobRecord;
    private List<JobSeekerRecordListBean> jobSeekerRecordList;

    public JobRecordBean getJobRecord() {
        return jobRecord;
    }

    public void setJobRecord(JobRecordBean jobRecord) {
        this.jobRecord = jobRecord;
    }

    public List<JobSeekerRecordListBean> getJobSeekerRecordList() {
        return jobSeekerRecordList;
    }

    public void setJobSeekerRecordList(List<JobSeekerRecordListBean> jobSeekerRecordList) {
        this.jobSeekerRecordList = jobSeekerRecordList;
    }

    public static class JobRecordBean {
        /**
         * work_year_name : 1-2年
         * address : 详细地址XXX
         * salary_id : 76
         * latitude : 11.1111
         * description : 职位描述XXX
         * benefit_ids : ["五险一金","旅游福利","加班费"]
         * education_background_id : 64
         * age_range_name : 18-28
         * salary_name : 2000-3000
         * job_name : 移动开发
         * age_range_id : 2
         * work_year_id : 30
         * recruitment_num : 5
         * order_sn : 0
         * publish_status : 0
         * longitude : 22.1111
         * education_background_name : 研究生
         */

        private String work_year_name;
        private String address;
        private int salary_id;
        private String latitude;
        private String description;
        private int education_background_id;
        private String age_range_name;
        private String salary_name;
        private String job_name;
        private int age_range_id;
        private int work_year_id;
        private int recruitment_num;
        private String order_sn;
        private int publish_status;
        private String longitude;
        private String education_background_name;
        private List<String> benefit_ids;
        private String title;
        private String info_fee;
        private int salary_type;
        private String salary_fee;
        private String end_salary_fee;

        public int getSalary_type() {
            return salary_type;
        }

        public void setSalary_type(int salary_type) {
            this.salary_type = salary_type;
        }

        public String getSalary_fee() {
            return salary_fee;
        }

        public void setSalary_fee(String salary_fee) {
            this.salary_fee = salary_fee;
        }

        public String getEnd_salary_fee() {
            return end_salary_fee;
        }

        public void setEnd_salary_fee(String end_salary_fee) {
            this.end_salary_fee = end_salary_fee;
        }

        public String getInfo_fee() {
            return info_fee;
        }

        public void setInfo_fee(String info_fee) {
            this.info_fee = info_fee;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWork_year_name() {
            return work_year_name;
        }

        public void setWork_year_name(String work_year_name) {
            this.work_year_name = work_year_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getSalary_id() {
            return salary_id;
        }

        public void setSalary_id(int salary_id) {
            this.salary_id = salary_id;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getEducation_background_id() {
            return education_background_id;
        }

        public void setEducation_background_id(int education_background_id) {
            this.education_background_id = education_background_id;
        }

        public String getAge_range_name() {
            return age_range_name;
        }

        public void setAge_range_name(String age_range_name) {
            this.age_range_name = age_range_name;
        }

        public String getSalary_name() {
            return salary_name;
        }

        public void setSalary_name(String salary_name) {
            this.salary_name = salary_name;
        }

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public int getAge_range_id() {
            return age_range_id;
        }

        public void setAge_range_id(int age_range_id) {
            this.age_range_id = age_range_id;
        }

        public int getWork_year_id() {
            return work_year_id;
        }

        public void setWork_year_id(int work_year_id) {
            this.work_year_id = work_year_id;
        }

        public int getRecruitment_num() {
            return recruitment_num;
        }

        public void setRecruitment_num(int recruitment_num) {
            this.recruitment_num = recruitment_num;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public int getPublish_status() {
            return publish_status;
        }

        public void setPublish_status(int publish_status) {
            this.publish_status = publish_status;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getEducation_background_name() {
            return education_background_name;
        }

        public void setEducation_background_name(String education_background_name) {
            this.education_background_name = education_background_name;
        }

        public List<String> getBenefit_ids() {
            return benefit_ids;
        }

        public void setBenefit_ids(List<String> benefit_ids) {
            this.benefit_ids = benefit_ids;
        }
    }

    public static class JobSeekerRecordListBean {
        /**
         * work_third_area_id : 3040
         * work_third_area_name : 天河区
         * true_name : 小黑
         * is_view : 0
         * sex : 1
         * latitude :
         * work_second_area_id : 289
         * job_resume_id : 47
         * match_value : 90
         * work_second_area_name : 广州市
         * user_avatar_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/0105051527761755997%E5%A3%81%E7%BA%B81.jpg
         * longitude :
         */

        private int work_third_area_id;
        private String work_third_area_name;
        private String true_name;
        private int is_view;
        private int sex;
        private String latitude;
        private int work_second_area_id;
        private int job_resume_id;
        private String match_value;
        private String work_second_area_name;
        private String user_avatar_url;
        private String longitude;

        public int getWork_third_area_id() {
            return work_third_area_id;
        }

        public void setWork_third_area_id(int work_third_area_id) {
            this.work_third_area_id = work_third_area_id;
        }

        public String getWork_third_area_name() {
            return work_third_area_name;
        }

        public void setWork_third_area_name(String work_third_area_name) {
            this.work_third_area_name = work_third_area_name;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public int getIs_view() {
            return is_view;
        }

        public void setIs_view(int is_view) {
            this.is_view = is_view;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
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

        public String getMatch_value() {
            return match_value;
        }

        public void setMatch_value(String match_value) {
            this.match_value = match_value;
        }

        public String getWork_second_area_name() {
            return work_second_area_name;
        }

        public void setWork_second_area_name(String work_second_area_name) {
            this.work_second_area_name = work_second_area_name;
        }

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }
}
