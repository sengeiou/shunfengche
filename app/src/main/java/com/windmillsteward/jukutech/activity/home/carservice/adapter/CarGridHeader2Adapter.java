package com.windmillsteward.jukutech.activity.home.carservice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.CarClassListBean;
import com.windmillsteward.jukutech.bean.CarPriceListBean;

import org.xutils.x;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/22/022
 * 作者：xjh
 */
public class CarGridHeader2Adapter extends BaseAdapter {

    private Context context;
    private List<CarPriceListBean> list;

    public CarGridHeader2Adapter(Context context, List<CarPriceListBean> list) {
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
        Holder holder=null;
        if (convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_car_header_grid2,parent,false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tv_price.setText(list.get(position).getName());
        return convertView;
    }

    static class Holder {

        TextView tv_price;

        Holder(View convertView) {
            tv_price = convertView.findViewById(R.id.tv_price);
        }
    }
}
