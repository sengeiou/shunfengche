package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.ApplyWithdrawView;
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
public class ApplyWithdrawPresenter extends BaseNetModelImpl {

    private static final int APPLY_WITHDRAW = 1;

    private ApplyWithdrawView view;

    public ApplyWithdrawPresenter(ApplyWithdrawView view) {
        this.view = view;
    }

    /**
     * 申请提现
     */
    public void applyWithdraw(String access_token,int account_id,String price) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, APPLY_WITHDRAW);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("account_id", account_id);
        map.put("price", price);
        httpInfo.setUrl(APIS.URL_APPLY_WITHDRAW);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }



    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case APPLY_WITHDRAW:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case APPLY_WITHDRAW:
                view.dismiss();
               view.applyWithdrawSuccess();
                break;

        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case APPLY_WITHDRAW:
                view.dismiss();
                view.applyWithdrawFailed(code,msg);
                break;

        }
    }
}
