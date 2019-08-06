package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2018/10/21
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 保姆 月嫂 育儿嫂 招聘方 model
 */
public class CommBmZhaoPinOrderDetailsModel {


    /**
     * start_booking_time : 1557417600
     * user_name : 木木
     * person_type_name : 初级育儿嫂
     * latitude : 36.52148077396836
     * uninterested_num : 2
     * title : 招聘初级育儿嫂
     * work_third_area_name : 博山区
     * info_fee : 1
     * service_content_name : 科学喂养,婴儿护理,清洁工作
     * mobile_phone : 13802739976
     * house_keeping_require_list : []
     * work_second_area_name : 淄博市
     * longitude : 117.86788763988103
     * require_type : 3
     * is_match : 4
     * end_salary : 0
     * address : 中国农业银行(凤凰城分理处)(山东省淄博市博山区珑山路166号)
     * sex : 2
     * end_booking_time : 1557590400
     * work_info : 工作描述不喝酒呢
     * work_time_type_name : 住家育儿嫂
     * service_content : 48,49,50
     * user_avatar_url : https://sfcgj.oss-cn-qingdao.aliyuncs.com/9537591545015507505wx7d7b71b9cc9cccd0.o6zAJs_F8ul4cO3SSiivJLwE_d-g.hwcAs6LPGlWb2d7cd657274e57a928d7ddd097dbed38.png
     * age_name : 不限
     * work_time_type : 3
     * user_id : 2
     * house_keeping_require : {"user_name":"sfc13","person_type_name":"初级育儿嫂","latitude":"36.520998","work_third_area_name":"高青县","salary_fee":0.01,"info_fee":1,"service_content_name":"科学喂养,婴儿护理,清洁工作","mobile_phone":"13727574858","work_second_area_name":"淄博市","longitude":"117.868868","require_type":3,"end_salary_fee":0,"is_match":1,"sex":-1,"work_time_type_name":"住家育儿嫂","service_content":"48,49,50","match_value":"100","is_uninterested":0,"user_id":194,"work_time_type":2,"self_intro":"工作描述吃不饱","salary_type":1,"add_time":1557395090,"age":39,"work_experience_name":"1-2年","order_sn":"2019050905405780814171","education_background_name":"高中","status":4}
     * salary_type : 1
     * start_salary : 0.01
     * add_time : 1557395044
     * order_sn : 2019050905440984229517
     * work_experience_name : 1-2年
     * education_background_name : 高中
     * status : 4
     */

    private long start_booking_time;
    private String user_name;
    private String person_type_name;
    private String latitude;
    private int uninterested_num;
    private String title;
    private String work_third_area_name;
    private double info_fee;
    private String service_content_name;
    private String mobile_phone;
    private String work_second_area_name;
    private String longitude;
    private int require_type;
    private int is_match;
    private int end_salary;
    private String address;
    private int sex;
    private long end_booking_time;
    private String work_info;
    private String work_time_type_name;
    private String service_content;
    private String user_avatar_url;
    private String age_name;
    private int work_time_type;
    private int user_id;
    private HouseKeepingRequireBean house_keeping_require;
    private int salary_type;
    private int start_salary;
    private int add_time;
    private String order_sn;
    private String work_experience_name;
    private String education_background_name;
    private int status;
    private List<HouseKeepingRequireBean> house_keeping_require_list;
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

    public String getPerson_type_name() {
        return person_type_name;
    }

    public void setPerson_type_name(String person_type_name) {
        this.person_type_name = person_type_name;
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

    public String getService_content_name() {
        return service_content_name;
    }

    public void setService_content_name(String service_content_name) {
        this.service_content_name = service_content_name;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getRequire_type() {
        return require_type;
    }

    public void setRequire_type(int require_type) {
        this.require_type = require_type;
    }

    public int getIs_match() {
        return is_match;
    }

    public void setIs_match(int is_match) {
        this.is_match = is_match;
    }

    public int getEnd_salary() {
        return end_salary;
    }

    public void setEnd_salary(int end_salary) {
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

    public String getWork_time_type_name() {
        return work_time_type_name;
    }

    public void setWork_time_type_name(String work_time_type_name) {
        this.work_time_type_name = work_time_type_name;
    }

    public String getService_content() {
        return service_content;
    }

    public void setService_content(String service_content) {
        this.service_content = service_content;
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

    public int getWork_time_type() {
        return work_time_type;
    }

    public void setWork_time_type(int work_time_type) {
        this.work_time_type = work_time_type;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public HouseKeepingRequireBean getHouse_keeping_require() {
        return house_keeping_require;
    }

    public void setHouse_keeping_require(HouseKeepingRequireBean house_keeping_require) {
        this.house_keeping_require = house_keeping_require;
    }

    public int getSalary_type() {
        return salary_type;
    }

    public void setSalary_type(int salary_type) {
        this.salary_type = salary_type;
    }

    public int getStart_salary() {
        return start_salary;
    }

    public void setStart_salary(int start_salary) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<HouseKeepingRequireBean> getHouse_keeping_require_list() {
        return house_keeping_require_list;
    }

    public void setHouse_keeping_require_list(List<HouseKeepingRequireBean> house_keeping_require_list) {
        this.house_keeping_require_list = house_keeping_require_list;
    }

    public static class HouseKeepingRequireBean {
        /**
         * user_name : sfc13
         * person_type_name : 初级育儿嫂
         * latitude : 36.520998
         * work_third_area_name : 高青县
         * salary_fee : 0.01
         * info_fee : 1
         * service_content_name : 科学喂养,婴儿护理,清洁工作
         * mobile_phone : 13727574858
         * work_second_area_name : 淄博市
         * longitude : 117.868868
         * require_type : 3
         * end_salary_fee : 0
         * is_match : 1
         * sex : -1
         * work_time_type_name : 住家育儿嫂
         * service_content : 48,49,50
         * match_value : 100
         * is_uninterested : 0
         * user_id : 194
         * work_time_type : 2
         * self_intro : 工作描述吃不饱
         * salary_type : 1
         * add_time : 1557395090
         * age : 39
         * work_experience_name : 1-2年
         * order_sn : 2019050905405780814171
         * education_background_name : 高中
         * status : 4
         * is_evaluation : 4
         */

        private String user_name;
        private String user_avatar_url;
        private String person_type_name;
        private String latitude;
        private String work_third_area_name;
        private int salary_fee;
        private int info_fee;
        private int require_id;
        private int is_evaluation;
        private String service_content_name;
        private String mobile_phone;
        private String work_second_area_name;
        private String longitude;
        private int require_type;
        private int end_salary_fee;
        private int is_match;
        private int sex;
        private String work_time_type_name;
        private String service_content;
        private String match_value;
        private int is_uninterested;
        private int user_id;
        private int work_time_type;
        private String self_intro;
        private int salary_type;
        private long add_time;
        private int age;
        private String work_experience_name;
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

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }

        public int getRequire_id() {
            return require_id;
        }

        public void setRequire_id(int require_id) {
            this.require_id = require_id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getPerson_type_name() {
            return person_type_name;
        }

        public void setPerson_type_name(String person_type_name) {
            this.person_type_name = person_type_name;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
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

        public int getInfo_fee() {
            return info_fee;
        }

        public void setInfo_fee(int info_fee) {
            this.info_fee = info_fee;
        }

        public String getService_content_name() {
            return service_content_name;
        }

        public void setService_content_name(String service_content_name) {
            this.service_content_name = service_content_name;
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

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public int getRequire_type() {
            return require_type;
        }

        public void setRequire_type(int require_type) {
            this.require_type = require_type;
        }

        public int getEnd_salary_fee() {
            return end_salary_fee;
        }

        public void setEnd_salary_fee(int end_salary_fee) {
            this.end_salary_fee = end_salary_fee;
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

        public String getWork_time_type_name() {
            return work_time_type_name;
        }

        public void setWork_time_type_name(String work_time_type_name) {
            this.work_time_type_name = work_time_type_name;
        }

        public String getService_content() {
            return service_content;
        }

        public void setService_content(String service_content) {
            this.service_content = service_content;
        }

        public String getMatch_value() {
            return match_value;
        }

        public void setMatch_value(String match_value) {
            this.match_value = match_value;
        }

        public int getIs_uninterested() {
            return is_uninterested;
        }

        public void setIs_uninterested(int is_uninterested) {
            this.is_uninterested = is_uninterested;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getWork_time_type() {
            return work_time_type;
        }

        public void setWork_time_type(int work_time_type) {
            this.work_time_type = work_time_type;
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

        public long getAdd_time() {
            return add_time;
        }

        public void setAdd_time(long add_time) {
            this.add_time = add_time;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getWork_experience_name() {
            return work_experience_name;
        }

        public void setWork_experience_name(String work_experience_name) {
            this.work_experience_name = work_experience_name;
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
