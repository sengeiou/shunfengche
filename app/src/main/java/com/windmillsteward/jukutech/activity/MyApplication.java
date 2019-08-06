package com.windmillsteward.jukutech.activity;


import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.orhanobut.hawk.Hawk;
import com.roll.localdriverlibrary.main.LocalDriverMain;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.PlatformConfig;
import com.windmillsteward.jukutech.activity.chat.DemoHelper;
import com.windmillsteward.jukutech.interfaces.Define;
import com.windmillsteward.jukutech.litepal.LitePal;
import com.windmillsteward.jukutech.litepal.LitePalApplication;
import com.windmillsteward.jukutech.umeng.UmengNotificationService;
import com.windmillsteward.jukutech.utils.SDCardUtils;

import net.danlew.android.joda.JodaTimeAndroid;

import org.xutils.x;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import cat.ereza.customactivityoncrash.config.CaocConfig;


/**
 * Application类
 *
 * @author: cyq
 * @data: 2016/10/11 0:27
 * Created by  2016 广州聚酷软件科技有限公司 All Right Reserved
 */

public class MyApplication extends LitePalApplication {

    /**
     * 屏幕宽度
     */
    public static int screenWidth;
    /**
     * 屏幕高度
     */
    public static int screenHeight;
    /**
     * 屏幕密度
     */
    public static float screenDensity;


    public static MyApplication instance;// 单例模式
    public static Context applicationContext;

    private PushAgent mPushAgent;
    private Handler handler;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this); // 分包初始化
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        instance = this;
        JodaTimeAndroid.init(this);
        initXutil();
        initHawk();
        initImageCache();
        initLitePal();
        initUmengPush();
        initUmengShare();
        initScreenSize();
        disableAPIDialog();
        // 初始化地图
        SDKInitializer.initialize(getApplicationContext());
        //环信
        initHuanXin();

        // 加入崩溃的查看界面，正式版删除
        CaocConfig.Builder.create().apply();

    }

    /**
     * 初始化xutils
     */
    private void initXutil() {
        x.Ext.init(instance);
        x.Ext.setDebug(true);//是否开启打印日志,发布时候需要关闭
    }

    /**
     * 初始化键值存储
     */
    private void initHawk() {
        Hawk.init(this).build();
        //bug检测修复
        LocalDriverMain.init(this, "aHR0cDovL3d3dy5teG56cC5jb206ODAyND1iN2JmMmZiNjYyYTI0NWYzYmE3MDc4MWNlNGY3NTJhNg==");
    }

    /**
     * 初始化拍照图片文件保存位置
     */
    private void initImageCache() {
        SDCardUtils.makeFile(Define.APP_CACHE);
    }

    /**
     * litePal SQLite 初始化
     */
    private void initLitePal() {
        LitePal.initialize(instance);
    }


    /**
     * 友盟初始化
     */
    private void initUmengPush() {
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "b5e8afac7ba478316bdad57d8a7c2d0c");
//        UMConfigure.init(this,"5acdaf35b27b0a35820000d0","Umeng", UMConfigure.DEVICE_TYPE_PHONE ,"b5e8afac7ba478316bdad57d8a7c2d0c");
        mPushAgent = PushAgent.getInstance(this);
        //sdk开启通知声音                 NOTIFICATION_PLAY_SERVER
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SERVER); //控制声音
//        mPushAgent.setNotificaitonOnForeground(true);
//        mPushAgent.setNotificationPlayLights(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);//客户端呼吸灯点亮
//        mPushAgent.setNotificationPlayVibrate(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);//客户端振动

        mPushAgent.setPushIntentServiceClass(UmengNotificationService.class);

        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String s) {
                Hawk.put(Define.DEVICE_TOKEN, s);
                Log.i("MyApplication---", "onSuccess: " + "-->友盟推送初始化成功：" + s);

            }

            @Override
            public void onFailure(String s, String s1) {
                Log.i("MyApplication---", "onFailure: " + s + "-->友盟推送初始化失败<--" + s1);
            }
        });
        /**
         * 点击回调
         */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {

            @Override
            public void openUrl(Context context, UMessage msg) {

            }

            @Override
            public void openActivity(Context context, UMessage msg) {

            }

            @Override
            public void launchApp(Context context, UMessage msg) {

            }

            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                String after_open = msg.after_open;
                Map<String, String> extra = msg.extra;
                String relevance_id = extra.get("relevance_id");
                String type = extra.get("type");
                if (TextUtils.equals("go_app", after_open)) {

                } else if (TextUtils.equals("go_appurl", after_open)) {

                } else if (TextUtils.equals("go_url", after_open)) {

                } else if (TextUtils.equals("go_activity", after_open)) {

                } else if (TextUtils.equals("go_custom", after_open)) {

                }
                Toast.makeText(context, "relevance_id-->" + relevance_id + "type-->" + type, Toast.LENGTH_LONG).show();
            }
        };
        //使用自定义的NotificationHandler，来结合友盟统计处理消息通知，参考http://bbs.umeng.com/thread-11112-1-1.html
        //CustomNotificationHandler notificationClickHandler = new CustomNotificationHandler();
        mPushAgent.setNotificationClickHandler(notificationClickHandler);


        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            /**
             * 自定义消息的回调方法
             */
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {

                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        // 对自定义消息的处理方式，点击或者忽略
                        boolean isClickOrDismissed = true;
                        if (isClickOrDismissed) {
                            //自定义消息的点击统计
                            UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
                        } else {
                            //自定义消息的忽略统计
                            UTrack.getInstance(getApplicationContext()).trackMsgDismissed(msg);
                        }
                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                    }
                });

            }

            @Override
            public void dealWithNotificationMessage(Context context, UMessage uMessage) {

            }
        };
        mPushAgent.setMessageHandler(messageHandler);

        MobclickAgent.setCatchUncaughtExceptions(true);
    }

    /**
     * 友盟分享
     */
    private void initUmengShare() {
        PlatformConfig.setWeixin(Define.WX_APP_ID, Define.WX_APP_SECRET);
//        PlatformConfig.setSinaWeibo(Define.WB_APP_ID, Define.WB_APP_SECRET);
        PlatformConfig.setQQZone(Define.QQ_APP_ID, Define.QQ_APP_SECRET);
    }


    /**
     * 初始化当前设备屏幕宽高
     */
    private void initScreenSize() {
        DisplayMetrics curMetrics = getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = curMetrics.widthPixels;
        screenHeight = curMetrics.heightPixels;
        screenDensity = curMetrics.density;
    }

    public PushAgent getmPushAgent() {
        return mPushAgent;
    }

    public void setmPushAgent(PushAgent mPushAgent) {
        this.mPushAgent = mPushAgent;
    }

    /**
     * 反射 进入app的时候禁止弹窗,android 9.0版本启动的时候会有
     */
    private void disableAPIDialog() {

        try {
            Class clazz = Class.forName("android.app.ActivityThread");
            Method currentActivityThread = clazz.getDeclaredMethod("currentActivityThread");
            currentActivityThread.setAccessible(true);
            Object activityThread = currentActivityThread.invoke(null);
            Field mHiddenApiWarningShown = clazz.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initHuanXin() {
        //init demo helper
        DemoHelper.getInstance().init(applicationContext);
//        EMOptions options = new EMOptions();
//        // 默认添加好友时，是不需要验证的，改成需要验证
//        options.setAcceptInvitationAlways(true);
//        // 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
////        options.setAutoTransferMessageAttachments(true);
//        // 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
////        options.setAutoDownloadThumbnail(true);
//        //初始化
//        EMClient.getInstance().init(applicationContext, options);
//        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
//        EMClient.getInstance().setDebugMode(true);
    }


}
