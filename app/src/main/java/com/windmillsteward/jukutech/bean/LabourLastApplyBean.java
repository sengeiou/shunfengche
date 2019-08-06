package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：查询最后一次的申请职位信息

 * author:cyq
 * 2018-07-31
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class LabourLastApplyBean extends BaseData {


    /**
     * true_name : 啊强
     * mobile_phone : 13727574858
     * self_intro : 自我介绍
     * sex : 1
     * job_id : 2
     */

    private String true_name;
    private String mobile_phone;
    private String self_intro;
    private int sex;
    private int job_id;

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

    public String getSelf_intro() {
        return self_intro;
    }

    public void setSelf_intro(String self_intro) {
        this.self_intro = self_intro;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }
}
