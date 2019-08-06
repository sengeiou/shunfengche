package com.windmillsteward.jukutech.activity.home.think.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.family.activity.IntelligentFamilyListView;
import com.windmillsteward.jukutech.activity.home.think.activity.IdeaThinkListView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 * 时间：2018/1/16/016
 * 作者：xjh
 */
public class IdeaThinkListActPresenter extends BaseNetModelImpl {

    private final int IS_AUTHEN=2;

    private IdeaThinkListView view;

    public IdeaThinkListActPresenter(IdeaThinkListView view) {
        this.view = view;
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
            case IS_AUTHEN:
                type = new TypeReference<BaseResultInfo<AuthenResultBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case IS_AUTHEN:
                view.dismiss();
                AuthenResultBean data = (AuthenResultBean) result.getData();
                view.isAuthen(data);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case IS_AUTHEN:
                view.dismiss();
                view.showTips("获取认证信息失败，请重试",0);
                break;
        }
    }
}
