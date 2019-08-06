package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2018/10/18
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 职位详情model
 */
public class HasLgTzgPublishedDetailsModel {

    /**
     * work_date : 2018-10-08
     * latitude : 113.4064504678
     * work_info : 工作描述: xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
     * labor_info_list : [{"work_third_area_name":"天河区","labor_recruitment_info_name":"水泥工","sex":2,"latitude":"113.4064504678","name":"啊超","contact_tel":"13727574859","is_pay":0,"work_second_area_name":"广州市","longitude":"23.1199587650","is_complaint":0}]
     * match_time : 1538992800
     * order_price : 0.01
     * title : 招聘水泥工3名,搬砖工4名,特级搬砖工4名,
     * work_third_area_name : 天河区
     * info_fee : 0
     * work_type_list : [{"work_type_id":51,"recruitment_id":29,"match_num":1,"other_work_type":"","price":200,"num":3,"name":"水泥工","labor_recruitment_info_id":16,"add_time":1539152936},{"work_type_id":52,"recruitment_id":29,"match_num":0,"other_work_type":"","price":250,"num":4,"name":"搬砖工","labor_recruitment_info_id":17,"add_time":1539152936},{"work_type_id":0,"recruitment_id":29,"match_num":0,"other_work_type":"特级搬砖工","price":300,"num":4,"name":null,"labor_recruitment_info_id":18,"add_time":1539152936}]
     * order_sn : 2018092906045640338666
     * work_second_area_name : 广州市
     * longitude : 23.1199587650
     * address : "科汇金谷"
     * num : 2 还差几人
     */

    private String work_date;
    private String latitude;
    private String work_info;
    private String match_time;
    private double order_price;
    private String title;
    private String work_third_area_name;
    private int info_fee;
    private String order_sn;
    private String work_second_area_name;
    private String longitude;
    private String address;
    private int pay_type;//支付方式 1.全额支付 2.只支付信息费用。 如果pay_type=1
    private List<LaborInfoListBean> labor_info_list;
    private List<WorkTypeListBean> work_type_list;
    private int num;
    private String consumer_hotline;
    private int salary_type;
    private String salary_fee;
    private String end_salary_fee;
    private int work_hour;//工作时间，匹配时间暂时不用
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

    public int getWork_hour() {
        return work_hour;
    }

    public void setWork_hour(int work_hour) {
        this.work_hour = work_hour;
    }

    public int getSalary_type() {
        return salary_type;
    }

    public void setSalary_type(int salary_type) {
        this.salary_type = salary_type;
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

    public String getConsumer_hotline() {
        return consumer_hotline;
    }

    public void setConsumer_hotline(String consumer_hotline) {
        this.consumer_hotline = consumer_hotline;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPay_type() {
        return pay_type;
    }

    public void setPay_type(int pay_type) {
        this.pay_type = pay_type;
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

    public String getWork_third_area_name() {
        return work_third_area_name;
    }

    public void setWork_third_area_name(String work_third_area_name) {
        this.work_third_area_name = work_third_area_name;
    }

    public int getInfo_fee() {
        return info_fee;
    }

    public void setInfo_fee(int info_fee) {
        this.info_fee = info_fee;
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

    public List<LaborInfoListBean> getLabor_info_list() {
        return labor_info_list;
    }

    public void setLabor_info_list(List<LaborInfoListBean> labor_info_list) {
        this.labor_info_list = labor_info_list;
    }

    public List<WorkTypeListBean> getWork_type_list() {
        return work_type_list;
    }

    public void setWork_type_list(List<WorkTypeListBean> work_type_list) {
        this.work_type_list = work_type_list;
    }

    public static class LaborInfoListBean {
        /**
         * work_third_area_name : 天河区
         * labor_recruitment_info_name : 水泥工
         * sex : 2
         * latitude : 113.4064504678
         * name : 啊超
         * contact_tel : 13727574859
         * is_pay : 0
         * work_second_area_name : 广州市
         * longitude : 23.1199587650
         * is_complaint : 0
         */

        private String work_third_area_name;
        private String labor_recruitment_info_name;
        private int sex;
        private String latitude;
        private String name;
        private String contact_tel;
        private int is_pay;
        private String work_second_area_name;
        private String longitude;
        private int is_complaint;
        private int evaluation_id;
        private int relate_id;
        private int info_id;

        public int getInfo_id() {
            return info_id;
        }

        public void setInfo_id(int info_id) {
            this.info_id = info_id;
        }

        public int getRelate_id() {
            return relate_id;
        }

        public void setRelate_id(int relate_id) {
            this.relate_id = relate_id;
        }

        public int getEvaluation_id() {
            return evaluation_id;
        }

        public void setEvaluation_id(int evaluation_id) {
            this.evaluation_id = evaluation_id;
        }

        public String getWork_third_area_name() {
            return work_third_area_name;
        }

        public void setWork_third_area_name(String work_third_area_name) {
            this.work_third_area_name = work_third_area_name;
        }

        public String getLabor_recruitment_info_name() {
            return labor_recruitment_info_name;
        }

        public void setLabor_recruitment_info_name(String labor_recruitment_info_name) {
            this.labor_recruitment_info_name = labor_recruitment_info_name;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContact_tel() {
            return contact_tel;
        }

        public void setContact_tel(String contact_tel) {
            this.contact_tel = contact_tel;
        }

        public int getIs_pay() {
            return is_pay;
        }

        public void setIs_pay(int is_pay) {
            this.is_pay = is_pay;
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

        public int getIs_complaint() {
            return is_complaint;
        }

        public void setIs_complaint(int is_complaint) {
            this.is_complaint = is_complaint;
        }
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
