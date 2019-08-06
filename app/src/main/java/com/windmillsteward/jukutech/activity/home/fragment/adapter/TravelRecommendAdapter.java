package com.windmillsteward.jukutech.activity.home.fragment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.TravelRecommendBean;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.x;

import java.util.List;

/**
 * 旅游推荐适配器
 */

public class TravelRecommendAdapter extends BaseAdapter {

    private Context context;
    private List<TravelRecommendBean.ListBean> list;

    public TravelRecommendAdapter(Context context, List<TravelRecommendBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    public void updateList(List<TravelRecommendBean.ListBean> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_travel_recomend, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        int resource_id = list.get(position).getResource_id();
//        holder.iv_pic.setImageResource(resource_id);
        x.image().bind(holder.iv_pic,list.get(position).getCover_url(), ImageUtils.defaulPicList());

        return convertView;
    }


    public static class ViewHolder {
        public ImageView iv_pic;

        public ViewHolder(View rootView) {
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
        }

    }
}
