package com.windmillsteward.jukutech.activity.home.carservice.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.CarClassListBean;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/22/022
 * 作者：xjh
 */
public class CarGridHeader1Adapter extends BaseAdapter {

    private Context context;
    private List<CarClassListBean> list;

    public CarGridHeader1Adapter(Context context, List<CarClassListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list.size()>8) {
            return 8;
        }
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
        Holder holder=null;
        if (convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_car_header_grid1,parent,false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        ViewGroup.LayoutParams layoutParams = holder.iv_car_icon.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH((Activity) context, GraphicUtil.TAG_WIDTH);
        layoutParams.width = (screenWH - GraphicUtil.dp2px((Activity) context, 150))/4;
        layoutParams.height =  layoutParams.width;
        holder.iv_car_icon.setLayoutParams(layoutParams);

        GlideUtil.show(context,list.get(position).getBrand_image(),holder.iv_car_icon,R.mipmap.pic_list,R.mipmap.pic_list);
        holder.tv_car_name.setText(list.get(position).getBrand_name());
        return convertView;
    }

    static class Holder {
        ImageView iv_car_icon;
        TextView tv_car_name;

        Holder(View convertView) {
            iv_car_icon = convertView.findViewById(R.id.iv_car_icon);
            tv_car_name = convertView.findViewById(R.id.tv_car_name);
        }
    }
}
