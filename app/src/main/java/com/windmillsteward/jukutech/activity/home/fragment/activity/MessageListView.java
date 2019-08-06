package com.windmillsteward.jukutech.activity.home.fragment.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.MessageBean;

/**
 * 描述：消息列表回调
 * author:cyq
 * 2018-02-06
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface MessageListView extends BaseViewModel {

    /**
     * 初始化数据
     */
    void initDataSuccess(MessageBean bean);

    /**
     * 刷新
     * @param bean
     */
    void refreshDataSuccess(MessageBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页
     * @param bean
     */
    void loadNextDataSuccess(MessageBean bean);

    /**
     * 加载下一页失败
     */
    void loadNextFailure();

}
