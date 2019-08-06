package com.windmillsteward.jukutech.activity.home.capitalmanager.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.CapitalDetailActivityView;
import com.windmillsteward.jukutech.activity.home.capitalmanager.activity.PublishView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.CapitalDetailBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.FileUploadResultBean;
import com.windmillsteward.jukutech.bean.NameAndIdBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
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
 * 时间：2018/3/29/029
 * 作者：xjh
 */
public class PublishPresenter extends BaseNetModelImpl {

    private static final int IS_CHARGE = 4;
    private static final int QUERY_CONDITION = 5;
    private static final int PUBLISH_AREA_LIST = 6;
    private static final int PUBLISH = 3;
    private static final int UPLOAD_PIC_DRIVING = 7;
    private static final int UPLOAD_PIC = 2;
    private static final int EDIT = 8;

    private PublishView view;
    private List<String> img_urls = new ArrayList<>();

    public PublishPresenter(PublishView view) {
        this.view = view;
    }

    /**
     * 产品分类
     */
    public void loadProductTypeData() {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, QUERY_CONDITION);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type", 1);
        httpInfo.setUrl(APIS.URL_QUERY_CONDITION);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadPublishAreaData(int second_area_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, PUBLISH_AREA_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("second_area_id", second_area_id);
        httpInfo.setUrl(APIS.URL_THIRD_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void uploadDrivingPic(String pic_path) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, UPLOAD_PIC_DRIVING);
        HttpInfo httpInfo = new HttpInfo();
        httpInfo.setUrl(APIS.URL_FILE_UPLOAD);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("file", new File(pic_path)));
        dataLoader.uploadFiles(httpInfo, list);
    }

    /**
     * 上传图片
     */
    public void uploadPic(List<String> pic_path) {
        img_urls.clear();
        view.showDialog("提交中...");

        DataLoader dataLoader = new DataLoader(this, UPLOAD_PIC);
        HttpInfo httpInfo = new HttpInfo();
        httpInfo.setUrl(APIS.URL_FILE_UPLOAD);
        List<KeyValue> list = new ArrayList<>();
        if (pic_path!=null && pic_path.size()>0) {
            for (int i = 0; i < pic_path.size(); i++) {
                if (pic_path.get(i).contains("http")) {
                    img_urls.add(pic_path.get(i));
                } else {
                    list.add(new KeyValue("file"+i, new File(pic_path.get(i))));
                }
            }
        }
        if (list.size()==0) {
            view.uploadPicSuccess(img_urls);
            return;
        }
        dataLoader.uploadFiles(httpInfo, list);
    }

    public void publish(String access_token,int capital_type,String title,int second_area_id,int third_area_id,String yield_rate,String minimum_amount,
                        String contact_mobile_phone,String contact_person,int product_type,String deadline,String description,String capital_logo,
                        List<String> pic_urls,String introduction) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, PUBLISH);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("capital_type", capital_type);
        map.put("title", title);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("yield_rate", yield_rate);
        map.put("minimum_amount", minimum_amount);
        map.put("contact_mobile_phone", contact_mobile_phone);
        map.put("contact_person", contact_person);
        map.put("product_type", product_type);
        map.put("deadline", deadline);
        map.put("description", description);
        map.put("capital_logo", capital_logo);
        map.put("pic_urls", JSON.toJSONString(pic_urls));
        map.put("introduction", introduction);
        httpInfo.setUrl(APIS.URL_CAPITAL_ADD);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void edit(String access_token,int capital_id,int capital_type,String title,int second_area_id,int third_area_id,String yield_rate,String minimum_amount,
                        String contact_mobile_phone,String contact_person,int product_type,String deadline,String description,String capital_logo,
                        List<String> pic_urls,String introduction) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, PUBLISH);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("capital_id", capital_id);
        map.put("capital_type", capital_type);
        map.put("title", title);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("yield_rate", yield_rate);
        map.put("minimum_amount", minimum_amount);
        map.put("contact_mobile_phone", contact_mobile_phone);
        map.put("contact_person", contact_person);
        map.put("product_type", product_type);
        map.put("deadline", deadline);
        map.put("description", description);
        map.put("capital_logo", capital_logo);
        map.put("pic_urls", JSON.toJSONString(pic_urls));
        map.put("introduction", introduction);
        httpInfo.setUrl(APIS.URL_CAPITAL_ADD);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void isCharge(String access_token,int type,int relate_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, IS_CHARGE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        map.put("access_token", access_token);
        map.put("relate_id", relate_id);
        httpInfo.setUrl(APIS.URL_IS_CHARGE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case IS_CHARGE:
                type = new TypeReference<BaseResultInfo<ChargeResultBean>>() {
                }.getType();
                break;
            case QUERY_CONDITION:
                type = new TypeReference<BaseResultInfo<List<NameAndIdBean>>>(){}.getType();
                break;
            case PUBLISH_AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>(){}.getType();
                break;
            case EDIT:
            case PUBLISH:
                type = new TypeReference<BaseResultInfo<String>>(){}.getType();
                break;
            case UPLOAD_PIC_DRIVING:
            case UPLOAD_PIC:
                type = new TypeReference<BaseResultInfo<FileUploadResultBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case IS_CHARGE:
                view.dismiss();
                ChargeResultBean chargeResultBean = (ChargeResultBean) result.getData();
                if (chargeResultBean!=null) {
                    view.isChargeResult(chargeResultBean);
                }
                break;
            case QUERY_CONDITION:
                List<NameAndIdBean> priceBeans = (List<NameAndIdBean>) result.getData();
                if (priceBeans!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    for (NameAndIdBean bean : priceBeans) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getId());
                        map.put("text",bean.getName());
                        maps.add(map);
                    }
                    view.loadProductTypeDataSuccess(maps);
                }
                break;
            case PUBLISH_AREA_LIST:
                view.dismiss();
                List<ThirdAreaBean> publishAreaData = (List<ThirdAreaBean>) result.getData();
                if(publishAreaData!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    for (ThirdAreaBean bean : publishAreaData) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getThird_area_id());
                        map.put("text",bean.getThird_area_name());
                        maps.add(map);
                    }
                    view.loadPublishAreaDataSuccess(maps);
                }
                break;
            case UPLOAD_PIC_DRIVING:
                view.dismiss();
                FileUploadResultBean picData = (FileUploadResultBean) result.getData();
                if (picData != null) {
                    List<String> fileUrls = picData.getFileUrls();
                    if (fileUrls!=null && fileUrls.size()>0) {
                        view.uploadPicSuccess(fileUrls.get(0));
                    }
                } else {
                    view.showTips("上传失败", 0);
                }
                break;
            case UPLOAD_PIC:
                view.dismiss();
                FileUploadResultBean imageData = (FileUploadResultBean) result.getData();
                if (imageData!=null) {
                    img_urls.addAll(imageData.getFileUrls());
                    view.uploadPicSuccess(img_urls);
                } else {
                    view.dismiss();
                    view.showTips("发布失败",0);
                }
                break;
            case PUBLISH:
                view.dismiss();
                view.publishSuccess();
                view.showTips("提交成功",0);
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
        view.dismiss();
        view.showTips(msg,0);
    }
}
