package com.windmillsteward.jukutech.activity.home.specialty.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyDetailActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.SpecialtyDetailBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public class SpecialtyDetailActivityPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA=1;
    private static final int COLLECT = 2;
    private static final int COLLECT_STORE = 5;
    private static final int CANCEL_COLLECT = 3;
    private static final int CANCEL_COLLECT_STORE = 6;
    private static final int ADD_SHOPPING_TO_CART = 4;

    private SpecialtyDetailActivityView view;

    public SpecialtyDetailActivityPresenter(SpecialtyDetailActivityView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     */
    public void initData(String access_token,int commodity_id){
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("commodity_id", commodity_id);
        httpInfo.setUrl(APIS.URL_SPECIALTY_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     *
     * @param commodity_id 商品id
     * @param type 操作类型 1收藏 2.取消收藏
     * @param access_token token
     */
    public void collect(int commodity_id,int type,String access_token) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, COLLECT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("commodity_id", commodity_id);
        map.put("access_token", access_token);
        map.put("type", type);
        httpInfo.setUrl(APIS.URL_COLLECT_COMMODITY);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void cancelCollect(int commodity_id,int type,String access_token) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, CANCEL_COLLECT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("commodity_id", commodity_id);
        map.put("access_token", access_token);
        map.put("type", type);
        httpInfo.setUrl(APIS.URL_COLLECT_COMMODITY);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void collectStore(int store_id,int type,String access_token) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, COLLECT_STORE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("store_id", store_id);
        map.put("access_token", access_token);
        map.put("type", type);
        httpInfo.setUrl(APIS.URL_COLLECT_STORE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void cancelCollectStore(int store_id,int type,String access_token) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, CANCEL_COLLECT_STORE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("store_id", store_id);
        map.put("access_token", access_token);
        map.put("type", type);
        httpInfo.setUrl(APIS.URL_COLLECT_STORE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    /**
     * 加入购物车
     * @param access_token token
     * @param commodity_id 商品ID
     * @param commodity_num 数量
     * @param commodity_model_id 型号ID
     * @param store_id 商店ID
     */
    public void addToCart(String access_token,int commodity_id,int commodity_num,int commodity_model_id,int store_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, ADD_SHOPPING_TO_CART);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("commodity_id", commodity_id);
        map.put("commodity_num", commodity_num);
        map.put("commodity_model_id", commodity_model_id);
        map.put("store_id", store_id);
        httpInfo.setUrl(APIS.URL_ADD_SHOPPING_TO_CART);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<SpecialtyDetailBean>>() {
                }.getType();
                break;
            case COLLECT_STORE:
            case CANCEL_COLLECT_STORE:
            case COLLECT:
            case CANCEL_COLLECT:
            case ADD_SHOPPING_TO_CART:
                type = new TypeReference<BaseResultInfo<String>>(){}.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case INIT_DATA:
                SpecialtyDetailBean data = (SpecialtyDetailBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case COLLECT:
                view.dismiss();
                view.collectSuccess();
                view.showTips("收藏成功",0);
                break;
            case CANCEL_COLLECT:
                view.dismiss();
                view.cancelCollectSuccess();
                view.showTips("取消收藏成功",0);
                break;
            case ADD_SHOPPING_TO_CART:
                view.addToCarSuccess();
                view.showTips("添加成功",1);
                break;
            case COLLECT_STORE:
                view.dismiss();
                view.collectStoreSuccess();
                view.showTips("收藏成功",0);
                break;
            case CANCEL_COLLECT_STORE:
                view.dismiss();
                view.cancelcollectStoreSuccess();
                view.showTips("取消收藏成功",0);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,0);
    }
}
