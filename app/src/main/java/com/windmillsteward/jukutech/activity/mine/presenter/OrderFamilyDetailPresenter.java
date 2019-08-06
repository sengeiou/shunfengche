package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.OrderFamilyDetailView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.OrderFamilyDetailBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 * 时间：2018/3/3/003
 * 作者：xjh
 */
public class OrderFamilyDetailPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;
    private static final int URGE_SMARTHOMEORDER = 2;
    private static final int CANCEL_ORDER = 3;
    private static final int FINISH_ORDER = 4;
    private static final int DELETE_ORDER = 5;


    private OrderFamilyDetailView view;

    public OrderFamilyDetailPresenter(OrderFamilyDetailView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     */
    public void initData(String access_token,int order_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("order_id", order_id);
        httpInfo.setUrl(APIS.URL_ORDER_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 催单
     * @param access_token
     * @param require_id
     */
    public void urgeSmartHomeOrder(String access_token, int require_id){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, URGE_SMARTHOMEORDER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("order_id", require_id);
        httpInfo.setUrl(APIS.URL_URGE_SMARTHOMEORDER);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 发布人取消订单
     * @param access_token
     * @param require_id
     */
    public void cancelOrder(String access_token, int require_id){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, CANCEL_ORDER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("require_id", require_id);
        httpInfo.setUrl(APIS.URL_EMPLOYEE_CANCEL_ORDER);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 完成订单
     * @param access_token
     * @param require_id
     */
    public void finishOrder(String access_token, int require_id){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, FINISH_ORDER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("require_id", require_id);
        httpInfo.setUrl(APIS.URL_FINISH_SMARTHOMEORDER);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 催单
     * @param access_token
     * @param require_id
     */
    public void deleteOrder(String access_token, int require_id){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, DELETE_ORDER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("order_id", require_id);
        httpInfo.setUrl(APIS.URL_CDELETE_HOTEL_ORDER);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<OrderFamilyDetailBean>>() {
                }.getType();
                break;
            case CANCEL_ORDER:
            case FINISH_ORDER:
            case DELETE_ORDER:
            case URGE_SMARTHOMEORDER:
                type = new TypeReference<BaseResultInfo<String>>() {
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
                OrderFamilyDetailBean data = (OrderFamilyDetailBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case URGE_SMARTHOMEORDER:
                view.dismiss();
                view.showTips("催单成功",0);
                break;
            case CANCEL_ORDER:
                view.dismiss();
                view.showTips("取消成功",1);
                view.cancelOrderSuccess();
                break;
            case FINISH_ORDER:
                view.dismiss();
                view.finishOrderSuccess();
                break;
            case DELETE_ORDER:
                view.dismiss();
                view.deleteOrderSuccess();
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                view.showTips(msg,0);
                break;
            case URGE_SMARTHOMEORDER:
                view.dismiss();
                view.showTips(msg,0);
                break;
            case CANCEL_ORDER:
                view.dismiss();
                view.showTips(msg,0);
                break;
            case FINISH_ORDER:
                view.dismiss();
                view.showTips(msg,0);
                break;
            case DELETE_ORDER:
                view.dismiss();
                view.showTips(msg,0);
                break;
        }
    }
}
