package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.LabourPositionTwoCLassActivityLeftAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.LabourPositionTwoCLassActivityRightAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.LabourPositionClassActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.LabourPositionClassBean;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 时间：2018/8/1
 * 作者：cyq
 */
public class LabourPositionTwoCLassActivity extends BaseActivity implements LabourPositionClassActivityView {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView et_search;
    private RecyclerView mRecyclerView1;
    private RecyclerView mRecyclerView2;
    private List<LabourPositionClassBean> all_list,list_lift,list_right;
    private LabourPositionTwoCLassActivityLeftAdapter leftAdapter;
    private LabourPositionTwoCLassActivityRightAdapter rightAdapter;

    private int job_class_id_one;
    private String job_class_name_one;
    private LabourPositionClassActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_class_two);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            job_class_id_one = extras.getInt(Define.INTENT_DATA);
            job_class_name_one = extras.getString(Define.INTENT_DATA_TWO);
        } else {
            finish();
            return;
        }

        initView();
        initToolbar();
        initRecyclerView();

        presenter = new LabourPositionClassActivityPresenter(this);
        presenter.initData("",getAccessToken());
    }

    private void initRecyclerView() {
        all_list = new ArrayList<>();
        list_lift = new ArrayList<>();
        list_right = new ArrayList<>();
        leftAdapter = new LabourPositionTwoCLassActivityLeftAdapter(list_lift);
        rightAdapter = new LabourPositionTwoCLassActivityRightAdapter(list_right);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView1.setAdapter(leftAdapter);
        mRecyclerView2.setAdapter(rightAdapter);
        leftAdapter.removeEmptyView();
        rightAdapter.removeEmptyView();

        leftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                list_right.clear();
                for (LabourPositionClassBean classBean : list_lift) {
                    classBean.setSelect(false);
                }
                LabourPositionClassBean bean = list_lift.get(position);
                bean.setSelect(true);
                int job_class_id = bean.getJob_class_id();
                for (LabourPositionClassBean classBean : all_list) {
                    if (classBean.getType()==5 && classBean.getParent_id()==job_class_id) {
                        list_right.add(classBean);
                    }
                }
                leftAdapter.notifyDataSetChanged();
                rightAdapter.notifyDataSetChanged();
            }
        });
        rightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int job_class_id_two=0;
                String job_class_name_two="";
                for (LabourPositionClassBean classBean : list_lift) {
                    boolean select = classBean.isSelect();
                    if (select) {
                        job_class_id_two = classBean.getJob_class_id();
                        job_class_name_two = classBean.getClass_name();
                    }
                }
                LabourPositionClassBean classBean = list_right.get(position);
                int job_class_id_three = classBean.getJob_class_id();
                String job_class_name_three = classBean.getClass_name();
                Intent data = new Intent();
                Bundle extras = new Bundle();
                extras.putInt(Define.INTENT_DATA, job_class_id_one);
                extras.putInt(Define.INTENT_DATA_TWO, job_class_id_two);
                extras.putInt(Define.INTENT_DATA_THREE, job_class_id_three);
                extras.putString(Define.INTENT_DATA_FOUR,job_class_name_one);
                extras.putString(Define.INTENT_DATA_FIVE,job_class_name_two);
                extras.putString(Define.INTENT_DATA_SIX,job_class_name_three);
                data.putExtras(extras);
                setResult(200,data);
                finish();
            }
        });
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("选择工种");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        et_search = (TextView) findViewById(R.id.et_search);
        mRecyclerView1 = (RecyclerView) findViewById(R.id.mRecyclerView1);
        mRecyclerView2 = (RecyclerView) findViewById(R.id.mRecyclerView2);
        et_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initDataSuccess(List<LabourPositionClassBean> list) {
        all_list.clear();
        all_list.addAll(list);
        list_lift.clear();
        list_right.clear();
        for (LabourPositionClassBean bean : list) {
            if (bean.getType()==4 && bean.getParent_id()==job_class_id_one) {
                list_lift.add(bean);
            }
        }
        if (list_lift.size()>0) {
            LabourPositionClassBean positionClassBean = list_lift.get(0);
            positionClassBean.setSelect(true);
            int job_class_id = positionClassBean.getJob_class_id();
            for (LabourPositionClassBean bean : all_list) {
                if (bean.getType()==5 && bean.getParent_id()==job_class_id) {
                    list_right.add(bean);
                }
            }
        }
        leftAdapter.notifyDataSetChanged();
        rightAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshDataSuccess(List<LabourPositionClassBean> list) {

    }
}
