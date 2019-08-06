package com.windmillsteward.jukutech.activity.shoppingcart.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.activity.shoppingcart.activity.AddressListActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.AddressListBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * Created by Administrator on 2018/4/17 0017.
 */

public class AddressListActivityPresenter extends BaseNetModelImpl {


    private final int INIT_DATA=1;
    private final int REFRESH_DATA=2;
    private final int NEXT_DATA=3;

    private AddressListActivityView view;

    public AddressListActivityPresenter(AddressListActivityView view) {
        this.view = view;
    }


    /**
     * 初始化数据
     * @param access_token token
     */
    public void initData(String access_token, int page, int page_count){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        httpInfo.setUrl(APIS.URL_SHOPPING_ADDRESS_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 初始化数据
     * @param access_token token
     */
    public void refreshData(String access_token, int page, int page_count){
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        httpInfo.setUrl(APIS.URL_SHOPPING_ADDRESS_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 初始化数据
     * @param access_token token
     */
    public void loadNextData(String access_token, int page, int page_count){
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("page", page);
        map.put("page_count", page_count);
        httpInfo.setUrl(APIS.URL_SHOPPING_ADDRESS_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case REFRESH_DATA:
            case NEXT_DATA:
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<AddressListBean>>() {}.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case INIT_DATA:
                AddressListBean data = (AddressListBean) result.getData();
                if (data!=null) {
                    List<AddressListBean.ListBean> list = data.getList();
                    if (list!=null) {
                        for (AddressListBean.ListBean bean : list) {
                            int is_default = bean.getIs_default();
                            if (is_default==1) {
                                Hawk.put(Define.DEFAULT_SHOPPING_ADDRESS, JSON.toJSONString(bean));
                            }
                        }
                    }
                    view.initDataSuccess(data);
                }
                break;
            case REFRESH_DATA:
                AddressListBean refreshData = (AddressListBean) result.getData();
                if (refreshData!=null) {
                    List<AddressListBean.ListBean> list = refreshData.getList();
                    if (list!=null) {
                        for (AddressListBean.ListBean bean : list) {
                            int is_default = bean.getIs_default();
                            if (is_default==1) {
                                Hawk.put(Define.DEFAULT_SHOPPING_ADDRESS, JSON.toJSONString(bean));
                            }
                        }
                    }
                    view.refreshDataSuccess(refreshData);
                } else {
                    view.refreshDataFailure();
                }
                break;
            case NEXT_DATA:
                AddressListBean nextData = (AddressListBean) result.getData();
                if (nextData!=null) {
                    List<AddressListBean.ListBean> list = nextData.getList();
                    if (list!=null) {
                        for (AddressListBean.ListBean bean : list) {
                            int is_default = bean.getIs_default();
                            if (is_default==1) {
                                Hawk.put(Define.DEFAULT_SHOPPING_ADDRESS, JSON.toJSONString(bean));
                            }
                        }
                    }
                    view.loadNextDataSuccess(nextData);
                } else {
                    view.loadNextDataFailure();
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case REFRESH_DATA:
                view.refreshDataFailure();
                break;
            case NEXT_DATA:
                view.loadNextDataFailure();
                break;
        }
    }
}
