package com.windmillsteward.jukutech.activity.home.personnel.adapter;

import android.support.annotation.Nullable;

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
public class LabourPositionTwoCLassActivityRightAdapter extends BaseQuickAdapter<LabourPositionClassBean,BaseViewHolder> {


    public LabourPositionTwoCLassActivityRightAdapter(@Nullable List<LabourPositionClassBean> data) {
        super(R.layout.item_position_class_right, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LabourPositionClassBean item) {
        helper.setText(R.id.tv_text,item.getClass_name());
    }
}
