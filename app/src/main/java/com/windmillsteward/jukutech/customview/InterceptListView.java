package com.windmillsteward.jukutech.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * 描述：
 * author:cyq
 * 2017-11-14
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class InterceptListView extends ListView {

    public InterceptListView(Context context) {
        super(context);
    }

    public InterceptListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InterceptListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        return false;
    }
}
