package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2018/10/24
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class HasJiajiaoPublishedDetailsModel {

    /**
     * area_name : 广州市天河区
     * start_booking_time : 1539532800
     * coach_time : 2
     * latitude : 23.7342311
     * tail_fee : 6076
     * fee : 共8680.00元,(含定金￥2604.00)
     * second_area_id : 289
     * coach_subject_id : 55
     * salary : 220.00-280.00元/天
     * coach_time_list : ["工作日"]
     * longitude : 113.150123
     * coach_grade_name : 初中
     * address : 广东软件园B栋202
     * end_salary : 280
     * end_booking_time : 1542211200
     * booking_time : 2018-10-15-2018-11-15
     * prepay_fee : 2604
     * third_area_id : 3040
     * coach_grade_id : 58
     * when_tutor_list : [{"area_name":"广州市天河区","user_name":"高达","sex":1,"latitude":"23.4534545","second_area_id":289,"match_value":"70","third_area_id":3040,"when_tutor_id":1,"user_avatar_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/0105051527761755997%E5%A3%81%E7%BA%B81.jpg","sex_name":"男","user_id":1,"add_time":1523456453,"longitude":"112.343434"}]
     * coach_subject_name : 数学
     * look_for_tutor_id : 1
     * user_id : 1
     * start_salary : 220
     * order_sn : 0
     * status : 1
     */

    private String area_name;
    private int start_booking_time;
    private String coach_time;
    private String latitude;
    private int tail_fee;
    private String fee;
    private int second_area_id;
    private int coach_subject_id;
    private String salary;
    private String longitude;
    private String coach_grade_name;
    private String address;
    private int end_salary;
    private int end_booking_time;
    private String booking_time;
    private int prepay_fee;
    private int third_area_id;
    private int coach_grade_id;
    private String coach_subject_name;
    private int look_for_tutor_id;
    private int user_id;
    private int start_salary;
    private String order_sn;
    private int status;
    private List<String> coach_time_list;
    private List<WhenTutorListBean> when_tutor_list;

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public int getStart_booking_time() {
        return start_booking_time;
    }

    public void setStart_booking_time(int start_booking_time) {
        this.start_booking_time = start_booking_time;
    }

    public String getCoach_time() {
        return coach_time;
    }

    public void setCoach_time(String coach_time) {
        this.coach_time = coach_time;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getTail_fee() {
        return tail_fee;
    }

    public void setTail_fee(int tail_fee) {
        this.tail_fee = tail_fee;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public int getSecond_area_id() {
        return second_area_id;
    }

    public void setSecond_area_id(int second_area_id) {
        this.second_area_id = second_area_id;
    }

    public int getCoach_subject_id() {
        return coach_subject_id;
    }

    public void setCoach_subject_id(int coach_subject_id) {
        this.coach_subject_id = coach_subject_id;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEnd_salary() {
        return end_salary;
    }

    public void setEnd_salary(int end_salary) {
        this.end_salary = end_salary;
    }

    public int getEnd_booking_time() {
        return end_booking_time;
    }

    public void setEnd_booking_time(int end_booking_time) {
        this.end_booking_time = end_booking_time;
    }

    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }

    public int getPrepay_fee() {
        return prepay_fee;
    }

    public void setPrepay_fee(int prepay_fee) {
        this.prepay_fee = prepay_fee;
    }

    public int getThird_area_id() {
        return third_area_id;
    }

    public void setThird_area_id(int third_area_id) {
        this.third_area_id = third_area_id;
    }

    public int getCoach_grade_id() {
        return coach_grade_id;
    }

    public void setCoach_grade_id(int coach_grade_id) {
        this.coach_grade_id = coach_grade_id;
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

    public int getStart_salary() {
        return start_salary;
    }

    public void setStart_salary(int start_salary) {
        this.start_salary = start_salary;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getCoach_time_list() {
        return coach_time_list;
    }

    public void setCoach_time_list(List<String> coach_time_list) {
        this.coach_time_list = coach_time_list;
    }

    public List<WhenTutorListBean> getWhen_tutor_list() {
        return when_tutor_list;
    }

    public void setWhen_tutor_list(List<WhenTutorListBean> when_tutor_list) {
        this.when_tutor_list = when_tutor_list;
    }

    public static class WhenTutorListBean {
        /**
         * area_name : 广州市天河区
         * user_name : 高达
         * sex : 1
         * latitude : 23.4534545
         * second_area_id : 289
         * match_value : 70
         * third_area_id : 3040
         * when_tutor_id : 1
         * user_avatar_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/0105051527761755997%E5%A3%81%E7%BA%B81.jpg
         * sex_name : 男
         * user_id : 1
         * add_time : 1523456453
         * longitude : 112.343434
         */

        private String area_name;
        private String user_name;
        private int sex;
        private String latitude;
        private int second_area_id;
        private String match_value;
        private int third_area_id;
        private int when_tutor_id;
        private String user_avatar_url;
        private String sex_name;
        private int user_id;
        private int add_time;
        private String longitude;

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
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

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public int getSecond_area_id() {
            return second_area_id;
        }

        public void setSecond_area_id(int second_area_id) {
            this.second_area_id = second_area_id;
        }

        public String getMatch_value() {
            return match_value;
        }

        public void setMatch_value(String match_value) {
            this.match_value = match_value;
        }

        public int getThird_area_id() {
            return third_area_id;
        }

        public void setThird_area_id(int third_area_id) {
            this.third_area_id = third_area_id;
        }

        public int getWhen_tutor_id() {
            return when_tutor_id;
        }

        public void setWhen_tutor_id(int when_tutor_id) {
            this.when_tutor_id = when_tutor_id;
        }

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }

        public String getSex_name() {
            return sex_name;
        }

        public void setSex_name(String sex_name) {
            this.sex_name = sex_name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }
}
