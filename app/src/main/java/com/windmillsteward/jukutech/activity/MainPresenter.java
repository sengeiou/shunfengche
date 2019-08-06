package com.windmillsteward.jukutech.activity;

import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.newpage.model.OrderParentModel;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.IsHaveHongBaoBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：首页请求
 * author:cyq
 * 2019-01-04
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MainPresenter extends BaseNetModelImpl {

    private final int IS_COUPON_HONG_BAO = 1;
    private final int GET_COUPON_HONG_BAO = 2;
    private final int IS_HAVE_HONG_BAO = 3;
    private final int GET_MONEY_HONG_BAO = 4;
    private final int ON_GOING_ORDER_NUM = 5;
    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }

    /**
     * 是否有优惠券红包抢
     */
    public void isGetHongBao() {
        DataLoader dataLoader = new DataLoader(this, IS_COUPON_HONG_BAO);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        httpInfo.setUrl(APIS.URL_IS_GET_HONGBAO);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 抢优惠券红包
     */
    public void getHongBao() {
        DataLoader dataLoader = new DataLoader(this, GET_COUPON_HONG_BAO);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        httpInfo.setUrl(APIS.URL_GET_COUPON);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
        view.showDialog("");
    }

    /**
     * 是否有现金红包抢
     */
    public void isHaveHongBao() {
        DataLoader dataLoader = new DataLoader(this, IS_HAVE_HONG_BAO);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        httpInfo.setUrl(APIS.URL_IS_HAVE_HONGBAO);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 是否有现金红包抢
     */
    public void getMoneyHongBao() {
        DataLoader dataLoader = new DataLoader(this, GET_MONEY_HONG_BAO);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        httpInfo.setUrl(APIS.URL_GET_MONEY_HONGBAO);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
        view.showDialog("");
    }

    /**
     * 获取正在进行中的订单数量
     */
    public void onGoingOrderNum(int status) {
        DataLoader dataLoader = new DataLoader(this, ON_GOING_ORDER_NUM);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("status", status);
        httpInfo.setUrl(APIS.URL_TALENT_GET_MATCH_ORDER_LIST_NEW);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
        view.showDialog("");
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case IS_COUPON_HONG_BAO:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case GET_COUPON_HONG_BAO:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case IS_HAVE_HONG_BAO:
                type = new TypeReference<BaseResultInfo<IsHaveHongBaoBean>>() {
                }.getType();
                break;
            case GET_MONEY_HONG_BAO:
                type = new TypeReference<BaseResultInfo<IsHaveHongBaoBean>>() {
                }.getType();
                break;
            case ON_GOING_ORDER_NUM:
                type = new TypeReference<BaseResultInfo<OrderParentModel>>() {
                }.getType();
                break;

        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case IS_COUPON_HONG_BAO:
                String data = (String) result.getData();
                JSONObject object = null;
                try {
                    object = new JSONObject(data);
                    int is_get_coupon = object.getInt("is_get_coupon");
                    view.isGetCouponSuccess(is_get_coupon);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case GET_COUPON_HONG_BAO:
                view.getCouponHongBaoSuccess();
                break;
            case IS_HAVE_HONG_BAO:
                IsHaveHongBaoBean hongBaoBean = (IsHaveHongBaoBean) result.getData();
                view.isHaveHongBao(hongBaoBean);
                break;
            case GET_MONEY_HONG_BAO:
                IsHaveHongBaoBean getHongBaoBean = (IsHaveHongBaoBean) result.getData();
                view.getMoneyHongBao(result.getMsg(), getHongBaoBean);
                break;
            case ON_GOING_ORDER_NUM:
                OrderParentModel orderParentModel = (OrderParentModel) result.getData();
                OrderParentModel.DataBean list = orderParentModel.getList();
                if (list != null) {
                    view.getOnGoingOrderNum(list.getTotalRow(),orderParentModel.getTotalUnreadNum());
                }
                break;

        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case IS_COUPON_HONG_BAO:
                break;
            case GET_COUPON_HONG_BAO:
                view.showTips(msg, Toast.LENGTH_SHORT);
                break;
            case IS_HAVE_HONG_BAO:
                view.showTips(msg, Toast.LENGTH_SHORT);
                break;
            case GET_MONEY_HONG_BAO:
                view.showTips(msg, Toast.LENGTH_SHORT);
                break;
        }
    }
}
