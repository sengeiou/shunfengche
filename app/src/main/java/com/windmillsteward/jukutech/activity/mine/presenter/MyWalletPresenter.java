package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.MyWalletView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.MyWalletBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：我的钱包实现类
 * 时间：2018/3/5
 * 作者：cyq
 */
public class MyWalletPresenter extends BaseNetModelImpl {

    private static final int MY_WALLET_BALANCE = 1;

    private MyWalletView view;

    public MyWalletPresenter(MyWalletView view) {
        this.view = view;
    }

    /**
     * 获取钱包余额
     */
    public void getBalance(String access_token) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, MY_WALLET_BALANCE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_MY_WALLET);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }



    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case MY_WALLET_BALANCE:
                type = new TypeReference<BaseResultInfo<MyWalletBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case MY_WALLET_BALANCE:
                view.dismiss();
                MyWalletBean data = (MyWalletBean) result.getData();
                if (data!=null) {
                    view.getDataSuccess(data);
                }
                break;

        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case MY_WALLET_BALANCE:
                view.dismiss();
                view.getDataFailed(code,msg);
                break;

        }
    }
}
