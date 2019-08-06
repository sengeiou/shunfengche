package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.activity.mine.activity.MyInfoView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.CountOrderNumberBean;
import com.windmillsteward.jukutech.bean.UserInfo;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 个人信息操作类
 */

public class MyInfoImpl extends BaseNetModelImpl {
    private final int GET_MY_INFO = 1;
    private final int COUNT_ORDER_NUMBER = 2;

    private MyInfoView view;

    public MyInfoImpl(MyInfoView view) {
        this.view = view;
    }

    /**
     * 获取个人信息
     */
    public void getMyInfo() {
        DataLoader dataLoader = new DataLoader(this, GET_MY_INFO);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("access_token", Hawk.get(Define.ACCESS_TOKEN));
        httpInfo.setUrl(APIS.URL_GET_MY_INFO);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    public void geCountOrderNumberData() {
        DataLoader dataLoader = new DataLoader(this, COUNT_ORDER_NUMBER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("access_token", Hawk.get(Define.ACCESS_TOKEN));
        httpInfo.setUrl(APIS.URL_COUNT_ORDER_NUMBER);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case GET_MY_INFO:
                type = new TypeReference<BaseResultInfo<UserInfo>>() {
                }.getType();
                break;
            case COUNT_ORDER_NUMBER:
                type = new TypeReference<BaseResultInfo<CountOrderNumberBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case GET_MY_INFO:
                UserInfo data = (UserInfo) result.getData();
                view.getMyInfoSuccess(data);
                break;
            case COUNT_ORDER_NUMBER:
                CountOrderNumberBean resultData = (CountOrderNumberBean) result.getData();
                if (resultData!=null) {
                    view.getCountOrderNumber(resultData);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case GET_MY_INFO:
                view.getMyInfoFailed(code, msg);
                break;
        }
    }
}