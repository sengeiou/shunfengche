package com.windmillsteward.jukutech.base.net;

import android.content.Intent;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.$Gson$Types;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.hyphenate.EMCallBack;
import com.orhanobut.hawk.Hawk;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.windmillsteward.jukutech.activity.MyApplication;
import com.windmillsteward.jukutech.activity.chat.DemoHelper;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.base.BaseErrorInfo;
import com.windmillsteward.jukutech.base.BaseResultInfo;
import com.windmillsteward.jukutech.bean.LoginSuccessInfo;
import com.windmillsteward.jukutech.bean.UserInfo;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.http.DataCallBack;
import com.windmillsteward.jukutech.utils.http.JsonUtil;
import com.windmillsteward.jukutech.utils.http.ResultInfo;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @date: on 2018/10/5
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public abstract class BaseNewNetModelimpl<T> implements DataCallBack {
    private static Gson gson;
    public static final TypeAdapter<String> STRING = new TypeAdapter<String>() {
        public String read(JsonReader reader) {
            try {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return "";//原先是返回Null，这里改为返回空字符串
                }
                return reader.nextString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "";
        }

        public void write(JsonWriter writer, String value) {
            try {
                if (value == null) {
                    writer.nullValue();
                    return;
                }
                writer.value(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 自定义adapter，解决由于数据类型为Int,实际传过来的值为Float，导致解析出错的问题
     * 目前的解决方案为将所有Int类型当成Double解析，再强制转换为Int
     */
    public static final TypeAdapter<Number> INTEGER = new TypeAdapter<Number>() {
        @Override
        public Number read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return 0;
            }
            try {
                double i = in.nextDouble();
                return (int) i;
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        }

        @Override
        public void write(JsonWriter out, Number value) throws IOException {
            out.value(value);
        }
    };

//
//    public static final TypeAdapter<Object> OBJECT = new TypeAdapter<Object>() {
//        public Object read(JsonReader reader) {
//            try {
//                if (reader.peek() == JsonToken.NULL) {
//                    reader.nextNull();
//                    return "";//原先是返回Null，这里改为返回空字符串
//                }
//                return reader.nextString();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return "";
//        }
//
//        public void write(JsonWriter writer, Object value) {
//            try {
//                if (value == null) {
//                    writer.nullValue();
//                    return;
//                }
//                writer.value(value);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    };

    static {
        GsonBuilder gsonBulder = new GsonBuilder();
        gsonBulder.registerTypeAdapter(String.class, STRING);   //所有String类型null替换为字符串“”
        gsonBulder.registerTypeAdapter(int.class, INTEGER); //int类型对float做兼容

        gson = gsonBulder.create();
    }

    protected abstract void onFail(int type, String msg);

    protected abstract void onSuccess(int code, BaseResultInfo<T> respnse, String source);

    protected abstract Type getType();

    @Override
    public void handlerData(Message msg, int action, String json) {
        switch (msg.what) {
            case ResultInfo.REQUEST_ERROR:// 请求失败,天杀的，说不定来个404,看个日志先
                onFail(REQUESS_ERRO, "网络不给力~" + "");
                break;
            case ResultInfo.REQUEST_ELSE_ERROR:// 请求失败(框架，或者后台返回了null等问题)
                onFail(REQUEST_ERRO, "网络不给力~" + "");
                break;
            case ResultInfo.NO_NET:// 请求失败，无网络或网络故障,谁又在下片了!天天下片，硬盘都满了吧!!!把公司网络都下瘫痪了
                onFail(NET_ERRO, "网络连接失败!" + "");
                break;
            default://统一入口,有事没事进来跑一圈,谁对谁错一看便知。》》》有事没事提示个服务器返回错误，屡试不爽，哈哈哈！！！
                JSONObject object = JSONObject.parseObject(json);//最开始进来的,先解析一层，慢慢分析
                if (object == null) {
                    onFail(DATA_ERRO, "网络不给力~" + "");
                    return;
                }
                int code = object.getIntValue("code");// 结果码
                if (code == RESULT_OK) {
                    /**
                     * 解析Code为0
                     */
                    Type objectType = getType();

                    BaseResultInfo baseResultInfo = null;

                    try {
                        baseResultInfo = gson.fromJson(json, objectType);
//                        Object data = baseResultInfo.getData();
//                        String s = JsonUtil.ObjectToJson(data);
//                        if ("{}".equals(s)) {
//                            baseResultInfo.setData(null);
//                        }
                    } catch (Exception e) {
                        //捕获到解析异常
                        e.printStackTrace();
                    }

                    if (baseResultInfo == null) {//数据格式错误,坑爹的后台接口犯抽了吧
                        onFail(DATA_ERRO, "服务器返回错误,解析异常!" + DATA_ERRO );
                        return;
                    }
//                    if (baseResultInfo.getData() == null) {//data结果为空或是解析异常,后台是不是喝了酒再来写接口的，这个地方怎么能出错呢
//                        onFail(DATA_ERRO, baseResultInfo.getMsg() + "。数据格式错误!" + DATA_ERRO + json);
//                        return;
//                    }
                    onSuccess(baseResultInfo.getCode(), baseResultInfo, json);//真正请求成功,终于修成正果了
                } else {
                    /**
                     * 解析code不为0
                     */
                    BaseErrorInfo baseErrorInfo = JsonUtil.fromJson(json, BaseErrorInfo.class);
                    if (baseErrorInfo == null) {
                        onFail(DATA_ERRO, "服务器返回错误,解析异常!" + DATA_ERRO );
                        return;
                    }
                    handErrorResult(baseErrorInfo);//特殊错误统一处理
                    onFail(baseErrorInfo.getCode(), baseErrorInfo.getMsg() );
                }
                break;
        }
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        //通过反射得到泛型参数
        //Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        //ParameterizedType参数化类型，即泛型
        ParameterizedType parameterized = (ParameterizedType) superclass;
        //getActualTypeArguments获取参数化类型的数组，泛型可能有多个
        //将Java 中的Type实现,转化为自己内部的数据实现,得到gson解析需要的泛型
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }


    private void handErrorResult(BaseErrorInfo baseErrorInfo) {
        switch (baseErrorInfo.getCode()) {
            case ERRO_TOKEN:// 用户token过期
                removeAlias(Hawk.get(Define.USER_ID,0)+"","sfc_android");
                if (DemoHelper.getInstance().isLoggedIn()) {
                    hxLogout();
                }
                Hawk.put(Define.ACCESS_TOKEN, "");
                Hawk.put(Define.USER_ID, 0);
                Hawk.put(Define.USER_INFO, new UserInfo());
                Hawk.put(Define.LOGIN_SUCCESS, new LoginSuccessInfo());
                Intent intent = new Intent(MyApplication.instance, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                MyApplication.instance.startActivity(intent);
                break;
        }
    }

    /**
     * 环信登出
     */
    private void hxLogout() {
        //注销环信
        DemoHelper.getInstance().logout(true, new EMCallBack() {

            @Override
            public void onSuccess() {
                // TODO Auto-generated method stub
                Log.d("main", "注销已经登录的账号成功");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {

            }
        });
    }

    /**
     * 友盟推送移除别名，每次需要绑定新的别名，必须要移除旧的别名
     */
    private void removeAlias(final String uid, String type) {
        PushAgent pushAgent = MyApplication.instance.getmPushAgent();
        pushAgent.deleteAlias(uid, type, new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean isSuccess, String s) {
                if (isSuccess) {
                    Log.i("登录过期", "delet alias was set successfully.");
                } else {
                    Log.i("登录过期", "alias was set failed.");
                }
            }
        });
    }
}
