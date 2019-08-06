package com.windmillsteward.jukutech.activity.newpage.customerservice.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.newpage.customerservice.activity.HomeCustomerView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.HomeCustomerClassicModel;
import com.windmillsteward.jukutech.bean.HomeCustomerModel;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date: on 2018/10/2
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class HomeCustomerActPresenter extends BaseNetModelImpl {
    private static final int HEADER_DATA = 1;
    private static final int NORMAL_DATA = 2;
    private HomeCustomerView view;

    public HomeCustomerActPresenter(HomeCustomerView view) {
        this.view = view;
    }

    /**
     * 获取分类信息
     */
    public void getHeaderData() {
        DataLoader dataLoader = new DataLoader(this, HEADER_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_HOME_CUSTOMER_CLASSIC_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 获取列表数据
     *
     * @param longitude
     * @param latitude
     * @param search_distance
     * @param class_id
     */
    public void getListData(String longitude, String latitude, int search_distance, int class_id) {
        DataLoader dataLoader = new DataLoader(this, NORMAL_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        map.put("search_distance", search_distance);
        if (class_id != -1) {
            map.put("class_id", class_id);
        }
        httpInfo.setUrl(APIS.URL_HOME_CUSTOMER_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case HEADER_DATA:
                type = new TypeReference<BaseResultInfo<List<HomeCustomerClassicModel>>>() {
                }.getType();
                break;
            case NORMAL_DATA:
                type = new TypeReference<BaseResultInfo<List<HomeCustomerModel>>>() {
                }.getType();
                break;
        }

        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        view.showContentView();
        switch (action) {
            case HEADER_DATA:
                List<HomeCustomerClassicModel> data = (List<HomeCustomerClassicModel>) result.getData();
                if (data != null) {
                    view.showHeaderData(data);
                }
                break;
            case NORMAL_DATA:
                List<HomeCustomerModel> data1 = (List<HomeCustomerModel>) result.getData();
                if (data1 != null)
                    view.showListData(data1);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg, 1);
        view.showErrorView();
    }
}
