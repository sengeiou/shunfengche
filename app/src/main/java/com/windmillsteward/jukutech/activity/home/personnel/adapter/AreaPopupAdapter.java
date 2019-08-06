package com.windmillsteward.jukutech.activity.home.personnel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/9
 * 作者：xjh
 */

public class AreaPopupAdapter extends BaseAdapter {

    private Context context;
    private List<ThirdAreaBean> list;

    public AreaPopupAdapter(Context context, List<ThirdAreaBean> list) {
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

        PopupHolder holder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_popup,parent,false);
            holder = new PopupHolder();
            holder.tvPopupItem = (TextView) convertView.findViewById(R.id.tv_popupItem);
            convertView.setTag(holder);
        } else {
            holder = (PopupHolder) convertView.getTag();
        }

        ThirdAreaBean bean = list.get(position);
        if (bean!=null) {
            holder.tvPopupItem.setText(bean.getThird_area_name());
        }

        return convertView;
    }

    class PopupHolder {
        TextView tvPopupItem;
    }


}
