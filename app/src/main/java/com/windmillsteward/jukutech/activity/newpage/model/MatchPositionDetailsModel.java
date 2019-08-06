package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2018/10/17
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class MatchPositionDetailsModel {

    /**
     * work_date : 2018-10-08
     * latitude : 113.4064504678
     * work_info : 工作描述: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
     * match_time : 1538992800
     * order_price : 0.01
     * title : 招聘水泥工3名,搬砖工4名,特级搬砖工4名
     * recruitment_id : 29
     * work_third_area_name : 天河区
     * info_fee : 0
     * work_type_list : [{"work_type_id":51,"recruitment_id":29,"match_num":1,"other_work_type":"","price":200,"num":3,"name":"水泥工","labor_recruitment_info_id":16,"add_time":1539152936},{"work_type_id":52,"recruitment_id":29,"match_num":0,"other_work_type":"","price":250,"num":4,"name":"搬砖工","labor_recruitment_info_id":17,"add_time":1539152936},{"work_type_id":0,"recruitment_id":29,"match_num":0,"other_work_type":"特级搬砖工","price":300,"num":4,"name":null,"labor_recruitment_info_id":18,"add_time":1539152936}]
     * order_sn : 2018092906045640338666
     * work_second_area_name : 广州市
     * longitude : 23.1199587650
     */

    private String work_date;
    private String latitude;
    private String work_info;
    private String match_time;
    private double order_price;
    private String title;
    private int recruitment_id;
    private String work_third_area_name;
    private double info_fee;
    private String order_sn;
    private String work_second_area_name;
    private String longitude;
    private List<WorkTypeListBean> work_type_name_list;
    private int confirm_complete;
    private String address;
    private String match_value;
    private String avatar_url;
    private String contact_person;
    private String contact_tel;
    private int salary_type;
    private int sex;
    private int is_macth;
    private int age;
    private String work_type_ids;
    private String other_work_type;
    private String self_intro;
    private String info_id;
    private String work_second_area_id;
    private String name;
    private String macth_work_name;
    private String salary_fee;
    private String end_salary_fee;
    private int work_hour;//工作时间，暂不用匹配时间字段
    private int work_third_area_id;
    private int type;
    private String work_type_name;
    private long update_time;
    private long add_time;
    private WorkDetail  recruitment;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getIs_macth() {
        return is_macth;
    }

    public void setIs_macth(int is_macth) {
        this.is_macth = is_macth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getWork_type_ids() {
        return work_type_ids;
    }

    public void setWork_type_ids(String work_type_ids) {
        this.work_type_ids = work_type_ids;
    }

    public String getOther_work_type() {
        return other_work_type;
    }

    public void setOther_work_type(String other_work_type) {
        this.other_work_type = other_work_type;
    }

    public String getSelf_intro() {
        return self_intro;
    }

    public void setSelf_intro(String self_intro) {
        this.self_intro = self_intro;
    }

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }

    public String getWork_second_area_id() {
        return work_second_area_id;
    }

    public void setWork_second_area_id(String work_second_area_id) {
        this.work_second_area_id = work_second_area_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMacth_work_name() {
        return macth_work_name;
    }

    public void setMacth_work_name(String macth_work_name) {
        this.macth_work_name = macth_work_name;
    }

    public int getWork_third_area_id() {
        return work_third_area_id;
    }

    public void setWork_third_area_id(int work_third_area_id) {
        this.work_third_area_id = work_third_area_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getWork_type_name() {
        return work_type_name;
    }

    public void setWork_type_name(String work_type_name) {
        this.work_type_name = work_type_name;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public WorkDetail getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(WorkDetail recruitment) {
        this.recruitment = recruitment;
    }

    public static class WorkDetail{
        private int work_third_area_id;
        private int work_second_area_id;
        private int sex;
        private int total_match_num;
        private int recruitment_id;
        private int salary_type;
        private int work_hour;
        private String work_third_area_name;
        private String salary_fee;
        private String end_salary_fee;
        private String address;
        private String work_date;
        private String contact_person;
        private String work_info;
        private String longitude;
        private String latitude;
        private String contact_tel;
        private String work_second_area_name;
        private String order_sn;
        private String title;
        private String match_value;
        private List<WorkTypeListBean> work_type_name_list;
        private String work_type_name;
        private String voice_url;
        private String image_url;
        private String video_url;
        private String user_avatar_url;

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
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

        public List<WorkTypeListBean> getWork_type_name_list() {
            return work_type_name_list;
        }

        public void setWork_type_name_list(List<WorkTypeListBean> work_type_name_list) {
            this.work_type_name_list = work_type_name_list;
        }

        public String getWork_type_name() {
            return work_type_name;
        }

        public void setWork_type_name(String work_type_name) {
            this.work_type_name = work_type_name;
        }

        public String getMatch_value() {
            return match_value;
        }

        public void setMatch_value(String match_value) {
            this.match_value = match_value;
        }

        public int getWork_hour() {
            return work_hour;
        }

        public void setWork_hour(int work_hour) {
            this.work_hour = work_hour;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWork_third_area_id() {
            return work_third_area_id;
        }

        public void setWork_third_area_id(int work_third_area_id) {
            this.work_third_area_id = work_third_area_id;
        }

        public int getWork_second_area_id() {
            return work_second_area_id;
        }

        public void setWork_second_area_id(int work_second_area_id) {
            this.work_second_area_id = work_second_area_id;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getTotal_match_num() {
            return total_match_num;
        }

        public void setTotal_match_num(int total_match_num) {
            this.total_match_num = total_match_num;
        }

        public int getRecruitment_id() {
            return recruitment_id;
        }

        public void setRecruitment_id(int recruitment_id) {
            this.recruitment_id = recruitment_id;
        }

        public int getSalary_type() {
            return salary_type;
        }

        public void setSalary_type(int salary_type) {
            this.salary_type = salary_type;
        }

        public String getWork_third_area_name() {
            return work_third_area_name;
        }

        public void setWork_third_area_name(String work_third_area_name) {
            this.work_third_area_name = work_third_area_name;
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

        public String getWork_info() {
            return work_info;
        }

        public void setWork_info(String work_info) {
            this.work_info = work_info;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
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

        public String getWork_second_area_name() {
            return work_second_area_name;
        }

        public void setWork_second_area_name(String work_second_area_name) {
            this.work_second_area_name = work_second_area_name;
        }
    }

    public int getWork_hour() {
        return work_hour;
    }

    public void setWork_hour(int work_hour) {
        this.work_hour = work_hour;
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

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
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

    public String getMatch_value() {
        return match_value;
    }

    public void setMatch_value(String match_value) {
        this.match_value = match_value;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getConfirm_complete() {
        return confirm_complete;
    }

    public void setConfirm_complete(int confirm_complete) {
        this.confirm_complete = confirm_complete;
    }

    public String getWork_date() {
        return work_date;
    }

    public void setWork_date(String work_date) {
        this.work_date = work_date;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getWork_info() {
        return work_info;
    }

    public void setWork_info(String work_info) {
        this.work_info = work_info;
    }

    public String getMatch_time() {
        return match_time;
    }

    public void setMatch_time(String match_time) {
        this.match_time = match_time;
    }

    public double getOrder_price() {
        return order_price;
    }

    public void setOrder_price(double order_price) {
        this.order_price = order_price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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



    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
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

    public List<WorkTypeListBean> getWork_type_name_list() {
        return work_type_name_list;
    }

    public void setWork_type_name_list(List<WorkTypeListBean> work_type_name_list) {
        this.work_type_name_list = work_type_name_list;
    }

    public static class WorkTypeListBean {
        /**
         * work_type_id : 51
         * recruitment_id : 29
         * match_num : 1
         * other_work_type :
         * price : 200
         * num : 3
         * name : 水泥工
         * labor_recruitment_info_id : 16
         * add_time : 1539152936
         */

        private int work_type_id;
        private int recruitment_id;
        private int match_num;
        private String other_work_type;
        private double price;
        private int num;
        private String name;
        private int labor_recruitment_info_id;
        private int add_time;
        private double info_fee;

        public double getInfo_fee() {
            return info_fee;
        }

        public void setInfo_fee(double info_fee) {
            this.info_fee = info_fee;
        }

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
