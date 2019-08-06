package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.RefundDeliveryActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/4/22/022
 * 作者：xjh
 */
public class RefundDeliveryActivityPresenter extends BaseNetModelImpl {

    private static final int SEND = 1;
    private RefundDeliveryActivityView view;

    public RefundDeliveryActivityPresenter(RefundDeliveryActivityView view) {
        this.view = view;
    }

    public void send(String access_token,int record_id,String logistics_single_number,String order_sn) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, SEND);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("record_id", record_id);
        map.put("logistics_single_number", logistics_single_number);
        map.put("order_sn", order_sn);
        httpInfo.setUrl(APIS.URL_REFUND_DELIVERY);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case SEND:
                type = new TypeReference<BaseResultInfo<String>>(){}.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case SEND:
                view.sendSuccess();
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,0);
    }
}
