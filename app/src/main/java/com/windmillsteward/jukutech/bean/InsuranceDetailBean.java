package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8 0008.
 */

public class InsuranceDetailBean extends BaseData {


    /**
     * delete_time : 0
     * insurance_introduce : 这个一个很好的保险,没事你就买,使劲买
     * second_area_id : 289
     * is_hosting : 0
     * title : 卖保险咯
     * is_collect : 0
     * update_time : 1520392697
     * third_area_name : 天河区
     * nickname : 啊强
     * publish_status : 1
     * insurance_id : 44
     * view_num : 0
     * contact_person : 啊强
     * second_area_name : 广州市
     * pic_urls : null
     * third_area_id : 3040
     * insurance_information :
     * user_avatar_url : https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2671632699,2186072093&fm=27&gp=0.jpg
     * insurance_type : 173
     * hosting_show : 2018030711181779271365
     * contact_mobile_phone : 13727574858
     * user_id : 666
     * company_name : 平安保险
     * audit_reason :
     * add_time : 1520392697
     * insurance_type_name : 健康保险
     */

    private int delete_time;
    private String insurance_introduce;
    private int second_area_id;
    private int is_hosting;
    private String title;
    private int is_collect;
    private int update_time;
    private String third_area_name;
    private String nickname;
    private int publish_status;
    private int insurance_id;
    private int view_num;
    private String contact_person;
    private String second_area_name;
    private List<String> pic_urls;
    private int third_area_id;
    private String insurance_information;
    private String user_avatar_url;
    private int insurance_type;
    private String hosting_show;
    private String contact_mobile_phone;
    private int user_id;
    private String company_name;
    private String audit_reason;
    private int add_time;
    private String insurance_type_name;
    private String health_introduce;
    private String status; // 0：未购买，1：售中，2：售后
    private String health_for_sale; // 服务售中详情 status=1或者status=2时返回显示
    private String health_after_sale; // 服务售后详情 status=2时返回显示
    private String price; //

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHealth_for_sale() {
        return health_for_sale;
    }

    public void setHealth_for_sale(String health_for_sale) {
        this.health_for_sale = health_for_sale;
    }

    public String getHealth_after_sale() {
        return health_after_sale;
    }

    public void setHealth_after_sale(String health_after_sale) {
        this.health_after_sale = health_after_sale;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHealth_introduce() {
        return health_introduce;
    }

    public void setHealth_introduce(String health_introduce) {
        this.health_introduce = health_introduce;
    }

    public int getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(int delete_time) {
        this.delete_time = delete_time;
    }

    public String getInsurance_introduce() {
        return insurance_introduce;
    }

    public void setInsurance_introduce(String insurance_introduce) {
        this.insurance_introduce = insurance_introduce;
    }

    public int getSecond_area_id() {
        return second_area_id;
    }

    public void setSecond_area_id(int second_area_id) {
        this.second_area_id = second_area_id;
    }

    public int getIs_hosting() {
        return is_hosting;
    }

    public void setIs_hosting(int is_hosting) {
        this.is_hosting = is_hosting;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
    }

    public int getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(int update_time) {
        this.update_time = update_time;
    }

    public String getThird_area_name() {
        return third_area_name;
    }

    public void setThird_area_name(String third_area_name) {
        this.third_area_name = third_area_name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPublish_status() {
        return publish_status;
    }

    public void setPublish_status(int publish_status) {
        this.publish_status = publish_status;
    }

    public int getInsurance_id() {
        return insurance_id;
    }

    public void setInsurance_id(int insurance_id) {
        this.insurance_id = insurance_id;
    }

    public int getView_num() {
        return view_num;
    }

    public void setView_num(int view_num) {
        this.view_num = view_num;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getSecond_area_name() {
        return second_area_name;
    }

    public void setSecond_area_name(String second_area_name) {
        this.second_area_name = second_area_name;
    }

    public List<String> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(List<String> pic_urls) {
        this.pic_urls = pic_urls;
    }

    public int getThird_area_id() {
        return third_area_id;
    }

    public void setThird_area_id(int third_area_id) {
        this.third_area_id = third_area_id;
    }

    public String getInsurance_information() {
        return insurance_information;
    }

    public void setInsurance_information(String insurance_information) {
        this.insurance_information = insurance_information;
    }

    public String getUser_avatar_url() {
        return user_avatar_url;
    }

    public void setUser_avatar_url(String user_avatar_url) {
        this.user_avatar_url = user_avatar_url;
    }

    public int getInsurance_type() {
        return insurance_type;
    }

    public void setInsurance_type(int insurance_type) {
        this.insurance_type = insurance_type;
    }

    public String getHosting_show() {
        return hosting_show;
    }

    public void setHosting_show(String hosting_show) {
        this.hosting_show = hosting_show;
    }

    public String getContact_mobile_phone() {
        return contact_mobile_phone;
    }

    public void setContact_mobile_phone(String contact_mobile_phone) {
        this.contact_mobile_phone = contact_mobile_phone;
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

    public String getAudit_reason() {
        return audit_reason;
    }

    public void setAudit_reason(String audit_reason) {
        this.audit_reason = audit_reason;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public String getInsurance_type_name() {
        return insurance_type_name;
    }

    public void setInsurance_type_name(String insurance_type_name) {
        this.insurance_type_name = insurance_type_name;
    }
}
