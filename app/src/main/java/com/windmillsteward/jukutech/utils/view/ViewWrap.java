package com.windmillsteward.jukutech.utils.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;

import com.windmillsteward.jukutech.activity.home.commons.web.CommonWebActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.PhotoViewActivity;
import com.windmillsteward.jukutech.customview.FlyBanner;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import org.joda.time.DateTime;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @date: on 2018/10/9
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 处理一些VIew相关的处理
 */
public class ViewWrap {
    /**
     * 全局处理FlyBanner 防止需求改来改去
     * 使用指定比例
     *
     * @param flyBanner
     */
    public static void handlerFlyBanner(final FlyBanner flyBanner, final int ratioX, final int ratioY) {
        ViewTreeObserver vto = flyBanner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onGlobalLayout() {
                flyBanner.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int width = flyBanner.getMeasuredWidth();
                ViewGroup.LayoutParams layoutParams = flyBanner.getLayoutParams();
                layoutParams.width = width;
                layoutParams.height = ratioY * layoutParams.width / ratioX;
                flyBanner.setLayoutParams(layoutParams);
            }
        });
    }

    /**
     * 全局处理FlyBanner 防止需求改来改去
     * 使用指定比例
     *
     * @param img
     */
    public static void handlerImageView(final ImageView img, final int ratioX, final int ratioY) {
        ViewTreeObserver vto = img.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onGlobalLayout() {
                img.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                int width = img.getMeasuredWidth();
                ViewGroup.LayoutParams layoutParams = img.getLayoutParams();
                layoutParams.width = width;
                layoutParams.height = ratioY * layoutParams.width / ratioX;
                img.setLayoutParams(layoutParams);
            }
        });
    }
    /**
     * 全局处理FlyBanner 防止需求改来改去
     * 使用指定比例
     */
    public static void handlerImageViewTwo(Context context,ImageView mImageView,int mip, int ratioX, int ratioY) {
        //获取屏幕宽度
        WindowManager m = (WindowManager)context .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        m.getDefaultDisplay().getMetrics(outMetrics);

        //计算宽高
        int width = (outMetrics.widthPixels- GraphicUtil.dp2px(context,40))/mip; //乘以2是因为左右两侧的宽度
        int height = (int) (ratioY * width / ratioX); //280*136

        ViewGroup.LayoutParams layoutParams = mImageView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        mImageView.setLayoutParams(layoutParams);
    }



    /**
     * 打开看大图的Activity
     *
     * @param activity
     * @param urls
     * @param position
     */
    public static void showPicActivity(Activity activity, ArrayList<String> urls, int position) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(PhotoViewActivity.PIC_URLS, urls);
        bundle.putInt(PhotoViewActivity.CURR_POSITION, position);
        Intent intent = new Intent(activity, PhotoViewActivity.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    /**
     * 全局处理FlyBanner 防止需求改来改去 使用默认比例
     *
     * @param flyBanner
     */
    public static void handlerFlyBanner(final FlyBanner flyBanner) {
        handlerFlyBanner(flyBanner, 670, 250);
    }

    /**
     * 全局处理ImageView 防止需求改来改去 使用默认比例
     *
     * @param img
     */
    public static void handlerImageView(final ImageView img) {
        handlerImageView(img, 670, 480);
    }


    /**
     * 获取视频的关键帧作为封面
     *
     * @param videoPath 视频本地路径
     * @param width     宽
     * @param height    高
     * @param kind      could be MINI_KIND or MICRO_KIND
     * @return bitmap
     */
    public static Bitmap getVideoThumbnail(Context context, String videoPath, int width, int height,
                                           int kind) {
        Bitmap bitmap = null;
        // 获取视频的缩略图
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
        bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height,
                ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        String video_cover_path = context.getCacheDir().getAbsolutePath() + File.separator + "takephoto_cache" + File.separator + DateTime.now().toString("yyyyMMddHHmmsss") + ".jpg";

        try {
            File file = new File(video_cover_path);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    public static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }


    /**
     * 给flayBanner设置内容
     *
     * @param context
     * @param pics
     * @param urls
     * @param flyBanner
     */
    public static void setUpFlyBanner(final Context context, final List<String> pics, final List<String> urls, FlyBanner flyBanner) {
        if (flyBanner != null) {
            if (pics != null)
                flyBanner.setImagesUrl(pics);
            handlerFlyBanner(flyBanner);
            if (urls != null)
                flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (urls.size() > position) {

                            Intent intent = new Intent();
                            intent.setClass(context, CommonWebActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString(Define.INTENT_DATA, urls.get(position));
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                        }
                    }
                });
        }
    }


    /**
     * 给flayBanner设置内容
     *
     * @param context
     * @param pics
     * @param urls
     * @param flyBanner
     * @param width
     * @param height
     */
    public static void setUpFlyBanner(final Context context, final List<String> pics, final List<String> urls, FlyBanner flyBanner, int width, int height) {
        setUpFlyBanner(context, pics, urls, flyBanner, width, height, true);
    }

    /**
     * 给flayBanner设置内容
     *
     * @param context
     * @param pics
     * @param urls
     * @param flyBanner
     * @param width
     * @param height
     * @param seeBigPic
     */
    public static void setUpFlyBanner(final Context context, final List<String> pics, final List<String> urls, FlyBanner flyBanner, int width, int height, final boolean seeBigPic) {
        if (flyBanner != null) {
            if (pics != null) {
                flyBanner.setImagesUrl(pics);

                //看大图
                if (seeBigPic) {
                    flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            if (pics.size() > position) {
                                Intent intent = new Intent();
                                intent.setClass(context, PhotoViewActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putStringArrayList(PhotoViewActivity.PIC_URLS, (ArrayList<String>) pics);
                                bundle.putInt(PhotoViewActivity.CURR_POSITION, position);
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                            }
                        }
                    });
                }
            }

            handlerFlyBanner(flyBanner, width, height);

            if (urls != null)
                flyBanner.setOnItemClickListener(new FlyBanner.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        if (urls.size() > position) {
                                Intent intent = new Intent();
                                intent.setClass(context, CommonWebActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString(Define.INTENT_DATA, urls.get(position));
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                        }
                    }
                });

        }
    }




}
