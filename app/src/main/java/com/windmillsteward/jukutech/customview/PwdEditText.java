package com.windmillsteward.jukutech.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.widget.EditText;

import com.windmillsteward.jukutech.R;

/**
 * Created by xing on 2017/5/13.
 */

public class PwdEditText extends EditText {

    private final int STYLE_RECTANGLE = 0;

    private final int STYLE_ROUND_RECTANGLE = 1;

    private final int DEFAULT_STYLE = STYLE_RECTANGLE;

    private final int DEFAULT_PWD_COUNT = 6;

    private final float DEFAULT_STROKE_RADIUS = dp2Px(6);

    private final float DEFAULT_STROKE_WIDTH = dp2Px(1);

    private final int DEFAULT_STROKE_COLOR = Color.parseColor("#737373");

    private final int DEFAULT_DOT_COLOR = Color.BLACK;

    private final float DEFAULT_DOT_RADIUS = dp2Px(4);

    private int style;   // 控件的样式，矩形或圆角矩形

    private float strokeRadius;  // 边框圆角的半径

    private float strokeWidth;   // 边框宽度

    private int strokeColor;  // 边框颜色

    private int pwdDotColor;  // 密码圆点颜色

    private float pwdDotRadius;  // 密码圆点半径

    private int mWidth;  // 控件宽度

    private int mHeight;  // 控件高度

    private Paint strokePaint;    // 绘制边框paint

    private Paint pwdDotPaint;    // 绘制密码圆点paint

    private int mCount;   // 密码框个数

    private float cellWidth;   // 每个密码框的宽度

    private float halfStrokeWidth;

    private int mCurInputCount;  // 当前输入字符个数


    public PwdEditText(Context context) {
        this(context, null);
        Log.i("debug", "one params");
    }

    /**
     * 无论xml布局文件中有没有写自定义属性，都调用两个参数的构造函数
     */
    public PwdEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("debug", "two params");
        initAttrs(context, attrs);
        init();
    }

    /**
     * 当有自定义的样式时，调用三个参数的构造函数
     */
    public PwdEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i("debug", "three params");
    }

    /**
     * 初始化自定义属性
     */
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PwdEditText);
        style = typedArray.getInt(R.styleable.PwdEditText_style, DEFAULT_STYLE);
        mCount = typedArray.getInt(R.styleable.PwdEditText_pwdCount, DEFAULT_PWD_COUNT);
        strokeColor = typedArray.getColor(R.styleable.PwdEditText_strokeColor, DEFAULT_STROKE_COLOR);
        strokeWidth = typedArray.getDimension(R.styleable.PwdEditText_strokeWidth, DEFAULT_STROKE_WIDTH);
        strokeRadius = typedArray.getDimension(R.styleable.PwdEditText_strokeRadius, DEFAULT_STROKE_RADIUS);
        pwdDotColor = typedArray.getColor(R.styleable.PwdEditText_dotColor, DEFAULT_DOT_COLOR);
        pwdDotRadius = typedArray.getDimension(R.styleable.PwdEditText_dotRadius, DEFAULT_DOT_RADIUS);
        typedArray.recycle();
        setInputTypeToNumber();
    }

    public void setInputTypeToNumber(){
        setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    /**
     * 初始化操作
     */
    private void init() {
        // 初始化边框画笔
        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        strokePaint.setColor(strokeColor);
        strokePaint.setStrokeWidth(strokeWidth);
        strokePaint.setStyle(Paint.Style.STROKE);

        // 初始化圆点画笔
        pwdDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pwdDotPaint.setStyle(Paint.Style.FILL);
        pwdDotPaint.setColor(pwdDotColor);

        halfStrokeWidth = strokeWidth / 2;

        // 设置光标不可见
        setCursorVisible(false);
        // 设置限定最大长度
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(mCount)});
        // 设置无背景
//        setBackgroundColor(getResources().getColor(android.R.color.transparent));

        setBackgroundColor(Color.WHITE);

        setMaxLines(1);

        setFocusable(false);

        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (onTextChangedListener != null) {
                    onTextChangedListener.beforeTextChanged(s, start, count, after);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (onTextChangedListener != null) {
                    onTextChangedListener.onTextChanged(s, start, before, count);
                }
                mCurInputCount = s.toString().length();

                // 输入完成的回调
                if (mCurInputCount == mCount) {
                    if (onTextInputListener != null) {
                        onTextInputListener.onComplete(s.toString());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (onTextChangedListener != null) {
                    onTextChangedListener.afterTextChanged(s);
                }
            }
        });

        setInputTypeToNumber();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        cellWidth = (mWidth - strokeWidth) / mCount;

    }


    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);   // 不需要父类的绘制方法
        drawStroke(canvas);
        drawVerticalDivder(canvas);
        drawPwdDot(canvas);

    }

    private void drawPwdDot(Canvas canvas) {
        for (int i = 1; i <= mCurInputCount; i++) {
            canvas.drawCircle(halfStrokeWidth + cellWidth / 2 + cellWidth * (i - 1), (mHeight) / 2,
                    pwdDotRadius, pwdDotPaint);
        }

    }

    // 绘制竖直方向分割线
    private void drawVerticalDivder(Canvas canvas) {
        if (mCount == 1) {
            return;
        }
        for (int i = 1; i < mCount; i++) {
            canvas.drawLine(halfStrokeWidth + cellWidth * i, halfStrokeWidth, halfStrokeWidth + cellWidth * i,
                    mHeight - halfStrokeWidth, strokePaint);
        }
    }

    private void drawStroke(Canvas canvas) {
        if (style == STYLE_RECTANGLE) {
            canvas.drawRect(halfStrokeWidth, halfStrokeWidth, mWidth - halfStrokeWidth,
                    mHeight - halfStrokeWidth, strokePaint);
        } else {
            RectF rectF = new RectF(halfStrokeWidth, halfStrokeWidth, mWidth - halfStrokeWidth,
                    mHeight - halfStrokeWidth);
            canvas.drawRoundRect(rectF, strokeRadius, strokeRadius, strokePaint);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private float dp2Px(int dpValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, getResources().getDisplayMetrics());
    }

    public interface OnTextChangedListener {
        void beforeTextChanged(CharSequence s, int start, int count, int after);

        void onTextChanged(CharSequence s, int start, int before, int count);

        void afterTextChanged(Editable s);
    }


    public interface OnTextInputListener {
        void onComplete(String result);
    }

    private OnTextInputListener onTextInputListener;

    public void setOnTextInputListener(OnTextInputListener onTextInputListener) {
        this.onTextInputListener = onTextInputListener;
    }


    private OnTextChangedListener onTextChangedListener;

    public void addTextChangedListener(OnTextChangedListener listener) {
        this.onTextChangedListener = listener;
    }


}
