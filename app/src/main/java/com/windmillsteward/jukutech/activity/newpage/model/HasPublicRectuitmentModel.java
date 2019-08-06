package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/10/17
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 是否发布过招聘
 */
public class HasPublicRectuitmentModel {

    /**
     * mobile_phone : 13727574858
     * nickname : 啊强
     * count : 0
     * is_posted : 0
     */

    private String mobile_phone;
    private String nickname;
    private int count;
    private int is_posted;

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getIs_posted() {
        return is_posted;
    }

    public void setIs_posted(int is_posted) {
        this.is_posted = is_posted;
    }
}
