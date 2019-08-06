package com.windmillsteward.jukutech.activity;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.CheckVersionUpdateBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：检查更新
 * author:cyq
 * 2018-04-11
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class CheckVersionUpdatePresenter extends BaseNetModelImpl {

    private final int CHECK_UPDATE = 1;
    private CheckVersionUpdateView view;

    public CheckVersionUpdatePresenter(CheckVersionUpdateView view) {
        this.view = view;
    }

    public void checkUpdate() {
        DataLoader dataLoader = new DataLoader(this, CHECK_UPDATE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        httpInfo.setUrl(APIS.URL_CHECK_UPDATE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case CHECK_UPDATE:
                type = new TypeReference<BaseResultInfo<CheckVersionUpdateBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case CHECK_UPDATE:
                CheckVersionUpdateBean bean = (CheckVersionUpdateBean) result.getData();
                view.getCheckVersionDataSuccess(bean);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case CHECK_UPDATE:
                view.getCheckVersionDataFailed(code, msg);
                break;
        }
    }
}
