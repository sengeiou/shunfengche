package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2019-05-08
 * @author: cyq
 * @desc: 求职招聘-应聘方-订单详情
 */
public class QiuZhiYingPinOrderDetailsModel {


    /**
     * birthday : 2017-01-01
     * view_num : 0
     * end_salary_fee : 0
     * work_year_name : 1-2年
     * is_match : 2
     * latitude :
     * sex : 1
     * recruitment_job_list : [{"is_uninterested":0,"end_salary_fee":0,"work_third_area_name":"博山区","salary_fee":0,"job_name":"后端开发","job_id":186,"company_name":"个人","match_value":"90","title":"招聘后端开发1名","salary_type":3,"work_second_area_name":"淄博市","salary_name":"不限"}]
     * salary_name : 不限
     * work_third_area_name : 博山区
     * job_name : 后端开发
     * salary_fee : 0
     * info_fee : 2
     * true_name : sfc13
     * self_intro : 是是是
     * mobile_phone : 13727578910
     * job_class_id_two_name : 后端开发
     * salary_type : 2
     * work_second_area_name : 淄博市
     * publish_status : 1
     * age : 30
     * order_sn : 2019050704195206778118
     * education_background_name : 研究生
     * longitude :
     * add_time :
     */

    private String birthday;
    private int view_num;
    private int end_salary_fee;
    private String work_year_name;
    private int is_match;
    private String latitude;
    private int sex;
    private String salary_name;
    private String work_third_area_name;
    private String job_name;
    private int salary_fee;
    private double info_fee;
    private String true_name;
    private String self_intro;
    private String mobile_phone;
    private String job_class_id_two_name;
    private int salary_type;
    private String work_second_area_name;
    private int publish_status;
    private int age;
    private int uninterested_num;
    private String order_sn;
    private String education_background_name;
    private String longitude;
    private String benefit_name;
    private long add_time;
    private List<RecruitmentJobListBean> recruitment_job_list;
    private RecruitmentBean recruitment_job;
    private String voice_url;
    private String image_url;
    private String video_url;

    public String getVoice_url() {
        return voice_url;
    }

    public void setVoice_url(String voice_url) {
        this.voice_url = voice_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getBenefit_name() {
        return benefit_name;
    }

    public void setBenefit_name(String benefit_name) {
        this.benefit_name = benefit_name;
    }

    public RecruitmentBean getRecruitment_job() {
        return recruitment_job;
    }

    public void setRecruitment_job(RecruitmentBean recruitment_job) {
        this.recruitment_job = recruitment_job;
    }

    public int getUninterested_num() {
        return uninterested_num;
    }

    public void setUninterested_num(int uninterested_num) {
        this.uninterested_num = uninterested_num;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getView_num() {
        return view_num;
    }

    public void setView_num(int view_num) {
        this.view_num = view_num;
    }

    public int getEnd_salary_fee() {
        return end_salary_fee;
    }

    public void setEnd_salary_fee(int end_salary_fee) {
        this.end_salary_fee = end_salary_fee;
    }

    public String getWork_year_name() {
        return work_year_name;
    }

    public void setWork_year_name(String work_year_name) {
        this.work_year_name = work_year_name;
    }

    public int getIs_match() {
        return is_match;
    }

    public void setIs_match(int is_match) {
        this.is_match = is_match;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getSalary_name() {
        return salary_name;
    }

    public void setSalary_name(String salary_name) {
        this.salary_name = salary_name;
    }

    public String getWork_third_area_name() {
        return work_third_area_name;
    }

    public void setWork_third_area_name(String work_third_area_name) {
        this.work_third_area_name = work_third_area_name;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public int getSalary_fee() {
        return salary_fee;
    }

    public void setSalary_fee(int salary_fee) {
        this.salary_fee = salary_fee;
    }

    public double getInfo_fee() {
        return info_fee;
    }

    public void setInfo_fee(double info_fee) {
        this.info_fee = info_fee;
    }

    public String getTrue_name() {
        return true_name;
    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name;
    }

    public String getSelf_intro() {
        return self_intro;
    }

    public void setSelf_intro(String self_intro) {
        this.self_intro = self_intro;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getJob_class_id_two_name() {
        return job_class_id_two_name;
    }

    public void setJob_class_id_two_name(String job_class_id_two_name) {
        this.job_class_id_two_name = job_class_id_two_name;
    }

    public int getSalary_type() {
        return salary_type;
    }

    public void setSalary_type(int salary_type) {
        this.salary_type = salary_type;
    }

    public String getWork_second_area_name() {
        return work_second_area_name;
    }

    public void setWork_second_area_name(String work_second_area_name) {
        this.work_second_area_name = work_second_area_name;
    }

    public int getPublish_status() {
        return publish_status;
    }

    public void setPublish_status(int publish_status) {
        this.publish_status = publish_status;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getEducation_background_name() {
        return education_background_name;
    }

    public void setEducation_background_name(String education_background_name) {
        this.education_background_name = education_background_name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<RecruitmentJobListBean> getRecruitment_job_list() {
        return recruitment_job_list;
    }

    public void setRecruitment_job_list(List<RecruitmentJobListBean> recruitment_job_list) {
        this.recruitment_job_list = recruitment_job_list;
    }


    public static class RecruitmentBean{
        private String latitude;
        private String longitude;
        private String address;
        private int end_salary_fee;
        private int salary_fee;
        private String work_year_name;
        private String benefit_name;
        private String description;
        private String match_value;
        private String title;
        private String user_avatar_url;
        private String work_third_area_name;
        private String job_name;
        private String company_name;
        private String work_second_area_name;
        private String order_sn;
        private String education_background_name;
        private int salary_type;
        private int recruitment_num;
        private int sex;
        private String age_range_name;
        private String contact_tel;
        private String contact_person;
        private int is_uninterested;
        private long add_time;
        private String voice_url;
        private String image_url;
        private String video_url;

        public String getVoice_url() {
            return voice_url;
        }

        public void setVoice_url(String voice_url) {
            this.voice_url = voice_url;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
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

        public int getEnd_salary_fee() {
            return end_salary_fee;
        }

        public void setEnd_salary_fee(int end_salary_fee) {
            this.end_salary_fee = end_salary_fee;
        }

        public int getSalary_fee() {
            return salary_fee;
        }

        public void setSalary_fee(int salary_fee) {
            this.salary_fee = salary_fee;
        }

        public String getWork_year_name() {
            return work_year_name;
        }

        public void setWork_year_name(String work_year_name) {
            this.work_year_name = work_year_name;
        }

        public String getBenefit_name() {
            return benefit_name;
        }

        public void setBenefit_name(String benefit_name) {
            this.benefit_name = benefit_name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getMatch_value() {
            return match_value;
        }

        public void setMatch_value(String match_value) {
            this.match_value = match_value;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }

        public String getWork_third_area_name() {
            return work_third_area_name;
        }

        public void setWork_third_area_name(String work_third_area_name) {
            this.work_third_area_name = work_third_area_name;
        }

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getWork_second_area_name() {
            return work_second_area_name;
        }

        public void setWork_second_area_name(String work_second_area_name) {
            this.work_second_area_name = work_second_area_name;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getEducation_background_name() {
            return education_background_name;
        }

        public void setEducation_background_name(String education_background_name) {
            this.education_background_name = education_background_name;
        }

        public int getSalary_type() {
            return salary_type;
        }

        public void setSalary_type(int salary_type) {
            this.salary_type = salary_type;
        }

        public int getRecruitment_num() {
            return recruitment_num;
        }

        public void setRecruitment_num(int recruitment_num) {
            this.recruitment_num = recruitment_num;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getAge_range_name() {
            return age_range_name;
        }

        public void setAge_range_name(String age_range_name) {
            this.age_range_name = age_range_name;
        }

        public int getIs_uninterested() {
            return is_uninterested;
        }

        public void setIs_uninterested(int is_uninterested) {
            this.is_uninterested = is_uninterested;
        }
    }



    public static class RecruitmentJobListBean {
        /**
         * is_uninterested : 0
         * end_salary_fee : 0
         * work_third_area_name : 博山区
         * salary_fee : 0
         * job_name : 后端开发
         * job_id : 186
         * company_name : 个人
         * match_value : 90
         * title : 招聘后端开发1名
         * salary_type : 3
         * work_second_area_name : 淄博市
         * salary_name : 不限
         * job_resume_id : 1
         */

        private int is_uninterested;
        private int job_resume_id;
        private double end_salary_fee;
        private String work_third_area_name;
        private double salary_fee;
        private String job_name;
        private int job_id;
        private String company_name;
        private String match_value;
        private String title;
        private int salary_type;
        private String work_second_area_name;
        private String salary_name;

        public int getJob_resume_id() {
            return job_resume_id;
        }

        public void setJob_resume_id(int job_resume_id) {
            this.job_resume_id = job_resume_id;
        }

        public int getIs_uninterested() {
            return is_uninterested;
        }

        public void setIs_uninterested(int is_uninterested) {
            this.is_uninterested = is_uninterested;
        }

        public double getEnd_salary_fee() {
            return end_salary_fee;
        }

        public void setEnd_salary_fee(double end_salary_fee) {
            this.end_salary_fee = end_salary_fee;
        }

        public String getWork_third_area_name() {
            return work_third_area_name;
        }

        public void setWork_third_area_name(String work_third_area_name) {
            this.work_third_area_name = work_third_area_name;
        }

        public double getSalary_fee() {
            return salary_fee;
        }

        public void setSalary_fee(double salary_fee) {
            this.salary_fee = salary_fee;
        }

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public int getJob_id() {
            return job_id;
        }

        public void setJob_id(int job_id) {
            this.job_id = job_id;
        }

        public String getCompany_name() {
            return company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public String getMatch_value() {
            return match_value;
        }

        public void setMatch_value(String match_value) {
            this.match_value = match_value;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getSalary_type() {
            return salary_type;
        }

        public void setSalary_type(int salary_type) {
            this.salary_type = salary_type;
        }

        public String getWork_second_area_name() {
            return work_second_area_name;
        }

        public void setWork_second_area_name(String work_second_area_name) {
            this.work_second_area_name = work_second_area_name;
        }

        public String getSalary_name() {
            return salary_name;
        }

        public void setSalary_name(String salary_name) {
            this.salary_name = salary_name;
        }
    }
}
