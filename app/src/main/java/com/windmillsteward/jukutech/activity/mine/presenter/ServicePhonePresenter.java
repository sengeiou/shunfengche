package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.ServicePhoneView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.ModuleIntroduceBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 客服电话操作类
 */

public class ServicePhonePresenter extends BaseNetModelImpl {
    private final int SERVICE_PHONE = 1;
    private final int MODULE_INTRODUCE = 2;
    private ServicePhoneView view;

    public ServicePhonePresenter(ServicePhoneView view) {
        this.view = view;
    }

    /**
     * 客服电话
     */
    public void getServicePhone() {
        DataLoader dataLoader = new DataLoader(this, SERVICE_PHONE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        httpInfo.setUrl(APIS.URL_SERVICE_PHONE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 获取模块介绍
     */
    public void getModuleIntroduce() {
        DataLoader dataLoader = new DataLoader(this, MODULE_INTRODUCE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        httpInfo.setUrl(APIS.URL_MODULE_INTRODUCE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case SERVICE_PHONE:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case MODULE_INTRODUCE:
                type = new TypeReference<BaseResultInfo<List<ModuleIntroduceBean>>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case SERVICE_PHONE:
                String data = (String) result.getData();
                try {
                    view.Success(new JSONObject(data).getString("hotline_tel"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case MODULE_INTRODUCE:
                List<ModuleIntroduceBean> introduceBeanList = (List<ModuleIntroduceBean>) result.getData();
                view.getModuleListSuccess(introduceBeanList);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case SERVICE_PHONE:
                view.Failed(code, msg);
                break;
            case MODULE_INTRODUCE:
                view.showTips(msg, 1);
                break;
        }
    }
}