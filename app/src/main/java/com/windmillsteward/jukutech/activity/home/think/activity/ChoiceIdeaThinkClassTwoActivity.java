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
import com.windmillsteward.jukutech.activity.home.think.adapter.ChoiceIdeaThinkClassTwoAdapter;
import com.windmillsteward.jukutech.activity.home.think.presenter.ChoiceIdeaThinkClassPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.IdeaThinkClassBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：思想智库二级分类
 * 时间：2018/3/10/010
 * 作者：xjh
 */
public class ChoiceIdeaThinkClassTwoActivity extends BaseActivity implements ChoiceIdeaThinkClassView {

    public static final String FIRST_CLASS_ID = "FIRST_CLASS_ID";

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private RecyclerView mRecyclerView;
    private List<IdeaThinkClassBean.SecondClassListBean> list;
    private ChoiceIdeaThinkClassTwoAdapter adapter;

    private ChoiceIdeaThinkClassPresenter presenter;

    private int first_class_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choiceideathinkclass);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            first_class_id = extras.getInt(FIRST_CLASS_ID);
        }

        initView();
        initToolbar();
        initRecyclerView();

        presenter = new ChoiceIdeaThinkClassPresenter(this);
        presenter.initData();
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new ChoiceIdeaThinkClassTwoAdapter(list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent =new Intent();
                Bundle bundle = new Bundle();
                bundle.putString(ChoiceIdeaThinkClassActivity.RESULT_DATA_NAME,list.get(position).getSecond_class_name());
                bundle.putInt(ChoiceIdeaThinkClassActivity.RESULT_DATA_ID,list.get(position).getSecond_class_id());
                bundle.putInt(ChoiceIdeaThinkClassActivity.RESULT_DATA_FIRST_ID,first_class_id);
                intent.putExtras(bundle);
                setResult(ChoiceIdeaThinkClassActivity.RESULT_CODE,intent);
                finish();
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
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getFirst_class_id()==first_class_id) {
                list.addAll(data.get(i).getSecond_class_list());
                toolbar_iv_title.setText(data.get(i).getFirst_class_name());
                break;
            }
        }
        adapter.setNewData(list);
    }
}
