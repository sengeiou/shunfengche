package com.windmillsteward.jukutech.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * 描述：自定义横向联动ScrollView
 * author:cyq
 * 2017-11-14
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class BindableScrollView extends HorizontalScrollView {
    BindableScrollView bindedScrollView;

    public BindableScrollView(Context context) {
        super(context);
    }

    public BindableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BindableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bindView(BindableScrollView scrollView) {
        this.bindedScrollView = scrollView;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (bindedScrollView != null) {
            bindedScrollView.scrollTo(x, y);
        }
    }
}
