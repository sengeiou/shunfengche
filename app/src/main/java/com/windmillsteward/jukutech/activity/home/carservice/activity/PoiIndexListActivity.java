package com.windmillsteward.jukutech.activity.home.carservice.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.carservice.adapter.PoiIndexListAdapter;
import com.windmillsteward.jukutech.activity.home.fragment.activity.SelectCityActivity;
import com.windmillsteward.jukutech.base.BaseActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 * 时间：2018/3/26/026
 * 作者：xjh
 */
public class PoiIndexListActivity extends BaseActivity implements View.OnClickListener {

    private static final int SELECT_CITY = 1000;

    private TextView tv_city;
    private LinearLayout lay_ll_search;
    private LinearLayout lay_ll_top;
    private RecyclerView mRecyclerView;
    private EditText tv_search_hint;
    private TextView iv_cancel;

//    private PoiSearch mPoiSearch ;
    private LocationClient mLocClient;

    private int pageSize=15;
    private List<PoiInfo> list;
    private PoiIndexListAdapter adapter;
    private String keyword;
    private String currCity;
    private boolean isClear;
    private double latitude;
    private double longitude;
    private List<PoiInfo> history_list;
    private SuggestionSearch mSuggestionSearch ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poiindex_list);
        initView();
        initRecyclerView();
        initLocation();
        showDialog("正在获取当前位置");
//        mPoiSearch  = PoiSearch.newInstance();
        mSuggestionSearch  = SuggestionSearch.newInstance();
//        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
        mSuggestionSearch.setOnGetSuggestionResultListener(listener);
        currCity = getCurrCityName();
        tv_city.setText(currCity);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==SELECT_CITY) {
            if (resultCode == RESULT_OK){
                String city_name = data.getStringExtra(Define.INTENT_DATA);
                tv_city.setText(TextUtils.isEmpty(city_name)?"全国":city_name);
                currCity = TextUtils.isEmpty(city_name)?"全国":city_name;
            }
        }
    }

    private void loadHistory() {
        history_list = Hawk.get(Define.POI_INDEX_HISTORY);
        if (history_list!=null) {
            list.clear();
            list.addAll(history_list);
            adapter.notifyDataSetChanged();
        }
        adapter.loadMoreEnd();
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                dismiss();
                if (bdLocation == null) {
                    showTips("获取位置失败",0);
                    return;
                }
                latitude = Double.valueOf(String.valueOf(bdLocation.getLatitude()));
                longitude = Double.valueOf(String.valueOf(bdLocation.getLongitude()));
                adapter.setLatitude(latitude);
                adapter.setLongitude(longitude);
                if (latitude==0 || longitude==0) {
                    showTips("获取位置失败",0);
                }
                mLocClient.unRegisterLocationListener(this);

                loadHistory();
            }
        });
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(150); // 大于1000才循环
        option.setIsNeedAddress(true);
        mLocClient.setLocOption(option);
        mLocClient.start();
    }

    private void initRecyclerView() {
        list = new ArrayList<>();
        adapter = new PoiIndexListAdapter(list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        adapter.setEnableLoadMore(false);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (TextUtils.isEmpty(keyword)) {
                    return;
                }
                pageSize+=15;
//                mPoiSearch.searchInCity((new PoiCitySearchOption())
//                        .city(currCity)
//                        .keyword(keyword)
//                        .pageNum(pageSize));
            }
        }, mRecyclerView);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PoiInfo info = list.get(position);
                String name = info.name;
                String address = info.address;
                LatLng location = info.location;
                Intent intent = new Intent();
                Bundle extras = new Bundle();
                extras.putString(Define.INTENT_DATA,name);
                extras.putParcelable(Define.INTENT_DATA_TWO,location);
                extras.putString(Define.INTENT_DATA_THREE,address);
                intent.putExtras(extras);
                setResult(200,intent);
                finish();
                if (history_list==null) {
                    history_list = new ArrayList<>();
                }
                for (PoiInfo poiInfo : history_list) {
                    if (TextUtils.equals(poiInfo.name,info.name)) {
                        history_list.remove(poiInfo);
                    }
                }
                history_list.add(0,info);
                Hawk.put(Define.POI_INDEX_HISTORY, history_list);
            }
        });

        View header = LayoutInflater.from(this).inflate(R.layout.header_poi_history, mRecyclerView, false);
        adapter.addHeaderView(header);
        TextView tv_clear = header.findViewById(R.id.tv_clear);
        tv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hawk.put(Define.POI_INDEX_HISTORY, new ArrayList<PoiInfo>());
                loadHistory();
            }
        });
        adapter.removeEmptyView();
    }

    OnGetSuggestionResultListener listener = new OnGetSuggestionResultListener() {
        public void onGetSuggestionResult(SuggestionResult res) {
            dismiss();
            list.clear();
            adapter.removeAllHeaderView();
            if (res == null || res.getAllSuggestions() == null) {
                return;
                //未找到相关结果
            }
            List<SuggestionResult.SuggestionInfo> allSuggestions = res.getAllSuggestions();
            if (allSuggestions.size()>0) {
                for (SuggestionResult.SuggestionInfo suggestion : allSuggestions) {
                    PoiInfo poiInfo = new PoiInfo();
                    poiInfo.name = suggestion.key;
                    poiInfo.address = suggestion.key + suggestion.district + suggestion.key;
                    poiInfo.location = suggestion.pt;
                    list.add(poiInfo);
                }
            }
            adapter.notifyDataSetChanged();
        }
    };

    OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener(){

        public void onGetPoiResult(PoiResult result){
            //获取POI检索结果
            dismiss();
            List<PoiInfo> allPoi = result.getAllPoi();
            if (isClear) {
                list.clear();
                isClear = false;
            }
            if (allPoi!=null) {
                list.addAll(allPoi);
                adapter.loadMoreComplete();
            } else {
                adapter.loadMoreEnd();
            }
            adapter.notifyDataSetChanged();
            adapter.removeAllHeaderView();
        }

        public void onGetPoiDetailResult(PoiDetailResult result){
            //获取Place详情页检索结果
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

        }
    };

    private void initView() {
        tv_city = (TextView) findViewById(R.id.tv_city);
        lay_ll_search = (LinearLayout) findViewById(R.id.lay_ll_search);
        lay_ll_top = (LinearLayout) findViewById(R.id.lay_ll_top);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        tv_search_hint = (EditText) findViewById(R.id.tv_search_hint);
        iv_cancel = (TextView) findViewById(R.id.iv_cancel);
        iv_cancel.setOnClickListener(this);
        tv_search_hint.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                keyword = tv_search_hint.getText().toString().trim();
                if (TextUtils.isEmpty(keyword)) {
                    iv_cancel.setText("取消");
                } else {
                    iv_cancel.setText("搜索");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        tv_city.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mPoiSearch.destroy();
        mSuggestionSearch.destroy();
        if (mLocClient!=null) {
            mLocClient.stop();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_city:
                intent.putExtra(Define.INTENT_DATA, currCity);
                intent.putExtra(Define.INTENT_DATA_TWO, 1);
                startAtvDonFinishForResult(SelectCityActivity.class,SELECT_CITY);
                break;
            case R.id.iv_cancel:
                SystemUtil.dismissKeyBorwd(this);
                String keyword = tv_search_hint.getText().toString().trim();
                if (TextUtils.isEmpty(keyword)) {
                    finish();
                } else {
                    showDialog("");
                    pageSize=15;
//                    mPoiSearch.searchInCity((new PoiCitySearchOption())
//                            .city(currCity)
//                            .keyword(keyword)
//                            .pageNum(pageSize));
                    isClear = true;
//                    mSuggestionSearch.requestSuggestion((new SuggestionSearchOption())
//                            .keyword(keyword)
//                            .city(currCity));
                    SuggestionSearchOption suggestionSearchOption = new SuggestionSearchOption();
                    mSuggestionSearch.requestSuggestion(suggestionSearchOption.keyword(keyword).city(currCity));
                }
                break;
        }
    }
}
