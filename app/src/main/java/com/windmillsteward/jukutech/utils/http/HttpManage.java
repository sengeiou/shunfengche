package com.windmillsteward.jukutech.utils.http;

import android.text.TextUtils;

import com.orhanobut.hawk.Hawk;
import com.windmillsteward.jukutech.interfaces.Define;

import org.xutils.common.util.KeyValue;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.http.body.MultipartBody;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Http管理类
 */
public class HttpManage {
	/**
	 * 获取请求信息体post方式
	 *
	 * @param httpInfo 请求的参数
	 * @return http请求体
	 */
	public static RequestParams postParams(HttpInfo httpInfo) {
		RequestParams params = new RequestParams(httpInfo.getUrl());// 设置请求参数
		Map<String, Object> parMap = httpInfo.getParams();
		Map<String, File> files = httpInfo.getFiles();
		Map<String, String> header = httpInfo.getHeader();
		setParamsConfig(params);
		if (header == null){
			Map<String, String> headersMap = new HashMap<>();
			headersMap.put("user_id", String.valueOf(Hawk.get(Define.USER_ID, 0)));
			headersMap.put("Connection", "close");
			httpInfo.setHeader(headersMap);
		}
//		int user_id = Hawk.get(Define.USER_ID, 0);
//		if (user_id != 0){
//			params.setHeader("user_id", user_id+"");
//		}
		if (parMap != null) {// 加载请求参数
			for (String name : parMap.keySet()) {
				params.addParameter(name, parMap.get(name));
			}
		}
		if (files != null) {
			params.setMultipart(true);
			for (String name : files.keySet()) {
				params.addBodyParameter(name, files.get(name), "multipart/form-data");
			}
		}
		Map<String, String> headers = httpInfo.getHeader();
		if (headers!=null) {
			for (String name : headers.keySet()) {
				params.setHeader(name,headers.get(name));
			}
			LogUtil.e("================"+headers.toString());
		}
		return params;
	}

	/**
	 * 获取请求信息体 get方式
	 * @param httpInfo 请求的参数
	 * @return http请求体
	 */
	public static RequestParams getParams(HttpInfo httpInfo) {
		RequestParams params = new RequestParams(httpInfo.getUrl());// 设置请求参数
		Map<String, Object> parMap = httpInfo.getParams();
		setParamsConfig(params);
		if (parMap != null) {// 加载请求参数
			for (String name : parMap.keySet()) {
				params.addQueryStringParameter(name,
						String.valueOf(parMap.get(name)));
			}
		}
		return params;
	}

	/**
	 * 获取设置请求信息 get方式 下载
	 *
	 * @param filePath 下载保存的路径
	 * @param httpInfo 请求的参数
	 * @return http请求实体
	 */
	public static RequestParams downLoadParams(HttpInfo httpInfo, String filePath) {
		RequestParams params = new RequestParams(httpInfo.getUrl());
		params.setAutoResume(true);// 设置断点续传
		params.setAutoRename(true);// 是否根据头信息自动命名文件
		Map<String, Object> parMap = httpInfo.getParams();
		params.setCharset("utf-8");// 设置请求编码
		if (!TextUtils.isEmpty(filePath)) {//设置文件保存路径
			params.setSaveFilePath(filePath);
		}
		if (parMap != null) {// 加载请求参数
			for (String name : parMap.keySet()) {
				params.setSaveFilePath(String.valueOf(parMap.get(name)));
			}
		}
		return params;
	}

	/**
	 * 上传多文件
	 * @param httpInfo 参数携带
	 * @return 返回请参数
	 */
	public static RequestParams uploadFiles(HttpInfo httpInfo, List<KeyValue> list) {
		RequestParams params = new RequestParams(httpInfo.getUrl());
		MultipartBody body=new MultipartBody(list,"UTF-8");
        params.setRequestBody(body);
		params.setMultipart(true);

		return params;
	}

	/**
	 * params统一配置
	 *
	 * @param params
	 */
	private static void setParamsConfig(RequestParams params) {
		params.setCharset("utf-8");// 设置请求编码
		params.setConnectTimeout(30 * 1000);//请求超时
		params.setMaxRetryCount(1);//设置重试两次
		params.setReadTimeout(30*1000);//
		params.setUseCookie(true);//暂时关掉缓冲，后期启用
		params.setCacheMaxAge(5 * 1000);//设置缓存有效的时间
	}
}
