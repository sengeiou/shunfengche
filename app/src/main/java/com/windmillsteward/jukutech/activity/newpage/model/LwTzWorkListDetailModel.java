package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * 劳务和特种工作信息列表详情
 */
public class LwTzWorkListDetailModel {


    /**
     * work_third_area_id : 2536
     * end_salary_fee : 0
     * address : 锦江之星品尚酒店(淄博博山客运站店)(山东省淄博市博山区珑山路179号)
     * work_date : 2019-05-14
     * contact_person : 木木
     * sex : 0
     * work_info : 描述是打发斯蒂芬;
     * latitude : 36.52095871033169
     * work_hour : 0
     * title : 招聘搬砖工3名,
     * total_match_num : 3
     * user_avatar_url : https://sfcgj.oss-cn-qingdao.aliyuncs.com/9537591545015507505wx7d7b71b9cc9cccd0.o6zAJs_F8ul4cO3SSiivJLwE_d-g.hwcAs6LPGlWb2d7cd657274e57a928d7ddd097dbed38.png
     * recruitment_id : 368
     * work_third_area_name : 博山区
     * salary_fee : 0
     * work_second_area_id : 225
     * salary_type : 1
     * work_type_name_list : [{"work_type_id":52,"recruitment_id":368,"match_num":2,"other_work_type":"","info_fee":0.01,"price":0.01,"num":3,"name":"搬砖工","labor_recruitment_info_id":521,"add_time":1557847694}]
     * work_second_area_name : 淄博市
     * longitude : 117.86708814797716
     */

    private int work_third_area_id;
    private int end_salary_fee;
    private String address;
    private String work_date;
    private String contact_person;
    private int sex;
    private String sex_name;
    private String work_info;
    private String latitude;
    private int work_hour;
    private String title;
    private int total_match_num;
    private String user_avatar_url;
    private String age_name;
    private int recruitment_id;
    private int recruitment_num;
    private String work_third_area_name;
    private int salary_fee;
    private int work_second_area_id;
    private int salary_type;
    private String work_second_area_name;
    private String longitude;
    private List<WorkTypeNameListBean> work_type_name_list;
    private String order_sn;
    private String enterprise_name;
    private long add_time;

    public String getAge_name() {
        return age_name;
    }

    public void setAge_name(String age_name) {
        this.age_name = age_name;
    }

    public String getSex_name() {
        return sex_name;
    }

    public void setSex_name(String sex_name) {
        this.sex_name = sex_name;
    }


    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
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

    public int getWork_third_area_id() {
        return work_third_area_id;
    }

    public void setWork_third_area_id(int work_third_area_id) {
        this.work_third_area_id = work_third_area_id;
    }

    public int getEnd_salary_fee() {
        return end_salary_fee;
    }

    public void setEnd_salary_fee(int end_salary_fee) {
        this.end_salary_fee = end_salary_fee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWork_date() {
        return work_date;
    }

    public void setWork_date(String work_date) {
        this.work_date = work_date;
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

    public String getWork_info() {
        return work_info;
    }

    public void setWork_info(String work_info) {
        this.work_info = work_info;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getWork_hour() {
        return work_hour;
    }

    public void setWork_hour(int work_hour) {
        this.work_hour = work_hour;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal_match_num() {
        return total_match_num;
    }

    public void setTotal_match_num(int total_match_num) {
        this.total_match_num = total_match_num;
    }

    public String getUser_avatar_url() {
        return user_avatar_url;
    }

    public void setUser_avatar_url(String user_avatar_url) {
        this.user_avatar_url = user_avatar_url;
    }

    public int getRecruitment_id() {
        return recruitment_id;
    }

    public void setRecruitment_id(int recruitment_id) {
        this.recruitment_id = recruitment_id;
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

    public int getWork_second_area_id() {
        return work_second_area_id;
    }

    public void setWork_second_area_id(int work_second_area_id) {
        this.work_second_area_id = work_second_area_id;
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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<WorkTypeNameListBean> getWork_type_name_list() {
        return work_type_name_list;
    }

    public void setWork_type_name_list(List<WorkTypeNameListBean> work_type_name_list) {
        this.work_type_name_list = work_type_name_list;
    }

    public static class WorkTypeNameListBean {
        /**
         * work_type_id : 52
         * recruitment_id : 368
         * match_num : 2
         * other_work_type :
         * info_fee : 0.01
         * price : 0.01
         * num : 3
         * name : 搬砖工
         * labor_recruitment_info_id : 521
         * add_time : 1557847694
         */

        private int work_type_id;
        private int recruitment_id;
        private int match_num;
        private String other_work_type;
        private double info_fee;
        private double price;
        private int num;
        private String name;
        private int labor_recruitment_info_id;
        private int add_time;

        public int getWork_type_id() {
            return work_type_id;
        }

        public void setWork_type_id(int work_type_id) {
            this.work_type_id = work_type_id;
        }

        public int getRecruitment_id() {
            return recruitment_id;
        }

        public void setRecruitment_id(int recruitment_id) {
            this.recruitment_id = recruitment_id;
        }

        public int getMatch_num() {
            return match_num;
        }

        public void setMatch_num(int match_num) {
            this.match_num = match_num;
        }

        public String getOther_work_type() {
            return other_work_type;
        }

        public void setOther_work_type(String other_work_type) {
            this.other_work_type = other_work_type;
        }

        public double getInfo_fee() {
            return info_fee;
        }

        public void setInfo_fee(double info_fee) {
            this.info_fee = info_fee;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLabor_recruitment_info_id() {
            return labor_recruitment_info_id;
        }

        public void setLabor_recruitment_info_id(int labor_recruitment_info_id) {
            this.labor_recruitment_info_id = labor_recruitment_info_id;
        }

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }
    }
}
