package com.windmillsteward.jukutech.activity.home.fragment.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.GuessYouLikeBean;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.List;

/**
 * 首页-推荐列表子item适配器
 */

public class HomeGuessYouLikeAdapter extends BaseAdapter {

    private Context context;
    private List<GuessYouLikeBean.ListBean> list;

    public HomeGuessYouLikeAdapter(Context context, List<GuessYouLikeBean.ListBean> list) {
        this.context = context;
        this.list = list;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_guess_you_like_grid, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String pic_url = list.get(position).getCommodity_cover_picture();
        String price = list.get(position).getCommodity_reserve_price();
        String title = list.get(position).getCommodity_title();
        int relate_id = list.get(position).getCommodity_id();
        ViewGroup.LayoutParams layoutParams = holder.iv_pic.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH((Activity) context, GraphicUtil.TAG_WIDTH);
        layoutParams.width = (screenWH-GraphicUtil.dp2px((Activity) context, 30))/2;
        layoutParams.height = layoutParams.width;
        holder.iv_pic.setLayoutParams(layoutParams);
        GlideUtil.show(context,pic_url,holder.iv_pic,R.mipmap.icon_default_pic);
        holder.tv_price.setText(TextUtils.isEmpty(price)?"":"¥"+price+"元");
        holder.tv_title.setText(TextUtils.isEmpty(title)?"":title);

        return convertView;
    }


    public static class ViewHolder {
        public ImageView iv_pic;
        public TextView tv_title;
        public TextView tv_price;

        public ViewHolder(View rootView) {
            this.iv_pic = (ImageView) rootView.findViewById(R.id.iv_pic);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_price = (TextView) rootView.findViewById(R.id.tv_price);
        }

    }
}
