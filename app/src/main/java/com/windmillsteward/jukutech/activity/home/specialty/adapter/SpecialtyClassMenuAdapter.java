package com.windmillsteward.jukutech.activity.home.specialty.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.ClassificationMenuBean;
import com.windmillsteward.jukutech.bean.SpecialtyClassBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/2/4/004
 * 作者：xjh
 */
public class SpecialtyClassMenuAdapter extends BaseQuickAdapter<SpecialtyClassBean,BaseViewHolder> {

    public SpecialtyClassMenuAdapter(@Nullable List<SpecialtyClassBean> data) {
        super(R.layout.item_classificationmenu, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SpecialtyClassBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_class_menu,item.getName());
            TextView textView = helper.getView(R.id.tv_class_menu);
            textView.setTextColor(item.getTextColor());
            textView.setBackgroundColor(item.getBackgroundColor());
        }
    }
}
