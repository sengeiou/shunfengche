package com.windmillsteward.jukutech.base;

import android.content.Intent;
import android.os.Message;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.hyphenate.EMCallBack;
import com.orhanobut.hawk.Hawk;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.windmillsteward.jukutech.activity.MyApplication;
import com.windmillsteward.jukutech.activity.chat.DemoHelper;
import com.windmillsteward.jukutech.activity.login.activity.LoginActivity;
import com.windmillsteward.jukutech.bean.LoginSuccessInfo;
import com.windmillsteward.jukutech.bean.UserInfo;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.utils.http.DataCallBack;
import com.windmillsteward.jukutech.utils.http.JsonUtil;
import com.windmillsteward.jukutech.utils.http.ResultInfo;

import java.lang.reflect.Type;

/**
 * 公用网络请求类，过虑等处理
 *
 * @author zhuxian
 */
public abstract class BaseNetModelImpl implements DataCallBack {

    /**
     * 重写这个方法，赋于结果相应的数据模型
     *
     * @param action 对应的网络请求标志
     * @return
     */
    protected abstract Type getDataType(int action);

    /**
     * 用于接收请求成功结果
     *
     * @param result     数据结果
     * @param action     相应的请求对象标志
     * @param code       响应码
     * @param sourceData 网络请求回来的源数据(方便debug查看结果)
     */
    protected abstract void onDataCallback(int code, int action, BaseResultInfo result, String sourceData);

    /**
     * 网络请求失败
     *
     * @param action     对应的网络请求标志
     * @param msg        请求结果对应的提示消息(一般指错误的消息)
     * @param code       响应码
     * @param sourceData 网络请求回来的源数据(方便debug查看结果)
     */
    protected abstract void requestFailed(int code, int action, String msg, String sourceData);

    /**
     * 网络请求回调
     *
     * @param msg    一些必要的消息
     * @param action 标识
     * @param json   网络数据
     */
    @Override
    public void handlerData(Message msg, int action, String json) {
        switch (msg.what) {
            case ResultInfo.REQUEST_ERROR:// 请求失败,天杀的，说不定来个404,看个日志先
                requestFailed(REQUESS_ERRO, action, "网络不给力~" + "", json);
                break;
            case ResultInfo.REQUEST_ELSE_ERROR:// 请求失败(框架，或者后台返回了null等问题)
                requestFailed(REQUEST_ERRO, action, "网络不给力~" + "", json);
                break;
            case ResultInfo.NO_NET:// 请求失败，无网络或网络故障,谁又在下片了!天天下片，硬盘都满了吧!!!把公司网络都下瘫痪了
                requestFailed(NET_ERRO, action, "网络连接失败!" + "", json);
                break;
            default://统一入口,有事没事进来跑一圈,谁对谁错一看便知。》》》有事没事提示个服务器返回错误，屡试不爽，哈哈哈！！！
                JSONObject object = JSONObject.parseObject(json);//最开始进来的,先解析一层，慢慢分析
                if (object == null) {
                    requestFailed(DATA_ERRO, action, "网络不给力~" + "", json);
                    return;
                }
                int code = object.getIntValue("code");// 结果码
                if (code == RESULT_OK) {
                    /**
                     * 解析Code为0
                     */
                    BaseResultInfo baseResultInfo = JsonUtil.fromJson(json, getDataType(action));
                    if (baseResultInfo == null) {//数据格式错误,坑爹的后台接口犯抽了吧
                        requestFailed(DATA_ERRO, action, "网络不给力~" + DATA_ERRO, json);
                        return;
                    }
                    if (baseResultInfo.getData() == null) {//data结果为空或是解析异常,后台是不是喝了酒再来写接口的，这个地方怎么能出错呢
                        requestFailed(DATA_ERRO, action, baseResultInfo.getMsg() + "网络不给力~" + DATA_ERRO, json);
                        return;
                    }
                    onDataCallback(baseResultInfo.getCode(), action, baseResultInfo, json);//真正请求成功,终于修成正果了
                } else {
                    /**
                     * 解析code不为0
                     */
                    BaseErrorInfo baseErrorInfo = JsonUtil.fromJson(json, BaseErrorInfo.class);
                    if (baseErrorInfo == null) {
                        requestFailed(DATA_ERRO, action, "服务器返回错误,解析异常!" + DATA_ERRO, json);
                        return;
                    }
                    handErrorResult(baseErrorInfo);//特殊错误统一处理
                    requestFailed(baseErrorInfo.getCode(), action, baseErrorInfo.getMsg(), json);
//                    requestFailed(baseErrorInfo.getCode(), action, baseErrorInfo.getMessage(), json);
                }
                break;
        }
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
