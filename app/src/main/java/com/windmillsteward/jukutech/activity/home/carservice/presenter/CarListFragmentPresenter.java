package com.windmillsteward.jukutech.activity.home.carservice.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.carservice.activity.CarListFragmentView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.CarListBean;
import com.windmillsteward.jukutech.bean.CarPriceListBean;
import com.windmillsteward.jukutech.bean.MoreCarListBean;
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
 * 时间：2018/3/28/028
 * 作者：xjh
 */
public class CarListFragmentPresenter extends BaseNetModelImpl {
    private final int INIT_DATA=1;
    private final int REFRESH_DATA=2;
    private final int NEXT_DATA=3;
    private final int AREA_LIST=4;
    private final int CAR_PRICE_DATA = 5;
    private final int MORE_CAR_LIST = 6;

    private CarListFragmentView view;

    public CarListFragmentPresenter(CarListFragmentView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     * @param page 页码
     */
    public void initData(int page,String keyword,int second_area_id,int third_area_id,int brand_id,int price_id,int mileage_id,int displacement_id,int gearbox_id){
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", 10);
        map.put("keyword", keyword);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("brand_id", brand_id);
        map.put("price_id", price_id);
        map.put("mileage_id", mileage_id);
        map.put("displacement_id", displacement_id);
        map.put("gearbox_id", gearbox_id);
        httpInfo.setUrl(APIS.URL_CAR_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     */
    public void refreshData(int page,String keyword,int second_area_id,int third_area_id,int brand_id,int price_id,int mileage_id,int displacement_id,int gearbox_id){
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", 10);
        map.put("keyword", keyword);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("brand_id", brand_id);
        map.put("price_id", price_id);
        map.put("mileage_id", mileage_id);
        map.put("displacement_id", displacement_id);
        map.put("gearbox_id", gearbox_id);
        httpInfo.setUrl(APIS.URL_CAR_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     */
    public void loadNextData(int page,String keyword,int second_area_id,int third_area_id,int brand_id,int price_id,int mileage_id,int displacement_id,int gearbox_id){
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", 10);
        map.put("keyword", keyword);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("brand_id", brand_id);
        map.put("price_id", price_id);
        map.put("mileage_id", mileage_id);
        map.put("displacement_id", displacement_id);
        map.put("gearbox_id", gearbox_id);
        httpInfo.setUrl(APIS.URL_CAR_LIST);
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
     * 加载价格分类
     */
    public void loadPriceData(){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, CAR_PRICE_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_CAR_PRICE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 筛选
     */
    public void loadMoreCarData(){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, MORE_CAR_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_MORE_CAR_LIST);
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
                type = new TypeReference<BaseResultInfo<CarListBean>>() {
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
            case MORE_CAR_LIST:
                type = new TypeReference<BaseResultInfo<MoreCarListBean>>() {
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
                CarListBean initData = (CarListBean) result.getData();
                if (initData!=null) {
                    view.initDataSuccess(initData);
                }
                break;
            case REFRESH_DATA:
                CarListBean refreshData = (CarListBean) result.getData();
                if (refreshData!=null) {
                    view.refreshDataSuccess(refreshData);
                } else {
                    view.refreshDataFailure();
                }
                break;
            case NEXT_DATA:
                CarListBean nextData = (CarListBean) result.getData();
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
                    Map<String,Object> mapAll = new HashMap<>();
                    mapAll.put("id",0);
                    mapAll.put("text","全部");
                    maps.add(mapAll);
                    for (CarPriceListBean bean : carPriceListBeans) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getPrice_id());
                        map.put("text",bean.getName());
                        maps.add(map);
                    }
                    view.loadPriceDataSuccess(maps);
                }
                break;
            case MORE_CAR_LIST:
                MoreCarListBean moreCarListBean = (MoreCarListBean) result.getData();
                if (moreCarListBean!=null) {
                    List<Map<String,Object>> maps1 = new ArrayList<>();
                    List<Map<String,Object>> maps2 = new ArrayList<>();
                    List<Map<String,Object>> maps3 = new ArrayList<>();
                    List<MoreCarListBean.MileageListBean> mileage_list = moreCarListBean.getMileage_list();
                    if (mileage_list!=null) {
                        for (MoreCarListBean.MileageListBean bean : mileage_list) {
                            Map<String,Object> map = new HashMap<>();
                            map.put("id",bean.getMileage_id());
                            map.put("text",bean.getMileage_name());
                            maps1.add(map);
                        }
                    }
                    List<MoreCarListBean.DisplacementListBean> displacement_list = moreCarListBean.getDisplacement_list();
                    if (displacement_list!=null) {
                        for (MoreCarListBean.DisplacementListBean bean : displacement_list) {
                            Map<String,Object> map = new HashMap<>();
                            map.put("id",bean.getDisplacement_id());
                            map.put("text",bean.getDisplacement_name());
                            maps2.add(map);
                        }
                    }
                    List<MoreCarListBean.GearboxListBean> gearbox_list = moreCarListBean.getGearbox_list();
                    if (gearbox_list!=null) {
                        for (MoreCarListBean.GearboxListBean bean : gearbox_list) {
                            Map<String,Object> map = new HashMap<>();
                            map.put("id",bean.getGearbox_id());
                            map.put("text",bean.getGearbox_name());
                            maps3.add(map);
                        }
                    }
                    view.loadMoreClassDataSuccess(maps1,maps2,maps3);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,0);
    }
}
