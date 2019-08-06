package com.windmillsteward.jukutech.activity.newpage.model;

/**
 * @date: on 2018/11/26
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class TuiguangModel {

    /**
     * level_rule : 等级规则
     * activity_rule : 活动规则
     * breeding_num : 10
     * share_url : https://baidu.com
     * qr_url : 二维码图片链接
     * recommended_num : 200
     * user_level : 1
     */

    private String level_rule;
    private String activity_rule;
    private int breeding_num;
    private String share_url;
    private String qr_url;
    private String recommended_title;
    private String recommended_description;
    private int recommended_num;
    private int user_level;
    private int total_recommended_num;
    private int total_breeding_num;
    private int recommended_proportion;
    private int breeding_proportion;

    public String getRecommended_title() {
        return recommended_title;
    }

    public void setRecommended_title(String recommended_title) {
        this.recommended_title = recommended_title;
    }

    public String getRecommended_description() {
        return recommended_description;
    }

    public void setRecommended_description(String recommended_description) {
        this.recommended_description = recommended_description;
    }

    public String getQr_url() {
        return qr_url;
    }

    public void setQr_url(String qr_url) {
        this.qr_url = qr_url;
    }

    public int getTotal_recommended_num() {
        return total_recommended_num;
    }

    public void setTotal_recommended_num(int total_recommended_num) {
        this.total_recommended_num = total_recommended_num;
    }

    public int getTotal_breeding_num() {
        return total_breeding_num;
    }

    public void setTotal_breeding_num(int total_breeding_num) {
        this.total_breeding_num = total_breeding_num;
    }

    public int getRecommended_proportion() {
        return recommended_proportion;
    }

    public void setRecommended_proportion(int recommended_proportion) {
        this.recommended_proportion = recommended_proportion;
    }

    public int getBreeding_proportion() {
        return breeding_proportion;
    }

    public void setBreeding_proportion(int breeding_proportion) {
        this.breeding_proportion = breeding_proportion;
    }

    public String getLevel_rule() {
        return level_rule;
    }

    public void setLevel_rule(String level_rule) {
        this.level_rule = level_rule;
    }

    public String getActivity_rule() {
        return activity_rule;
    }

    public void setActivity_rule(String activity_rule) {
        this.activity_rule = activity_rule;
    }

    public int getBreeding_num() {
        return breeding_num;
    }

    public void setBreeding_num(int breeding_num) {
        this.breeding_num = breeding_num;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public int getRecommended_num() {
        return recommended_num;
    }

    public void setRecommended_num(int recommended_num) {
        this.recommended_num = recommended_num;
    }

    public int getUser_level() {
        return user_level;
    }

    public void setUser_level(int user_level) {
        this.user_level = user_level;
    }
}
