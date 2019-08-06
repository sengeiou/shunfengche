package com.windmillsteward.jukutech.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.util.Linkify;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.windmillsteward.jukutech.R;

/**
 * @date: on 2018/10/6
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 数字指示器
 */
public class NumberIndicatorView extends LinearLayout {

    public NumberIndicatorView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_number_indicator, this);
    }

    public NumberIndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NumberIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
}
