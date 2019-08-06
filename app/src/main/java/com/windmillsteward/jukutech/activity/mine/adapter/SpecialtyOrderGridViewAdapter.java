package com.windmillsteward.jukutech.activity.mine.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.SpecialtyOrderListBean;
import com.windmillsteward.jukutech.utils.GlideUtil;

import java.util.List;

/**
 * 描述：
 * 时间：2018/4/15/015
 * 作者：xjh
 */
public class SpecialtyOrderGridViewAdapter extends BaseQuickAdapter<SpecialtyOrderListBean.ListBean.CommodityListBean,BaseViewHolder> {


    public SpecialtyOrderGridViewAdapter(List<SpecialtyOrderListBean.ListBean.CommodityListBean> list) {
        super(R.layout.item_specialty_order_list_gridview,list);
    }

    @Override
    protected void convert(BaseViewHolder helper, SpecialtyOrderListBean.ListBean.CommodityListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_title,item.getCommodity_title())
                    .setText(R.id.tv_price,"￥"+item.getCommodity_price())
                    .setText(R.id.tv_number,"X"+item.getCommodity_num())
                    .setText(R.id.tv_model,"型号:  "+item.getCommodity_model_name());
            ImageView iv_pic =  helper.getView(R.id.iv_pic);
            GlideUtil.show(mContext,item.getCommodity_cover_picture(),iv_pic);
        }
    }
}
