package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.AfterSealsListBean;
import com.windmillsteward.jukutech.bean.MyOrderBean;

/**
 * 描述：
 * 时间：2018/3/2
 * 作者：xjh
 */

public interface AfterSealsListActivityView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean
     */
    void initDataSuccess(AfterSealsListBean bean);

    /**
     * 刷新数据成功
     * @param bean
     */
    void refreshDataSuccess(AfterSealsListBean bean);

    void refreshDataFailure();

    /**
     * 加载下一页数据成功
     * @param bean
     */
    void loadNextDataSuccess(AfterSealsListBean bean);

    void loadNextDataFailure();
}
