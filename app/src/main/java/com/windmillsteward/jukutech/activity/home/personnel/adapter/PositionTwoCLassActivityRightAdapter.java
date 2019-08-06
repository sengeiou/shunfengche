package com.windmillsteward.jukutech.activity.home.personnel.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.model.WorkTypeModel;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.PositionClassBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/5/16/016
 * 作者：xjh
 */
public class PositionTwoCLassActivityRightAdapter extends BaseQuickAdapter<WorkTypeModel.ListBean,BaseViewHolder> {


    public PositionTwoCLassActivityRightAdapter(@Nullable List<WorkTypeModel.ListBean> data) {
        super(R.layout.item_position_class_right, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkTypeModel.ListBean item) {
        helper.setText(R.id.tv_text,item.getJob_class_id_two_name());
        LinearLayout lay_ll_select = (LinearLayout) helper.getView(R.id.lay_ll_select);
        ImageView iv_select = (ImageView) helper.getView(R.id.iv_select);
        if (item.isSelect()){
            iv_select.setImageResource(R.mipmap.regist_front);
        }else{
            iv_select.setImageDrawable(null);
        }

    }
}
