package com.windmillsteward.jukutech.activity.home.specialty.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.commons.search.SearchItemActivity;
import com.windmillsteward.jukutech.activity.home.specialty.fragment.SpecialtyListFragment;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.interfaces.Define;

/**
 * 特产列表
 * Created by Administrator on 2018/4/12 0012.
 */

public class SpecialtyListActivity extends BaseActivity implements View.OnClickListener {


    private ImageView iv_back;
    private TextView tv_searchHint;
    private LinearLayout linear_search;
    private TextView tv_postJob;
    private FrameLayout fl_content;

    private int class_id;
    private int class_id_parent;
    private String class_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialty_list);
        initView();
        initToolbar();
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            class_id = extras.getInt(Define.INTENT_DATA);
            class_id_parent = extras.getInt(Define.INTENT_DATA_TWO);
            class_text = extras.getString(Define.INTENT_DATA_THREE);
        }
        setParamInt(R.id.fl_content);
        startFragment(null, SpecialtyListFragment.getInstance("",class_id,class_id_parent,class_text));
    }

    private void initToolbar() {
        handleBackEvent(iv_back);
        tv_searchHint.setText("搜索商品");
        linear_search.setOnClickListener(this);
    }


    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_searchHint = (TextView) findViewById(R.id.tv_searchHint);
        linear_search = (LinearLayout) findViewById(R.id.linear_search);
        tv_postJob = (TextView) findViewById(R.id.tv_postJob);
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_search:
                SearchItemActivity.go(this,60,0);
                break;
        }
    }
}
