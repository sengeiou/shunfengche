package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.AuthenticationView;
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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述：我的钱包实现类
 * 时间：2018/3/5
 * 作者：cyq
 */
public class AuthenticationPresenter extends BaseNetModelImpl {

    private static final int PERSONAL = 1;
    private static final int COMPANY = 2;
    private static final int UPLOAD_PIC = 3;

    private AuthenticationView view;

    public AuthenticationPresenter(AuthenticationView view) {
        this.view = view;
    }

    /**
     * 个人认证
     */
    public void personalAuthentication(String access_token,String true_name,String id_card_no ,String id_card_front_url,String id_card_back_url ) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, PERSONAL);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("true_name", true_name);
        map.put("id_card_no", id_card_no);
        map.put("id_card_front_url", id_card_front_url);
        map.put("id_card_back_url", id_card_back_url);
        httpInfo.setUrl(APIS.URL_PERSONAL_AUTHENTICATION);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }
    /**
     * 企业认证
     */
    public void companyAuthentication(String access_token,String enterprise_name,String enterprise_address ,String corporation_name,String corporation_id_card_no,
    String business_licence_url,String corporation_id_card_front_url,String corporation_id_card_back_url ) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, COMPANY);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("enterprise_name", enterprise_name);
        map.put("enterprise_address", enterprise_address);
        map.put("corporation_name", corporation_name);
        map.put("corporation_id_card_no", corporation_id_card_no);
        map.put("business_licence_url", business_licence_url);
        map.put("corporation_id_card_front_url", corporation_id_card_front_url);
        map.put("corporation_id_card_back_url", corporation_id_card_back_url);
        httpInfo.setUrl(APIS.URL_COMPANY_AUTHENTICATION);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 上传图片
     */
    public void uploadPic(List<String> pic_path) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, UPLOAD_PIC);
        HttpInfo httpInfo = new HttpInfo();
        httpInfo.setUrl(APIS.URL_FILE_UPLOAD);
        List<KeyValue> list = new ArrayList<>();
        if (pic_path != null && pic_path.size() > 0) {
            for (int i = 0; i < pic_path.size(); i++) {
                list.add(new KeyValue("file" + i, new File(pic_path.get(i))));
            }
        }
        dataLoader.uploadFiles(httpInfo, list);
    }


    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case PERSONAL:
            case COMPANY:
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
            case PERSONAL:
            case COMPANY:
                view.dismiss();
               view.authenticationSuccess();
                break;
            case UPLOAD_PIC:
                view.dismiss();
                FileUploadResultBean picData = (FileUploadResultBean) result.getData();
                if (picData != null) {
                    List<String> fileUrls = picData.getFileUrls();
                    view.uploadPicSuccess(fileUrls);
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
            case PERSONAL:
            case COMPANY:
                view.dismiss();
                view.authenticationFailed(code,msg);
                break;
            case UPLOAD_PIC:
                view.showTips("操作失败", 1);
                break;
        }
    }
}
