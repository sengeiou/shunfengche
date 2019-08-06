package com.windmillsteward.jukutech.umeng;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import com.umeng.message.UTrack;
import com.umeng.message.entity.UMessage;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.utils.SoundUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/**
 * Created by mitic_xue on 16/10/28.
 */
public class MyNotificationService extends Service {
    private static final String TAG = UmengNotificationService.class.getName();
    public static UMessage oldMessage = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String message = intent.getStringExtra("UmengMsg");
            SoundUtils.playSound(R.raw.umeng_push_notification_default_sound);
            try {
                UMessage msg = new UMessage(new JSONObject(message));
                if (oldMessage != null) {
                    UTrack.getInstance(getApplicationContext()).setClearPrevMessage(true);
                    UTrack.getInstance(getApplicationContext()).trackMsgDismissed(oldMessage);
                }
                showNotification(msg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//        return super.onStartCommand(intent, flags, startId);START_REDELIVER_INTENT
        return super.onStartCommand(intent, flags, startId);
    }

    private void showNotification(UMessage msg) {

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int id = new Random(System.nanoTime()).nextInt();
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel channel = new NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_HIGH);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
            Notification.Builder builder = new Notification.Builder(this, "channel_id");
            builder.setSmallIcon(R.mipmap.icon_app_logo)
                    .setWhen(System.currentTimeMillis())
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_app_logo))
                    .setContentTitle(msg.title)
                    .setContentText(msg.text)
                    .setAutoCancel(true)
                    .setContentIntent(getClickPendingIntent(this, msg))
                    .setDeleteIntent(getDismissPendingIntent(this, msg));
            manager.notify(id, builder.build());

        } else {
            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(R.mipmap.icon_app_logo)
                    .setWhen(System.currentTimeMillis())
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.icon_app_logo))
                    .setContentTitle(msg.title)
                    .setContentText(msg.text)
                    .setAutoCancel(true)
                    .setContentIntent(getClickPendingIntent(this, msg))
                    .setDeleteIntent(getDismissPendingIntent(this, msg));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                manager.notify(id, builder.build());
            }
        }


//        oldMessage = msg;
//        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.cancelAll();
//        Notification.Builder mBuilder = new Notification.Builder(this);
//        mBuilder.setContentTitle(msg.title)
//                .setContentText(msg.text)
//                .setTicker(msg.ticker)
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.mipmap.icon_app_logo)
//                .setAutoCancel(true);
//        Notification notification = builder.getNotification();
//        PendingIntent clickPendingIntent = getClickPendingIntent(this, msg);
//        PendingIntent dismissPendingIntent = getDismissPendingIntent(this, msg);
//        notification.deleteIntent = dismissPendingIntent;
//        notification.contentIntent = clickPendingIntent;
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            manager.notify(id, builder.build());
//        }
        Log.i(TAG, "getClickPendingIntent: " + "你点到我了1");
    }

    public PendingIntent getClickPendingIntent(Context context, UMessage msg) {
        Intent clickIntent = new Intent();
        clickIntent.setClass(context, NotificationBroadcast.class);
        clickIntent.putExtra(NotificationBroadcast.EXTRA_KEY_MSG,
                msg.getRaw().toString());
        clickIntent.putExtra(NotificationBroadcast.EXTRA_KEY_ACTION,
                NotificationBroadcast.ACTION_CLICK);
        PendingIntent clickPendingIntent = PendingIntent.getBroadcast(context,
                (int) (System.currentTimeMillis()),
                clickIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        Log.i(TAG, "getClickPendingIntent: " + "你点到我了0");
        return clickPendingIntent;
    }

    public PendingIntent getDismissPendingIntent(Context context, UMessage msg) {
        Intent deleteIntent = new Intent();
        deleteIntent.setClass(context, NotificationBroadcast.class);
        deleteIntent.putExtra(NotificationBroadcast.EXTRA_KEY_MSG,
                msg.getRaw().toString());
        deleteIntent.putExtra(
                NotificationBroadcast.EXTRA_KEY_ACTION,
                NotificationBroadcast.ACTION_DISMISS);
        PendingIntent deletePendingIntent = PendingIntent.getBroadcast(context,
                (int) (System.currentTimeMillis() + 1),
                deleteIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        return deletePendingIntent;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
