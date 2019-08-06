package com.windmillsteward.jukutech.activity.home.personnel.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.personnel.activity.CreatePositionView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.JobClassBean;
import com.windmillsteward.jukutech.bean.MoreBean;
import com.windmillsteward.jukutech.bean.PostPositionResultBean;
import com.windmillsteward.jukutech.bean.SalaryBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.bean.WelfareBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 描述：
 * 时间：2018/1/14/014
 * 作者：xjh
 */
public class CreatePositionPresenter extends BaseNetModelImpl {

    private final int EDU_LIST=0;
    private final int WORK_LIST=1;
    private final int AREA_LIST=2;
    private final int SALARY_LIST=3;
    private final int BENEFIT_LIST=4;
    private final int POST_AREA_LIST=5;
    private final int JOB_CLASS=7;
    private final int POST=6;
    private final int EDIT=9;
    private final int IS_CHARGE=8;


    private Context context;
    private CreatePositionView view;

    public CreatePositionPresenter(Context context, CreatePositionView view) {
        this.context = context;
        this.view = view;
    }

    /**
     * 加载职业分类
     */
    public void loadJobClass() {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, JOB_CLASS);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_JOB_CLASS);
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
     * 加载学历
     */
    public void loadEdu() {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, EDU_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_POP_MORE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载工作年限
     */
    public void loadWork() {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, WORK_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_POP_MORE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载福利列表
     */
    public void loadBenefitList(){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, BENEFIT_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_BENEFIT_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载发布地区
     */
    public void loadPostArea(int second_area_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, POST_AREA_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("second_area_id", second_area_id);
        httpInfo.setUrl(APIS.URL_THIRD_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 判断是否收费
     */
    public void isCharge(String access_token,int relate_id){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, IS_CHARGE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type", 22);
        map.put("relate_id", relate_id);
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_IS_CHARGE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 发布
     * @param job_name 工作名称
     * @param salary_id 薪资id
     * @param work_second_area_id 工作区域市id
     * @param work_third_area_id 工作区域区id
     * @param address 详细地址
     * @param work_year_id 工作年限id
     * @param education_background_id 学历id
     * @param benefit_ids 福利id数组
     * @param require 需求描述
     * @param title 标题
     * @param contact_person 联系人
     * @param contact_tel 联系电话
     * @param second_area_id 发布区域市id
     * @param third_area_id 发布区域区id
     * @param description 描述
     */
    public void post(String access_token,String job_name, int job_class_id_one,int job_class_id_two, int job_class_id_three, int salary_id,
                     int work_second_area_id, int work_third_area_id,
                     String address, int work_year_id, int education_background_id,
                     Set<Integer> benefit_ids, String require, String title, String contact_person,
                     String contact_tel, int second_area_id, int third_area_id,
                     String description){
        view.showDialog("正在提交");

        DataLoader dataLoader = new DataLoader(this, POST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("job_name", job_name);
        map.put("job_class_id_one", job_class_id_one);
        map.put("job_class_id_two", job_class_id_two);
        map.put("job_class_id_three", job_class_id_three);
        map.put("salary_id", salary_id);
        map.put("work_second_area_id", work_second_area_id);
        map.put("work_third_area_id", work_third_area_id);
        map.put("address", address);
        map.put("work_year_id", work_year_id);
        map.put("education_background_id", education_background_id);
        map.put("benefit_ids", JSON.toJSONString(benefit_ids));
        map.put("require", require);
        map.put("title", title);
        map.put("contact_person", contact_person);
        map.put("contact_tel", contact_tel);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("description", description);
        httpInfo.setUrl(APIS.URL_ADD_JOB);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 发布
     * @param job_name 工作名称
     * @param salary_id 薪资id
     * @param work_second_area_id 工作区域市id
     * @param work_third_area_id 工作区域区id
     * @param address 详细地址
     * @param work_year_id 工作年限id
     * @param education_background_id 学历id
     * @param benefit_ids 福利id数组
     * @param require 需求描述
     * @param title 标题
     * @param contact_person 联系人
     * @param contact_tel 联系电话
     * @param second_area_id 发布区域市id
     * @param third_area_id 发布区域区id
     * @param description 描述
     */
    public void edit(String access_token,String job_name, int job_class_id_one,int job_class_id_two, int job_class_id_three, int salary_id,
                     int work_second_area_id, int work_third_area_id,
                     String address, int work_year_id, int education_background_id,
                     Set<Integer> benefit_ids, String require, String title, String contact_person,
                     String contact_tel, int second_area_id, int third_area_id,
                     String description,int job_id){
        view.showDialog("正在提交");

        DataLoader dataLoader = new DataLoader(this, EDIT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("job_id", job_id);
        map.put("access_token", access_token);
        map.put("job_name", job_name);
        map.put("job_class_id_one", job_class_id_one);
        map.put("job_class_id_two", job_class_id_two);
        map.put("job_class_id_three", job_class_id_three);
        map.put("salary_id", salary_id);
        map.put("work_second_area_id", work_second_area_id);
        map.put("work_third_area_id", work_third_area_id);
        map.put("address", address);
        map.put("work_year_id", work_year_id);
        map.put("education_background_id", education_background_id);
        map.put("benefit_ids", JSON.toJSONString(benefit_ids));
        map.put("require", require);
        map.put("title", title);
        map.put("contact_person", contact_person);
        map.put("contact_tel", contact_tel);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("description", description);
        httpInfo.setUrl(APIS.URL_EDIT_JOB);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case WORK_LIST:
            case EDU_LIST:
                type = new TypeReference<BaseResultInfo<MoreBean>>() {
                }.getType();
                break;
            case POST_AREA_LIST:
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>() {
                }.getType();
                break;
            case SALARY_LIST:
                type = new TypeReference<BaseResultInfo<List<SalaryBean>>>() {
                }.getType();
                break;
            case BENEFIT_LIST:
                type = new TypeReference<BaseResultInfo<List<WelfareBean>>>() {
                }.getType();
                break;
            case POST:
                type = new TypeReference<BaseResultInfo<PostPositionResultBean>>() {
                }.getType();
                break;
            case EDIT:
                type = new TypeReference<BaseResultInfo<String>>() {}.getType();
                break;
            case JOB_CLASS:
                type = new TypeReference<BaseResultInfo<List<JobClassBean>>>() {
                }.getType();
                break;
            case IS_CHARGE:
                type = new TypeReference<BaseResultInfo<ChargeResultBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case SALARY_LIST:
                view.dismiss();
                List<SalaryBean> salaryData = (List<SalaryBean>) result.getData();
                view.loadSalaryDataSuccess(salaryData);
                break;
            case AREA_LIST:
                view.dismiss();
                List<ThirdAreaBean> areaData = (List<ThirdAreaBean>) result.getData();
                view.loadAreaDataSuccess(areaData);
                break;
            case EDU_LIST:
                view.dismiss();
                MoreBean eduData = (MoreBean) result.getData();
                view.loadEduDataSuccess(eduData.getEducation_list());
                break;
            case WORK_LIST:
                view.dismiss();
                MoreBean workData = (MoreBean) result.getData();
                view.loadWorkDataSuccess(workData.getWord_year_list());
                break;
            case BENEFIT_LIST:
                view.dismiss();
                List<WelfareBean> welfareData = (List<WelfareBean>) result.getData();
                view.loadWelfareDataSuccess(welfareData);
                break;
            case POST_AREA_LIST:
                view.dismiss();
                List<ThirdAreaBean> postAreaData = (List<ThirdAreaBean>) result.getData();
                view.loadPostAreaDataSuccess(postAreaData);
                break;
            case POST:
                view.dismiss();
                PostPositionResultBean postResultBean = (PostPositionResultBean) result.getData();
                view.postResult(postResultBean);
                view.showTips("发布成功",0);
                break;
            case EDIT:
                view.dismiss();
                view.postResult(null);
                view.showTips("修改成功",0);
                break;
            case JOB_CLASS:
                view.dismiss();
                List<JobClassBean> jobClassBeans = (List<JobClassBean>) result.getData();
                view.loadPositionClassDataSuccess(jobClassBeans);
                break;
            case IS_CHARGE:
                view.dismiss();
                view.isCharge(((ChargeResultBean) result.getData()));
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case EDU_LIST:
                view.dismiss();
                break;
            case WORK_LIST:
                view.dismiss();
                break;
            case SALARY_LIST:
                view.dismiss();
                break;
            case AREA_LIST:
                view.dismiss();
                break;
            case BENEFIT_LIST:
                view.dismiss();
                break;
            case POST:
                view.dismiss();
                view.showTips("发布失败",0);
                break;
            case EDIT:
                view.dismiss();
                view.showTips("保存失败",0);
                break;
            case JOB_CLASS:
                view.dismiss();
                break;
            case IS_CHARGE:
                view.dismiss();
                view.showTips(msg,0);
                break;
        }
    }
}
