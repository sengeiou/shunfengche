package com.windmillsteward.jukutech.activity.home.commons.pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.RequirePayResultBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/3/7/007
 * 作者：xjh
 */
public class RequirePayPresenter extends BaseNetModelImpl {

    private static final int INIT_PAY = 0;

    public RequirePayView view;

    public RequirePayPresenter(RequirePayView view) {
        this.view = view;
    }

    /**
     */
    public void initPay(String access_token, int require_class_id, String title, String price, String description,
                        int second_area_id, int third_area_id,
                        String longitude, String latitude, List<String> pic_urls, List<String> video_urls,
                        String video_cover,
                        int coupon_receive_id,String order_sn){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_PAY);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("require_class_id", require_class_id);
        map.put("title", title);
        map.put("price", price);
        map.put("description", description);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        map.put("pic_urls", JSON.toJSONString(pic_urls));
        map.put("video_urls", JSON.toJSONString(video_urls));
        map.put("video_cover", video_cover);
        map.put("coupon_receive_id", coupon_receive_id);
        map.put("order_sn", order_sn);
        httpInfo.setUrl(APIS.URL_ADD_REQUIRE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_PAY:
                type = new TypeReference<BaseResultInfo<RequirePayResultBean>>(){}.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case INIT_PAY:
                view.dismiss();
                RequirePayResultBean data = (RequirePayResultBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                } else {
                    view.showTips("获取订单信息失败",0);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case INIT_PAY:
                view.dismiss();
                view.showTips(msg,0);
                break;
        }
    }
}
