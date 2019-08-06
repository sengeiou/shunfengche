package com.windmillsteward.jukutech.activity.home.fragment.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.fragment.activity.SearchModuleView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.SearchModuleBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：首页搜索
 * author:cyq
 * 2018-02-06
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public class SearchModulePresenter extends BaseNetModelImpl {

    private final int SEARCH_MODULE = 1;
    private SearchModuleView view;

    public SearchModulePresenter(SearchModuleView view) {
        this.view = view;
    }

    /**
     *
     * @param keyword 关键字
     * @param type 模块类型：【0：全部，1：人才驿站，2：思想智库，3：智慧家庭，4：房屋租售，5：住宿旅行】
     */
    public void searchModuleData(String keyword,int type,int second_area_id) {
        DataLoader dataLoader = new DataLoader(this, SEARCH_MODULE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("keyword", keyword);
        map.put("type", type);
        map.put("second_area_id", second_area_id);
        httpInfo.setUrl(APIS.URL_SEARCH_MODULE_DATA_NEW);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }




    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case SEARCH_MODULE:
                type = new TypeReference<BaseResultInfo<List<SearchModuleBean>>>() {
                }.getType();
                break;

        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case SEARCH_MODULE:
                List<SearchModuleBean> list = (List<SearchModuleBean>) result.getData();
                view.getSearchModuleListSuccess(list);
                break;

        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case SEARCH_MODULE:
                view.getSearchModuleListFailed(code, msg);
                break;

        }
    }
}
