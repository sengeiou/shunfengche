package com.windmillsteward.jukutech.activity.home.personnel.presenter;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.personnel.activity.LabourServiceCenterView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.LabourServiceCenterListBean;
import com.windmillsteward.jukutech.bean.MoreBean;
import com.windmillsteward.jukutech.bean.SalaryBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：劳务中心请求类
 * 时间：2018/7/31
 * 作者：cyq
 */

public class LabourServiceCenterPresenter extends BaseNetModelImpl {

    private final int INIT_DATA = 1;
    private final int REFRESH_DATA = 2;
    private final int NEXT_DATA = 3;
    private final int AREA_LIST = 4;
    private final int SALARY_LIST = 5;
    private final int POP_MORE = 6;
    private final int BANNER = 7;
    private Context context;
    private LabourServiceCenterView view;

    public LabourServiceCenterPresenter(Context context, LabourServiceCenterView view) {
        this.context = context;
        this.view = view;
    }


    /**
     * 初始化数据
     *
     * @param key            搜索的关键字
     * @param second_area_id 市id
     * @param third_area_id  区域id
     * @param salaryId       薪资id
     * @param workId         工作经验id
     * @param eduId          学历id
     */
    public void initData(String key, int second_area_id, int third_area_id, int salaryId, int workId, int eduId, int job_class_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("page_count", 10);
        map.put("keyword", key);
        map.put("work_second_area_id", second_area_id);
        map.put("work_third_area_id", third_area_id);
        map.put("salary_id", salaryId);
        map.put("education_id", eduId);
        map.put("work_year_id", workId);
        map.put("job_class_id", job_class_id);
        httpInfo.setUrl(APIS.URL_LABOUR_SERVICE_CENTER_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     *
     * @param key            搜索的关键字
     * @param second_area_id 区域id
     * @param salaryId       薪资id
     * @param workId         工作经验id
     * @param eduId          学历id
     */
    public void refreshData(String key, int second_area_id, int third_area_id, int salaryId, int workId, int eduId, int job_class_id) {
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("page", 1);
        map.put("page_count", 10);
        map.put("keyword", key);
        map.put("work_second_area_id", second_area_id);
        map.put("work_third_area_id", third_area_id);
        map.put("salary_id", salaryId);
        map.put("education_id", eduId);
        map.put("work_year_id", workId);
        map.put("job_class_id", job_class_id);
        httpInfo.setUrl(APIS.URL_LABOUR_SERVICE_CENTER_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载区域
     */
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

    /**
     * 加载薪资
     */
    public void loadSalaryData() {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, SALARY_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_SALARY_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载更多分类
     */
    public void loadMoreData() {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, POP_MORE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_POP_MORE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     *
     * @param page           页码
     * @param key            搜索的关键字
     * @param second_area_id 区域id
     * @param salaryId       薪资id
     * @param workId         工作经验id
     * @param eduId          学历id
     */
    public void loadNextData(int page, String key, int second_area_id, int third_area_id, int salaryId, int workId, int eduId, int job_class_id) {
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("page_count", 10);
        map.put("keyword", key);
        map.put("work_second_area_id", second_area_id);
        map.put("work_third_area_id", third_area_id);
        map.put("salary_id", salaryId);
        map.put("education_id", eduId);
        map.put("work_year_id", workId);
        map.put("job_class_id", job_class_id);
        httpInfo.setUrl(APIS.URL_LABOUR_SERVICE_CENTER_LIST);
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
                type = new TypeReference<BaseResultInfo<LabourServiceCenterListBean>>() {
                }.getType();
                break;
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>() {
                }.getType();
                break;
            case SALARY_LIST:
                type = new TypeReference<BaseResultInfo<List<SalaryBean>>>() {
                }.getType();
                break;
            case POP_MORE:
                type = new TypeReference<BaseResultInfo<MoreBean>>() {
                }.getType();
                break;
            case BANNER:
                type = new TypeReference<BaseResultInfo<List<SliderPictureInfo>>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                LabourServiceCenterListBean initData = (LabourServiceCenterListBean) result.getData();
                view.initDataSuccess(initData);
                break;
            case REFRESH_DATA:
                LabourServiceCenterListBean refreshData = (LabourServiceCenterListBean) result.getData();
                view.refreshDataSuccess(refreshData);
                break;
            case NEXT_DATA:
                LabourServiceCenterListBean nextData = (LabourServiceCenterListBean) result.getData();
                view.loadNextDataSuccess(nextData);
                break;
            case AREA_LIST:
                view.dismiss();
                List<ThirdAreaBean> areaBeans = (List<ThirdAreaBean>) result.getData();
                if (areaBeans != null) {
                    ThirdAreaBean areaBean = new ThirdAreaBean();
                    areaBean.setThird_area_id(0);
                    areaBean.setThird_area_name("全部");
                    areaBeans.add(0, areaBean);
                    view.loadAreaDataSuccess(areaBeans);
                }
                break;
            case SALARY_LIST:
                view.dismiss();
                List<SalaryBean> salaryBeans = (List<SalaryBean>) result.getData();
                if (salaryBeans != null) {
                    SalaryBean bean = new SalaryBean();
                    bean.setSalary_id(0);
                    bean.setSalary_show("全部");
                    salaryBeans.add(0, bean);
                    view.loadSalaryDataSuccess(salaryBeans);
                }
                break;
            case POP_MORE:
                view.dismiss();
                MoreBean moreBean = (MoreBean) result.getData();
                view.loadMoreDataSuccess(moreBean);
                break;

        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                break;
            case REFRESH_DATA:
                view.refreshDataFailure();
                break;
            case NEXT_DATA:
                view.loadNextDataError();
                break;
            case AREA_LIST:
                view.dismiss();
                view.showTips(msg, Toast.LENGTH_SHORT);
                break;
            case SALARY_LIST:
                view.dismiss();
                view.showTips(msg, Toast.LENGTH_SHORT);
                break;
            case POP_MORE:
                view.dismiss();
                view.showTips(msg, Toast.LENGTH_SHORT);
                break;
        }
    }

}
