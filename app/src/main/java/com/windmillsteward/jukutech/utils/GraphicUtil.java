package com.windmillsteward.jukutech.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.InputStream;

public class GraphicUtil {
    public final static int TAG_WIDTH = 0;
    public final static int TAG_HEIGHT = 1;
    public final static int IMAGE_TURN_LEFT = 2;
    public final static int IMAGE_TURN_RIGHT = 3;

    /**
     * @param activity
     * @param tagWH    TAG_WIDTH/TAG_HEIGHT
     * @return height in pixels(屏幕分辨率，包括了状态栏的高度)
     */
    public static int getScreenWH(Activity activity, int tagWH) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        switch (tagWH) {
            case TAG_WIDTH:
                return metrics.widthPixels;
            case TAG_HEIGHT:
                return metrics.heightPixels;
            default:
                return 0;
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        if (scale <= 0)
            return (int) pxValue;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 同dp2px
     *
     * @param context 上下文
     * @param dip     值
     * @return 像素
     */
    public static int dipToPixels(Context context, int dip) {
        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip,
                r.getDisplayMetrics());
        return (int) px;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        if (scale <= 0)
            return (int) dpValue;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        if (scale <= 0)
            return (int) pxValue;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param context
     * @param spValue
     * @return
     */
    @Deprecated
    // 不太靠谱
    public static int sp2px(Context context, float spValue) {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        if (scale <= 0)
            return (int) spValue;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * 设置view宽高(线性布局linearLayout)
     *
     * @param wh 控件的宽高,单位px
     * @param v  控件view
     */
    public static void setViewWH_Line(int wh, View v) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(wh, wh);
        v.setLayoutParams(params);
    }
    /**
     * 设置view宽高(线性布局linearLayout),并设置间距
     *
     * @param w 控件的宽
     * @param h 控件的高,单位px
     * @param v  控件view
     */
    public static void setViewWH_Line_margin(Context context, int w,int h, View v,int dp) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w, h);
        int top = GraphicUtil.dp2px(context, dp);
        params.setMargins(0, top, 0, 0);
        v.setLayoutParams(params);
    }

    /**
     * 设置View宽高(相对布局relativeLayout)
     *
     * @param wh 控件的宽高,单位px
     * @param v  控件
     */
    public static void setViewWH_Rela(int wh, View v) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                wh, wh);
        v.setLayoutParams(params);
    }

    /**
     * 设置view宽高(线性布局linearLayout)
     *
     * @param w 控件宽 ,单位px
     * @param h 控件高,单位px
     * @param v 控件view
     */
    public static void setViewWH_Line(int w, int h, View v) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w, h);
        v.setLayoutParams(params);
    }

    /**
     * 设置view宽高(相对布局relativeLayout)
     *
     * @param w 控件宽,单位px
     * @param h 控件高,单位px
     * @param v 控件view
     */
    public static void setViewWH_Rela(int w, int h, View v) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(w,
                h);
        v.setLayoutParams(params);
    }

    /**
     * 解码图片流，据说可以避免内存溢出：bitmap size exceeds VM budget
     *
     * @param picStream
     * @return
     */
    public static Bitmap decodeStream(InputStream picStream) {
        Bitmap bitmap = null;
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inSampleSize = 4;
        bitmap = BitmapFactory.decodeStream(picStream);
        return bitmap;
    }
}
