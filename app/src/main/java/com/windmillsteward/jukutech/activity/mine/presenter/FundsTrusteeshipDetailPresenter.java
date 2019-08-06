package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.FundsTrusteeshipDetailView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.FileUploadResultBean;
import com.windmillsteward.jukutech.bean.FundsTrusteeshipDetailBean;
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
 * 描述：托管详情实现类
 * 时间：2018/3/8
 * 作者：cyq
 */

public class FundsTrusteeshipDetailPresenter extends BaseNetModelImpl {

    private static final int DETAIL = 1;
    private static final int CANCEL = 2;
    private static final int REPORT = 3;
    private static final int RETURN_REPORT = 4;
    private static final int UPLOAD_PIC = 5;

    private FundsTrusteeshipDetailView view;

    private List<String> pis_urls = new ArrayList<>();

    public FundsTrusteeshipDetailPresenter(FundsTrusteeshipDetailView view) {
        this.view = view;
    }

    /**
     * 获取托管详情
     */
    public void getDetail(String access_token, int id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, DETAIL);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("id", id);
        httpInfo.setUrl(APIS.URL_TRUSTEESHIP_DETAIL);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 取消托管(取消申请)
     *
     * @param access_token
     * @param id
     */
    public void cancelTrusteeship(String access_token, int id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, CANCEL);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("id", id);
        httpInfo.setUrl(APIS.URL_CANCEL_TRUSTEESHIP);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }
    /**
     * 撤回举报
     *
     * @param access_token
     * @param id
     */
    public void returnTrusteeship(String access_token, int id) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, RETURN_REPORT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("id", id);
        httpInfo.setUrl(APIS.URL_RETURN_REPORT_TRUSTEESHIP);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }


    /**
     * 举报资金托管
     * @param access_token
     * @param id
     * @param consultation_reason
     * @param report_voucher
     */
    public void reportTrusteeship(String access_token,int id,String consultation_reason,String report_voucher){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, REPORT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("id", id);
        map.put("consultation_reason", consultation_reason);
        map.put("report_voucher", report_voucher);
        httpInfo.setUrl(APIS.URL_REPORT_TRUSTEESHIP);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }
    /**
     * 同意退款
     * @param access_token
     * @param id
     */
    public void agreeRefund(String access_token, int id){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, REPORT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("id", id);
        httpInfo.setUrl(APIS.URL_AGREE_REFUND);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 拒绝退款
     * @param access_token
     * @param id
     */
    public void refuseRefund(String access_token, int id){
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, REPORT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<>();
        map.put("access_token", access_token);
        map.put("id", id);
        httpInfo.setUrl(APIS.URL_REFUSE_REFUND);
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



    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case DETAIL:
                type = new TypeReference<BaseResultInfo<FundsTrusteeshipDetailBean>>() {
                }.getType();
                break;
            case RETURN_REPORT:
            case CANCEL:
            case REPORT:
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
            case DETAIL:
                view.dismiss();
                FundsTrusteeshipDetailBean data = (FundsTrusteeshipDetailBean) result.getData();
                if (data != null) {
                    view.getFundsTrusteeshipDetailSuccess(data);
                }
                break;
            case CANCEL:
            case RETURN_REPORT:
            case REPORT:
                view.dismiss();
                view.operateSuccess();
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
            case DETAIL:
                view.dismiss();
                view.getFundsTrusteeshipDetailFailed(code, msg);
                break;
            case CANCEL:
            case RETURN_REPORT:
            case REPORT:
                view.dismiss();
                view.operateFailed(code, msg);
                break;
            case UPLOAD_PIC:
                view.dismiss();
                view.uploadPicFailed(code,msg);
                break;
        }
    }
}
