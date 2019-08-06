package com.windmillsteward.jukutech.activity.home.fragment.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.fragment.HouseKeeperDataQuickView;
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
 * 描述：管家快讯列表
 * author:cyq
 * 2018-02-06
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class HouseKeeperDataQuickListPresenter extends BaseNetModelImpl {

    private final int INIT_DATA = 1;
    private final int REFRESH_DATA = 2;
    private final int NEXT_DATA = 3;
    private final int DATA_QUICK = 4;

    private HouseKeeperDataQuickView view;

    public HouseKeeperDataQuickListPresenter(HouseKeeperDataQuickView view) {
        this.view = view;
    }

    public void getHouseKeeperData(int page, int page_count) {
        DataLoader dataLoader = new DataLoader(this, DATA_QUICK);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("page", page);
        map.put("page_count", page_count);
        httpInfo.setUrl(APIS.URL_HOUSEKEEPER_DATA_QUICK);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }
    /**
     * 初始化数据
     */
    public void initData(){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", 1);
        map.put("page_count", 10);
        httpInfo.setUrl(APIS.URL_HOUSEKEEPER_DATA_QUICK);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     */
    public void refreshData(){
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", 1);
        map.put("page_count", 10);
        httpInfo.setUrl(APIS.URL_HOUSEKEEPER_DATA_QUICK);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     * @param page 页码
     */
    public void loadNextData(int page){
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", 10);
        httpInfo.setUrl(APIS.URL_HOUSEKEEPER_DATA_QUICK);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case DATA_QUICK:
            case NEXT_DATA:
            case REFRESH_DATA:
            case INIT_DATA:
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
            case DATA_QUICK:
                HouseKeeperDataQuickBean bean = (HouseKeeperDataQuickBean) result.getData();
                view.getHouseKeeperDataQuickListSuccess(bean);
                break;
            case INIT_DATA:
                view.dismiss();
                HouseKeeperDataQuickBean data = (HouseKeeperDataQuickBean) result.getData();
                if (data!=null) {
                    view.getHouseKeeperInitDataSuccess(data);
                }
                break;
            case REFRESH_DATA:
                HouseKeeperDataQuickBean refreshData = (HouseKeeperDataQuickBean) result.getData();
                if (refreshData!=null) {
                    view.getHouseKeeperRefreshDataSuccess(refreshData);
                } else {
                    view.getHouseKeeperRefreshDataFailure();
                }
                break;
            case NEXT_DATA:
                HouseKeeperDataQuickBean nextData = (HouseKeeperDataQuickBean) result.getData();
                if (nextData!=null) {
                    view.getHouseKeeperLoadNextDataSuccess(nextData);
                } else {
                    view.getHouseKeeperLoadNextFailure();
                }
                break;

        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case DATA_QUICK:
                view.getHouseKeeperDataQuickListFailed(code, msg);
                break;
            case INIT_DATA:
                view.dismiss();
                break;
            case REFRESH_DATA:
                view.getHouseKeeperRefreshDataFailure();
                break;
            case NEXT_DATA:
                view.getHouseKeeperLoadNextFailure();
                break;

        }
    }
}
