package com.windmillsteward.jukutech.activity.home.specialty.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.specialty.adapter.SpecialtyClassDetailAdapter;
import com.windmillsteward.jukutech.activity.home.specialty.adapter.SpecialtyClassMenuAdapter;
import com.windmillsteward.jukutech.activity.home.specialty.presenter.SpecialtyClassActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.SpecialtyClassBean;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public class SpecialtyClassActivity extends BaseActivity implements SpecialtyClassActivityView {


    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private RecyclerView tableRecyclerView;
    private RecyclerView mRecyclerView;

    private SpecialtyClassActivityPresenter presenter;
    private List<SpecialtyClassBean> list;
    private SpecialtyClassMenuAdapter classMenuAdapter;
    private List<SpecialtyClassBean.ChildBeanX> detailList;
    private SpecialtyClassDetailAdapter classDetailAdapter;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty_class);
        initView();
        initToolbar();
        initRecyclerView();

        presenter = new SpecialtyClassActivityPresenter(this,this);
        presenter.initDataSuccess(0);
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        classMenuAdapter = new SpecialtyClassMenuAdapter(list);
        tableRecyclerView.setAdapter(classMenuAdapter);
        tableRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        classMenuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (SpecialtyClassActivity.this.position == position) {
                    return;
                }
                SpecialtyClassActivity.this.position = position;
                for (int i = 0; i < list.size(); i++) {
                    if (i == position) {
                        list.get(i).setTextColor(ContextCompat.getColor(SpecialtyClassActivity.this, R.color.color_23abac));
                    } else {
                        list.get(i).setTextColor(ContextCompat.getColor(SpecialtyClassActivity.this, R.color.text_color_black));
                    }
                }
                detailList.clear();
                detailList.addAll(list.get(position).getChild());
                classMenuAdapter.notifyDataSetChanged();
                classDetailAdapter.notifyDataSetChanged();
            }
        });

        detailList = new ArrayList<>();
        classDetailAdapter = new SpecialtyClassDetailAdapter(this,detailList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(classDetailAdapter);
        classDetailAdapter.setOnItemClickListener(new SpecialtyClassDetailAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SpecialtyClassBean.ChildBeanX.ChildBean item) {
                Intent data = new Intent();
                Bundle extras = new Bundle();
                extras.putInt(Define.INTENT_DATA,list.get(position).getClass_id());
                extras.putInt(Define.INTENT_DATA_TWO,item.getParent_id());
                extras.putInt(Define.INTENT_DATA_THREE,item.getClass_id());
                extras.putString(Define.INTENT_DATA_FOUR,item.getName());
                data.putExtras(extras);
                setResult(200, data);
                finish();
            }
        });
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("类目");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        tableRecyclerView = (RecyclerView) findViewById(R.id.tableRecyclerView);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
    }

    @Override
    public void initDataSuccess(List<SpecialtyClassBean> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                list.get(0).setTextColor(ContextCompat.getColor(this, R.color.color_23abac));
            }
        }
        this.list.clear();
        this.list.addAll(list);
        classMenuAdapter.setNewData(list);
        View headView = LayoutInflater.from(this).inflate(R.layout.item_classificationmenu, ((ViewGroup) tableRecyclerView.getParent()), false);
        TextView text = (TextView) headView.findViewById(R.id.tv_class_menu);
        text.setText("全部");
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                Bundle extras = new Bundle();
                extras.putInt(Define.INTENT_DATA,0);
                extras.putInt(Define.INTENT_DATA_TWO,0);
                extras.putInt(Define.INTENT_DATA_THREE,0);
                extras.putString(Define.INTENT_DATA_FOUR,"全部");
                data.putExtras(extras);
                setResult(200, data);
                finish();
            }
        });
        classMenuAdapter.addHeaderView(headView);
        if (list.size()>0) {
            detailList.clear();
            detailList.addAll(list.get(position).getChild());
            classDetailAdapter.notifyDataSetChanged();
        }

    }
}
