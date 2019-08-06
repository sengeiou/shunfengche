package com.windmillsteward.jukutech.activity.home.stayandtravel.presenter;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.PublishTravelView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.AreaBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.FileUploadResultBean;
import com.windmillsteward.jukutech.bean.PublishTravelResultBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.bean.TravelClassBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import org.xutils.common.util.KeyValue;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：
 * 时间：2018/1/26
 * 作者：xjh
 */

public class PublishTravelPresenter extends BaseNetModelImpl {

    private final int LOAD_TRAVEL_CLASS = 0;
    private final int LOAD_TRAVEL_AREA = 1;
    private final int LOAD_PUBLISH_AREA = 2;
    private final int UPLOAD = 3;
    private final int PUBLISH = 4;
    private final int EDIT = 6;
    private final int IS_CHARGE = 5;

    private PublishTravelView view;

    private List<String> image_url=new ArrayList<>();

    public PublishTravelPresenter(PublishTravelView view) {
        this.view = view;
    }

    /**
     * 加载旅行分类数据
     */
    public void loadTravelClass(){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, LOAD_TRAVEL_CLASS);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        httpInfo.setUrl(APIS.URL_TRAVEL_CLASS_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载旅行地区
     */
    public void loadTravelArea(){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, LOAD_TRAVEL_AREA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        httpInfo.setUrl(APIS.URL_TRAVEL_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载发布地区数据
     */
    public void loadPublishAreaData(int second_area_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, LOAD_PUBLISH_AREA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("second_area_id", second_area_id);
        httpInfo.setUrl(APIS.URL_THIRD_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void uploadImages(List<String> images_paths) {
        view.showDialog("提交中...");
        image_url.clear();

        List<KeyValue> list = new ArrayList<>();
        for (int i = 0; i < images_paths.size(); i++) {
            if (images_paths.get(i).contains("http")) {
                image_url.add(images_paths.get(i));
            } else {
                list.add(new KeyValue("file"+i, new File(images_paths.get(i))));
            }
        }
        if (list.size()==0) {
            FileUploadResultBean resultBean = new FileUploadResultBean();
            resultBean.setFileUrls(image_url);
            view.uploadFileSuccess(resultBean);
            return;
        }
        DataLoader dataLoader = new DataLoader(this, UPLOAD);
        HttpInfo httpInfo = new HttpInfo();
        httpInfo.setUrl(APIS.URL_FILE_UPLOAD);
        dataLoader.uploadFiles(httpInfo, list);
    }

    /**
     * 判断发布是否收费
     * @param access_token
     */
    public void isCharge(String access_token,int relate_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, IS_CHARGE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type",  65);
        map.put("access_token", access_token);
        map.put("relate_id", relate_id);
        httpInfo.setUrl(APIS.URL_IS_CHARGE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    /**
     * 发布
     * @param pic_urls 图片url
     * @param title 标题
     * @param travel_class_id 旅游分类
     * @param travel_area_id 旅游地区
     * @param go_area_id 出发地
     * @param start_price 价格
     * @param description 产品描述
     * @param notes 须知
     * @param contact_tel 电话
     * @param contact_person 联系人
     * @param second_area_id 发布地区
     * @param third_area_id 发布地区
     */
    public void publish(String access_token,List<String> pic_urls,String title,int travel_class_id,int travel_area_id,int go_area_id,
                        String start_price,String description,String notes,String contact_tel,String contact_person,
                        int second_area_id,int third_area_id) {

        DataLoader dataLoader = new DataLoader(this, PUBLISH);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("pic_urls", JSON.toJSONString(pic_urls));
        map.put("title", title);
        map.put("travel_class_id", travel_class_id);
        map.put("travel_area_id", travel_area_id);
        map.put("go_area_id", go_area_id);
        map.put("start_price", start_price);
        map.put("description", description);
        map.put("notes", notes);
        map.put("contact_tel", contact_tel);
        map.put("contact_person", contact_person);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        httpInfo.setUrl(APIS.URL_PUBLISH_TRAVEL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 编辑
     */
    public void edit(int travel_id,String access_token,List<String> pic_urls,String title,int travel_class_id,int travel_area_id,int go_area_id,
                        String start_price,String description,String notes,String contact_tel,String contact_person,
                        int second_area_id,int third_area_id) {

        DataLoader dataLoader = new DataLoader(this, EDIT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("travel_id", travel_id);
        map.put("pic_urls", JSON.toJSONString(pic_urls));
        map.put("title", title);
        map.put("travel_class_id", travel_class_id);
        map.put("travel_area_id", travel_area_id);
        map.put("go_area_id", go_area_id);
        map.put("start_price", start_price);
        map.put("description", description);
        map.put("notes", notes);
        map.put("contact_tel", contact_tel);
        map.put("contact_person", contact_person);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        httpInfo.setUrl(APIS.URL_EDIT_TRAVEL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }
    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case LOAD_TRAVEL_CLASS:
                type = new TypeReference<BaseResultInfo<List<TravelClassBean>>>() {
                }.getType();
                break;
            case LOAD_PUBLISH_AREA:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>() {
                }.getType();
                break;
            case UPLOAD:
                type = new TypeReference<BaseResultInfo<FileUploadResultBean>>() {
                }.getType();
                break;
            case PUBLISH:
                type = new TypeReference<BaseResultInfo<PublishTravelResultBean>>() {
                }.getType();
                break;
            case LOAD_TRAVEL_AREA:
                type = new TypeReference<BaseResultInfo<List<AreaBean>>>() {
                }.getType();
                break;
            case IS_CHARGE:
                type = new TypeReference<BaseResultInfo<ChargeResultBean>>() {
                }.getType();
                break;
            case EDIT:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case LOAD_TRAVEL_CLASS:
                view.dismiss();
                List<TravelClassBean> data = (List<TravelClassBean>) result.getData();
                if (data!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    for (TravelClassBean datum : data) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",datum.getClass_id());
                        map.put("text",datum.getClass_name());
                        maps.add(map);
                    }
                    view.loadTravelClassSuccess(maps);
                }
                break;
            case LOAD_TRAVEL_AREA:
                view.dismiss();
                List<AreaBean> travelAreaData = (List<AreaBean>) result.getData();
                if (travelAreaData!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    for (AreaBean datum : travelAreaData) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",datum.getArea_id());
                        map.put("text",datum.getArea_name());
                        maps.add(map);
                    }
                    view.loadTravelAreaDataSuccess(maps);
                }
                break;
            case LOAD_PUBLISH_AREA:
                view.dismiss();
                List<ThirdAreaBean> areaBeans = (List<ThirdAreaBean>) result.getData();
                if (areaBeans!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    for (ThirdAreaBean datum : areaBeans) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",datum.getThird_area_id());
                        map.put("text",datum.getThird_area_name());
                        maps.add(map);
                    }
                    view.loadPublishAreaDataSuccess(maps);
                }
                break;
            case UPLOAD:
                FileUploadResultBean uploadResultBean = (FileUploadResultBean) result.getData();
                if (uploadResultBean!=null) {
                    List<String> fileUrls = uploadResultBean.getFileUrls();
                    fileUrls.addAll(image_url);
                    uploadResultBean.setFileUrls(fileUrls);
                    view.uploadFileSuccess(uploadResultBean);
                }
                break;
            case PUBLISH:
                view.dismiss();
                PublishTravelResultBean publishTravelResultBean = (PublishTravelResultBean) result.getData();
                if (publishTravelResultBean!=null) {
                    view.publishSuccess(publishTravelResultBean);
                    view.showTips("提交成功",0);
                }
                break;
            case IS_CHARGE:
                view.dismiss();
                ChargeResultBean chargeResultBean = (ChargeResultBean) result.getData();
                if (chargeResultBean!=null) {
                    view.isChargeResult(chargeResultBean);
                }
                break;
            case EDIT:
                view.dismiss();
                view.publishSuccess(null);
                view.showTips("提交成功",0);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case LOAD_TRAVEL_CLASS:
                view.dismiss();
                break;
            case LOAD_PUBLISH_AREA:
                view.dismiss();
                break;
            case UPLOAD:
                view.dismiss();
                view.showTips("图片上传失败",0);
                break;
            case PUBLISH:
                view.dismiss();
                view.showTips("提交失败",0);
                break;
            case IS_CHARGE:
                view.dismiss();
                view.showTips(msg,0);
                break;
            case EDIT:
                view.dismiss();
                view.showTips(msg,0);
                break;
        }
    }
}
