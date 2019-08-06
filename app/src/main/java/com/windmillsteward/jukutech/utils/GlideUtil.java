package com.windmillsteward.jukutech.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.windmillsteward.jukutech.R;

/**
 * @date: on 2018/10/7
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class GlideUtil {

    /**
     * 显示图片
     *
     * @param context
     * @param url
     * @param aimIv
     */
    public static void show(Context context, Object url, ImageView aimIv) {
        Glide.with(context).load(url).apply(defaultOptions(0, 0)).into(aimIv);
    }

//    public static Bitmap show1(Context context, Object url, ImageView aimIv){
//        Bitmap bitmap = null;
//        Glide.with(context).load(url).into(aimIv).
//        return bitmap;
//    }

//    public static void show(Context context, String url, ImageView aimIv,int o) {
//        Glide.with(context).load(Uri.parse(url)).apply(defaultOptions(0, 0)).into(aimIv);
//    }

    public static void show(Context context, Object url, SimpleTarget<Drawable> simpleTarget) {
        Glide.with(context).load(url).apply(defaultOptions(0, 0)).into(simpleTarget);
    }

    public static void show(Fragment context, Object url, ImageView aimIv) {
        Glide.with(context).load(url).apply(defaultOptions(0, 0)).into(aimIv);
    }

    public static void show(Context context, Object url, ImageView aimIv, int errorRes) {
        Glide.with(context).load(url).apply(defaultOptions(errorRes, 0)).into(aimIv);
    }

    public static void show(Fragment context, Object url, ImageView aimIv, int errorRes) {
        Glide.with(context).load(url).apply(defaultOptions(errorRes, 0)).into(aimIv);
    }

    public static void show(Context context, Object url, ImageView aimIv, int errorRes, int placeholderRes) {
        Glide.with(context).load(url).apply(defaultOptions(errorRes, placeholderRes)).into(aimIv);
    }

    public static void show(Fragment context, Object url, ImageView aimIv, int errorRes, int placeholderRes) {
        Glide.with(context).load(url).apply(defaultOptions(errorRes, placeholderRes)).into(aimIv);
    }


    /**
     * 获取默认配置
     *
     * @return
     */
    public static RequestOptions defaultOptions(int errorRes, int placeholderRes) {
        int error = R.mipmap.icon_default_pic;
        int placeholder = R.mipmap.icon_default_pic;
        if (errorRes != 0) {
            error = errorRes;
        }
        if (placeholderRes != 0) {
            placeholder = placeholderRes;
        }
        return new RequestOptions().error(error).placeholder(placeholder).centerCrop();
    }
}
