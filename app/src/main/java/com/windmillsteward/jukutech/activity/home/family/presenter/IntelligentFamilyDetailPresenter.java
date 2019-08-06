package com.windmillsteward.jukutech.activity.home.family.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.family.activity.IntelligentFamilyDetailView;
import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
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
public class IntelligentFamilyDetailPresenter extends BaseNetModelImpl {

    private final int INIT_DATA = 1;
    private final int COLLECT_REQUIRE = 2;
    private final int CANCEL_COLLECT_REQUIRE = 3;
    private final int GET_ORDER = 4;

    private IntelligentFamilyDetailView view;

    public IntelligentFamilyDetailPresenter(IntelligentFamilyDetailView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     *
     * @param require_id 需求id
     * @param longitude  坐标经度
     * @param latitude   坐标维度
     */
    public void initData(String access_token, int require_id, String longitude, String latitude) {
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
     *
     * @param require_id 需求id
     * @param longitude  坐标经度
     * @param latitude   坐标维度
     */
    public void refreshData(String access_token, int require_id, String longitude, String latitude) {
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
     * 收藏该需求
     *
     * @param require_id 需求id
     */
    public void collectRequire(String access_token, int require_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, COLLECT_REQUIRE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("require_id", require_id);
        httpInfo.setUrl(APIS.URL_COLLECT_REQUIRE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 取消收藏
     *
     * @param require_id 该需求id
     */
    public void cancelCollectRequire(String access_token, int require_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, CANCEL_COLLECT_REQUIRE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("require_id", require_id);
        httpInfo.setUrl(APIS.URL_CANCEL_COLLECT_REQUIRE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 抢单
     */
    public void getOrder(String access_token, int require_id, String area_name, String longitude, String latitude) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, GET_ORDER);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("require_id", require_id);
        map.put("area_name", area_name);
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        httpInfo.setUrl(APIS.URL_ROB_REQUIRE);
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
            case COLLECT_REQUIRE:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case CANCEL_COLLECT_REQUIRE:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case GET_ORDER:
                type = new TypeReference<BaseResultInfo<PublicResultModel>>() {
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
            case COLLECT_REQUIRE:
                view.dismiss();
                if (code == 0) {
                    view.collectionRequireSuccess();
                    view.showTips("收藏成功", 0);
                } else {
                    view.showTips("收藏失败", 0);
                }
                break;
            case CANCEL_COLLECT_REQUIRE:
                view.dismiss();
                if (code == 0) {
                    view.cancelCollectionRequireSuccess();
                    view.showTips("取消收藏成功", 0);
                } else {
                    view.showTips("取消收藏失败", 0);
                }
                break;
            case GET_ORDER:
                view.dismiss();
                String msg = result.getMsg();
                PublicResultModel resultModel = (PublicResultModel) result.getData();
                if (resultModel != null) {
                    view.getOrderSuccess(resultModel, msg);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                break;
            case COLLECT_REQUIRE:
                view.dismiss();
                view.showTips("收藏失败", 0);
                break;
            case CANCEL_COLLECT_REQUIRE:
                view.dismiss();
                view.showTips("取消收藏失败", 0);
                break;
            case GET_ORDER:
                view.dismiss();
                view.showTips(msg, 0);
                break;
        }
    }
}
