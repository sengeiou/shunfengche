package com.windmillsteward.jukutech.activity.classification.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.ClassificationMenuBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/2/4/004
 * 作者：xjh
 */
public class ClassificationMenuAdapter extends BaseQuickAdapter<ClassificationMenuBean,BaseViewHolder> {

    public ClassificationMenuAdapter(@Nullable List<ClassificationMenuBean> data) {
        super(R.layout.item_classificationmenu, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ClassificationMenuBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_class_menu,item.getText());
            TextView textView = helper.getView(R.id.tv_class_menu);
            LinearLayout lay_ll_line = helper.getView(R.id.lay_ll_line);
            lay_ll_line.setBackgroundColor(item.getLineColor());
            textView.setTextColor(item.getTextColor());
            textView.setBackgroundColor(item.getBackgroundColor());
        }
    }
}
