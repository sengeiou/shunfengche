package com.windmillsteward.jukutech.base.net;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.FileUploadResultBean;
import com.windmillsteward.jukutech.interfaces.APIS;
import com.windmillsteward.jukutech.utils.http.DataCallBack;
import com.windmillsteward.jukutech.utils.http.DataLoader;
import com.windmillsteward.jukutech.utils.http.HttpInfo;

import org.xutils.common.Callback;
import org.xutils.common.util.KeyValue;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date: on 2018/10/5
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class NetUtil {
    private DataCallBack callBackData;
    private String url;
    private Map<String, Object> map = new HashMap<>();
    private List<Callback.Cancelable> calls;
    private List<String> pic_path;

    public NetUtil setPic_path(List<String> pic_path) {
        this.pic_path = pic_path;
        return this;
    }

    public NetUtil setCallBackData(DataCallBack callBackData) {
        this.callBackData = callBackData;
        return this;
    }

    public NetUtil setCalls(List<Callback.Cancelable> calls) {
        this.calls = calls;
        return this;
    }

    public NetUtil setUrl(String url) {
        this.url = url;
        return this;
    }

    public NetUtil addParams(String key, String value) {
        map.put(key, value);
        return this;
    }

    public Callback.Cancelable buildPost() {
        DataLoader dataLoader = new DataLoader(callBackData, -1);
        HttpInfo httpInfo = new HttpInfo();
        if (TextUtils.isEmpty(url))
            throw new RuntimeException("url 不能为空");
        httpInfo.setUrl(url);
        httpInfo.setParams(map);
        Callback.Cancelable cancelable = dataLoader.httpPost(httpInfo);
        if (calls != null) {
            calls.add(cancelable);
        }
        return cancelable;
    }

    public Callback.Cancelable buildGet() {
        DataLoader dataLoader = new DataLoader(callBackData, -1);
        HttpInfo httpInfo = new HttpInfo();
        if (TextUtils.isEmpty(url))
            throw new RuntimeException("url 不能为空");
        httpInfo.setUrl(url);
        httpInfo.setParams(map);
        Callback.Cancelable cancelable = dataLoader.httpGet(httpInfo);
        if (calls != null) {
            calls.add(cancelable);
        }
        return cancelable;
    }

    public Callback.Cancelable buildUploadFile(final OnPicsUploadSuccessListener listener) {
        final List<String> pis_urls = new ArrayList<>();
        DataLoader dataLoader = new DataLoader(new BaseNewNetModelimpl<FileUploadResultBean>() {
            @Override
            protected void onFail(int type, String msg) {
                if (listener != null) {
                    listener.onPicsUploadFail(msg);
                }
            }

            @Override
            protected void onSuccess(int code, BaseResultInfo<FileUploadResultBean> respnse, String source) {
                if (respnse.getData() != null) {
                    if (respnse.getData().getFileUrls() != null)
                        pis_urls.addAll(respnse.getData().getFileUrls());
                    if (listener != null) {
                        listener.onPicsUploadSuccess(pis_urls);
                    }
                } else {
                    if (listener != null) {
                        listener.onPicsUploadFail("请求超时，请稍后再试");
                    }
                }
            }

            @Override
            protected Type getType() {
                return new TypeToken<BaseResultInfo<FileUploadResultBean>>() {
                }.getType();
            }
        }, -1);
        HttpInfo httpInfo = new HttpInfo();
        if (TextUtils.isEmpty(url))
            httpInfo.setUrl(APIS.URL_FILE_UPLOAD);
        else
            httpInfo.setUrl(url);
        if (pic_path == null || pic_path.isEmpty())
            throw new RuntimeException("pic_path 不能为空");

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
        Callback.Cancelable cancelable = dataLoader.uploadFiles(httpInfo, list);
        if (calls != null) {
            calls.add(cancelable);
        }
        return cancelable;
    }

    public interface OnPicsUploadSuccessListener {
        void onPicsUploadSuccess(List<String> pics);

        void onPicsUploadFail(String msg);
    }
}
