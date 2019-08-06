package com.windmillsteward.jukutech.activity.home.personnel.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.MoreBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/11
 * 作者：xjh
 */

public class EduPopupAdapter extends BaseAdapter {

    private Context context;
    private List<MoreBean.EducationListBean> list;
    private int select=-1;

    public EduPopupAdapter(Context context, List<MoreBean.EducationListBean> list) {
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
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_popup_more, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_more.setText(list.get(position).getEducation_name());
        holder.tv_more.setTextColor(ContextCompat.getColor(context,R.color.text_color_black));
        holder.tv_more.setBackground(ContextCompat.getDrawable(context,R.drawable.shape_popup_more_off));
        if (select==position) {
            holder.tv_more.setBackground(ContextCompat.getDrawable(context,R.drawable.shape_popup_more_on));
            holder.tv_more.setTextColor(ContextCompat.getColor(context,R.color.color_white));
        }
        return convertView;
    }

    public void setSelect(int pos) {
        select = pos;
        notifyDataSetChanged();
    }

    class ViewHolder {
        TextView tv_more;

        ViewHolder(View view) {
            this.tv_more = (TextView) view.findViewById(R.id.tv_more);
        }
    }
}
