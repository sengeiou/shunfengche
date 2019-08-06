package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.AddAccountView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
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
public class AddAccountPresenter extends BaseNetModelImpl {

    private static final int ADD_ACCOUNT = 1;

    private AddAccountView view;

    public AddAccountPresenter(AddAccountView view) {
        this.view = view;
    }

    /**
     * 添加支付宝
     */
    public void addAccount(String access_token,int type,String true_name,String account) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, ADD_ACCOUNT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("true_name", true_name);
        map.put("type", type);
        map.put("account", account);
        httpInfo.setUrl(APIS.URL_ADD_WITHDRAW_ACCOUNT);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 添加银行账户
     */
    public void addBankAccount(String access_token,int type,String true_name,String account,String for_bank,String sub_bank) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, ADD_ACCOUNT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("type", type);
        map.put("true_name", true_name);
        map.put("account", account);
        map.put("for_bank", for_bank);
        map.put("sub_bank", sub_bank);
        httpInfo.setUrl(APIS.URL_ADD_WITHDRAW_ACCOUNT);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }



    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case ADD_ACCOUNT:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case ADD_ACCOUNT:
                view.dismiss();
               view.addAccountSuccess();
                break;

        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case ADD_ACCOUNT:
                view.dismiss();
                view.addAccountFailed(code,msg);
                break;

        }
    }
}
