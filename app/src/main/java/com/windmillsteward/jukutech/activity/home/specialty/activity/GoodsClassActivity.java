package com.windmillsteward.jukutech.activity.home.specialty.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.specialty.adapter.GoodsClassActivityAdapter;
import com.windmillsteward.jukutech.activity.home.specialty.presenter.GoodsClassActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.GoodsClassListBean;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 时间：2018/4/23/023
 * 作者：xjh
 */
public class GoodsClassActivity extends BaseActivity implements GoodsClassActivityView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private RecyclerView mRecyclerView;

    private GoodsClassActivityPresenter presenter;
    private List<GoodsClassListBean> list;
    private GoodsClassActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goodsclass_list);
        initView();
        initToolbar();
        initRecyclerView();
        presenter = new GoodsClassActivityPresenter(this);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            presenter.initDataSuccess(extras.getInt(Define.INTENT_DATA));
        } else {
            finish();
        }
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new GoodsClassActivityAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent data = new Intent();
                Bundle extras = new Bundle();
                extras.putInt(Define.INTENT_DATA,list.get(position).getCommodity_category_id());
                extras.putString(Define.INTENT_DATA_TWO,list.get(position).getCommodity_category_name());
                data.putExtras(extras);
                setResult(200, data);
                finish();
            }
        });
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("分类");
    }

    @Override
    public void initDataSuccess(List<GoodsClassListBean> list) {
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
    }
}
