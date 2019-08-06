package com.windmillsteward.jukutech.activity.home.carservice.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.carservice.fragment.RentCarListFragmentView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.RentCarListBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * Created by Administrator on 2018/4/3.
 */

public class RentCarListFragmentPresenter extends BaseNetModelImpl {

    private final int INIT_DATA=1;
    private final int REFRESH_DATA=2;
    private final int NEXT_DATA=3;
    private static final int IS_CHARGE = 4;
    private final int IS_AUTHEN=5;

    private RentCarListFragmentView view;

    public RentCarListFragmentPresenter(RentCarListFragmentView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     * @param page 页码
     */
    public void initData(int type,int page,String departure_longitude,String departure_latitude,String destination_longitude,String destination_latitude,int go_off){
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("type", type);
        map.put("page", page);
        map.put("page_count", 10);
        map.put("departure_longitude", departure_longitude);
        map.put("departure_latitude", departure_latitude);
        map.put("destination_longitude", destination_longitude);
        map.put("destination_latitude", destination_latitude);
        map.put("go_off", go_off);
        httpInfo.setUrl(APIS.URL_CAR_RENT_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     */
    public void refreshData(int type,int page,String departure_longitude,String departure_latitude,String destination_longitude,String destination_latitude,int go_off){
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("type", type);
        map.put("page", page);
        map.put("page_count", 10);
        map.put("departure_longitude", departure_longitude);
        map.put("departure_latitude", departure_latitude);
        map.put("destination_longitude", destination_longitude);
        map.put("destination_latitude", destination_latitude);
        map.put("go_off", go_off);
        httpInfo.setUrl(APIS.URL_CAR_RENT_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     */
    public void loadNextData(int type,int page,String departure_longitude,String departure_latitude,String destination_longitude,String destination_latitude,int go_off){
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("type", type);
        map.put("page", page);
        map.put("page_count", 10);
        map.put("departure_longitude", departure_longitude);
        map.put("departure_latitude", departure_latitude);
        map.put("destination_longitude", destination_longitude);
        map.put("destination_latitude", destination_latitude);
        map.put("go_off", go_off);
        httpInfo.setUrl(APIS.URL_CAR_RENT_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void isCharge(String access_token,int type,int relate_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, IS_CHARGE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("access_token", access_token);
        map.put("relate_id", relate_id);
        httpInfo.setUrl(APIS.URL_IS_CHARGE);
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
                type = new TypeReference<BaseResultInfo<RentCarListBean>>() {
                }.getType();
                break;
            case IS_CHARGE:
                type = new TypeReference<BaseResultInfo<ChargeResultBean>>() {
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
                RentCarListBean initData = (RentCarListBean) result.getData();
                if (initData!=null) {
                    view.initDataSuccess(initData);
                }
                break;
            case REFRESH_DATA:
                RentCarListBean refreshData = (RentCarListBean) result.getData();
                if (refreshData!=null) {
                    view.refreshDataSuccess(refreshData);
                } else {
                    view.refreshDataFailure();
                }
                break;
            case NEXT_DATA:
                RentCarListBean nextData = (RentCarListBean) result.getData();
                if (nextData!=null) {
                    view.loadNextDataSuccess(nextData);
                } else {
                    view.loadNextDataFailure();
                }
                break;
            case IS_CHARGE:
                view.dismiss();
                ChargeResultBean chargeResultBean = (ChargeResultBean) result.getData();
                if (chargeResultBean!=null) {
                    view.isChargeResult(chargeResultBean);
                }
                break;
            case IS_AUTHEN:
                view.dismiss();
                AuthenResultBean data = (AuthenResultBean) result.getData();
                view.isAuthen(data);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,0);
    }
}
