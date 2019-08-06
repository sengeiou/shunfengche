package com.windmillsteward.jukutech.activity.home.houselease.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.carservice.activity.PublishSellCarActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.FileUploadResultBean;
import com.windmillsteward.jukutech.bean.HouseMoreBean;
import com.windmillsteward.jukutech.bean.MoreCarListBean;
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
 * 时间：2018/4/2/002
 * 作者：xjh
 */
public class PublishSellCarActivityPresenter extends BaseNetModelImpl {

    private static final int Gearbox_ID = 1;
    private static final int DEAL_AREA_LIST = 2;
    private static final int PUBLISH_AREA_LIST = 3;
    private static final int UPLOAD_PIC_DRIVING = 4;
    private static final int UPLOAD_PIC_VEHICEL = 5;
    private static final int UPLOAD_PIC = 6;
    private static final int PUBLISH = 7;
    private static final int IS_CHARGE = 8;
    private static final int EDIT = 9;

    private PublishSellCarActivityView view;
    private String[] car_colors = new String[]{"黑色","白色","红色","黄色","蓝色","绿色","紫色","橙色","棕色","银灰色","深灰色","香槟色","其他"};
    private List<String> img_urls = new ArrayList<>();

    public PublishSellCarActivityPresenter(PublishSellCarActivityView view) {
        this.view = view;
    }

    public void loadGearboxData() {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, Gearbox_ID);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_MORE_CAR_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadCarColorData() {
        List<Map<String,Object>> maps = new ArrayList<>();
        for (int i = 0; i < car_colors.length; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",i);
            map.put("text",car_colors[i]);
            maps.add(map);
        }
        view.loadCarColorDataSuccess(maps);
    }

    public void loadDealAreaData(int second_area_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, DEAL_AREA_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("second_area_id", second_area_id);
        httpInfo.setUrl(APIS.URL_THIRD_AREA_LIST);
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

    /**
     * 上传行驶证
     */
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
     * 上传车id
     */
    public void uploadVehiclePic(String pic_path) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, UPLOAD_PIC_VEHICEL);
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

    /**
     * 判断是否收费
     */
    public void isCharge(String access_token,int relate_id){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, IS_CHARGE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("type", 100);
        map.put("relate_id", relate_id);
        map.put("access_token", access_token);
        httpInfo.setUrl(APIS.URL_IS_CHARGE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    public void publish(String access_token,List<String> pic_urls,int brand_id,int series_id,int vehicle_module_id,
                        String name,String displacement,int gearbox_id,String license_time,String next_validate_time,
                        String compulsory_insurance_time,String commercial_insurance_time,String mileage,String price,
                        int is_transfer_fee,String car_color,int deal_second_area_id,int deal_third_area_id,String driving_license,
                        String vehicle_identification,String details,String contact_person,String contact_tel,int second_area_id,int third_area_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, PUBLISH);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("pic_urls", JSON.toJSONString(pic_urls));
        map.put("brand_id", brand_id);
        map.put("series_id", series_id);
        map.put("vehicle_module_id", vehicle_module_id);
        map.put("name", name);
        map.put("displacement", displacement);
        map.put("gearbox_id", gearbox_id);
        map.put("license_time", license_time);
        map.put("next_validate_time", next_validate_time);
        map.put("compulsory_insurance_time", compulsory_insurance_time);
        map.put("commercial_insurance_time", commercial_insurance_time);
        map.put("mileage", mileage);
        map.put("price", price);
        map.put("is_transfer_fee", is_transfer_fee);
        map.put("car_color", car_color);
        map.put("deal_second_area_id", deal_second_area_id);
        map.put("deal_third_area_id", deal_third_area_id);
        map.put("driving_license", driving_license);
        map.put("vehicle_identification", vehicle_identification);
        map.put("details", details);
        map.put("contact_person", contact_person);
        map.put("contact_tel", contact_tel);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        httpInfo.setUrl(APIS.URL_SALE_CAR);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void edit(String access_token,int car_id,List<String> pic_urls,int brand_id,int series_id,int vehicle_module_id,
                        String name,String displacement,int gearbox_id,String license_time,String next_validate_time,
                        String compulsory_insurance_time,String commercial_insurance_time,String mileage,String price,
                        int is_transfer_fee,String car_color,int deal_second_area_id,int deal_third_area_id,String driving_license,
                        String vehicle_identification,String details,String contact_person,String contact_tel,int second_area_id,int third_area_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, EDIT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("car_id", car_id);
        map.put("pic_urls", JSON.toJSONString(pic_urls));
        map.put("brand_id", brand_id);
        map.put("series_id", series_id);
        map.put("vehicle_module_id", vehicle_module_id);
        map.put("name", name);
        map.put("displacement", displacement);
        map.put("gearbox_id", gearbox_id);
        map.put("license_time", license_time);
        map.put("next_validate_time", next_validate_time);
        map.put("compulsory_insurance_time", compulsory_insurance_time);
        map.put("commercial_insurance_time", commercial_insurance_time);
        map.put("mileage", mileage);
        map.put("price", price);
        map.put("is_transfer_fee", is_transfer_fee);
        map.put("car_color", car_color);
        map.put("deal_second_area_id", deal_second_area_id);
        map.put("deal_third_area_id", deal_third_area_id);
        map.put("driving_license", driving_license);
        map.put("vehicle_identification", vehicle_identification);
        map.put("details", details);
        map.put("contact_person", contact_person);
        map.put("contact_tel", contact_tel);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        httpInfo.setUrl(APIS.URL_EDIT_SALE_CAR);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case Gearbox_ID:
                type = new TypeReference<BaseResultInfo<MoreCarListBean>>() {
                }.getType();
                break;
            case PUBLISH_AREA_LIST:
            case DEAL_AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>(){}.getType();
                break;
            case UPLOAD_PIC:
            case UPLOAD_PIC_VEHICEL:
            case UPLOAD_PIC_DRIVING:
                type = new TypeReference<BaseResultInfo<FileUploadResultBean>>() {
                }.getType();
                break;
            case IS_CHARGE:
                type = new TypeReference<BaseResultInfo<ChargeResultBean>>() {
                }.getType();
                break;
            case PUBLISH:
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
            case Gearbox_ID:
                view.dismiss();
                MoreCarListBean data = (MoreCarListBean) result.getData();
                if (data!=null) {
                    List<MoreCarListBean.GearboxListBean> gearbox_list = data.getGearbox_list();
                    if (gearbox_list!=null) {
                        List<Map<String,Object>> maps = new ArrayList<>();
                        for (int i = 0; i < gearbox_list.size(); i++) {
                            Map<String,Object> map = new HashMap<>();
                            map.put("id",gearbox_list.get(i).getGearbox_id());
                            map.put("text",gearbox_list.get(i).getGearbox_name());
                            maps.add(map);
                        }
                        view.loadGearboxDataSuccess(maps);
                    }
                }
                break;
            case DEAL_AREA_LIST:
                view.dismiss();
                List<ThirdAreaBean> dealAreaData = (List<ThirdAreaBean>) result.getData();
                if(dealAreaData!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    for (ThirdAreaBean bean : dealAreaData) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getThird_area_id());
                        map.put("text",bean.getThird_area_name());
                        maps.add(map);
                    }
                    view.loadDealAreaDataSuccess(maps);
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
            case UPLOAD_PIC_VEHICEL:
                view.dismiss();
                FileUploadResultBean picData1 = (FileUploadResultBean) result.getData();
                if (picData1 != null) {
                    List<String> fileUrls = picData1.getFileUrls();
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
            case IS_CHARGE:
                view.dismiss();
                view.isCharge(((ChargeResultBean) result.getData()));
                break;
            case EDIT:
            case PUBLISH:
                view.dismiss();
                view.publishSuccess();
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,0);
    }
}
