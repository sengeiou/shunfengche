package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.houselease.activity.HouseDetailView;
import com.windmillsteward.jukutech.activity.mine.activity.EditHouseDetailView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.HouseDetailBeam;
import com.windmillsteward.jukutech.bean.HouseMoreBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/2/6
 * 作者：xjh
 */

public class EditHouseDetailPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;
    private static final int DELETE = 2;
    private static final int RENT_TYPE_F = 9;

    private EditHouseDetailView view;

    public EditHouseDetailPresenter(EditHouseDetailView view) {
        this.view = view;
    }

    public void initData(int house_id,String access_token) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("house_id", house_id);
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_HOUSE_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载租房类型
     * type =1 租房 2 买房
     */
    public void loadRentTypeDataF(int type){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, RENT_TYPE_F);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        httpInfo.setUrl(APIS.URL_HOUS_MORE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void delete(String accessToken, int detail_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, DELETE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("house_id", detail_id);
        map.put("access_token", accessToken);
        httpInfo.setUrl(APIS.URL_DELETE_HOUSE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<HouseDetailBeam>>(){}.getType();
                break;
            case DELETE:
                type = new TypeReference<BaseResultInfo<String>>(){}.getType();
                break;
            case RENT_TYPE_F:
                type = new TypeReference<BaseResultInfo<HouseMoreBean>>(){}.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                HouseDetailBeam data = (HouseDetailBeam) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case DELETE:
                view.dismiss();
                view.deleteSuccess();
                view.showTips("删除成功",0);
                break;
            case RENT_TYPE_F:
                view.dismiss();
                HouseMoreBean moreBeanF = (HouseMoreBean) result.getData();
                view.loadRentTypeDataSuccessF(moreBeanF);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,1);
    }


}
