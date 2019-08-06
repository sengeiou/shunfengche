package com.windmillsteward.jukutech.activity.home.stayandtravel.presenter;

import android.util.Log;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.stayandtravel.fragment.TravelListView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.PriceBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.bean.TravelAreaBean;
import com.windmillsteward.jukutech.bean.TravelListBean;
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
 * 描述：
 * 时间：2018/1/25
 * 作者：xjh
 */

public class TravelListPresenter extends BaseNetModelImpl {

    private final int INIT_DATA = 0;
    private final int REFRESH_DATA = 1;
    private final int NEXT_DATA = 2;
    private final int AREA_LIST = 3;
    private final int TRAVEL_AREA = 4;
    private final int TRAVEL_PRICE = 5;

    private TravelListView view;
    public TravelListPresenter(TravelListView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     * @param page 页码
     * @param page_count 页数
     * @param travel_class_id 分类id
     * @param keyword 关键字
     * @param third_area_id 区域
     * @param price_id 价格id
     * @param travel_area_id 旅游区域id
     */
    public void initData(int page,int page_count, int travel_class_id, String keyword,int second_area_id,
                         int third_area_id, int price_id, int travel_area_id) {

        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("travel_class_id", travel_class_id);
        map.put("keyword", keyword);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("price_id", price_id);
        map.put("travel_area_id", travel_area_id);
        httpInfo.setUrl(APIS.URL_TRAVEL_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     * @param page 页码
     * @param page_count 页数
     * @param travel_class_id 分类id
     * @param keyword 关键字
     * @param third_area_id 区域
     * @param price_id 价格id
     * @param travel_area_id 旅游区域id
     */
    public void refreshData(int page,int page_count, int travel_class_id, String keyword,int second_area_id,
                         int third_area_id, int price_id, int travel_area_id) {

        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("travel_class_id", travel_class_id);
        map.put("keyword", keyword);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("price_id", price_id);
        map.put("travel_area_id", travel_area_id);
        httpInfo.setUrl(APIS.URL_TRAVEL_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     * @param page 页码
     * @param page_count 页数
     * @param travel_class_id 分类id
     * @param keyword 关键字
     * @param third_area_id 区域
     * @param price_id 价格id
     * @param travel_area_id 旅游区域id
     */
    public void loadNextData(int page,int page_count, int travel_class_id, String keyword,int second_area_id,
                            int third_area_id, int price_id, int travel_area_id) {

        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("travel_class_id", travel_class_id);
        map.put("keyword", keyword);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("price_id", price_id);
        map.put("travel_area_id", travel_area_id);
        httpInfo.setUrl(APIS.URL_TRAVEL_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载地区数据
     */
    public void loadAreaData(int second_area_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, AREA_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("second_area_id", second_area_id);
        httpInfo.setUrl(APIS.URL_THIRD_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载旅行地区
     */
    public void loadTravelAreaData(){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, TRAVEL_AREA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        httpInfo.setUrl(APIS.URL_TRAVEL_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载旅行价格
     */
    public void loadTravelPriceData(){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, TRAVEL_PRICE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        httpInfo.setUrl(APIS.URL_TRAVEL_PRICE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case REFRESH_DATA:
            case NEXT_DATA:
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<TravelListBean>>() {
                }.getType();
                break;
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>() {
                }.getType();
                break;
            case TRAVEL_AREA:
                type = new TypeReference<BaseResultInfo<List<TravelAreaBean>>>() {
                }.getType();
                break;
            case TRAVEL_PRICE:
                type = new TypeReference<BaseResultInfo<List<PriceBean>>>() {
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
                TravelListBean data = (TravelListBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case REFRESH_DATA:
                TravelListBean refreshData = (TravelListBean) result.getData();
                if (refreshData!=null) {
                    view.refreshDataSuccess(refreshData);
                }
                break;
            case NEXT_DATA:
                TravelListBean nextData = (TravelListBean) result.getData();
                if (nextData!=null) {
                    view.loadNextDataSuccess(nextData);
                }
                break;
            case AREA_LIST:
                view.dismiss();
                List<ThirdAreaBean> areaBeans = (List<ThirdAreaBean>) result.getData();
                if (areaBeans!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    Map<String,Object> all = new HashMap<>();
                    all.put("id",0);
                    all.put("text","全部");
                    maps.add(all);
                    for (ThirdAreaBean bean : areaBeans) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getThird_area_id());
                        map.put("text",bean.getThird_area_name());
                        maps.add(map);
                    }
                    view.loadAreaDataSuccess(maps);
                }
                break;
            case TRAVEL_AREA:
                view.dismiss();
                List<TravelAreaBean> travelAreaBeans = (List<TravelAreaBean>) result.getData();
                if (travelAreaBeans!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    Map<String,Object> all = new HashMap<>();
                    all.put("id",0);
                    all.put("text","全部");
                    maps.add(all);
                    for (TravelAreaBean bean : travelAreaBeans) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getArea_id());
                        map.put("text",bean.getArea_name());
                        maps.add(map);
                    }
                    view.loadTravelAreaDataSuccess(maps);
                }
                break;
            case TRAVEL_PRICE:
                view.dismiss();
                List<PriceBean> priceBeans = (List<PriceBean>) result.getData();
                if (priceBeans!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    Map<String,Object> all = new HashMap<>();
                    all.put("id",0);
                    all.put("text","全部");
                    maps.add(all);
                    for (PriceBean bean : priceBeans) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getPrice_id());
                        map.put("text",bean.getPrice_name());
                        maps.add(map);
                    }
                    view.loadTravelPriceSuccess(maps);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                view.showTips("加载失败",0);
                break;
            case REFRESH_DATA:
                view.refreshDataFailure();
                break;
            case NEXT_DATA:
                view.loadNextDataFailure();
                break;
        }
    }
}
