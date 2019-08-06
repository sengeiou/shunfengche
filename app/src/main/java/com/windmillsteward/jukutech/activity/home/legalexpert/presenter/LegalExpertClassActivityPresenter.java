package com.windmillsteward.jukutech.activity.home.legalexpert.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.legalexpert.activity.LegalExpertClassActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.NameAndIdBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class LegalExpertClassActivityPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;

    private LegalExpertClassActivityView view;

    public LegalExpertClassActivityPresenter(LegalExpertClassActivityView view) {
        this.view = view;
    }

    public void initData() {
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        httpInfo.setUrl(APIS.URL_LEGAL_EXPERT_TYPE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<List<NameAndIdBean>>>() {
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
                List<NameAndIdBean> topList = (List<NameAndIdBean>) result.getData();
                if (topList!=null)
                    view.initDataSuccess(topList);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,0);
    }
}
