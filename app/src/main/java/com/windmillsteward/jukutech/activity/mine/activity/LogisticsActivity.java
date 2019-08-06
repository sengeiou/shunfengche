package com.windmillsteward.jukutech.activity.mine.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.adapter.LogisticsActivityAdapter;
import com.windmillsteward.jukutech.activity.mine.presenter.LogisticsActivityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.bean.LogisticsInfoListBean;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.ImageUtils;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 物流
 * Created by Administrator on 2018/4/21 0021.
 */

public class LogisticsActivity extends BaseActivity implements LogisticsActivityView {

    private RecyclerView mRecyclerView;
    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;

    private List<LogisticsInfoListBean.LogisticsInfoBean> list;
    private LogisticsActivityAdapter adapter;
    private ImageView iv_pic;
    private TextView tv_status;
    private TextView tv_logistics_sn;
    private TextView tv_phone;

    private LogisticsActivityPresenter presenter;
    private String order_sn;
    private String logistics_single_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            order_sn = extras.getString(Define.INTENT_DATA);
            logistics_single_number = extras.getString(Define.INTENT_DATA_TWO);
        } else {
            finish();
            return;
        }
        initView();
        initToolbar();
        initRecyclerView();
        presenter = new LogisticsActivityPresenter(this);
        presenter.initData(getAccessToken(),order_sn,logistics_single_number);
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new LogisticsActivityAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        adapter.removeEmptyView();
        View header = LayoutInflater.from(this).inflate(R.layout.header_logistics, mRecyclerView, false);
        adapter.addHeaderView(header);
        iv_pic = (ImageView) header.findViewById(R.id.iv_pic);
        tv_status = (TextView) header.findViewById(R.id.tv_status);
        tv_logistics_sn = (TextView) header.findViewById(R.id.tv_logistics_sn);
        tv_phone = (TextView) header.findViewById(R.id.tv_phone);
        tv_phone.setVisibility(View.GONE);
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("物流信息");
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
    }

    @Override
    public void initDataSuccess(LogisticsInfoListBean bean) {
        // 物流状态：2-在途中（运输中）,3-签收(已签收),4-问题件 其他:已发货
        switch (bean.getStatus()) {
            case 2:
                tv_status.setText("在途中（运输中）");
                break;
            case 3:
                tv_status.setText("签收(已签收)");
                break;
            case 4:
                tv_status.setText("问题件");
                break;
            default:
                tv_status.setText("已发货");
                break;
        }
        x.image().bind(iv_pic,bean.getCommodity_img(), ImageUtils.defaulPicList());
        tv_logistics_sn.setText(bean.getName()+"："+bean.getLogistics_single_number());
        list.clear();
        list.addAll(bean.getLogistics_info());
        adapter.notifyDataSetChanged();
    }
}
