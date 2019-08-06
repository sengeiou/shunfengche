package com.windmillsteward.jukutech.base;

/**
 * 网络请求错误实体信息
 *
 * @author: zhuxian
 * @data: 2016/12/1 10:47
 * Created by  2016 广州聚酷软件科技有限公司 All Right Reserved
 */

public class BaseErrorInfo extends BaseData {
	private int code = -1;//请求结果返回码
	private String msg = "";//返回的消息提示(包括正确和错误的消息）
	private String data = "";//请求数据结果

	/**
	 * @return 请求返回码 请参考接口文档
	 */
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return 请求结果数据 请参考接口文档
	 */
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
