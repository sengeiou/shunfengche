package com.windmillsteward.jukutech.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 描述：
 * 时间：2018/1/21/021
 * 作者：xjh
 */
public class LimitHeightListView extends ListView {

    private int limitHeight;

    public LimitHeightListView(Context context) {
        super(context);
        limitHeight = (int) ((getResources().getDisplayMetrics().heightPixels)*0.5);
    }

    public LimitHeightListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        limitHeight = (int) ((getResources().getDisplayMetrics().heightPixels)*0.5);
    }

    public LimitHeightListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        limitHeight = (int) ((getResources().getDisplayMetrics().heightPixels)*0.5);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);

        if (getMeasuredHeight()>limitHeight) {
            setMeasuredDimension(getMeasuredWidth(),limitHeight);
        }
    }
}
