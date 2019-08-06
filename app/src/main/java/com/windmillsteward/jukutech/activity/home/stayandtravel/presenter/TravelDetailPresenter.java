package com.windmillsteward.jukutech.activity.home.stayandtravel.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.TravelDetailView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.TravelDetailBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/1/25
 * 作者：xjh
 */

public class TravelDetailPresenter extends BaseNetModelImpl {

    private final int INIT_DATA = 0;
    private final int COLLECT_TRAVEL = 1;
    private final int CANCEL_COLLECT_TRAVEL = 2;
    private final int IS_CHARGE = 3;

    private TravelDetailView view;

    public TravelDetailPresenter(TravelDetailView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     * @param travel_id 旅游id
     */
    public void initData(String access_token,int travel_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("travel_id", travel_id);
        httpInfo.setUrl(APIS.URL_TRAVEL_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 收藏旅游
     * @param travel_id 旅游id
     */
    public void collectTravel(String access_token,int travel_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, COLLECT_TRAVEL);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("travel_id", travel_id);
        httpInfo.setUrl(APIS.URL_COLLECT_TRAVEL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 取消收藏
     * @param travel_id 旅游id
     */
    public void cancelCollectTravel(String access_token,int travel_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, CANCEL_COLLECT_TRAVEL);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("travel_id", travel_id);
        httpInfo.setUrl(APIS.URL_CANCEL_COLLECT_TRAVEL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 判断打电话是否收费
     * @param access_token
     */
    public void isContacCharge(String access_token,int relate_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, IS_CHARGE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type", 64);
        map.put("access_token", access_token);
        map.put("relate_id", relate_id);
        httpInfo.setUrl(APIS.URL_IS_CHARGE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<TravelDetailBean>>(){}.getType();
                break;
            case CANCEL_COLLECT_TRAVEL:
            case COLLECT_TRAVEL:
                type = new TypeReference<BaseResultInfo<String>>(){}.getType();
                break;
            case IS_CHARGE:
                type = new TypeReference<BaseResultInfo<ChargeResultBean>>() {
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
                TravelDetailBean data = (TravelDetailBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case COLLECT_TRAVEL:
                view.dismiss();
                view.collectTravelSuccess();
                view.showTips("收藏成功",0);
                break;
            case CANCEL_COLLECT_TRAVEL:
                view.dismiss();
                view.cancelCollectTravelSuccess();
                view.showTips("取消收藏成功",0);
                break;
            case IS_CHARGE:
                view.dismiss();
                ChargeResultBean chargeResultBean = (ChargeResultBean) result.getData();
                if (chargeResultBean!=null) {
                    view.isChargeResult(chargeResultBean);
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
            case COLLECT_TRAVEL:
                view.dismiss();
                view.showTips("收藏失败",0);
                break;
            case CANCEL_COLLECT_TRAVEL:
                view.dismiss();
                view.showTips("取消收藏失败",0);
                break;
            case IS_CHARGE:
                view.dismiss();
                view.showTips(msg,0);
                break;
        }
    }


}
