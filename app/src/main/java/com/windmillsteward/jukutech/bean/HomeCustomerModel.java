package com.windmillsteward.jukutech.bean;

/**
 * @date: on 2018/10/4
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 便民服务的列表上数据
 */
public class HomeCustomerModel {

    /**
     * distance : 0.77
     * service_name : 维修专线
     * class_id : 2
     * latitude : 113.4192521879
     * contact_tel : 13737374545
     * pic_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png
     * longitude : 23.1781881906
     * service_address : 科汇金谷
     * desc_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png
     */

    private double distance;
    private String service_name;
    private int class_id;
    private String latitude;
    private String contact_tel;
    private String pic_url;
    private String longitude;
    private String service_address;
    private String desc_url ;

    public String getDesc_url() {
        return desc_url;
    }

    public void setDesc_url(String desc_url) {
        this.desc_url = desc_url;
    }

    public String getService_address() {
        return service_address;
    }

    public void setService_address(String service_address) {
        this.service_address = service_address;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
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

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
