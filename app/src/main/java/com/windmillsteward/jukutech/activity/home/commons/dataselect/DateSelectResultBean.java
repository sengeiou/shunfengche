package com.windmillsteward.jukutech.activity.home.commons.dataselect;

import com.windmillsteward.jukutech.base.BaseData;

/**
 * 描述：
 * 时间：2018/3/6/006
 * 作者：xjh
 */
public class DateSelectResultBean extends BaseData {

    private int day;
    private int month;
    private int year;

    public DateSelectResultBean(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
