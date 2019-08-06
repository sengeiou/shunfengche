package com.windmillsteward.jukutech.utils.http;


import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.windmillsteward.jukutech.base.BaseResultInfo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * json工具类
 *
 * @time 2016/10/10 11:37
 */
public class JsonUtil {

    /**
     * bean 转化成json
     *
     * @param t
     * @return
     * @throws Exception
     */
    public static <T> String ObjectToJson(T t) throws Exception {
        return JSON.toJSONString(t);
    }

    /**
     * JSON字符串，返回T对象
     *
     * @param <T>  泛型方法定义
     * @param json 返回JSON字符串
     * @param type 类型
     */
    public static <T> T fromJson(String json, Type type) {
        try {
            return JSON.parseObject(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * JSON字符串，返回T对象
     *
     * @param json  json字符串
     * @param clazz 实体类.class
     * @return 返回T对象
     * @author zhuxian
     * @time 2016/10/10 11:51
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }

}
