package com.windmillsteward.jukutech.activity.home.legalexpert.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.legalexpert.adapter.LegalExpertClassActivityAdapter;
import com.windmillsteward.jukutech.activity.home.legalexpert.presenter.LegalExpertClassActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.NameAndIdBean;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class LegalExpertClassActivity extends BaseActivity implements LegalExpertClassActivityView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private RecyclerView mRecyclerView;

    private List<NameAndIdBean> list;
    private LegalExpertClassActivityAdapter adapter;
    private LegalExpertClassActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legalexpert_class);
        initView();
        initToolbar();
        initRecyclerView();

        presenter = new LegalExpertClassActivityPresenter(this);
        presenter.initData();
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new LegalExpertClassActivityAdapter(list);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NameAndIdBean nameAndIdBean = list.get(position);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putInt(Define.INTENT_DATA,nameAndIdBean.getId());
                bundle.putString(Define.INTENT_DATA_TWO,nameAndIdBean.getName());
                intent.putExtras(bundle);
                setResult(200,intent);
                finish();
            }
        });
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("类别");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
    }

    @Override
    public void initDataSuccess(List<NameAndIdBean> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.setNewData(list);
    }
}
