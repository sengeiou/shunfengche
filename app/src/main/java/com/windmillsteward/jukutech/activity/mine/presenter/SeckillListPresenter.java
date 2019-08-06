package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SeckillListView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.SeckillListBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：秒杀列表
 * author:lc
 * 2018-07-09
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SeckillListPresenter extends BaseNetModelImpl {

    private final int INIT_DATA = 1;
    private final int REFRESH_DATA = 2;
    private final int NEXT_DATA = 3;
    private final int DATA_SECKILL = 4;

    private SeckillListView view;

    public SeckillListPresenter(SeckillListView view) {
        this.view = view;
    }

    public void getSeckillListData(int page, int page_count) {
        DataLoader dataLoader = new DataLoader(this, DATA_SECKILL);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("page", page);
        map.put("page_count", page_count);
        httpInfo.setUrl(APIS.SPIKE_LIST);
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
        httpInfo.setUrl(APIS.SPIKE_LIST);
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
        httpInfo.setUrl(APIS.SPIKE_LIST);
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
        httpInfo.setUrl(APIS.SPIKE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case DATA_SECKILL:
            case NEXT_DATA:
            case REFRESH_DATA:
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<List<SeckillListBean>>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case DATA_SECKILL:
                List<SeckillListBean> data = (List<SeckillListBean>) result.getData();
                if (data!=null) {
                    view.getSeckillListSuccess(data);
                }
                break;
            case INIT_DATA:
                view.dismiss();
                List<SeckillListBean> data1 = (List<SeckillListBean>) result.getData();
                if (data1!=null) {
                    view.getSeckillInitDataSuccess(data1);
                }
                break;
            case REFRESH_DATA:
                List<SeckillListBean> refreshData = (List<SeckillListBean>) result.getData();
                if (refreshData!=null) {
                    view.getSeckillRefreshDataSuccess(refreshData);
                } else {
                    view.getSeckillRefreshDataFailure();
                }
                break;
            case NEXT_DATA:
                List<SeckillListBean> nextData = (List<SeckillListBean>) result.getData();
                if (nextData!=null) {
                    view.getSeckillLoadNextDataSuccess(nextData);
                } else {
                    view.getSeckillLoadNextFailure();
                }
                break;

        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case DATA_SECKILL:
                view.getSeckillFailed(code, msg);
                break;
            case INIT_DATA:
                view.dismiss();
                break;
            case REFRESH_DATA:
                view.getSeckillRefreshDataFailure();
                break;
            case NEXT_DATA:
                view.getSeckillLoadNextFailure();
                break;

        }
    }
}
