package com.windmillsteward.jukutech.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.windmillsteward.jukutech.activity.MyApplication;

/**
 * @date: on 2018/10/17
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: View 的一些常用工具
 */
public class ViewUtils {
    private static TextView textView;

    /**
     * 获取屏幕缩放比
     *
     * @return
     */
    public static float getScaleSize() {
        if (textView == null)
            textView = new TextView(MyApplication.instance);
        textView.setTextSize(1);
        return textView.getTextSize();

    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
