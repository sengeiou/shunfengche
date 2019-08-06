package com.windmillsteward.jukutech.utils;

import android.text.TextUtils;
import android.text.format.DateFormat;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期时间工具类
 */
public class DateUtil {
    /**
     * 获取当前时间
     *
     * @param format 时间格式 "yyyy-MM-dd HH:mm"时间格式
     * @return
     */
    public static String getCurrentDate(String format) {
        Date d = new Date();// 获取时间
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(d);
    }

    /**
     * 将时间翟转成时间字符串
     *
     * @param stampTime  时间翟
     * @param dateFormat "yyyy-MM-dd HH:mm"时间格式
     * @return 日期
     */
    public static String StampTimeToDate(String stampTime, String dateFormat) {
        if (TextUtils.isEmpty(stampTime)) {
            return "";
        } else {
            SimpleDateFormat df = new SimpleDateFormat(dateFormat);
            Date date = new Date(formatMillis(Long.parseLong(stampTime)));
            return df.format(date);
        }
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
     * 将时间字符串转成是间翟
     *
     * @param StampTime  时间翟
     * @param dateFormat 时间格式
     * @return 时间翟
     */
    public static String DateToStampTime(String StampTime, String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        try {
            long stampNum = df.parse(StampTime).getTime();
            return String.valueOf(stampNum).substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param strDate1 时间1
     * @param strDate2 时间2
     * @param format   输入的时间格式要求
     * @return 返回值为ture, 表示时间1小于时间2，false则相反
     */
    public static boolean compareDate(String strDate1, String strDate2,
                                      String format) {
        SimpleDateFormat adf = new SimpleDateFormat(format);
        try {
            Date date1 = adf.parse(strDate1);
            Date date2 = adf.parse(strDate2);
            return date1.before(date2);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取以今天0点0分0秒为基准，X天之前的时间戳值
     *
     * @param days 几天前
     * @return
     */
    public static long threeDayBeforeTimeStamp(int days) {
        long oneDayMillis = 24 * 60 * 60;//10位的时间戳,一天的时间戳值
        long threeDayMillis = oneDayMillis * days;//三天的时间戳值
        Long todaytimesmorning = new Long((long) DateUtil.getTimesmorning());//获取当天0点0分0秒的时间戳
        return todaytimesmorning - threeDayMillis;
    }

    //获得本周一0点时间
    public static int getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return (int) (cal.getTimeInMillis() / 1000);
    }

    //获得本周日24点时间
    public static int getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return (int) ((cal.getTime().getTime() + (7 * 24 * 60 * 60 * 1000)) / 1000);
    }

    /**
     * 生成一个时间翟，作为图片名字，时间格式：yyyyMMdd_hhmmss
     *
     * @return
     */
    public static String getImageName() {
        String name = new DateFormat().format("yyyyMMdd_hhmmss",
                Calendar.getInstance(Locale.CHINA))
                + ".jpg";
        return name;
    }

    /**
     * 生成一个当前时间的时间翟,10位
     *
     * @return
     */
    public static String getCurrentTimesTamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * 生成一个当前时间的时间翟,10位
     *
     * @return
     */
    public static long getCurrentTimesLong() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 生成一个当前时间的时间翟,13位
     *
     * @return
     */
    public static String getCurrentTimesStamptime() {
        return String.valueOf(System.currentTimeMillis());
    }


    //获得以本月为基准X月第一天0点时间
    public static int getTimesMonthmorning(int now_month) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, now_month);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return (int) (c.getTimeInMillis() / 1000);
    }

    //获得本月第一天0点时间
    public static int getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return (int) (cal.getTimeInMillis() / 1000);
    }

    /**
     * 转换时间
     * @param mills
     * @return
     */
    public static String toYYYYMMDD(long mills) {
        DateTime dateTime = new DateTime(mills);
        return dateTime.toString("yyyy-MM-dd");
    }

    //获得本月最后一天24点时间
    public static int getTimesMonthnight() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        //将小时至0
        ca.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        ca.set(Calendar.MINUTE, 0);
        //将秒至0
        ca.set(Calendar.SECOND, 0);
        //将毫秒至0
        ca.set(Calendar.MILLISECOND, 0);
        // 获取本月最后一天的时间戳
        return (int) (ca.getTimeInMillis() / 1000);
    }

    //获得上一个月第一天0点时间
    public static int getTimesLastMonthmorning() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return (int) (c.getTimeInMillis() / 1000);
    }

    //获得下一个月第一天0点时间
    public static int getTimesNextMonthmorning() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        //将小时至0
        c.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        c.set(Calendar.MINUTE, 0);
        //将秒至0
        c.set(Calendar.SECOND, 0);
        //将毫秒至0
        c.set(Calendar.MILLISECOND, 0);
        // 获取本月第一天的时间戳
        return (int) (c.getTimeInMillis() / 1000);
    }

    //获得当天0点时间
    public static int getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) (cal.getTimeInMillis() / 1000);
    }

    //获得当天24点时间
    public static int getTimesnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) (cal.getTimeInMillis() / 1000);
    }

    /**
     * 根据生日获取年龄
     *
     * @param year
     * @return
     */
    public static int getAgeByBirthday(int year) {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR) - year;
    }

    /**
     * 计算传入的时间戳与当前系统时间戳的关系,只识别传入的时间戳是否是今天,传入的时间戳是否是昨天,其余的返回""
     *
     * @param stampTime
     * @return
     */
    public static String getDayString(String stampTime) {
        String dd = getCurrentDate("dd");
        String dd1 = StampTimeToDate(stampTime, "dd");
        int i = Integer.parseInt(dd);
        int i1 = Integer.parseInt(dd1);
        if (i == i1) {
            return "今天";
        } else if ((i - 1) == i1) {
            return "昨天";
        } else {
            return "";
        }

    }

    /**
     * 传入 2018-1-25格式转换成2018.1
     *
     * @param data
     * @return
     */
    public static String dateRemoveDay(String data) {
        if (data != null && data.length() > 0) {
            if (data.contains("-")) {
                String replace = data.replace("-", ".");
                return replace.substring(0, replace.lastIndexOf("."));
            }
        }
        return "";
    }

    /**
     * 根据传入的时间返回格式化的时间
     *
     * @param date data
     * @return 格式化后的时间
     */
    public static String getYY_MM_DD(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * 将时间的-替换成.
     *
     * @param time
     * @return
     */
    public static String replaceTime_(String time) {
        if (!TextUtils.isEmpty(time)) {
            return time.replace("-", ".");
        }
        return "";
    }

    /**
     * 将日期转换成时间戳
     *
     * @param s      格式化后的时间
     * @param format "yyyy-MM-dd HH:mm:ss"
     * @return 返回时间戳
     */
    public static String dateToStamp(String s, String format) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
            long ts = date.getTime();
            res = String.valueOf(ts);
            return res;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "0";
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //不同年
        {
            return day2 - day1;
        }
    }

    /**
     * 获取当天的时间的年月日
     *
     * @return
     */
    public static String getTodayYearMonthDay() {
        Calendar now = Calendar.getInstance();
        String month = (now.get(Calendar.MONTH) + 1) < 10 ? ("0" + (now.get(Calendar.MONTH) + 1)) : ((now.get(Calendar.MONTH) + 1) + "");
        String day = (now.get(Calendar.DAY_OF_MONTH)) < 10 ? ("0" + (now.get(Calendar.DAY_OF_MONTH))) : ((now.get(Calendar.DAY_OF_MONTH)) + "");
        return now.get(Calendar.YEAR) + "_" + month + "_" + day;
    }

    /**
     * 获取当天的时间的时分
     *
     * @return
     */
    public static String getTodayTime() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        return (hour < 10 ? "0" + hour : hour) + "_" + (minute < 10 ? "0" + minute : minute);
    }

    /**
     * 获取当天的时间的年月日时分秒
     *
     * @return
     */
    public static String getTodayDetailsTime() {
        Calendar now = Calendar.getInstance();
        String month = (now.get(Calendar.MONTH) + 1) < 10 ? ("0" + (now.get(Calendar.MONTH) + 1)) : ((now.get(Calendar.MONTH) + 1) + "");
        String day = (now.get(Calendar.DAY_OF_MONTH) + 1) < 10 ? ("0" + (now.get(Calendar.DAY_OF_MONTH) + 1)) : ((now.get(Calendar.DAY_OF_MONTH) + 1) + "");
        String hour = (now.get(Calendar.HOUR_OF_DAY) + 1) < 10 ? ("0" + (now.get(Calendar.HOUR_OF_DAY) + 1)) : ((now.get(Calendar.HOUR_OF_DAY) + 1) + "");
        String minute = (now.get(Calendar.MINUTE) + 1) < 10 ? ("0" + (now.get(Calendar.MINUTE) + 1)) : ((now.get(Calendar.MINUTE) + 1) + "");
        String second = (now.get(Calendar.SECOND) + 1) < 10 ? ("0" + (now.get(Calendar.SECOND) + 1)) : ((now.get(Calendar.SECOND) + 1) + "");
        return now.get(Calendar.YEAR) + "_" + month + "_" + day + "_" + hour + "_" + minute + "_" + second;
    }

    /**
     * 获取两个时间直接的差(毫秒值)
     */
    public static long getTimeCount(long beginTime, long endTime) {
        return (endTime - beginTime) * 1000L;
    }

}
