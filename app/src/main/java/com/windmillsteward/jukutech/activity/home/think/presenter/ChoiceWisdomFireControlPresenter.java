package com.windmillsteward.jukutech.activity.home.think.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.think.activity.ChoiceIdeaThinkClassView;
import com.windmillsteward.jukutech.activity.home.think.activity.ChoiceWisdomFireControlView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.IdeaThinkClassBean;
import com.windmillsteward.jukutech.bean.SpecialtyHomeRecommendBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/2/5/005
 * 作者：xjh
 */
public class ChoiceWisdomFireControlPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;

    private ChoiceWisdomFireControlView view;

    public ChoiceWisdomFireControlPresenter(ChoiceWisdomFireControlView view) {
        this.view = view;
    }

    public void initData() {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_CONSUMPTION_TYPE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<List<SpecialtyHomeRecommendBean>>>(){}.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                List<SpecialtyHomeRecommendBean> data = (List<SpecialtyHomeRecommendBean>) result.getData();
                if(data!=null) {
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
