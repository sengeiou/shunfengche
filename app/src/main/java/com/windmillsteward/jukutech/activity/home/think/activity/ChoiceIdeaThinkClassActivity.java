package com.windmillsteward.jukutech.activity.home.think.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.think.adapter.ChoiceIdeaThinkClassAdapter;
import com.windmillsteward.jukutech.activity.home.think.presenter.ChoiceIdeaThinkClassPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.IdeaThinkClassBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 时间：2018/2/5
 * 作者：xjh
 */

public class ChoiceIdeaThinkClassActivity extends BaseActivity implements ChoiceIdeaThinkClassView {

    public static final int RESULT_CODE = 200;
    public static final String RESULT_DATA_NAME = "RESULT_DATA_NAME";
    public static final String RESULT_DATA_ID = "RESULT_DATA_ID";
    public static final String RESULT_DATA_FIRST_ID = "RESULT_DATA_FIRST_ID";

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private RecyclerView mRecyclerView;
    private List<IdeaThinkClassBean> list;
    private ChoiceIdeaThinkClassAdapter adapter;

    private ChoiceIdeaThinkClassPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choiceideathinkclass);
        initView();
        initToolbar();
        initRecyclerView();

        presenter = new ChoiceIdeaThinkClassPresenter(this);
        presenter.initData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==100 && resultCode == RESULT_CODE) {
            setResult(RESULT_CODE,data);
            finish();
        }
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new ChoiceIdeaThinkClassAdapter(list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putInt(ChoiceIdeaThinkClassTwoActivity.FIRST_CLASS_ID,list.get(position).getFirst_class_id());
                startAtvDonFinishForResult(ChoiceIdeaThinkClassTwoActivity.class,100, bundle);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
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
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
    }

    @Override
    public void initDataSuccess(List<IdeaThinkClassBean> data) {
        list.clear();
        list.addAll(data);
        adapter.setNewData(list);
    }
}
