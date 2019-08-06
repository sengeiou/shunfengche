package com.windmillsteward.jukutech.umeng;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.message.UTrack;
import com.umeng.message.entity.UMessage;
import com.windmillsteward.jukutech.bean.MessageBean;
import com.windmillsteward.jukutech.utils.UmengPushUtil;
import com.windmillsteward.jukutech.utils.http.JsonUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

/**
 * Created by mitic_xue on 16/10/26.
 */
public class NotificationBroadcast extends BroadcastReceiver {
    public static final String EXTRA_KEY_ACTION = "ACTION";
    public static final String EXTRA_KEY_MSG = "MSG";
    public static final int ACTION_CLICK = 10;
    public static final int ACTION_DISMISS = 11;
    public static final int EXTRA_ACTION_NOT_EXIST = -1;
    private static final String TAG = NotificationBroadcast.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra(EXTRA_KEY_MSG);
        int action = intent.getIntExtra(EXTRA_KEY_ACTION,
                EXTRA_ACTION_NOT_EXIST);
        try {
            UMessage msg = (UMessage) new UMessage(new JSONObject(message));
            switch (action) {
                case ACTION_DISMISS:
                    UMRTLog.d(TAG, "dismiss notification");
                    UTrack.getInstance(context).setClearPrevMessage(true);
                    UTrack.getInstance(context).trackMsgDismissed(msg);
                    break;
                case ACTION_CLICK:
                    UMRTLog.d(TAG, "click notification");
                    UTrack.getInstance(context).setClearPrevMessage(true);
                    MyNotificationService.oldMessage = null;
                    UTrack.getInstance(context).trackMsgClick(msg);
                    JSONObject json = new JSONObject(msg.extra);
                    MessageBean.ListBean umengPushBean = JsonUtil.fromJson(json.toString(), MessageBean.ListBean.class);
                    new UmengPushUtil(context, umengPushBean).jumpRelativeActivityByType();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
