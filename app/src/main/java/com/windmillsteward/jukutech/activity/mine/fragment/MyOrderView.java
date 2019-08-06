package com.windmillsteward.jukutech.activity.mine.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.MyOrderBean;

/**
 * 描述：
 * 时间：2018/3/2
 * 作者：xjh
 */

public interface MyOrderView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean
     */
    void initDataSuccess(MyOrderBean bean);

    /**
     * 刷新数据成功
     * @param bean
     */
    void refreshDataSuccess(MyOrderBean bean);

    void refreshDataFailure();

    /**
     * 加载下一页数据成功
     * @param bean
     */
    void loadNextDataSuccess(MyOrderBean bean);

    void loadNextDataFailure();
}
