package com.windmillsteward.jukutech.activity.home.capitalmanager.presenter;

import android.util.Log;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.capitalmanager.fragment.FinancingListFragmentView;
import com.windmillsteward.jukutech.activity.home.houselease.fragment.BuyHouseView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.CapitalListBean;
import com.windmillsteward.jukutech.bean.HouseMoreBean;
import com.windmillsteward.jukutech.bean.HousePriceBean;
import com.windmillsteward.jukutech.bean.HouseSealListBean;
import com.windmillsteward.jukutech.bean.NameAndIdBean;
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
 * 描述：
 * 时间：2018/1/19
 * 作者：xjh
 */

public class FinancingListFragmentPresenter extends BaseNetModelImpl {

    private final int INIT_DATA = 1;
    private final int REFRESH_DATA = 2;
    private final int NEXT_DATA = 3;
    private static final int AREA_LIST = 4;
    private static final int QUERY_CONDITION = 5;
    private static final int QUERY_CONDITION_1 = 6;

    private FinancingListFragmentView view;

    public FinancingListFragmentPresenter(FinancingListFragmentView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     * @param type 1.理财 2.贷款
     * @param page 页
     * @param page_count 大小
     */
    public void initData(int type,int page, int page_count,int second_area_id,int third_area_id,int sorting_type,int product_type,int deadline_id,String param_name){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("type", type);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("sorting_type", sorting_type);
        map.put("product_type", product_type);
        map.put("deadline_id", deadline_id);
        map.put("param_name", param_name);
        httpInfo.setUrl(APIS.URL_CAPITAL_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     * @param page 页
     * @param param_name 搜索内容
     * @param type 1卖房，2租房
     */
    public void refreshData(int type,int page, int page_count,int second_area_id,int third_area_id,int sorting_type,int product_type,int deadline_id,String param_name){
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("type", type);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("sorting_type", sorting_type);
        map.put("product_type", product_type);
        map.put("deadline_id", deadline_id);
        map.put("param_name", param_name);
        httpInfo.setUrl(APIS.URL_CAPITAL_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     * @param page 页
     * @param param_name 搜索内容
     * @param type 1卖房，2租房
     */
    public void loadNextData(int type,int page, int page_count,int second_area_id,int third_area_id,int sorting_type,int product_type,int deadline_id,String param_name){

        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("type", type);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("sorting_type", sorting_type);
        map.put("product_type", product_type);
        map.put("deadline_id", deadline_id);
        map.put("param_name", param_name);
        httpInfo.setUrl(APIS.URL_CAPITAL_LIST);
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
     * 排序
     */
    public void loadSortingData() {
            List<Map<String,Object>> maps = new ArrayList<>();
            Map<String,Object> map1 = new HashMap<>();
            map1.put("id",1);
            map1.put("text","由低到高");
            maps.add(map1);
            Map<String,Object> map2 = new HashMap<>();
            map2.put("id",2);
            map2.put("text","由高到低");
            maps.add(map2);
            view.loadSortingDataSuccess(maps);
    }

    /**
     * 产品分类
     */
    public void loadProductTypeData() {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, QUERY_CONDITION);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type", 1);
        httpInfo.setUrl(APIS.URL_QUERY_CONDITION);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 期限
     */
    public void loadDeadlineData() {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, QUERY_CONDITION_1);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type", 2);
        httpInfo.setUrl(APIS.URL_QUERY_CONDITION);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }
    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
            case REFRESH_DATA:
            case NEXT_DATA:
                type = new TypeReference<BaseResultInfo<CapitalListBean>>(){}.getType();
                break;
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>(){}.getType();
                break;
            case QUERY_CONDITION:
            case QUERY_CONDITION_1:
                type = new TypeReference<BaseResultInfo<List<NameAndIdBean>>>(){}.getType();
                break;

        }

        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case INIT_DATA:
                CapitalListBean data = (CapitalListBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case REFRESH_DATA:
                CapitalListBean refreshData = (CapitalListBean) result.getData();
                if (refreshData!=null) {
                    view.refreshDataSuccess(refreshData);
                }
                break;
            case NEXT_DATA:
                CapitalListBean nextData = (CapitalListBean) result.getData();
                if (nextData!=null) {
                    view.loadNextDataSuccess(nextData);
                }
                break;
            case AREA_LIST:
                List<ThirdAreaBean> areaBeans = (List<ThirdAreaBean>) result.getData();
                if (areaBeans!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    HashMap<String, Object> all = new HashMap<>();
                    all.put("id",0);
                    all.put("text","全部");
                    maps.add(0, all);
                    for (ThirdAreaBean bean : areaBeans) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getThird_area_id());
                        map.put("text",bean.getThird_area_name());
                        maps.add(map);
                    }
                    view.loadAreaDataSuccess(maps);
                }
                break;
            case QUERY_CONDITION:
                List<NameAndIdBean> priceBeans = (List<NameAndIdBean>) result.getData();
                if (priceBeans!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    Map<String,Object> all = new HashMap<>();
                    all.put("id",0);
                    all.put("text","全部");
                    maps.add(0,all);
                    for (NameAndIdBean bean : priceBeans) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getId());
                        map.put("text",bean.getName());
                        maps.add(map);
                    }
                    view.loadProductTypeDataSuccess(maps);
                }
                break;
            case QUERY_CONDITION_1:
                List<NameAndIdBean> priceBeans1 = (List<NameAndIdBean>) result.getData();
                if (priceBeans1!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    Map<String,Object> all = new HashMap<>();
                    all.put("id",0);
                    all.put("text","全部");
                    maps.add(0,all);
                    for (NameAndIdBean bean : priceBeans1) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getId());
                        map.put("text",bean.getName());
                        maps.add(map);
                    }
                    view.loadDeadlineDataSuccess(maps);
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
