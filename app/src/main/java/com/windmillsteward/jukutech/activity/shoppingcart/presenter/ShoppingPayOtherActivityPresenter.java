package com.windmillsteward.jukutech.activity.shoppingcart.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.activity.shoppingcart.activity.ShoppingPayActivityView;
import com.windmillsteward.jukutech.activity.shoppingcart.activity.ShoppingPayOtherActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.BeforeAddOrderRequest;
import com.windmillsteward.jukutech.bean.BigHealthDetailBuyBean;
import com.windmillsteward.jukutech.bean.ShoppingPayBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 * 时间：2018/3/4/004
 * 作者：xjh
 */
public class ShoppingPayOtherActivityPresenter extends BaseNetModelImpl {

    private static final int BEFORE_PAY = 0;
    private static final int AFTER_PAY = 1;

    public ShoppingPayOtherActivityView view;

    public ShoppingPayOtherActivityPresenter(ShoppingPayOtherActivityView view) {
        this.view = view;
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case BEFORE_PAY:
                type = new TypeReference<BaseResultInfo<BigHealthDetailBuyBean>>(){}.getType();
                break;
            case AFTER_PAY:
                type = new TypeReference<BaseResultInfo<String>>(){}.getType();
                break;
        }
        return type;
    }

    /**
     * 大健康购买
     * @param health_id
     */
    public void pay(String health_id){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, BEFORE_PAY);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", Hawk.get(Define.ACCESS_TOKEN, ""));
        map.put("health_id", health_id);
        httpInfo.setUrl(APIS.URL_BUY_HEALTH);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case BEFORE_PAY:
                view.dismiss();
                BigHealthDetailBuyBean bighealthdetailbuybean = (BigHealthDetailBuyBean) result.getData();
                if (bighealthdetailbuybean!=null) {
                    view.payBefore(bighealthdetailbuybean);
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
