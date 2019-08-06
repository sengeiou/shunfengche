package com.windmillsteward.jukutech.base;

/**
 * 网络请求结果最外层
 *
 * @author: zhuxian
 * @data: 2016/12/1 10:47
 * Created by  2016 广州聚酷软件科技有限公司 All Right Reserved
 */

public class BaseResultInfo<T> extends BaseData {
    private int code = -1;//请求结果返回码
    private String msg = "";//返回的消息提示(包括正确和错误的消息）
    private T data = null;//请求数据结果

    /**
     * @return 请求返回码 请参考接口文档
     */
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return 返回错误或成功提示消息 请参考接口文档
     */
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return 请求结果数据 请参考接口文档
     */
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
