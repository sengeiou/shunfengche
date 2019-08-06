package com.windmillsteward.jukutech.umeng;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.message.UmengMessageService;
import com.umeng.message.entity.UMessage;
import com.windmillsteward.jukutech.bean.MessageBean;
import com.windmillsteward.jukutech.bean.event.NotifyReceivedMsgRefreshOrder;
import com.windmillsteward.jukutech.utils.http.JsonUtil;

import org.android.agoo.common.AgooConstants;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mitic_xue on 16/10/26.
 */
public class UmengNotificationService extends UmengMessageService {
    @Override
    public void onMessage(Context context, Intent intent) {
        UMRTLog.d("UmengNotificationService", "onMessage");
        String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
        UMessage msg = null;
        try {
            msg = (UMessage) new UMessage(new JSONObject(message));
            if (msg != null) {
                JSONObject json = new JSONObject(msg.extra);
                MessageBean.ListBean umengPushBean = JsonUtil.fromJson(json.toString(), MessageBean.ListBean.class);
                String type = umengPushBean.getType();
                if (!TextUtils.isEmpty(type)) {
                    switch (type) {
                        case "2":
                        case "4":
                        case "6":
                        case "8":
                        case "10":
                        case "12":
                        case "14":
                        case "18":
                        case "20":
                        case "24":
                        case "26":
                        case "30":
                        case "32":
                        case "36":
                        case "38":
                        case "40":
                        case "42":
                        case "44":
                        case "49":
                        case "61":
                        case "97":
                        case "98":
                        case "101":
                        case "102":
                            EventBus.getDefault().post(new NotifyReceivedMsgRefreshOrder(Integer.parseInt(type)));
                            break;
                    }
                }
            }
            } catch(JSONException e){
                e.printStackTrace();
            }



        Intent intent1 = new Intent();
        intent1.setClass(context, MyNotificationService.class);
        intent1.putExtra("UmengMsg", message);
        context.startService(intent1);

    }


//    public void getNotification(Context context, String title, String msg) {
//        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        int id = (int) (System.currentTimeMillis() / 1000);
//        Intent intentClick = new Intent(this, NotificationClickReceiver.class);
//        intentClick.putExtra("title", title);
//        intentClick.putExtra("msg", msg);
//        intentClick.setAction("notification_clicked");
//        intentClick.putExtra(NotificationClickReceiver.TYPE, 0); //0代表点击
//        PendingIntent pendingIntentClick = PendingIntent.getBroadcast(this, id, intentClick, PendingIntent.FLAG_ONE_SHOT);
//
//        Intent intentCancel = new Intent(this, NotificationClickReceiver.class);
//        intentCancel.setAction("notification_cancelled");
//        intentCancel.putExtra(NotificationClickReceiver.TYPE, 1); //1代表清除的监听
//        PendingIntent pendingIntentCancel = PendingIntent.getBroadcast(this, id, intentCancel, PendingIntent.FLAG_ONE_SHOT);
//
//        if (Build.VERSION.SDK_INT >= 26) {  //判断8.0，若为8.0型号的手机进行创下一下的通知栏
//            NotificationChannel channel = new NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_HIGH);
//            if (manager != null) {
//                manager.createNotificationChannel(channel);
//            }
//            Notification.Builder builder = new Notification.Builder(context, "channel_id");
//            builder.setSmallIcon(R.drawable.icon_180)
//                    .setWhen(System.currentTimeMillis())
//                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_180))
//                    .setContentTitle(title)
//                    .setContentText(msg)
//                    .setAutoCancel(true)
//                    .setContentIntent(pendingIntentClick)
//                    .setDeleteIntent(pendingIntentCancel);
//            manager.notify(id, builder.build());
//        } else {
//            Notification.Builder builder = new Notification.Builder(context);
//            builder.setSmallIcon(R.drawable.icon_180)
//                    .setWhen(System.currentTimeMillis())
//                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_180))
//                    .setContentTitle(title)
//                    .setContentText(msg)
//                    .setAutoCancel(true)
//                    .setContentIntent(pendingIntentClick)
//                    .setDeleteIntent(pendingIntentCancel);;
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                manager.notify(id, builder.build());
//            }
//        }
//    }


}
