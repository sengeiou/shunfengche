package com.windmillsteward.jukutech.activity.mine.presenter;

import com.alibaba.fastjson.TypeReference;
import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.activity.mine.activity.EditMyInfoView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 个人信息操作类
 */

public class EditMyInfoImpl extends BaseNetModelImpl {
    private final int EDIT_MY_INFO = 1;
    private EditMyInfoView view;

    private String[] sex_module = new String[]{"男","女"};

    public EditMyInfoImpl(EditMyInfoView view) {
        this.view = view;
    }

    /**
     * 编辑个人信息
     */
    public void editMyInfo(String key, String value) {
        DataLoader dataLoader = new DataLoader(this, EDIT_MY_INFO);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new TreeMap<String, Object>();
        map.put("access_token", Hawk.get(Define.ACCESS_TOKEN));
        map.put(key, value);
        httpInfo.setUrl(APIS.URL_EDIT_MY_INFO);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 加载托管模块 0保密1男2女
     */
    public void loadSexModule(){
        List<Map<String,Object>> maps = new ArrayList<>();
        for (int i = 0; i < sex_module.length; i++) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",i+1);
            map.put("text",sex_module[i]);
            maps.add(map);
        }
        view.loadSexModuleSuccess(maps);
    }

//    /**
//     * 上传图片
//     */
//    public void uploadPic(List<String> pic_path) {
//        view.showDialog("");
//        DataLoader dataLoader = new DataLoader(this, UPLOAD_PIC);
//        HttpInfo httpInfo = new HttpInfo();
//        httpInfo.setUrl(APIS.URL_FILE_UPLOAD);
//        List<KeyValue> list = new ArrayList<>();
//        if (pic_path != null && pic_path.size() > 0) {
//            for (int i = 0; i < pic_path.size(); i++) {
//                list.add(new KeyValue("file" + i, new File(pic_path.get(i))));
//            }
//        }
//        dataLoader.uploadFiles(httpInfo, list);
//    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case EDIT_MY_INFO:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        view.dismiss();
        switch (action) {
            case EDIT_MY_INFO:
                view.editSuccess();
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        view.dismiss();
        switch (action) {
            case EDIT_MY_INFO:
                view.editFailed(code, msg);
                break;
        }
    }
}