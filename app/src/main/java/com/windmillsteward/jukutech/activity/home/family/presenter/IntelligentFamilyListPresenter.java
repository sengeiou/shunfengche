package com.windmillsteward.jukutech.activity.home.family.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.family.fragment.IntelligentFamilyListView;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.bean.IntelligentFamilyBean;
import com.windmillsteward.jukutech.bean.RequireClassBean;
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
 * 描述：智慧家庭
 * 时间：2018/1/16/016
 * 作者：xjh
 */
public class IntelligentFamilyListPresenter extends BaseNetModelImpl {

    private final int INIT_DATA = 1;
    private final int REFRESH_DATA = 2;
    private final int NEXT_DATA = 3;
    private final int AREA_LIST = 4;
    private final int GET_ORDER = 5;
    private final int FAMILY_CLASS = 6;
    private final int IS_AUTHEN = 7;

    private IntelligentFamilyListView view;

    public IntelligentFamilyListPresenter(IntelligentFamilyListView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     *
     * @param keyword       关键词
     * @param third_area_id 地区id
     * @param time_sort     时间排序 0默认，1最新，2最早
     *                      //     * @param price_sort    价格排序 0默认，1从高到低，2从低到高
     * @param filter_type   筛选：【0：默认，1：距离最近，:2：悬赏金最高】
     * @param longitude     经度
     * @param latitude      维度
     */
    public void initData(String keyword, int second_area_id, int third_area_id, int time_sort, int filter_type, String longitude, String latitude, int require_class_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", 1);
        map.put("page_count", 10);
        map.put("keyword", keyword);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("time_sort", time_sort);
        //筛选 筛选：【0：默认，1：距离最近，:2：悬赏金最高】
        map.put("filter_type", filter_type);
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        map.put("require_class_id", require_class_id);
        httpInfo.setUrl(APIS.URL_REQUIRE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 获取认证状态
     *
     * @param access_token
     */
    public void getAuthenState(String access_token) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, IS_AUTHEN);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_IS_AUTHEN);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     *
     * @param keyword       关键词
     * @param third_area_id 地区id
     * @param time_sort     时间排序 0默认，1最新，2最早
     *                      //     * @param price_sort    价格排序 0默认，1从高到低，2从低到高
     * @param filter_type   筛选：【0：默认，1：距离最近，:2：悬赏金最高】
     * @param longitude     经度
     * @param latitude      维度
     */
    public void refreshData(String keyword, int second_area_id, int third_area_id, int time_sort, int filter_type, String longitude, String latitude, int require_class_id) {
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", 1);
        map.put("page_count", 10);
        map.put("keyword", keyword);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("time_sort", time_sort);
        map.put("filter_type", filter_type);
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        map.put("require_class_id", require_class_id);
        httpInfo.setUrl(APIS.URL_REQUIRE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     *
     * @param page          页码
     * @param keyword       关键词
     * @param third_area_id 地区id
     * @param time_sort     时间排序 0默认，1最新，2最早
     *                      //     * @param price_sort    价格排序 0默认，1从高到低，2从低到高
     * @param filter_type   筛选：【0：默认，1：距离最近，:2：悬赏金最高】
     * @param longitude     经度
     * @param latitude      维度
     */
    public void loadNextData(int page, String keyword, int second_area_id, int third_area_id, int time_sort, int filter_type, String longitude, String latitude, int require_class_id) {
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", 10);
        map.put("keyword", keyword);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("time_sort", time_sort);
        map.put("filter_type", filter_type);
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        map.put("require_class_id", require_class_id);
        httpInfo.setUrl(APIS.URL_REQUIRE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadFamilyClassData() {
        DataLoader dataLoader = new DataLoader(this, FAMILY_CLASS);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        httpInfo.setUrl(APIS.URL_REQUIRE_CLASS_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载地区
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
     * 加载发布时间
     */
    public void loadPushTimeData() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map0 = new HashMap<>();
        map0.put("id", 0);
        map0.put("text", "默认");
        list.add(map0);
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("text", "最新");
        list.add(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 2);
        map1.put("text", "最早");
        list.add(map1);
        view.loadPushTimeDataSuccess(list);
    }

    /**
     * 加载价格
     */
    public void loadPriceData() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map0 = new HashMap<>();
        map0.put("id", 0);
        map0.put("text", "默认");
        list.add(map0);
        map.put("id", 1);  // time_sort
        map.put("text", "从高到低");
        list.add(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 2);  // time_sort
        map1.put("text", "从低到高");
        list.add(map1);
        view.loadPriceDataSuccess(list);
    }

    /**
     * 抢单
     */
    public void getOrder(String access_token, int require_id,String area_name,String longitude,String latitude) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, GET_ORDER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("require_id", require_id);
        map.put("area_name", area_name);
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        httpInfo.setUrl(APIS.URL_ROB_REQUIRE);
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
                type = new TypeReference<BaseResultInfo<IntelligentFamilyBean>>() {
                }.getType();
                break;
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>() {
                }.getType();
                break;
            case FAMILY_CLASS:
                type = new TypeReference<BaseResultInfo<List<RequireClassBean>>>() {
                }.getType();
                break;
            case GET_ORDER:
                type = new TypeReference<BaseResultInfo<PublicResultModel>>() {
                }.getType();
                break;
            case IS_AUTHEN:
                type = new TypeReference<BaseResultInfo<AuthenResultBean>>() {
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
                IntelligentFamilyBean data = (IntelligentFamilyBean) result.getData();
                if (data != null) {
                    view.initDataSuccess(data);
                }
                break;
            case REFRESH_DATA:
                IntelligentFamilyBean refreshData = (IntelligentFamilyBean) result.getData();
                if (refreshData != null) {
                    view.refreshDataSuccess(refreshData);
                } else {
                    view.refreshDataFailure();
                }
                break;
            case NEXT_DATA:
                IntelligentFamilyBean nextData = (IntelligentFamilyBean) result.getData();
                if (nextData != null) {
                    view.loadNextDataSuccess(nextData);
                } else {
                    view.loadNextFailure();
                }
                break;
            case FAMILY_CLASS:
                List<RequireClassBean> list = (List<RequireClassBean>) result.getData();
                if (list != null) {
                    view.loadRequireClassListSuccess(list);
                } else {
                    view.loadRequireClassListFail();
                }
                break;
            case AREA_LIST:
                view.dismiss();
                List<ThirdAreaBean> areaData = (List<ThirdAreaBean>) result.getData();
                if (areaData != null) {
                    view.loadAreaDataSuccess(areaData);
                }
                break;
            case GET_ORDER:
                view.dismiss();
                PublicResultModel resultModel = (PublicResultModel) result.getData();
                if (resultModel!= null){
                    view.getOrderSuccess(resultModel,result.getMsg());
                }
                break;
            case IS_AUTHEN:
                view.dismiss();
                AuthenResultBean authenData = (AuthenResultBean) result.getData();
                view.isAuthen(authenData);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                break;
            case REFRESH_DATA:
                view.refreshDataFailure();
                break;
            case NEXT_DATA:
                view.loadNextFailure();
                break;
            case AREA_LIST:
                view.dismiss();
                break;
            case GET_ORDER:
                view.dismiss();
                view.showTips(msg, 0);
                break;
            case IS_AUTHEN:
                view.dismiss();
                view.showTips("获取认证信息失败，请重试", 0);
                break;
        }
    }
}
