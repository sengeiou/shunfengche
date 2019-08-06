package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.OrderHotelDetailView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.OrderFamilyDetailBean;
import com.windmillsteward.jukutech.bean.OrderHotelDetailBean;
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
public class OrderHotelDetailPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;
    private static final int CANCEL_ORDER = 2;
    private static final int DELETE_ORDER = 3;


    private OrderHotelDetailView view;

    public OrderHotelDetailPresenter(OrderHotelDetailView view) {
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
        httpInfo.setUrl(APIS.URL_ORDER_HOTEL_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 取消酒店订单
     * @param access_token token
     * @param order_id 订单id
     */
    public void cancelOrder(String access_token,int order_id){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, CANCEL_ORDER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("order_id", order_id);
        httpInfo.setUrl(APIS.URL_CANCEL_HOTEL_ORDER);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 取消酒店订单
     * @param access_token token
     * @param order_id 订单id
     */
    public void deleteOrder(String access_token,int order_id){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, DELETE_ORDER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("order_id", order_id);
        httpInfo.setUrl(APIS.URL_CDELETE_HOTEL_ORDER);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }



    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<OrderHotelDetailBean>>() {
                }.getType();
                break;
            case CANCEL_ORDER:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case DELETE_ORDER:
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
                OrderHotelDetailBean data = (OrderHotelDetailBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case CANCEL_ORDER:
                view.dismiss();
                view.cancelHotelOrderSuccess();
                break;
            case DELETE_ORDER:
                view.dismiss();
                view.deleteHotelOrderSuccess();
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
            case CANCEL_ORDER:
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
