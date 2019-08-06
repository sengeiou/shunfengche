package com.windmillsteward.jukutech.activity.home.insurance.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.insurance.fragment.BigHealthListFragmentView;
import com.windmillsteward.jukutech.activity.home.insurance.fragment.InsuranceListFragmentView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.BigHealthListBean;
import com.windmillsteward.jukutech.bean.InsuranceListBean;
import com.windmillsteward.jukutech.bean.InsuranceListTypeBean;
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
 * 时间：2018/3/18/018
 * 作者：xjh
 */
public class BigHealthListFragmentPresenter extends BaseNetModelImpl {

    private final int INIT_DATA=1;
    private final int REFRESH_DATA=2;
    private final int NEXT_DATA=3;
    private final int AREA_LIST=4;

    private BigHealthListFragmentView view;

    public BigHealthListFragmentPresenter(BigHealthListFragmentView view) {
        this.view = view;
    }

    /**
     * 大健康列表数据
     * @param page 页码
     * @param page_count 当夜数据量
     * @param keyword 搜索关键词
     * @param second_area_id 市id
     * @param third_area_id 区id
     */
    public void initData(int page,int page_count,String keyword,int second_area_id,int third_area_id){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("keyword", keyword);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        httpInfo.setUrl(APIS.URL_HEALTH_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     */
    public void refreshData(int page,int page_count,String keyword,int second_area_id,int third_area_id){
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("keyword", keyword);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        httpInfo.setUrl(APIS.URL_HEALTH_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     */
    public void loadNextData(int page,int page_count,String keyword,int second_area_id,int third_area_id){
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("keyword", keyword);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        httpInfo.setUrl(APIS.URL_HEALTH_LIST);
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


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case NEXT_DATA:
            case REFRESH_DATA:
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<BigHealthListBean>>() {
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
                BigHealthListBean initData = (BigHealthListBean) result.getData();
                if (initData!=null) {
                    view.initDataSuccess(initData);
                }
                break;
            case REFRESH_DATA:
                BigHealthListBean refreshData = (BigHealthListBean) result.getData();
                if (refreshData!=null) {
                    view.refreshDataSuccess(refreshData);
                } else {
                    view.refreshDataFailure();
                }
                break;
            case NEXT_DATA:
                BigHealthListBean nextData = (BigHealthListBean) result.getData();
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
        view.showTips(msg,1);
    }
}
