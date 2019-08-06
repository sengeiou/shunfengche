package com.windmillsteward.jukutech.activity.home.houselease.presenter;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.houselease.activity.PublishRentOutView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.FileUploadResultBean;
import com.windmillsteward.jukutech.bean.HouseConfigBean;
import com.windmillsteward.jukutech.bean.HouseMoreBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.StaticData;
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
 * 时间：2018/2/6
 * 作者：xjh
 */

public class PublishRentOutPresenter extends BaseNetModelImpl {

    private static final int AREA_LIST = 1;
    private static final int UPLOAD_PIC = 2;
    private static final int UPLOAD_CERT = 3;
    private static final int PUBLISH = 4;
    private static final int EDIT = 5;
    private static final int IS_CHARGE = 6;
    private static final int HOUSE_CONFIG = 7;
    private static final int RENT_TYPE = 8;
    private static final int RENT_TYPE_F = 9;

    private PublishRentOutView view;
    private String[] cx = StaticData.getOrientation_text();
    private String[] fixture = StaticData.getDecoration_text();
    private String[] rent_deposit = StaticData.getRent_deposit_text();
    private List<String> img_urls = new ArrayList<>();

    public PublishRentOutPresenter(PublishRentOutView view) {
        this.view = view;
    }

    public void loadHouseConfig() {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, HOUSE_CONFIG);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        httpInfo.setUrl(APIS.URL_HOUSE_FONFID);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载朝向
     */
    public void loadCXData() {
        List<Map<String,Object>> maps = new ArrayList<>();
        for (int i = 0; i < cx.length; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",i+1);
            map.put("text",cx[i]);
            maps.add(map);
        }
        view.loadCXDataSuccess(maps);
    }

    /**
     * 加载装修
     */
    public void loadFixtureData() {
        List<Map<String,Object>> maps = new ArrayList<>();
        for (int i = 0; i < fixture.length; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",i+1);
            map.put("text",fixture[i]);
            maps.add(map);
        }
        view.loadFixtureDataSuccess(maps);
    }

    /**
     * 加载租金
     */
    public void loadRentDepositData(){
        List<Map<String,Object>> maps = new ArrayList<>();
        for (int i = 0; i < rent_deposit.length; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",i+1);
            map.put("text",rent_deposit[i]);
            maps.add(map);
        }
        view.loadRentDepositDataSuccess(maps);
    }

    /**
     * 加载租房类型
     */
//    public void loadRentTypeData(){
//        view.showDialog("");
//
//        DataLoader dataLoader = new DataLoader(this, RENT_TYPE);
//        HttpInfo httpInfo = new HttpInfo();
//        Map<String, Object> map = new HashMap<>();
//        map.put("type", 1);
//        httpInfo.setUrl(APIS.URL_HOUS_MORE);
//        httpInfo.setParams(map);
//        dataLoader.httpPost(httpInfo);
//    }

//    /**
//     * 加载租房类型
//     */
//    public void loadRentTypeDataF(){
//        view.showDialog("");
//
//        DataLoader dataLoader = new DataLoader(this, RENT_TYPE_F);
//        HttpInfo httpInfo = new HttpInfo();
//        Map<String, Object> map = new HashMap<>();
//        map.put("type", 1);
//        httpInfo.setUrl(APIS.URL_HOUS_MORE);
//        httpInfo.setParams(map);
//        dataLoader.httpPost(httpInfo);
//    }

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
        dataLoader.uploadFiles(httpInfo, list);
    }

    /**
     * 上传图片
     * @param cert_path
     */
    public void uploadPropertyCertPic(String cert_path){
        if (TextUtils.isEmpty(cert_path)) {
            view.uploadCertPicSuccess("");
        } else {
            if (cert_path.contains("http")) {
                view.uploadCertPicSuccess(cert_path);
            } else {
                DataLoader dataLoader = new DataLoader(this, UPLOAD_CERT);
                HttpInfo httpInfo = new HttpInfo();
                httpInfo.setUrl(APIS.URL_FILE_UPLOAD);
                List<KeyValue> list = new ArrayList<>();
                list.add(new KeyValue("file", new File(cert_path)));
                dataLoader.uploadFiles(httpInfo, list);
            }
        }
    }

    /**
     * 发布
     */
    public void publish(String access_token,int require_type,int house_second_id,int house_third_id,int house_fourth_id,
                        String house_rooms, String house_parlor,String floor, String total_price,String title,String description,
                        String contact_person,String contact_mobile_phone,int second_area_id,int third_area_id,
                        String floor_area,int orientation,int decoration,List<String> pic_urls,String community_name,
                        String property_cert_pic_url,int house_type,int property_right,String developers_name,int rent_deposit_type,
                        List<Integer> house_config_ids,int school_degree_type){
        DataLoader dataLoader = new DataLoader(this, PUBLISH);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("require_type", require_type);
        map.put("house_second_id", house_second_id);
        map.put("house_third_id", house_third_id);
        map.put("house_fourth_id", house_fourth_id);
        map.put("house_rooms", house_rooms);
        map.put("house_parlor", house_parlor);
        map.put("total_price", total_price);
        map.put("title", title);
        map.put("floor", floor);
        map.put("description", description);
        map.put("contact_person", contact_person);
        map.put("contact_mobile_phone", contact_mobile_phone);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("floor_area", floor_area);
        map.put("orientation", orientation);
        map.put("decoration", decoration);
        map.put("community_name", community_name);
        map.put("property_cert_pic_url", property_cert_pic_url);
        map.put("house_type", house_type);
        map.put("property_right", property_right);
        map.put("developers_name", developers_name);
        map.put("rent_deposit_type", rent_deposit_type);
        map.put("house_config_ids", JSON.toJSONString(house_config_ids));
        map.put("school_degree_type", school_degree_type);
        map.put("pic_urls", JSON.toJSONString(pic_urls));

        httpInfo.setUrl(APIS.URL_HOUSE_PUBLISH);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 发布
     */
    public void edit(int house_id,String access_token,int require_type,int house_second_id,int house_third_id,int house_fourth_id,
                        String house_rooms, String house_parlor,String floor, String total_price,String title,String description,
                        String contact_person,String contact_mobile_phone,int second_area_id,int third_area_id,
                        String floor_area,int orientation,int decoration,List<String> pic_urls,String community_name,
                        String property_cert_pic_url,int house_type,int property_right,String developers_name,int rent_deposit_type,
                        List<Integer> house_config_ids,int school_degree_type){
        DataLoader dataLoader = new DataLoader(this, EDIT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("house_id", house_id);
        map.put("access_token", access_token);
        map.put("require_type", require_type);
        map.put("house_second_id", house_second_id);
        map.put("house_third_id", house_third_id);
        map.put("house_fourth_id", house_fourth_id);
        map.put("house_rooms", house_rooms);
        map.put("house_parlor", house_parlor);
        map.put("total_price", total_price);
        map.put("title", title);
        map.put("floor", floor);
        map.put("description", description);
        map.put("contact_person", contact_person);
        map.put("contact_mobile_phone", contact_mobile_phone);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("floor_area", floor_area);
        map.put("orientation", orientation);
        map.put("decoration", decoration);
        map.put("community_name", community_name);
        map.put("property_cert_pic_url", property_cert_pic_url);
        map.put("house_type", house_type);
        map.put("property_right", property_right);
        map.put("developers_name", developers_name);
        map.put("rent_deposit_type", rent_deposit_type);
        map.put("house_config_ids", JSON.toJSONString(house_config_ids));
        map.put("school_degree_type", school_degree_type);
        map.put("pic_urls", JSON.toJSONString(pic_urls));

        httpInfo.setUrl(APIS.URL_EDIT_HOUSE);
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
        map.put("type", 51);
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
            case HOUSE_CONFIG:
                type = new TypeReference<BaseResultInfo<List<HouseConfigBean>>>(){}.getType();
                break;
            case AREA_LIST:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>(){}.getType();
                break;
            case UPLOAD_PIC:
            case UPLOAD_CERT:
                type = new TypeReference<BaseResultInfo<FileUploadResultBean>>() {
                }.getType();
                break;
            case PUBLISH:
            case EDIT:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case IS_CHARGE:
                type = new TypeReference<BaseResultInfo<ChargeResultBean>>() {
                }.getType();
                break;
            case RENT_TYPE_F:
            case RENT_TYPE:
                type = new TypeReference<BaseResultInfo<HouseMoreBean>>(){}.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case HOUSE_CONFIG:
                view.dismiss();
                List<HouseConfigBean> configBeans = (List<HouseConfigBean>) result.getData();
                if (configBeans!=null) {
                    view.loadHouseConfigSuccess(configBeans);
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
                FileUploadResultBean picData = (FileUploadResultBean) result.getData();
                if (picData!=null) {
                    img_urls.addAll(picData.getFileUrls());
                    view.uploadPicSuccess(img_urls);
                } else {
                    view.dismiss();
                    view.showTips("发布失败",0);
                }
                break;
            case UPLOAD_CERT:
                FileUploadResultBean certData = (FileUploadResultBean) result.getData();
                if (certData!=null) {
                    List<String> fileUrls = certData.getFileUrls();
                    if (fileUrls.size()>0) {
                        view.uploadCertPicSuccess(fileUrls.get(0));
                    } else {
                        view.dismiss();
                        view.showTips("发布失败",0);
                    }
                } else {
                    view.dismiss();
                    view.showTips("发布失败",0);
                }
                break;
            case PUBLISH:
                view.dismiss();
                String resultData = (String) result.getData();
                view.publishSuccess(resultData);
                view.showTips("发布成功",0);
                break;
            case EDIT:
                view.dismiss();
                view.publishSuccess("");
                view.showTips("修改成功",0);
                break;
            case IS_CHARGE:
                view.dismiss();
                ChargeResultBean chargeResultBean = (ChargeResultBean) result.getData();
                if (chargeResultBean!=null) {
                    view.isChargeResult(chargeResultBean);
                }
                break;
            case RENT_TYPE:
//                view.dismiss();
//                HouseMoreBean moreBean = (HouseMoreBean) result.getData();
//                if (moreBean!=null) {
//                    List<Map<String,Object>> maps = new ArrayList<>();
//                    List<HouseMoreBean.HouseTypeListBean> house_type_list = moreBean.getHouse_type_list();
//                    if (house_type_list!=null) {
//                        for (HouseMoreBean.HouseTypeListBean houseTypeListBean : house_type_list) {
//                            Map<String,Object> map = new HashMap<>();
//                            map.put("id",houseTypeListBean.getHouse_type_id());
//                            map.put("text",houseTypeListBean.getHouse_type_name());
//                            maps.add(map);
//                        }
//                    }
//                    view.loadRentTypeDataSuccess(maps);
//                }
                break;
            case RENT_TYPE_F:
//                view.dismiss();
//                HouseMoreBean moreBeanF = (HouseMoreBean) result.getData();
//                view.loadRentTypeDataSuccessF(moreBeanF);
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,1);
    }
}
