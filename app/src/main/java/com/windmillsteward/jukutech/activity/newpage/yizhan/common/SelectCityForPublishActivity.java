package com.windmillsteward.jukutech.activity.newpage.yizhan.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.alibaba.fastjson.TypeReference;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.fragment.activity.SelectCityView;
import com.windmillsteward.jukutech.activity.home.fragment.adapter.SelectCityAdapter;
import com.windmillsteward.jukutech.activity.home.fragment.presenter.SelectCityPresenter;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.bean.CityBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.customview.AssortView;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：城市选择(用再发布页面里选择发布地区时，切换城市)
 * author:cyq
 * 2018-03-02
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SelectCityForPublishActivity extends BaseActivity implements View.OnClickListener, SelectCityView, View.OnKeyListener {
    public static final int GET_CITY_REQUEST_CODE = 100;

    private EditText et_search_key;
    private ExpandableListView elv_city;
    private AssortView assort_member;
    private ImageView iv_back;

    private SelectCityPresenter selectCityPresenter;

    private SelectCityAdapter selectCityAdapter;

    private List<CityBean> cityList = new ArrayList<>();

    private android.support.v7.widget.RecyclerView mRecycler_01;
    private LinearLayout mLl_recent;
    private TextView tv_recent_city;
    private TextView tv_recent_area;

    private LocationClient mLocClient;
    private MyLocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        initView();
        iniLocation();
        initData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            setResult(200, data);
            finish();
        }
    }

    private void initView() {

        iv_back = (ImageView) findViewById(R.id.iv_back);
        et_search_key = (EditText) findViewById(R.id.et_search_key);
        elv_city = (ExpandableListView) findViewById(R.id.elv_city);
        assort_member = (AssortView) findViewById(R.id.assort_member);

        initHeaderView();
        mLl_recent.setOnClickListener(this);
        tv_recent_city.setOnClickListener(this);

        iv_back.setOnClickListener(this);
        et_search_key.setOnKeyListener(this);


        elv_city.setOnGroupClickListener(onGroupClickListener);
        elv_city.setOnChildClickListener(onChildClickListener);
        assort_member.setOnTouchAssortListener(getAssortListener(this));// 中间字母弹窗
    }

    private void initData() {
        selectCityAdapter = new SelectCityAdapter(this, new ArrayList<CityBean>());

        elv_city.setAdapter(selectCityAdapter);

        selectCityPresenter = new SelectCityPresenter(this);
        selectCityPresenter.getCityList(et_search_key.getText().toString());
        loadHotCityData();//获取热门城市
    }

    private void initHeaderView() {
        View view = View.inflate(this, R.layout.header_search_city_for_publish, null);
        mRecycler_01 = (android.support.v7.widget.RecyclerView) view.findViewById(R.id.recycler_01);
        mLl_recent = (LinearLayout) view.findViewById(R.id.ll_recent);
        tv_recent_city = (TextView) view.findViewById(R.id.tv_recent_city);
        tv_recent_area = (TextView) view.findViewById(R.id.tv_recent_area);

        initHotAdapter();

        elv_city.addHeaderView(view);

    }

    private void iniLocation() {
        mLocClient = new LocationClient(this);
        locationListener = new MyLocationListener();
        mLocClient.registerLocationListener(locationListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setIsNeedAddress(true);//允许获取当前的位置信息，不开启则获取不了当前城市
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(30000); // 大于1000才循环
        option.setIsNeedAddress(true);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }


    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                return;
            }
           String currName = location.getCity();
            String district = location.getDistrict();
            tv_recent_area.setText(TextUtils.isEmpty(district) ? "地区" : district);
            tv_recent_city.setText(TextUtils.isEmpty(currName) ? "" : currName);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_recent_city:
                if (cityList == null) {
                    showTips("请重新获取定位", 1);
                    return;
                }

                for (int i = 0; i < cityList.size(); i++) {
                    if (cityList.get(i).getArea_name().contains(tv_recent_city.getText().toString())) {
                        int city_id = cityList.get(i).getArea_id();
                        String city_name = cityList.get(i).getArea_name();
                        Intent intent = getIntent();
                        intent.putExtra("city_name", city_name);
                        intent.putExtra("city_id", city_id);
                        setResult(RESULT_OK, intent);
                        finish();
                        break;
                    }
                }
                break;
        }
    }



    private void loadHotCityData() {
        selectCityPresenter.getHotCityList(new BaseNewNetModelimpl<List<CityBean>>() {
            @Override
            protected void onFail(int type, String msg) {
                dismiss();
                showTips(msg);
            }

            @Override
            protected void onSuccess(int code, BaseResultInfo<List<CityBean>> respnse, String source) {
                dismiss();
                if (respnse.getData() != null)
                    getHotCityListSuccess(respnse.getData());
            }

            @Override
            protected Type getType() {
                return new TypeReference<BaseResultInfo<List<CityBean>>>() {
                }.getType();
            }
        });
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_ENTER) {
            SystemUtil.dismissKeyBorwd(this);
            String key = et_search_key.getText().toString();
            selectCityPresenter.getCityList(key);
        }
        return false;
    }

    /**
     * 右边字母导航栏监听器
     */
    private AssortView.OnTouchAssortListener getAssortListener(final Context ctx) {
        AssortView.OnTouchAssortListener assortListener = new AssortView.OnTouchAssortListener() {
            View layoutView = LayoutInflater.from(ctx).inflate(
                    R.layout.layout_dialog_alert_menu, null);
            TextView text = (TextView) layoutView.findViewById(R.id.content);
            PopupWindow popupWindow;

            @Override
            public void onTouchAssortUP() {
                if (popupWindow != null)
                    popupWindow.dismiss();
                popupWindow = null;
            }

            @Override
            public void onTouchAssortListener(String str) {
                int index = selectCityAdapter.getAssort().getMemberHashList()
                        .indexOfKey(str);
                if (index != -1) {
                    elv_city.setSelectedGroup(index);
                }
                if (popupWindow != null) {
                    text.setText(str);
                } else {
                    popupWindow = new PopupWindow(layoutView, 200, 200, false);
                    // 显示在Activity的根视图中心
                    popupWindow.showAtLocation(getWindow().getDecorView(),
                            Gravity.CENTER, 0, 0);
                }
                text.setText(str);
            }
        };
        return assortListener;
    }

    // 二级列表中的父级点击跳转
    ExpandableListView.OnGroupClickListener onGroupClickListener = new ExpandableListView.OnGroupClickListener() { // 点击父列表不做处理
        public boolean onGroupClick(ExpandableListView parent, View v,
                                    int groupPosition, long id) {
            return true;
        }
    };

    // 二级列表中的子级点击跳转,获取城市id，并且保存
    ExpandableListView.OnChildClickListener onChildClickListener = new ExpandableListView.OnChildClickListener() {
        public boolean onChildClick(ExpandableListView parent, View v,
                                    int groupPosition, int childPosition, long id) {
            CityBean cityBean = (CityBean) selectCityAdapter.getChild(groupPosition, childPosition);
            int city_id = cityBean.getArea_id();
            String city_name = cityBean.getArea_name();
            Intent intent = getIntent();
            intent.putExtra("city_name", city_name);
            intent.putExtra("city_id", city_id);
            setResult(RESULT_OK, intent);
            finish();

            return true;
        }
    };

    @Override
    public void getCityListSuccess(List<CityBean> list) {
        if (list == null) {
            return;
        }
        this.cityList = list;
        selectCityAdapter.setList(list);
        // 展开所有
        for (int i = 0, length = selectCityAdapter.getGroupCount(); i < length; i++) {
            elv_city.expandGroup(i);
        }
    }

    public void getHotCityListSuccess(List<CityBean> list) {
        listHot.clear();
        listHot.addAll(list);
        adapterHot.notifyDataSetChanged();
    }


    public void getCityAreaListSuccess(List<ThirdAreaBean> list) {
        listArea.clear();
        listArea.addAll(list);
        adapterArea.notifyDataSetChanged();


    }

    @Override
    public void getCityListFailed(int code, String msg) {
        showTips(msg, 1);
    }


    private HotRecyclerViewAdapter adapterHot;
    private List<CityBean> listHot;

    public void initHotAdapter() {
        listHot = new ArrayList<>();
        adapterHot = new HotRecyclerViewAdapter(listHot);
        mRecycler_01.setLayoutManager(new GridLayoutManager(this, 3));
        mRecycler_01.setAdapter(adapterHot);
        mRecycler_01.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
        mRecycler_01.setHasFixedSize(true);

        //事件监听
        adapterHot.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CityBean cityBean = listHot.get(position);
                Intent intent = getIntent();
                intent.putExtra("city_name", cityBean.getArea_name());
                intent.putExtra("city_id", cityBean.getArea_id());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    class HotRecyclerViewAdapter extends BaseQuickAdapter<CityBean, BaseViewHolder> {

        public HotRecyclerViewAdapter(@Nullable List<CityBean> data) {
            super(R.layout.item_recycler_hot_city, data, false);
        }

        @Override
        protected void convert(BaseViewHolder helper, CityBean item) {
            helper.setText(R.id.tv_name, item.getArea_name());
        }
    }

    private RecyclerViewAdapter adapterArea;
    private List<ThirdAreaBean> listArea;



    class RecyclerViewAdapter extends BaseQuickAdapter<ThirdAreaBean, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<ThirdAreaBean> data) {
            super(R.layout.item_recycler_area_city, data, false);
        }

        @Override
        protected void convert(BaseViewHolder helper, ThirdAreaBean item) {
            helper.setText(R.id.tv_name, item.getThird_area_name());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocClient != null) {
            mLocClient.stop();
        }
    }
}
