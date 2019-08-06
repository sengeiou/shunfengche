package com.windmillsteward.jukutech.activity.home.stayandtravel.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.HotelAndHouseDetailView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.HotelAndHouseDetailBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 * 时间：2018/1/28/028
 * 作者：xjh
 */
public class HotelAndHouseDetailPresenter extends BaseNetModelImpl {
    private final int INIT_DATA = 0;
    private final int COLLECT = 1;
    private final int CANCEL_COLLECT = 2;
    private HotelAndHouseDetailView view;

    public HotelAndHouseDetailPresenter(HotelAndHouseDetailView view) {
        this.view = view;
    }

    public void initData(String access_token,int hotel_id,String planed_start_time,String planed_end_time){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("hotel_id",hotel_id);
        map.put("access_token",access_token);
        map.put("planed_start_time",planed_start_time);
        map.put("planed_end_time",planed_end_time);
        httpInfo.setUrl(APIS.URL_HOTEL_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void collect(String access_token,int hotel_id){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, COLLECT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("hotel_id",hotel_id);
        map.put("access_token",access_token);
        httpInfo.setUrl(APIS.URL_COLLECT_HOTEL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void cancelCollect(String access_token,int hotel_id){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, CANCEL_COLLECT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("hotel_id",hotel_id);
        map.put("access_token",access_token);
        httpInfo.setUrl(APIS.URL_CANCEL_COLLECT_HOTEL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<HotelAndHouseDetailBean>>(){}.getType();
                break;
            case COLLECT:
            case CANCEL_COLLECT:
                type = new TypeReference<BaseResultInfo<String>>(){}.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                HotelAndHouseDetailBean data = (HotelAndHouseDetailBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case COLLECT:
                view.dismiss();
                view.collectionSuccess();
                view.showTips("收藏成功",0);
                break;
            case CANCEL_COLLECT:
                view.dismiss();
                view.cancelCollectionSuccess();
                view.showTips("取消收藏成功",0);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                break;
            case COLLECT:
                view.dismiss();
                view.showTips("收藏失败",0);
                break;
            case CANCEL_COLLECT:
                view.dismiss();
                view.showTips("取消收藏失败",0);
                break;
        }
    }
}
