package com.windmillsteward.jukutech.activity.home.carservice.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.carservice.activity.PublishCarOwnerActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
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
 * 描述：
 * 时间：2018/4/3/003
 * 作者：xjh
 */
public class PublishCarOwnerActivityPresenter extends BaseNetModelImpl {

    private static final int PUBLISH_AREA_LIST = 2;
    private static final int PUBLISH = 3;
    private static final int EDIT = 4;
    private static final int IS_CHARGE = 5;

    private PublishCarOwnerActivityView view;

    public PublishCarOwnerActivityPresenter(PublishCarOwnerActivityView view) {
        this.view = view;
    }

    public void loadUnoccupiedNumData() {
        List<Map<String,Object>> maps = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",i+1);
            map.put("text",(i+1)+"人");
            maps.add(map);
        }
        view.loadUnoccupiedNumDataSuccess(maps);
    }

    public void loadVehicleModuleName() {
        String[] nums = new String[]{"轿车","suv","mpv","跑车","大巴车"};
        List<Map<String,Object>> maps = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",i+1);
            map.put("text",nums[i]);
            maps.add(map);
        }
        view.loadVehicleModuleNameSuccess(maps);
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
        map.put("type", 102);
        map.put("relate_id", relate_id);
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_IS_CHARGE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 发布
     */
    public void publish(String access_token,String departure_longitude,String departure_latitude,String departure_place,String departure_place_title,String destination_longitude,
                        String destination_latitude,String destination_place,String destination_place_title,int go_off,int unoccupied_num,String price,String vehicle_module_name,
                        String pass_place_one,String pass_place_two,String remark,String contact_person,String contact_tel,int second_area_id,int third_area_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, PUBLISH);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("departure_longitude", departure_longitude);
        map.put("departure_latitude", departure_latitude);
        map.put("departure_place", departure_place);
        map.put("departure_place_title", departure_place_title);
        map.put("destination_longitude", destination_longitude);
        map.put("destination_latitude", destination_latitude);
        map.put("destination_place", destination_place);
        map.put("destination_place_title", destination_place_title);
        map.put("go_off", go_off);
        map.put("unoccupied_num", unoccupied_num);
        map.put("price", price);
        map.put("vehicle_module_name", vehicle_module_name);
        map.put("pass_place_one", pass_place_one);
        map.put("pass_place_two", pass_place_two);
        map.put("remark", remark);
        map.put("contact_person", contact_person);
        map.put("contact_tel", contact_tel);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        httpInfo.setUrl(APIS.URL_CAR_OWNER_ISSUE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void edit(String access_token,int car_rent_id,String departure_longitude,String departure_latitude,String departure_place,String departure_place_title,String destination_longitude,
                        String destination_latitude,String destination_place,String destination_place_title,int go_off,int unoccupied_num,String price,String vehicle_module_name,
                        String pass_place_one,String pass_place_two,String remark,String contact_person,String contact_tel,int second_area_id,int third_area_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, EDIT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("car_rent_id", car_rent_id);
        map.put("departure_longitude", departure_longitude);
        map.put("departure_latitude", departure_latitude);
        map.put("departure_place", departure_place);
        map.put("departure_place_title", departure_place_title);
        map.put("destination_longitude", destination_longitude);
        map.put("destination_latitude", destination_latitude);
        map.put("destination_place", destination_place);
        map.put("destination_place_title", destination_place_title);
        map.put("go_off", go_off);
        map.put("unoccupied_num", unoccupied_num);
        map.put("price", price);
        map.put("vehicle_module_name", vehicle_module_name);
        map.put("pass_place_one", pass_place_one);
        map.put("pass_place_two", pass_place_two);
        map.put("remark", remark);
        map.put("contact_person", contact_person);
        map.put("contact_tel", contact_tel);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        httpInfo.setUrl(APIS.URL_EDIT_CAR_OWNER_ISSUE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case PUBLISH_AREA_LIST:
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
