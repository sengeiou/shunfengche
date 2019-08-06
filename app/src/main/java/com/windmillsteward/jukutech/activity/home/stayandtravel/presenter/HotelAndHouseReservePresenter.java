package com.windmillsteward.jukutech.activity.home.stayandtravel.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.HotelAndHouseReserveView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.HotelPayBeforeBean;
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
 * 时间：2018/3/6/006
 * 作者：xjh
 */
public class HotelAndHouseReservePresenter extends BaseNetModelImpl {

    private static final int PAY_BEFORE = 1;
    private static final int PAY = 2;

    private HotelAndHouseReserveView view;

    public HotelAndHouseReservePresenter(HotelAndHouseReserveView view) {
        this.view = view;
    }

    public void payBefore(String access_token, int hotel_id, String planed_start_time, String planed_end_time, int night_num, int room_type_id,
                          int room_num, List<String> contact_person_list,String contact_tel,int coupon_receive_id,String total_order_sn) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, PAY_BEFORE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("hotel_id", hotel_id);
        map.put("planed_start_time", planed_start_time);
        map.put("planed_end_time", planed_end_time);
        map.put("night_num", night_num);
        map.put("room_type_id", room_type_id);
        map.put("room_num", room_num);
        map.put("contact_person_list", JSON.toJSONString(contact_person_list));
        map.put("contact_tel", contact_tel);
        map.put("coupon_receive_id", coupon_receive_id);
        map.put("total_order_sn", total_order_sn);
        httpInfo.setUrl(APIS.URL_BOOK_HOTEL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void pay(String access_token, int hotel_id, String planed_start_time, String planed_end_time, int night_num, int room_type_id,
                          int room_num, List<String> contact_person_list,String contact_tel,int coupon_receive_id,String total_order_sn) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, PAY);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("hotel_id", hotel_id);
        map.put("planed_start_time", planed_start_time);
        map.put("planed_end_time", planed_end_time);
        map.put("night_num", night_num);
        map.put("room_type_id", room_type_id);
        map.put("room_num", room_num);
        map.put("contact_person_list", JSON.toJSONString(contact_person_list));
        map.put("contact_tel", contact_tel);
        map.put("coupon_receive_id", coupon_receive_id);
        map.put("total_order_sn", total_order_sn);
        httpInfo.setUrl(APIS.URL_BOOK_HOTEL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadRoomsData() {
        List<Map<String,Object>> maps = new ArrayList<>();
        String[] data = new String[]{"1","2","3","4","5"};
        for (int i = 0; i < data.length; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",i+1);
            map.put("text",data[i]);
            maps.add(map);
        }

        view.loadRoomNumSuccess(maps);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case PAY:
            case PAY_BEFORE:
                type = new TypeReference<BaseResultInfo<HotelPayBeforeBean>>(){}.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case PAY_BEFORE:
                view.dismiss();
                HotelPayBeforeBean data = (HotelPayBeforeBean) result.getData();
                if (data!=null) {
                    view.payBeforeResult(data);
                }
                break;
            case PAY:
                view.dismiss();
                HotelPayBeforeBean payData = (HotelPayBeforeBean) result.getData();
                if (payData!=null) {
                    view.pay(payData);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case PAY_BEFORE:
                view.dismiss();
                view.showTips(msg,0);
                break;
            case PAY:
                view.dismiss();
                view.showTips(msg,0);
                break;
        }
    }
}
