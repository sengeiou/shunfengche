package com.windmillsteward.jukutech.activity.home.personnel.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.personnel.activity.LabourPositionClassActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.LabourPositionClassBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：劳务工种分类
 * 时间：2018/8/1
 * 作者：cyq
 */
public class LabourPositionClassActivityPresenter extends BaseNetModelImpl {

    private final int INIT_DATA=1;
    private final int SEARCH_DATA=2;

    private LabourPositionClassActivityView view;

    public LabourPositionClassActivityPresenter(LabourPositionClassActivityView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     */
    public void initData(String keyword,String access_token){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_LABOR_CLASS);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 搜索
     */
    public void searchData(String keyword,String access_token){
        DataLoader dataLoader = new DataLoader(this, SEARCH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_LABOR_CLASS);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case SEARCH_DATA:
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<List<LabourPositionClassBean>>>() {
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
                List<LabourPositionClassBean> data = (List<LabourPositionClassBean>) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case SEARCH_DATA:
                List<LabourPositionClassBean> searchData = (List<LabourPositionClassBean>) result.getData();
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
