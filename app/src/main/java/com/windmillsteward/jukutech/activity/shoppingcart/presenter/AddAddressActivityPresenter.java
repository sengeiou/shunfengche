package com.windmillsteward.jukutech.activity.shoppingcart.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.shoppingcart.activity.AddAddressActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class AddAddressActivityPresenter extends BaseNetModelImpl {

    private final int INIT_DATA=1;

    private AddAddressActivityView view;

    public AddAddressActivityPresenter(AddAddressActivityView view) {
        this.view = view;
    }


    /**
     *
     * @param access_token token
     * @param user_name 收货人
     * @param mobile_phone 联系电话
     * @param first_area_id 省
     * @param second_area_id 市
     * @param third_area_id 区
     * @param address 详细地址
     * @param is_default 是否默认0不是1是
     */
    public void addAddress(String access_token, String user_name, String mobile_phone,int first_area_id,int second_area_id,int third_area_id,String address,int is_default){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("user_name", user_name);
        map.put("mobile_phone", mobile_phone);
        map.put("first_area_id", first_area_id);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("address", address);
        map.put("is_default", is_default);
        httpInfo.setUrl(APIS.URL_ADD_SHOPPING_ADDRESS);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void editAddress(int address_id,String access_token, String user_name, String mobile_phone,int first_area_id,int second_area_id,int third_area_id,String address,int is_default){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("address_id", address_id);
        map.put("access_token", access_token);
        map.put("user_name", user_name);
        map.put("mobile_phone", mobile_phone);
        map.put("first_area_id", first_area_id);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("address", address);
        map.put("is_default", is_default);
        httpInfo.setUrl(APIS.URL_EDIT_SHOPPING_ADDRESS);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<String>>() {}.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case INIT_DATA:
                view.addAddressSuccess();
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,0);
    }
}
