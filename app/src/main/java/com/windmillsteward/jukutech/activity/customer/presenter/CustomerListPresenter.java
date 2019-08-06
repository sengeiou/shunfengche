package com.windmillsteward.jukutech.activity.customer.presenter;

import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.customer.activity.CustomerListView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：客服列表
 * 时间：2018/4/19
 * 作者：cyq
 */

public class CustomerListPresenter extends BaseNetModelImpl {

    private final int CUSTOMER_URL = 1;
    private CustomerListView view;

    public CustomerListPresenter(CustomerListView view) {
        this.view = view;
    }


    /**
     * 获取客服聊天地址
     *
     * @param type
     * @param area_id
     * @param access_token
     */
    public void getCustomerUrl(int type,int area_id, String access_token) {
        DataLoader dataLoader = new DataLoader(this, CUSTOMER_URL);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("type", type);
        map.put("area_id", area_id);
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_GET_CUSTOMER_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }




    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case CUSTOMER_URL:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case CUSTOMER_URL:
                String customer_url = result.getData().toString();
                view.getCustomerUrlSuccess(customer_url);
                break;

        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case CUSTOMER_URL:
                view.dismiss();
                view.showTips(msg, Toast.LENGTH_SHORT);
                view.getCustomerUrlFailed(code, msg);
                break;

        }
    }
}
