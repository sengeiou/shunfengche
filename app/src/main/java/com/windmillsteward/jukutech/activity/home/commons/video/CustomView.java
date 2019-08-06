package com.windmillsteward.jukutech.activity.home.commons.video;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.utils.GraphicUtil;

/**
 *  模仿微信视频录制的按钮
 *  Created by Administrator on 2018/1/7/007.
 */

public class CustomView extends View {

    /**
     * 画笔
     */
    private Paint paint;
    private Paint paint1;
    /**
     * 中间圆的颜色
     */
    private int circleColor;


    /**
     * 圆环的宽度
     */
    private float roundWidth;

    /**
     * 时间，默认十秒
     */
    private int max=10;

    private int roundColor;

    private ValueAnimator animator;
    private int currProgress;
    private RectF oval;

    public CustomView(Context context) {
        this(context, null);

    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint();
        paint1 = new Paint();
        paint1.setStyle(Paint.Style.STROKE);

        @SuppressLint("CustomViewStyleable") TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);

        //获取自定义属性和默认值
        // 中间圆的颜色
        circleColor = mTypedArray.getColor(R.styleable.RoundProgressBar_circleColor, Color.argb(150,255,255,255));
        roundColor = mTypedArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.argb(200,50,50,50));
        roundWidth = mTypedArray.getDimension(R.styleable.RoundProgressBar_roundWidth, 0);

        mTypedArray.recycle();

        animator = ValueAnimator.ofInt(0,360);
        animator.setDuration(max*1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currProgress = ((int) animation.getAnimatedValue());

                postInvalidate();

                if (progressListener!=null) {
                    progressListener.onProgress((currProgress*max*1000)/360000);
                }
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);

                if (progressListener!=null) {
                    progressListener.onStart();
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                if (progressListener!=null) {
                    progressListener.onEnd((currProgress*max*1000)/360000);
                }
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // TODO: 2018/1/7/007 宽高写死吧
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int centre = getWidth() / 2; //获取圆心的x坐标
        if (roundWidth==0) {
            roundWidth = centre/9;
        }
        int radius = (int) (centre - roundWidth / 2); //圆环的半径
        oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);  //用于定义的圆弧的形状和大小的界限
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
         * 画圆
         */
        int centre = getWidth() / 2; //获取圆心的x坐标
        int radius = (int) (centre - roundWidth / 2); //圆环的半径
        paint.setColor(circleColor); //设置圆环的颜色
        paint.setStyle(Paint.Style.FILL); //设置空心
        paint.setAntiAlias(true);  //消除锯齿
        canvas.drawCircle(centre, centre, radius, paint); //画出圆

        // 画缺口标记
        paint1.setColor(roundColor);
        paint1.setStrokeWidth(roundWidth); //设置圆环的宽度
        canvas.drawArc(oval,10,15,false,paint1);

        //设置进度是实心还是空心
        paint.setStrokeWidth(roundWidth); //设置圆环的宽度
        paint.setColor(roundColor);  //设置进度的颜色
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(oval, -90, currProgress, false, paint);  //根据进度画圆弧
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (animator.isStarted()) {
                    animator.cancel();
                }
                animator.start();
                break;
            case MotionEvent.ACTION_CANCEL:
                animator.pause();
                animator.cancel();
                break;
            case MotionEvent.ACTION_UP:
                animator.pause();
                animator.cancel();

                break;
            default:
                break;
        }
        return true;
    }

    public interface OnProgressListener {

        void onStart();

        /**
         * 进度回调，返回多少秒
         * @param progress 进度 单位秒
         */
        void onProgress(int progress);

        /**
         * 设置时长，单位秒
         * @param totalTime 时长，单位秒
         */
        void onEnd(int totalTime);
    }

    private OnProgressListener progressListener;

    /**
     * 设置监听
     * @param progressListener {@link #progressListener}
     */
    public void setProgressListener(OnProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    /**
     * 设置最大录制时间
     * @param max 秒
     */
    public void setMax(int max) {
        this.max = max;
        animator.setDuration(max*1000);
    }
}
