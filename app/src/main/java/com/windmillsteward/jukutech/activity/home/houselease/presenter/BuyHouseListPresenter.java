package com.windmillsteward.jukutech.activity.home.houselease.presenter;

import android.util.Log;

import com.alibaba.fastjson.TypeReference;
import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.activity.home.houselease.fragment.BuyHouseView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.BaseNewNetModelimpl;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.HouseMoreBean;
import com.windmillsteward.jukutech.bean.HousePriceBean;
import com.windmillsteward.jukutech.bean.HouseSealListBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import org.xutils.common.Callback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 * 时间：2018/1/19
 * 作者：xjh
 */

public class BuyHouseListPresenter extends BaseNetModelImpl {

    private final int INIT_DATA = 1;
    private final int REFRESH_DATA = 2;
    private final int NEXT_DATA = 3;
    private static final int AREA_LIST = 4;
    private static final int PRICE_DATA = 5;
    private static final int MORE_DATA = 6;

    private BuyHouseView view;

    public BuyHouseListPresenter(BuyHouseView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     *
     * @param page            页
     * @param pageSize        大小
     * @param house_second_id 市id
     * @param house_third_id  地区id
     * @param title           标题
     * @param time_type       时间筛选 1.七天内  2.一个月内 3.半年内  4.一年内  5.一年以上
     * @param house_area_id   面积id
     * @param house_type_id   房屋类型id
     * @param ren_type_number 房屋户型id
     * @param price_sorting   排序 1.价格最高 2.价格最低
     */
    public void initData(int page, int pageSize, int house_second_id, int house_third_id, String title, String time_type,
                         int house_area_id, int house_type_id, int ren_type_number, String price_sorting) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("page", page);
        map.put("page_count", pageSize);
        map.put("house_second_id", house_second_id);
        map.put("house_third_id", house_third_id);
        map.put("house_area_id", house_area_id);
        map.put("house_type_id", house_type_id);
        map.put("ren_type_number", ren_type_number);
        map.put("title", title);
        map.put("time_type", time_type);
        map.put("price_sorting", price_sorting);
        httpInfo.setUrl(APIS.URL_HOUS_SALE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     *
     * @param page            页
     * @param pageSize        大小
     * @param house_second_id 市id
     * @param house_third_id  地区id
     * @param title           标题
     * @param time_type       时间筛选 1.七天内  2.一个月内 3.半年内  4.一年内  5.一年以上
     * @param house_area_id   面积id
     * @param house_type_id   房屋类型id
     * @param ren_type_number 房屋户型id
     * @param price_sorting   排序 1.价格最高 2.价格最低
     */
    public void refreshData(int page, int pageSize, int house_second_id, int house_third_id, String title, String time_type,
                            int house_area_id, int house_type_id, int ren_type_number, String price_sorting) {
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("page", page);
        map.put("page_count", pageSize);
        map.put("house_second_id", house_second_id);
        map.put("house_third_id", house_third_id);
        map.put("house_area_id", house_area_id);
        map.put("house_type_id", house_type_id);
        map.put("ren_type_number", ren_type_number);
        map.put("title", title);
        map.put("time_type", time_type);
        map.put("price_sorting", price_sorting);
        httpInfo.setUrl(APIS.URL_HOUS_SALE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     *
     * @param page            页
     * @param pageSize        大小
     * @param house_second_id 市id
     * @param house_third_id  地区id
     * @param title           标题
     * @param time_type       时间筛选 1.七天内  2.一个月内 3.半年内  4.一年内  5.一年以上
     * @param house_area_id   面积id
     * @param house_type_id   房屋类型id
     * @param ren_type_number 房屋户型id
     * @param price_sorting   排序 1.价格最高 2.价格最低
     */
    public void loadNextData(int page, int pageSize, int house_second_id, int house_third_id, String title, String time_type,
                             int house_area_id, int house_type_id, int ren_type_number, String price_sorting) {
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("page", page);
        map.put("page_count", pageSize);
        map.put("house_second_id", house_second_id);
        map.put("house_third_id", house_third_id);
        map.put("house_area_id", house_area_id);
        map.put("house_type_id", house_type_id);
        map.put("ren_type_number", ren_type_number);
        map.put("title", title);
        map.put("time_type", time_type);
        map.put("price_sorting", price_sorting);
        httpInfo.setUrl(APIS.URL_HOUS_SALE_LIST);
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
        httpInfo.setUrl(APIS.URL_THIRD_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 分类
     */
    public Callback.Cancelable loadClassData() {
        return new NetUtil().setCallBackData(
                new BaseNewNetModelimpl<List<HouseClassModel>>() {
                    @Override
                    protected void onFail(int type, String msg) {
                        view.dismiss();
                    }

                    @Override
                    protected void onSuccess(int code, BaseResultInfo<List<HouseClassModel>> respnse, String source) {
                        List<HouseClassModel> data = respnse.getData();
                        List<Map<String, Object>> maps = new ArrayList<>();
                        if (data != null) {
                            Map<String, Object> map1 = new HashMap<>();
                            map1.put("id", 0);
                            map1.put("text", "全部");
                            maps.add(map1);
                            for (HouseClassModel datum : data) {
                                Map<String, Object> map = new HashMap<>();
                                map.put("id", datum.getHouse_type_id());
                                map.put("text", datum.getHouse_type_name());
                                maps.add(map);
                            }
                        }
                        view.loadClassDataSuccess(maps);
                    }

                    @Override
                    protected Type getType() {
                        return new TypeToken<BaseResultInfo<List<HouseClassModel>>>() {
                        }.getType();
                    }
                })
                .setUrl(APIS.URL_HOUSE_TYPE)
                .buildPost();

    }

    /**
     * 加载价格数据
     */
    public void loadPriceData() {
//       1.价格最高 2.价格最低
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 0);
        map.put("text", "全部");
        maps.add(map);
        Map<String, Object> map0 = new HashMap<>();
        map0.put("id", 1);
        map0.put("text", "价格最高");
        maps.add(map0);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 2);
        map1.put("text", "价格最低");
        maps.add(map1);
        view.loadPriceDataSuccess(maps);
    }

    public  void loadTimeData(){
        List<Map<String, Object>> maps = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", 0);
        map.put("text", "全部");
        maps.add(map);
        Map<String, Object> map0 = new HashMap<>();
        map0.put("id", 1);
        map0.put("text", "七天内");
        maps.add(map0);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("id", 2);
        map1.put("text", "一个月内");
        maps.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("id", 3);
        map2.put("text", "半年内");
        maps.add(map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("id", 4);
        map3.put("text", "一年内");
        maps.add(map3);
        Map<String, Object> map4 = new HashMap<>();
        map4.put("id", 5);
        map4.put("text", "一年以上");
        maps.add(map4);
        view.loadTimeDataSuccess(maps);
    }

    /**
     * 加载更多赛选
     *
     * @param
     */
    public void loadMoreData() {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, MORE_DATA);
        HttpInfo httpInfo = new HttpInfo();
        httpInfo.setUrl(APIS.URL_HOUS_MORE);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
            case REFRESH_DATA:
            case NEXT_DATA:
                type = new TypeReference<BaseResultInfo<HouseSealListBean>>() {
                }.getType();
                break;
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>() {
                }.getType();
                break;
            case PRICE_DATA:
                type = new TypeReference<BaseResultInfo<List<HousePriceBean>>>() {
                }.getType();
                break;
            case MORE_DATA:
                type = new TypeReference<BaseResultInfo<HouseMoreBean>>() {
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
                HouseSealListBean data = (HouseSealListBean) result.getData();
                if (data != null) {
                    view.initDataSuccess(data);
                }
                break;
            case REFRESH_DATA:
                HouseSealListBean refreshData = (HouseSealListBean) result.getData();
                if (refreshData != null) {
                    view.refreshDataSuccess(refreshData);
                }
                break;
            case NEXT_DATA:
                HouseSealListBean nextData = (HouseSealListBean) result.getData();
                if (nextData != null) {
                    view.loadNextDataSuccess(nextData);
                }
                break;
            case AREA_LIST:
                List<ThirdAreaBean> areaBeans = (List<ThirdAreaBean>) result.getData();
                if (areaBeans != null) {
                    List<Map<String, Object>> maps = new ArrayList<>();
                    HashMap<String, Object> all = new HashMap<>();
                    all.put("id", 0);
                    all.put("text", "全部");
                    maps.add(0, all);
                    for (ThirdAreaBean bean : areaBeans) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", bean.getThird_area_id());
                        map.put("text", bean.getThird_area_name());
                        maps.add(map);
                    }
                    view.loadAreaDataSuccess(maps);
                }
                break;
            case PRICE_DATA:
                List<HousePriceBean> priceBeans = (List<HousePriceBean>) result.getData();
                if (priceBeans != null) {
                    List<Map<String, Object>> maps = new ArrayList<>();
                    Map<String, Object> all = new HashMap<>();
                    all.put("id", 0);
                    all.put("text", "全部");
                    maps.add(0, all);
                    for (HousePriceBean bean : priceBeans) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("id", bean.getHouse_price_id());
                        map.put("text", bean.getHouse_price_name());
                        maps.add(map);
                    }
                    view.loadPriceDataSuccess(maps);
                }
                break;
            case MORE_DATA:
                HouseMoreBean moreBean = (HouseMoreBean) result.getData();
                if (moreBean != null) {
                    view.loadMoreDataSuccess(moreBean);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                break;
            case REFRESH_DATA:
                view.refreshDataFailure();
                break;
            case NEXT_DATA:
                view.dismiss();
                view.loadNextDataFailure();
                break;
            case MORE_DATA:
                break;
        }
    }
}
