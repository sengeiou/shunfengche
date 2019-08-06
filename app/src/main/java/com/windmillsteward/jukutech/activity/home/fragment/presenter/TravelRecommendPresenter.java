package com.windmillsteward.jukutech.activity.home.fragment.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.fragment.TravelRecommendView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.TravelRecommendBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：旅游推荐
 * author:cyq
 * 2018-02-06
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class TravelRecommendPresenter extends BaseNetModelImpl {

    private final int TRAVEL_RECOMMEND = 1;
    private TravelRecommendView view;

    public TravelRecommendPresenter(TravelRecommendView view) {
        this.view = view;
    }

    public void getTravelRecommendList() {
        DataLoader dataLoader = new DataLoader(this, TRAVEL_RECOMMEND);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        httpInfo.setUrl(APIS.URL_TRAVEL_RECOMMEND_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case TRAVEL_RECOMMEND:
                type = new TypeReference<BaseResultInfo<TravelRecommendBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case TRAVEL_RECOMMEND:
                TravelRecommendBean bean = (TravelRecommendBean) result.getData();
                view.getTravelRecommendDataSuccess(bean);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case TRAVEL_RECOMMEND:
                view.getTravelRecommendDataFailed(code, msg);
                break;
        }
    }
}
