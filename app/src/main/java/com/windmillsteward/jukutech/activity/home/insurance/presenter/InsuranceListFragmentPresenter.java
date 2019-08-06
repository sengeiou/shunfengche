package com.windmillsteward.jukutech.activity.home.insurance.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.insurance.fragment.InsuranceListFragmentView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.InsuranceListBean;
import com.windmillsteward.jukutech.bean.InsuranceListTypeBean;
import com.windmillsteward.jukutech.bean.IntelligentFamilyBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 * 时间：2018/3/18/018
 * 作者：xjh
 */
public class InsuranceListFragmentPresenter extends BaseNetModelImpl {

    private final int INIT_DATA=1;
    private final int REFRESH_DATA=2;
    private final int NEXT_DATA=3;
    private final int AREA_LIST=4;
    private final int INSURANCE_TYPE_LIST=5;

    private InsuranceListFragmentView view;

    public InsuranceListFragmentPresenter(InsuranceListFragmentView view) {
        this.view = view;
    }

    /**
     * 初始化数据
     * @param page 页码
     * @param page_count 当夜数据量
     * @param second_area_id 市id
     * @param third_area_id 区id
     * @param param_name 标题
     */
    public void initData(int page,int page_count,int second_area_id,int third_area_id,int insurance_type,String param_name){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("insurance_type", insurance_type);
        map.put("param_name", param_name);
        httpInfo.setUrl(APIS.URL_INSURANCE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     */
    public void refreshData(int page,int page_count,int second_area_id,int third_area_id,int insurance_type,String param_name){
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("insurance_type", insurance_type);
        map.put("param_name", param_name);
        httpInfo.setUrl(APIS.URL_INSURANCE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     */
    public void loadNextData(int page,int page_count,int second_area_id,int third_area_id,int insurance_type,String param_name){
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("page", page);
        map.put("page_count", page_count);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("insurance_type", insurance_type);
        map.put("param_name", param_name);
        httpInfo.setUrl(APIS.URL_INSURANCE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载地区
     */
    public void loadAreaData(int second_area_id){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, AREA_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("second_area_id", second_area_id);
        httpInfo.setUrl(APIS.URL_THIRD_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载险种列表
     */
    public void loadInsuranceListTypeData() {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INSURANCE_TYPE_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_INSURANCE_TYPE_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case NEXT_DATA:
            case REFRESH_DATA:
            case INIT_DATA:
                type = new TypeReference<BaseResultInfo<InsuranceListBean>>() {
                }.getType();
                break;
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>() {
                }.getType();
                break;
            case INSURANCE_TYPE_LIST:
                type = new TypeReference<BaseResultInfo<List<InsuranceListTypeBean>>>() {
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
                InsuranceListBean initData = (InsuranceListBean) result.getData();
                if (initData!=null) {
                    view.initDataSuccess(initData);
                }
                break;
            case REFRESH_DATA:
                InsuranceListBean refreshData = (InsuranceListBean) result.getData();
                if (refreshData!=null) {
                    view.refreshDataSuccess(refreshData);
                } else {
                    view.refreshDataFailure();
                }
                break;
            case NEXT_DATA:
                InsuranceListBean nextData = (InsuranceListBean) result.getData();
                if (nextData!=null) {
                    view.loadNextDataSuccess(nextData);
                } else {
                    view.loadNextFailure();
                }
                break;
            case AREA_LIST:
                List<ThirdAreaBean> areaData = (List<ThirdAreaBean>) result.getData();
                if (areaData!=null) {
                    view.loadAreaDataSuccess(areaData);
                }
                break;
            case INSURANCE_TYPE_LIST:
                List<InsuranceListTypeBean> insuranceListTypeBeans = (List<InsuranceListTypeBean>) result.getData();
                if (insuranceListTypeBeans!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    for (InsuranceListTypeBean bean : insuranceListTypeBeans) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getId());
                        map.put("text",bean.getName());
                        maps.add(map);
                    }
                    view.loadInsuranceListTypeSuccess(maps);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,1);
    }
}
