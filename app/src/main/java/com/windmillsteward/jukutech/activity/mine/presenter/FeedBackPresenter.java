package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.FeedBackView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 留言反馈操作类
 */

public class FeedBackPresenter extends BaseNetModelImpl {
    private final int FEEDBACK = 1;
    private FeedBackView view;

    public FeedBackPresenter(FeedBackView view) {
        this.view = view;
    }

    /**
     * 留言反馈
     */
    public void feedback(String access_token,String content,String contact_tel) {
        DataLoader dataLoader = new DataLoader(this, FEEDBACK);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("access_token", access_token);
        map.put("content", content);
        map.put("contact_tel", contact_tel);
        httpInfo.setUrl(APIS.URL_FEEDBACK);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case FEEDBACK:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case FEEDBACK:
               view.Success();
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case FEEDBACK:
                view.Failed(code, msg);
                break;
        }
    }
}