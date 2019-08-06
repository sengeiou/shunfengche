package com.windmillsteward.jukutech.customview;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 描述：scrollView嵌套listview解决listview显示不全的问题
 * author:cyq
 * 2017/9/23
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MyListView extends ListView {

    public MyListView(Context context) {
        super(context);
    }
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyListView(Context context, AttributeSet attrs,
                      int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
