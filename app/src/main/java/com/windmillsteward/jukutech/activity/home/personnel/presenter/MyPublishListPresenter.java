package com.windmillsteward.jukutech.activity.home.personnel.presenter;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.personnel.fragment.MyPublishListView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.MyPublishBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/1/12
 * 作者：xjh
 */

public class MyPublishListPresenter extends BaseNetModelImpl {

    private final int INIT_DATA=1;
    private final int REFRESH_DATA=2;
    private final int NEXT_DATA=3;
    private Context context;
    private MyPublishListView view;

    public MyPublishListPresenter(Context context, MyPublishListView view) {
        this.context = context;
        this.view = view;
    }

    /**
     * 初始化数据
     * @param type 【1：职位，2：简历】
     */
    public void initData(String access_token,int type){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("page_count", 10);
        map.put("access_token", access_token);
        map.put("type", type);
        httpInfo.setUrl(APIS.URL_MY_PUBLISH_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }
    /**
     * 刷新数据
     * @param type 【1：职位，2：简历】
     */
    public void refreshData(String access_token,int type){
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("page_count", 10);
        map.put("access_token", access_token);
        map.put("type", type);
        httpInfo.setUrl(APIS.URL_MY_PUBLISH_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }
    /**
     * 加载下一页
     * @param page 页码
     * @param type 【1：职位，2：简历】
     */
    public void loadNextData(String access_token,int page, int type){
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("page_count", 10);
        map.put("access_token", access_token);
        map.put("type", type);
        httpInfo.setUrl(APIS.URL_MY_PUBLISH_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case REFRESH_DATA:
            case NEXT_DATA:
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<MyPublishBean>>() {
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
                MyPublishBean data = (MyPublishBean) result.getData();
                view.initDataSuccess(data);
                break;
            case REFRESH_DATA:
                MyPublishBean refreshData = (MyPublishBean) result.getData();
                view.refreshDataSuccess(refreshData);
                break;
            case NEXT_DATA:
                MyPublishBean nextData = (MyPublishBean) result.getData();
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
                view.refreshDataSuccess(null);
                break;
            case NEXT_DATA:
                view.loadNextError(msg);
                break;
        }
    }
}
