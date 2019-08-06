package com.windmillsteward.jukutech.utils.view;

import android.app.Activity;

import com.blankj.utilcode.util.ScreenUtils;
import com.windmillsteward.jukutech.utils.DateUtil;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.DateTimePicker;
import cn.qqtheme.framework.picker.NumberPicker;
import cn.qqtheme.framework.picker.SinglePicker;
import cn.qqtheme.framework.picker.TimePicker;

/**
 * @date: on 2018/10/15
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 显示Picker
 */
public class PickerViewWrap<T> {
    /**
     * 显示日期选择器
     *
     * @param activity
     * @param listener
     */
    public DatePicker showSelectBirthdayPicker(Activity activity, DatePicker.OnYearMonthDayTimePickListener listener) {
        String today = DateUtil.getTodayYearMonthDay();
        return showSelectBirthdayPicker(activity, listener, today);
    }

    /**
     * 日期选择器
     *
     * @param activity
     * @param listener
     * @param aimDate
     */
    public DatePicker showSelectBirthdayPicker(Activity activity, DatePicker.OnYearMonthDayTimePickListener listener, String aimDate) {
        DatePicker datePicker = new DatePicker(activity);
        datePicker.setRangeStart(1970, 1, 1);
        String today = DateUtil.getTodayYearMonthDay();
        datePicker.setRangeEnd(Integer.parseInt(today.split("_")[0]),
                Integer.parseInt(today.split("_")[1]),
                Integer.parseInt(today.split("_")[2]));
        try {
            datePicker.setSelectedItem(Integer.parseInt(aimDate.split("_")[0]),
                    Integer.parseInt(aimDate.split("_")[1]),
                    Integer.parseInt(aimDate.split("_")[2]));
        } catch (Exception e) {
            throw new RuntimeException("格式yyyy_MM_dd");
        }
        datePicker.setOnDateTimePickListener(listener);
        datePicker.setUseWeight(true);
        datePicker.setOffset(2);
        datePicker.setLineSpaceMultiplier(3);
        datePicker.setHeight(ScreenUtils.getScreenHeight()/3);
        datePicker.show();
        return datePicker;
    }
    /**
     * 日期选择器(年月日时分)
     *
     * @param activity
     * @param listener
     */
    public DateTimePicker showNianYueRiShiFenPicker(Activity activity, DatePicker.OnYearMonthDayTimePickListener listener, int delayDays) {
        DateTimePicker datePicker = new DateTimePicker(activity,DateTimePicker.HOUR_24);
        DateTime dateTime1 = new DateTime();
        DateTime dateTime = dateTime1.plusDays(delayDays);
        int year = dateTime.getYear(); // 2014
        int month = dateTime.getMonthOfYear();
        int day = dateTime.getDayOfMonth();
        datePicker.setTimeRangeStart(0, 0);
        datePicker.setTimeRangeEnd(23, 59);
        String times = DateUtil.getTodayTime();
        datePicker.setDateRangeStart(year, month, day);
        DateTime dateTime2 = dateTime.plusYears(5);
        int year1 = dateTime2.getYear();
        int month1 = dateTime2.getMonthOfYear();
        int day1 = dateTime2.getDayOfMonth();
        datePicker.setDateRangeEnd(year1, month1, day1);
        datePicker.setSelectedItem(year, month, day,Integer.parseInt(times.split("_")[0]), Integer.parseInt(times.split("_")[1]));
        datePicker.setOnDateTimePickListener(listener);
        datePicker.setHeight(ScreenUtils.getScreenHeight()/3);
        datePicker.setUseWeight(true);
        datePicker.setOffset(2);
        datePicker.setLineSpaceMultiplier(3);
        datePicker.show();
        return datePicker;

//        DateTimePicker picker = new DateTimePicker(activity, DateTimePicker.HOUR_24);
//        picker.setDateRangeStart(2017, 1, 1);
//        picker.setDateRangeEnd(2025, 11, 11);
//        picker.setTimeRangeStart(9, 0);
//        picker.setTimeRangeEnd(20, 30);
//        picker.setTopLineColor(0x99FF0000);
//        picker.setLabelTextColor(0xFFFF0000);
//        picker.setDividerColor(0xFFFF0000);
//        picker.setOnDateTimePickListener(listener);
//        picker.show();
//        return picker;
    }
    /**
     * 日期选择器 从今天起往后延迟 delayDays 天
     *
     * @param activity
     * @param listener
     * @param delayDays
     * @return
     */
    public DatePicker showDateFromTodayPicker(Activity activity, DatePicker.OnYearMonthDayTimePickListener listener, int delayDays) {
        DatePicker datePicker = new DatePicker(activity);
        DateTime dateTime1 = new DateTime();
        DateTime dateTime = dateTime1.plusDays(delayDays);
        int year = dateTime.getYear(); // 2014
        int month = dateTime.getMonthOfYear();
        int day = dateTime.getDayOfMonth();
        datePicker.setRangeStart(year, month, day);
        DateTime dateTime2 = dateTime.plusYears(5);
        int year1 = dateTime2.getYear();
        int month1 = dateTime2.getMonthOfYear();
        int day1 = dateTime2.getDayOfMonth();
        datePicker.setRangeEnd(year1, month1, day1);
        datePicker.setSelectedItem(year, month, day);
        datePicker.setOnDateTimePickListener(listener);
        datePicker.setHeight(ScreenUtils.getScreenHeight()/3);
        datePicker.setUseWeight(true);
        datePicker.setOffset(2);
        datePicker.setLineSpaceMultiplier(3);
        datePicker.show();
        return datePicker;
    }


    /**
     * 显示性别选择器
     *
     * @param activity
     * @param position
     * @param listener
     */
    public SinglePicker showSexSelectPicker(Activity activity, int position, SinglePicker.OnItemPickListener listener) {
        List<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        SinglePicker<String> picker = new SinglePicker<String>(activity, list);
        picker.setCanceledOnTouchOutside(false);
        picker.setSelectedIndex(1);
        picker.setUseWeight(true);
        picker.setItemWidth(ScreenUtils.getScreenWidth());
        picker.setOnItemPickListener(listener);
        if (position != -1)
            picker.setSelectedIndex(position);
        picker.setHeight(ScreenUtils.getScreenHeight()/3);
        picker.setOffset(2);
        picker.setLineSpaceMultiplier(3);
        picker.show();
        return picker;
    }

    /**
     * 显示通用选择器
     *
     * @param activity
     * @param position
     * @param listener
     * @param list
     */
    public SinglePicker showCommonSelectPicker(Activity activity, int position, SinglePicker.OnItemPickListener<T> listener, List<T> list) {
        if (list == null)
            throw new RuntimeException("数据源不能为空");
        SinglePicker<T> picker = new SinglePicker(activity, list);
        picker.setItemWidth(ScreenUtils.getScreenWidth());
        if (position != -1)
            picker.setSelectedIndex(position);
        picker.setOnItemPickListener(listener);
        picker.setHeight(ScreenUtils.getScreenHeight()/3);
        picker.setUseWeight(true);
        picker.setOffset(2);
        picker.setLineSpaceMultiplier(3);
        picker.show();
        return picker;
    }

    /**
     * 显示选择时间的弹窗
     *
     * @param activity
     */
    public TimePicker showTimeSelectPicker(Activity activity, TimePicker.OnTimePickListener listener) {
        TimePicker timePicker = new TimePicker(activity);
        timePicker.setRangeStart(0, 0);
        timePicker.setRangeEnd(23, 59);
        String times = DateUtil.getTodayTime();
        timePicker.setSelectedItem(Integer.parseInt(times.split("_")[0]), Integer.parseInt(times.split("_")[1]));
        timePicker.setOnTimePickListener(listener);
        timePicker.setHeight(ScreenUtils.getScreenHeight()/3);
        timePicker.setUseWeight(true);
        timePicker.setLineSpaceMultiplier(3);
        timePicker.setOffset(2);
        timePicker.show();
        return timePicker;
    }
    /**
     * 显示选择时间的弹窗
     *
     * @param activity
     */
    public TimePicker showTimeSelectPickerNew(Activity activity,TimePicker.OnWheelListener onWheelListener, TimePicker.OnTimePickListener listener) {
        TimePicker timePicker = new TimePicker(activity);

        timePicker.setOnWheelListener(onWheelListener);
        timePicker.setRangeStart(0, 0);
        timePicker.setRangeEnd(23, 59);
        String times = DateUtil.getTodayTime();
        timePicker.setSelectedItem(Integer.parseInt(times.split("_")[0]), Integer.parseInt(times.split("_")[1]));
        timePicker.setOnTimePickListener(listener);
        timePicker.setHeight(ScreenUtils.getScreenHeight()/3);
        timePicker.setUseWeight(true);
        timePicker.setLineSpaceMultiplier(3);
        timePicker.setOffset(2);
        timePicker.show();
        return timePicker;
    }
    /**
     * 显示选择时间的弹窗
     *
     * @param activity
     */
    public TimePicker showTimeSelectPicker(Activity activity, int hour, int miniute, TimePicker.OnTimePickListener listener) {
        TimePicker timePicker = new TimePicker(activity);
        timePicker.setRangeStart(0, 0);
        timePicker.setRangeEnd(23, 0);
        timePicker.setSelectedItem(hour, miniute);
        timePicker.setOnTimePickListener(listener);
        timePicker.setHeight(ScreenUtils.getScreenHeight()/3);
        timePicker.setUseWeight(true);
        timePicker.setOffset(2);
        timePicker.setLineSpaceMultiplier(3);
        timePicker.show();
        return timePicker;
    }

    public NumberPicker showNumberPicker(Activity activity){
        NumberPicker numberPicker = new NumberPicker(activity);
        return numberPicker;
    }

}
