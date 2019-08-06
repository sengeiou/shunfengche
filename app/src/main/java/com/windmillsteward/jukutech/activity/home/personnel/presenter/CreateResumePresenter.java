package com.windmillsteward.jukutech.activity.home.personnel.presenter;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.personnel.activity.CreateResumeView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.EduExpBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.bean.WorkExpBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/1/14/014
 * 作者：xjh
 */
public class CreateResumePresenter extends BaseNetModelImpl {

    private final int ADD_RESUME=2;
    private final int EDIT=3;
    private CreateResumeView view;

    public CreateResumePresenter( CreateResumeView view) {
        this.view = view;
    }


    public void push(String access_token,List<WorkExpBean> work_experience_list,
                     List<EduExpBean> education_list,
                     String self_intro,String resume_id){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, ADD_RESUME);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("work_experience_list", JSON.toJSONString(work_experience_list));
        map.put("education_list", JSON.toJSONString(education_list));
        map.put("self_intro", self_intro);
        map.put("resume_id", resume_id);
        httpInfo.setUrl(APIS.URL_ADD_RESUME);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);


    }

    public void edit(String access_token,List<WorkExpBean> work_experience_list,
                     List<EduExpBean> education_list,
                     String self_intro,int resume_id){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, EDIT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("work_experience_list", JSON.toJSONString(work_experience_list));
        map.put("education_list", JSON.toJSONString(education_list));
        map.put("self_intro", self_intro);
        map.put("resume_id", resume_id);
        httpInfo.setUrl(APIS.URL_EDIT_RESUME);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);


    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case EDIT:
            case ADD_RESUME:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case ADD_RESUME:
                view.dismiss();
                view.publishSuccess();
                view.showTips("发布成功",0);
                break;
            case EDIT:
                view.dismiss();
                view.publishSuccess();
                view.showTips("修改成功",0);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case ADD_RESUME:
                view.dismiss();
                view.showTips("发布失败",0);
                break;
            case EDIT:
                view.dismiss();
                view.showTips("修改失败",0);
                break;
        }
    }
}
