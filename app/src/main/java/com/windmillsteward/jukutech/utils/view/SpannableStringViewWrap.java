package com.windmillsteward.jukutech.utils.view;

import android.text.TextUtils;
import android.widget.TextView;

import com.blankj.utilcode.util.SpanUtils;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.utils.ResUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: on 2018/10/16
 * @author: ctetin
 * @email: mxnzp_life@163.com
 * @desc: 处理第一个文字变红
 */
public class SpannableStringViewWrap {
    private List<TextView> views;

    public SpannableStringViewWrap() {
        views = new ArrayList<>();
    }

    public SpannableStringViewWrap addView(TextView view) {
        views.add(view);
        return this;
    }

    public SpannableStringViewWrap addViews(TextView... views) {
        for (TextView view : views) {
            this.views.add(view);
        }
        return this;
    }

    public void build(int start, int end) {
        for (TextView view : views) {
            String content = view.getText().toString();
            if (!TextUtils.isEmpty(content)) {
                view.setText(new SpanUtils()
                        .append(content.substring(start, end))
                        .setForegroundColor(ResUtils.getColor(R.color.color_fd4949))
                        .append(content.substring(end, content.length()))
                        .create());
            }
        }
    }

    public void build() {
        build(0, 1);
    }

}
