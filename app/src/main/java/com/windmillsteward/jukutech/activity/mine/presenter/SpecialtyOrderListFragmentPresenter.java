package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.fragment.MyPublishView;
import com.windmillsteward.jukutech.activity.mine.fragment.SpecialtyOrderListFragmentView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.MyPublishBean;
import com.windmillsteward.jukutech.bean.SpecialtyOrderListBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public class SpecialtyOrderListFragmentPresenter extends BaseNetModelImpl {
    private static final int INIT_DATA = 1;
    private static final int NEXT_DATA = 2;
    private static final int REFRESH_DATA = 3;
    private static final int DELETE_ORDER = 4;
    private static final int CLOSE_ORDER = 5;
    private static final int CONFIRM_ORDER = 6;

    private SpecialtyOrderListFragmentView view;

    public SpecialtyOrderListFragmentPresenter(SpecialtyOrderListFragmentView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     * @param order_status 订单状态：【0：全部，1：待付款，2：待发货，3：待收货，5：已取消】
     */
    public void initData(String access_token,int page,int page_count,int order_status) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("order_status", order_status);
        httpInfo.setUrl(APIS.URL_SPECIALTY_ORDER_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     */
    public void refreshData(String access_token,int page,int page_count,int order_status) {
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("order_status", order_status);
        httpInfo.setUrl(APIS.URL_SPECIALTY_ORDER_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     */
    public void loadNextData(String access_token,int page,int page_count,int order_status) {
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("order_status", order_status);
        httpInfo.setUrl(APIS.URL_SPECIALTY_ORDER_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 删除订单
     * @param access_token
     * @param order_id
     */
    public void deleteOrder(String access_token,int order_id) {
        DataLoader dataLoader = new DataLoader(this, DELETE_ORDER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("order_id", order_id);
        httpInfo.setUrl(APIS.URL_DELETE_SPECIALTY_ORDER);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    /**
     * 关闭订单
     * @param access_token
     * @param order_id
     */
    public void closeOrder(String access_token,int order_id) {
        DataLoader dataLoader = new DataLoader(this, CLOSE_ORDER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("order_id", order_id);
        httpInfo.setUrl(APIS.URL_CLOSE_SPECIALTY_ORDER);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 确认收货
     * @param access_token
     * @param order_id
     */
    public void confirmOrder(String access_token,int order_id) {
        DataLoader dataLoader = new DataLoader(this, CONFIRM_ORDER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("order_id", order_id);
        httpInfo.setUrl(APIS.URL_CONFIRM_SPECIALTY_ORDER);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }
    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
            case NEXT_DATA:
            case REFRESH_DATA:
                type = new TypeReference<BaseResultInfo<SpecialtyOrderListBean>>() {
                }.getType();
                break;
            case DELETE_ORDER:
            case CLOSE_ORDER:
            case CONFIRM_ORDER:
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
                SpecialtyOrderListBean data = (SpecialtyOrderListBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case REFRESH_DATA:
                SpecialtyOrderListBean refreshData = (SpecialtyOrderListBean) result.getData();
                if (refreshData!=null) {
                    view.refreshDataSuccess(refreshData);
                } else {
                    view.refreshDataFailure();
                }
                break;
            case NEXT_DATA:
                SpecialtyOrderListBean nextData = (SpecialtyOrderListBean) result.getData();
                if (nextData!=null) {
                    view.loadNextDataSuccess(nextData);
                } else {
                    view.loadNextDataFailure();
                }
                break;
            case DELETE_ORDER:
                view.deleteOrderSuccess();
                break;
            case CLOSE_ORDER:
                view.closeOrderSuccess();
                break;
            case CONFIRM_ORDER:
                view.confirmOrderSuccess();
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,0);
        switch (action) {
            case NEXT_DATA:
                view.loadNextDataFailure();
                break;
            case REFRESH_DATA:
                view.refreshDataFailure();
                break;
        }
    }
}
