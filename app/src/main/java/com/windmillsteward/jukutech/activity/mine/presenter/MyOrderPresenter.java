package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.fragment.MyOrderView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.MyOrderBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 * 时间：2018/3/2
 * 作者：xjh
 */

public class MyOrderPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;
    private static final int NEXT_DATA = 2;
    private static final int REFRESH_DATA = 3;

    private MyOrderView view;

    public MyOrderPresenter(MyOrderView view) {
        this.view = view;
    }
    /**
     * 初始化数据
     * @param type 模块类型：【0：全部，1：住宿旅游，2：智慧家庭】
     * @param status 状态：【1：进行中，2：已完成，3：已取消】
     */
    public void initData(String access_token,int page,int page_count,int type,int status) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("type", type);
        map.put("status", status);
        httpInfo.setUrl(APIS.URL_MY_Order);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     */
    public void refreshData(String access_token,int page,int page_count,int type,int status) {
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("type", type);
        map.put("status", status);
        httpInfo.setUrl(APIS.URL_MY_Order);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     */
    public void loadNextData(String access_token,int page,int page_count,int type,int status) {
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("type", type);
        map.put("status", status);
        httpInfo.setUrl(APIS.URL_MY_Order);
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
                type = new TypeReference<BaseResultInfo<MyOrderBean>>() {
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
                MyOrderBean data = (MyOrderBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case REFRESH_DATA:
                MyOrderBean refreshData = (MyOrderBean) result.getData();
                if (refreshData!=null) {
                    view.refreshDataSuccess(refreshData);
                } else {
                    view.refreshDataFailure();
                }
                break;
            case NEXT_DATA:
                MyOrderBean nextData = (MyOrderBean) result.getData();
                if (nextData!=null) {
                    view.loadNextDataSuccess(nextData);
                } else {
                    view.loadNextDataFailure();
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
            case NEXT_DATA:
                view.loadNextDataFailure();
                break;
            case REFRESH_DATA:
                view.refreshDataFailure();
                break;
        }
    }
}
