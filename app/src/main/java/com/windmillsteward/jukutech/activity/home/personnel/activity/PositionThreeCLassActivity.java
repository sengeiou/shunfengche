package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.PositionTwoCLassActivityLeftAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.PositionTwoCLassActivityRightAdapter;
import com.windmillsteward.jukutech.activity.newpage.model.WorkTypeModel;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：单选职位
 * 时间：2018/5/16/016
 * 作者：xjh
 */
public class PositionThreeCLassActivity extends BaseActivity {
    public static final int RESULT_CODE = 222;
    public static final int REQUEST_CODE = 1026;
    public static final String JOB_CLASS_ONE_ID = "job_class_one_id";
    public static final String JOB_CLASS_TWO_ID = "job_class_two_id";
    public static final String JOB_CLASS_ONE_NAME = "job_class_one_name";
    public static final String JOB_CLASS_TWO_NAME = "job_class_two_name";
    public static final String JOB_CLASS_TWO_DESC = "job_class_two_desc";
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private TextView et_search;
    private RecyclerView mRecyclerView1;
    private RecyclerView mRecyclerView2;
    private List<WorkTypeModel> list_left;
    private List<WorkTypeModel.ListBean> list_right;
    private PositionTwoCLassActivityLeftAdapter leftAdapter;
    private PositionTwoCLassActivityRightAdapter rightAdapter;

    private int leftPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_class_three);
        initView();
        initToolbar();
        initRecyclerView();

        addCall(new NetUtil().setUrl(APIS.URL_TALENT_GET_JOB_CLASS_LIST_COMMON)
                .setCallBackData(new BaseNewNetModelimpl<List<WorkTypeModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        showErrorView();
                        showTips(msg);
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<WorkTypeModel>> respnse, String source) {
                        if (respnse.getData() != null) {
                            list_left.clear();
                            list_right.clear();
                            List<WorkTypeModel> data = respnse.getData();
                            list_left.addAll(data);
                            if (list_left.size() > 0) {
                                WorkTypeModel positionClassBean = list_left.get(0);
                                positionClassBean.setSelect(true);
                                if (positionClassBean.getList() != null)
                                    list_right.addAll(positionClassBean.getList());
                            }
                            leftAdapter.notifyDataSetChanged();
                            rightAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<WorkTypeModel>>>() {
                        }.getType();
                    }
                }).buildPost()
        );
    }

    private void initRecyclerView() {
        list_left = new ArrayList<>();
        list_right = new ArrayList<>();
        leftAdapter = new PositionTwoCLassActivityLeftAdapter(list_left);
        rightAdapter = new PositionTwoCLassActivityRightAdapter(list_right);
        mRecyclerView1.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView1.setAdapter(leftAdapter);
        mRecyclerView2.setAdapter(rightAdapter);
        leftAdapter.removeEmptyView();
        rightAdapter.removeEmptyView();

        leftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                leftPosition = position;
                list_right.clear();
                for (WorkTypeModel workTypeModel : list_left) {
                    workTypeModel.setSelect(false);
                }
                WorkTypeModel bean = list_left.get(position);
                bean.setSelect(true);
                List<WorkTypeModel.ListBean> list = bean.getList();
                if (list != null)
                    list_right.addAll(list);
                leftAdapter.notifyDataSetChanged();
                rightAdapter.notifyDataSetChanged();
            }
        });
        rightAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WorkTypeModel.ListBean classBean = list_right.get(position);
                WorkTypeModel left = list_left.get(leftPosition);
                Intent data = new Intent();
                Bundle extras = new Bundle();
                extras.putString(JOB_CLASS_ONE_ID, left.getJob_class_id_one()+"");
                extras.putString(JOB_CLASS_TWO_ID, classBean.getJob_class_id_two()+"");
                extras.putString(JOB_CLASS_ONE_NAME, left.getJob_class_id_one_name());
                extras.putString(JOB_CLASS_TWO_NAME, classBean.getJob_class_id_two_name());
                extras.putString(JOB_CLASS_TWO_DESC, classBean.getDescription());
                data.putExtras(extras);
                setResult(RESULT_CODE, data);
                finish();
            }
        });
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("选择期望职位");
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
}
