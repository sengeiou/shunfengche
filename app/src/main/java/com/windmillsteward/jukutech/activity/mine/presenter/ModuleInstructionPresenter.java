package com.windmillsteward.jukutech.activity.mine.presenter;

import android.text.TextUtils;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.ModuleInstructionView;
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
 * 个人信息操作类
 */

public class ModuleInstructionPresenter extends BaseNetModelImpl {
    private final int MODULE_INSTRUCTION = 1;
    private ModuleInstructionView view;

    public ModuleInstructionPresenter(ModuleInstructionView view) {
        this.view = view;
    }

    /**
     * 获取网页url
     *
     * @param type 模块类型：【1：人才驿站，2：思想智库，3：智慧家庭，4：房屋租售，5：住宿旅游，6：车辆买卖，7：大健康，8：名优特产，9：资本管理，10：法律专家】
     */
    public void getHtmlUrl(int type) {
        DataLoader dataLoader = new DataLoader(this, MODULE_INSTRUCTION);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("type", type);
        httpInfo.setUrl(APIS.URL_MODULE_INTRODUCTION);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case MODULE_INSTRUCTION:
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
            case MODULE_INSTRUCTION:
                String data = (String) result.getData();
                try {
                    JSONObject object = new JSONObject(data);
                    String html_url = object.getString("html_url");
                    if (TextUtils.isEmpty(html_url)) {
                        view.showTips("操作失败", 1);
                        return;
                    }
                    view.getHtmlUrlSuccess(html_url);
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
            case MODULE_INSTRUCTION:
                view.getHtmlUrlFailed(code, msg);
                break;
        }
    }
}