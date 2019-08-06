package com.windmillsteward.jukutech.activity.home.commons.quickindex;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.FourthAreaBean;
import com.windmillsteward.jukutech.bean.ThirdAndFourthAreaBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/2/2
 * 作者：xjh
 */

public class QuickIndexStreetPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;
    private QuickIndexStreetView view;

    public QuickIndexStreetPresenter(QuickIndexStreetView view) {
        this.view = view;
    }

    public void initData(int second_area_id){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("second_area_id", second_area_id);
        httpInfo.setUrl(APIS.URL_THIRD_FOURTH_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<List<ThirdAndFourthAreaBean>>>(){}.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                List<ThirdAndFourthAreaBean> data = (List<ThirdAndFourthAreaBean>) result.getData();
                if (data!=null) {
                    view.initData(data);
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
