package com.windmillsteward.jukutech.activity.map;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.fragment.activity.SelectCityActivity;
import com.windmillsteward.jukutech.base.BaseInitActivity;
import com.windmillsteward.jukutech.base.BasePresenter;
import com.windmillsteward.jukutech.base.KV;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class SearchBaiduCityActivity extends BaseInitActivity {

    @Bind(R.id.ed_search)
    EditText edSearch;
    @Bind(R.id.tv_cancel)
    TextView tvCancel;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.ll_root)
    LinearLayout llRoot;
    @Bind(R.id.tv_city)
    TextView tvCity;

    private PoiSearch mPoiSearch;
    private SuggestionSearch mysearch;
    private String city;

    @Override
    protected void initView(View view) {
        hidTitleView();
        showContentView();

        city = KV.get(Define.CURR_CITY_NAME, "淄博").replace("市", "");
        tvCity.setText(city);
    }

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_search_baidu_city;
    }

    @Override
    protected void initData() {
        initAdapter();

        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
//        mysearch = SuggestionSearch.newInstance();//单例模式
//        mysearch.setOnGetSuggestionResultListener(suggestionListener);
        tvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!chekcLocationPermission()) {
                    showTips("请确认是否开启了定位权限", 1);
                    return;
                }
                Intent intent2 = new Intent();
                intent2.putExtra("type", SelectCityActivity.TYPE_SELECT);
                startAtvDonFinishForResult(SelectCityActivity.class, 100, intent2);
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s.toString())) {
                    mPoiSearch.searchInCity((new PoiCitySearchOption())
                            .city(city)
                            .keyword(s.toString())
                            .pageCapacity(50)
                            .isReturnAddr(true));
                    tvCancel.setText("取消");
                } else {
                    tvCancel.setText("取消");
                    list.clear();
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

//    OnGetSuggestionResultListener suggestionListener = new OnGetSuggestionResultListener() {
//        @Override
//        public void onGetSuggestionResult(SuggestionResult suggestionResult) {
//            List<SuggestionResult.SuggestionInfo> allSuggestions = suggestionResult.getAllSuggestions();
//            if (allSuggestions != null){
//                list.clear();
//                for (SuggestionResult.SuggestionInfo info:allSuggestions ){
//                    PoiInfo poiInfo = new PoiInfo();
//                    poiInfo.setAddress(info.key);
//                    poiInfo.setName(info.city);
//                    poiInfo.setLocation(info.getPt());
//                    list.add(poiInfo);
//                }
//                adapter.loadMoreEnd();
//                adapter.notifyDataSetChanged();
//            }
////            for (SuggestionResult.SuggestionInfo aaa:allSuggestions ){
////            }
//        }
//    };

    OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {

        public void onGetPoiResult(PoiResult result) {
            if (result.getAllPoi() != null) {
                list.clear();
                list.addAll(result.getAllPoi());
                adapter.loadMoreEnd();
                adapter.notifyDataSetChanged();
            } else {
                list.clear();
                adapter.loadMoreEnd();
                adapter.notifyDataSetChanged();
            }
        }

        public void onGetPoiDetailResult(PoiDetailResult result) {
            //获取Place详情页检索结果
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

        }

        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
        }
    };

    @Override
    protected void refreshPageData() {

    }


    private RecyclerViewAdapter adapter;
    private List<PoiInfo> list;

    public void initAdapter() {
        list = new ArrayList<>();
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setEnableLoadMore(false);

        //事件监听
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PoiInfo poiInfo = list.get(position);

                Intent intent = new Intent();
                intent.putExtra("address", poiInfo.name+"(" +poiInfo.address+ ")");
                intent.putExtra("lat", poiInfo.location.latitude);
                intent.putExtra("lng", poiInfo.location.longitude);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            city = data.getStringExtra(Define.INTENT_DATA).replace("市", "");;
            tvCity.setText(TextUtils.isEmpty(city)?"淄博":city);
            list.clear();
            adapter.notifyDataSetChanged();
        }
    }

    class RecyclerViewAdapter extends BaseQuickAdapter<PoiInfo, BaseViewHolder> {

        public RecyclerViewAdapter(@Nullable List<PoiInfo> data) {
            super(R.layout.item_recycler_search, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, PoiInfo item) {
            helper.setText(R.id.tv_01, item.name).
                    setText(R.id.tv_02, item.address);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPoiSearch != null)
            mPoiSearch.destroy();
    }
}
