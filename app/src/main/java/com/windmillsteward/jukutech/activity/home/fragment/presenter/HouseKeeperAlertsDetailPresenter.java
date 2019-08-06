package com.windmillsteward.jukutech.activity.home.fragment.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.fragment.activity.HouseKeeperAlertsDetailView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.HouseKeeperDataQuickBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：轮播图
 * author:cyq
 * 2018-04-10
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class HouseKeeperAlertsDetailPresenter extends BaseNetModelImpl {

    private final int DETAIL = 1;
    private HouseKeeperAlertsDetailView view;

    public HouseKeeperAlertsDetailPresenter(HouseKeeperAlertsDetailView view) {
        this.view = view;
    }

    public void getDataDetail(int news_flash_id) {
        DataLoader dataLoader = new DataLoader(this, DETAIL);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("news_flash_id", news_flash_id);
        httpInfo.setUrl(APIS.URL_HOUSEKEEPER_DATA_QUICK_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }



    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case DETAIL:
                type = new TypeReference<BaseResultInfo<HouseKeeperDataQuickBean>>() {
                }.getType();
                break;

        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case DETAIL:
                if (view == null){
                    return;
                }
                HouseKeeperDataQuickBean bean = (HouseKeeperDataQuickBean) result.getData();
                view.getDataSuccess(bean);
                break;

        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case DETAIL:
                if (view == null){
                    return;
                }
                view.getDataFailed(code, msg);
                break;
        }
    }
}
