package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：资金托管详情实体
 * author:cyq
 * 2018-03-08
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class FundsTrusteeshipDetailBean extends BaseData {


    /**
     * processing_time : 0
     * trusteeship_money : 100
     * delete_time : 0
     * trusteeship_id : 16357100
     * contact_person : qzq
     * status_name : 审核中
     * mobile_no : 13727574858
     * end_valid_time : 2018-03-02
     * start_valid_time : 2018-03-01
     * take_effect_time : 0
     * trusteeship_module : 4
     * trusteeship_voucher :
     * user_id : 666
     * to_user_id : 0
     * admin_id : 0
     * trusteeship_title : 世纪花园竹园顶层复式带大楼台精装未住14
     * id : 1
     * describe : 托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管托管
     * trusteeship_status : 1
     * report_user_id : 0
     * trusteeship_module_name : 房屋租售
     * add_time : 0
     */

    private int processing_time;
    private double trusteeship_money;
    private int delete_time;
    private String trusteeship_id;
    private String contact_person;
    private String status_name;
    private String mobile_no;
    private String end_valid_time;
    private String start_valid_time;
    private int take_effect_time;
    private int trusteeship_module;
    private List<String> trusteeship_voucher;
    private int user_id;
    private int to_user_id;
    private int admin_id;
    private String trusteeship_title;
    private int id;
    private String describe;
    private int trusteeship_status;
    private int report_user_id;
    private String trusteeship_module_name;
    private long add_time;
    private long report_time;//举报时间
    private List<ListBean> consultation_record;
    private String report_sn;//举报编号
    private String report_user_name;//举报人，发起人
    private String consultation_reason;//举证描述
    private String consumer_hotline;//客服电话(只有已完成和已取消的托管信息才有)
    private List<String> report_voucher;//举证图片

    public String getConsumer_hotline() {
        return consumer_hotline;
    }

    public void setConsumer_hotline(String consumer_hotline) {
        this.consumer_hotline = consumer_hotline;
    }

    public List<String> getReport_voucher() {
        return report_voucher;
    }

    public void setReport_voucher(List<String> report_voucher) {
        this.report_voucher = report_voucher;
    }

    public String getConsultation_reason() {
        return consultation_reason;
    }

    public void setConsultation_reason(String consultation_reason) {
        this.consultation_reason = consultation_reason;
    }

    public String getReport_user_name() {
        return report_user_name;
    }

    public void setReport_user_name(String report_user_name) {
        this.report_user_name = report_user_name;
    }

    public long getReport_time() {
        return report_time;
    }

    public void setReport_time(long report_time) {
        this.report_time = report_time;
    }

    public String getReport_sn() {
        return report_sn;
    }

    public void setReport_sn(String report_sn) {
        this.report_sn = report_sn;
    }

    public int getProcessing_time() {
        return processing_time;
    }

    public void setProcessing_time(int processing_time) {
        this.processing_time = processing_time;
    }

    public double getTrusteeship_money() {
        return trusteeship_money;
    }

    public void setTrusteeship_money(double trusteeship_money) {
        this.trusteeship_money = trusteeship_money;
    }

    public int getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(int delete_time) {
        this.delete_time = delete_time;
    }

    public String getTrusteeship_id() {
        return trusteeship_id;
    }

    public void setTrusteeship_id(String trusteeship_id) {
        this.trusteeship_id = trusteeship_id;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEnd_valid_time() {
        return end_valid_time;
    }

    public void setEnd_valid_time(String end_valid_time) {
        this.end_valid_time = end_valid_time;
    }

    public String getStart_valid_time() {
        return start_valid_time;
    }

    public void setStart_valid_time(String start_valid_time) {
        this.start_valid_time = start_valid_time;
    }

    public int getTake_effect_time() {
        return take_effect_time;
    }

    public void setTake_effect_time(int take_effect_time) {
        this.take_effect_time = take_effect_time;
    }

    public int getTrusteeship_module() {
        return trusteeship_module;
    }

    public void setTrusteeship_module(int trusteeship_module) {
        this.trusteeship_module = trusteeship_module;
    }

    public List<String> getTrusteeship_voucher() {
        return trusteeship_voucher;
    }

    public void setTrusteeship_voucher(List<String> trusteeship_voucher) {
        this.trusteeship_voucher = trusteeship_voucher;
    }

    public List<ListBean> getConsultation_record() {
        return consultation_record;
    }

    public void setConsultation_record(List<ListBean> consultation_record) {
        this.consultation_record = consultation_record;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(int to_user_id) {
        this.to_user_id = to_user_id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getTrusteeship_title() {
        return trusteeship_title;
    }

    public void setTrusteeship_title(String trusteeship_title) {
        this.trusteeship_title = trusteeship_title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getTrusteeship_status() {
        return trusteeship_status;
    }

    public void setTrusteeship_status(int trusteeship_status) {
        this.trusteeship_status = trusteeship_status;
    }

    public int getReport_user_id() {
        return report_user_id;
    }

    public void setReport_user_id(int report_user_id) {
        this.report_user_id = report_user_id;
    }

    public String getTrusteeship_module_name() {
        return trusteeship_module_name;
    }

    public void setTrusteeship_module_name(String trusteeship_module_name) {
        this.trusteeship_module_name = trusteeship_module_name;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public class ListBean{
        private int consultation_id;//协商记录id
        private int user_id;//发表用户id
        private String report_voucher;//举证图片(json格式)
        private String user_name;//举证人(如:小铭-举报人)
        private String consultation_reason;//举证描述
        private String type;//1双方撕逼 2客户留言
        private String add_time;//举证时间
        private String user_avatar_url;//用户头像

        public int getConsultation_id() {
            return consultation_id;
        }

        public void setConsultation_id(int consultation_id) {
            this.consultation_id = consultation_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getReport_voucher() {
            return report_voucher;
        }

        public void setReport_voucher(String report_voucher) {
            this.report_voucher = report_voucher;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getConsultation_reason() {
            return consultation_reason;
        }

        public void setConsultation_reason(String consultation_reason) {
            this.consultation_reason = consultation_reason;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }
    }
}
