package com.windmillsteward.jukutech.utils;

/**
 * @date: on 2018/10/1
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class DistanceUtils {
    public static String getFormatDistance(int distance) {
        if (distance < 1000) {
            return distance + "m";
        } else {
            return MathUtils.div(String.valueOf(distance), String.valueOf(1000), 2) + "km";
        }
    }

    public static String getFormatDistance(double distance) {
        if (distance < 1000) {
            return distance + "m";
        } else {
            return MathUtils.div(String.valueOf(distance), String.valueOf(1000), 2) + "km";
        }
    }
}
