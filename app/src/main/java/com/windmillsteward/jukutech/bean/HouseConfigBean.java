package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/3/8/008
 * 作者：xjh
 */
public class HouseConfigBean extends BaseData {

    /**
     * img : http://jk-shunfengche.oss-cn-qingdao.aliyuncs.com/6515171521542021546icon_bed.jpg
     * name : 床
     * facility_id : 33
     */

    private String img;
    private String name;
    private int facility_id;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(int facility_id) {
        this.facility_id = facility_id;
    }
}
