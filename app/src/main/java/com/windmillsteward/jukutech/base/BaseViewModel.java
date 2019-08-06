package com.windmillsteward.jukutech.base;

/**
 * 展示view的模型（多使用java接口的多继承，减少代码的偶合度,提高扩展性)
 * Created by zhuxian on 2016/12/9.
 */

public interface BaseViewModel {
	/**
	 * 展示加载对话框
	 *
	 * @fparam content 展示的内容提示
	 */
	void showDialog(String content);

	/**
	 * 取消对话框
	 */
	void dismiss();

	/**
	 * @param content  提示的内容
	 * @param duration 提示的时间
	 */
	void showTips(String content, int duration);

	/**
	 * 显示内容视图
	 */
	void showContentView();

	/**
	 * 显示错误视图
	 */
	void showErrorView();
}
