package com.windmillsteward.jukutech.activity.home.specialty.presenter;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.home.specialty.activity.SpecialtyClassActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.SpecialtyClassBean;
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

public class SpecialtyClassActivityPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;

    private Context context;
    private SpecialtyClassActivityView view;

    public SpecialtyClassActivityPresenter(Context context, SpecialtyClassActivityView view) {
        this.context = context;
        this.view = view;
    }


    /**
     * 初始化数据
     * @param parent_id 父id默认0全部
     */
    public void initDataSuccess(int parent_id){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("parent_id", parent_id);
        httpInfo.setUrl(APIS.URL_CATEGORY_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<List<SpecialtyClassBean>>>() {
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
                List<SpecialtyClassBean> data = (List<SpecialtyClassBean>) result.getData();
                if (data!=null) {
                    for (SpecialtyClassBean bean : data) {
                        bean.setTextColor(ContextCompat.getColor(context, R.color.text_color_black));
                        bean.setBackgroundColor(ContextCompat.getColor(context,R.color.color_f3f4f6));
                    }
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
