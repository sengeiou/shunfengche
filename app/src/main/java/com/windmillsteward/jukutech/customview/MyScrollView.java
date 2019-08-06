package com.windmillsteward.jukutech.customview;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

/**
 * 描述：自定义Scrollview
 * author:cyq
 * 2018-02-07
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MyScrollView extends NestedScrollView {
    private ScrollViewListener scrollViewListener = null;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int l, int y, int oldl, int oldt) {
        super.onScrollChanged(l, y, oldl, oldt);

        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(l, y, oldl, oldt);
        }
    }

    public interface ScrollViewListener{
        void onScrollChanged(int l, int y, int oldl, int oldt);
    }

}
