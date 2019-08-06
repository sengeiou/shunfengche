package com.windmillsteward.jukutech.utils.http;

import android.os.Message;
import android.text.TextUtils;

import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.base.KV;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.LogUtil;

import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.ProgressCallback;
import org.xutils.common.util.KeyValue;
import org.xutils.ex.HttpException;
import org.xutils.x;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网络请求 工具类
 *
 * @author zhuxian
 */
public class DataLoader {

    private DataCallBack callBackData = null;// 自定义的接口回调
    private int action = -1;// 发起网络请求的//到解析时要用到
    private Message msg = null;// 回调信息提示

    private String info;
    private long startTime;

    /**
     * @param callBackData 回调监听
     * @param action       请求标识
     */
    public DataLoader(DataCallBack callBackData, int action) {
        this.callBackData = callBackData;
        this.action = action;
        if (msg == null) {
            msg = new Message();
        }
    }

    /**
     * get请求方式
     * 加头
     *
     * @param httpInfo 网络请求信息实体
     */
    public Cancelable httpGet(HttpInfo httpInfo) {
        if (!NetUtil.isConnected()) {
            msg.what = ResultInfo.NO_NET;
            if (callBackData != null)
                callBackData.handlerData(msg, action, "");
            return null;
        }
        Map<String, String> header = httpInfo.getHeader();
        int user_id =(int) Hawk.get(Define.USER_ID, 0);
        if (header != null) {
            header.put("user_id", user_id+"");
            header.put("Connection", "close");
        } else {
            Map<String, String> headers = new HashMap<>();
            headers.put("user_id", user_id+"");
            headers.put("Connection", "close");
            httpInfo.setHeader(headers);
        }

        Map<String, Object> params = httpInfo.getParams();
        if (params != null) {
            String token = KV.get(Define.ACCESS_TOKEN);
            if (!TextUtils.isEmpty(token)) {
                params.put("access_token", token);
            }
        } else {
            params = new HashMap<>();
            String token = KV.get(Define.ACCESS_TOKEN);
            if (!TextUtils.isEmpty(token)) {
                params.put("access_token", token);
            }
            httpInfo.setParams(params);
        }
        info = httpInfo.toString() + "method：GET\n";
        startTime = System.currentTimeMillis();
        return x.http().get(HttpManage.getParams(httpInfo), resultCallback);// 发起请求并反回操作是否取消
    }

    /**
     * post请求方式
     * update: xjh: 添加请求头
     *
     * @param httpInfo 网络请求实体
     */
    public Cancelable httpPost(HttpInfo httpInfo) {
        if (!NetUtil.isConnected()) {
            msg.what = ResultInfo.NO_NET;
            if (callBackData != null)
                callBackData.handlerData(msg, action, "");
            return null;
        }
        Map<String, String> header = httpInfo.getHeader();

        if (header != null) {//如果请求头不为空，则直接把头部放进去
            header.put("user_id", String.valueOf(Hawk.get(Define.USER_ID, 0)));
            header.put("Connection", "close");
        } else {//如果请求头为空，说明没有set请求头
            Map<String, String> headers = new HashMap<>();
            headers.put("user_id", String.valueOf(Hawk.get(Define.USER_ID, 0)));
            headers.put("Connection", "close");
            httpInfo.setHeader(headers);
        }
        Map<String, Object> params = httpInfo.getParams();
        if (params != null) {
            String token = KV.get(Define.ACCESS_TOKEN);
            if (!TextUtils.isEmpty(token)) {
                params.put("access_token", token);
            }
        } else {
            params = new HashMap<>();
            String token = KV.get(Define.ACCESS_TOKEN);
            if (!TextUtils.isEmpty(token)) {
                params.put("access_token", token);
            }
            httpInfo.setParams(params);
        }
        info = httpInfo.toString() + "method：POST\n";
        startTime = System.currentTimeMillis();
        return x.http().post(HttpManage.postParams(httpInfo), resultCallback);// 发起请求
    }

    /**
     * 多文件上传
     * 加头
     *
     * @param httpInfo 携带url
     * @param files    文件
     */
    public Cancelable uploadFiles(HttpInfo httpInfo, List<KeyValue> files) {
        if (!NetUtil.isConnected()) {
            msg.what = ResultInfo.NO_NET;
            if (callBackData != null)
                callBackData.handlerData(msg, action, "");
            return null;
        }
        Map<String, String> header = httpInfo.getHeader();
        if (header != null) {
            header.put("user_id", String.valueOf(Hawk.get(Define.USER_ID, 0)));
        } else {
            Map<String, String> headers = new HashMap<>();
            headers.put("user_id", String.valueOf(Hawk.get(Define.USER_ID, 0)));
            httpInfo.setHeader(headers);
        }

        Map<String, Object> params = httpInfo.getParams();
        if (params != null) {
            String token = KV.get(Define.ACCESS_TOKEN);
            if (!TextUtils.isEmpty(token)) {
                params.put("access_token", token);
            }
        } else {
            params = new HashMap<>();
            String token = KV.get(Define.ACCESS_TOKEN);
            if (!TextUtils.isEmpty(token)) {
                params.put("access_token", token);
            }
            httpInfo.setParams(params);
        }

        Map<String, File> files1 = httpInfo.getFiles();
        if (files1 == null) {
            files1 = new HashMap<>();
            for (KeyValue file : files) {
                files1.put(file.key, null);
            }
            httpInfo.setFiles(files1);
        }

        info = httpInfo.toString() + "method：UploadFiles\n";
        startTime = System.currentTimeMillis();
        return x.http().post(HttpManage.uploadFiles(httpInfo, files), resultCallback);
    }


    /**
     * 请求结果回调监听
     */
    ProgressCallback<String> resultCallback = new ProgressCallback<String>() {
        @Override
        public void onSuccess(String arg0) {
            msg.what = ResultInfo.REQUEST_SUCESS;
            if (callBackData != null)
                callBackData.handlerData(msg, action, arg0);
            if (LogUtil.isDebug) {
                LogUtil.e(info + "time：" + (System.currentTimeMillis() - startTime) + "ms\nstatus：success" + "\ndata：" + arg0);
                LogUtil.e("\n***********************请求结束*************************");
            }
        }

        @Override
        public void onFinished() {

        }

        @Override
        public void onError(Throwable arg0, boolean isOnCallback) {
            arg0.printStackTrace();
            if (arg0 instanceof HttpException) { // 网络错误
                HttpException httpEx = (HttpException) arg0;
                int responseCode = httpEx.getCode();
                String responseMsg = httpEx.getMessage();
                String errorResult = httpEx.getResult();
                msg.what = ResultInfo.REQUEST_ERROR;
                if (callBackData != null)
                    callBackData.handlerData(msg, action, arg0.toString());
            } else { // 其他错误
                msg.what = ResultInfo.REQUEST_ELSE_ERROR;
                if (callBackData != null)
                    callBackData.handlerData(msg, action, arg0.toString());
            }
            if (LogUtil.isDebug) {
                LogUtil.e(info + "time：" + (System.currentTimeMillis() - startTime) + "ms\nstatus：error" + "\ndata：" + msg);
                LogUtil.e("\n***********************请求结束*************************");
            }
        }

        @Override
        public void onCancelled(CancelledException arg0) {
            arg0.printStackTrace();
        }

        @Override
        public void onLoading(long total, long current, boolean isDownloading) {//进度条会用到，暂时不会用到

        }

        @Override
        public void onStarted() {

        }

        @Override
        public void onWaiting() {
        }
    };

}
