package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.WalletListDetailView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.WalletListDetailBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 钱包明细详情操作类
 */

public class WalletListDetailPresenter extends BaseNetModelImpl {
    private final int WALLET_LIST_DETAIL = 1;
    private WalletListDetailView view;

    public WalletListDetailPresenter(WalletListDetailView view) {
        this.view = view;
    }

    /**
     * 钱包明细详情信息
     */
    public void getDetailData(String access_token,int detail_id,int detail_type ) {
        DataLoader dataLoader = new DataLoader(this, WALLET_LIST_DETAIL);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("access_token",access_token);
        map.put("detail_id",detail_id);
        map.put("detail_type",detail_type);
        httpInfo.setUrl(APIS.URL_MY_WALLET_DETAIL_LIST_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }




    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case WALLET_LIST_DETAIL:
                type = new TypeReference<BaseResultInfo<WalletListDetailBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case WALLET_LIST_DETAIL:
                if (view == null){
                    return;
                }
                WalletListDetailBean data = (WalletListDetailBean) result.getData();
                view.getWalletListDetailSuccess(data);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case WALLET_LIST_DETAIL:
                view.getWalletListDetailFailed(code, msg);
                break;
        }
    }
}