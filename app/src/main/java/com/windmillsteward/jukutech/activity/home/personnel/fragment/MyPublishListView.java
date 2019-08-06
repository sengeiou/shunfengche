package com.windmillsteward.jukutech.activity.home.personnel.fragment;

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
     */
    void initDataSuccess(MyPublishBean bean);

    /**
     * 刷新
     */
    void refreshDataSuccess(MyPublishBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页
     */
    void loadNextDataSuccess(MyPublishBean bean);

    /**
     * 加载下一页失败
     */
    void loadNextError(String msg);
}
