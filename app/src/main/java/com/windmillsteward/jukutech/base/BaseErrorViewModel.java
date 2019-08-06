package com.windmillsteward.jukutech.base;

/**
 * function des:加载错误等数据模型
 * author zhuxian
 * date 2017/2/4 15 58
 * Created by jukutech
 */

public interface BaseErrorViewModel {
	/**
	 * 加载失败，网络问题或是服务器接口出错
	 *
	 * @param isClear 是否清除或清空页面数据
	 */
	void loadError(boolean isClear);

	/**
	 * 加载成功，无数据返回
	 *
	 * @param isClear 是否清除或清空页面数据
	 */
	void loadEmpty(boolean isClear);
}
