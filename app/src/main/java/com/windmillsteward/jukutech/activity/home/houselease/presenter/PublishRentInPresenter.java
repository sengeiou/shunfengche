package com.windmillsteward.jukutech.activity.home.houselease.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.houselease.activity.PublishRentInView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.FileUploadResultBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataCallBack;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/2/6
 * 作者：xjh
 */

public class PublishRentInPresenter extends BaseNetModelImpl {

    private static final int AREA_LIST = 1;
    private static final int PUBLISH = 4;
    private static final int EDIT = 5;
    private static final int IS_CHARGE = 6;

    private PublishRentInView view;

    public PublishRentInPresenter(PublishRentInView view) {
        this.view = view;
    }

    /**
     * 加载发布地区
     */
    public void loadPublishAreaData(int second_area_id) {
        view.showDialog("");
//        URL_TALENT_HOME_INDEX_CLASS
        DataLoader dataLoader = new DataLoader(this, AREA_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("second_area_id", second_area_id);
        httpInfo.setUrl(APIS.URL_THIRD_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 发布
     */
    public void publish(String access_token, int require_type, int house_second_id, int house_third_id, int house_fourth_id,
                        String house_rooms, String house_parlor, String floor, String total_price, String title, String description,
                        String contact_person, String contact_mobile_phone, int second_area_id, int third_area_id,
                        String floor_area, int orientation, int decoration, List<String> pic_urls, String community_name,
                        String property_cert_pic_url, int house_type, int property_right, String developers_name, int rent_deposit_type,
                        List<Integer> house_config_ids, int school_degree_type){
        DataLoader dataLoader = new DataLoader(this, PUBLISH);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("require_type", require_type);
        map.put("house_second_id", house_second_id);
        map.put("house_third_id", house_third_id);
        map.put("house_fourth_id", house_fourth_id);
        map.put("house_rooms", house_rooms);
        map.put("house_parlor", house_parlor);
        map.put("total_price", total_price);
        map.put("title", title);
        map.put("floor", floor);
        map.put("description", description);
        map.put("contact_person", contact_person);
        map.put("contact_mobile_phone", contact_mobile_phone);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("floor_area", floor_area);
        map.put("orientation", orientation);
        map.put("decoration", decoration);
        map.put("community_name", community_name);
        map.put("property_cert_pic_url", property_cert_pic_url);
        map.put("house_type", house_type);
        map.put("property_right", property_right);
        map.put("developers_name", developers_name);
        map.put("rent_deposit_type", rent_deposit_type);
        map.put("house_config_ids", JSON.toJSONString(house_config_ids));
        map.put("school_degree_type", school_degree_type);
        map.put("pic_urls", JSON.toJSONString(pic_urls));

        httpInfo.setUrl(APIS.URL_HOUSE_PUBLISH);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 发布
     */
    public void edit(int house_id,String access_token, int require_type, int house_second_id, int house_third_id, int house_fourth_id,
                        String house_rooms, String house_parlor, String floor, String total_price, String title, String description,
                        String contact_person, String contact_mobile_phone, int second_area_id, int third_area_id,
                        String floor_area, int orientation, int decoration, List<String> pic_urls, String community_name,
                        String property_cert_pic_url, int house_type, int property_right, String developers_name, int rent_deposit_type,
                        List<Integer> house_config_ids, int school_degree_type){
        DataLoader dataLoader = new DataLoader(this, EDIT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("house_id", house_id);
        map.put("access_token", access_token);
        map.put("require_type", require_type);
        map.put("house_second_id", house_second_id);
        map.put("house_third_id", house_third_id);
        map.put("house_fourth_id", house_fourth_id);
        map.put("house_rooms", house_rooms);
        map.put("house_parlor", house_parlor);
        map.put("total_price", total_price);
        map.put("title", title);
        map.put("floor", floor);
        map.put("description", description);
        map.put("contact_person", contact_person);
        map.put("contact_mobile_phone", contact_mobile_phone);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("floor_area", floor_area);
        map.put("orientation", orientation);
        map.put("decoration", decoration);
        map.put("community_name", community_name);
        map.put("property_cert_pic_url", property_cert_pic_url);
        map.put("house_type", house_type);
        map.put("property_right", property_right);
        map.put("developers_name", developers_name);
        map.put("rent_deposit_type", rent_deposit_type);
        map.put("house_config_ids", JSON.toJSONString(house_config_ids));
        map.put("school_degree_type", school_degree_type);
        map.put("pic_urls", JSON.toJSONString(pic_urls));

        httpInfo.setUrl(APIS.URL_EDIT_HOUSE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 判断发布是否收费
     * @param access_token
     */
    public void isCharge(String access_token,int relate_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, IS_CHARGE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type", 52);
        map.put("access_token", access_token);
        map.put("relate_id", relate_id);
        httpInfo.setUrl(APIS.URL_IS_CHARGE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>(){}.getType();
                break;
            case EDIT:
            case PUBLISH:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case IS_CHARGE:
                type = new TypeReference<BaseResultInfo<ChargeResultBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case AREA_LIST:
                view.dismiss();
                List<ThirdAreaBean> data = (List<ThirdAreaBean>) result.getData();
                if(data!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    for (ThirdAreaBean bean : data) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getThird_area_id());
                        map.put("text",bean.getThird_area_name());
                        maps.add(map);
                    }
                    view.loadPublishAreaDataSuccess(maps);
                }
                break;
            case PUBLISH:
                String resultData = (String) result.getData();
                view.publishSuccess(resultData);
                view.showTips("发布成功",0);
                break;
            case EDIT:
                view.dismiss();
                view.publishSuccess("");
                view.showTips("修改成功",0);
                break;
            case IS_CHARGE:
                view.dismiss();
                ChargeResultBean chargeResultBean = (ChargeResultBean) result.getData();
                if (chargeResultBean!=null) {
                    view.isChargeResult(chargeResultBean);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case AREA_LIST:
                view.dismiss();
                break;
            case PUBLISH:
                view.dismiss();
                view.showTips("发布失败",0);
                break;
            case EDIT:
                view.dismiss();
                view.showTips("修改失败",0);
                break;
            case IS_CHARGE:
                view.dismiss();
                view.showTips(msg,0);
                break;
        }
    }
}
