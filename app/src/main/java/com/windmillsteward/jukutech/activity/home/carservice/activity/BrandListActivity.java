package com.windmillsteward.jukutech.activity.home.carservice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.carservice.adapter.BrandListAdapter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.CarClassListBean;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 时间：2018/4/2/002
 * 作者：xjh
 */
public class BrandListActivity extends BaseActivity {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private RecyclerView mRecyclerView;
    private List<CarClassListBean.SeriesListBean> list;
    private BrandListAdapter adapter;

    private int id2;
    private String text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list);
        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            String data = extras.getString(Define.INTENT_DATA);
            list = JSON.parseArray(data,CarClassListBean.SeriesListBean.class);
            if (list==null) {
                list = new ArrayList<>();
            }
        }
        initView();
        initToolbar();
        initRecyclerView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode == 101) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                extras.putInt(CarBrandListActivity.ID2,id2);
                extras.putString(CarBrandListActivity.TEXT2,text2);
                intent.putExtras(extras);
                setResult(101,intent);
                finish();
            }
        }
    }

    private void initRecyclerView() {
        adapter = new BrandListAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                CarClassListBean.SeriesListBean value = list.get(position);
                id2 = value.getSeries_id();
                text2 = value.getSeries_name();
                List<CarClassListBean.SeriesListBean.VehicleModuleListBean> vehicle_module_list = value.getVehicle_module_list();
                if (vehicle_module_list==null || vehicle_module_list.size()==0) {
                    Bundle extras = new Bundle();
                    extras.putInt(CarBrandListActivity.ID2,id2);
                    extras.putString(CarBrandListActivity.TEXT2,text2);
                    intent.putExtras(extras);
                    setResult(101,intent);
                    finish();
                    return;
                }
                bundle.putString(Define.INTENT_DATA, JSON.toJSONString(vehicle_module_list));
                startAtvDonFinishForResult(VehicleListActivity.class,100, bundle);
            }
        });
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("品牌");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
    }
}
