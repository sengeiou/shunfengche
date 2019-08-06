package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.MyCollectBean;

/**
 * 描述：
 * 时间：2018/2/21/021
 * 作者：xjh
 */
public interface MyCollectView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean
     */
    void initDataSuccess(MyCollectBean bean);

    /**
     * 刷新成功
     * @param bean
     */
    void refreshDataSuccess(MyCollectBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页成功
     * @param bean
     */
    void loadNextDataSuccess(MyCollectBean bean);

    /**
     * 加载下一页失败
     */
    void loadNextDataFailure();

    /**
     * 删除收藏成功
     */
    void deleteCollectSuccess();
}
