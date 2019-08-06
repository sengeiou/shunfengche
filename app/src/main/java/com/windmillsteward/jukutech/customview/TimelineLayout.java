package com.windmillsteward.jukutech.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.Arrays;

/**
 * 描述：时间轴
 * 时间：2018/1/10
 * 作者：xjh
 */

public class TimelineLayout extends LinearLayout {

    private Paint paint;
    private int pointX;
    private int cricyR;


    public TimelineLayout(Context context) {
        this(context, null);
    }

    public TimelineLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TimelineLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setWillNotDraw(false);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GRAY);
        pointX = GraphicUtil.dp2px(getContext(),15);
        cricyR = GraphicUtil.dp2px(getContext(),3);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (getChildCount()==1) {
            LayoutParams layoutParams = (LayoutParams)getChildAt(0).getLayoutParams();
            layoutParams.leftMargin= GraphicUtil.dp2px(getContext(),15);
            layoutParams.rightMargin = GraphicUtil.dp2px(getContext(),15);
        } else if (getChildCount()>1){
            for (int i = 0; i < getChildCount(); i++) {
                LayoutParams layoutParams = (LayoutParams)getChildAt(i).getLayoutParams();
                layoutParams.leftMargin= GraphicUtil.dp2px(getContext(),25);
                layoutParams.rightMargin = GraphicUtil.dp2px(getContext(),15);
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int childCount = getChildCount();
        if (childCount==1) {
            return;
        }

        int h=0;
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            int cH = child.getHeight();

            h = i*cH;

            View time= child.findViewById(R.id.timeline_point);

            int bottom = time.getBottom();
            int cY = bottom / 2 + GraphicUtil.dipToPixels(getContext(),11);


            canvas.drawCircle(pointX,cY + h,cricyR,paint);

            canvas.drawLine(pointX,cY,pointX,cY+h,paint);
        }

    }
}
