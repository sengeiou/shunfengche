package com.windmillsteward.jukutech.activity.home.legalexpert.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.legalexpert.activity.PublishLegalExpertActivityView;
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
 * Created by Administrator on 2018/4/10 0010.
 */

public class PublishLegalExpertActivityPresenter extends BaseNetModelImpl {

    private static final int PUBLISH_AREA_LIST = 1;
    private static final int PUBLISH = 2;
    private static final int IS_CHARGE = 4;
    private static final int EDIT = 5;

    private PublishLegalExpertActivityView view;

    public PublishLegalExpertActivityPresenter(PublishLegalExpertActivityView view) {
        this.view = view;
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

    public void isCharge(String access_token,int type,int relate_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, IS_CHARGE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("access_token", access_token);
        map.put("relate_id", relate_id);
        httpInfo.setUrl(APIS.URL_IS_CHARGE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    public void publish(String access_token,int second_area_id,int third_area_id,int legal_expert_type,String contact_person,String contact_mobile_phone,
                        String title,String description) {

        DataLoader dataLoader = new DataLoader(this, PUBLISH);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("legal_expert_type", legal_expert_type);
        map.put("contact_person", contact_person);
        map.put("contact_mobile_phone", contact_mobile_phone);
        map.put("title", title);
        map.put("description", description);
        httpInfo.setUrl(APIS.URL_LEGAL_EXPERT_ADD);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void edit(String access_token,int legal_expert_id,int second_area_id,int third_area_id,int legal_expert_type,String contact_person,String contact_mobile_phone,
                        String title,String description) {

        DataLoader dataLoader = new DataLoader(this, EDIT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("legal_expert_id", legal_expert_id);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("legal_expert_type", legal_expert_type);
        map.put("contact_person", contact_person);
        map.put("contact_mobile_phone", contact_mobile_phone);
        map.put("title", title);
        map.put("description", description);
        httpInfo.setUrl(APIS.URL_LEGAL_EXPERT_EDIT);
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
                ChargeResultBean chargeResultBean = (ChargeResultBean) result.getData();
                if (chargeResultBean!=null) {
                    view.isChargeResult(chargeResultBean);
                }
                break;
            case PUBLISH:
                view.dismiss();
                view.publishSuccess();
                view.showTips("提交成功",0);
                break;
            case EDIT:
                view.dismiss();
                view.publishSuccess();
                view.showTips("修改成功",0);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,0);
    }
}
