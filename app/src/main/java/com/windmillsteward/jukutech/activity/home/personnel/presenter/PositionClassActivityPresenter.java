package com.windmillsteward.jukutech.activity.home.personnel.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.personnel.activity.PositionClassActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.PositionClassBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/5/16/016
 * 作者：xjh
 */
public class PositionClassActivityPresenter extends BaseNetModelImpl {

    private final int INIT_DATA=1;
    private final int SEARCH_DATA=2;

    private PositionClassActivityView view;

    public PositionClassActivityPresenter(PositionClassActivityView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     */
    public void initData(String keyword){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        httpInfo.setUrl(APIS.URL_JOB_CLASS);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 搜索
     */
    public void searchData(String keyword){
        DataLoader dataLoader = new DataLoader(this, SEARCH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        httpInfo.setUrl(APIS.URL_JOB_CLASS);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case SEARCH_DATA:
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<List<PositionClassBean>>>() {
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
                List<PositionClassBean> data = (List<PositionClassBean>) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case SEARCH_DATA:
                List<PositionClassBean> searchData = (List<PositionClassBean>) result.getData();
                if (searchData!=null) {
                    view.refreshDataSuccess(searchData);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,0);
    }
}
