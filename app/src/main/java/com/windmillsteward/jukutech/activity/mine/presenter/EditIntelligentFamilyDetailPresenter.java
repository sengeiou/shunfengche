package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.EditIntelligentFamilyDetailView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.IntelligentFamilyDetailBean;
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
public class EditIntelligentFamilyDetailPresenter extends BaseNetModelImpl {

    private final int INIT_DATA=1;
    private final int DELETE=2;
    private final int CANCEL_REQUIRE=3;
    private final int SURE_REQUIRE=4;
    private final int ONFINISH_REQUIRE=5;
    private final int FINISH_REQUIRE=6;

    private EditIntelligentFamilyDetailView view;

    public EditIntelligentFamilyDetailPresenter(EditIntelligentFamilyDetailView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     * @param require_id 需求id
     * @param longitude 坐标经度
     * @param latitude 坐标维度
     */
    public void initData(String access_token,int require_id, String longitude, String latitude){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("require_id", require_id);
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        httpInfo.setUrl(APIS.URL_REQUIRE_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     * @param require_id 需求id
     * @param longitude 坐标经度
     * @param latitude 坐标维度
     */
    public void refreshData(String access_token,int require_id, String longitude, String latitude) {
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("require_id", require_id);
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        httpInfo.setUrl(APIS.URL_REQUIRE_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 删除
     * @param require_id
     * @param access_token
     */
    public void deleteRequire(int require_id,String access_token) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, DELETE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("require_id", require_id);
        httpInfo.setUrl(APIS.URL_DELETE_REQUIRE_1);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 取消订单
     * @param require_id
     * @param access_token
     */
    public void cancelRequire(int require_id,String access_token) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, CANCEL_REQUIRE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("require_id", require_id);
        httpInfo.setUrl(APIS.URL_CANCEL_REQUIRE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 确认订单
     * @param access_token
     * @param require_id
     */
    public void sureRequire(String access_token,int require_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, SURE_REQUIRE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("require_id", require_id);
        httpInfo.setUrl(APIS.URL_SURE_REQUIRE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 未完成订单
     * @param require_id
     * @param access_token
     */
    public void onFinishRequire(int require_id,String access_token) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, ONFINISH_REQUIRE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("require_id", require_id);
        httpInfo.setUrl(APIS.URL_ONFINISH_REQUIRE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 完成订单
     * @param require_id
     * @param access_token
     */
    public void finishRequire(int require_id,String access_token) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, FINISH_REQUIRE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("require_id", require_id);
        httpInfo.setUrl(APIS.URL_FINISH_REQUIRE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<IntelligentFamilyDetailBean>>() {
                }.getType();
                break;
            case DELETE:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case CANCEL_REQUIRE:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case SURE_REQUIRE:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case ONFINISH_REQUIRE:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case FINISH_REQUIRE:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                view.initData((IntelligentFamilyDetailBean) result.getData());
                break;
            case DELETE:
                view.dismiss();
                view.deleteRequireSuccess();
                view.showTips("删除成功",0);
                break;
            case CANCEL_REQUIRE:
                view.dismiss();
                view.cancelRequireSuccess();
                break;
            case SURE_REQUIRE:
                view.dismiss();
                view.sureRequireSuccess();
                break;
            case ONFINISH_REQUIRE:
                view.dismiss();
                view.onFinishRequireSuccess();
                break;
            case FINISH_REQUIRE:
                view.dismiss();
                view.finishRequireSuccess();
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                break;
            case DELETE:
                view.dismiss();
                view.showTips("删除失败",0);
                break;
            case CANCEL_REQUIRE:
                view.dismiss();
                view.showTips(msg,0);
                break;
            case SURE_REQUIRE:
                view.dismiss();
                view.showTips(msg,0);
                break;
            case ONFINISH_REQUIRE:
                view.dismiss();
                view.showTips(msg,0);
                break;
            case FINISH_REQUIRE:
                view.dismiss();
                view.showTips(msg,0);
                break;
        }
    }
}
