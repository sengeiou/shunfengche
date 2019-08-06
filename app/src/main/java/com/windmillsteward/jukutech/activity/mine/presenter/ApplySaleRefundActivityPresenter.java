package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.mine.activity.ApplySaleRefundActivityView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.FileUploadResultBean;
import com.windmillsteward.jukutech.bean.RefoundReasonListBean;
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
 * Created by Administrator on 2018/4/21 0021.
 */

public class ApplySaleRefundActivityPresenter extends BaseNetModelImpl {

    private static final int UPLOAD_PIC = 1;
    private static final int APPLY = 2;
    private static final int APPLY_REASON_LIST = 3;

    private ApplySaleRefundActivityView view;

    public ApplySaleRefundActivityPresenter(ApplySaleRefundActivityView view) {
        this.view = view;
    }

    public void loadAplyReasonData() {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, APPLY_REASON_LIST);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        httpInfo.setUrl(APIS.URL_APPLY_REASON_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    public void loadNumberData(int number) {
        List<Map<String,Object>> maps = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",i+1);
            map.put("text",i+1+"");
            maps.add(map);
        }
        view.loadNumberDataSuccess(maps);
    }

    // 退款途径：【1：退到钱包，2：原路退回】
    public void loadRefundWayData() {
        List<Map<String,Object>> maps = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("text","退到钱包");
        maps.add(map);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("id",2);
        map1.put("text","原路退回");
        maps.add(map1);
        view.loadRefundWayDataSuccess(maps);
    }

    /**
     * 上传车id
     */
    public void uploadVehiclePic(String pic_path) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, UPLOAD_PIC);
        HttpInfo httpInfo = new HttpInfo();
        httpInfo.setUrl(APIS.URL_FILE_UPLOAD);
        List<KeyValue> list = new ArrayList<>();
        list.add(new KeyValue("file", new File(pic_path)));
        dataLoader.uploadFiles(httpInfo, list);
    }

    public void apply(String access_token,String order_sn,int order_commodity_id,int commodity_id,int commodity_num,int type,int reason_id,int refund_way,
                      String user_remark,List<String> credentials_urls) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, APPLY);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token",access_token);
        map.put("order_sn",order_sn);
        map.put("order_commodity_id",order_commodity_id);
        map.put("commodity_id",commodity_id);
        map.put("commodity_num",commodity_num);
        map.put("type",type);
        map.put("reason_id",reason_id);
        map.put("refund_way",refund_way);
        map.put("user_remark",user_remark);
        map.put("credentials_urls", JSON.toJSONString(credentials_urls));
        httpInfo.setUrl(APIS.URL_APPLY_SALE_REFUND);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case UPLOAD_PIC:
                type = new TypeReference<BaseResultInfo<FileUploadResultBean>>() {
                }.getType();
                break;
            case APPLY:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case APPLY_REASON_LIST:
                type = new TypeReference<BaseResultInfo<List<RefoundReasonListBean>>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case UPLOAD_PIC:
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
            case APPLY:
                view.applySuccess();
                break;
            case APPLY_REASON_LIST:
                view.dismiss();
                List<RefoundReasonListBean> data = (List<RefoundReasonListBean>) result.getData();
                if (data!=null) {
                    List<Map<String,Object>> maps = new ArrayList<>();
                    for (RefoundReasonListBean datum : data) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",datum.getReason_id());
                        map.put("text",datum.getName());
                        maps.add(map);
                    }
                    view.loadAplyReasonDataSuccess(maps);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        view.showTips(msg,0);
    }
}
