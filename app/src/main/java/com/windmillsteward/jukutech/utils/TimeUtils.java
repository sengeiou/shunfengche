package com.windmillsteward.jukutech.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class TimeUtils {
    public static String longDataFormat(String str) {
        String ret = "";
        if (str == null || str.equals(""))
            return ret;
        long mLongData = Long.parseLong(str);
        Date data = new Date(mLongData);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ret = formatter.format(data);
        return ret;
    }

    public static String getCurrentDate() {
        String ret = "";
        Date data = new Date(System.currentTimeMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ret = formatter.format(data);
        return ret;
    }

    /**
     * 获取两个日期相差的月数
     *
     * @param d1 较大的日期
     * @param d2 较小的日期
     * @return 如果d1>d2返回 月数差 否则返回0
     */
    public static int getMonthDiff(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
//        if(c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        // 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30
        int yearInterval = year1 - year2;
        // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
        if (month1 < month2 || month1 == month2 && day1 < day2) yearInterval--;
        // 获取月数差值
        int monthInterval = (month1 + 12) - month2;
        if (day1 < day2) monthInterval--;
        monthInterval %= 12;
        return yearInterval * 12 + monthInterval;
    }

    public static String longDataFormat2(String str) {
        String ret = "";
        if (str == null || str.equals(""))
            return ret;
        long mLongData = Long.parseLong(str);
        Date data = new Date(mLongData);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        ret = formatter.format(data);
        return ret;
    }

    //字符串转date
    public static Date formatDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 将毫秒转化成YYYYMMDD
     * @param mill
     * @return
     */
    public static String formatMillToYYYYMMDD(long mill) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(mill));
    }

    public static final int FORMAT_DURING_TYPE_DAY = 0;
    public static final int FORMAT_DURING_TYPE_HOUR = 1;
    public static final int FORMAT_DURING_TYPE_MINUTE = 2;
    public static final int FORMAT_DURING_TYPE_SECOND = 3;
    public static final int FORMAT_DURING_TYPE_ALL = 4;

    public static String formatDuring(long mss, int type) {
        Log.e("test11", "formatDuring: " + System.currentTimeMillis());
        mss = mss - System.currentTimeMillis();
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        switch (type) {
            case 0:
                if (hours > 1 || minutes > 1 || seconds > 1)
                    return days + 1 + "";
                return days + "";
            case 1:
                return hours + "";
            case 2:
                return minutes + "";
            case 3:
                return seconds + "";
        }
        return hours + "小时 " + minutes + "分 " + seconds + "秒 ";
//        return days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds ";
    }

    public static String getLeftTime(int s) {
        long hours = (s % (60 * 60 * 24)) / (60 * 60);
        long minutes = (s % (60 * 60)) / 60;
        long seconds = s % 60;
        return hours + "时 " + minutes + "分 " + seconds + "秒 ";
//        return days + " days " + hours + " hours " + minutes + " minutes " + seconds + " seconds ";
    }

    /**
     * @param date riqi
     * @return 输入的两个Date类型数据之间的时间间格用* days * hours * minutes * seconds的格式展示
     */
    public static String formatDuring(String date, int type) {
        if (date == null || "0".equals(date))
            return "0";
        return formatDuring(formatDate(date).getTime(), type);
    }

    //yyyy/MM/dd HH:mm  返回 HH:mm
    public static String getHour(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d = null;
        try {
            d = sdf.parse(date);
            if (d != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(d);
                int hour = calendar.get(Calendar.HOUR);
                int minute = calendar.get(Calendar.MINUTE);
                return hour + ":" + minute;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDateWithWeek() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String curWeek = "";
        switch (dayOfWeek) {
            case 1:
                curWeek = "星期日";
                break;
            case 2:
                curWeek = "星期一";
                break;
            case 3:
                curWeek = "星期二";
                break;
            case 4:
                curWeek = "星期三";
                break;
            case 5:
                curWeek = "星期四";
                break;
            case 6:
                curWeek = "星期五";
                break;
            case 7:
                curWeek = "星期六";
                break;
        }

        return getCurrentDate() + "   " + curWeek;
    }

    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime) throws ParseException {
        String formatType = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    /**
     * 生成一个当前时间的时间翟
     *
     * @return
     */
    public static long getCurrentTimesTamp() {
        return System.currentTimeMillis() / 1000;
    }

    /*
     *计算time2减去time1的差值 差值只设置 几天 几个小时 或 几分钟
     * 根据差值返回多长之间前或多长时间后
     * */
    public static String getDistanceTime(long time1, long time2) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        long diff;
        String flag;
        if (time1 < time2) {
            diff = time2 - time1;
            flag = "前";
        } else {
            diff = time1 - time2;
            flag = "后";
        }
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (day != 0) return day + "天" + flag;
        if (hour != 0) return hour + "小时" + flag;
        if (min != 0) return min + "分钟" + flag;
        return "刚刚";
    }
}
