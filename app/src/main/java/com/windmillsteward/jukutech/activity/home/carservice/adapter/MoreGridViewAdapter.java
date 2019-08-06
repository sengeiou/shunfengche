package com.windmillsteward.jukutech.activity.home.carservice.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.WorkPopupAdapter;
import com.windmillsteward.jukutech.bean.MoreBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/3/28/028
 * 作者：xjh
 */
public class MoreGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<Map<String,Object>> list;
    private int select=-1;

    public MoreGridViewAdapter(Context context, List<Map<String,Object>> list) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<Map<String, Object>> list) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_popup_more, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Map<String, Object> map = list.get(position);
        holder.tv_more.setText((String) map.get("text"));
        holder.tv_more.setTextColor(ContextCompat.getColor(context,R.color.text_color_black));
        holder.tv_more.setBackground(ContextCompat.getDrawable(context,R.drawable.shape_popup_more_off));
        if (select==position) {
            holder.tv_more.setBackground(ContextCompat.getDrawable(context,R.drawable.shape_popup_more_on));
            holder.tv_more.setTextColor(ContextCompat.getColor(context,R.color.color_white));
        }

        return convertView;
    }

    public void setSelect(int select_id){
        for (int i = 0; i < list.size(); i++) {
            if (((int) list.get(i).get("id")) == select_id) {
                select = i;
                break;
            }
        }
        notifyDataSetChanged();
    }

    public void reset() {
        select = -1;
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView tv_more;

        ViewHolder(View view) {
            this.tv_more = (TextView) view.findViewById(R.id.tv_more);
        }
    }
}
