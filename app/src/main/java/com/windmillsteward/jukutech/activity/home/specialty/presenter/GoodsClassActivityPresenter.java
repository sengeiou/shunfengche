package com.windmillsteward.jukutech.activity.home.specialty.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.specialty.activity.GoodsClassActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.GoodsClassListBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public class GoodsClassActivityPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;

    private GoodsClassActivityView view;

    public GoodsClassActivityPresenter(GoodsClassActivityView view) {
        this.view = view;
    }


    /**
     * 初始化数据
     */
    public void initDataSuccess(int store_id){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("store_id", store_id);
        httpInfo.setUrl(APIS.URL_GOODS_CLASS_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<List<GoodsClassListBean>>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case INIT_DATA:
                List<GoodsClassListBean> data = (List<GoodsClassListBean>) result.getData();
                if (data!=null) {
                    GoodsClassListBean bean = new GoodsClassListBean();
                    bean.setCommodity_category_id(0);
                    bean.setCommodity_category_name("全部");
                    data.add(0,bean);
                    view.initDataSuccess(data);
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
