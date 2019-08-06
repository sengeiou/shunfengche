package com.windmillsteward.jukutech.activity.home.fragment.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.PublicSelectInfo;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.List;

/**
 * 首页-功能 适配器(人才驿站，思想智库)
 */

public class HomeFunctionAdapter extends BaseAdapter {

    private Context context;
    private List<PublicSelectInfo> list;

    public HomeFunctionAdapter(Context context, List<PublicSelectInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home_function, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ViewGroup.LayoutParams layoutParams = holder.iv_pic.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH((Activity) context, GraphicUtil.TAG_WIDTH);
        int i1 = GraphicUtil.dp2px((Activity) context, 120);
        layoutParams.width = (screenWH-GraphicUtil.dp2px((Activity) context, 120))/5;
        int i = GraphicUtil.px2dp(context, layoutParams.width);
        layoutParams.height =  layoutParams.width;
        holder.iv_pic.setLayoutParams(layoutParams);
        String name = list.get(position).getName();
        int resource_id = list.get(position).getResource_id();

        holder.iv_pic.setImageResource(resource_id);
        holder.tv_content.setText(TextUtils.isEmpty(name)?"":name);
        return convertView;
    }


    public static class ViewHolder {
        public ImageView iv_pic;
        public TextView tv_content;

        public ViewHolder(View rootView) {
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
            this.tv_content = (TextView) rootView.findViewById(R.id.tv_content);
        }

    }
}
