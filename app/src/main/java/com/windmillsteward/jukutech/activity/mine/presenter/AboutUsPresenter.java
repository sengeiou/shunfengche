package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.AboutUsView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.AboutUsBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 关于我们操作类
 */

public class AboutUsPresenter extends BaseNetModelImpl {
    private final int ABOUT_US = 1;
    private AboutUsView view;

    public AboutUsPresenter(AboutUsView view) {
        this.view = view;
    }

    /**
     * 获取关于我们信息
     */
    public void getAboutUsData() {
        DataLoader dataLoader = new DataLoader(this, ABOUT_US);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        httpInfo.setUrl(APIS.URL_ABOUT_US);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }




    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case ABOUT_US:
                type = new TypeReference<BaseResultInfo<List<AboutUsBean>>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case ABOUT_US:
                List<AboutUsBean> data = (List<AboutUsBean>) result.getData();
                view.getAboutUsDataSuccess(data);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case ABOUT_US:
                view.getAboutUsDataFailed(code, msg);
                break;
        }
    }
}