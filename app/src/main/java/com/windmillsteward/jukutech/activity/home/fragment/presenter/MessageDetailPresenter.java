package com.windmillsteward.jukutech.activity.home.fragment.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.fragment.activity.MessageDetailView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.MessageDetailBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：消息详情实现类
 * author:cyq
 * 2018-04-16
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MessageDetailPresenter extends BaseNetModelImpl {

    private final int DETAIL = 1;
    private MessageDetailView view;

    public MessageDetailPresenter(MessageDetailView view) {
        this.view = view;
    }

    public void getMessageDetail(String access_token, String msg_id) {
        DataLoader dataLoader = new DataLoader(this, DETAIL);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("access_token", access_token);
        map.put("msg_id", msg_id);
        httpInfo.setUrl(APIS.URL_MESSAGE_LIST_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case DETAIL:
                type = new TypeReference<BaseResultInfo<MessageDetailBean>>() {
                }.getType();
                break;

        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case DETAIL:
                if (view == null) {
                    return;
                }
                MessageDetailBean bean = (MessageDetailBean) result.getData();
                view.getMessageDetailSuccess(bean);
                break;

        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case DETAIL:
                if (view == null) {
                    return;
                }
                view.getMessageDetailFailed(code, msg);
                break;
        }
    }
}
