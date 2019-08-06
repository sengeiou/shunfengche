package com.windmillsteward.jukutech.bean;

import android.support.annotation.NonNull;

import com.windmillsteward.jukutech.utils.PinyinUtils;

/**
 * 描述：
 * 时间：2018/2/2
 * 作者：xjh
 */

public class SecondAreaBean implements Comparable<SecondAreaBean> {
    private int area_id;
    private String area_name;
    private String pinyin;

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
        this.pinyin = PinyinUtils.getPinyin(area_name);
    }

    public String getPinyin() {
        return pinyin;
    }

    @Override
    public int compareTo(@NonNull SecondAreaBean o) {
        return pinyin.compareTo(o.pinyin);
    }
}
