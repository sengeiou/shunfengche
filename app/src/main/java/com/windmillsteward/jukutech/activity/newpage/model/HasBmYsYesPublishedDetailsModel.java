package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2018/10/21
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 职位详情model
 */
public class HasBmYsYesPublishedDetailsModel {

    /**
     * work_third_area_id : 3040
     * area_name : 广州市天河区
     * start_booking_time : 1535011079
     * person_type : 34
     * person_type_name : 中级保姆
     * latitude : 123.423435342
     * fee : 共800.01元,(含定金￥200.00)
     * require_list : [{"work_third_area_id":3040,"area_name":"广州市天河区","require_id":1,"user_name":"方小小","sex":1,"latitude":"43.234234","match_value":"80","user_avatar_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/0105051527761755997%E5%A3%81%E7%BA%B81.jpg","sex_name":"男","user_id":1,"work_second_area_id":289,"add_time":0,"longitude":"112.342423"}]
     * salary : 200.00-300.00元/天
     * recruitment_id : 1
     * work_experience : 30
     * longitude : 134.123123123
     * require_type : 1
     * end_salary : 300
     * address : 广东软件园
     * end_booking_time : 1535021079
     * work_time_type_name : 住家保姆
     * service_content : 43,44,45
     * booking_time : 2018-08-23-2018-08-23
     * order_price : 200.01
     * work_time_type : 2
     * user_id : 1
     * tail_price : 600
     * work_second_area_id : 289
     * service_contents : ["买菜做饭","带小孩子","科学喂养"]
     * start_salary : 200
     * add_time : 1538979687
     * work_experience_name : 1-2年
     * order_sn : 12345678910
     * status : 2
     */

    private int work_third_area_id;
    private String area_name;
    private int start_booking_time;
    private int person_type;
    private String person_type_name;
    private String latitude;
    private String fee;
    private String salary;
    private int recruitment_id;
    private int work_experience;
    private String longitude;
    private int require_type;
    private int end_salary;
    private String address;
    private int end_booking_time;
    private String work_time_type_name;
    private String service_content;
    private String booking_time;
    private double order_price;
    private int work_time_type;
    private int user_id;
    private int tail_price;
    private int work_second_area_id;
    private int start_salary;
    private int add_time;
    private String work_experience_name;
    private String order_sn;
    private int status;
    private List<RequireListBean> require_list;
    private List<String> service_contents;

    public int getWork_third_area_id() {
        return work_third_area_id;
    }

    public void setWork_third_area_id(int work_third_area_id) {
        this.work_third_area_id = work_third_area_id;
    }

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

    public int getPerson_type() {
        return person_type;
    }

    public void setPerson_type(int person_type) {
        this.person_type = person_type;
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

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public int getRecruitment_id() {
        return recruitment_id;
    }

    public void setRecruitment_id(int recruitment_id) {
        this.recruitment_id = recruitment_id;
    }

    public int getWork_experience() {
        return work_experience;
    }

    public void setWork_experience(int work_experience) {
        this.work_experience = work_experience;
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

    public int getEnd_booking_time() {
        return end_booking_time;
    }

    public void setEnd_booking_time(int end_booking_time) {
        this.end_booking_time = end_booking_time;
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

    public String getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(String booking_time) {
        this.booking_time = booking_time;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
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

    public int getTail_price() {
        return tail_price;
    }

    public void setTail_price(int tail_price) {
        this.tail_price = tail_price;
    }

    public int getWork_second_area_id() {
        return work_second_area_id;
    }

    public void setWork_second_area_id(int work_second_area_id) {
        this.work_second_area_id = work_second_area_id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<RequireListBean> getRequire_list() {
        return require_list;
    }

    public void setRequire_list(List<RequireListBean> require_list) {
        this.require_list = require_list;
    }

    public List<String> getService_contents() {
        return service_contents;
    }

    public void setService_contents(List<String> service_contents) {
        this.service_contents = service_contents;
    }

    public static class RequireListBean {
        /**
         * work_third_area_id : 3040
         * area_name : 广州市天河区
         * require_id : 1
         * user_name : 方小小
         * sex : 1
         * latitude : 43.234234
         * match_value : 80
         * user_avatar_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/0105051527761755997%E5%A3%81%E7%BA%B81.jpg
         * sex_name : 男
         * user_id : 1
         * work_second_area_id : 289
         * add_time : 0
         * longitude : 112.342423
         */

        private int work_third_area_id;
        private String area_name;
        private int require_id;
        private String user_name;
        private int sex;
        private String latitude;
        private String match_value;
        private String user_avatar_url;
        private String sex_name;
        private int user_id;
        private int work_second_area_id;
        private int add_time;
        private String longitude;

        public int getWork_third_area_id() {
            return work_third_area_id;
        }

        public void setWork_third_area_id(int work_third_area_id) {
            this.work_third_area_id = work_third_area_id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
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

        public int getWork_second_area_id() {
            return work_second_area_id;
        }

        public void setWork_second_area_id(int work_second_area_id) {
            this.work_second_area_id = work_second_area_id;
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
