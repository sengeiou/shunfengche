package com.windmillsteward.jukutech.activity.home.carservice.presenter;

import android.util.Log;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.carservice.activity.QuickIndexCarActivityView;
import com.windmillsteward.jukutech.activity.home.commons.quickindex.QuickIndexCityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.AreaBean;
import com.windmillsteward.jukutech.bean.CarClassListBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.PinyinUtils;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/1/26
 * 作者：xjh
 */

public class QuickIndexCarPresenter extends BaseNetModelImpl {

    private final int INIT_DATA=0;

    private QuickIndexCarActivityView view;

    public QuickIndexCarPresenter(QuickIndexCarActivityView view) {
        this.view = view;
    }

    /**
     * 加载车辆品牌
     */
    public void loadAreaData(){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_CAR_CLASS_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    @Override
    protected Type getDataType(int action) {
        Type type=null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<List<CarClassListBean>>>(){}.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                List<CarClassListBean> data = (List<CarClassListBean>) result.getData();
                if (data!=null){
                    for (CarClassListBean bean : data) {
                        bean.setPinyin(PinyinUtils.getPinyin(bean.getBrand_name().replace("·","")));
                    }
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
                break;
        }
    }
}
