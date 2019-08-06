package com.windmillsteward.jukutech.activity.classification.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.CapitalManagerActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.CarListActivity;
import com.windmillsteward.jukutech.activity.home.carservice.activity.CarServiceActivity;
import com.windmillsteward.jukutech.activity.home.family.activity.IntelligentFamilyListActivity;
import com.windmillsteward.jukutech.activity.home.houselease.activity.HouseRentingActivity;
import com.windmillsteward.jukutech.activity.home.insurance.activity.InsuranceListActivity;
import com.windmillsteward.jukutech.activity.home.legalexpert.activity.PublishLegalExpertActivity;
import com.windmillsteward.jukutech.activity.home.personnel.activity.TalentInnListActivity;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyListActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.HotelAndHouseListActivity;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.TravelListActivity;
import com.windmillsteward.jukutech.activity.home.think.activity.IdeaThinkListActivity;
import com.windmillsteward.jukutech.activity.newpage.model.ClassificationModel;
import com.windmillsteward.jukutech.bean.ClassificationClassBean;
import com.windmillsteward.jukutech.bean.ClassificationPersonalBean;
import com.windmillsteward.jukutech.customview.MyGridView;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.DateUtil;
import com.windmillsteward.jukutech.utils.GlideUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 描述：
 * 时间：2018/3/14/014
 * 作者：xjh
 */
public class ClassificationDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<ClassificationModel.ListBean> list;
    private int type;

    public ClassificationDetailAdapter(Context context, ArrayList<ClassificationModel.ListBean> list, int type) {
        this.context = context;
        this.list = list;
        this.type = type;
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
        ClassificationModel.ListBean bean = list.get(position);
        myViewHolder.tv_class.setText(bean.getName());
        myGridView.setAdapter(new MyGridViewAdapter(context, bean.getClass_list(), type));

        myGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ClassificationModel.ListBean.ClassListBean item = (ClassificationModel.ListBean.ClassListBean) parent.getAdapter().getItem(position);
                if (type == 0) {
                    //人才驿站



                } else if (type == 1) {
                    //智慧生活
                } else if (type == 2) {
                    //房屋信息
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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
