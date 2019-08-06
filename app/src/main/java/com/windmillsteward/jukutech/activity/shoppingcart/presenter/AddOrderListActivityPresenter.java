package com.windmillsteward.jukutech.activity.shoppingcart.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.shoppingcart.activity.AddOrderListActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.AddOrderListBean;
import com.windmillsteward.jukutech.bean.BeforeAddOrderRequest;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class AddOrderListActivityPresenter extends BaseNetModelImpl {

    private final int INIT_DATA=1;

    private AddOrderListActivityView view;

    public AddOrderListActivityPresenter(AddOrderListActivityView view) {
        this.view = view;
    }


    /**
     * 初始化数据
     * @param access_token token
     * @param cart_id 购物车ID
     * @param address_id 地址ID
     * @param list shuju
     */
    public void initData(String access_token, int cart_id, int address_id, List<BeforeAddOrderRequest.CommodityListBean> list){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("cart_id", cart_id);
        map.put("address_id", address_id);
        map.put("commodity_list", JSON.toJSONString(list));
        httpInfo.setUrl(APIS.URL_BEFORE_ADD_ORDER);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<AddOrderListBean>>() {}.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case INIT_DATA:
                AddOrderListBean data = (AddOrderListBean) result.getData();
                view.initDataSuccess(data);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case INIT_DATA:

                break;
        }
    }
}
