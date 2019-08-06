package com.windmillsteward.jukutech.activity.home.family.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.carservice.fragment.BuyCarListFragmentView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.bean.BuyCarListBean;
import com.windmillsteward.jukutech.bean.CarPriceListBean;
import com.windmillsteward.jukutech.bean.IntelligentFamilyBean;
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
 * 时间：2018/3/25/025
 * 作者：xjh
 */
public class BuyCarListFragmentPresenter extends BaseNetModelImpl {

    private final int INIT_DATA=1;
    private final int REFRESH_DATA=2;
    private final int NEXT_DATA=3;
    private final int AREA_LIST=4;
    private final int CAR_PRICE_DATA=5;
    private final int IS_AUTHEN=6;

    private BuyCarListFragmentView view;

    public BuyCarListFragmentPresenter(BuyCarListFragmentView view) {
        this.view = view;
    }


    /**
     * 初始化数据
     * @param keyword 关键词
     * @param third_area_id 地区id
     * @param price_id 价格id
     */
    public void initData(String keyword,int second_area_id, int third_area_id, int price_id){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", 1);
        map.put("page_count", 10);
        map.put("keyword", keyword);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("price_id", price_id);
        httpInfo.setUrl(APIS.URL_BUY_CAR_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新
     * @param keyword 关键词
     * @param third_area_id 地区id
     * @param price_id 价格id
     */
    public void refreshData(String keyword,int second_area_id, int third_area_id, int price_id){
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", 1);
        map.put("page_count", 10);
        map.put("keyword", keyword);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("price_id", price_id);
        httpInfo.setUrl(APIS.URL_BUY_CAR_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     * @param keyword 关键词
     * @param third_area_id 地区id
     * @param price_id 价格id
     */
    public void loadNextData(int page,String keyword,int second_area_id, int third_area_id, int price_id){
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", 10);
        map.put("keyword", keyword);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("price_id", price_id);
        httpInfo.setUrl(APIS.URL_BUY_CAR_LIST);
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
     * 获取车辆价格
     */
    public void loadCarPriceListData(){
        DataLoader dataLoader = new DataLoader(this, CAR_PRICE_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        httpInfo.setUrl(APIS.URL_CAR_PRICE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void getAuthenState(String access_token){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, IS_AUTHEN);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_IS_AUTHEN);
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
                type = new TypeReference<BaseResultInfo<BuyCarListBean>>() {
                }.getType();
                break;
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>() {
                }.getType();
                break;
            case CAR_PRICE_DATA:
                type = new TypeReference<BaseResultInfo<List<CarPriceListBean>>>() {
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
        view.dismiss();
        switch (action) {
            case INIT_DATA:
                BuyCarListBean data = (BuyCarListBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case REFRESH_DATA:
                BuyCarListBean refreshData = (BuyCarListBean) result.getData();
                if (refreshData!=null) {
                    view.refreshDataSuccess(refreshData);
                } else {
                    view.refreshDataFailure();
                }
                break;
            case NEXT_DATA:
                BuyCarListBean nextData = (BuyCarListBean) result.getData();
                if (nextData!=null) {
                    view.loadNextDataSuccess(nextData);
                } else {
                    view.loadNextDataFailure();
                }
                break;
            case AREA_LIST:
                List<ThirdAreaBean> areaData = (List<ThirdAreaBean>) result.getData();
                if (areaData!=null) {
                    view.loadAreaDataSuccess(areaData);
                }
                break;
            case CAR_PRICE_DATA:
                List<CarPriceListBean> carPriceListBeans = (List<CarPriceListBean>) result.getData();
                if (carPriceListBeans!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    Map<String,Object> all = new HashMap<>();
                    all.put("id",0);
                    all.put("text","全部");
                    maps.add(all);
                    for (CarPriceListBean bean : carPriceListBeans) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getPrice_id());
                        map.put("text",bean.getName());
                        maps.add(map);
                    }
                    view.loadPriceDataSuccess(maps);
                }
                break;
            case IS_AUTHEN:
                view.dismiss();
                AuthenResultBean authenResultBean = (AuthenResultBean) result.getData();
                view.isAuthen(authenResultBean);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case INIT_DATA:
                break;
            case REFRESH_DATA:
                view.refreshDataFailure();
                break;
            case NEXT_DATA:
                view.loadNextDataFailure();
                break;
            case AREA_LIST:
                view.showTips(msg,1);
                break;
            case CAR_PRICE_DATA:
                view.showTips(msg,1);
                break;
        }
    }
}
