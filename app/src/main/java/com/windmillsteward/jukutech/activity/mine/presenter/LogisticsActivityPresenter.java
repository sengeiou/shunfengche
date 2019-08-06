package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.LogisticsActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.FundsTrusteeshipBean;
import com.windmillsteward.jukutech.bean.LogisticsInfoListBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/4/21 0021.
 */

public class LogisticsActivityPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;

    private LogisticsActivityView view;

    public LogisticsActivityPresenter(LogisticsActivityView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     */
    public void initData(String access_token,String order_sn,String logistics_single_number) {
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("access_token",access_token);
        map.put("order_sn",order_sn);
        map.put("logistics_single_number",logistics_single_number);
        httpInfo.setUrl(APIS.URL_LOGISTICS_INFO);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<LogisticsInfoListBean>>() {
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
                LogisticsInfoListBean data = (LogisticsInfoListBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                view.showTips(msg,0);
                break;
        }
    }
}
