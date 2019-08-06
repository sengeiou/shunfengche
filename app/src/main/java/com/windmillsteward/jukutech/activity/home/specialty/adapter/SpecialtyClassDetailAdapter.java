package com.windmillsteward.jukutech.activity.home.specialty.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.classification.adapter.MyGridViewAdapter;
import com.windmillsteward.jukutech.activity.home.family.activity.IntelligentFamilyListActivity;
import com.windmillsteward.jukutech.activity.home.houselease.activity.HouseRentingActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.TalentInnListActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.HotelAndHouseListActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.TravelListActivity;
import com.windmillsteward.jukutech.activity.home.think.activity.IdeaThinkListActivity;
import com.windmillsteward.jukutech.bean.ClassificationClassBean;
import com.windmillsteward.jukutech.bean.ClassificationPersonalBean;
import com.windmillsteward.jukutech.bean.SpecialtyClassBean;
import com.windmillsteward.jukutech.customview.MyGridView;
import com.windmillsteward.jukutech.utils.DateUtil;

import java.util.Calendar;
import java.util.List;

/**
 * 描述：
 * 时间：2018/3/14/014
 * 作者：xjh
 */
public class SpecialtyClassDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<SpecialtyClassBean.ChildBeanX> list;
    private OnItemClickListener onItemClickListener;

    public SpecialtyClassDetailAdapter(Context context, List<SpecialtyClassBean.ChildBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_mygrvideview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolder myViewHolder = (MyViewHolder) holder;
        MyGridView myGridView = myViewHolder.myGridView;
        SpecialtyClassBean.ChildBeanX bean = list.get(position);
        myViewHolder.tv_class.setText(bean.getName());
        myGridView.setNumColumns(2);
        myGridView.setAdapter(new SpecialtyClassGridViewAdapter(context, bean.getChild()));
        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SpecialtyClassBean.ChildBeanX.ChildBean item = (SpecialtyClassBean.ChildBeanX.ChildBean) parent.getAdapter().getItem(position);
                if (onItemClickListener!=null) {
                    onItemClickListener.onItemClick(item);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

     public interface OnItemClickListener {
        void onItemClick(SpecialtyClassBean.ChildBeanX.ChildBean item);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_class;
        MyGridView myGridView;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_class = itemView.findViewById(R.id.tv_class);
            myGridView = itemView.findViewById(R.id.myGridView);
        }
    }
}
