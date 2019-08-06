package com.windmillsteward.jukutech.activity.home.think.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.think.fragment.IdeaThinkListView;
import com.windmillsteward.jukutech.activity.home.think.fragment.WisdomFireControlListView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.IdeaThinkClassBean;
import com.windmillsteward.jukutech.bean.IdeaThinkClassBeanTwo;
import com.windmillsteward.jukutech.bean.IdeaThinkListBean;
import com.windmillsteward.jukutech.bean.SpecialtyHomeRecommendBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/2/5
 * 作者：xjh
 */

public class WisdomFireControlListPresenter extends BaseNetModelImpl {

    private static final int INIT_DATA = 1;
    private static final int REFRESH_DATA = 2;
    private static final int NEXT_DATA = 3;
    private static final int AREA_LIST = 4;
    private static final int CLASS_LIST = 5;

    private WisdomFireControlListView view;

    public WisdomFireControlListPresenter(WisdomFireControlListView view) {
        this.view = view;
    }

    /**
     *
     * @param keyword 关键字
     * @param page
     * @param page_count
     * @param second_area_id 市id
     * @param third_area_id 区id
     * @param consumption_type 消防类型
     */
    public void initData(String keyword,int page,int page_count,int second_area_id,int third_area_id,int consumption_type){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("consumption_type", consumption_type);
        httpInfo.setUrl(APIS.URL_SMART_FIRE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void refreshData(String keyword,int page,int page_count,int second_area_id,int third_area_id,int consumption_type){
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("consumption_type", consumption_type);
        httpInfo.setUrl(APIS.URL_SMART_FIRE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadNextData(String keyword,int page,int page_count,int second_area_id,int third_area_id,int consumption_type) {
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("consumption_type", consumption_type);
        httpInfo.setUrl(APIS.URL_SMART_FIRE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadAreaData(int second_area_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, AREA_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("second_area_id", second_area_id);
        httpInfo.setUrl(APIS.URL_THIRD_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadClassData() {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, CLASS_LIST);
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
            case REFRESH_DATA:
            case NEXT_DATA:
                type = new TypeReference<BaseResultInfo<IdeaThinkListBean>>(){}.getType();
                break;
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>(){}.getType();
                break;
            case CLASS_LIST:
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
                IdeaThinkListBean data = (IdeaThinkListBean) result.getData();
                if (data!=null) {
                    view.initDataSuccess(data);
                }
                break;
            case REFRESH_DATA:
                view.dismiss();
                IdeaThinkListBean refreshData = (IdeaThinkListBean) result.getData();
                if (refreshData!=null) {
                    view.refreshDataSuccess(refreshData);
                } else {
                    view.refreshDataFailure();
                }
                break;
            case NEXT_DATA:
                view.dismiss();
                IdeaThinkListBean nextData = (IdeaThinkListBean) result.getData();
                if (nextData!=null) {
                    view.loadNextDataSuccess(nextData);
                } else {
                    view.loadNextDataFailure();
                }
                break;
            case AREA_LIST:
                view.dismiss();
                List<ThirdAreaBean> areaBeans = (List<ThirdAreaBean>) result.getData();
                if (areaBeans!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    HashMap<String, Object> all = new HashMap<>();
                    all.put("id",0);
                    all.put("text","全部");
                    maps.add(all);
                    for (ThirdAreaBean bean : areaBeans) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getThird_area_id());
                        map.put("text",bean.getThird_area_name());
                        maps.add(map);
                    }
                    view.loadAreaDataSuccess(maps);
                }
                break;
            case CLASS_LIST:
                view.dismiss();
                List<SpecialtyHomeRecommendBean> classBeans = (List<SpecialtyHomeRecommendBean>) result.getData();
                if (classBeans!=null) {
                    view.loadClassDataSuccess(classBeans);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action){
            case INIT_DATA:
                view.dismiss();
                break;
            case AREA_LIST:
                view.dismiss();
                break;
            case CLASS_LIST:
                view.dismiss();
                break;
            case REFRESH_DATA:
                view.dismiss();
                view.refreshDataFailure();
                break;
            case NEXT_DATA:
                view.dismiss();
                view.loadNextDataFailure();
                break;
        }
    }
}
