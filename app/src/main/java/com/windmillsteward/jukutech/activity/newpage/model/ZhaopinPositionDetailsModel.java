package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2018/10/28
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class ZhaopinPositionDetailsModel {

    /**
     * work_year_name : 1-2年
     * address : 天河路xx号B栋
     * latitude :
     * description : 职位描述XXX
     * job_resume_id : 41
     * benefit_ids : 68,69
     * salary_name : 2000-3000
     * age_range_name : 18-28
     * job_class_id_two_name : 人力资源
     * benefit_name_list : ["旅游福利","加班费"]
     * recruitment_num : 2
     * order_sn : 2
     * publish_status : 1
     * longitude :
     * education_background_name : 研究生
     */


    private String work_year_name;
    private String address;
    private String latitude;
    private String description;
    private int job_resume_id;
    private String benefit_ids;
    private String salary_name;
    private String age_range_name;
    private String job_class_id_two_name;
    private int recruitment_num;
    private String order_sn;
    private int publish_status;
    private String longitude;
    private String education_background_name;
    private List<String> benefit_name_list;
    private String title;
    private String salary_fee;
    private String end_salary_fee;
    private int salary_type;
    private int uninterested_num;
    private int is_uninterested;
    private String voice_url;
    private String image_url;
    private String video_url;
    /**
     * work_third_area_name : 高青县
     * update_time : 1558005411
     * salary_fee : 0
     * info_fee : 2
     * work_second_area_name : 淄博市
     * end_salary_fee : 0
     * benefit_name :
     * contact_person : sfc13
     * sex : 2
     * job_name : 人力资源
     * user_id : 194
     * company_name : 个人
     */

    private String work_third_area_name;
    private long update_time;
    private int info_fee;
    private String work_second_area_name;
    private String benefit_name;
    private List<BenefitTypeModel> benefit_list;
    private String contact_person;
    private int sex;
    private String job_name;
    private int user_id;
    private String company_name;
    /**
     * work_third_area_id : 2542
     * job_class_id_two : 81
     * salary_fee : 0
     * age_range_id : 136
     * end_salary_fee : 0
     * job_class_id_one : 73
     * education_background_id : 64
     * job_id : 216
     * work_second_area_id : 225
     * work_year_id : 30
     */

    private int work_third_area_id;
    private String job_class_id_two;
    private int age_range_id;
    private String job_class_id_one;
    private int education_background_id;
    private int job_id;
    private int work_second_area_id;
    private int work_year_id;


    public List<BenefitTypeModel> getBenefit_list() {
        return benefit_list;
    }

    public void setBenefit_list(List<BenefitTypeModel> benefit_list) {
        this.benefit_list = benefit_list;
    }

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

    public int getSalary_type() {
        return salary_type;
    }

    public void setSalary_type(int salary_type) {
        this.salary_type = salary_type;
    }

    public int getUninterested_num() {
        return uninterested_num;
    }

    public void setUninterested_num(int uninterested_num) {
        this.uninterested_num = uninterested_num;
    }

    public int getIs_uninterested() {
        return is_uninterested;
    }

    public void setIs_uninterested(int is_uninterested) {
        this.is_uninterested = is_uninterested;
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

    public int getJob_resume_id() {
        return job_resume_id;
    }

    public void setJob_resume_id(int job_resume_id) {
        this.job_resume_id = job_resume_id;
    }

    public String getBenefit_ids() {
        return benefit_ids;
    }

    public void setBenefit_ids(String benefit_ids) {
        this.benefit_ids = benefit_ids;
    }

    public String getSalary_name() {
        return salary_name;
    }

    public void setSalary_name(String salary_name) {
        this.salary_name = salary_name;
    }

    public String getAge_range_name() {
        return age_range_name;
    }

    public void setAge_range_name(String age_range_name) {
        this.age_range_name = age_range_name;
    }

    public String getJob_class_id_two_name() {
        return job_class_id_two_name;
    }

    public void setJob_class_id_two_name(String job_class_id_two_name) {
        this.job_class_id_two_name = job_class_id_two_name;
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

    public List<String> getBenefit_name_list() {
        return benefit_name_list;
    }

    public void setBenefit_name_list(List<String> benefit_name_list) {
        this.benefit_name_list = benefit_name_list;
    }

    public String getWork_third_area_name() {
        return work_third_area_name;
    }

    public void setWork_third_area_name(String work_third_area_name) {
        this.work_third_area_name = work_third_area_name;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }


    public int getInfo_fee() {
        return info_fee;
    }

    public void setInfo_fee(int info_fee) {
        this.info_fee = info_fee;
    }

    public String getWork_second_area_name() {
        return work_second_area_name;
    }

    public void setWork_second_area_name(String work_second_area_name) {
        this.work_second_area_name = work_second_area_name;
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

    public int getWork_third_area_id() {
        return work_third_area_id;
    }

    public void setWork_third_area_id(int work_third_area_id) {
        this.work_third_area_id = work_third_area_id;
    }

    public String getJob_class_id_two() {
        return job_class_id_two;
    }

    public void setJob_class_id_two(String job_class_id_two) {
        this.job_class_id_two = job_class_id_two;
    }

    public int getAge_range_id() {
        return age_range_id;
    }

    public void setAge_range_id(int age_range_id) {
        this.age_range_id = age_range_id;
    }

    public String getJob_class_id_one() {
        return job_class_id_one;
    }

    public void setJob_class_id_one(String job_class_id_one) {
        this.job_class_id_one = job_class_id_one;
    }

    public int getEducation_background_id() {
        return education_background_id;
    }

    public void setEducation_background_id(int education_background_id) {
        this.education_background_id = education_background_id;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public int getWork_second_area_id() {
        return work_second_area_id;
    }

    public void setWork_second_area_id(int work_second_area_id) {
        this.work_second_area_id = work_second_area_id;
    }

    public int getWork_year_id() {
        return work_year_id;
    }

    public void setWork_year_id(int work_year_id) {
        this.work_year_id = work_year_id;
    }
}
