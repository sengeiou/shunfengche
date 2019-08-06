package com.windmillsteward.jukutech.activity.home.personnel.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.model.WorkTypeModel;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;

import java.util.List;

/**
 * 描述：
 * 时间：2018/5/16/016
 * 作者：xjh
 */
public class PositionTwoCLassActivityLeftAdapter extends BaseQuickAdapter<WorkTypeModel,BaseViewHolder> {


    public PositionTwoCLassActivityLeftAdapter(@Nullable List<WorkTypeModel> data) {
        super(R.layout.item_position_class_left, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkTypeModel item) {
        helper.setText(R.id.tv_text,item.getJob_class_id_one_name());
        TextView view = helper.getView(R.id.tv_text);
        ImageView iv_select = helper.getView(R.id.iv_select);
        LinearLayout lay_ll_select = helper.getView(R.id.lay_ll_select);
        if (item.isSelect()) {
            view.setTextColor(ContextCompat.getColor(mContext,R.color.text_blue));
            view.setBackgroundColor(ContextCompat.getColor(mContext,R.color.color_white));
            lay_ll_select.setBackgroundColor(ContextCompat.getColor(mContext,R.color.color_white));
                iv_select.setImageResource(R.mipmap.icon_arrow_right_solid_black);
        } else {
            view.setTextColor(ContextCompat.getColor(mContext,R.color.text_color_black));
            view.setBackgroundColor(ContextCompat.getColor(mContext,R.color.color_f3f4f6));
            lay_ll_select.setBackgroundColor(ContextCompat.getColor(mContext,R.color.color_f3f4f6));
            if (item.isHaveOneSelect()){
                iv_select.setImageResource(R.mipmap.regist_front);
            }else{
                iv_select.setImageResource(R.mipmap.icon_arrow_right_solid_black);
            }
        }

    }
}
