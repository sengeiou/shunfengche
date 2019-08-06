package com.windmillsteward.jukutech.activity.home.stayandtravel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/1/27/027
 * 作者：xjh
 */
public class SimplePopupListAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String,Object>> list;

    public SimplePopupListAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;

        if (view==null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_popup,viewGroup,false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Map<String, Object> map = list.get(i);
        if (map!=null && map.containsKey("text")) {
            String text = (String) map.get("text");
            holder.tv_item.setText(text);
        }

        return view;
    }

    class ViewHolder {
        TextView tv_item;
        ViewHolder(View view) {
            tv_item = (TextView) view.findViewById(R.id.tv_popupItem);
        }
    }
}
