package com.windmillsteward.jukutech.activity.home.personnel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.MyPublishBean;

/**
 * 描述：
 * 时间：2018/1/12
 * 作者：xjh
 */

public interface MyPublishListView extends BaseViewModel {

    /**
     * 初始化
     * @param bean
     */
    void initData(MyPublishBean bean);

    /**
     * 刷新
     * @param bean
     */
    void refreshData(MyPublishBean bean);

    /**
     * 加载下一页
     * @param bean
     */
    void loadNextData(MyPublishBean bean);

    /**
     * 加载下一页失败
     * @param msg
     */
    void loadNextError(String msg);
}
