package com.windmillsteward.jukutech.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.windmillsteward.jukutech.R;


/**
 * 字母滑动索引 2016
 *
 * @author cyq
 */
public class AssortView extends Button {

    private float density;
    private Context context;
    private final int TEXT_SIZE_SELECT = 18;
    // 文字大小设置
    private final int TEXT_SIZE_NORMAL = 12;

    public interface OnTouchAssortListener {
        public void onTouchAssortListener(String s);

        public void onTouchAssortUP();
    }

    public AssortView(Context context) {
        super(context);
        this.context = context;
    }

    public AssortView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public AssortView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    // 分类
    private String[] assort = {"?", "#", "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};
    private Paint paint = new Paint();
    // 选择的索引
    private int selectIndex = -1;
    // 字母监听器
    private OnTouchAssortListener onTouch;

    public void setOnTouchAssortListener(OnTouchAssortListener onTouch) {
        this.onTouch = onTouch;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();
        int interval = height / assort.length;
        density = getContext().getResources().getDisplayMetrics().density;

        for (int i = 0, length = assort.length; i < length; i++) {
            // 抗锯齿
            paint.setAntiAlias(true);
            // 默认粗体
            paint.setTypeface(Typeface.MONOSPACE);
            // 蓝色字体
            paint.setColor(getResources().getColor(R.color.text_blue));
            paint.setTextSize(TEXT_SIZE_NORMAL * density);
            if (i == selectIndex) {
                // 被选择的字母改变颜色和粗体
                paint.setColor(Color.parseColor("#3399ff"));
                paint.setFakeBoldText(true);
                paint.setTextSize(TEXT_SIZE_SELECT * density);
            }

            // 计算字母的X坐标
            float xPos = width / 2 - paint.measureText(assort[i]) / 2;
            // 计算字母的Y坐标
            float yPos = interval * i + interval;
            canvas.drawText(assort[i], xPos, yPos, paint);
            paint.reset();
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        float y = event.getY();
        int index = (int) (y / getHeight() * assort.length);
        if (index >= 0 && index < assort.length) {

            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    // 如果滑动改变
                    if (selectIndex != index) {
                        selectIndex = index;
                        if (onTouch != null) {
                            onTouch.onTouchAssortListener(assort[selectIndex]);
                        }

                    }
                    break;
                case MotionEvent.ACTION_DOWN:
                    selectIndex = index;
                    if (onTouch != null) {
                        onTouch.onTouchAssortListener(assort[selectIndex]);
                    }

                    break;
                case MotionEvent.ACTION_UP:
                    if (onTouch != null) {
                        onTouch.onTouchAssortUP();
                    }
                    selectIndex = -1;
                    break;
            }
        } else {
            selectIndex = -1;
            if (onTouch != null) {
                onTouch.onTouchAssortUP();
            }
        }
        invalidate();

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
