package com.windmillsteward.jukutech.activity.home.carservice.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.carservice.activity.PublishBuyCarActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.FileUploadResultBean;
import com.windmillsteward.jukutech.bean.MoreCarListBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/3.
 */

public class PublishBuyCarActivityPresenter extends BaseNetModelImpl {

    private static final int DEAL_AREA_LIST = 2;
    private static final int PUBLISH_AREA_LIST = 3;
    private static final int PUBLISH = 4;
    private static final int EDIT = 6;
    private static final int IS_CHARGE = 5;

    private PublishBuyCarActivityView view;

    public PublishBuyCarActivityPresenter(PublishBuyCarActivityView view) {
        this.view = view;
    }

    public void loadDealAreaData(int second_area_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, DEAL_AREA_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("second_area_id", second_area_id);
        httpInfo.setUrl(APIS.URL_THIRD_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadPublishAreaData(int second_area_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, PUBLISH_AREA_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("second_area_id", second_area_id);
        httpInfo.setUrl(APIS.URL_THIRD_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 判断是否收费
     */
    public void isCharge(String access_token,int relate_id){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, IS_CHARGE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type", 101);
        map.put("relate_id", relate_id);
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_IS_CHARGE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void publish(String access_token,String title,String price,int deal_second_area_id,int deal_third_area_id,
                        String details,String contact_person,String contact_tel,int second_area_id,int third_area_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, PUBLISH);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("title", title);
        map.put("price", price);
        map.put("deal_second_area_id", deal_second_area_id);
        map.put("deal_third_area_id", deal_third_area_id);
        map.put("details", details);
        map.put("contact_person", contact_person);
        map.put("contact_tel", contact_tel);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        httpInfo.setUrl(APIS.URL_BUY_CAR);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void edit(String access_token,int buy_car_id,String title,String price,int deal_second_area_id,int deal_third_area_id,
                        String details,String contact_person,String contact_tel,int second_area_id,int third_area_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, EDIT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("buy_car_id", buy_car_id);
        map.put("title", title);
        map.put("price", price);
        map.put("deal_second_area_id", deal_second_area_id);
        map.put("deal_third_area_id", deal_third_area_id);
        map.put("details", details);
        map.put("contact_person", contact_person);
        map.put("contact_tel", contact_tel);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        httpInfo.setUrl(APIS.URL_EDIT_BUY_CAR);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case PUBLISH_AREA_LIST:
            case DEAL_AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>(){}.getType();
                break;
            case IS_CHARGE:
                type = new TypeReference<BaseResultInfo<ChargeResultBean>>() {
                }.getType();
                break;
            case EDIT:
            case PUBLISH:
                type = new TypeReference<BaseResultInfo<String>>(){}.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case DEAL_AREA_LIST:
                view.dismiss();
                List<ThirdAreaBean> dealAreaData = (List<ThirdAreaBean>) result.getData();
                if(dealAreaData!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    for (ThirdAreaBean bean : dealAreaData) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getThird_area_id());
                        map.put("text",bean.getThird_area_name());
                        maps.add(map);
                    }
                    view.loadDealAreaDataSuccess(maps);
                }
                break;
            case PUBLISH_AREA_LIST:
                view.dismiss();
                List<ThirdAreaBean> publishAreaData = (List<ThirdAreaBean>) result.getData();
                if(publishAreaData!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    for (ThirdAreaBean bean : publishAreaData) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getThird_area_id());
                        map.put("text",bean.getThird_area_name());
                        maps.add(map);
                    }
                    view.loadPublishAreaDataSuccess(maps);
                }
                break;
            case IS_CHARGE:
                view.dismiss();
                view.isCharge(((ChargeResultBean) result.getData()));
                break;
            case PUBLISH:
                view.dismiss();
                view.showTips("提交成功",0);
                view.publishSuccess();
                break;
            case EDIT:
                view.dismiss();
                view.showTips("修改成功",0);
                view.publishSuccess();
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,0);
    }
}
