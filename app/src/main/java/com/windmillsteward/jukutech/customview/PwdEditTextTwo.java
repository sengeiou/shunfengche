package com.windmillsteward.jukutech.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.windmillsteward.jukutech.R;

/**
 * 描述：
 * author:cyq
 * 2019/4/18
 * Created by 2019 广州聚酷软件科技有限公司 All Right Reserved
 */
public class PwdEditTextTwo extends AppCompatEditText {

    /**
     * 间隔
     */
    private final int PWD_SPACING = 40;
    /**
     * 密码大小
     */
    private final int PWD_SIZE = 60;
    /**
     * 密码长度
     */
    private final int PWD_LENGTH = 4;
    private final int pwdLegth;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 宽度
     */
    private int mWidth;
    /**
     * 高度
     */
    private int mHeight;
    /**
     * 密码框
     */
    private Rect mRect;

    /**
     * 密码画笔
     */
    private Paint mPwdPaint;

    private Paint mPwdPaintText;


    /**
     * 密码框画笔
     */
    private Paint mRectPaint;
    /**
     * 输入的密码长度
     */
    private int mInputLength;

    /**
     * 输入结束监听
     */
    private OnInputFinishListener mOnInputFinishListener;
    private String text;
    private boolean isPassword=false;

    /**
     * 构造方法
     *
     * @param context
     * @param attrs
     */
    public PwdEditTextTwo(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array =context.obtainStyledAttributes(attrs
                , R.styleable.PwdEditText2);
        isPassword=array.getBoolean(R.styleable.PwdEditText2_password,false);
        pwdLegth=array.getInteger(R.styleable.PwdEditText2_length,0);
        // 初始化密码画笔
        mPwdPaint = new Paint();
        mPwdPaint.setColor(Color.parseColor("#ffa800"));
        mPwdPaint.setStyle(Paint.Style.FILL);
        mPwdPaint.setAntiAlias(true);

        // 初始化文字画笔
        mPwdPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPwdPaintText.setColor(Color.parseColor("#000000"));
        mPwdPaintText.setTextSize(60f);
        // 初始化密码框
        mRectPaint = new Paint();
        mRectPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mRectPaint.setColor(Color.parseColor("#ffffff"));
        mRectPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = getWidth();
        mHeight = getHeight();

        // 这三行代码非常关键，大家可以注释点在看看效果
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#f7f5f6"));
        canvas.drawRect(0, 0, mWidth, mHeight, paint);

        // 计算每个密码框宽度
        int rectWidth = (mWidth - PWD_SPACING * (PWD_LENGTH - 1)) / PWD_LENGTH;
        // 绘制密码框
        for (int i = 0; i < PWD_LENGTH; i++) {
            int left = (rectWidth + PWD_SPACING) * i;
            int top = 2;
            int right = left + rectWidth;
            int bottom = mHeight - top;
            mRect = new Rect(left, top, right, bottom);
            canvas.drawRect(mRect, mRectPaint);
        }

        // 绘制密码
        for (int i = 0; i < mInputLength; i++) {
            int cx = rectWidth / 2 + (rectWidth + PWD_SPACING) * i-15;
            int cy = mHeight / 2+15;
            if (!isPassword){
                String[] c=text.split("");
                canvas.drawText(c[i+1],cx, cy, mPwdPaintText);
            }else {
                canvas.drawCircle(cx, cy, PWD_SIZE, mPwdPaint);
            }

        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start,
                                 int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        this.mInputLength = text.toString().length();
        this.text=text.toString();
        invalidate();
        if (mInputLength == PWD_LENGTH && mOnInputFinishListener != null) {
            mOnInputFinishListener.onInputFinish(text.toString());
        }
    }

    public interface OnInputFinishListener {
        /**
         * 密码输入结束监听
         *
         * @param password
         */
        void onInputFinish(String password);
    }

    /**
     * 设置输入完成监听
     *
     * @param onInputFinishListener
     */
    public void setOnInputFinishListener(
            OnInputFinishListener onInputFinishListener) {
        this.mOnInputFinishListener = onInputFinishListener;
    }

}
