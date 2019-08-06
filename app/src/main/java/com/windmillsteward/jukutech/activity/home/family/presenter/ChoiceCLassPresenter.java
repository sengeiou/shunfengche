package com.windmillsteward.jukutech.activity.home.family.presenter;

import android.util.Log;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.family.activity.ChoiceCLassView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.RequireClassBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 * 时间：2018/1/19
 * 作者：xjh
 */

public class ChoiceCLassPresenter extends BaseNetModelImpl {

    private final int INIT_DATA = 1;

    private ChoiceCLassView view;

    public ChoiceCLassPresenter(ChoiceCLassView view) {
        this.view = view;
    }

    public void initData(){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        httpInfo.setUrl(APIS.URL_REQUIRE_CLASS_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<List<RequireClassBean>>>() {
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
                view.initData((List<RequireClassBean>) result.getData());
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action){
            case INIT_DATA:
                view.dismiss();
                break;
        }
    }
}
