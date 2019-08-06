package com.windmillsteward.jukutech.utils.http;

import android.os.Message;

/**
 * 网络请求回调
 *
 * @author zhuxian
 */
public interface DataCallBack {
    /**
     * 请求成功
     */
    int RESULT_OK = 0;
    int ERRO_100 = 100;
    /**
     * 数据格式错误
     */
    int DATA_ERRO = -1;
    /**
     * 请求错误
     */
    int REQUESS_ERRO = -4;
    /**
     * 网络错误或没有网络
     */
    int NET_ERRO = -3;
    /**
     * 框架偶发性请求失败
     */
    int REQUEST_ERRO = -2;

    /**************************************************************************************************
     * 服务端返回错误码
     **************************************************************************************************/

    /**
     * 用户token过期
     */
    int ERRO_TOKEN = 10008;
    /**
     * 账户被禁用
     */
    int ACCOUNT_DISABLED = 4006;


    /**
     * @param msg    消息收集(包括了成功与错误的回调)
     * @param action 网络请求标志
     * @param json   网络json数据
     */
    void handlerData(Message msg, int action, String json);
}
