package com.windmillsteward.jukutech.utils;

/**
 * File: StaticData.java
 * 作者: 大海
 * 创建日期: 2018/5/28 0028 16:44
 * 描述：
 */
public class StaticData {

    public static String[] getOrientation_text() {
        return new String[]{"东", "南", "西", "北","南北","东西","东南","西南","东北","西北"};
    }
    public static String[] getDecoration_text() {
        return new String[] {"毛培","简单装修","中等装修","精装修","豪华装修"};
    }

    public static String[] getRent_deposit_text() {
        return new String[]{"押一付一","押一付二","押一付三","押二付一","押二付二","押二付三","半年付","年付","面议"};
    }

    public static String[] getSchool_degree_text() {
        return new String[]{"无学位","带学位"};
    }

    public static String[] getProperty_right() {
        return new String[]{"70年","50年","40年","无"};
    }

    public static int[] getProperty_right_id() {
        return new int[]{70,50,40,0};
    }

}
