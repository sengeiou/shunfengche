package com.windmillsteward.jukutech.activity.home.specialty.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.SpecialtyHomeRecommendBean;
import com.windmillsteward.jukutech.customview.GradientTextView;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.List;
import java.util.Random;

/**
 * 描述：特产推荐列表主item
 * 时间：2018/7/10/
 * 作者：cyq
 */
public class SpecialtyRecommendGroupAdapter extends BaseQuickAdapter<SpecialtyHomeRecommendBean, BaseViewHolder> {

    private SpecialtyHomeAdapter.RecyclerViewDataCallBack mDataCallBack;
    private Context context;


    public SpecialtyRecommendGroupAdapter(Context context,List<SpecialtyHomeRecommendBean> list,SpecialtyHomeAdapter.RecyclerViewDataCallBack mDataCallBack) {
        super(R.layout.specialty_home_recommend_group, list);
        this.mDataCallBack = mDataCallBack;
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final SpecialtyHomeRecommendBean item) {
        if (item != null) {
            Random rand = new Random();
            int random = rand.nextInt(3);
            GradientTextView tv_title = (GradientTextView) helper.getView(R.id.tv_title);
            ImageView iv_bg = (ImageView) helper.getView(R.id.iv_bg);
            ImageView iv_banner = (ImageView) helper.getView(R.id.iv_banner);
            TextView tv_more = (TextView) helper.getView(R.id.tv_more);
            RecyclerView mRecyclerView = (RecyclerView) helper.getView(R.id.mRecyclerView);

            if (random == 0) {
                tv_title.setmColorList(new int[]{0xb0f451, 0xFFffd229, 0xFFfd7c00});
                iv_bg.setImageResource(R.mipmap.icon_home_title2);
            } else if (random == 1) {
                tv_title.setmColorList(new int[]{0xFF08e0f9, 0xFF78c800, 0xFFfd7c00});
                iv_bg.setImageResource(R.mipmap.icon_home_title);
            } else if (random == 2) {
                tv_title.setmColorList(new int[]{0xFFdde742, 0xFFffa200, 0xFF77c600});
                iv_bg.setImageResource(R.mipmap.icon_home_title3);
            } else if (random == 3) {
                tv_title.setmColorList(new int[]{0xFF09f08e, 0xFF00cfd7, 0xFF00878c});
                iv_bg.setImageResource(R.mipmap.icon_home_title);
            }
            tv_title.setText(item.getName());
            tv_title.setVertrial(false);


            ViewGroup.LayoutParams layoutParams = iv_banner.getLayoutParams();
            int screenWH = GraphicUtil.getScreenWH((Activity)context, GraphicUtil.TAG_WIDTH);
            layoutParams.width = screenWH;
            layoutParams.height = 275 * layoutParams.width / 1010;
            iv_banner.setLayoutParams(layoutParams);
            GlideUtil.show(context,item.getClass_banner(),iv_banner,R.mipmap.icon_default_pic);
            int position = helper.getAdapterPosition() - getHeaderLayoutCount();
            List<SpecialtyHomeRecommendBean.ListBean> recommend_list = item.getRecommend_list();
            SpecialtyRecommendChildAdapter adapter = new SpecialtyRecommendChildAdapter(position,recommend_list,context);
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

            tv_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDataCallBack.setOnRecommendMoreClick(item);
                }
            });
        }
    }
}
