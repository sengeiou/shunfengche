package com.windmillsteward.jukutech.customview;

/**
 * 描述：
 * author:cyq
 * 2018-06-08
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

public class GradientTextView extends TextView {

    private LinearGradient mLinearGradient;
    private Paint mPaint;
    private int mViewWidth = 0;//文字的宽度
    private int mViewHeight = 0;//文字的高度
    private Rect mTextBound = new Rect();
    private int[] mColorList;//存放颜色的数组
    private boolean isVertrial;//默认是横向

    public GradientTextView(Context context) {
        this(context, null);
    }

    public GradientTextView(Context context,
                            AttributeSet attrs) {
        super(context, attrs);
        //设置默认的颜色
        mColorList = new int[]{0xFFffff40, 0xFFff6027};
    }


    @Override
    protected void onDraw(Canvas canvas) {

        if (isVertrial) {
            mViewHeight = getMeasuredHeight();
        } else {
            mViewWidth = getMeasuredWidth();
        }
        mPaint = getPaint();
        String mTipText = getText().toString();
        mPaint.getTextBounds(mTipText, 0, mTipText.length(), mTextBound);
        //前面4个参数分别表示渐变的开始x轴,开始y轴,结束的x轴,结束的y轴,mcolorList表示渐变的颜色数组
        mLinearGradient = new LinearGradient(0, 0, mViewWidth, mViewHeight, mColorList, null, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
        //画出文字
        canvas.drawText(mTipText, getMeasuredWidth() / 2 - mTextBound.width() / 2, getMeasuredHeight() / 2 + mTextBound.height() / 2, mPaint);
    }

    /**
     * true表示纵向渐变,false变身横向渐变
     *
     * @param vertrial
     */
    public void setVertrial(boolean vertrial) {
        isVertrial = vertrial;
    }

    /**
     * 设置渐变的颜色
     *
     * @param mColorList
     */
    public void setmColorList(int[] mColorList) {
        if (mColorList != null && mColorList.length < 2) {
            throw new RuntimeException("mClorList's length must be > 2");
        } else {

            this.mColorList = mColorList;
        }
    }
}