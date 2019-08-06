package com.windmillsteward.jukutech.activity.home.legalexpert.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.legalexpert.activity.LegalExpertActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class LegalExpertActivityPresenter extends BaseNetModelImpl {

    private static final int TOP_BANNER = 1;
    private static final int IS_AUTHEN=2;

    private LegalExpertActivityView view;

    public LegalExpertActivityPresenter(LegalExpertActivityView view) {
        this.view = view;
    }

    public void getBannerTopList(int banner_position) {
        DataLoader dataLoader = new DataLoader(this, TOP_BANNER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("banner_position", banner_position);
        httpInfo.setUrl(APIS.URL_BANNER_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void getAuthenState(String access_token){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, IS_AUTHEN);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_IS_AUTHEN);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case TOP_BANNER:
                type = new TypeReference<BaseResultInfo<List<SliderPictureInfo>>>() {
                }.getType();
                break;
            case IS_AUTHEN:
                type = new TypeReference<BaseResultInfo<AuthenResultBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case TOP_BANNER:
                List<SliderPictureInfo> topList = (List<SliderPictureInfo>) result.getData();
                if (topList!=null)
                view.getTopBannerListSuccess(topList);
                break;
            case IS_AUTHEN:
                AuthenResultBean data = (AuthenResultBean) result.getData();
                if (data!=null) {
                    view.isAuthen(data);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,0);
    }
}
