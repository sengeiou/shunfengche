package com.windmillsteward.jukutech.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @date: on 2018/10/4
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 便民服务分类model
 */
public class HomeCustomerClassicModel {

    /**
     * service_name : 政府热线
     * count(distinct class_id) : 1
     * class_id : 1
     * pic_url : http://sfcgj.oss-cn-qingdao.aliyuncs.com/8946491538122324327icon.png
     */

    private String service_name;
    @SerializedName("count(distinct class_id)")
    private int _$CountDistinctClass_id60; // FIXME check this code
    private int class_id;
    private String pic_url;

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public int get_$CountDistinctClass_id60() {
        return _$CountDistinctClass_id60;
    }

    public void set_$CountDistinctClass_id60(int _$CountDistinctClass_id60) {
        this._$CountDistinctClass_id60 = _$CountDistinctClass_id60;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}
