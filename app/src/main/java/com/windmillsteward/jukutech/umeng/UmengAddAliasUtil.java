package com.windmillsteward.jukutech.umeng;

import android.util.Log;

import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.windmillsteward.jukutech.activity.MyApplication;

/**
 * 描述：友盟绑定uid
 * author:cyq
 * 2019/4/3
 * Created by 2019 广州聚酷软件科技有限公司 All Right Reserved
 */
public class UmengAddAliasUtil {



    /**
     * 友盟推送添加别名
     */
    public static void addAlias(String uid, String type) {
        PushAgent pushAgent = MyApplication.instance.getmPushAgent();
        pushAgent.addAlias(uid, type, new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean isSuccess, String message) {
                if (isSuccess) {
                    Log.i("sfc", "alias was set successfully.");
                } else {
                    Log.i("sfc", "alias was set failed.");
                }
            }
        });
    }


    /**
     * 友盟推送移除别名，每次需要绑定新的别名，必须要移除旧的别名
     */
    public  static void removeAndAddAlias(final String uid, final String type) {
        PushAgent pushAgent = MyApplication.instance.getmPushAgent();
        pushAgent.deleteAlias(uid, type, new UTrack.ICallBack() {
            @Override
            public void onMessage(boolean isSuccess, String s) {
                if (isSuccess) {
                    Log.i("sfc", "delet alias was set successfully.");
                    addAlias(uid, type);
                } else {
                    Log.i("sfc", "alias was set failed.");
                }
            }
        });
    }
}
