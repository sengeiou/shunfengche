package com.windmillsteward.jukutech.activity.home.personnel.presenter;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.personnel.fragment.ResumeView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.MoreBean;
import com.windmillsteward.jukutech.bean.ResumeListBean;
import com.windmillsteward.jukutech.bean.SalaryBean;
import com.windmillsteward.jukutech.bean.SexBean;
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
 * 时间：2018/1/10
 * 作者：xjh
 */

public class ResumeListPresenter extends BaseNetModelImpl {

    private Context context;
    private ResumeView view;
    private final int INIT_DATA=1;
    private final int REFRESH_DATA=2;
    private final int NEXT_DATA=3;
    private final int AREA_LIST=4;
    private final int SALARY_LIST=5;
    private final int POP_MORE=6;
    private String[] sexs = new String[]{"全部","男","女"};

    public ResumeListPresenter(Context context, ResumeView view) {
        this.context = context;
        this.view = view;
    }

    /**
     * 初始化数据
     * @param keyword 关键词
     * @param sexId 性别id
     * @param salaryId 薪资id
     * @param workId 工作经验id
     * @param eduId 教育经历id
     */
    public void initData(String keyword, int sexId, int second_area_id,int third_area_id, int salaryId, int workId, int eduId){

        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, INIT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("page", 1);
        map.put("page_count", 10);
        map.put("keyword", keyword);   // 搜索关键词
        map.put("second_area_id", second_area_id);  //	第二区域 城市id
        map.put("third_area_id", third_area_id);  // 	第三区域 区县id
        map.put("sex", sexId);             // 	性别【0：全部，1：男，2：女】
        map.put("salary_id", salaryId);  // 	薪资id
        map.put("work_year_id", workId);  // 	工作年限id
        map.put("education_id", eduId);  // 	学历id
        httpInfo.setUrl(APIS.URL_RESUME_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 刷新数据
     * @param keyword 关键词
     * @param sexId 性别id
     * @param salaryId 薪资id
     * @param workId 工作经验id
     * @param eduId 教育经历id
     */
    public void refreshData(String keyword, int second_area_id,int third_area_id, int sexId, int salaryId, int workId, int eduId) {
        DataLoader dataLoader = new DataLoader(this, REFRESH_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("page", 1);
        map.put("page_count", 10);
        map.put("keyword", keyword);   // 搜索关键词
        map.put("second_area_id", second_area_id);  //	第二区域 城市id
        map.put("third_area_id", third_area_id);  // 	第三区域 区县id
        map.put("sex", sexId);             // 	性别【0：全部，1：男，2：女】
        map.put("salary_id", salaryId);  // 	薪资id
        map.put("work_year_id", workId);  // 	工作年限id
        map.put("education_id", eduId);  // 	学历id
        httpInfo.setUrl(APIS.URL_RESUME_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载下一页
     * @param page 页码
     * @param keyword 关键词
     * @param sexId 性别id
     * @param salaryId 薪资id
     * @param workId 工作经验id
     * @param eduId 教育经历id
     */
    public void loadNextData(int page, String keyword, int second_area_id,int third_area_id, int sexId, int salaryId, int workId, int eduId){
        DataLoader dataLoader = new DataLoader(this, NEXT_DATA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("page", page);
        map.put("page_count", 10);
        map.put("keyword", keyword);   // 搜索关键词
        map.put("second_area_id", second_area_id);  //	第二区域 城市id
        map.put("third_area_id", third_area_id);  // 	第三区域 区县id
        map.put("sex", sexId);             // 	性别【0：全部，1：男，2：女】
        map.put("salary_id", salaryId);  // 	薪资id
        map.put("work_year_id", workId);  // 	工作年限id
        map.put("education_id", eduId);  // 	学历id
        httpInfo.setUrl(APIS.URL_RESUME_LIST);
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
     * 加载性别
     */
    public void loadSexData() {
        view.showDialog("");
        List<SexBean> list = new ArrayList<>();
        for (int i = 0; i < sexs.length; i++) {
            SexBean sexBean = new SexBean();
            sexBean.setSex_id(i);
            sexBean.setSex_name(sexs[i]);
            list.add(sexBean);
        }
        view.dismiss();
        view.loadSexDataSuccess(list);
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
     * 加载更多选项
     */
    public void loadMoreData(){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, POP_MORE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_POP_MORE);
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
                type = new TypeReference<BaseResultInfo<ResumeListBean>>() {
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
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                ResumeListBean data = (ResumeListBean) result.getData();
                view.initDataSuccess(data);
                break;
            case REFRESH_DATA:
                ResumeListBean refreshData = (ResumeListBean) result.getData();
                view.refreshDataSuccess(refreshData);
                break;
            case NEXT_DATA:
                ResumeListBean nextData = (ResumeListBean) result.getData();
                view.loadNextDataSuccess(nextData);
                break;
            case AREA_LIST:
                view.dismiss();
                List<ThirdAreaBean> areaBeans = (List<ThirdAreaBean>) result.getData();
                if (areaBeans!=null) {
                    ThirdAreaBean areaBean = new ThirdAreaBean();
                    areaBean.setThird_area_name("全部");
                    areaBean.setThird_area_id(0);
                    areaBeans.add(0, areaBean);
                    view.loadAreaDataSuccess(areaBeans);
                }
                break;
            case SALARY_LIST:
                view.dismiss();
                List<SalaryBean> salaryBeans = (List<SalaryBean>) result.getData();
                if (salaryBeans!=null) {
                    SalaryBean bean = new SalaryBean();
                    bean.setSalary_id(0);
                    bean.setSalary_show("全部");
                    salaryBeans.add(0,bean);
                    view.loadSalaryDataSuccess(salaryBeans);
                }
                break;
            case POP_MORE:
                MoreBean moreBean = (MoreBean) result.getData();
                view.loadMoreDataSuccess(moreBean);
                view.dismiss();
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case INIT_DATA:
                view.dismiss();
                view.showTips(msg, Toast.LENGTH_SHORT);
                break;
            case REFRESH_DATA:
                view.refreshDataFailure();
                break;
            case NEXT_DATA:
                view.loadNextError(msg);
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
                view.showTips(msg,Toast.LENGTH_SHORT);
                break;
        }
    }
}
