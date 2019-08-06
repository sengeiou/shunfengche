package com.windmillsteward.jukutech.activity.home.stayandtravel.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.TravelListBean;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.List;

/**
 * 描述：住宿旅行gridView的适配器
 * 时间：2018/1/25
 * 作者：xjh
 */

public class TravelListAdapter extends BaseQuickAdapter<TravelListBean.ListBean,BaseViewHolder> {

    private Context context;

    public TravelListAdapter(Context context, List<TravelListBean.ListBean> list) {
        super(R.layout.item_staytravelgridview, list);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, TravelListBean.ListBean item) {
        holder.setText(R.id.tv_title,item.getTitle())
                .setText(R.id.tv_area,item.getArea_name())
                .setText(R.id.tv_travel_class_name,item.getTravel_class_name())
                .setText(R.id.tv_price,"￥"+item.getStart_price());

        ImageView iv_img = (ImageView) holder.getView(R.id.iv_img);
        RelativeLayout lay_rl_top = (RelativeLayout) holder.getView(R.id.lay_rl_top);

        ViewGroup.LayoutParams layoutParams = iv_img.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH((Activity) context, GraphicUtil.TAG_WIDTH);
        layoutParams.width = (screenWH-GraphicUtil.dp2px((Activity) context, 30))/2;
        layoutParams.height = layoutParams.width/2;
        iv_img.setLayoutParams(layoutParams);

//        ViewGroup.LayoutParams layoutParams_top = lay_rl_top.getLayoutParams();
//        layoutParams.width = (screenWH-GraphicUtil.dp2px((Activity) context, 30))/2;
//        layoutParams.height = layoutParams.width/2;
//        lay_rl_top.setLayoutParams(layoutParams_top);

//        GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
//            params.width = (screenWH-GraphicUtil.dp2px((Activity) context, 30))/2;
//            holder.itemView.setLayoutParams(params);

        GlideUtil.show(context,item.getCover_url(),iv_img,R.mipmap.pic_list_set,R.mipmap.pic_list_set);
        int position = holder.getAdapterPosition()-getHeaderLayoutCount();
        if (position<0) {
            position = 0;
        }
        if (position %2==0) {
            GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            params.leftMargin = GraphicUtil.dp2px(context,10);
            params.rightMargin = GraphicUtil.dp2px(context,5);
            params.width = (screenWH-GraphicUtil.dp2px((Activity) context, 30))/2;
            holder.itemView.setLayoutParams(params);
        } else {
            GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            params.leftMargin = GraphicUtil.dp2px(context,5);
            params.rightMargin = GraphicUtil.dp2px(context,10);
            params.width = (screenWH-GraphicUtil.dp2px((Activity) context, 30))/2;
            holder.itemView.setLayoutParams(params);
        }

    }
}
