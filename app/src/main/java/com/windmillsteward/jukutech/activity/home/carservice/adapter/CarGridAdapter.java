package com.windmillsteward.jukutech.activity.home.carservice.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.RecommendCarListBean;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/22/022
 * 作者：xjh
 */
public class CarGridAdapter extends BaseQuickAdapter<RecommendCarListBean,BaseViewHolder> {

    private Context context;
    public CarGridAdapter(Context context,@Nullable List<RecommendCarListBean> data) {
        super(R.layout.item_car_grid, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendCarListBean item) {
        if (item!=null) {
            ImageView iv_cover = helper.getView(R.id.iv_cover);
            ViewGroup.LayoutParams layoutParams_img = iv_cover.getLayoutParams();
            int screenWH = GraphicUtil.getScreenWH((Activity) context, GraphicUtil.TAG_WIDTH);
            layoutParams_img.width = (screenWH-GraphicUtil.dp2px((Activity) context, 30))/2;
            layoutParams_img.height = layoutParams_img.width/2;
            iv_cover.setLayoutParams(layoutParams_img);

            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) helper.itemView.getLayoutParams();
            if ((helper.getAdapterPosition()-getHeaderLayoutCount())%2==0) {
                layoutParams.leftMargin = GraphicUtil.dp2px(context, 10);
                layoutParams.rightMargin = GraphicUtil.dp2px(context, 5);
                layoutParams.width = (screenWH-GraphicUtil.dp2px((Activity) context, 30))/2;
                helper.itemView.setLayoutParams(layoutParams);
            } else {
                layoutParams.leftMargin = GraphicUtil.dp2px(context, 5);
                layoutParams.rightMargin = GraphicUtil.dp2px(context, 10);
                layoutParams.width = (screenWH-GraphicUtil.dp2px((Activity) context, 30))/2;
                helper.itemView.setLayoutParams(layoutParams);
            }
            helper.setText(R.id.tv_title,item.getName())
                    .setText(R.id.tv_license_time,item.getLicense_time())
                    .setText(R.id.tv_mileage,item.getMileage()+"万公里")
                    .setText(R.id.tv_price,item.getPrice());

            GlideUtil.show(context,item.getCover_url(),iv_cover,R.mipmap.pic_list,R.mipmap.pic_list);
        }
    }
}
