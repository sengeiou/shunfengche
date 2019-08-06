package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.TravelDetailView;
import com.windmillsteward.jukutech.activity.mine.activity.EditTravelDetailView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.TravelDetailBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/1/25
 * 作者：xjh
 */

public class EditTravelDetailPresenter extends BaseNetModelImpl {

    private final int INIT_DATA = 0;
    private final int DELETE_TRAVEL = 1;

    private EditTravelDetailView view;

    public EditTravelDetailPresenter(EditTravelDetailView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     * @param travel_id 旅游id
     */
    public void initData(String access_token,int travel_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("travel_id", travel_id);
        httpInfo.setUrl(APIS.URL_TRAVEL_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 删除
     * @param travel_id 旅游id
     */
    public void deleteRequire(String access_token,int travel_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, DELETE_TRAVEL);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("travel_id", travel_id);
        httpInfo.setUrl(APIS.URL_DELETE_TRAVEL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<TravelDetailBean>>(){}.getType();
                break;
            case DELETE_TRAVEL:
                type = new TypeReference<BaseResultInfo<String>>(){}.getType();
                break;
        }

        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                TravelDetailBean data = (TravelDetailBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case DELETE_TRAVEL:
                view.dismiss();
                view.deleteIdeaThinkSuccess();
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,0);
    }


}
