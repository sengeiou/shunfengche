package com.windmillsteward.jukutech.activity.home.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;

/**
 * 描述：首页--未读的消息数
 * author:cyq
 * 2018-03-02
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface UnreadMessageCountView extends BaseViewModel {
    /**
     * 获取成功
     * @param count
     */
    void getUnreadMessageCountSuccess(String count);

    /**
     * 获取失败
     * @param code
     * @param msg
     */
    void getUnreadMessageCountFailed(int code, String msg);
}
