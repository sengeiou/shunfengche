package com.windmillsteward.jukutech.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.interfaces.Define;

import org.xutils.image.ImageOptions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 图片配置与压缩操作工具
 *
 * @author zhuxian
 * @time 2016/10/9 23:00
 */
public class ImageUtils {

    /**
     * 默认头像图片
     */
    public static ImageOptions defaulHeadOptionsTwo() {
        ImageOptions options = new ImageOptions.Builder().setIgnoreGif(false)
                .setLoadingDrawableId(R.mipmap.icon_default_pic)
                .setFailureDrawableId(R.mipmap.icon_default_pic)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setCircular(false)
                .setUseMemCache(true).build();
        return options;
    }
    /**
     * 默认头像图片
     */
    public static ImageOptions defaulHeadOptionsCenterCrop() {
        ImageOptions options = new ImageOptions.Builder()
                .setFadeIn(true)
                .setIgnoreGif(false)
                .setFailureDrawableId(R.mipmap.icon_default_pic)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setCircular(false)
                .setUseMemCache(true).build();
        return options;
    }
    /**
     * 默认头像图片
     */
    public static ImageOptions defaulHeadOptionsThree() {
        ImageOptions options = new ImageOptions.Builder().setIgnoreGif(false)
                .setLoadingDrawableId(R.mipmap.pic_list_set)
                .setFailureDrawableId(R.mipmap.pic_list_set)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setCircular(false)
                .setUseMemCache(true).build();
        return options;
    }
    /**
     * 默认头像图片(圆形)
     */
    public static ImageOptions defaulCircleHeadOptions() {
        ImageOptions options = new ImageOptions.Builder().setIgnoreGif(false)
                .setLoadingDrawableId(R.mipmap.icon_default_pic)
                .setFailureDrawableId(R.mipmap.icon_default_pic)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setLoadingDrawableId(R.mipmap.icon_default_pic)
                .setCircular(true)
                .setUseMemCache(true).build();
        return options;
    }

    /**
     * 默认轮播图图片
     */
    public static ImageOptions defaulBannerPic() {
        ImageOptions options = new ImageOptions.Builder().setIgnoreGif(false)
                .setLoadingDrawableId(R.mipmap.icon_default_banner)
                .setFailureDrawableId(R.mipmap.icon_default_banner)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setCircular(false)
                .setUseMemCache(true).build();
        return options;
    }

    /**
     * 房屋租售默认图片
     *
     * @return
     */
    public static ImageOptions defaulPicList() {
        ImageOptions options = new ImageOptions.Builder().setIgnoreGif(false)
                .setLoadingDrawableId(R.drawable.pic_list)
                .setFailureDrawableId(R.drawable.pic_list)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setCircular(false)
                .setUseMemCache(true).build();
        return options;
    }

    public static ImageOptions defaulHeader() {
        ImageOptions options = new ImageOptions.Builder().setIgnoreGif(false)
                .setLoadingDrawableId(R.drawable.icon_head_male)
                .setFailureDrawableId(R.drawable.icon_head_male)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setCircular(false)
                .setUseMemCache(true).build();
        return options;
    }

    /**
     * 默认人物头像图片
     */
    public static ImageOptions defaultPersonHeadOptions() {
        ImageOptions options = new ImageOptions.Builder().setIgnoreGif(false)
                .setLoadingDrawableId(R.mipmap.icon_default_head)
                .setFailureDrawableId(R.mipmap.icon_default_head)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setCircular(true)
                .setUseMemCache(true).build();
        return options;
    }

    /**
     * 默认男头像图片
     */
    public static ImageOptions defaultBoyHeadOptions() {
        ImageOptions options = new ImageOptions.Builder().setIgnoreGif(false)
                .setLoadingDrawableId(R.mipmap.icon_big_head_male)
                .setFailureDrawableId(R.mipmap.icon_big_head_male)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setCircular(true)
                .setUseMemCache(true).build();
        return options;
    }

    /**
     * 默认女头像图片
     */
    public static ImageOptions defaultGirlHeadOptions() {
        ImageOptions options = new ImageOptions.Builder().setIgnoreGif(false)
                .setLoadingDrawableId(R.mipmap.icon_big_head_female)
                .setFailureDrawableId(R.mipmap.icon_big_head_female)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setCircular(true)
                .setUseMemCache(true).build();
        return options;
    }

    /**
     * 通过uri获取图片的绝对路径例:从相册中选择图片的情况下
     *
     * @param uri
     * @param context
     * @return
     */
    public static String getPath(Uri uri, Context context) {
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, filePathColumn, null, null, null);
        if (cursor == null) {
            return "";
        }
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }

    /**
     * 图片按比例大小压缩方法
     *
     * @param srcPath 图片路径
     * @param imH     图片高度
     * @param imW     图片宽度
     * @return
     */
    public static Bitmap compressImage(String srcPath, float imH, float imW) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = imH;// 这里设置高度为800f
        float ww = imW;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        return bitmap;
    }

    /**
     * 图片按比例大小压缩方法
     *
     * @param image 需要压缩的图片
     * @param size  目标图片大小
     * @param imW   图片宽度
     * @param imH   图片高度
     * @return 返回压缩后的图片
     */
    public static Bitmap compressImage(Bitmap image, int size, float imW,
                                       float imH) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        while (baos.toByteArray().length / 1024 > size && options > 0) {// 判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 把压缩后的数据存放到baos中
            options -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = imH;// 这里设置高度为800f
        float ww = imW;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return bitmap;
    }

    /**
     * 压缩图片(message.what=101)
     *
     * @param photoPath 图片本地址
     * @param handler   回调压缩结果
     */
    public static void compressSave(final String photoPath, final Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap tempBitmap = compressImage(photoPath, 800f, 480f);
                Bitmap bitmapCompress = ImageUtils.compressImage(tempBitmap,
                        Define.IMAGE_SIZE, 800f, 480f);
                String tempPath = Define.APP_CACHE + DateUtil.getImageName();// 临时文件名
                boolean b = SDCardUtils.writeSDCard(bitmapCompress, tempPath);
                if (b) {
                    Message message = new Message();
                    message.what = Define.COMPRESS_TAG;
                    message.obj = tempPath;
                    handler.sendMessage(message);
                }
            }
        }).start();
    }

}
