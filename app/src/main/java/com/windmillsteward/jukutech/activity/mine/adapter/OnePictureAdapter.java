package com.windmillsteward.jukutech.activity.mine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;

import java.util.List;

/**
 */

public class OnePictureAdapter extends BaseAdapter  {
    private Context context;
    private List<String> data;


    public OnePictureAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    public void refreshData(List<String> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data == null?0:data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_one_picture, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(data.get(position)).into(viewHolder.iv_pic);
        return convertView;
    }



    public static class ViewHolder {

        public ImageView iv_pic;

        public ViewHolder(View rootView) {
            iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
        }

    }

}
