package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.AddFundsTrusteeshipView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.FileUploadResultBean;
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
 * 描述：我的钱包实现类
 * 时间：2018/3/5
 * 作者：cyq
 */
public class AddFundsTrusteeshipPresenter extends BaseNetModelImpl {

    private static final int ADD_FUNDS_TRUSTEESHIP = 1;
    private static final int UPLOAD_PIC = 2;

    private List<String> pis_urls = new ArrayList<>();

    private AddFundsTrusteeshipView view;

    //托管模块 1人才驿站 2智慧家庭 3思想智库 4房屋租售 5住宿旅游 6大健康 7法律专家 8资本管理 9名优特产	10车辆买卖
    private String[] trusteeship_module = new String[]{"人才驿站","智慧生活","思想智库","房屋租售","住宿旅游","大健康","法律专家","资本管理","名优特产","汽车服务"};


    public AddFundsTrusteeshipPresenter(AddFundsTrusteeshipView view) {
        this.view = view;
    }

    /**
     * 添加资金托管
     */
    public void addFundsTrusteeship(String access_token, int trusteeship_module, String trusteeship_id, String trusteeship_money, String start_valid_time,
                                    String end_valid_time, String mobile_no, String contact_person, String describe, String trusteeship_voucher) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, ADD_FUNDS_TRUSTEESHIP);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("trusteeship_module", trusteeship_module);
        map.put("trusteeship_id", trusteeship_id);
        map.put("trusteeship_money", trusteeship_money);
        map.put("start_valid_time", start_valid_time);
        map.put("end_valid_time", end_valid_time);
        map.put("mobile_no", mobile_no);
        map.put("contact_person", contact_person);
        map.put("describe", describe);
        map.put("trusteeship_voucher", trusteeship_voucher);
        httpInfo.setUrl(APIS.URL_ADD_TRUSTEESHIP);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    /**
     * 上传图片
     */
    public void uploadPic(List<String> pic_path) {
        pis_urls.clear();
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, UPLOAD_PIC);
        HttpInfo httpInfo = new HttpInfo();
        httpInfo.setUrl(APIS.URL_FILE_UPLOAD);
        List<KeyValue> list = new ArrayList<>();
        if (pic_path != null && pic_path.size() > 0) {
            for (int i = 0; i < pic_path.size(); i++) {
                if (pic_path.get(i).contains("http")) {
                    pis_urls.add(pic_path.get(i));
                } else {
                    list.add(new KeyValue("file" + i, new File(pic_path.get(i))));
                }
            }
        }
        dataLoader.uploadFiles(httpInfo, list);
    }

    /**
     * 加载托管模块 1人才驿站 2智慧家庭 3思想智库 4房屋租售 5住宿旅游
     */
    public void loadTrusteeshipModule(){
        List<Map<String,Object>> maps = new ArrayList<>();
        for (int i = 0; i < trusteeship_module.length; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",i+1);
            map.put("text",trusteeship_module[i]);
            maps.add(map);
        }
        view.loadTrusteeshipModuleSuccess(maps);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case ADD_FUNDS_TRUSTEESHIP:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case UPLOAD_PIC:
                type = new TypeReference<BaseResultInfo<FileUploadResultBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case ADD_FUNDS_TRUSTEESHIP:
                view.dismiss();
                view.addAddFundsTrusteeshipSuccess();
                break;
            case UPLOAD_PIC:
                FileUploadResultBean picData = (FileUploadResultBean) result.getData();
                if (picData != null) {
                    List<String> fileUrls = picData.getFileUrls();
                    pis_urls.addAll(fileUrls);
                    view.uploadPicSuccess(pis_urls);
                } else {
                    view.dismiss();
                    view.showTips("操作失败", 0);
                }
                break;

        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action) {
            case ADD_FUNDS_TRUSTEESHIP:
                view.dismiss();
                view.addAddFundsTrusteeshipFailed(code, msg);
                break;
            case UPLOAD_PIC:
                view.dismiss();
                view.uploadPicFailed(code,msg);
                break;
        }
    }
}
