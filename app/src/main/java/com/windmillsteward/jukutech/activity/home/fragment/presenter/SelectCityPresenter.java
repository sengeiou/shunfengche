package com.windmillsteward.jukutech.activity.home.fragment.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.fragment.activity.SelectCityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.base.net.NetUtil;
import com.windmillsteward.jukutech.bean.CityBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataCallBack;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import org.xutils.common.Callback;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：城市选择
 * author:cyq
 * 2018-03-02
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SelectCityPresenter extends BaseNetModelImpl {
    private List<Callback.Cancelable> cancelableList = new ArrayList<>();
    private final int CITY = 1;
    private final int AREA_LIST = 2;
    private final int HOT_CITY_LIST = 3;
    private SelectCityView view;

    public SelectCityPresenter(SelectCityView view) {
        this.view = view;
    }

    public void getCityList(String keyword) {
        DataLoader dataLoader = new DataLoader(this, CITY);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("keyword", keyword);
        httpInfo.setUrl(APIS.URL_SECOND_AREA_LIST);
        httpInfo.setParams(map);
        cancelableList.add(dataLoader.httpPost(httpInfo));
    }

    public void getHotCityList(DataCallBack callBack) {
        new NetUtil().setUrl(APIS.URL_HOT_CITY_LIST)
                .setCallBackData(callBack)
                .buildPost();
    }

    /**
     * 加载发布地区
     *
     * @param second_area_id 当前城市市id
     */
    public void loadAreaData(int second_area_id, DataCallBack dataCallBack) {
        view.showDialog("");
        new NetUtil().setUrl(APIS.URL_THIRD_AREA_LIST)
                .setCallBackData(dataCallBack)
                .addParams("second_area_id", second_area_id + "")
                .buildPost();
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case CITY:
            case HOT_CITY_LIST:
                type = new TypeReference<BaseResultInfo<List<CityBean>>>() {
                }.getType();
                break;
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String
            sourceData) {
        view.dismiss();
        switch (action) {
            case CITY:
                List<CityBean> cityBeanList = (List<CityBean>) result.getData();
                view.getCityListSuccess(cityBeanList);
                break;
            case AREA_LIST:
                view.dismiss();
                break;
            case HOT_CITY_LIST:
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case CITY:
                view.getCityListFailed(code, msg);
                break;
            case AREA_LIST:
                break;
            case HOT_CITY_LIST:
                break;

        }
    }

    public void onCancel() {
        for (Callback.Cancelable cancelable : cancelableList) {
            if (cancelable != null){
                if (!cancelable.isCancelled()){
                    cancelable.cancel();
                }
            }
        }
    }
}
