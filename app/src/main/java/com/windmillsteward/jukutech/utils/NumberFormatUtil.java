package com.windmillsteward.jukutech.utils;

import java.math.BigDecimal;

/**
 * File: NumberFormatUtil.java
 * 作者: 大海
 * 创建日期: 2018/5/14 0014 19:27
 * 描述：
 */
public class NumberFormatUtil {

    public static String save2Point(double v) {
        BigDecimal bg = new BigDecimal(v);
        return bg.setScale(2, BigDecimal.ROUND_DOWN).toString();
    }
}
