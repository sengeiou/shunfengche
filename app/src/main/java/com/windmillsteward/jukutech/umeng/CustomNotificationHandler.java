package com.windmillsteward.jukutech.umeng;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import java.util.HashMap;
import java.util.Map;

//使用自定义的NotificationHandler，来结合友盟统计处理消息通知
//参考http://bbs.umeng.com/thread-11112-1-1.html
public class CustomNotificationHandler extends UmengNotificationClickHandler {
	
	private static final String TAG = CustomNotificationHandler.class.getName();
	
	@Override
	public void dismissNotification(Context context, UMessage msg) {
		UMRTLog.d(TAG, "dismissNotification");
		super.dismissNotification(context, msg);
		MobclickAgent.onEvent(context, "dismiss_notification");
	}
	
	@Override
	public void launchApp(Context context, UMessage msg) {
		UMRTLog.d(TAG, "launchApp");
		super.launchApp(context, msg);
		Map<String, String> map = new HashMap<String, String>();
		map.put("action", "launch_app");
		MobclickAgent.onEvent(context, "click_notification", map);
	}
	
	@Override
	public void openActivity(Context context, UMessage msg) {
		UMRTLog.d(TAG, "openActivity");
		super.openActivity(context, msg);
		Map<String, String> map = new HashMap<String, String>();
		map.put("action", "open_activity");
		MobclickAgent.onEvent(context, "click_notification", map);
	}
	
	@Override
	public void openUrl(Context context, UMessage msg) {
		UMRTLog.d(TAG, "openUrl");
		super.openUrl(context, msg);
		Map<String, String> map = new HashMap<String, String>();
		map.put("action", "open_url");
		MobclickAgent.onEvent(context, "click_notification", map);
	}
	
	@Override
	public void dealWithCustomAction(Context context, UMessage msg) {
		UMRTLog.d(TAG, "dealWithCustomAction");
		super.dealWithCustomAction(context, msg);
		Map<String, String> map = new HashMap<String, String>();
		map.put("action", "custom_action");
		MobclickAgent.onEvent(context, "click_notification", map);
	}
	
	@Override
	public void autoUpdate(Context context, UMessage msg) {
		UMRTLog.d(TAG, "autoUpdate");
		super.autoUpdate(context, msg);
		Map<String, String> map = new HashMap<String, String>();
		map.put("action", "auto_update");
		MobclickAgent.onEvent(context, "click_notification", map);
	}

}
