package com.windmillsteward.jukutech.activity.home.personnel.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.LabourPositionClassBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/8/1
 * 作者：cyq
 */
public class LabourPositionTwoCLassActivityLeftAdapter extends BaseQuickAdapter<LabourPositionClassBean,BaseViewHolder> {


    public LabourPositionTwoCLassActivityLeftAdapter(@Nullable List<LabourPositionClassBean> data) {
        super(R.layout.item_position_class_left, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LabourPositionClassBean item) {
        helper.setText(R.id.tv_text,item.getClass_name());
        TextView view = helper.getView(R.id.tv_text);
        if (item.isSelect()) {
            view.setTextColor(ContextCompat.getColor(mContext,R.color.color_23abac));
            view.setBackgroundColor(ContextCompat.getColor(mContext,R.color.color_white));
        } else {
            view.setTextColor(ContextCompat.getColor(mContext,R.color.text_color_black));
            view.setBackgroundColor(ContextCompat.getColor(mContext,R.color.color_f3f4f6));
        }
    }
}
