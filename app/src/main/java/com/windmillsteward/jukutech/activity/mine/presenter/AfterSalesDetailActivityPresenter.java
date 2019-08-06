package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.AboutUsView;
import com.windmillsteward.jukutech.activity.mine.activity.AfterSalesDetailActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.AfterSalesDetailBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 * 时间：2018/4/22/022
 * 作者：xjh
 */
public class AfterSalesDetailActivityPresenter extends BaseNetModelImpl {
    private final int INIT_DATA = 1;
    private AfterSalesDetailActivityView view;

    public AfterSalesDetailActivityPresenter(AfterSalesDetailActivityView view) {
        this.view = view;
    }


    public void initData(String access_token,int record_id) {
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("access_token",access_token);
        map.put("record_id",record_id);
        httpInfo.setUrl(APIS.URL_AFTER_SALE_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }




    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<AfterSalesDetailBean>>() {
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
                AfterSalesDetailBean data = (AfterSalesDetailBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
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
