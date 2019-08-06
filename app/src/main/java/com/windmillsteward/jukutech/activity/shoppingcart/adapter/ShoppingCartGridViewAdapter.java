package com.windmillsteward.jukutech.activity.shoppingcart.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.ShoppingCarListBean;
import com.windmillsteward.jukutech.utils.GlideUtil;

import java.util.List;

/**
 * 描述：
 * 时间：2018/4/15/015
 * 作者：xjh
 */
public class ShoppingCartGridViewAdapter  extends BaseQuickAdapter<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean,BaseViewHolder> {


    public ShoppingCartGridViewAdapter(List<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean> list) {
        super(R.layout.item_shopping_cart_list_gridview,list);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_title,item.getCommodity_title())
                    .setText(R.id.tv_price,"￥"+item.getCommodity_price())
                    .setText(R.id.tv_number,"X"+item.getCommodity_num())
                    .setText(R.id.tv_model,item.getCommodity_model_name())
                    .setText(R.id.tv_number_,String.valueOf(item.getCommodity_num()))
                    .setText(R.id.tv_commodity_model,item.getCommodity_model_name())
                    .addOnClickListener(R.id.iv_choice_model)
                    .addOnClickListener(R.id.tv_commodity_model)
                    .addOnClickListener(R.id.iv_select)
                    .addOnClickListener(R.id.tv_delete)
                    .addOnClickListener(R.id.tv_reduce)
                    .addOnClickListener(R.id.tv_add);
            List<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean.CommodityModelListBean> model_list = item.getCommodity_model_list();
            ImageView iv_pic =  helper.getView(R.id.iv_pic);
            GlideUtil.show(mContext,item.getCommodity_cover_picture(),iv_pic);
            LinearLayout linear_edit = helper.getView(R.id.linear_edit);
            LinearLayout linear_normal = helper.getView(R.id.linear_normal);
            if (item.isEdit()) {
                linear_edit.setVisibility(View.VISIBLE);
                linear_normal.setVisibility(View.GONE);
            } else {
                linear_edit.setVisibility(View.GONE);
                linear_normal.setVisibility(View.VISIBLE);
            }
            ImageView iv_select =  helper.getView(R.id.iv_select);
            if (item.isSelect()) {
                iv_select.setImageResource(R.mipmap.icon_select);
            } else {
                iv_select.setImageResource(R.mipmap.icon_select_n);
            }
            TextView tv_commodity_status = helper.getView(R.id.tv_commodity_status);
            if (item.getCommodity_status()==2) {
                tv_commodity_status.setVisibility(View.GONE);
            } else {
                tv_commodity_status.setVisibility(View.VISIBLE);
            }
        }
    }
}
