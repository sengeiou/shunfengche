/************************************************************
  *  * Hyphenate CONFIDENTIAL 
  * __________________ 
  * Copyright (C) 2016 Hyphenate Inc. All rights reserved. 
  *  
  * NOTICE: All information contained herein is, and remains 
  * the property of Hyphenate Inc..
  * Dissemination of this information or reproduction of this material 
  * is strictly forbidden unless prior written permission is obtained
  * from Hyphenate Inc.
  */
package com.windmillsteward.jukutech.activity.chat.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hyphenate.chat.EMChatService;
import com.hyphenate.util.EMLog;

/**
 * start service if user enabled start from boot.
 */
public class StartServiceReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		if (!intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)
				&& !intent.getAction().equals("android.intent.action.QUICKBOOT_POWERON")) {
			return;
		}
		EMLog.d("boot", "start IM service on boot");
		Intent startServiceIntent=new Intent(context, EMChatService.class);
		startServiceIntent.putExtra("reason", "boot");
		try {
			context.startService(startServiceIntent);
		} catch (Exception e) {
			EMLog.d("EMMonitorReceiver", "exception in start service, e: " + e.getMessage());
		}
	}
}
