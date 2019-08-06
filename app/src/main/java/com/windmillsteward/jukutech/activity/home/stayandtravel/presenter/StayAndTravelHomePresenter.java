package com.windmillsteward.jukutech.activity.home.stayandtravel.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.StayAndTravelHomeView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.BannerBean;
import com.windmillsteward.jukutech.bean.TravelListBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/1/25
 * 作者：xjh
 */

public class StayAndTravelHomePresenter extends BaseNetModelImpl {

    private final int INIT_BANNER = 0;
    private final int INIT_DATA = 1;
    private StayAndTravelHomeView view;

    public StayAndTravelHomePresenter(StayAndTravelHomeView view) {
        this.view = view;
    }

    /**
     * 获取banner数据
     */
    public void initBannerData(){
        DataLoader dataLoader = new DataLoader(this, INIT_BANNER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("banner_position", 3);
        httpInfo.setUrl(APIS.URL_BANNER_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 获取推荐数据
     */
    public void initData(int second_area_id){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("page_count", 20);
        map.put("travel_class_id", 0);
        map.put("keyword", "");
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", 0);
        map.put("price_id", 0);
        map.put("travel_area_id", 0);
        httpInfo.setUrl(APIS.URL_TRAVEL_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<TravelListBean>>() {
                }.getType();
                break;
            case INIT_BANNER:
                type = new TypeReference<BaseResultInfo<List<BannerBean>>>() {
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
            case INIT_BANNER:
                List<BannerBean> bannerData = (List<BannerBean>) result.getData();
                if (bannerData!=null) {
                    view.initBannerDataSuccess(bannerData);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (code) {
            case INIT_DATA:
                view.dismiss();
                view.showTips("加载失败",0);
                break;
        }
    }
}
