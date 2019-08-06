package com.windmillsteward.jukutech.base;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

public class AppManager {
	/**
	 * 用于activity的管理和程序的
	 */
	private static Stack<Activity> activityStack;
	private static AppManager instance;

	private AppManager() {
	}

	/**
	 * 单一实例
	 */
	public static AppManager getAppManager() { 
		if (instance == null) {
			instance = new AppManager();
		}
		return instance;
	}

	/**
	 * 添加Activity到堆
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 获取当前Activity（堆栈中后一个压入的)
	 */
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 结束当前Activity（堆栈中后一个压入的)
	 */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * 保留指定的activity,其他的finish掉
	 */
	public void onSaveActivity(Class<?> cls) {
		if (activityStack == null)
			return;
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)
					&& !activityStack.get(i).getClass().equals(cls)) {
				activityStack.get(i).finish();
			}
		}
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			if (!activity.isFinishing()) {
				activity.finish();
			}
			activity = null;
		}
	}

	/**
	 * 结束指定类名的Activity(并发，会崩溃，待大神修改)
	 */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				if (!activity.isFinishing()){
					activity.finish();
				}
			}
		}
	}


	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity() {
		if (activityStack == null)
			return;
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * 获取当前app中所有打开的activity
	 *
	 * @return
	 */
	public Stack<Activity> getAppActivitys() {
		return activityStack;
	}

	/**
	 * 获取指定类名的activity对象
	 *
	 * @param cls 指定的类
	 * @return activity对象，若不存在则返回null
	 */
	public Activity getActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				return activity;
			}
		}
		return null;
	}

	/**
	 * 出应用程
	 */
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
