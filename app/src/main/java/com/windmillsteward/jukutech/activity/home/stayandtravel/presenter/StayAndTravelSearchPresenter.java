package com.windmillsteward.jukutech.activity.home.stayandtravel.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.StayAndTravelSearchView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.StayAndTravelSearchResultBean;
import com.windmillsteward.jukutech.bean.TravelDetailBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/3/7/007
 * 作者：xjh
 */
public class StayAndTravelSearchPresenter extends BaseNetModelImpl {

    private final int SEARCH = 0;

    private StayAndTravelSearchView view;

    public StayAndTravelSearchPresenter(StayAndTravelSearchView view) {
        this.view = view;
    }

    public void search(String keyword) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, SEARCH);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        httpInfo.setUrl(APIS.URL_SEARCH_HOTEL_AND_TRAVEL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case SEARCH:
                type = new TypeReference<BaseResultInfo<StayAndTravelSearchResultBean>>(){}.getType();
                break;
        }

        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case SEARCH:
                view.dismiss();
                StayAndTravelSearchResultBean data = (StayAndTravelSearchResultBean) result.getData();
                if (data!=null) {
                    view.searchResult(data);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case SEARCH:
                view.dismiss();
                view.showTips(msg,0);
                break;
        }
    }
}
