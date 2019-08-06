package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.MyCollectView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.MyCollectBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 * 时间：2018/2/21/021
 * 作者：xjh
 */
public class MyCollectPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;
    private static final int NEXT_DATA = 2;
    private static final int REFRESH_DATA = 3;
    private static final int DELETE_MY_COLLECT = 4;

    private MyCollectView view;

    public MyCollectPresenter(MyCollectView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     */
    public void initData(String access_token,int page,int page_count) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        httpInfo.setUrl(APIS.URL_MY_COLLECT);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     */
    public void refreshData(String access_token,int page,int page_count) {
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        httpInfo.setUrl(APIS.URL_MY_COLLECT);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     */
    public void loadNextData(String access_token,int page,int page_count) {
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        httpInfo.setUrl(APIS.URL_MY_COLLECT);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 删除收藏
     */
    public void deleteCollect(String access_token, List<Integer> collect_ids){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, DELETE_MY_COLLECT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("collect_ids", JSON.toJSONString(collect_ids));
        httpInfo.setUrl(APIS.URL_DELETE_MY_COLLECT);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
            case NEXT_DATA:
            case REFRESH_DATA:
                type = new TypeReference<BaseResultInfo<MyCollectBean>>() {
                }.getType();
                break;
            case DELETE_MY_COLLECT:
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
                MyCollectBean data = (MyCollectBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case REFRESH_DATA:
                MyCollectBean refreshData = (MyCollectBean) result.getData();
                if (refreshData!=null) {
                    view.refreshDataSuccess(refreshData);
                } else {
                    view.refreshDataFailure();
                }
                break;
            case NEXT_DATA:
                MyCollectBean nextData = (MyCollectBean) result.getData();
                if (nextData!=null) {
                    view.loadNextDataSuccess(nextData);
                } else {
                    view.loadNextDataFailure();
                }
                break;
            case DELETE_MY_COLLECT:
                view.dismiss();
                view.deleteCollectSuccess();
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                break;
            case NEXT_DATA:
                view.loadNextDataFailure();
                break;
            case REFRESH_DATA:
                view.refreshDataFailure();
                break;
            case DELETE_MY_COLLECT:
                view.dismiss();
                view.showTips("删除失败",0);
                break;
        }
    }
}
