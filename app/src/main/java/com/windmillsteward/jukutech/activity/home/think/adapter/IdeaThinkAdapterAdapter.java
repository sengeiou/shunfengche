package com.windmillsteward.jukutech.activity.home.think.adapter;

import android.support.annotation.Nullable;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.IdeaThinkListBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/2/4/004
 * 作者：xjh
 */
public class IdeaThinkAdapterAdapter extends BaseQuickAdapter<IdeaThinkListBean.ListBean,BaseViewHolder> {

    public IdeaThinkAdapterAdapter(@Nullable List<IdeaThinkListBean.ListBean> data) {
        super(R.layout.item_wisdomfirecontrol, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IdeaThinkListBean.ListBean item) {
        if(item!=null) {
            helper.setText(R.id.tv_title,item.getTitle())
                    .setText(R.id.tv_area,item.getSecond_area_name() + item.getThird_area_name())
                    .setText(R.id.tv_class,item.getConsumption_type_name());
//                    .setText(R.id.tv_price,"￥"+item.getPrice());
        }
    }
}
