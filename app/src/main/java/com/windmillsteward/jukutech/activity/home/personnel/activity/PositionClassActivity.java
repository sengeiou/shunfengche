package com.windmillsteward.jukutech.activity.home.personnel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.personnel.adapter.PositionClassActivityAdapter;
import com.windmillsteward.jukutech.activity.home.personnel.presenter.PositionClassActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.PositionClassBean;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 时间：2018/5/16/016
 * 作者：xjh
 */
public class PositionClassActivity extends BaseActivity implements PositionClassActivityView {

    private ImageView toolbar_iv_back;
    private EditText et_search;
    private RecyclerView mRecyclerView;

    private List<PositionClassBean> list;
    private PositionClassActivityAdapter adapter;
    private PositionClassActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_class);
        initView();
        initToolbar();
        initRecyclerView();
        presenter = new PositionClassActivityPresenter(this);
        presenter.initData("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==101 && resultCode ==200) {
            setResult(200,data);
            finish();
        }
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new PositionClassActivityAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PositionClassBean bean = list.get(position);
                if (PositionClassActivity.this.adapter.getType()==0) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Define.INTENT_DATA, bean.getJob_class_id());
                    bundle.putString(Define.INTENT_DATA_TWO, bean.getClass_name());
                    startAtvDonFinishForResult(PositionTwoCLassActivity.class,101, bundle);
                } else {
                    Intent data = new Intent();
                    Bundle extras = new Bundle();
                    extras.putInt(Define.INTENT_DATA, bean.getJob_class_id_one());
                    extras.putInt(Define.INTENT_DATA_TWO, bean.getJob_class_id_two());
                    extras.putInt(Define.INTENT_DATA_THREE, bean.getJob_class_id());
                    extras.putString(Define.INTENT_DATA_FOUR,bean.getJob_class_one_name());
                    extras.putString(Define.INTENT_DATA_FIVE,bean.getJob_class_two_name());
                    extras.putString(Define.INTENT_DATA_SIX,bean.getClass_name());
                    data.putExtras(extras);
                    setResult(200,data);
                    finish();
                }

            }
        });
        View header = LayoutInflater.from(this).inflate(R.layout.header_position_class, mRecyclerView, false);
        adapter.addHeaderView(header);
        adapter.removeEmptyView();
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        et_search = (EditText) findViewById(R.id.et_search);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = et_search.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    presenter.initData(trim);
                    SystemUtil.dismissKeyBorwd(PositionClassActivity.this);
                } else {
                    presenter.searchData(trim);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void initDataSuccess(List<PositionClassBean> list) {
        for (int i = list.size() - 1; i >= 0; i--) {
            PositionClassBean bean = list.get(i);
            if (bean.getType()!=3) {
                list.remove(i);
            }
        }
        this.list.clear();
        this.list.addAll(list);
        adapter.setType(0);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshDataSuccess(List<PositionClassBean> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.setType(1);
        adapter.notifyDataSetChanged();
    }
}
