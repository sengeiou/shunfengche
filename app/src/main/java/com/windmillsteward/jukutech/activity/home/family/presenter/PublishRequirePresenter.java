package com.windmillsteward.jukutech.activity.home.family.presenter;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.windmillsteward.jukutech.activity.home.family.activity.PublishRequireView;
import com.windmillsteward.jukutech.base.BaseNetModelImpl;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.AddRequireResultBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.FileUploadResultBean;
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
 * 时间：2018/1/19
 * 作者：xjh
 */

public class PublishRequirePresenter extends BaseNetModelImpl {

    private final int LOAD_AREA = 1;
    private final int RELEASE = 2;
    private final int UPLOAD = 3;
    private final int EDIT = 4;
    private final int IS_CHARGE = 5;

    private PublishRequireView view;
    private List<String> image_urls=new ArrayList<>();

    public PublishRequirePresenter(PublishRequireView view) {
        this.view = view;
    }

    /**
     * 加载地区数据
     */
    public void loadAreaData(int second_area_id){
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, LOAD_AREA);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("second_area_id", second_area_id);
        httpInfo.setUrl(APIS.URL_THIRD_AREA_LIST);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 图片和视频至少有一样
     * 上传文件，这里的情况有：1.有视频没有图片；2.有视频有图片；3.没有视频有图片；
     * @param imageItems 图片集合
     * @param videoPath 视频路径
     * @param video_cover 视频封面路径
     */
    public void uploadFile(List<String> imageItems, String videoPath, String  video_cover) {
        if ((imageItems==null || imageItems.size()==0) && (videoPath==null || videoPath.length()==0)) {
            view.showTips("请选择图片或视频",0);
            return;
        }
        view.showDialog("发布中...");
        // 上传视频和封面
        if (videoPath!=null && videoPath.length()>0) {
            DataLoader dataLoader = new DataLoader(this, UPLOAD);
            HttpInfo httpInfo = new HttpInfo();
            httpInfo.setUrl(APIS.URL_FILE_UPLOAD);
            List<KeyValue> list = new ArrayList<>();
            list.add(new KeyValue("file1", new File(videoPath)));
            list.add(new KeyValue("file2", new File(video_cover)));

            if (imageItems!=null && imageItems.size()>0) {
                for (int i = 0; i < imageItems.size(); i++) {
                    list.add(new KeyValue("file1"+i, new File(imageItems.get(i))));
                }
            }

            dataLoader.uploadFiles(httpInfo, list);
        } else {
            DataLoader dataLoader = new DataLoader(this, UPLOAD);
            HttpInfo httpInfo = new HttpInfo();
            httpInfo.setUrl(APIS.URL_FILE_UPLOAD);
            List<KeyValue> list = new ArrayList<>();

            if (imageItems!=null && imageItems.size()>0) {
                for (int i = 0; i < imageItems.size(); i++) {
                    list.add(new KeyValue("file1"+i, new File(imageItems.get(i))));
                }
            }
            if (videoPath!=null && videoPath.length()>0) {
                list.add(new KeyValue("file1", new File(videoPath)));
                list.add(new KeyValue("file2", new File(video_cover)));
            }
            dataLoader.uploadFiles(httpInfo, list);
        }

    }

    public void edit_uploadFile(List<String> imageItems, String video_path, String  video_cover_path,String video_cover_url,String video_url) {
        view.showDialog("发布中...");

        image_urls.clear();

        DataLoader dataLoader = new DataLoader(this, UPLOAD);
        HttpInfo httpInfo = new HttpInfo();
        httpInfo.setUrl(APIS.URL_FILE_UPLOAD);
        List<KeyValue> list = new ArrayList<>();

        if (!TextUtils.isEmpty(video_path)) {  // 更换了新视频，就不用管有没有旧视频了
            list.add(new KeyValue("file1", new File(video_path)));
            list.add(new KeyValue("file2", new File(video_cover_path)));

            if (imageItems!=null && imageItems.size()>0) {
                for (int i = 0; i < imageItems.size(); i++) {
                    if (imageItems.get(i).contains("http")) {
                        image_urls.add(imageItems.get(i));
                    } else {
                        list.add(new KeyValue("file1"+i, new File(imageItems.get(i))));
                    }
                }
            }
        } else {  // 没有新视频
            if (!TextUtils.isEmpty(video_url)){  // 有原来的视频
                image_urls.add(video_url);
                image_urls.add(video_cover_url);
                if (imageItems!=null && imageItems.size()>0) {
                    for (int i = 0; i < imageItems.size(); i++) {
                        if (imageItems.get(i).contains("http")) {
                            image_urls.add(imageItems.get(i));
                        } else {
                            list.add(new KeyValue("file1"+i, new File(imageItems.get(i))));
                        }
                    }
                }
            } else {
                if (imageItems!=null && imageItems.size()>0) {
                    for (int i = 0; i < imageItems.size(); i++) {
                        if (imageItems.get(i).contains("http")) {
                            image_urls.add(imageItems.get(i));
                        } else {
                            list.add(new KeyValue("file1"+i, new File(imageItems.get(i))));
                        }
                    }
                } else {
                    view.showTips("请选择图片或视频",0);
                    return;
                }
            }
        }

        dataLoader.uploadFiles(httpInfo, list);
    }

    /**
     * 视频上传成功，发布
     */
    public void addRequire(String access_token,int require_class_id, String title, String price, String description,
                           int second_area_id, int third_area_id,
                           String longitude, String latitude, List<String> pic_urls, List<String> video_urls,
                           String video_cover) {

        DataLoader dataLoader = new DataLoader(this, RELEASE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("require_class_id", require_class_id);
        map.put("title", title);
        map.put("price", price);
        map.put("description", description);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        map.put("pic_urls", JSON.toJSONString(pic_urls));
        map.put("video_urls", JSON.toJSONString(video_urls));
        map.put("video_cover", video_cover);
        httpInfo.setUrl(APIS.URL_ADD_REQUIRE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 视频上传成功，发布
     */
    public void edit(int require_id,String access_token,int require_class_id, String title, String price, String description,
                           int second_area_id, int third_area_id,
                           String longitude, String latitude, List<String> pic_urls, List<String> video_urls,
                           String video_cover) {
        view.showDialog("");
        DataLoader dataLoader = new DataLoader(this, EDIT);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("require_id", require_id);
        map.put("access_token", access_token);
        map.put("require_class_id", require_class_id);
        map.put("title", title);
        map.put("price", price);
        map.put("description", description);
        map.put("second_area_id", second_area_id);
        map.put("third_area_id", third_area_id);
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        map.put("pic_urls", JSON.toJSONString(pic_urls));
        map.put("video_urls", JSON.toJSONString(video_urls));
        map.put("video_cover", video_cover);
        httpInfo.setUrl(APIS.URL_EDIT_REQUIRE_1);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    /**
     * 判断是否收费
     * @param access_token
     */
    public void isCharge(String access_token,int relate_id) {
        view.showDialog("");

        DataLoader dataLoader = new DataLoader(this, IS_CHARGE);
        HttpInfo httpInfo = new HttpInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("type", 42);
        map.put("relate_id", relate_id);
        httpInfo.setUrl(APIS.URL_IS_CHARGE);
        httpInfo.setParams(map);
        dataLoader.httpPost(httpInfo);
    }

    @Override
    protected Type getDataType(int action) {
        Type type = null;
        switch (action) {
            case LOAD_AREA:
                type = new TypeReference<BaseResultInfo<List<ThirdAreaBean>>>() {
                }.getType();
                break;
            case UPLOAD:
                type = new TypeReference<BaseResultInfo<FileUploadResultBean>>() {
                }.getType();
                break;
            case RELEASE:
                type = new TypeReference<BaseResultInfo<AddRequireResultBean>>() {
                }.getType();
                break;
            case EDIT:
                type = new TypeReference<BaseResultInfo<String>>() {
                }.getType();
                break;
            case IS_CHARGE:
                type = new TypeReference<BaseResultInfo<ChargeResultBean>>() {
                }.getType();
                break;
        }
        return type;
    }

    @Override
    protected void onDataCallback(int code, int action, BaseResultInfo result, String sourceData) {
        switch (action) {
            case LOAD_AREA:
                view.dismiss();
                List<Map<String,Object>> maps = new ArrayList<>();
                List<ThirdAreaBean> list = (List<ThirdAreaBean>) result.getData();
                if (list!=null) {
                    for (ThirdAreaBean listBean : list) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("id",listBean.getThird_area_id());
                        map.put("text",listBean.getThird_area_name());
                        maps.add(map);
                    }
                }

                view.loadAreaDataSuccess(maps);
                break;
            case UPLOAD:
                view.dismiss();
                FileUploadResultBean data = (FileUploadResultBean) result.getData();
                if (data!=null) {
                    List<String> fileUrls = data.getFileUrls();
                    if (fileUrls!=null) {
                        fileUrls.addAll(image_urls);
                    }
                    view.uploadFileSuccess(fileUrls);
                }
                break;
            case RELEASE:
                view.dismiss();
                AddRequireResultBean resultData = (AddRequireResultBean) result.getData();
                if (resultData!=null) {
                    view.addRequireSuccess(resultData);
                    view.showTips("发布成功",0);
                } else {
                    view.showTips("发布失败，请重试",0);
                }
                break;
            case EDIT:
                view.dismiss();
                view.addRequireSuccess(null);
                view.showTips("保存成功",0);
                break;
            case IS_CHARGE:
                view.dismiss();
                ChargeResultBean chargeResultBean = (ChargeResultBean) result.getData();
                if (chargeResultBean!=null) {
                    view.isCharge(chargeResultBean);
                } else {
                    view.showTips("获取收费信息失败，请重试",0);
                }
                break;
        }
    }

    @Override
    protected void requestFailed(int code, int action, String msg, String sourceData) {
        switch (action){
            case LOAD_AREA:
                view.dismiss();
                break;
            case UPLOAD:
                view.dismiss();
                view.showTips("文件上传失败，请重试",0);
                break;
            case RELEASE:
                view.dismiss();
                view.showTips(msg,0);
                break;
            case EDIT:
                view.dismiss();
                view.showTips("保存失败",0);
                break;
            case IS_CHARGE:
                view.dismiss();
                view.showTips("获取收费信息失败，请重试",0);
                break;
        }
    }


}
