package com.windmillsteward.jukutech.activity.home.specialty.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyDetailActivity;
import com.windmillsteward.jukutech.bean.SpecialtyHomeRecommendBean;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.GlideUtil;

import java.util.List;

/**
 * 描述：特产推荐列表主item
 * 时间：2018/7/10/
 * 作者：cyq
 */
public class SpecialtyRecommendChildAdapter extends RecyclerView.Adapter {

    private List<SpecialtyHomeRecommendBean.ListBean> list;
    private Context context;
    private final int TOP = 100;
    private final int OTHER = 101;
    private int groupPosition;//父级下标

    private TopViewHolder topViewHolder;
    private OtherViewHolder otherViewHolder;

    public SpecialtyRecommendChildAdapter(int groupPosition, List<SpecialtyHomeRecommendBean.ListBean> list, Context context) {
        this.groupPosition = groupPosition;
        this.list = list;
        this.context = context;
    }

//    @Override
//    public int getItemViewType(int position) {
//        if (position == 0) {
//            return TOP;
//        } else {
//            return OTHER;
//        }
//
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (groupPosition == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.item_specialty_recommend_top_child, parent, false);
            topViewHolder = new TopViewHolder(view);
            return topViewHolder;
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_specialty_recommend_child, parent, false);
            otherViewHolder = new OtherViewHolder(view);
            return otherViewHolder;
        }
//        switch (viewType) {
//            case TOP:
//                view = LayoutInflater.from(context).inflate(R.layout.item_specialty_recommend_top_child, parent, false);
//                topViewHolder = new TopViewHolder(view);
//                return topViewHolder;
//            case OTHER:
//                view = LayoutInflater.from(context).inflate(R.layout.item_specialty_recommend_child, parent, false);
//                otherViewHolder = new OtherViewHolder(view);
//                return otherViewHolder;
//        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

//        if (holder.getItemViewType() == TOP){
//            topViewHolder.setTopData(list);
//        }else{
//            otherViewHolder.setOtherData(list);
//        }
        if (groupPosition == 0) {
            TopViewHolder topViewHolder = (TopViewHolder) holder;
            topViewHolder.setTopData(list);
        } else {
            OtherViewHolder otherViewHolder = (OtherViewHolder) holder;
            otherViewHolder.setOtherData(list);
        }


    }

    @Override
    public int getItemCount() {
//        return list == null ? 0 : list.size();
        return list == null ? 0 : 1;
    }

    private class TopViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_goods_title[] = new TextView[8];
        private ImageView iv_pic[] = new ImageView[8];

        public TopViewHolder(View itemView) {
            super(itemView);
            tv_goods_title[0] = (TextView) itemView.findViewById(R.id.tv_goods1_title);
            tv_goods_title[1] = (TextView) itemView.findViewById(R.id.tv_goods2_title);
            tv_goods_title[2] = (TextView) itemView.findViewById(R.id.tv_goods3_title);
            tv_goods_title[3] = (TextView) itemView.findViewById(R.id.tv_goods4_title);
            tv_goods_title[4] = (TextView) itemView.findViewById(R.id.tv_goods5_title);
            tv_goods_title[5] = (TextView) itemView.findViewById(R.id.tv_goods6_title);
            tv_goods_title[6] = (TextView) itemView.findViewById(R.id.tv_goods7_title);
            tv_goods_title[7] = (TextView) itemView.findViewById(R.id.tv_goods8_title);

            iv_pic[0] = (ImageView) itemView.findViewById(R.id.iv_pic1);
            iv_pic[1] = (ImageView) itemView.findViewById(R.id.iv_pic2);
            iv_pic[2] = (ImageView) itemView.findViewById(R.id.iv_pic3);
            iv_pic[3] = (ImageView) itemView.findViewById(R.id.iv_pic4);
            iv_pic[4] = (ImageView) itemView.findViewById(R.id.iv_pic5);
            iv_pic[5] = (ImageView) itemView.findViewById(R.id.iv_pic6);
            iv_pic[6] = (ImageView) itemView.findViewById(R.id.iv_pic7);
            iv_pic[7] = (ImageView) itemView.findViewById(R.id.iv_pic8);
        }

        public void setTopData(List<SpecialtyHomeRecommendBean.ListBean> list) {
            if (list.size() == 0 || list == null) {
                return;
            }

            int limitTop = 8;
            if (limitTop >= list.size()) {
                limitTop = list.size();
            }
            for (int i = 0; i < limitTop; i++) {
                GlideUtil.show(context,list.get(i).getCommodity_cover_picture(),iv_pic[i],R.mipmap.icon_default_pic,R.mipmap.icon_default_pic);

                if (i != 1 && i != 3) {

                    tv_goods_title[i].setText(list.get(i).getCommodity_title());

                }
                final int commodity_id = list.get(i).getCommodity_id();

                iv_pic[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, SpecialtyDetailActivity.class);
                        intent.putExtra(Define.INTENT_DATA,commodity_id);
                        context.startActivity(intent);
                    }
                });
            }
        }
    }

    private class OtherViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_goods_title[] = new TextView[8];
        private ImageView iv_pic[] = new ImageView[8];
        private RelativeLayout lay_rl_bg[] = new RelativeLayout[8];

        public OtherViewHolder(View itemView) {
            super(itemView);
            tv_goods_title[0] = (TextView) itemView.findViewById(R.id.tv_goods1_title);
            tv_goods_title[1] = (TextView) itemView.findViewById(R.id.tv_goods2_title);
            tv_goods_title[2] = (TextView) itemView.findViewById(R.id.tv_goods3_title);
            tv_goods_title[3] = (TextView) itemView.findViewById(R.id.tv_goods4_title);
            tv_goods_title[4] = (TextView) itemView.findViewById(R.id.tv_goods5_title);
            tv_goods_title[5] = (TextView) itemView.findViewById(R.id.tv_goods6_title);


            iv_pic[0] = (ImageView) itemView.findViewById(R.id.iv_pic1);
            iv_pic[1] = (ImageView) itemView.findViewById(R.id.iv_pic2);
            iv_pic[2] = (ImageView) itemView.findViewById(R.id.iv_pic3);
            iv_pic[3] = (ImageView) itemView.findViewById(R.id.iv_pic4);
            iv_pic[4] = (ImageView) itemView.findViewById(R.id.iv_pic5);
            iv_pic[5] = (ImageView) itemView.findViewById(R.id.iv_pic6);

            lay_rl_bg[0] = (RelativeLayout) itemView.findViewById(R.id.lay_rl_bg1);
            lay_rl_bg[1] = (RelativeLayout) itemView.findViewById(R.id.lay_rl_bg2);
            lay_rl_bg[2] = (RelativeLayout) itemView.findViewById(R.id.lay_rl_bg3);
            lay_rl_bg[3] = (RelativeLayout) itemView.findViewById(R.id.lay_rl_bg4);
            lay_rl_bg[4] = (RelativeLayout) itemView.findViewById(R.id.lay_rl_bg5);
            lay_rl_bg[5] = (RelativeLayout) itemView.findViewById(R.id.lay_rl_bg6);


        }

        public void setOtherData(List<SpecialtyHomeRecommendBean.ListBean> list) {
            if (list.size() == 0 || list == null) {
                return;
            }
            int limit = 6;
            if (limit >= list.size()) {
                limit = list.size();
            }
            for (int i = 0; i < limit; i++) {
                GlideUtil.show(context,list.get(i).getCommodity_cover_picture(),iv_pic[i],R.mipmap.icon_default_pic);
                final int commodity_id = list.get(i).getCommodity_id();
                tv_goods_title[i].setText(list.get(i).getCommodity_title());
                lay_rl_bg[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, SpecialtyDetailActivity.class);
                        intent.putExtra(Define.INTENT_DATA,commodity_id);
                        context.startActivity(intent);
                    }
                });
            }
        }
    }

}
