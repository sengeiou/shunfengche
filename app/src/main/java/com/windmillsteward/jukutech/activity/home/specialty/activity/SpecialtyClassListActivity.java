package com.windmillsteward.jukutech.activity.home.specialty.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.specialty.adapter.SpecialtyClassListAdapter;
import com.windmillsteward.jukutech.activity.home.specialty.presenter.SpecialtyClassListActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.SpecialtyClassListBean;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public class SpecialtyClassListActivity extends BaseActivity implements SpecialtyClassListActivityView{

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private ListView mListView;

    private SpecialtyClassListAdapter adapter;
    private List<SpecialtyClassListBean> list;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty_class_list);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            id = extras.getInt(Define.INTENT_DATA);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        initRecyclerView();
        SpecialtyClassListActivityPresenter presenter = new SpecialtyClassListActivityPresenter(this);
        presenter.initDataSuccess(id);
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new SpecialtyClassListAdapter(this,list);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putInt(Define.INTENT_DATA,list.get((int) l).getCommodity_category_id());
                bundle.putInt(Define.INTENT_DATA_TWO,id);
                startAtvAndFinish(GoodsListActivity.class,bundle);
            }
        });


        View header = LayoutInflater.from(this).inflate(R.layout.item_specialty_class_list, mListView, false);
        mListView.addHeaderView(header);
        TextView textView = (TextView) header.findViewById(R.id.tv_class);
        textView.setText("全部商品");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt(Define.INTENT_DATA,0);
                bundle.putInt(Define.INTENT_DATA_TWO,id);
                startAtvAndFinish(GoodsListActivity.class,bundle);
            }
        });
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("分类");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        mListView = (ListView) findViewById(R.id.mListView);
    }

    @Override
    public void initDataSuccess(List<SpecialtyClassListBean> bean) {
        list.clear();
        list.addAll(bean);
        adapter.notifyDataSetChanged();
    }
}
