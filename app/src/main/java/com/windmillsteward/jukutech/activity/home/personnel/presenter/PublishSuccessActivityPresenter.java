package com.windmillsteward.jukutech.activity.home.personnel.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PublishSuccessActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.PositionDetailBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * File: PublishSuccessActivityPresenter.java
 * 作者: 大海
 * 创建日期: 2018/5/9 0009 10:45
 * 描述：
 */
public class PublishSuccessActivityPresenter extends BaseNetModelImpl {

    private final int INIT_DATA = 1;

    private PublishSuccessActivityView view;

    public PublishSuccessActivityPresenter(PublishSuccessActivityView view) {
        this.view = view;
    }

    public void initData(int type){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type",type);
        httpInfo.setUrl(APIS.URL_IS_AUDIT);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<String>>() {
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
                String data = (String) result.getData();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    boolean aBoolean = jsonObject.getBoolean("is_check");
                    view.initDataSuccess(aBoolean);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
    }
}
