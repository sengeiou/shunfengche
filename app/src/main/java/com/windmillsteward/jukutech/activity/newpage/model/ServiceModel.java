package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/20
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 服务类型model
 */
public class ServiceModel {
    /**
     * service_content_name : 打扫卫生
     * service_content_id : 42
     */

    private String service_content_name;
    private int service_content_id;
    private boolean select;



    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public String getService_content_name() {
        return service_content_name;
    }

    public void setService_content_name(String service_content_name) {
        this.service_content_name = service_content_name;
    }

    public int getService_content_id() {
        return service_content_id;
    }

    public void setService_content_id(int service_content_id) {
        this.service_content_id = service_content_id;
    }
}
