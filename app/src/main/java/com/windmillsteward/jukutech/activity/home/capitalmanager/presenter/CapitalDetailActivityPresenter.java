package com.windmillsteward.jukutech.activity.home.capitalmanager.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.CapitalDetailActivityView;
import com.windmillsteward.jukutech.activity.home.insurance.activity.InsuranceDetailActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.CapitalDetailBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.InsuranceDetailBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 * 时间：2018/3/29/029
 * 作者：xjh
 */
public class CapitalDetailActivityPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA=1;
    private static final int COLLECT = 2;
    private static final int CANCEL_COLLECT = 3;
    private static final int IS_CHARGE = 4;

    private CapitalDetailActivityView view;

    public CapitalDetailActivityPresenter(CapitalDetailActivityView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     */
    public void initData(String access_token,int capital_id){
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("capital_id", capital_id);
        httpInfo.setUrl(APIS.URL_CAPITAL_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void collect(int capital_id,String access_token) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, COLLECT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("capital_id", capital_id);
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_CAPITAL_COLLECT);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void cancelCollect(int capital_id,String access_token) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, CANCEL_COLLECT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("capital_id", capital_id);
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_CAPITAL_CANCEL_COLLECT);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 判断发布是否收费
     * @param access_token
     */
    public void isCharge(String access_token,int type,int relate_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, IS_CHARGE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("access_token", access_token);
        map.put("relate_id", relate_id);
        httpInfo.setUrl(APIS.URL_IS_CHARGE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<CapitalDetailBean>>() {
                }.getType();
                break;
            case COLLECT:
            case CANCEL_COLLECT:
                type = new TypeReference<BaseResultInfo<String>>(){}.getType();
                break;
            case IS_CHARGE:
                type = new TypeReference<BaseResultInfo<ChargeResultBean>>() {
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
                CapitalDetailBean data = (CapitalDetailBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case COLLECT:
                view.dismiss();
                view.collectSuccess();
                view.showTips("收藏成功",0);
                break;
            case CANCEL_COLLECT:
                view.dismiss();
                view.cancelCollectSuccess();
                view.showTips("取消收藏成功",0);
                break;
            case IS_CHARGE:
                view.dismiss();
                ChargeResultBean chargeResultBean = (ChargeResultBean) result.getData();
                if (chargeResultBean!=null) {
                    view.isChargeResult(chargeResultBean);
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
