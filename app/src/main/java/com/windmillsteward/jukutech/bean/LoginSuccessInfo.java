package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 登录成功实体
 */

public class LoginSuccessInfo extends BaseData {


    /**
     * access_token : d98efd9fa2314eb4b6dd317935dae17b
     * user_id : 2
     */

    private String access_token;
    private int user_id;
    private int hw_pay_type;//钟点工-支付方式 1.全额托管支付 2.信息费支付
    private int sw_pay_type ;//劳务工-支付方式 1.全额托管支付 2.信息费支付
    private int spw_pay_type;//特种工-支付方式 1.全额托管支付 2.信息费支付

    private int tutor_pay_type;//发布家教-支付方式 1.全额托管支付 2.信息费支付
    private int bm_pay_type;//发布保姆-支付方式 1.全额托管支付 2.信息费支付
    private int ys_pay_type;//发布月嫂-支付方式 1.全额托管支付 2.信息费支付
    private int yes_pay_type;//发布育儿嫂-支付方式 1.全额托管支付 2.信息费支付
    private int smart_pay_type;//发布智慧生活-支付方式 1.全额托管支付 2.信息费支付

    private String hx_user;//环信账号
    private String user_avatar_url;//环信账号
    private String nickname;//环信账号
    private int sex ;//性别【0：保密，1：男，2：女】

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

    public String getHx_user() {
        return hx_user;
    }

    public void setHx_user(String hx_user) {
        this.hx_user = hx_user;
    }

    public int getTutor_pay_type() {
        return tutor_pay_type;
    }

    public void setTutor_pay_type(int tutor_pay_type) {
        this.tutor_pay_type = tutor_pay_type;
    }

    public int getBm_pay_type() {
        return bm_pay_type;
    }

    public void setBm_pay_type(int bm_pay_type) {
        this.bm_pay_type = bm_pay_type;
    }

    public int getYs_pay_type() {
        return ys_pay_type;
    }

    public void setYs_pay_type(int ys_pay_type) {
        this.ys_pay_type = ys_pay_type;
    }

    public int getYes_pay_type() {
        return yes_pay_type;
    }

    public void setYes_pay_type(int yes_pay_type) {
        this.yes_pay_type = yes_pay_type;
    }

    public int getSmart_pay_type() {
        return smart_pay_type;
    }

    public void setSmart_pay_type(int smart_pay_type) {
        this.smart_pay_type = smart_pay_type;
    }

    public int getHw_pay_type() {
        return hw_pay_type;
    }

    public void setHw_pay_type(int hw_pay_type) {
        this.hw_pay_type = hw_pay_type;
    }

    public int getSw_pay_type() {
        return sw_pay_type;
    }

    public void setSw_pay_type(int sw_pay_type) {
        this.sw_pay_type = sw_pay_type;
    }

    public int getSpw_pay_type() {
        return spw_pay_type;
    }

    public void setSpw_pay_type(int spw_pay_type) {
        this.spw_pay_type = spw_pay_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
