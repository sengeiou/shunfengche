package com.windmillsteward.jukutech.interfaces;

/**
 *
 */
public interface PublicSelectCallBack {
	/**
	 * PopupWinUtils下拉选择结果回调
	 * @param tag 一个页面多个选择框时,标记是哪个选择框
	 * @param id 已选择内容的ID
	 * @param name 已选择内容的内容
	 */
	void selectData(int tag, int id, String name);
}
