package com.windmillsteward.jukutech.base;

import android.view.View;

/**
 * function des:基础自定义监听器
 * author zhuxian
 * date 2017/3/3 11 26
 * Created by jukutech
 */

public interface BaseListener {
	/**
	 * 自定义recycleView的item点击监听器
	 */
	public interface OnRecyclerItemClickListener extends BaseListener {
		/**
		 * @param adapterType 适配器类型
		 * @param itemType    item布局类型
		 * @param view        item布局
		 * @param position    位置，下标
		 * @param itemBean    对应的实体
		 */
		void onItemClick(int adapterType, int itemType, View view, int position, Object itemBean);
	}
}
