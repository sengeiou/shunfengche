package com.windmillsteward.jukutech.activity.home.specialty.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.ClassificationClassBean;
import com.windmillsteward.jukutech.bean.SpecialtyClassBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/14/014
 * 作者：xjh
 */
public class SpecialtyClassGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<SpecialtyClassBean.ChildBeanX.ChildBean> list;

    public SpecialtyClassGridViewAdapter(Context context, List<SpecialtyClassBean.ChildBeanX.ChildBean> list) {
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

        Holder holder = null;
        if (convertView==null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_classificationmenu,parent,false);
            holder.tv_class = convertView.findViewById(R.id.tv_class_menu);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.tv_class.setText(list.get(position).getName());
        return convertView;
    }

    class Holder {
        TextView tv_class;
    }
}
