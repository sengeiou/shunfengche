package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：个人信息
 * author:cyq
 * 2018-02-10
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class UserInfo extends BaseData {

    private int user_id;//用户id
    private String user_avatar_url;//用户头像
    private String nickname;//用户昵称
    private int sex;//性别【0：保密，1：男，2：女】
    private int user_authen_status; //个人认证状态【 0.未上传资料,1已上传待审核，2审核通过，3审核不通过】
    private int company_authen_status;//企业认证状态【 0.未上传资料,1已上传待审核，2审核通过，3审核不通过】

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_avatar_url() {
        return user_avatar_url;
    }

    public void setUser_avatar_url(String user_avatar_url) {
        this.user_avatar_url = user_avatar_url;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getUser_authen_status() {
        return user_authen_status;
    }

    public void setUser_authen_status(int user_authen_status) {
        this.user_authen_status = user_authen_status;
    }

    public int getCompany_authen_status() {
        return company_authen_status;
    }

    public void setCompany_authen_status(int company_authen_status) {
        this.company_authen_status = company_authen_status;
    }
}
