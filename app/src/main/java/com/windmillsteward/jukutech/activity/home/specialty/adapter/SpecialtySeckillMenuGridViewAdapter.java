package com.windmillsteward.jukutech.activity.home.specialty.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.bean.SeckillBean;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.GraphicUtil;

import java.util.List;

/**
 * 描述：秒杀适配器
 * author:cyq
 * 2018-07-09
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SpecialtySeckillMenuGridViewAdapter extends BaseAdapter {

    private Context context;
    private List<SeckillBean.ListBean> lists;//数据源
    private int mIndex; // 页数下标，标示第几页，从0开始
    private int mPargerSize;// 每页显示的最大的数量
    private SpecialtyHomeAdapter.RecyclerViewDataCallBack mDataCallBack;


    public SpecialtySeckillMenuGridViewAdapter(Context context, List<SeckillBean.ListBean> lists,
                                               int mIndex, int mPargerSize, SpecialtyHomeAdapter.RecyclerViewDataCallBack mDataCallBack) {
        this.context = context;
        this.lists = lists;
        this.mIndex = mIndex;
        this.mPargerSize = mPargerSize;
        this.mDataCallBack = mDataCallBack;
    }

    /**
     * 先判断数据及的大小是否显示满本页lists.size() > (mIndex + 1)*mPagerSize
     * 如果满足，则此页就显示最大数量lists的个数
     * 如果不够显示每页的最大数量，那么剩下几个就显示几个
     */
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lists.size() > (mIndex + 1) * mPargerSize ?
                mPargerSize : (lists.size() - mIndex * mPargerSize);
    }

    @Override
    public SeckillBean.ListBean getItem(int arg0) {
        // TODO Auto-generated method stub
        return lists.get(arg0 + mIndex * mPargerSize);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0 + mIndex * mPargerSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_specialty_home_seckill_list, null);
            holder.tv_kill_price = (TextView) convertView.findViewById(R.id.tv_kill_price);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            holder.iv_pic = (ImageView) convertView.findViewById(R.id.iv_pic);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //重新确定position因为拿到的总是数据源，数据源是分页加载到每页的GridView上的
        final int pos = position + mIndex * mPargerSize;//假设mPageSiez
        //假设mPagerSize=8，假如点击的是第二页（即mIndex=1）上的第二个位置item(position=1),那么这个item的实际位置就是pos=9
        holder.tv_kill_price.setText("¥" + lists.get(pos).getCost_price() + "");
        holder.tv_price.setText("¥" + lists.get(pos).getCommodity_price() + "");
        holder.tv_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中间横线

        ViewGroup.LayoutParams layoutParams = holder.iv_pic.getLayoutParams();
        int screenWH = GraphicUtil.getScreenWH((Activity) context, GraphicUtil.TAG_WIDTH);
        layoutParams.width = (screenWH - GraphicUtil.dp2px((Activity) context, 90)) / 4;
        int i = GraphicUtil.px2dp(context, layoutParams.width);
        layoutParams.height = layoutParams.width;
        holder.iv_pic.setLayoutParams(layoutParams);

        GlideUtil.show(context,lists.get(pos).getCommodity_model_image(),holder.iv_pic,R.mipmap.icon_default_pic,R.mipmap.icon_default_pic);

//        添加item监听
        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                mDataCallBack.setOnSeckillGoodsDetailClick(lists.get(pos));
            }
        });
        return convertView;
    }

    static class ViewHolder {
        private TextView tv_kill_price;
        private TextView tv_price;
        private ImageView iv_pic;
    }

}
