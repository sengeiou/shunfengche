package com.windmillsteward.jukutech.utils.http;

import com.google.gson.Gson;
import com.windmillsteward.jukutech.base.BaseResultInfo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @date: on 2018/10/5
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class JsonNewUtil<T> {
    public static BaseResultInfo fromJson(String json, Class clazz) {
        Gson gson = new Gson();
        Type objectType = type(BaseResultInfo.class, clazz);
        return gson.fromJson(json, objectType);
    }

    public String toJson(Class<T> clazz) {
        Gson gson = new Gson();
        Type objectType = type(BaseResultInfo.class, clazz);
        return gson.toJson(this, objectType);
    }

    static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}
