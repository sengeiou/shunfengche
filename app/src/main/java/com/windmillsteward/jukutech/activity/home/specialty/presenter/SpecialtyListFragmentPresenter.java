package com.windmillsteward.jukutech.activity.home.specialty.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.insurance.fragment.InsuranceListFragmentView;
import com.windmillsteward.jukutech.activity.home.specialty.fragment.SpecialtyListFragmentView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.InsuranceListBean;
import com.windmillsteward.jukutech.bean.InsuranceListTypeBean;
import com.windmillsteward.jukutech.bean.SpecialtyListBean;
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

public class SpecialtyListFragmentPresenter extends BaseNetModelImpl {
    private final int INIT_DATA=1;
    private final int REFRESH_DATA=2;
    private final int NEXT_DATA=3;
    private final int AREA_LIST=4;

    private SpecialtyListFragmentView view;

    public SpecialtyListFragmentPresenter(SpecialtyListFragmentView view) {
        this.view = view;
    }


    /**
     * 初始化数据
     * @param page 页数
     * @param page_count 数量
     * @param commodity_first_type 一级分类
     * @param commodity_second_type 二级分类
     * @param commodity_third_type 三级分类
     * @param second_area_id 市
     * @param third_area_id 区
     * @param sort_id 排序
     * @param commodity_title 关键词
     */
    public void initData(int page,int page_count,int commodity_first_type,int commodity_second_type,int commodity_third_type,int second_area_id,int third_area_id,int sort_id,String commodity_title){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("commodity_first_type", commodity_first_type);
        map.put("commodity_second_type", commodity_second_type);
        map.put("commodity_third_type", commodity_third_type);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("sort_id", sort_id);
        map.put("commodity_title", commodity_title);
        httpInfo.setUrl(APIS.URL_SPECIALTY_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     */
    public void refreshData(int page,int page_count,int commodity_first_type,int commodity_second_type,int commodity_third_type,int second_area_id,int third_area_id,int sort_id,String commodity_title){
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("commodity_first_type", commodity_first_type);
        map.put("commodity_second_type", commodity_second_type);
        map.put("commodity_third_type", commodity_third_type);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("sort_id", sort_id);
        map.put("commodity_title", commodity_title);
        httpInfo.setUrl(APIS.URL_SPECIALTY_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     */
    public void loadNextData(int page,int page_count,int commodity_first_type,int commodity_second_type,int commodity_third_type,int second_area_id,int third_area_id,int sort_id,String commodity_title){
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("commodity_first_type", commodity_first_type);
        map.put("commodity_second_type", commodity_second_type);
        map.put("commodity_third_type", commodity_third_type);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("sort_id", sort_id);
        map.put("commodity_title", commodity_title);
        httpInfo.setUrl(APIS.URL_SPECIALTY_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载地区
     */
    public void loadAreaData(int second_area_id){
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
     * 加载排序
     * 综合排序id(写死) 1. 销量 2.价格高到低 3.价格低到高 不传默认添加时间倒序排序	是	[s
     */
    public void loadSortData() {
        List<Map<String,Object>> maps = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("id",1);
        map1.put("text","销量");
        maps.add(map1);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("id",2);
        map2.put("text","价格高到低");
        maps.add(map2);
        Map<String,Object> map3 = new HashMap<>();
        map3.put("id",3);
        map3.put("text","价格低到高");
        maps.add(map3);
        view.loadSortDataSuccess(maps);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case NEXT_DATA:
            case REFRESH_DATA:
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<SpecialtyListBean>>() {
                }.getType();
                break;
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {

        view.dismiss();
        switch (action) {
            case INIT_DATA:
                SpecialtyListBean initData = (SpecialtyListBean) result.getData();
                if (initData!=null) {
                    view.initDataSuccess(initData);
                }
                break;
            case REFRESH_DATA:
                SpecialtyListBean refreshData = (SpecialtyListBean) result.getData();
                if (refreshData!=null) {
                    view.refreshDataSuccess(refreshData);
                } else {
                    view.refreshDataFailure();
                }
                break;
            case NEXT_DATA:
                SpecialtyListBean nextData = (SpecialtyListBean) result.getData();
                if (nextData!=null) {
                    view.loadNextDataSuccess(nextData);
                } else {
                    view.loadNextFailure();
                }
                break;
            case AREA_LIST:
                List<ThirdAreaBean> areaData = (List<ThirdAreaBean>) result.getData();
                if (areaData!=null) {
                    view.loadAreaDataSuccess(areaData);
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
