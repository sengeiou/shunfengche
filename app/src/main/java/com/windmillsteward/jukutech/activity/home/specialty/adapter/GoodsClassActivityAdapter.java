package com.windmillsteward.jukutech.activity.home.specialty.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.GoodsClassListBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/4/23/023
 * 作者：xjh
 */
public class GoodsClassActivityAdapter extends BaseQuickAdapter<GoodsClassListBean,BaseViewHolder> {


    public GoodsClassActivityAdapter(@Nullable List<GoodsClassListBean> data) {
        super(R.layout.item_goodsclass_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsClassListBean item) {
        if(item!=null) {
            helper.setText(R.id.tv_text,item.getCommodity_category_name());
            if (helper.getAdapterPosition()==0) {
                helper.getView(R.id.v_line).setVisibility(View.VISIBLE);
            } else {
                helper.getView(R.id.v_line).setVisibility(View.GONE);
            }
        }
    }
}
