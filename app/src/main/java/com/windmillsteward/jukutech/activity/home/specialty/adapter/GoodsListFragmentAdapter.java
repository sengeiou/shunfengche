package com.windmillsteward.jukutech.activity.home.specialty.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.GoodsListBean;
import com.windmillsteward.jukutech.bean.SpecialtyListBean;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class GoodsListFragmentAdapter extends BaseQuickAdapter<GoodsListBean.ListBean,BaseViewHolder> {



    public GoodsListFragmentAdapter(@Nullable List<GoodsListBean.ListBean> data) {
        super(R.layout.item_goods_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsListBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_title,item.getCommodity_title())
                    .setText(R.id.tv_price,"￥"+item.getCommodity_reserve_price())
                    .setText(R.id.tv_commodity_sales_volume,item.getCommodity_sales_volume()+"");

            ImageView imageView = (ImageView) helper.getView(R.id.iv_image);
            GlideUtil.show(mContext,item.getCommodity_cover_picture(),imageView,R.mipmap.icon_default_pic,R.mipmap.icon_default_pic);

            // 动态设置宽度和高度问题
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            int screenWH = GraphicUtil.getScreenWH(((Activity) mContext), GraphicUtil.TAG_WIDTH);
            layoutParams.height = screenWH/2 - GraphicUtil.dp2px(mContext,5);
            layoutParams.width = screenWH/2 - GraphicUtil.dp2px(mContext,5);
            imageView.setLayoutParams(layoutParams);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) helper.itemView.getLayoutParams();
            params.topMargin = GraphicUtil.dp2px(mContext,10);
            if ((helper.getAdapterPosition()-getHeaderLayoutCount())%2==0) {
                params.rightMargin = GraphicUtil.dp2px(mContext,5);
            } else {
                params.leftMargin = GraphicUtil.dp2px(mContext,5);
            }
        }
    }
}
