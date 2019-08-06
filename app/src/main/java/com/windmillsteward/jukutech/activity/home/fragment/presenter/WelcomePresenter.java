package com.windmillsteward.jukutech.activity.home.fragment.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.fragment.activity.WelcomeView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.WelcomeBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import org.xutils.common.Callback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：欢迎界面
 * author:cyq
 * 2018-03-02
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class WelcomePresenter extends BaseNetModelImpl {
    private List<Callback.Cancelable> cancelableList = new ArrayList<>();
    private final int WELCOME = 1;
    private final int AREA_LIST = 2;
    private final int HOT_CITY_LIST = 3;
    private WelcomeView view;

    public WelcomePresenter(WelcomeView view) {
        this.view = view;
    }

    public void getWelcomePic() {
        DataLoader dataLoader = new DataLoader(this, WELCOME);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        httpInfo.setUrl(APIS.URL_START_IMAGE);
        httpInfo.setParams(map);
        cancelableList.add(dataLoader.httpPost(httpInfo));
    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case WELCOME:
                type = new TypeReference<BaseResultInfo<WelcomeBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case WELCOME:
                WelcomeBean welcomeBean = (WelcomeBean) result.getData();
                view.getWelcomePicSuccess(welcomeBean);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case WELCOME:
                view.getWelcomePicFailed(code, msg);
                break;
        }
    }

    public void onCancel() {
        if (cancelableList != null){
            for (Callback.Cancelable cancelable : cancelableList) {
                if (cancelable != null){
                    if (!cancelable.isCancelled()){
                        cancelable.cancel();
                    }
                }
            }
        }
    }

}
