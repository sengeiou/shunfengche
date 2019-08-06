package com.windmillsteward.jukutech.activity.home.specialty.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.SpecialtyHomeRecommendBean;
import com.windmillsteward.jukutech.customview.GradientTextView;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.List;
import java.util.Random;

/**
 * 描述：名优特产首页推荐
 * 时间：2018年6月8日
 * 作者：cyq
 */
public class SpecialtyHomeRecommendGridViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<SpecialtyHomeRecommendBean> list;
    private SpecialtyHomeAdapter.RecyclerViewDataCallBack mDataCallBack;

    private final int TOP_VIEW_TYPE = 1;
    private final int VIEW_TYPE = 2;


    public SpecialtyHomeRecommendGridViewAdapter(Context context, List<SpecialtyHomeRecommendBean> list, SpecialtyHomeAdapter.RecyclerViewDataCallBack mDataCallBack) {
        this.context = context;
        this.list = list;
        this.mDataCallBack = mDataCallBack;
    }


    @Override
    public int getGroupCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
//        return list.get(groupPosition).getRecommend_list() == null ? 0 : list.get(groupPosition).getRecommend_list().size();
        return 1;
    }


    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getRecommend_list().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder = null;
        if (convertView == null) {
            holder = new GroupHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_specialty_recommend_grid_group, parent, false);
            holder.tv_title = (GradientTextView) convertView.findViewById(R.id.tv_title);
            holder.tv_more = (TextView) convertView.findViewById(R.id.tv_more);
            holder.iv_bg = (ImageView) convertView.findViewById(R.id.iv_bg);
            holder.iv_banner = (ImageView) convertView.findViewById(R.id.iv_banner);
            holder.lay_rl_bg = (RelativeLayout) convertView.findViewById(R.id.lay_rl_bg);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }

        Random rand = new Random();
        int random = rand.nextInt(3);
        if (random == 0) {
            holder.tv_title.setmColorList(new int[]{0xb0f451, 0xFFffd229, 0xFFfd7c00});
            holder.iv_bg.setImageResource(R.mipmap.icon_home_title2);
        } else if (random == 1) {
            holder.tv_title.setmColorList(new int[]{0xFF08e0f9, 0xFF78c800, 0xFFfd7c00});
            holder.iv_bg.setImageResource(R.mipmap.icon_home_title);
        } else if (random == 2) {
            holder.tv_title.setmColorList(new int[]{0xFFdde742, 0xFFffa200, 0xFF77c600});
            holder.iv_bg.setImageResource(R.mipmap.icon_home_title3);
        } else if (random == 3) {
            holder.tv_title.setmColorList(new int[]{0xFF09f08e, 0xFF00cfd7, 0xFF00878c});
            holder.iv_bg.setImageResource(R.mipmap.icon_home_title);
        }
        holder.tv_title.setText(list.get(groupPosition).getName());
        holder.tv_title.setVertrial(false);


        ViewGroup.LayoutParams layoutParams = holder.iv_banner.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH((Activity) context, GraphicUtil.TAG_WIDTH);
        layoutParams.width = screenWH;
        layoutParams.height = 275 * layoutParams.width / 1010;
        holder.iv_banner.setLayoutParams(layoutParams);
        GlideUtil.show(context,list.get(groupPosition).getClass_banner(),holder.iv_banner,R.mipmap.icon_default_pic,R.mipmap.icon_default_pic);

        final SpecialtyHomeRecommendBean recommendGridBean = list.get(groupPosition);
        holder.tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataCallBack.setOnRecommendMoreClick(recommendGridBean);
            }
        });
        return convertView;
    }


    public int getItemViewType(int groupPosition) {
        if (groupPosition == 0) {
            return TOP_VIEW_TYPE;
        } else {
            return VIEW_TYPE;
//            return TOP_VIEW_TYPE;
        }
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        List<SpecialtyHomeRecommendBean.ListBean> recommend_list = list.get(groupPosition).getRecommend_list();

        ChildHolder topChildHolder = null;
        ChildHolder holder = null;
        int type = getItemViewType(groupPosition);
        if (convertView == null) {
            switch (type) {
                case TOP_VIEW_TYPE:
                    topChildHolder = new ChildHolder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_specialty_recommend_top_child, parent, false);
                    topChildHolder.tv_goods_title[0] = (TextView) convertView.findViewById(R.id.tv_goods1_title);
                    topChildHolder.tv_goods_title[1] = (TextView) convertView.findViewById(R.id.tv_goods2_title);
                    topChildHolder.tv_goods_title[2] = (TextView) convertView.findViewById(R.id.tv_goods3_title);
                    topChildHolder.tv_goods_title[3] = (TextView) convertView.findViewById(R.id.tv_goods4_title);
                    topChildHolder.tv_goods_title[4] = (TextView) convertView.findViewById(R.id.tv_goods5_title);
                    topChildHolder.tv_goods_title[5] = (TextView) convertView.findViewById(R.id.tv_goods6_title);
                    topChildHolder.tv_goods_title[6] = (TextView) convertView.findViewById(R.id.tv_goods7_title);
                    topChildHolder.tv_goods_title[7] = (TextView) convertView.findViewById(R.id.tv_goods8_title);

                    topChildHolder.iv_pic[0] = (ImageView) convertView.findViewById(R.id.iv_pic1);
                    topChildHolder.iv_pic[1] = (ImageView) convertView.findViewById(R.id.iv_pic2);
                    topChildHolder.iv_pic[2] = (ImageView) convertView.findViewById(R.id.iv_pic3);
                    topChildHolder.iv_pic[3] = (ImageView) convertView.findViewById(R.id.iv_pic4);
                    topChildHolder.iv_pic[4] = (ImageView) convertView.findViewById(R.id.iv_pic5);
                    topChildHolder.iv_pic[5] = (ImageView) convertView.findViewById(R.id.iv_pic6);
                    topChildHolder.iv_pic[6] = (ImageView) convertView.findViewById(R.id.iv_pic7);
                    topChildHolder.iv_pic[7] = (ImageView) convertView.findViewById(R.id.iv_pic8);

                    convertView.setTag(topChildHolder);
                    break;

                case VIEW_TYPE:
                    holder = new ChildHolder();
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_specialty_recommend_child, parent, false);
                    holder.tv_goods_title[0] = (TextView) convertView.findViewById(R.id.tv_goods1_title);
                    holder.tv_goods_title[1] = (TextView) convertView.findViewById(R.id.tv_goods2_title);
                    holder.tv_goods_title[2] = (TextView) convertView.findViewById(R.id.tv_goods3_title);
                    holder.tv_goods_title[3] = (TextView) convertView.findViewById(R.id.tv_goods4_title);
                    holder.tv_goods_title[4] = (TextView) convertView.findViewById(R.id.tv_goods5_title);
                    holder.tv_goods_title[5] = (TextView) convertView.findViewById(R.id.tv_goods6_title);

                    holder.iv_pic[0] = (ImageView) convertView.findViewById(R.id.iv_pic1);
                    holder.iv_pic[1] = (ImageView) convertView.findViewById(R.id.iv_pic2);
                    holder.iv_pic[2] = (ImageView) convertView.findViewById(R.id.iv_pic3);
                    holder.iv_pic[3] = (ImageView) convertView.findViewById(R.id.iv_pic4);
                    holder.iv_pic[4] = (ImageView) convertView.findViewById(R.id.iv_pic5);
                    holder.iv_pic[5] = (ImageView) convertView.findViewById(R.id.iv_pic6);
                    convertView.setTag(holder);
                    break;
            }
        } else {
            switch (type) {
                case TOP_VIEW_TYPE:
                    topChildHolder = (ChildHolder) convertView.getTag();
                    break;
                case VIEW_TYPE:
                    holder = (ChildHolder) convertView.getTag();
                    break;
            }
        }
            switch (type) {
                case TOP_VIEW_TYPE:
                    int limitTop = 8;
                    if (limitTop >= recommend_list.size()){
                        limitTop = recommend_list.size();
                    }
                    for (int i = 0; i <limitTop; i++) {
                        GlideUtil.show(context,recommend_list.get(i).getCommodity_cover_picture(),topChildHolder.iv_pic[i],R.mipmap.icon_default_pic,R.mipmap.icon_default_pic);

                        if (i != 1 && i != 3) {

                            topChildHolder.tv_goods_title[i].setText(recommend_list.get(i).getCommodity_title());
                        }
                    }

                    break;
                case VIEW_TYPE:
                    int limit = 6;
                    if (limit >= recommend_list.size()){
                        limit = recommend_list.size();
                    }
                    for (int i = 0; i < limit; i++) {
                        GlideUtil.show(context,recommend_list.get(i).getCommodity_cover_picture(),holder.iv_pic[i],R.mipmap.icon_default_pic,R.mipmap.icon_default_pic);
                        holder.tv_goods_title[i].setText(recommend_list.get(i).getCommodity_title());
                    }
                    break;



//            final List<SpecialtyHomeRecommendBean.ListBean> recommend_list = list.get(groupPosition).getRecommend_list();
//            SpecialtyHomeRecommendGridViewChildAdapter adapter = new SpecialtyHomeRecommendGridViewChildAdapter(context, recommend_list);
//            holder.gv_content.setAdapter(adapter);
//            holder.gv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    mDataCallBack.setOnRecommendClick(recommend_list.get(position));
//                }
//            });


        }


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    class GroupHolder {
        GradientTextView tv_title;
        TextView tv_more;
        ImageView iv_bg;
        ImageView iv_banner;
        RelativeLayout lay_rl_bg;

    }

    class ChildHolder {
        private TextView tv_goods_title[] = new TextView[8];
        private ImageView iv_pic[] = new ImageView[8];

    }



}
