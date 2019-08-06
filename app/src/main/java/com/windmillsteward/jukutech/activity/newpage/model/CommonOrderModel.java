package com.windmillsteward.jukutech.activity.newpage.model;

import com.windmillsteward.jukutech.base.baseadapter.entity.MultiItemEntity;

/**
 * @date: on 2018/10/30
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class CommonOrderModel implements MultiItemEntity {

    /**
     * record : {"user_name":"小明","match_id":16,"sex":1,"personal_assets_status":"","live_second_area_name":"广州市","match_value":"90","age":23,"user_avatar_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/998307152360347334620180413_031111.jpg"}
     * relate_id : 5
     * type : 9
     */

    private RecordBean record;
    private int relate_id;
    private int matching_order_id;//订单id
    //1.智慧生活-抢单人 2.智慧生活-发布人，3.房屋模块，5劳务中介-应聘方，
    // 6.劳务中介-招聘方，7.求职招聘-应聘方，8求职招聘-招聘方 9.姻缘
    // 10.家教-应聘方 11.家教-招聘方 12.保姆-应聘方，13.保姆-招聘方，
    // 14.月嫂-应聘方 15.月嫂-招聘方 16.育儿嫂应聘方 17.育儿嫂-招聘方
    // 18.钟点工-应聘方，19.钟点工-招聘方 20.特种工-应聘方，21.特种工-招聘方
    private int type;
    private int read;//0未读 1已读

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getMatching_order_id() {
        return matching_order_id;
    }

    public void setMatching_order_id(int matching_order_id) {
        this.matching_order_id = matching_order_id;
    }

    public RecordBean getRecord() {
        return record;
    }

    public void setRecord(RecordBean record) {
        this.record = record;
    }

    public int getRelate_id() {
        return relate_id;
    }

    public void setRelate_id(int relate_id) {
        this.relate_id = relate_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }

    public static class RecordBean {

        /**
         * type== 22时 ，record返回的数据,劳务工应聘方
         */

//        private String area_name;
//        private String update_time;
        private int is_match;
        private int people_num;
        private String remark;
        private long booking_time;
        private String work_date;
        private int index_type;
//        private String latitude;
//        private String longitude;
        private int work_hour;
        private String work_type_name;
        private String salary_name;
        private String start_zo_height;
        private String end_zo_height;
        private String start_zo_weight;
        private String end_zo_weight;
        private String end_zo_age;
        private String start_zo_age;
        private String coach_grade_name;
        private String coach_subject_name;
        private String coach_time_name;
        private String service_content_name;
        private String work_time_type_name;
        private String work_experience_name;
        private String person_type_name;

        public int getIndex_type() {
            return index_type;
        }

        public void setIndex_type(int index_type) {
            this.index_type = index_type;
        }

        public int getPeople_num() {
            return people_num;
        }

        public void setPeople_num(int people_num) {
            this.people_num = people_num;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public long getBooking_time() {
            return booking_time;
        }

        public void setBooking_time(long booking_time) {
            this.booking_time = booking_time;
        }

        public String getPerson_type_name() {
            return person_type_name;
        }

        public void setPerson_type_name(String person_type_name) {
            this.person_type_name = person_type_name;
        }

        public String getService_content_name() {
            return service_content_name;
        }

        public void setService_content_name(String service_content_name) {
            this.service_content_name = service_content_name;
        }

        public String getWork_time_type_name() {
            return work_time_type_name;
        }

        public void setWork_time_type_name(String work_time_type_name) {
            this.work_time_type_name = work_time_type_name;
        }

        public String getWork_experience_name() {
            return work_experience_name;
        }

        public void setWork_experience_name(String work_experience_name) {
            this.work_experience_name = work_experience_name;
        }

        public String getCoach_grade_name() {
            return coach_grade_name;
        }

        public void setCoach_grade_name(String coach_grade_name) {
            this.coach_grade_name = coach_grade_name;
        }

        public String getCoach_subject_name() {
            return coach_subject_name;
        }

        public void setCoach_subject_name(String coach_subject_name) {
            this.coach_subject_name = coach_subject_name;
        }

        public String getCoach_time_name() {
            return coach_time_name;
        }

        public void setCoach_time_name(String coach_time_name) {
            this.coach_time_name = coach_time_name;
        }

        public String getStart_zo_height() {
            return start_zo_height;
        }

        public void setStart_zo_height(String start_zo_height) {
            this.start_zo_height = start_zo_height;
        }

        public String getEnd_zo_height() {
            return end_zo_height;
        }

        public void setEnd_zo_height(String end_zo_height) {
            this.end_zo_height = end_zo_height;
        }

        public String getStart_zo_weight() {
            return start_zo_weight;
        }

        public void setStart_zo_weight(String start_zo_weight) {
            this.start_zo_weight = start_zo_weight;
        }

        public String getEnd_zo_weight() {
            return end_zo_weight;
        }

        public void setEnd_zo_weight(String end_zo_weight) {
            this.end_zo_weight = end_zo_weight;
        }

        public String getEnd_zo_age() {
            return end_zo_age;
        }

        public void setEnd_zo_age(String end_zo_age) {
            this.end_zo_age = end_zo_age;
        }

        public String getStart_zo_age() {
            return start_zo_age;
        }

        public void setStart_zo_age(String start_zo_age) {
            this.start_zo_age = start_zo_age;
        }

        public String getSalary_name() {
            return salary_name;
        }

        public void setSalary_name(String salary_name) {
            this.salary_name = salary_name;
        }

        public int getIs_match() {
            return is_match;
        }

        public void setIs_match(int is_match) {
            this.is_match = is_match;
        }

        public String getWork_date() {
            return work_date;
        }

        public void setWork_date(String work_date) {
            this.work_date = work_date;
        }

        public int getWork_hour() {
            return work_hour;
        }

        public void setWork_hour(int work_hour) {
            this.work_hour = work_hour;
        }

        public String getWork_type_name() {
            return work_type_name;
        }

        public void setWork_type_name(String work_type_name) {
            this.work_type_name = work_type_name;
        }

        /**
         * 24 钟点工--应聘方
         */
        private String service_name;

        public String getService_name() {
            return service_name;
        }

        public void setService_name(String service_name) {
            this.service_name = service_name;
        }

        /**
         * user_name : 小明
         * match_id : 16
         * sex : 1
         * personal_assets_status :
         * live_second_area_name : 广州市
         * match_value : 90
         * age : 23
         * user_avatar_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/998307152360347334620180413_031111.jpg
         * is_push_msg : 0没点，1已点击确认完成
         * num : 3 还差几人
         * is_complaint : 0未投诉1已投诉
         */

        private String user_name;
        private int match_id;
        private int sex;
        private String personal_assets_status;
        private String live_second_area_name;
        private String match_value;
        private int age;
        private String user_avatar_url;
        private int is_push_msg;
        private int num;
        private int is_complaint;

        //21 6
        /**
         * address : 广州市萝岗区
         * contact_person : 啊里妈妈
         * latitude : 23.162252
         * contact_tel : 13727577868
         * confirm_complete : 0
         * longitude : 113.435444
         */
        private String title;
        private String update_time;
        private String area_name;
        private int recruitment_id;
        private String address;
        private String contact_person;
        private String latitude;
        private String contact_tel;
        private int confirm_complete;
        private String longitude;
        private int house_id;

        //7
        /**
         * job_name : 移动开发
         * company_name :
         * job_resume_id : 81
         * recruitment_num : 5
         */
        private String job_name;
        private String company_name;
        private int job_resume_id;
        private int recruitment_num;

        //10
        //家教工作id
        private String look_for_tutor_id;


        //12 14 16 保姆 月嫂 育儿嫂

        private String sex_name;

        //18 19 钟点工
        /**
         * lookfor_bell_worker_id : 13
         * is_pay : 0
         */

        private int lookfor_bell_worker_id;
        private int is_pay;
        /**
         * true_name : 小强
         * match : 80
         */

        private String true_name;
        private String match;

        private long add_time;
        private int status;
        private String mobile_phone;
        private int require_id;

        private String assets_require;

        public int getHouse_id() {
            return house_id;
        }

        public void setHouse_id(int house_id) {
            this.house_id = house_id;
        }

        public int getIs_complaint() {
            return is_complaint;
        }

        public void setIs_complaint(int is_complaint) {
            this.is_complaint = is_complaint;
        }

        public String getAssets_require() {
            return assets_require;
        }

        public void setAssets_require(String assets_require) {
            this.assets_require = assets_require;
        }

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }

        public int getRequire_id() {
            return require_id;
        }

        public void setRequire_id(int require_id) {
            this.require_id = require_id;
        }

        public String getSex_name() {
            return sex_name;
        }

        public void setSex_name(String sex_name) {
            this.sex_name = sex_name;
        }

        public String getLook_for_tutor_id() {
            return look_for_tutor_id;
        }

        public void setLook_for_tutor_id(String look_for_tutor_id) {
            this.look_for_tutor_id = look_for_tutor_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public int getRecruitment_id() {
            return recruitment_id;
        }

        public void setRecruitment_id(int recruitment_id) {
            this.recruitment_id = recruitment_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getMatch_id() {
            return match_id;
        }

        public void setMatch_id(int match_id) {
            this.match_id = match_id;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getPersonal_assets_status() {
            return personal_assets_status;
        }

        public void setPersonal_assets_status(String personal_assets_status) {
            this.personal_assets_status = personal_assets_status;
        }

        public String getLive_second_area_name() {
            return live_second_area_name;
        }

        public void setLive_second_area_name(String live_second_area_name) {
            this.live_second_area_name = live_second_area_name;
        }

        public String getMatch_value() {
            return match_value;
        }

        public void setMatch_value(String match_value) {
            this.match_value = match_value;
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

        public int getJob_resume_id() {
            return job_resume_id;
        }

        public void setJob_resume_id(int job_resume_id) {
            this.job_resume_id = job_resume_id;
        }

        public int getRecruitment_num() {
            return recruitment_num;
        }

        public void setRecruitment_num(int recruitment_num) {
            this.recruitment_num = recruitment_num;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getContact_person() {
            return contact_person;
        }

        public void setContact_person(String contact_person) {
            this.contact_person = contact_person;
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

        public int getConfirm_complete() {
            return confirm_complete;
        }

        public void setConfirm_complete(int confirm_complete) {
            this.confirm_complete = confirm_complete;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public int getLookfor_bell_worker_id() {
            return lookfor_bell_worker_id;
        }

        public void setLookfor_bell_worker_id(int lookfor_bell_worker_id) {
            this.lookfor_bell_worker_id = lookfor_bell_worker_id;
        }

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
            this.is_pay = is_pay;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public String getMatch() {
            return match;
        }

        public void setMatch(String match) {
            this.match = match;
        }

        public int getIs_push_msg() {
            return is_push_msg;
        }

        public void setIs_push_msg(int is_push_msg) {
            this.is_push_msg = is_push_msg;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
