package com.windmillsteward.jukutech.activity.home.personnel.presenter;

import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.personnel.activity.ApplyLabourServiceView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.LabourLastApplyBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：劳务申请实现类
 * 时间：2018/7/31
 * 作者：cyq
 */

public class ApplyLabourServicePresenter extends BaseNetModelImpl {

    private final int LAST_DATA = 1;
    private final int CRAFF_DATA = 2;
    private final int APPLY_COMMIT = 3;
    private ApplyLabourServiceView view;

    private String[] sex_module = new String[]{"男", "女"};

    public ApplyLabourServicePresenter(ApplyLabourServiceView view) {
        this.view = view;
    }

    /**
     * 初始化数据-只在刚进来时，获取上次申请过的数据
     */
    public void initLastData(String access_token) {

        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, LAST_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_LABOR_LAST_APPLY_DATA);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 提交申请
     */
    public void applyCommit(String access_token, int labor_intermediary_id, String true_name, int sex, String mobile_phone, int job_id, String self_intro) {
        DataLoader dataLoader = new DataLoader(this, APPLY_COMMIT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("labor_intermediary_id", labor_intermediary_id);
        map.put("true_name", true_name);
        map.put("access_token", access_token);
        map.put("sex", sex);
        map.put("mobile_phone", mobile_phone);
        map.put("job_id", job_id);
        map.put("self_intro", self_intro);
        httpInfo.setUrl(APIS.URL_LABOR_APPLY);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载托管模块 0保密1男2女
     */
    public void loadSexModule() {
        List<Map<String, Object>> maps = new ArrayList<>();
        for (int i = 0; i < sex_module.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", i + 1);
            map.put("text", sex_module[i]);
            maps.add(map);
        }
        view.loadSexModuleSuccess(maps);
    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case APPLY_COMMIT:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case CRAFF_DATA:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case LAST_DATA:
                type = new TypeReference<BaseResultInfo<LabourLastApplyBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case LAST_DATA:
                view.dismiss();
                LabourLastApplyBean data = (LabourLastApplyBean) result.getData();
                view.initLastDataSuccess(data);
                break;
            case APPLY_COMMIT:
                view.dismiss();
                view.applyCommitSuccess();
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case LAST_DATA:
                view.showTips(msg, Toast.LENGTH_SHORT);
                break;
            case CRAFF_DATA:
                view.showTips(msg, 1);
                break;
            case APPLY_COMMIT:
                view.showTips(msg, 1);
                break;
        }
    }
}
