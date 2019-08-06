package com.windmillsteward.jukutech.activity.mine.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.MyPublishBean;


/**
 * 描述：
 * 时间：2018/2/20/020
 * 作者：xjh
 */
public interface MyPublishView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean
     */
    void initDataSuccess(MyPublishBean bean);

    /**
     * 刷新数据
     * @param bean
     */
    void refreshDataSuccess(MyPublishBean bean);

    void refreshDataFailure();

    /**
     * 加载下一页
     * @param bean
     */
    void loadNextDataSuccess(MyPublishBean bean);

    /**
     * 加载下一页失败
     */
    void loadNextDataFailure();
}
