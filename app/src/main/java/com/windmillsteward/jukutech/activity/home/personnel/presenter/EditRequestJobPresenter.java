package com.windmillsteward.jukutech.activity.home.personnel.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.personnel.activity.EditRequestJobView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.MoreBean;
import com.windmillsteward.jukutech.bean.PostResultBean;
import com.windmillsteward.jukutech.bean.SalaryBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.bean.UploadResultBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 * 时间：2018/1/13/013
 * 作者：xjh
 */
public class EditRequestJobPresenter extends BaseNetModelImpl {

    private final int EDU_LIST=0;
    private final int WORK_LIST=1;
    private final int AREA_LIST=2;
    private final int PUBLISH_AREA_LIST=6;
    private final int SALARY_LIST=3;
    private final int POST=4;
    private final int UPLOAD=5;
    private EditRequestJobView view;

    public EditRequestJobPresenter(EditRequestJobView view) {
        this.view = view;
    }

    public void loadEdu() {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, EDU_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_POP_MORE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

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
     * 加载发布地区
     */
    public void loadPublishAreaData(int second_area_id){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, PUBLISH_AREA_LIST);
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

    public void upload(File file_data) {
        view.showDialog("上传中");

        DataLoader dataLoader = new DataLoader(this, UPLOAD);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, File> fileMap = new TreeMap<>();
        Map<String, Object> map = new TreeMap<>();
        fileMap.put("fileData", file_data);
        httpInfo.setUrl(APIS.UPLOAD);
        httpInfo.setParams(map);
        httpInfo.setFiles(fileMap);
        dataLoader.httpPost(httpInfo);
    }

    public void post(String access_token,String url, String username, int sex,String phone,
                     String birthday, int eduId,int workId,
                     String jobName,int salaryId,
                     int second_area_id, int third_area_id,int work_second_area_id,int work_third_area_id){
        view.showDialog("提交中");

        DataLoader dataLoader = new DataLoader(this, POST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("user_avatar_url", url);
        map.put("true_name", username);
        map.put("sex", sex);
        map.put("mobile_phone", phone);
        map.put("birthday", birthday);
        map.put("education_background_id", eduId);
        map.put("work_year_id", workId);
        map.put("expected_position", jobName);
        map.put("salary_id", salaryId);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("work_second_area_id", work_second_area_id);
        map.put("work_third_area_id", work_third_area_id);
        httpInfo.setUrl(APIS.URL_CREATE_RESUME);
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
            case PUBLISH_AREA_LIST:
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>() {
                }.getType();
                break;
            case SALARY_LIST:
                type = new TypeReference<BaseResultInfo<List<SalaryBean>>>() {
                }.getType();
                break;
            case POST:
                type = new TypeReference<BaseResultInfo<PostResultBean>>() {
                }.getType();
                break;
            case UPLOAD:
                type = new TypeReference<BaseResultInfo<UploadResultBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {

        switch (action) {
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
            case PUBLISH_AREA_LIST:
                view.dismiss();
                List<ThirdAreaBean> publishAreaData = (List<ThirdAreaBean>) result.getData();
                view.loadPublishAreaDataSuccess(publishAreaData);
                break;
            case POST:
                view.dismiss();
                PostResultBean postResultBean = (PostResultBean) result.getData();
                view.postResult(postResultBean);
                break;
            case UPLOAD:
                UploadResultBean uploadResultBean = (UploadResultBean) result.getData();
                view.uploadSuccess(uploadResultBean);
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
            case POST:
                view.dismiss();
                view.showDialog("提交失败");
                break;
            case UPLOAD:
                view.dismiss();
                view.showDialog("头像上传失败");
                break;
        }
    }
}
