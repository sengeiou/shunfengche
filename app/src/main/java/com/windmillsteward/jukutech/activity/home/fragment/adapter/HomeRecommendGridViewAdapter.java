package com.windmillsteward.jukutech.activity.home.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.RecommendGridBean;
import com.windmillsteward.jukutech.customview.GradientTextView;
import com.windmillsteward.jukutech.customview.MyGridView;

import java.util.List;
import java.util.Random;

/**
 * 描述：首页推荐
 * 时间：2018年6月7日
 * 作者：cyq
 */
public class HomeRecommendGridViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<RecommendGridBean> list;
    private HomeRecyclerViewAdapter.RecyclerViewDataCallBack mDataCallBack;

    public HomeRecommendGridViewAdapter(Context context, List<RecommendGridBean> list,HomeRecyclerViewAdapter.RecyclerViewDataCallBack mDataCallBack) {
        this.context = context;
        this.list = list;
        this.mDataCallBack = mDataCallBack;
    }


    @Override
    public int getGroupCount() {
        return list == null ?0:list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
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
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder = null;
        if (convertView==null) {
            holder = new GroupHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend_grid_group,parent,false);
            holder.tv_title =(GradientTextView) convertView.findViewById(R.id.tv_title);
            holder.tv_more =(TextView)  convertView.findViewById(R.id.tv_more);
            holder.iv_bg =(ImageView)  convertView.findViewById(R.id.iv_bg);
            holder.lay_rl_bg =(RelativeLayout)  convertView.findViewById(R.id.lay_rl_bg);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }

        Random rand = new Random();
        int random = rand.nextInt(3);
        if(random == 0){
            holder.tv_title.setmColorList(new int[]{0xb0f451, 0xFFffd229,0xFFfd7c00});
            holder.iv_bg.setImageResource(R.mipmap.icon_home_title2);
        }else if (random == 1){
            holder.tv_title.setmColorList(new int[]{0xFF08e0f9, 0xFF78c800,0xFFfd7c00});
            holder.iv_bg.setImageResource(R.mipmap.icon_home_title);
        }else if (random == 2){
            holder.tv_title.setmColorList(new int[]{0xFFdde742, 0xFFffa200,0xFF77c600});
            holder.iv_bg.setImageResource(R.mipmap.icon_home_title3);
        }else if (random == 3){
            holder.tv_title.setmColorList(new int[]{0xFF09f08e,0xFF00cfd7,0xFF00878c});
            holder.iv_bg.setImageResource(R.mipmap.icon_home_title);
        }
        holder.tv_title.setText(list.get(groupPosition).getModule_name());
        holder.tv_title.setVertrial(false);

        final RecommendGridBean recommendGridBean = list.get(groupPosition);
        holder.tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataCallBack.setOnRecommendMoreClick(recommendGridBean);
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder = null;
        if (convertView==null) {
            holder = new ChildHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_recommend_grid_child,parent,false);
            holder.gv_content = convertView.findViewById(R.id.gv_content);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }

        final List<RecommendGridBean.ListBean> recommend_list = list.get(groupPosition).getRecommend_list();
        HomeRecommendGridViewChildAdapter adapter = new HomeRecommendGridViewChildAdapter(context,recommend_list);
        holder.gv_content.setAdapter(adapter);
        holder.gv_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RecommendGridBean.ListBean listBean = recommend_list.get(position);
                mDataCallBack.setOnRecommendClick(listBean);
            }
        });

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
        RelativeLayout lay_rl_bg;

    }

    class ChildHolder{
        MyGridView gv_content;
    }
}
