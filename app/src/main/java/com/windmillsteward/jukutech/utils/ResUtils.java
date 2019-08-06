package com.windmillsteward.jukutech.utils;

import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.MyApplication;

/**
 * @date: on 2018/10/15
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class ResUtils {
    /**
     * 获取公共橘黄色
     *
     * @return
     */
    public static int getCommRed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return MyApplication.instance.getColor(R.color.color_comm_red);
        }else{
            return MyApplication.instance.getResources().getColor(R.color.color_comm_red);
        }
    }

    /**
     * 获取主题色
     *
     * @return
     */
    public static int getCommThem() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return MyApplication.instance.getColor(R.color.color_them);
        }else{
            return MyApplication.instance.getResources().getColor(R.color.color_them);
        }

    }

    /**
     * 获取指定颜色
     *
     * @param id
     * @return
     */
    public static int getColor(@ColorRes int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return MyApplication.instance.getColor(id);
        }else{
            return MyApplication.instance.getResources().getColor(id);
        }

    }

    /**
     * 获取指定字符串
     *
     * @param resId
     * @return
     */
    public static String getString(@StringRes int resId) {
        return MyApplication.instance.getString(resId);
    }
}
