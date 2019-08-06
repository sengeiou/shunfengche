package com.windmillsteward.jukutech.activity.home.commons.quickindex;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 时间：2018/2/2
 * 作者：xjh
 */

public class QuickIndexAreaActivity extends BaseActivity implements QuickIndexAreaView, View.OnClickListener {

    private ImageView toolbar_iv_back;
    private TextView toolbar_iv_title;
    private ImageView toolbar_iv_right;
    private TextView toolbar_tv_right;
    private RecyclerView mRecyclerView;
    private QuickIndexAreaPresenter presenter;
    private List<ThirdAreaBean> list;
    private QuickIndexAreaAdapter adapter;

    private LocationClient mLocationClient;
    private TextView tv_area;

    private int city_id;
    private String city_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_index_area);

        Bundle extras = getIntent().getExtras();
        if (extras!=null) {
            city_id = extras.getInt(Define.INTENT_DATA);
            city_name = extras.getString(Define.INTENT_DATA_TWO);
        }
        initView();
        initToolbar();
        initRecyclerView();
        // 加载区域
        // 定位
        mLocationClient = new LocationClient(getApplicationContext());
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(150); // 大于1000才循环
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);

        //声明LocationClient类
        mLocationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {

                if (bdLocation==null) {
                    return;
                }
                String addr = bdLocation.getAddrStr();    //获取详细地址信息
                String country = bdLocation.getCountry();    //获取国家
                String province = bdLocation.getProvince();    //获取省份
                String city = bdLocation.getCity();    //获取城市
                String district = bdLocation.getDistrict();    //获取区县
                String street = bdLocation.getStreet();    //获取街道信息

                if (tv_area!=null) {
                    tv_area.setText("当前位置："+(TextUtils.isEmpty(city)?"未知":(city+district+street)));
                }
                mLocationClient.unRegisterLocationListener(this);
            }
        });

        mLocationClient.start();

        presenter = new QuickIndexAreaPresenter(this);
        presenter.initData((city_id==0)?getCurrCityId():city_id);
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new QuickIndexAreaAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (city_id!=0) {
                    Bundle bundle = new Bundle();
                    Intent data = new Intent();
                    bundle.putInt(Define.INTENT_DATA,city_id);
                    bundle.putString(Define.INTENT_DATA_TWO,city_name);
                    bundle.putInt(Define.INTENT_DATA_THREE,list.get(position).getThird_area_id());
                    bundle.putString(Define.INTENT_DATA_FOUR,list.get(position).getThird_area_name());
                    data.putExtras(bundle);
                    setResult(200, data);
                    finish();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt(QuickIndexStreetActivity.THIRD_ID,list.get(position).getThird_area_id());
                    bundle.putString(QuickIndexStreetActivity.THIRD_NAME,list.get(position).getThird_area_name());
                    startAtvDonFinishForResult(QuickIndexStreetActivity.class,100, bundle);
                }
            }
        });

        View header = LayoutInflater.from(this).inflate(R.layout.item_quick_index_area, ((ViewGroup) mRecyclerView.getParent()), false);
        tv_area = (TextView) header.findViewById(R.id.tv_area);
        tv_area.setOnClickListener(this);
        ImageView iv_jump = (ImageView) header.findViewById(R.id.iv_jump);
        iv_jump.setVisibility(View.GONE);
        adapter.addHeaderView(header);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100 && resultCode == QuickIndexStreetActivity.RESULT_CODE) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                setResult(200, data);
                finish();
            }
        }
    }

    private void initToolbar() {
        handleBackEvent(toolbar_iv_back);
        toolbar_iv_title.setText("区域");
    }

    private void initView() {
        toolbar_iv_back = (ImageView) findViewById(R.id.toolbar_iv_back);
        toolbar_iv_title = (TextView) findViewById(R.id.toolbar_iv_title);
        toolbar_iv_right = (ImageView) findViewById(R.id.toolbar_iv_right);
        toolbar_tv_right = (TextView) findViewById(R.id.toolbar_tv_right);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
    }

    @Override
    public void initDataSuccess(List<ThirdAreaBean> list) {
        this.list.addAll(list);
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                adapter.setNewData(QuickIndexAreaActivity.this.list);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_area:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationClient!=null) {
            mLocationClient.stop();
        }
    }
}
