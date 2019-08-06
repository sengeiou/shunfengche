package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.fragment.MyPublishView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.MyPublishBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 * 时间：2018/2/20/020
 * 作者：xjh
 */
public class MyPublishPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;
    private static final int NEXT_DATA = 2;
    private static final int REFRESH_DATA = 3;

    private MyPublishView view;

    public MyPublishPresenter(MyPublishView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     * @param type 【0：全部，1.人才驿站，2.思想智库，3.智慧家庭，4.房屋模块，5.旅游住宿，6.大健康，7.法律专家，8.资本管理，9.车辆管理】
     * @param publish_status 【0：审核中，1：审核通过，2：审核不通过】
     */
    public void initData(String access_token,int page,int page_count,int type,int publish_status) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("type", type);
        map.put("publish_status", publish_status);
        httpInfo.setUrl(APIS.URL_MY_ISSUE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     * @param type 0：全部，1：人才驿站，2：思想智，3：智慧家庭，4：房屋租售，5：旅游住宿
     * @param publish_status 【0：审核中，1：审核通过，2：审核不通过】
     */
    public void refreshData(String access_token,int page,int page_count,int type,int publish_status) {
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("type", type);
        map.put("publish_status", publish_status);
        httpInfo.setUrl(APIS.URL_MY_ISSUE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     * @param type 0：全部，1：人才驿站，2：思想智，3：智慧家庭，4：房屋租售，5：旅游住宿
     * @param publish_status 【0：审核中，1：审核通过，2：审核不通过】
     */
    public void loadNextData(String access_token,int page,int page_count,int type,int publish_status) {
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("type", type);
        map.put("publish_status", publish_status);
        httpInfo.setUrl(APIS.URL_MY_ISSUE);
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
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case REFRESH_DATA:
                MyPublishBean refreshData = (MyPublishBean) result.getData();
                if (refreshData!=null) {
                    view.refreshDataSuccess(refreshData);
                } else {
                    view.refreshDataFailure();
                }
                break;
            case NEXT_DATA:
                MyPublishBean nextData = (MyPublishBean) result.getData();
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
