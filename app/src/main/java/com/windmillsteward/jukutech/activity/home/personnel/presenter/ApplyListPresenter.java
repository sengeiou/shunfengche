package com.windmillsteward.jukutech.activity.home.personnel.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.personnel.activity.ApplyListView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.ApplyListBean;
import com.windmillsteward.jukutech.bean.EmployResumeListBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 * 时间：2018/1/10
 * 作者：xjh
 */

public class ApplyListPresenter extends BaseNetModelImpl {

    private final int INIT_DATA=1;
    private final int REFRESH_DATA=2;
    private final int NEXT_DATA=3;
    private ApplyListView view;

    public ApplyListPresenter(ApplyListView view) {
        this.view = view;
    }

    /**
     * 初始化数据-只在刚进来时刷,要显示动画
     */
    public void initData(String access_token,int isView, int sortType){

        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("page", 1);
        map.put("page_count", 10);
        map.put("access_token", access_token);
        map.put("is_view", isView);
        map.put("sort_type", sortType);
        httpInfo.setUrl(APIS.URL_EMPLOY_RESUME);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     */
    public void refreshData(String access_token,int isView, int sortType) {
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("page", 1);
        map.put("page_count", 10);
        map.put("access_token", access_token);
        map.put("is_view", isView);
        map.put("sort_type", sortType);
        httpInfo.setUrl(APIS.URL_EMPLOY_RESUME);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     */
    public void loadNextData(String access_token,int page, int isView, int sortType){
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("page", page);
        map.put("page_count", 10);
        map.put("access_token", access_token);
        map.put("is_view", isView);
        map.put("sort_type", sortType);
        httpInfo.setUrl(APIS.URL_EMPLOY_RESUME);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case NEXT_DATA:
            case REFRESH_DATA:
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<EmployResumeListBean>>() {
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
                EmployResumeListBean data = (EmployResumeListBean) result.getData();
                view.initDataSuccess(data);
                break;
            case REFRESH_DATA:
                EmployResumeListBean refreshData = (EmployResumeListBean) result.getData();
                view.refreshDataSuccess(refreshData);
                break;
            case NEXT_DATA:
                EmployResumeListBean nextData = (EmployResumeListBean) result.getData();
                view.loadNextDataSuccess(nextData);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                view.showTips(msg, Toast.LENGTH_SHORT);
                break;
            case REFRESH_DATA:
                view.refreshDataFailure();
                break;
            case NEXT_DATA:
                view.loadNextError(msg);
                break;
        }
    }
}
