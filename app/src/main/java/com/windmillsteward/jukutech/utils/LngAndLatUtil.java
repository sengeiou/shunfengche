package com.windmillsteward.jukutech.utils;

import java.text.DecimalFormat;

/**
 * 描述：
 * 时间：2018/3/28/028
 * 作者：xjh
 */
public class LngAndLatUtil {
    private static final double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     */
    public static String getDistance(double lat1, double lng1, double lat2,
                                     double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
//        s = s*1000;
        DecimalFormat df   = new DecimalFormat("######0.00");

        return df.format(s);
    }
}
