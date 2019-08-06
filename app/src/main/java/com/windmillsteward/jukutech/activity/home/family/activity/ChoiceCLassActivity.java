package com.windmillsteward.jukutech.activity.home.family.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.family.adapter.RequireChoiceClassAdapter;
import com.windmillsteward.jukutech.activity.home.family.presenter.ChoiceCLassPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.RequireClassBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：选择分类
 * 时间：2018/1/16/016
 * 作者：xjh
 */
public class ChoiceCLassActivity extends BaseActivity implements ChoiceCLassView {

    public static final String DATA = "DATA";
    public static final int RESULT_CODE = 200;
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private ListView mListView;
    private List<RequireClassBean> list;
    private RequireChoiceClassAdapter adapter;
    private ChoiceCLassPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choiceclass);
        initView();
        initToolbar();
        initListView();
        presenter = new ChoiceCLassPresenter(this);
        presenter.initData();
    }

    private void initListView() {
        list = new ArrayList<>();
        adapter = new RequireChoiceClassAdapter(this,list);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent data = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable(DATA,list.get((int) id));
                data.putExtras(bundle);
                setResult(RESULT_CODE, data);
                finish();
            }
        });
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("选择分类");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        mListView = (ListView) findViewById(R.id.mListView);
    }

    @Override
    public void initData(List<RequireClassBean> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
