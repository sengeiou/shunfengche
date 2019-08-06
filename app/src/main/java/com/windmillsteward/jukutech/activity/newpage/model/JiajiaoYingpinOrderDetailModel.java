package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 10/24/18
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述 家教-招聘方订单详情实体
 */
public class JiajiaoYingpinOrderDetailModel {


    /**
     * look_for_tutor : {"coach_time":"1,2","start_booking_time":1554998400,"user_name":"啊强","latitude":"23.168606781112214","uninterested_num":2,"work_third_area_name":"萝岗区","info_fee":2,"tutor_pay_type":2,"work_second_area_name":"广州市","longitude":"113.44330180203274","coach_grade_name":"小学","end_salary":1500,"address":"广东软件园(广东省广州市黄埔区科学城彩频路11号)","sex":0,"end_booking_time":1555084800,"work_info":"超级保镖","coach_time_name":"周末,工作日","match_value":"100","age_name":"不限","is_uninterested":0,"coach_subject_name":"语文","user_id":6,"salary_type":2,"start_salary":500,"add_time":1554877130,"order_sn":"2019041002185284490648","education_background_name":"高中","status":4}
     * coach_time : 1,2
     * user_name : 龙计算机
     * latitude : 23.168225
     * salary : 0
     * work_third_area_name : 萝岗区
     * info_fee : 15
     * mobile_phone : 13802739976
     * coach_grade_ids : 57,58,59
     * work_second_area_name : 广州市
     * head_portrait :
     * longitude : 113.442047
     * end_salary_fee : 1800
     * coach_grade_name : 小学,初中,高中
     * is_match : 2
     * sex : 1
     * order_price : 15
     * coach_time_name : 周末,工作日
     * coach_subject_name : 语文
     * user_id : 2
     * self_intro : vxvsbs
     * salary_type : 2
     * add_time : 1554877421
     * order_id : 0
     * age : 36
     * order_sn : 2019041002234810096595
     * education_background_name : 高中
     * status : 4
     */

    private LookForTutorBean look_for_tutor;
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
    private int is_match;
    private int sex;
    private int order_price;
    private String coach_time_name;
    private String coach_subject_name;
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

    public LookForTutorBean getLook_for_tutor() {
        return look_for_tutor;
    }

    public void setLook_for_tutor(LookForTutorBean look_for_tutor) {
        this.look_for_tutor = look_for_tutor;
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

    public int getIs_match() {
        return is_match;
    }

    public void setIs_match(int is_match) {
        this.is_match = is_match;
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

    public static class LookForTutorBean {
        /**
         * coach_time : 1,2
         * start_booking_time : 1554998400
         * user_name : 啊强
         * latitude : 23.168606781112214
         * uninterested_num : 2
         * work_third_area_name : 萝岗区
         * info_fee : 2
         * tutor_pay_type : 2
         * work_second_area_name : 广州市
         * longitude : 113.44330180203274
         * coach_grade_name : 小学
         * end_salary : 1500
         * address : 广东软件园(广东省广州市黄埔区科学城彩频路11号)
         * sex : 0
         * end_booking_time : 1555084800
         * work_info : 超级保镖
         * coach_time_name : 周末,工作日
         * match_value : 100
         * age_name : 不限
         * is_uninterested : 0
         * coach_subject_name : 语文
         * user_id : 6
         * salary_type : 2
         * start_salary : 500
         * add_time : 1554877130
         * order_sn : 2019041002185284490648
         * education_background_name : 高中
         * status : 4
         * title : zczczxcz
         */

        private String coach_time;
        private String user_avatar_url;
        private String title;
        private String mobile_phone;
        private long start_booking_time;
        private String user_name;
        private String latitude;
        private int uninterested_num;
        private String work_third_area_name;
        private double info_fee;
        private int tutor_pay_type;
        private String work_second_area_name;
        private String longitude;
        private String coach_grade_name;
        private String end_salary;
        private String address;
        private int sex;
        private int age;
        private long end_booking_time;
        private String work_info;
        private String coach_time_name;
        private String match_value;
        private String age_name;
        private int is_uninterested;
        private String coach_subject_name;
        private int user_id;
        private int salary_type;
        private String start_salary;
        private int add_time;
        private String order_sn;
        private String education_background_name;
        private int status;
        private String voice_url;
        private String image_url;
        private String video_url;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
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

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
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

        public String getMatch_value() {
            return match_value;
        }

        public void setMatch_value(String match_value) {
            this.match_value = match_value;
        }

        public String getAge_name() {
            return age_name;
        }

        public void setAge_name(String age_name) {
            this.age_name = age_name;
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
    }
}
