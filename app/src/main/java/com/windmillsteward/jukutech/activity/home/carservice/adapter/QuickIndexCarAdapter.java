package com.windmillsteward.jukutech.activity.home.carservice.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.AreaBean;
import com.windmillsteward.jukutech.bean.CarClassListBean;

import java.util.ArrayList;

/**
 * 描述：
 * 时间：2018/1/26
 * 作者：xjh
 */

public class QuickIndexCarAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<CarClassListBean> names;

    public QuickIndexCarAdapter(Context context, ArrayList<CarClassListBean> names) {
        this.context = context;
        this.names = names;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_quick_index, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        boolean isHide = false;
        if (position == 0) {
            isHide = false;
        } else {
            isHide = names.get(position).getPinyin().charAt(0) == names.get(position - 1).getPinyin().charAt(0);
        }

        holder.tv_Index.setVisibility(isHide ? View.GONE : View.VISIBLE);

        CarClassListBean person = names.get(position);
        // 这里不能直接使用char，直接使用char和int相等，是获取资源
        holder.tv_Index.setText(person.getPinyin().charAt(0) + "");
        holder.tv_name.setText(person.getBrand_name());
        return convertView;
    }

    private class ViewHolder {
        TextView tv_Index;
        TextView tv_name;

        public ViewHolder(View view) {
            tv_Index = (TextView) view.findViewById(R.id.tv_index);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
        }
    }
}
