package com.windmillsteward.jukutech.activity.home.insurance.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.insurance.activity.PublishInsuranceActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.FileUploadResultBean;
import com.windmillsteward.jukutech.bean.InsuranceListTypeBean;
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

/**
 * 描述：
 * 时间：2018/3/20/020
 * 作者：xjh
 */
public class PublishInsuranceActivityPresenter extends BaseNetModelImpl {

    private final int INSURANCE_TYPE_LIST=1;
    private final int AREA_LIST=2;
    private final int UPLOAD_PIC=3;
    private final int IS_CHARGE=4;
    private final int PUBLISH=5;
    private static final int EDIT = 6;

    private PublishInsuranceActivityView view;

    private List<String> img_urls = new ArrayList<>();


    public PublishInsuranceActivityPresenter(PublishInsuranceActivityView view) {
        this.view = view;
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

    /**
     * 加载发布地区
     */
    public void loadPublishAreaData(int second_area_id) {
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
     * 上传图片
     */
    public void uploadPic(List<String> pic_path) {
        img_urls.clear();
        view.showDialog("发布中...");

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

    /**
     * 判断发布是否收费
     * @param access_token
     */
    public void isCharge(String access_token,int relate_id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, IS_CHARGE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type", 70);
        map.put("access_token", access_token);
        map.put("relate_id", relate_id);
        httpInfo.setUrl(APIS.URL_IS_CHARGE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void publish(String access_token,int second_area_id,int third_area_id,int insurance_type,String contact_person,
                        String contact_mobile_phone,String title,String insurance_introduce,String company_name,String insurance_information,
                        List<String> pic_urls) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, PUBLISH);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("insurance_type", insurance_type);
        map.put("contact_person", contact_person);
        map.put("contact_mobile_phone", contact_mobile_phone);
        map.put("title", title);
        map.put("insurance_introduce", insurance_introduce);
        map.put("company_name", company_name);
        map.put("insurance_information", insurance_information);
        map.put("pic_urls", JSON.toJSONString(pic_urls));
        httpInfo.setUrl(APIS.URL_ADD_INSURANCE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void edit(String access_token,int insurance_id,int second_area_id,int third_area_id,int insurance_type,String contact_person,
                        String contact_mobile_phone,String title,String insurance_introduce,String company_name,String insurance_information,
                        List<String> pic_urls) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, EDIT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("insurance_id", insurance_id);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("insurance_type", insurance_type);
        map.put("contact_person", contact_person);
        map.put("contact_mobile_phone", contact_mobile_phone);
        map.put("title", title);
        map.put("insurance_introduce", insurance_introduce);
        map.put("company_name", company_name);
        map.put("insurance_information", insurance_information);
        map.put("pic_urls", JSON.toJSONString(pic_urls));
        httpInfo.setUrl(APIS.URL_ADD_INSURANCE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case INSURANCE_TYPE_LIST:
                type = new TypeReference<BaseResultInfo<List<InsuranceListTypeBean>>>() {
                }.getType();
                break;
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>(){}.getType();
                break;
            case UPLOAD_PIC:
                type = new TypeReference<BaseResultInfo<FileUploadResultBean>>() {
                }.getType();
                break;
            case IS_CHARGE:
                type = new TypeReference<BaseResultInfo<ChargeResultBean>>() {
                }.getType();
                break;
            case EDIT:
            case PUBLISH:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case INSURANCE_TYPE_LIST:
                view.dismiss();
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
            case AREA_LIST:
                view.dismiss();
                List<ThirdAreaBean> data = (List<ThirdAreaBean>) result.getData();
                if(data!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    for (ThirdAreaBean bean : data) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getThird_area_id());
                        map.put("text",bean.getThird_area_name());
                        maps.add(map);
                    }
                    view.loadPublishAreaDataSuccess(maps);
                }
                break;
            case UPLOAD_PIC:
                view.dismiss();
                FileUploadResultBean picData = (FileUploadResultBean) result.getData();
                if (picData!=null) {
                    img_urls.addAll(picData.getFileUrls());
                    view.uploadPicSuccess(img_urls);
                } else {
                    view.dismiss();
                    view.showTips("发布失败",0);
                }
                break;
            case IS_CHARGE:
                view.dismiss();
                ChargeResultBean chargeResultBean = (ChargeResultBean) result.getData();
                if (chargeResultBean!=null) {
                    view.isChargeResult(chargeResultBean);
                }
                break;
            case PUBLISH:
                view.dismiss();
                view.publishSuccess();
                view.showTips("发布成功",1);
                break;
            case EDIT:
                view.dismiss();
                view.publishSuccess();
                view.showTips("修改成功",1);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,0);
    }
}
