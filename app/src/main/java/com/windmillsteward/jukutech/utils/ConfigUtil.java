package com.windmillsteward.jukutech.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;

/***
 * 获取配置文件信息 lrx 2014-4-17 v1.0
 */
public class ConfigUtil {
    /**
     * @param context 上下文
     * @param key     需要获取的key名
     * @return 字符串
     * @throws NameNotFoundException
     */
    public static String getValue(Context context, String key)
            throws NameNotFoundException {
        PackageManager pm = context.getPackageManager();
        ApplicationInfo ai = pm.getApplicationInfo(context.getPackageName(),
                PackageManager.GET_META_DATA);
        Bundle bundle = ai.metaData;
        String str = bundle.getString(key);
        String result = str.substring(0, str.length() - 1);
        return result;
    }

    /**
     * 得到客户端版本名称
     * @param ctx 上下文
     * @return V1.1.1
     */
    public static String getVersionName(Context ctx) {
        String versionName = "";
        PackageManager manager;
        PackageInfo info = null;
        manager = ctx.getPackageManager();
        try {
            info = manager.getPackageInfo(ctx.getPackageName(), 0);
            versionName = info.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 得到客户端版本号
     * @param ctx 上下文
     * @return 2
     */
    public static int getVersionCode(Context ctx) {
        int versionCode = 0;
        PackageManager manager;
        PackageInfo info = null;
        manager = ctx.getPackageManager();
        try {
            info = manager.getPackageInfo(ctx.getPackageName(), 0);
            versionCode = info.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context)
    {
        try
        {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (NameNotFoundException e)
        {
            e.printStackTrace();
        }
        return "";
    }


}
