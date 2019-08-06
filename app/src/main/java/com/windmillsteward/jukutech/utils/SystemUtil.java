package com.windmillsteward.jukutech.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import java.security.MessageDigest;

/**
 * 系统工具类
 *
 * @author yanchunhan
 * @created 2014-04-02
 */
public class SystemUtil {


    /**
     * 隐藏键盘
     */
    public static void dismissKeyBorwd(Activity activity) {
        try {
            if (activity.getCurrentFocus() != null) {
                ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭键盘,稍微不同于上面的隐藏键盘
     * @param activity
     */
    public static void closeKeyBord(Activity activity){
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm.isActive()&&activity.getCurrentFocus()!=null){
            if (activity.getCurrentFocus().getWindowToken()!=null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 弹出键盘
     */
    public static void showKeyBorwd(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 加密算法 使用位运算
     *
     * @time 2016/10/10 9:56
     */
    private static String getHashString(MessageDigest digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        return builder.toString();
    }


//    /**
//     * MD5加密
//     *
//     * @param content 需要加密的字符
//     * @return 加密后的字符串
//     */
//    public static String getMD5(String content) {
//        try {
//            MessageDigest digest = MessageDigest.getInstance("MD5");
//            digest.update(content.getBytes());
//            return getHashString(digest);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
