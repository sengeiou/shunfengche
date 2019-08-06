package com.windmillsteward.jukutech.activity.home.family.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.RequireClassBean;

import java.util.List;

/**
 * 描述：发布需求的选择分类
 * 时间：2018/1/19
 * 作者：xjh
 */

public class RequireChoiceClassAdapter extends BaseAdapter {

    private Context context;
    private List<RequireClassBean> list;

    public RequireChoiceClassAdapter(Context context, List<RequireClassBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_choiceclass, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_class.setText(list.get(position).getClass_name());


        return convertView;
    }

    class ViewHolder {
        TextView tv_class;

        ViewHolder(View rootView) {
            this.tv_class = (TextView) rootView.findViewById(R.id.tv_class);
        }

    }
}
