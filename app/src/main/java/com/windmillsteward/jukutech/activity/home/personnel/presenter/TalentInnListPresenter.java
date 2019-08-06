package com.windmillsteward.jukutech.activity.home.personnel.presenter;

import android.content.Context;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.personnel.activity.TalentInnListView;
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
 * 时间：2018/1/8
 * 作者：xjh
 */

public class TalentInnListPresenter extends BaseNetModelImpl {


    private final int IS_AUTHEN = 1;

    private Context context;
    private TalentInnListView view;

    public TalentInnListPresenter(Context context, TalentInnListView view) {
        this.context = context;
        this.view = view;
    }

    /**
     * 判断是否认证
     */
    public void isAuthen(String access_token,int type) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, IS_AUTHEN);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("type",type);
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
