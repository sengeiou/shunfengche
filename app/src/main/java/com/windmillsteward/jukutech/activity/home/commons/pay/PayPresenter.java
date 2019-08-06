package com.windmillsteward.jukutech.activity.home.commons.pay;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.PayBeforeResultBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/3/4/004
 * 作者：xjh
 */
public class PayPresenter extends BaseNetModelImpl {

    private static final int BEFORE_PAY = 0;
    private static final int AFTER_PAY = 1;

    public PayView view;

    public PayPresenter(PayView view) {
        this.view = view;
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case BEFORE_PAY:
                type = new TypeReference<BaseResultInfo<PayBeforeResultBean>>(){}.getType();
                break;
            case AFTER_PAY:
                type = new TypeReference<BaseResultInfo<String>>(){}.getType();
                break;
        }
        return type;
    }

    /**
     *
     * @param coupon_receive_id 优惠券
     * @param order_sn 订单编号
     */
    public void payBrfore(String access_token,int type,int coupon_receive_id,String order_sn,int relate_id){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, BEFORE_PAY);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("type", type);
        map.put("coupon_receive_id", coupon_receive_id);
        map.put("order_sn", order_sn);
        map.put("relate_id", relate_id);
        httpInfo.setUrl(APIS.URL_BEFORE_PAY);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

//    public void payAfter(String access_token,String order_sn,int pay_type) {
//        view.showDialog("");
//        DataLoader dataLoader = new DataLoader(this, AFTER_PAY);
//        HttpInfo httpInfo = new HttpInfo();
//        Map<String, Object> map = new HashMap<>();
//        map.put("access_token", access_token);
//        map.put("order_sn", order_sn);
//        map.put("relate_id", pay_type);
//        httpInfo.setUrl(APIS.URL_AFTER_PAY);
//        httpInfo.setParams(map);
//        dataLoader.httpPost(httpInfo);
//    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case BEFORE_PAY:
                view.dismiss();
                PayBeforeResultBean payBeforeResultBean = (PayBeforeResultBean) result.getData();
                if (payBeforeResultBean!=null) {
                    view.payBefore(payBeforeResultBean);
                } else {
                    view.showTips("获取订单信息失败",0);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case BEFORE_PAY:
                view.dismiss();
                view.showTips("获取订单信息失败",0);
                break;
        }
    }
}
