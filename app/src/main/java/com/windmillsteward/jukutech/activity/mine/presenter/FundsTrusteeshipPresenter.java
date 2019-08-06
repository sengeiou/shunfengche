package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.fragment.FundsTrusteeshipView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.FundsTrusteeshipBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：我的优惠券列表实现类
 * 时间：2018/3/5
 * 作者：cyq
 */

public class FundsTrusteeshipPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;
    private static final int NEXT_DATA = 2;
    private static final int REFRESH_DATA = 3;

    private FundsTrusteeshipView view;

    public FundsTrusteeshipPresenter(FundsTrusteeshipView view) {
        this.view = view;
    }
    /**
     * 初始化数据
     * @param type 类别 1待审核 2托管中 3纠纷订单 4已完成
     */
    public void initData(String access_token,int page,int page_count,int type) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("type", type);
        httpInfo.setUrl(APIS.URL_TRUSTEESHIP_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     */
    public void refreshData(String access_token,int page,int page_count,int type) {
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("type", type);
        httpInfo.setUrl(APIS.URL_TRUSTEESHIP_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     */
    public void loadNextData(String access_token,int page,int page_count,int type) {
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("type", type);
        httpInfo.setUrl(APIS.URL_TRUSTEESHIP_LIST);
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
                type = new TypeReference<BaseResultInfo<FundsTrusteeshipBean>>() {
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
                FundsTrusteeshipBean data = (FundsTrusteeshipBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case REFRESH_DATA:
                FundsTrusteeshipBean refreshData = (FundsTrusteeshipBean) result.getData();
                if (refreshData!=null) {
                    view.refreshDataSuccess(refreshData);
                } else {
                    view.refreshDataFailure();
                }
                break;
            case NEXT_DATA:
                FundsTrusteeshipBean nextData = (FundsTrusteeshipBean) result.getData();
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
