package com.windmillsteward.jukutech.utils.http;

/**
 * 请求结果状态实体类
 */
public class ResultInfo {
    /**
     * 请求成功
     */
    public final static int REQUEST_SUCESS = 0;
    /**
     * 请求失败
     */
    public final static int REQUEST_ERROR = 1;
    /**
     * 请求失败(框架，或者后台返回了null等问题)
     */
    public final static int REQUEST_ELSE_ERROR = 2;
    /**
     * 没有网络
     */
    public final static int NO_NET = -1;

}
