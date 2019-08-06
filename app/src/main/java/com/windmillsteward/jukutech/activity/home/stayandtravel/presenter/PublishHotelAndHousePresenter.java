package com.windmillsteward.jukutech.activity.home.stayandtravel.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.stayandtravel.activity.PublishHotelAndHouseView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.FileUploadResultBean;
import com.windmillsteward.jukutech.bean.HotelConfigsBean;
import com.windmillsteward.jukutech.bean.HotelTypeBean;
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
 * 时间：2018/2/1
 * 作者：xjh
 */

public class PublishHotelAndHousePresenter extends BaseNetModelImpl {

    private static final int HOUSE_TYPE = 1;
    private static final int AREA_LIST = 2;
    private static final int PUBLISH_AREA = 3;
    private static final int UPLOAD_pic = 4;
    private static final int UPLOAD_LICENSE = 5;
    private static final int UPLOAD_IDCARD = 6;
    private static final int UPLOAD_IDCARD_B = 7;
    private static final int PUBLISH = 8;
    private static final int IS_CHARGE = 9;
    private static final int HOTEL_FACILITY = 10;

    private PublishHotelAndHouseView view;

    public PublishHotelAndHousePresenter(PublishHotelAndHouseView view) {
        this.view = view;
    }

    /**
     * 加载房屋分类
     */
    public void loadHoseTypeData(int type){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, HOUSE_TYPE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        if (type==1) {
            httpInfo.setUrl(APIS.URL_HOTEL_TYPE_LIST);
        } else {
            httpInfo.setUrl(APIS.URL_HOUSE_TYPE_LIST);
        }
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载地区
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
     * 加载膳食安排
     */
    public void loadFoodData() {
        List<Map<String,Object>> maps = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("text","有");
        maps.add(map);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("id",0);
        map1.put("text","无");
        maps.add(map1);

        view.loadFoodSupplyDataSuccess(maps);
    }

    /**
     * 加载是否可以携带宠物
     */
    public void loadPetData() {
        List<Map<String,Object>> maps = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("id",0);
        map.put("text","不可携带");
        maps.add(map);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("id",1);
        map1.put("text","可以携带");
        maps.add(map1);

        view.loadTakePetDataSuccess(maps);
    }

    /**
     * 加载预定是否收取定金
     */
    public void loadCollectionDepositData() {
        List<Map<String,Object>> maps = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("id",0);
        map.put("text","不收定金");
        maps.add(map);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("id",1);
        map1.put("text","收取定金");
        maps.add(map1);

        view.loadCollectionDepositDataSucces(maps);
    }

    /**
     * 加载配置
     */
    public void loadConfigsData() {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, HOTEL_FACILITY);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_HOTEL_FACILITY);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    public void loadPublishAreaData(int second_area_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, PUBLISH_AREA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("second_area_id", second_area_id);
        httpInfo.setUrl(APIS.URL_THIRD_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void uploadPicFile(List<String> pic_urls){
        view.showDialog("发布中。。。");
        DataLoader dataLoader = new DataLoader(this, UPLOAD_pic);
        HttpInfo httpInfo = new HttpInfo();
        httpInfo.setUrl(APIS.URL_FILE_UPLOAD);
        List<KeyValue> list = new ArrayList<>();

        if (pic_urls!=null && pic_urls.size()>0) {
            for (int i = 0; i < pic_urls.size(); i++) {
                list.add(new KeyValue("file1"+i, new File(pic_urls.get(i))));
            }
        }
        dataLoader.uploadFiles(httpInfo, list);
    }

    public void uploadLicense(String business_license_url) {
        DataLoader dataLoader = new DataLoader(this, UPLOAD_LICENSE);
        HttpInfo httpInfo = new HttpInfo();
        httpInfo.setUrl(APIS.URL_FILE_UPLOAD);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("file1", new File(business_license_url)));
        dataLoader.uploadFiles(httpInfo, list);
    }
    public void uploadIdCard(String legal_person_id_card_front_url){
        DataLoader dataLoader = new DataLoader(this, UPLOAD_IDCARD);
        HttpInfo httpInfo = new HttpInfo();
        httpInfo.setUrl(APIS.URL_FILE_UPLOAD);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("file1", new File(legal_person_id_card_front_url)));
        dataLoader.uploadFiles(httpInfo, list);
    }

    public void uploadIdCard_b(String legal_person_id_card_back_url) {
        DataLoader dataLoader = new DataLoader(this, UPLOAD_IDCARD_B);
        HttpInfo httpInfo = new HttpInfo();
        httpInfo.setUrl(APIS.URL_FILE_UPLOAD);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("file1", new File(legal_person_id_card_back_url)));
        dataLoader.uploadFiles(httpInfo, list);
    }

    public void publish(String access_token,int hotel_business_class,List<String> pic_urls,String hotel_name,int hotel_type,String opening_date,
                        String customer_service_phone,int second_area_id,int third_area_id,String address,
                        List<Integer> public_facility_ids,List<Integer> room_facility_ids,List<Integer> activity_facility_ids,
                        List<Integer> service_ids,String leave_time,int is_food_supply,int can_take_pet,int is_collection_deposit,
                        String description,String principal_name,String principal_mobile_phone,String business_license_url,
                        String legal_person_id_card_front_url,String legal_person_id_card_back_url,int publish_area_id) {

        DataLoader dataLoader = new DataLoader(this, PUBLISH);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("hotel_business_class", hotel_business_class);
        map.put("pic_urls", JSON.toJSONString(pic_urls));
        map.put("hotel_name", hotel_name);
        map.put("hotel_type", hotel_type);
        map.put("opening_date", opening_date);
        map.put("customer_service_phone", customer_service_phone);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("address", address);
        map.put("public_facility_ids", JSON.toJSONString(public_facility_ids));
        map.put("room_facility_ids", JSON.toJSONString(room_facility_ids));
        map.put("activity_facility_ids", JSON.toJSONString(activity_facility_ids));
        map.put("service_ids", JSON.toJSONString(service_ids));
        map.put("leave_time", leave_time);
        map.put("is_food_supply", is_food_supply);
        map.put("can_take_pet", can_take_pet);
        map.put("is_collection_deposit", is_collection_deposit);
        map.put("description", description);
        map.put("principal_name", principal_name);
        map.put("principal_mobile_phone", principal_mobile_phone);
        map.put("business_license_url", business_license_url);
        map.put("legal_person_id_card_front_url", legal_person_id_card_front_url);
        map.put("legal_person_id_card_back_url", legal_person_id_card_back_url);
        map.put("publish_area_id", publish_area_id);
        httpInfo.setUrl(APIS.URL_ADD_HOTEL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
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
        map.put("type",  61);
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
            case HOUSE_TYPE:
                type = new TypeReference<BaseResultInfo<List<HotelTypeBean>>>(){}.getType();
                break;
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>(){}.getType();
                break;
            case PUBLISH_AREA:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>(){}.getType();
                break;
            case UPLOAD_pic:
                type = new TypeReference<BaseResultInfo<FileUploadResultBean>>(){}.getType();
                break;
            case UPLOAD_LICENSE:
                type = new TypeReference<BaseResultInfo<FileUploadResultBean>>(){}.getType();
                break;
            case UPLOAD_IDCARD:
                type = new TypeReference<BaseResultInfo<FileUploadResultBean>>(){}.getType();
                break;
            case UPLOAD_IDCARD_B:
                type = new TypeReference<BaseResultInfo<FileUploadResultBean>>(){}.getType();
                break;
            case PUBLISH:
                type = new TypeReference<BaseResultInfo<String>>(){}.getType();
                break;
            case IS_CHARGE:
                type = new TypeReference<BaseResultInfo<ChargeResultBean>>() {
                }.getType();
                break;
            case HOTEL_FACILITY:
                type = new TypeReference<BaseResultInfo<HotelConfigsBean>>() {
                }.getType();
                break;

        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case HOUSE_TYPE:
                view.dismiss();
                List<HotelTypeBean> typeBeans = (List<HotelTypeBean>) result.getData();
                if (typeBeans!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    for (HotelTypeBean bean : typeBeans) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getHotel_type());
                        map.put("text",bean.getType_name());
                        maps.add(map);
                    }
                    view.loadHouseTypeDataSuccess(maps);
                }
                break;
            case AREA_LIST:
                view.dismiss();
                List<ThirdAreaBean> areaBeans = (List<ThirdAreaBean>) result.getData();
                if (areaBeans!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    for (ThirdAreaBean bean : areaBeans) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getThird_area_id());
                        map.put("text",bean.getThird_area_name());
                        maps.add(map);
                    }
                    view.loadAreaDataSuccess(maps);
                }
                break;
            case PUBLISH_AREA:
                view.dismiss();
                List<ThirdAreaBean> publishBean = (List<ThirdAreaBean>) result.getData();
                if (publishBean!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    for (ThirdAreaBean bean : publishBean) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",bean.getThird_area_id());
                        map.put("text",bean.getThird_area_name());
                        maps.add(map);
                    }
                    view.loadPublishAreaDataSuccess(maps);
                }
                break;
            case UPLOAD_pic:
                FileUploadResultBean data = (FileUploadResultBean) result.getData();
                if (data!=null) {
                    view.uploadPicSuccess(data.getFileUrls());
                } else {
                    view.dismiss();
                    view.showTips("图片上传失败",0);
                }
                break;
            case UPLOAD_LICENSE:
                FileUploadResultBean licenseData = (FileUploadResultBean) result.getData();
                if (licenseData!=null) {
                    view.uploadLicenseSuccess(licenseData.getFileUrls());
                } else {
                    view.dismiss();
                    view.showTips("图片上传失败",0);
                }
                break;
            case UPLOAD_IDCARD:
                FileUploadResultBean idCradData = (FileUploadResultBean) result.getData();
                if (idCradData!=null) {
                    view.uploadIdCardSuccess(idCradData.getFileUrls());
                } else {
                    view.dismiss();
                    view.showTips("图片上传失败",0);
                }
                break;
            case UPLOAD_IDCARD_B:
                FileUploadResultBean idCard_b = (FileUploadResultBean) result.getData();
                if (idCard_b!=null) {
                    view.uploadIdCardSuccess_b(idCard_b.getFileUrls());
                } else {
                    view.dismiss();
                    view.showTips("图片上传失败",0);
                }
                break;
            case PUBLISH:
                view.dismiss();
                view.publishSuccess(((String) result.getData()));
                view.showTips("发布成功",0);
                break;
            case IS_CHARGE:
                view.dismiss();
                ChargeResultBean chargeResultBean = (ChargeResultBean) result.getData();
                if (chargeResultBean!=null) {
                    view.isChargeResult(chargeResultBean);
                }
                break;
            case HOTEL_FACILITY:
                view.dismiss();
                HotelConfigsBean hotelConfigsBean = (HotelConfigsBean) result.getData();
                if (hotelConfigsBean!=null) {
                    view.loadHotelConfigsResuccess(hotelConfigsBean);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case HOUSE_TYPE:
                view.dismiss();
                break;
            case AREA_LIST:
                view.dismiss();
                break;
            case PUBLISH_AREA:
                view.dismiss();
                break;
            case UPLOAD_pic:
                view.dismiss();
                break;
            case UPLOAD_LICENSE:
                view.dismiss();
                break;
            case UPLOAD_IDCARD:
                view.dismiss();
                break;
            case UPLOAD_IDCARD_B:
                view.dismiss();
                break;
            case PUBLISH:
                view.dismiss();
                break;
            case IS_CHARGE:
                view.dismiss();
                view.showTips(msg,0);
                break;
            case HOTEL_FACILITY:
                view.dismiss();
                break;
        }
    }
}
