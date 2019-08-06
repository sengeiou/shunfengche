package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2019-05-08
 * @author: cyq
 * @desc: 求职招聘-招聘方-订单详情
 */
public class QiuZhiZhaoPinOrderDetailsModel {


    /**
     * recruitment_resume_list : []
     * latitude : 36.521276
     * contact_tel : 13727576543
     * description : /。 vvvv
     * recruitment_resume : {"birthday":"2017-01-01","latitude":"","uninterested_num":0,"work_third_area_name":"博山区","salary_fee":0,"info_fee":2,"true_name":"sfc13","mobile_phone":"13727578910","work_second_area_name":"淄博市","publish_status":2,"longitude":"","view_num":0,"end_salary_fee":0,"work_year_name":"1-2年","benefit_name":"五险一金,旅游福利,加班费","sex":1,"benefit_ids":"67,68,69","match_value":"100","user_avatar_url":"https://sfcgj.oss-cn-qingdao.aliyuncs.com/2325611542532154216icon_head_male%403x.png","is_uninterested":0,"job_name":"后端开发","user_id":196,"self_intro":"是是是","salary_type":2,"add_time":1557112070,"age":30,"order_sn":"2019050611071168704146","education_background_name":"研究生"}
     * title : 招聘后端开发1名
     * age_range_name : 不限
     * work_third_area_name : 博山区
     * salary_fee : 0
     * work_second_area_name : 淄博市
     * publish_status : 2
     * longitude : 117.868306
     * end_salary_fee : 0
     * work_year_name : 1-2年
     * is_match : 2
     * address : 冠华·龙凤城-南门(山东省淄博市博山区珑山路冠华·龙凤城(博山客运站北))
     * benefit_name :
     * contact_person : sfc13
     * sex : 0
     * benefit_ids :
     * job_name : 后端开发
     * user_id : 197
     * company_name : 个人
     * salary_type : 3
     * recruitment_num : 1
     * add_time : 1557112032
     * order_sn : 2019050611071568521607
     * education_background_name : 研究生
     */

    private String latitude;
    private String contact_tel;
    private String description;
    private RecruitmentResumeBean recruitment_resume;
    private String title;
    private String age_range_name;
    private String work_third_area_name;
    private int salary_fee;
    private String work_second_area_name;
    private int publish_status;
    private String longitude;
    private int end_salary_fee;
    private double info_fee;
    private String work_year_name;
    private int is_match;
    private String address;
    private String benefit_name;
    private String contact_person;
    private int sex;
    private String benefit_ids;
    private String job_name;
    private int user_id;
    private String company_name;
    private int salary_type;
    private int recruitment_num;
    private int add_time;
    private int uninterested_num;
    private String order_sn;
    private String education_background_name;
    private List<RecruitmentResumeBean> recruitment_resume_list;
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

    public double getInfo_fee() {
        return info_fee;
    }

    public void setInfo_fee(double info_fee) {
        this.info_fee = info_fee;
    }

    public int getUninterested_num() {
        return uninterested_num;
    }

    public void setUninterested_num(int uninterested_num) {
        this.uninterested_num = uninterested_num;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getContact_tel() {
        return contact_tel;
    }

    public void setContact_tel(String contact_tel) {
        this.contact_tel = contact_tel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RecruitmentResumeBean getRecruitment_resume() {
        return recruitment_resume;
    }

    public void setRecruitment_resume(RecruitmentResumeBean recruitment_resume) {
        this.recruitment_resume = recruitment_resume;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAge_range_name() {
        return age_range_name;
    }

    public void setAge_range_name(String age_range_name) {
        this.age_range_name = age_range_name;
    }

    public String getWork_third_area_name() {
        return work_third_area_name;
    }

    public void setWork_third_area_name(String work_third_area_name) {
        this.work_third_area_name = work_third_area_name;
    }

    public int getSalary_fee() {
        return salary_fee;
    }

    public void setSalary_fee(int salary_fee) {
        this.salary_fee = salary_fee;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBenefit_name() {
        return benefit_name;
    }

    public void setBenefit_name(String benefit_name) {
        this.benefit_name = benefit_name;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBenefit_ids() {
        return benefit_ids;
    }

    public void setBenefit_ids(String benefit_ids) {
        this.benefit_ids = benefit_ids;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
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

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
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

    public List<RecruitmentResumeBean> getRecruitment_resume_list() {
        return recruitment_resume_list;
    }

    public void setRecruitment_resume_list(List<RecruitmentResumeBean> recruitment_resume_list) {
        this.recruitment_resume_list = recruitment_resume_list;
    }

    public static class RecruitmentResumeBean {
        /**
         * birthday : 2017-01-01
         * latitude :
         * uninterested_num : 0
         * work_third_area_name : 博山区
         * salary_fee : 0
         * info_fee : 2
         * true_name : sfc13
         * mobile_phone : 13727578910
         * work_second_area_name : 淄博市
         * publish_status : 2
         * longitude :
         * view_num : 0
         * end_salary_fee : 0
         * work_year_name : 1-2年
         * benefit_name : 五险一金,旅游福利,加班费
         * sex : 1
         * benefit_ids : 67,68,69
         * match_value : 100
         * user_avatar_url : https://sfcgj.oss-cn-qingdao.aliyuncs.com/2325611542532154216icon_head_male%403x.png
         * is_uninterested : 0
         * job_name : 后端开发
         * user_id : 196
         * self_intro : 是是是
         * salary_type : 2
         * add_time : 1557112070
         * age : 30
         * order_sn : 2019050611071168704146
         * education_background_name : 研究生
         * is_view : 1
         * is_evaluation : 1
         */

        private String birthday;
        private String latitude;
        private int uninterested_num;
        private int job_resume_id;
        private String work_third_area_name;
        private double salary_fee;
        private int info_fee;
        private int is_evaluation;
        private String true_name;
        private String mobile_phone;
        private String work_second_area_name;
        private int publish_status;
        private String longitude;
        private int view_num;
        private double end_salary_fee;
        private String work_year_name;
        private String benefit_name;
        private int sex;
        private String benefit_ids;
        private String match_value;
        private String user_avatar_url;
        private int is_uninterested;
        private String job_name;
        private int user_id;
        private String self_intro;
        private int salary_type;
        private int add_time;
        private int age;
        private int is_view;
        private String order_sn;
        private String education_background_name;

        public int getIs_evaluation() {
            return is_evaluation;
        }

        public void setIs_evaluation(int is_evaluation) {
            this.is_evaluation = is_evaluation;
        }

        public int getIs_view() {
            return is_view;
        }

        public void setIs_view(int is_view) {
            this.is_view = is_view;
        }

        public int getJob_resume_id() {
            return job_resume_id;
        }

        public void setJob_resume_id(int job_resume_id) {
            this.job_resume_id = job_resume_id;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public int getUninterested_num() {
            return uninterested_num;
        }

        public void setUninterested_num(int uninterested_num) {
            this.uninterested_num = uninterested_num;
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

        public int getInfo_fee() {
            return info_fee;
        }

        public void setInfo_fee(int info_fee) {
            this.info_fee = info_fee;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
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

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public int getView_num() {
            return view_num;
        }

        public void setView_num(int view_num) {
            this.view_num = view_num;
        }

        public double getEnd_salary_fee() {
            return end_salary_fee;
        }

        public void setEnd_salary_fee(double end_salary_fee) {
            this.end_salary_fee = end_salary_fee;
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getBenefit_ids() {
            return benefit_ids;
        }

        public void setBenefit_ids(String benefit_ids) {
            this.benefit_ids = benefit_ids;
        }

        public String getMatch_value() {
            return match_value;
        }

        public void setMatch_value(String match_value) {
            this.match_value = match_value;
        }

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }

        public int getIs_uninterested() {
            return is_uninterested;
        }

        public void setIs_uninterested(int is_uninterested) {
            this.is_uninterested = is_uninterested;
        }

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getSelf_intro() {
            return self_intro;
        }

        public void setSelf_intro(String self_intro) {
            this.self_intro = self_intro;
        }

        public int getSalary_type() {
            return salary_type;
        }

        public void setSalary_type(int salary_type) {
            this.salary_type = salary_type;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
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
    }
}
