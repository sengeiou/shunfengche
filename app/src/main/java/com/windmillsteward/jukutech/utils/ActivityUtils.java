package com.windmillsteward.jukutech.utils;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.windmillsteward.jukutech.activity.map.ShowMapActivity;
import com.windmillsteward.jukutech.activity.map.ShowMapZhaoPinActivity;

/**
 * @date: on 2018/10/18
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class ActivityUtils {
    public static final int MAP_REQUEST_CODE = 1999;

    /**
     * 打开显示Map地图的Activity
     *
     * @param activity
     * @param longilati
     */
    public static void jumpToShowMap(Activity activity, String longilati) {
        if (TextUtils.isEmpty(longilati) || !longilati.contains(",")) {
            return;
        }
        Intent intent = new Intent(activity, ShowMapActivity.class);
        intent.putExtra("longitude", longilati.split(",")[0]);
        intent.putExtra("latitude", longilati.split(",")[1]);
        activity.startActivity(intent);
    }

    //招聘方调用
    public static void jumpToShowMapZhaopin(Activity activity, int type, int require_id,String longitude,String latitude) {
        ShowMapZhaoPinActivity.showMap(activity, type, require_id, longitude,latitude);
    }

    //应聘方调用
    public static void jumpToShowMapYingpin(Activity activity, int type, int require_id,String longilati) {
        if (TextUtils.isEmpty(longilati) || !longilati.contains(",")) {
            return;
        }
        ShowMapActivity.showMap(activity, type, require_id, longilati.split(",")[0],longilati.split(",")[1]);
    }

    /**
     * 打开显示Map地图的Activity
     *
     * @param activity
     * @param longilati
     */
    public static void jumpToShowMapForResult(Activity activity, String longilati) {
        if (TextUtils.isEmpty(longilati) || !longilati.contains(",")) {
            return;
        }
        Intent intent = new Intent(activity, ShowMapActivity.class);
        intent.putExtra("longitude", longilati.split(",")[0]);
        intent.putExtra("latitude", longilati.split(",")[1]);
        activity.startActivityForResult(intent, MAP_REQUEST_CODE);
    }

    /**
     * 打开显示Map地图的Activity
     *
     * @param fragment
     * @param longilati
     */
    public static void jumpToShowMap(Fragment fragment, String longilati) {
        if (TextUtils.isEmpty(longilati) || !longilati.contains(",")) {
            return;
        }
        Intent intent = new Intent(fragment.getContext(), ShowMapActivity.class);
        intent.putExtra("longitude", longilati.split(",")[0]);
        intent.putExtra("latitude", longilati.split(",")[1]);
        fragment.startActivity(intent);
    }

    /**
     * 打开显示Map地图的Activity
     *
     * @param fragment
     * @param longilati
     */
    public static void jumpToShowMapForResult(Fragment fragment, String longilati) {
        if (TextUtils.isEmpty(longilati) || !longilati.contains(",")) {
            return;
        }
        Intent intent = new Intent(fragment.getContext(), ShowMapActivity.class);
        intent.putExtra("longitude", longilati.split(",")[0]);
        intent.putExtra("latitude", longilati.split(",")[1]);
        fragment.startActivityForResult(intent, MAP_REQUEST_CODE);
    }
}
