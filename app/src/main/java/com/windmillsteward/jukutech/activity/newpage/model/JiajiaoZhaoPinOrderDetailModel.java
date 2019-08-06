package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 10/24/18
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class JiajiaoZhaoPinOrderDetailModel {


    /**
     * coach_time : 1,2
     * start_booking_time : 1545235200
     * user_name : 木木122123213
     * latitude : 23.128302189403545
     * uninterested_num : 1
     * title : 招聘高中语文老师
     * work_third_area_name : 海珠区
     * info_fee : 2
     * tutor_pay_type : 2
     * mobile_phone : 13802739976
     * when_tutor : {"coach_time":"1,2","user_name":"赢政","latitude":"","salary":400,"work_third_area_name":"海珠区","info_fee":15,"mobile_phone":"13226508191","coach_grade_ids":"57,58,59","work_second_area_name":"广州市","head_portrait":"","longitude":"","end_salary_fee":0,"coach_grade_name":"小学,初中,高中","sex":1,"order_price":15,"coach_time_name":"周末,工作日","when_tutor_id":57,"is_uninterested":0,"coach_subject_name":"语文","look_for_tutor_id":61,"user_id":4,"self_intro":"我要当语文老师","salary_type":2,"add_time":1545032080,"order_id":0,"age":23,"order_sn":"2018121703342314684681","education_background_name":"本科","status":4}
     * work_second_area_name : 广州市
     * longitude : 113.36749996139297
     * coach_grade_name : 高中
     * is_match : 4
     * end_salary : 600
     * address : 广东省广州市天河区员村西街东璟花园
     * sex : 1
     * end_booking_time : 1545494400
     * work_info : 招语文老师
     * coach_time_name : 周末,工作日
     * user_avatar_url : https://sfcgj.oss-cn-qingdao.aliyuncs.com/9537591545015507505wx7d7b71b9cc9cccd0.o6zAJs_F8ul4cO3SSiivJLwE_d-g.hwcAs6LPGlWb2d7cd657274e57a928d7ddd097dbed38.png
     * age_name : 不限
     * when_tutor_list : []
     * coach_subject_name : 语文
     * user_id : 2
     * salary_type : 2
     * start_salary : 200
     * add_time : 1545029813
     * order_sn : 2018121702570065642219
     * education_background_name : 本科
     * status : 2
     */

    private String coach_time;
    private long start_booking_time;
    private String user_name;
    private String latitude;
    private int uninterested_num;
    private String title;
    private String work_third_area_name;
    private double info_fee;
    private int tutor_pay_type;
    private String mobile_phone;
    private WhenTutorBean when_tutor;
    private String work_second_area_name;
    private String longitude;
    private String coach_grade_name;
    private int is_match;
    private String end_salary;
    private String address;
    private int sex;
    private long end_booking_time;
    private String work_info;
    private String coach_time_name;
    private String user_avatar_url;
    private String age_name;
    private String coach_subject_name;
    private int user_id;
    private int salary_type;
    private String start_salary;
    private int add_time;
    private String order_sn;
    private String education_background_name;
    private int status;
    private List<WhenTutorBean> when_tutor_list;
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

    public String getCoach_time() {
        return coach_time;
    }

    public void setCoach_time(String coach_time) {
        this.coach_time = coach_time;
    }

    public long getStart_booking_time() {
        return start_booking_time;
    }

    public void setStart_booking_time(long start_booking_time) {
        this.start_booking_time = start_booking_time;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWork_third_area_name() {
        return work_third_area_name;
    }

    public void setWork_third_area_name(String work_third_area_name) {
        this.work_third_area_name = work_third_area_name;
    }

    public double getInfo_fee() {
        return info_fee;
    }

    public void setInfo_fee(double info_fee) {
        this.info_fee = info_fee;
    }

    public int getTutor_pay_type() {
        return tutor_pay_type;
    }

    public void setTutor_pay_type(int tutor_pay_type) {
        this.tutor_pay_type = tutor_pay_type;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public WhenTutorBean getWhen_tutor() {
        return when_tutor;
    }

    public void setWhen_tutor(WhenTutorBean when_tutor) {
        this.when_tutor = when_tutor;
    }

    public String getWork_second_area_name() {
        return work_second_area_name;
    }

    public void setWork_second_area_name(String work_second_area_name) {
        this.work_second_area_name = work_second_area_name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCoach_grade_name() {
        return coach_grade_name;
    }

    public void setCoach_grade_name(String coach_grade_name) {
        this.coach_grade_name = coach_grade_name;
    }

    public int getIs_match() {
        return is_match;
    }

    public void setIs_match(int is_match) {
        this.is_match = is_match;
    }

    public String getEnd_salary() {
        return end_salary;
    }

    public void setEnd_salary(String end_salary) {
        this.end_salary = end_salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public long getEnd_booking_time() {
        return end_booking_time;
    }

    public void setEnd_booking_time(long end_booking_time) {
        this.end_booking_time = end_booking_time;
    }

    public String getWork_info() {
        return work_info;
    }

    public void setWork_info(String work_info) {
        this.work_info = work_info;
    }

    public String getCoach_time_name() {
        return coach_time_name;
    }

    public void setCoach_time_name(String coach_time_name) {
        this.coach_time_name = coach_time_name;
    }

    public String getUser_avatar_url() {
        return user_avatar_url;
    }

    public void setUser_avatar_url(String user_avatar_url) {
        this.user_avatar_url = user_avatar_url;
    }

    public String getAge_name() {
        return age_name;
    }

    public void setAge_name(String age_name) {
        this.age_name = age_name;
    }

    public String getCoach_subject_name() {
        return coach_subject_name;
    }

    public void setCoach_subject_name(String coach_subject_name) {
        this.coach_subject_name = coach_subject_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getSalary_type() {
        return salary_type;
    }

    public void setSalary_type(int salary_type) {
        this.salary_type = salary_type;
    }

    public String getStart_salary() {
        return start_salary;
    }

    public void setStart_salary(String start_salary) {
        this.start_salary = start_salary;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<WhenTutorBean> getWhen_tutor_list() {
        return when_tutor_list;
    }

    public void setWhen_tutor_list(List<WhenTutorBean> when_tutor_list) {
        this.when_tutor_list = when_tutor_list;
    }

    public static class WhenTutorBean {
        /**
         * coach_time : 1,2
         * user_name : 赢政
         * latitude :
         * salary : 400
         * work_third_area_name : 海珠区
         * info_fee : 15
         * mobile_phone : 13226508191
         * coach_grade_ids : 57,58,59
         * work_second_area_name : 广州市
         * head_portrait :
         * longitude :
         * end_salary_fee : 0
         * coach_grade_name : 小学,初中,高中
         * sex : 1
         * order_price : 15
         * coach_time_name : 周末,工作日
         * when_tutor_id : 57
         * is_uninterested : 0
         * coach_subject_name : 语文
         * look_for_tutor_id : 61
         * user_id : 4
         * self_intro : 我要当语文老师
         * salary_type : 2
         * add_time : 1545032080
         * order_id : 0
         * age : 23
         * order_sn : 2018121703342314684681
         * education_background_name : 本科
         * status : 4
         * tutor_matching_id : 4
         * is_evaluation : 4
         */

        private String user_avatar_url;
        private String match_value;
        private String coach_time;
        private String user_name;
        private String latitude;
        private String salary;
        private String work_third_area_name;
        private double info_fee;
        private String mobile_phone;
        private String coach_grade_ids;
        private String work_second_area_name;
        private String head_portrait;
        private String longitude;
        private String end_salary_fee;
        private String coach_grade_name;
        private int sex;
        private int is_evaluation;
        private int order_price;
        private int tutor_matching_id;
        private String coach_time_name;
        private int when_tutor_id;
        private int is_uninterested;
        private String coach_subject_name;
        private int look_for_tutor_id;
        private int user_id;
        private String self_intro;
        private int salary_type;
        private int add_time;
        private int order_id;
        private int age;
        private String order_sn;
        private String education_background_name;
        private int status;
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

        public int getIs_evaluation() {
            return is_evaluation;
        }

        public void setIs_evaluation(int is_evaluation) {
            this.is_evaluation = is_evaluation;
        }

        public int getTutor_matching_id() {
            return tutor_matching_id;
        }

        public void setTutor_matching_id(int tutor_matching_id) {
            this.tutor_matching_id = tutor_matching_id;
        }

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }

        public String getMatch_value() {
            return match_value;
        }

        public void setMatch_value(String match_value) {
            this.match_value = match_value;
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

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getWork_third_area_name() {
            return work_third_area_name;
        }

        public void setWork_third_area_name(String work_third_area_name) {
            this.work_third_area_name = work_third_area_name;
        }

        public double getInfo_fee() {
            return info_fee;
        }

        public void setInfo_fee(double info_fee) {
            this.info_fee = info_fee;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }

        public String getCoach_grade_ids() {
            return coach_grade_ids;
        }

        public void setCoach_grade_ids(String coach_grade_ids) {
            this.coach_grade_ids = coach_grade_ids;
        }

        public String getWork_second_area_name() {
            return work_second_area_name;
        }

        public void setWork_second_area_name(String work_second_area_name) {
            this.work_second_area_name = work_second_area_name;
        }

        public String getHead_portrait() {
            return head_portrait;
        }

        public void setHead_portrait(String head_portrait) {
            this.head_portrait = head_portrait;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getEnd_salary_fee() {
            return end_salary_fee;
        }

        public void setEnd_salary_fee(String end_salary_fee) {
            this.end_salary_fee = end_salary_fee;
        }

        public String getCoach_grade_name() {
            return coach_grade_name;
        }

        public void setCoach_grade_name(String coach_grade_name) {
            this.coach_grade_name = coach_grade_name;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getOrder_price() {
            return order_price;
        }

        public void setOrder_price(int order_price) {
            this.order_price = order_price;
        }

        public String getCoach_time_name() {
            return coach_time_name;
        }

        public void setCoach_time_name(String coach_time_name) {
            this.coach_time_name = coach_time_name;
        }

        public int getWhen_tutor_id() {
            return when_tutor_id;
        }

        public void setWhen_tutor_id(int when_tutor_id) {
            this.when_tutor_id = when_tutor_id;
        }

        public int getIs_uninterested() {
            return is_uninterested;
        }

        public void setIs_uninterested(int is_uninterested) {
            this.is_uninterested = is_uninterested;
        }

        public String getCoach_subject_name() {
            return coach_subject_name;
        }

        public void setCoach_subject_name(String coach_subject_name) {
            this.coach_subject_name = coach_subject_name;
        }

        public int getLook_for_tutor_id() {
            return look_for_tutor_id;
        }

        public void setLook_for_tutor_id(int look_for_tutor_id) {
            this.look_for_tutor_id = look_for_tutor_id;
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

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
