package com.windmillsteward.jukutech.activity.classification.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.newpage.model.ClassificationModel;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/14/014
 * 作者：xjh
 */
public class MyGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<ClassificationModel.ListBean.ClassListBean> list;
    private int type;

    public MyGridViewAdapter(Context context, List<ClassificationModel.ListBean.ClassListBean> list, int type) {
        this.context = context;
        this.list = list;
        this.type = type;
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
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_classification_detail, parent, false);
            holder.tv_class = convertView.findViewById(R.id.tv_class);
            holder.iv_icon = convertView.findViewById(R.id.iv_icon);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        ViewGroup.LayoutParams layoutParams = holder.iv_icon.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH((Activity) context, GraphicUtil.TAG_WIDTH);
        int i = GraphicUtil.dp2px((Activity) context, 100);
        int i1 = GraphicUtil.dp2px((Activity) context, 110);
        layoutParams.width = (screenWH - i - i1) / 3;

        layoutParams.height = layoutParams.width;
        holder.iv_icon.setLayoutParams(layoutParams);

        if (type == 0) {
            //人才驿站
            holder.tv_class.setText(list.get(position).getName());
            GlideUtil.show(context, list.get(position).getImage_url(), holder.iv_icon);
        } else if (type == 1) {
            //智慧生活
            holder.tv_class.setText(list.get(position).getClass_name());
            GlideUtil.show(context, list.get(position).getClass_image(), holder.iv_icon);
        } else if (type == 2) {
            //房屋信息
            holder.tv_class.setText(list.get(position).getHouse_type_name());
            GlideUtil.show(context, list.get(position).getImage(), holder.iv_icon);
        }
        return convertView;
    }

    class Holder {
        TextView tv_class;
        ImageView iv_icon;
    }
}
