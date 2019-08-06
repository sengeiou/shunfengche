package com.windmillsteward.jukutech.activity.home.fragment.presenter;

import android.text.TextUtils;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.fragment.UnreadMessageCountView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：首页--未读的消息数
 * author:cyq
 * 2018-04-16
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class UnreadMessageCountPresenter extends BaseNetModelImpl {

    private final int UNREAD_MESSAGE_COUNT = 1;
    private UnreadMessageCountView view;

    public UnreadMessageCountPresenter(UnreadMessageCountView view) {
        this.view = view;
    }

    public void getUnreadMessageCount(String access_token) {
        DataLoader dataLoader = new DataLoader(this, UNREAD_MESSAGE_COUNT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("access_token",access_token);
        httpInfo.setUrl(APIS.URL_UNREAD_MESSAGE_COUNT);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case UNREAD_MESSAGE_COUNT:
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
            case UNREAD_MESSAGE_COUNT:
                String data = (String) result.getData();
                if (TextUtils.isEmpty(data)){
                    return;
                }
                try {
                    JSONObject object = new JSONObject(data);
                    String count = object.getString("count");
                    view.getUnreadMessageCountSuccess(count);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case UNREAD_MESSAGE_COUNT:
                view.getUnreadMessageCountFailed(code, msg);
                break;
        }
    }
}
