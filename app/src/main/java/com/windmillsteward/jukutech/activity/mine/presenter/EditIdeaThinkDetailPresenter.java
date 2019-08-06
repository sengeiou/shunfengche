package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.think.activity.IdeaThinkDetailView;
import com.windmillsteward.jukutech.activity.mine.activity.EditIdeaThinkDetailView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.IdeaThinkDetailBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/2/5
 * 作者：xjh
 */

public class EditIdeaThinkDetailPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;
    private static final int DELETE = 2;

    private EditIdeaThinkDetailView view;

    public EditIdeaThinkDetailPresenter(EditIdeaThinkDetailView view) {
        this.view = view;
    }

    public void initData(String access_token,int require_id){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("require_id", require_id);
        httpInfo.setUrl(APIS.URL_IDEA_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void deleteRequire(String accessToken, int require_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, DELETE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", accessToken);
        map.put("require_id", require_id);
        httpInfo.setUrl(APIS.URL_DELETE_REQUIRE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<IdeaThinkDetailBean>>(){}.getType();
                break;
            case DELETE:
                type = new TypeReference<BaseResultInfo<String>>(){}.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                IdeaThinkDetailBean data = (IdeaThinkDetailBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case DELETE:
                view.dismiss();
                view.deleteIdeaThinkSuccess();
                view.showTips("删除成功",0);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action){
            case INIT_DATA:
                view.dismiss();
                break;
            case DELETE:
                view.dismiss();
                view.showTips("删除失败",0);
                break;
        }
    }
}
