package com.windmillsteward.jukutech.activity.home.stayandtravel.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.stayandtravel.fragment.HotelHomeView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.HotelAndHouseBean;
import com.windmillsteward.jukutech.bean.HotelAndHouseHomeBean;
import com.windmillsteward.jukutech.bean.HotelTypeBean;
import com.windmillsteward.jukutech.bean.PriceBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/1/30
 * 作者：xjh
 */

public class HotelHomePresenter extends BaseNetModelImpl {

    private final int INIT_DATA = 0;
    private final int HOTEL_TYPE_DATA = 1;
    private final int HOTEL_PRICE_DATA = 2;

    private HotelHomeView view;

    public HotelHomePresenter(HotelHomeView view) {
        this.view = view;
    }

    /**
     * 初始化酒店列表
     * @param hotel_business_class 业务分类，1.酒店 2.家庭房源
     */
    public void initData(int hotel_business_class,String longitude,String latitude,int second_area_id) {
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("hotel_business_class", hotel_business_class);
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        map.put("second_area_id", second_area_id);
        httpInfo.setUrl(APIS.URL_RECOMMEND_HOTEL_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载酒店类型数据
     */
    public void loadHotelTypeData() {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, HOTEL_TYPE_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_HOTEL_TYPE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载酒店价格
     */
    public void loadHotelPriceData() {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, HOTEL_PRICE_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_HOTEL_PRICE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<List<HotelAndHouseHomeBean>>>(){}.getType();
                break;
            case HOTEL_TYPE_DATA:
                type = new TypeReference<BaseResultInfo<List<HotelTypeBean>>>(){}.getType();
                break;
            case HOTEL_PRICE_DATA:
                type = new TypeReference<BaseResultInfo<List<PriceBean>>>(){}.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                List<HotelAndHouseHomeBean> data = (List<HotelAndHouseHomeBean>) result.getData();
                if (data != null) {
                    view.initDataSuccess(data);
                }
                break;
            case HOTEL_TYPE_DATA:
                List<HotelTypeBean> hotelTypeBeans = (List<HotelTypeBean>) result.getData();
                if (hotelTypeBeans!=null) {
                    view.loadHouseTypeDataSuccess(hotelTypeBeans);
                } else {
                    view.dismiss();
                }
                break;
            case HOTEL_PRICE_DATA:
                List<PriceBean> priceBeans = (List<PriceBean>) result.getData();
                if (priceBeans!=null) {
                    view.loadPriceDataSuccess(priceBeans);
                } else {
                    view.dismiss();
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                break;
        }
    }
}
