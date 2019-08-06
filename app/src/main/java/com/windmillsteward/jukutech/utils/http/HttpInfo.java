package com.windmillsteward.jukutech.utils.http;

import android.text.TextUtils;

import java.io.File;
import java.util.Map;

/**
 * 请求实体
 */
public class HttpInfo {
    private String url = "";
    private Map<String, Object> params = null;
    private Map<String, File> files = null;
    private Map<String, String> headers = null;

    public Map<String, File> getFiles() {
        return files;
    }

    public void setFiles(Map<String, File> files) {
        this.files = files;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Map<String, String> getHeader() {
        return headers;
    }

    public void setHeader(Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public String toString() {
        StringBuilder stringBuffer = new StringBuilder();
        stringBuffer.append("\n***********************请求开始*************************\n");
        stringBuffer.append("url：" + url + "\n");
        Map<String, Object> params = getParams();
        if (params != null) {
            StringBuilder paramStr = new StringBuilder();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                paramStr.append(entry.getKey() + "=" + entry.getValue() + ",");
            }
            if (!TextUtils.isEmpty(paramStr)) {
                stringBuffer.append("params：" + paramStr.substring(0, paramStr.length() - 1) + "\n");
            } else {
                stringBuffer.append("params：" + paramStr.toString() + "\n");
            }
        } else {
            stringBuffer.append("params：none" + "\n");
        }

        if (headers != null) {
            StringBuilder headerStr = new StringBuilder();
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                headerStr.append(entry.getKey() + "=" + entry.getValue() + ",");
            }
            if (!TextUtils.isEmpty(headerStr)) {
                stringBuffer.append("headers：" + headerStr.substring(0, headerStr.length() - 1) + "\n");
            } else {
                stringBuffer.append("headers：" + headerStr.toString() + "\n");
            }
        } else {
            stringBuffer.append("headers：none" + "\n");
        }

        if (files != null) {
            StringBuilder fileStr = new StringBuilder();
            for (Map.Entry<String, File> entry : files.entrySet()) {
                fileStr.append(entry.getKey() + "=" + entry.getValue() + ",");
            }
            if (!TextUtils.isEmpty(fileStr)) {
                stringBuffer.append("files：" + fileStr.substring(0, fileStr.length() - 1) + "\n");
            } else {
                stringBuffer.append("files：" + fileStr.toString() + "\n");
            }
        } else {
            stringBuffer.append("files：none" + "\n");
        }

        return stringBuffer.toString();
    }
}
