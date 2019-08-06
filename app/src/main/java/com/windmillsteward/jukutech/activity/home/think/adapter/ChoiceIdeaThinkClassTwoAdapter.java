package com.windmillsteward.jukutech.activity.home.think.adapter;

import android.support.annotation.Nullable;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.IdeaThinkClassBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/2/5/005
 * 作者：xjh
 */
public class ChoiceIdeaThinkClassTwoAdapter extends BaseQuickAdapter<IdeaThinkClassBean.SecondClassListBean,BaseViewHolder> {

    public ChoiceIdeaThinkClassTwoAdapter(@Nullable List<IdeaThinkClassBean.SecondClassListBean> data) {
        super(R.layout.item_choiceideathink, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IdeaThinkClassBean.SecondClassListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_title,item.getSecond_class_name());
        }
    }
}
