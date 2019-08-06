package com.windmillsteward.jukutech.utils;

import android.util.Pair;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 描述：
 * 时间：2018/2/17/017
 * 作者：xjh
 */
public class DateTimeFormatUtil {

    private static int year = Calendar.getInstance().get(Calendar.YEAR);

    public static String dateTimeFormatString(long time) {
        String patten;
        // 将时间转换成毫秒
        time = formatMillis(time);
        Date date = new Date(time);
        if (!checkYear(time)) {
            patten = "yyyy-MM-dd HH:mm";
        } else if (checkToday(time)) {
            long diff = (System.currentTimeMillis() - time) / 1000;
            if (diff < 60) {
                return "刚刚";
            }
            if (diff < 3600) {
                return (diff / 60) + "分钟前";
            }
            return (diff / 3600) + "小时前";
        } else if (checkYesterday(time)) {
            patten = "昨天 HH:mm";
        } else if (checkBeforeYesterday(time)) {
            patten = "前天 HH:mm";
        } else {
            patten = "MM-dd HH:mm";
        }
        return new SimpleDateFormat(patten, Locale.getDefault()).format(date);
    }

    /**
     * 将时间转换成毫秒
     */
    private static long formatMillis(long time) {
        if (String.valueOf(time).length() == 10) {
            return time * 1000;
        }
        return time;
    }

    /**
     * 判断是否是当前这一年
     */
    private static boolean checkYear(long timeInSeconds) {
        long millis = formatMillis(timeInSeconds);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        if (year == calendar.get(Calendar.YEAR)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是当前这天
     */
    private static boolean checkToday(long millisecond) {
        Pair<Long, Long> today = diffDays(0);
        return millisecond >= ((Long) today.first).longValue() && millisecond < ((Long) today.second).longValue();
    }

    /**
     * 根据传入的值获取多少天之前的毫秒的起始毫秒和结束毫秒值
     * @param amount 数量，天数
     * @return 返回对应的起始值
     */
    private static Pair<Long, Long> diffDays(int amount) {
        Calendar startCalendar = Calendar.getInstance();
        if (amount != 0) {
            startCalendar.add(Calendar.DATE, amount);
        }
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
        startCalendar.set(Calendar.MILLISECOND, 0);
        long starTime = startCalendar.getTime().getTime();
        Calendar endCalendar = Calendar.getInstance();
        if (amount != 0) {
            endCalendar.add(Calendar.DATE, amount);
        }
        endCalendar.set(Calendar.HOUR_OF_DAY, 23);
        endCalendar.set(Calendar.MINUTE, 59);
        endCalendar.set(Calendar.SECOND, 59);
        endCalendar.set(Calendar.MILLISECOND, 999);
        return new Pair(Long.valueOf(starTime), Long.valueOf(endCalendar.getTime().getTime()));
    }

    /**
     * 判断是否是昨天
     */
    private static boolean checkYesterday(long millisecond) {
        Pair<Long, Long> yesterday = diffDays(-1);
        return millisecond >= ((Long) yesterday.first).longValue() && millisecond < ((Long) yesterday.second).longValue();
    }

    /**
     * 判断是否是前天
     */
    private static boolean checkBeforeYesterday(long millisecond) {
        Pair<Long, Long> beforeYesterday = diffDays(-2);
        return millisecond >= ((Long) beforeYesterday.first).longValue() && millisecond < ((Long) beforeYesterday.second).longValue();
    }

}
