package com.windmillsteward.jukutech.customview;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Locale;

/**
 * 描述：日历控件fragment，由于showDialog方法已经遗弃，官方推荐使用DialogFragment
 * author:cyq
 * 2017/7/2
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;
    public Calendar calendar;
    private OnDateInputListener onDateInputListener;//回调给activity


    public interface OnDateInputListener {
        void getDate(int year, int month, int day);
    }

    public void setOnDateInputListener(OnDateInputListener onDateInputListener) {
        this.onDateInputListener = onDateInputListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        getCurrentTime();
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        onDateInputListener.getDate(year, month + 1, dayOfMonth);
    }

    /**
     * 获取当前时间，并且赋值
     */
    private void getCurrentTime() {
        //设置时间为中国
        calendar = Calendar.getInstance(Locale.CHINA);
        //获取日期
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }
}
