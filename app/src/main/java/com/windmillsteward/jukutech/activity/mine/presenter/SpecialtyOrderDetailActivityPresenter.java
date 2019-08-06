package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.SpecialtyOrderDetailActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.LogisticsInfoListBean;
import com.windmillsteward.jukutech.bean.SpecialtyOrderDetailBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/4/19 0019.
 */

public class SpecialtyOrderDetailActivityPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;
    private static final int LOGISTICS_INFO = 2;
    private static final int DELETE_ORDER = 3;
    private static final int CLOSE_ORDER = 4;
    private static final int CONFIRM_ORDER = 5;


    private SpecialtyOrderDetailActivityView view;

    public SpecialtyOrderDetailActivityPresenter(SpecialtyOrderDetailActivityView view) {
        this.view = view;
    }

    /**
     *
     */
    public void initData(String access_token,int order_id) {
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("access_token",access_token);
        map.put("order_id",order_id);
        httpInfo.setUrl(APIS.URL_SPECIALTY_ORDER_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void initLogisticsInfo(String access_token,String order_sn,String logistics_single_number) {
        DataLoader dataLoader = new DataLoader(this, LOGISTICS_INFO);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("access_token",access_token);
        map.put("order_sn",order_sn);
        map.put("logistics_single_number",logistics_single_number);
        httpInfo.setUrl(APIS.URL_LOGISTICS_INFO);
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
                type = new TypeReference<BaseResultInfo<SpecialtyOrderDetailBean>>() {
                }.getType();
                break;
            case LOGISTICS_INFO:
                type = new TypeReference<BaseResultInfo<LogisticsInfoListBean>>() {
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
                SpecialtyOrderDetailBean data = (SpecialtyOrderDetailBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case LOGISTICS_INFO:
                LogisticsInfoListBean infoListBean = (LogisticsInfoListBean) result.getData();
                if (infoListBean!=null) {
                    List<LogisticsInfoListBean.LogisticsInfoBean> logistics_info = infoListBean.getLogistics_info();
                    if (logistics_info!=null) {
                        if (logistics_info.size()>0) {
                            LogisticsInfoListBean.LogisticsInfoBean bean = logistics_info.get(logistics_info.size() - 1);
                            String acceptStation = bean.getAcceptStation();
                            String acceptTime = bean.getAcceptTime();
                            view.loadLogisticsInfoSuccess(acceptStation,acceptTime);
                        }
                    }
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
    }
}
