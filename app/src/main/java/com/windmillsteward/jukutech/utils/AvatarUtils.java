package com.windmillsteward.jukutech.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import java.util.List;

/**
 * Created by jack on 2017/10/5.
 */

public class AvatarUtils {

    private static final int WHOLE = 0;
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int LEFT_TOP = 3;
    private static final int LEFT_BOTTOM = 4;
    private static final int RIGHT_TOP = 5;
    private static final int RIGHT_BOTTOM = 6;

    private static final int marginWhiteWidth = 2; // 中间白色宽度

    private static int mWidth;
    private static int mHeight;

    public static Bitmap getAvatar(List<Object> list, int width, int height){
        mWidth = width;
        mHeight = height;
        final Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);

        Path mPath = new Path();
        mPath.addCircle(width/2, height/2, width/2, Path.Direction.CCW);
        //canvas.clipPath(mPath); //切割画布

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawPath(mPath, paint);

        final int listSize = list.size();
        switch (listSize) {
            case 1:
                if(list.get(0) instanceof Bitmap) {
                    drawBitmap(canvas, (Bitmap) list.get(0), WHOLE);
                }else if(list.get(0) instanceof String){
                    drawText(canvas, (String) list.get(0), "#ff0000", WHOLE);
                }
                break;
            case 2:
                if(list.get(0) instanceof Bitmap) {
                    drawBitmap(canvas, (Bitmap) list.get(0), LEFT);
                }else if(list.get(0) instanceof String){
                    drawText(canvas, (String) list.get(0), "#ff0000", LEFT);
                }

                if(list.get(1) instanceof Bitmap) {
                    drawBitmap(canvas, (Bitmap) list.get(1), RIGHT);
                }else if(list.get(1) instanceof String){
                    drawText(canvas, (String) list.get(1), "#00ff00", RIGHT);
                }
                break;
            case 3:
                if(list.get(0) instanceof Bitmap) {
                    drawBitmap(canvas, (Bitmap) list.get(0), LEFT);
                }else if(list.get(0) instanceof String){
                    drawText(canvas, (String) list.get(0), "#ff0000", LEFT);
                }

                if(list.get(1) instanceof Bitmap) {
                    drawBitmap(canvas, (Bitmap) list.get(1), RIGHT_TOP);
                }else if(list.get(1) instanceof String){
                    drawText(canvas, (String) list.get(1), "#00ff00", RIGHT_TOP);
                }

                if(list.get(2) instanceof Bitmap) {
                    drawBitmap(canvas, (Bitmap) list.get(2), RIGHT_BOTTOM);
                }else if(list.get(2) instanceof String){
                    drawText(canvas, (String) list.get(2), "#0000ff", RIGHT_BOTTOM);
                }
                break;
            case 4:
                if(list.get(0) instanceof Bitmap) {
                    drawBitmap(canvas, (Bitmap) list.get(0), LEFT_TOP);
                }else if(list.get(0) instanceof String){
                    drawText(canvas, (String) list.get(0), "#ff0000", LEFT_TOP);
                }

                if(list.get(1) instanceof Bitmap) {
                    drawBitmap(canvas, (Bitmap) list.get(1), LEFT_BOTTOM);
                }else if(list.get(1) instanceof String){
                    drawText(canvas, (String) list.get(1), "#00ff00", LEFT_BOTTOM);
                }

                if(list.get(2) instanceof Bitmap) {
                    drawBitmap(canvas, (Bitmap) list.get(2), RIGHT_TOP);
                }else if(list.get(2) instanceof String){
                    drawText(canvas, (String) list.get(2), "#0000ff", RIGHT_TOP);
                }

                if(list.get(3) instanceof Bitmap) {
                    drawBitmap(canvas, (Bitmap) list.get(3), RIGHT_BOTTOM);
                }else if(list.get(3) instanceof String){
                    drawText(canvas, (String) list.get(3), "#009999", RIGHT_BOTTOM);
                }
                break;
            default:
                break;
        }
        return  result;
    }

    private static void drawBitmap(Canvas canvas, Bitmap bitmap, int mode){
        int left, top;
        int x, y, width, height;
        int dstWidth, dstHeight;

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        if(mode==WHOLE) {
            // 比例缩放
            Bitmap bmp = Bitmap.createScaledBitmap(bitmap, mWidth, mHeight, false);
            canvas.drawBitmap(bmp, 0, 0, paint);
        }else if(mode==LEFT){
            dstWidth = mWidth;
            dstHeight = mHeight;

            x = mWidth / 4 + marginWhiteWidth / 2;
            y = 0;
            width = mWidth / 2 - marginWhiteWidth / 2;// 中间留有1px白条
            height = mHeight;

            left=0;
            top=0;

            // 比例缩放
            Bitmap bmp = Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight, false);
            // 裁取中间部分(从x点裁取置顶距离)
            Bitmap dstBmp = Bitmap.createBitmap(bmp, x, y, width,  height);
            // 绘图
            canvas.drawBitmap(dstBmp, left, top, paint);
        }else if(mode==RIGHT){
            dstWidth = mWidth;
            dstHeight = mHeight;

            x = mWidth / 4 + marginWhiteWidth / 2;
            y = 0;
            width = mWidth / 2 - marginWhiteWidth / 2;// 中间留有1px白条
            height = mHeight;

            left = mWidth / 2 + marginWhiteWidth / 2;
            top = 0;

            // 比例缩放
            Bitmap bmp = Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight, false);
            // 裁取中间部分(从x点裁取置顶距离)
            Bitmap dstBmp = Bitmap.createBitmap(bmp, x, y, width,  height);
            // 绘图
            canvas.drawBitmap(dstBmp, left, top, paint);
        }else if(mode==LEFT_TOP){
            dstWidth = mWidth/2 - marginWhiteWidth / 2;
            dstHeight = mHeight/2 - marginWhiteWidth / 2;

            left = 0;
            top = 0;

            // 比例缩放
            Bitmap bmp = Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight, false);
            // 绘图
            canvas.drawBitmap(bmp, left, top, paint);
        }else if(mode==LEFT_BOTTOM){
            dstWidth = mWidth/2 - marginWhiteWidth / 2;
            dstHeight = mHeight/2 - marginWhiteWidth / 2;

            left = 0;
            top = mHeight/2 + marginWhiteWidth / 2;

            // 比例缩放
            Bitmap bmp = Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight, false);
            // 绘图
            canvas.drawBitmap(bmp, left, top, paint);
        }else if(mode==RIGHT_TOP){
            dstWidth = mWidth/2 - marginWhiteWidth / 2;
            dstHeight = mHeight/2 - marginWhiteWidth / 2;

            left = mWidth/2 + marginWhiteWidth / 2;
            top = 0;

            // 比例缩放
            Bitmap bmp = Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight, false);
            // 绘图
            canvas.drawBitmap(bmp, left, top, paint);
        }else if(mode==RIGHT_BOTTOM){
            dstWidth = mWidth/2 - marginWhiteWidth / 2;
            dstHeight = mHeight/2 - marginWhiteWidth / 2;

            left = mWidth/2 + marginWhiteWidth / 2;
            top = mHeight/2 + marginWhiteWidth / 2;

            // 比例缩放
            Bitmap bmp = Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight, false);
            // 绘图
            canvas.drawBitmap(bmp, left, top, paint);
        }
    }

    private static void drawText(Canvas canvas, String text, String bgColer, int mode){
        float bgLeft=0, bgTop=0, bgRight=0, bgBottom=0;
        float textSize = 50;
        float x=0, y=0;

        Paint textBgPaint = new Paint();
        textBgPaint.setColor(Color.parseColor(bgColer));
        textBgPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        if(mode==WHOLE){
            bgLeft = 0;
            bgTop = 0;
            bgRight = mWidth;
            bgBottom = mHeight;
            textSize = mWidth/2;
        }else if(mode==LEFT){
            bgLeft = 0;
            bgTop = 0;
            bgRight = mWidth/2 - marginWhiteWidth / 2;
            bgBottom = mHeight;
            textSize = mWidth/4;
        }else if(mode==RIGHT){
            bgLeft = mWidth/2 + marginWhiteWidth / 2;
            bgTop = 0;
            bgRight = mWidth;
            bgBottom = mHeight;
            textSize = mWidth/4;
        }else if(mode==LEFT_TOP){
            bgLeft = 0;
            bgTop = 0;
            bgRight = mWidth/2 - marginWhiteWidth / 2;
            bgBottom = mHeight/2 - marginWhiteWidth / 2;
            textSize = mWidth/4;
        }else if(mode==LEFT_BOTTOM){
            bgLeft = 0;
            bgTop = mHeight/2 + marginWhiteWidth / 2;
            bgRight = mWidth/2 - marginWhiteWidth / 2;
            bgBottom = mHeight;
            textSize = mWidth/4;
        }else if(mode==RIGHT_TOP){
            bgLeft = mWidth/2 + marginWhiteWidth / 2;
            bgTop = 0;
            bgRight = mWidth;
            bgBottom = mHeight/2 - marginWhiteWidth / 2;
            textSize = mWidth/4;
        }else if(mode==RIGHT_BOTTOM){
            bgLeft = mWidth/2 + marginWhiteWidth / 2;
            bgTop = mHeight/2 + marginWhiteWidth / 2;
            bgRight = mWidth;
            bgBottom = mHeight;
            textSize = mWidth/4;
        }
        canvas.drawRect(bgLeft, bgTop, bgRight, bgBottom, textBgPaint);
        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(textSize);

        textPaint.setStyle(Paint.Style.FILL);
        //该方法即为设置基线上那个点究竟是left,center,还是right  这里我设置为center
        textPaint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        if(mode==WHOLE){
            x = mWidth/2;
            y = mHeight/2 - top/2 - bottom/2;//基线中间点的y轴计算公式
        }else if(mode==LEFT){
            x = mWidth/4 - marginWhiteWidth / 2;
            y = mHeight/2 - top/2 - bottom/2;//基线中间点的y轴计算公式
        }else if(mode==RIGHT){
            x = mWidth/4*3 + marginWhiteWidth / 2;
            y = mHeight/2 - top/2 - bottom/2;//基线中间点的y轴计算公式
        }else if(mode==LEFT_TOP){
            x = mWidth/4 - marginWhiteWidth / 2;
            y = mHeight/4 - top/2 - bottom/2;//基线中间点的y轴计算公式
        }else if(mode==LEFT_BOTTOM){
            x = mWidth/4 - marginWhiteWidth / 2;
            y = mHeight/4*3 - top/2 - bottom/2;//基线中间点的y轴计算公式
        }else if(mode==RIGHT_TOP){
            x = mWidth/4*3 + marginWhiteWidth / 2;
            y = mHeight/4 - top/2 - bottom/2;//基线中间点的y轴计算公式
        }else if(mode==RIGHT_BOTTOM){
            x = mWidth/4*3 + marginWhiteWidth / 2;
            y = mHeight/4*3 - top/2 - bottom/2;//基线中间点的y轴计算公式
        }
        //int baseLineY = (int) (mHeight/2 - top/2 - bottom/2);//基线中间点的y轴计算公式
        canvas.drawText(text, x, y, textPaint);
    }
}
