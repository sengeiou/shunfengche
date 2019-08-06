package com.windmillsteward.jukutech.activity.home.stayandtravel.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.stayandtravel.fragment.HotelAndHouseListView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.FourthAreaBean;
import com.windmillsteward.jukutech.bean.HotelAndHouseBean;
import com.windmillsteward.jukutech.bean.HotelTypeBean;
import com.windmillsteward.jukutech.bean.PriceBean;
import com.windmillsteward.jukutech.bean.ThirdAndFourthAreaBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/1/27/027
 * 作者：xjh
 */
public class HotelAndHouseListPresenter extends BaseNetModelImpl {

    private final int INIT_DATA = 0;
    private final int REFRES_DATA = 1;
    private final int NEXT_DATA = 2;
    private final int AREA_LIST = 3;
    private final int HOTEL_TYPE = 4;
    private final int PRICE_TYPE = 5;

    private HotelAndHouseListView view;

    public HotelAndHouseListPresenter(HotelAndHouseListView view) {
        this.view = view;
    }

    /**
     * 初始化酒店列表
     * @param hotel_business_class 业务分类，1.酒店 2.家庭房源
     * @param page 页码
     * @param page_count 页码大小
     * @param keyword 关键词
     * @param hotel_type 星级id 默认0
     * @param price_id 价格id 默认0
     * @param third_area_id 第三地区id
     */
    public void initData(int hotel_business_class,int page,int page_count,String keyword,int hotel_type,int price_id,int second_area_id,int third_area_id, int fourth_area_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("hotel_business_class", hotel_business_class);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("keyword", keyword);
        map.put("hotel_type", hotel_type);
        map.put("price_id", price_id);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("fourth_area_id", fourth_area_id);
        httpInfo.setUrl(APIS.URL_HOTEL_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     * @param hotel_business_class 业务分类，1.酒店 2.家庭房源
     * @param page 页码
     * @param page_count 页码大小
     * @param keyword 关键词
     * @param hotel_type 星级id 默认0
     * @param price_id 价格id 默认0
     * @param third_area_id 第三地区id
     */
    public void refreshData(int hotel_business_class,int page,int page_count,String keyword,int hotel_type,int price_id,int second_area_id,int third_area_id,int fourth_area_id) {
        DataLoader dataLoader = new DataLoader(this, REFRES_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("hotel_business_class", hotel_business_class);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("keyword", keyword);
        map.put("hotel_type", hotel_type);
        map.put("price_id", price_id);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("fourth_area_id", fourth_area_id);
        httpInfo.setUrl(APIS.URL_HOTEL_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     * @param hotel_business_class 业务分类，1.酒店 2.家庭房源
     * @param page 页码
     * @param page_count 页码大小
     * @param keyword 关键词
     * @param hotel_type 星级id 默认0
     * @param price_id 价格id 默认0
     * @param third_area_id 第三地区id
     */
    public void loadNextData(int hotel_business_class,int page,int page_count,String keyword,int hotel_type,int price_id,int second_area_id,int third_area_id,int fourth_area_id) {
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("hotel_business_class", hotel_business_class);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("keyword", keyword);
        map.put("hotel_type", hotel_type);
        map.put("price_id", price_id);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("fourth_area_id", fourth_area_id);
        httpInfo.setUrl(APIS.URL_HOTEL_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载地区数据
     */
    public void loadAreaData(int second_area_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, AREA_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("second_area_id", second_area_id);
        httpInfo.setUrl(APIS.URL_THIRD_FOURTH_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载酒店分类
     */
    public void loadHotelTypeData() {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, HOTEL_TYPE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_HOTEL_TYPE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载酒店价格
     */
    public void loadPriceData() {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, PRICE_TYPE);
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
            case REFRES_DATA:
            case NEXT_DATA:
                type = new TypeReference<BaseResultInfo<HotelAndHouseBean>>(){}.getType();
                break;
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAndFourthAreaBean>>>(){}.getType();
                break;
            case HOTEL_TYPE:
                type = new TypeReference<BaseResultInfo<List<HotelTypeBean>>>(){}.getType();
                break;
            case PRICE_TYPE:
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
                HotelAndHouseBean data = (HotelAndHouseBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case REFRES_DATA:
                HotelAndHouseBean refreshData = (HotelAndHouseBean) result.getData();
                if (refreshData!=null) {
                    view.refreshDataSuccess(refreshData);
                }
                break;
            case NEXT_DATA:
                HotelAndHouseBean nextData = (HotelAndHouseBean) result.getData();
                if (nextData!=null) {
                    view.loadNextDataSuccess(nextData);
                }
                break;
            case AREA_LIST:
                view.dismiss();
                List<ThirdAndFourthAreaBean> areaData = (List<ThirdAndFourthAreaBean>) result.getData();
                if (areaData!=null) {
                    ThirdAndFourthAreaBean firstAll = new ThirdAndFourthAreaBean();
                    firstAll.setThird_area_id(0);
                    firstAll.setThird_area_name("全部");
                    ArrayList<FourthAreaBean> secondAll = new ArrayList<>();
                    FourthAreaBean fourthAreaBean = new FourthAreaBean();
                    fourthAreaBean.setFourth_area_id(0);
                    fourthAreaBean.setFourth_area_name("全部");
                    secondAll.add(fourthAreaBean);
                    firstAll.setFourth_area_list(secondAll);
                    areaData.add(0, firstAll);
                    view.loadAreaDataSuccess(areaData);
                }
                break;
            case HOTEL_TYPE:
                view.dismiss();
                List<HotelTypeBean> hotelData = (List<HotelTypeBean>) result.getData();
                if (hotelData!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    Map<String,Object> mapAll = new HashMap<>();
                    mapAll.put("id",0);
                    mapAll.put("text","全部");
                    maps.add(mapAll);
                    for (HotelTypeBean bean : hotelData) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getHotel_type());
                        map.put("text",bean.getType_name());
                        maps.add(map);
                    }
                    view.loadHouseTypeDataSuccess(maps);
                }
                break;
            case PRICE_TYPE:
                view.dismiss();
                List<PriceBean> priceData = (List<PriceBean>) result.getData();
                if (priceData!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    Map<String,Object> mapAll = new HashMap<>();
                    mapAll.put("id",0);
                    mapAll.put("text","全部");
                    maps.add(mapAll);
                    for (PriceBean bean : priceData) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getPrice_id());
                        map.put("text",bean.getPrice_name());
                        maps.add(map);
                    }
                    view.loadPriceDataSuccess(maps);
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
            case REFRES_DATA:
                view.refreshDataFailure();
                break;
            case NEXT_DATA:
                view.loadNextDataFailure();
                break;
            case AREA_LIST:
                view.dismiss();
                break;
            case HOTEL_TYPE:
                view.dismiss();
                break;
            case PRICE_TYPE:
                view.dismiss();
                break;
        }
    }
}
