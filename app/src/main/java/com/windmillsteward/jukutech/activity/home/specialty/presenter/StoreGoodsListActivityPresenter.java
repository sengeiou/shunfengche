package com.windmillsteward.jukutech.activity.home.specialty.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.specialty.activity.StoreGoodsListActivityView;
import com.windmillsteward.jukutech.activity.home.specialty.fragment.GoodsListFragmentView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.GoodsListBean;
import com.windmillsteward.jukutech.bean.StoreInfoBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class StoreGoodsListActivityPresenter extends BaseNetModelImpl {
    private final int INIT_DATA=1;
    private final int INIT_STORE=5;
    private final int REFRESH_DATA=2;
    private final int NEXT_DATA=3;

    private StoreGoodsListActivityView view;

    public StoreGoodsListActivityPresenter(StoreGoodsListActivityView view) {
        this.view = view;
    }


    /**
     * 初始化数据
     * @param page 页数
     * @param page_count 数量
     * @param sort_id 排序
     */
    public void initData(int page,int page_count,int store_id,int commodity_category_id,int sort_id){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("store_id", store_id);
        map.put("commodity_category_id", commodity_category_id);
        map.put("sort_id", sort_id);
        httpInfo.setUrl(APIS.URL_STORE_COMMODITY_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);

        initStoreData(store_id);
    }

    private void initStoreData(int store_id){
        DataLoader dataLoader = new DataLoader(this, INIT_STORE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("store_id", store_id);
        httpInfo.setUrl(APIS.URL_STORE_INFO);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     */
    public void refreshData(int page,int page_count,int store_id,int commodity_category_id,int sort_id){
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("store_id", store_id);
        map.put("commodity_category_id", commodity_category_id);
        map.put("sort_id", sort_id);
        httpInfo.setUrl(APIS.URL_STORE_COMMODITY_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);

        initStoreData(store_id);
    }

    /**
     * 加载下一页
     */
    public void loadNextData(int page,int page_count,int store_id,int commodity_category_id,int sort_id){
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("store_id", store_id);
        map.put("commodity_category_id", commodity_category_id);
        map.put("sort_id", sort_id);
        httpInfo.setUrl(APIS.URL_STORE_COMMODITY_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case NEXT_DATA:
            case REFRESH_DATA:
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<GoodsListBean>>() {
                }.getType();
                break;
            case INIT_STORE:
                type = new TypeReference<BaseResultInfo<StoreInfoBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                GoodsListBean initData = (GoodsListBean) result.getData();
                if (initData!=null) {
                    view.initDataSuccess(initData);
                }
                break;
            case REFRESH_DATA:
                GoodsListBean refreshData = (GoodsListBean) result.getData();
                if (refreshData!=null) {
                    view.refreshDataSuccess(refreshData);
                } else {
                    view.refreshDataFailure();
                }
                break;
            case NEXT_DATA:
                GoodsListBean nextData = (GoodsListBean) result.getData();
                if (nextData!=null) {
                    view.loadNextDataSuccess(nextData);
                } else {
                    view.loadNextFailure();
                }
                break;
            case INIT_STORE:
                StoreInfoBean data = (StoreInfoBean) result.getData();
                if (data!=null) {
                    view.initHeaderDataSuccess(data);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,0);
    }
}
