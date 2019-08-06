package com.windmillsteward.jukutech.utils;

import android.content.Intent;

import com.windmillsteward.jukutech.activity.MyApplication;


/**
 * 广播工具类
 */

public class BroadcastUtils {
    /**
     * 发送空数据广播
     *
     * @param action 广播识别值
     */
    public static void sendNullDataBroadcast(String action) {
        Intent data = new Intent(action);
        MyApplication.instance.sendBroadcast(data);
    }
}
