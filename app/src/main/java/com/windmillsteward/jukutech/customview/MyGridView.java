package com.windmillsteward.jukutech.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 描述：重写的gridView 解决scollview嵌套的时候 只显示一行的问题
 * author:cyq
 * 2017/9/28
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MyGridView extends GridView {
    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
