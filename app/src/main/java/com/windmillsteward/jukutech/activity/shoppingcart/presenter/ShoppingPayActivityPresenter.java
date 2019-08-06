package com.windmillsteward.jukutech.activity.shoppingcart.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.shoppingcart.activity.ShoppingPayActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.BeforeAddOrderRequest;
import com.windmillsteward.jukutech.bean.ShoppingPayBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/3/4/004
 * 作者：xjh
 */
public class ShoppingPayActivityPresenter extends BaseNetModelImpl {

    private static final int BEFORE_PAY = 0;
    private static final int AFTER_PAY = 1;

    public ShoppingPayActivityView view;

    public ShoppingPayActivityPresenter(ShoppingPayActivityView view) {
        this.view = view;
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case BEFORE_PAY:
                type = new TypeReference<BaseResultInfo<ShoppingPayBean>>(){}.getType();
                break;
            case AFTER_PAY:
                type = new TypeReference<BaseResultInfo<String>>(){}.getType();
                break;
        }
        return type;
    }

    /**
     *
     * @param address_id 地址ID
     */
    public void pay(String access_token,int cart_id,int address_id,List<BeforeAddOrderRequest.CommodityListBean> list){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, BEFORE_PAY);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("cart_id", cart_id);
        map.put("address_id", address_id);
        map.put("commodity_list", JSON.toJSONString(list));
        httpInfo.setUrl(APIS.URL_ADD_ORDER);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 使用优惠券支付
     * @param access_token token
     * @param order_sn 订单编号
     * @param coupon_id 优惠券ID
     */
    public void userCouponPay(String access_token,String order_sn,int coupon_id){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, BEFORE_PAY);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("order_sn", order_sn);
        map.put("coupon_id", coupon_id);
        httpInfo.setUrl(APIS.URL_USE_COUPON_ORDER);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case BEFORE_PAY:
                view.dismiss();
                ShoppingPayBean payBeforeResultBean = (ShoppingPayBean) result.getData();
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
