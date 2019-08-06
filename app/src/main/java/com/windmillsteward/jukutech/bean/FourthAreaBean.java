package com.windmillsteward.jukutech.bean;

import android.support.annotation.NonNull;

import com.windmillsteward.jukutech.utils.PinyinUtils;

/**
 * 描述：
 * 时间：2018/2/2
 * 作者：xjh
 */

public class FourthAreaBean implements Comparable<FourthAreaBean> {
    /**
     * fourth_area_id : 28980
     * fourth_area_name : 夏港街道
     */

    private int fourth_area_id;
    private String fourth_area_name;
    private String pinyin;

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public int getFourth_area_id() {
        return fourth_area_id;
    }

    public void setFourth_area_id(int fourth_area_id) {
        this.fourth_area_id = fourth_area_id;
    }

    public String getFourth_area_name() {
        return fourth_area_name;
    }

    public void setFourth_area_name(String fourth_area_name) {
        this.fourth_area_name = fourth_area_name;
        this.pinyin = PinyinUtils.getPinyin(fourth_area_name);
    }

    @Override
    public int compareTo(@NonNull FourthAreaBean o) {
        return pinyin.compareTo(o.pinyin);
    }
}
