package com.windmillsteward.jukutech.activity.home.personnel.presenter;

import android.content.Context;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PositionDetailView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.MyPublishBean;
import com.windmillsteward.jukutech.bean.PositionDetailBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/1/15
 * 作者：xjh
 */

public class PositionDetailPresenter extends BaseNetModelImpl {

    private final int INIT_DATA = 1;
    private final int APPLY = 2;
    private final int COLLECTION=3;
    private final int CANCEL_COLLECTION = 5;
    private final int RESUME_LIST=4;
    private final int IS_CHARGE=6;

    private PositionDetailView view;

    public PositionDetailPresenter(PositionDetailView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     * @param job_id 工作id
     */
    public void initData(String access_token,int job_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token",access_token);
        map.put("job_id",job_id);
        httpInfo.setUrl(APIS.URL_JOB_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 收藏工作
     * @param job_id 工作id
     */
    public void collection(String access_token,int job_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, COLLECTION);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token",access_token);
        map.put("job_id",job_id);
        httpInfo.setUrl(APIS.URL_COLLECT_JOB);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 取消收藏
     * @param job_id 工作id
     */
    public void cancelCollection(String access_token,int job_id){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, CANCEL_COLLECTION);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token",access_token);
        map.put("job_id",job_id);
        httpInfo.setUrl(APIS.URL_CANCEL_COLLECT_JOB);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 生气工作
     * @param job_id 工作id
     * @param resume_id 简历id
     */
    public void applyJob(String access_token,int job_id, int resume_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, APPLY);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token",access_token);
        map.put("job_id",job_id);
        map.put("resume_id",resume_id);
        httpInfo.setUrl(APIS.URL_APPLY_JOB);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 获取我的简历
     */
    public void getMyResumeList(String access_token) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, RESUME_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("page_count", 20);
        map.put("access_token", access_token);
        map.put("type", 2);
        httpInfo.setUrl(APIS.URL_MY_PUBLISH_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 判断打电话是否收费
     * @param access_token
     */
    public void isContacCharge(String access_token,int relate_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, IS_CHARGE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type", 23);  // 23 职位联系人是否需要费用
        map.put("access_token", access_token);
        map.put("relate_id", relate_id);
        httpInfo.setUrl(APIS.URL_IS_CHARGE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<PositionDetailBean>>() {
                }.getType();
                break;
            case COLLECTION:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case RESUME_LIST:
                type = new TypeReference<BaseResultInfo<MyPublishBean>>() {
                }.getType();
                break;
            case APPLY:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case CANCEL_COLLECTION:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case IS_CHARGE:
                type = new TypeReference<BaseResultInfo<ChargeResultBean>>() {
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
                PositionDetailBean data = (PositionDetailBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case COLLECTION:
                view.dismiss();
                view.collectionSuccess();
                break;
            case RESUME_LIST:
                view.dismiss();
                MyPublishBean publishBean = (MyPublishBean) result.getData();
                if (publishBean!=null) {
                    view.showResumeListSuccess(publishBean);
                }
                break;
            case APPLY:
                view.dismiss();
                view.applySuccess();
                view.showTips("申请成功",0);
                break;
            case CANCEL_COLLECTION:
                view.dismiss();
                view.cancelCollectionSuccess();
                break;
            case IS_CHARGE:
                view.dismiss();
                ChargeResultBean chargeResultBean = (ChargeResultBean) result.getData();
                if (chargeResultBean!=null) {
                    view.isChargeResult(chargeResultBean);
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
            case COLLECTION:
                view.dismiss();
                view.showTips(msg,0);
                break;
            case RESUME_LIST:
                view.dismiss();
                break;
            case APPLY:
                view.dismiss();
                view.showTips(msg,0);
                break;
            case CANCEL_COLLECTION:
                view.dismiss();
                view.showTips(msg,0);
                break;
            case IS_CHARGE:
                view.dismiss();
                view.showTips(msg,0);
                break;
        }
    }
}
