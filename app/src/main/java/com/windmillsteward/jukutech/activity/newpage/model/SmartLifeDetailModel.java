package com.windmillsteward.jukutech.activity.newpage.model;

import java.util.List;

/**
 * @date: on 2018/11/5
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class SmartLifeDetailModel {

    /**
     * area_name : 广州市
     * require_id : 3
     * uRecord : {"area_name":"广州天河","sex_name":"男","true_name":"许铭源","mobile_phone":"13790097063","complaint_call":"888-8888","sex":1,"latitude":"23.23423411","user_avatar_url":"http://sfcgj.oss-cn-qingdao.aliyuncs.com/0105051527761755997%E5%A3%81%E7%BA%B81.jpg","longitude":"112.3234344"}
     * address : 广东软件园B栋202
     * status_name : 已接单
     * fee : 共￥0.00(含服务费0.00)
     * my_role : 1
     * description : 广东软件园聚酷科技代写代码
     * title : 广东软件园聚酷科技代写代码123
     * order_sn : 2018092903455974867244
     * status : 2
     */

    private String area_name;
    private String user_name;
    private String service_name;
    private String boss_name;
    private String boss_mobile_phone;
    private String mobile_phone;
    private long booking_time;
    private String remark;
    private int people_num;
    private int index_type;
    private int require_id;
    private URecordBean uRecord;
    private String address;
    private String status_name;
    private String fee;
    private int my_role;
    private String description;
    private String title;
    private String order_sn;
    private long add_time;
    private int status;
    private List<String> pic_urls;
    private int is_complaint;
    private int is_push_msg;
    private String longitude;
    private String latitude;
    private String t_longitude;
    private String t_latitude;
    private String t_address;
    private int evaluation_id;

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public String getBoss_name() {
        return boss_name;
    }

    public void setBoss_name(String boss_name) {
        this.boss_name = boss_name;
    }

    public String getBoss_mobile_phone() {
        return boss_mobile_phone;
    }

    public void setBoss_mobile_phone(String boss_mobile_phone) {
        this.boss_mobile_phone = boss_mobile_phone;
    }

    public long getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(long booking_time) {
        this.booking_time = booking_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getPeople_num() {
        return people_num;
    }

    public void setPeople_num(int people_num) {
        this.people_num = people_num;
    }

    public int getIndex_type() {
        return index_type;
    }

    public void setIndex_type(int index_type) {
        this.index_type = index_type;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public URecordBean getuRecord() {
        return uRecord;
    }

    public void setuRecord(URecordBean uRecord) {
        this.uRecord = uRecord;
    }

    public String getT_longitude() {
        return t_longitude;
    }

    public void setT_longitude(String t_longitude) {
        this.t_longitude = t_longitude;
    }

    public String getT_latitude() {
        return t_latitude;
    }

    public void setT_latitude(String t_latitude) {
        this.t_latitude = t_latitude;
    }

    public String getT_address() {
        return t_address;
    }

    public void setT_address(String t_address) {
        this.t_address = t_address;
    }

    public int getEvaluation_id() {
        return evaluation_id;
    }

    public void setEvaluation_id(int evaluation_id) {
        this.evaluation_id = evaluation_id;
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

    public int getIs_complaint() {
        return is_complaint;
    }

    public void setIs_complaint(int is_complaint) {
        this.is_complaint = is_complaint;
    }

    public int getIs_push_msg() {
        return is_push_msg;
    }

    public void setIs_push_msg(int is_push_msg) {
        this.is_push_msg = is_push_msg;
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

    public URecordBean getURecord() {
        return uRecord;
    }

    public void setURecord(URecordBean uRecord) {
        this.uRecord = uRecord;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public int getMy_role() {
        return my_role;
    }

    public void setMy_role(int my_role) {
        this.my_role = my_role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public List<String> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(List<String> pic_urls) {
        this.pic_urls = pic_urls;
    }

    public static class URecordBean {
        /**
         * area_name : 广州天河
         * sex_name : 男
         * true_name : 许铭源
         * mobile_phone : 13790097063
         * complaint_call : 888-8888
         * sex : 1
         * latitude : 23.23423411
         * user_avatar_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/0105051527761755997%E5%A3%81%E7%BA%B81.jpg
         * longitude : 112.3234344
         */

        private String area_name;
        private String sex_name;
        private String true_name;
        private String mobile_phone;
        private String complaint_call;
        private int sex;
        private String latitude;
        private String user_avatar_url;
        private String longitude;

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getSex_name() {
            return sex_name;
        }

        public void setSex_name(String sex_name) {
            this.sex_name = sex_name;
        }

        public String getTrue_name() {
            return true_name;
        }

        public void setTrue_name(String true_name) {
            this.true_name = true_name;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }

        public String getComplaint_call() {
            return complaint_call;
        }

        public void setComplaint_call(String complaint_call) {
            this.complaint_call = complaint_call;
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

        public String getUser_avatar_url() {
            return user_avatar_url;
        }

        public void setUser_avatar_url(String user_avatar_url) {
            this.user_avatar_url = user_avatar_url;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }
}
