package com.windmillsteward.jukutech.activity.home.fragment.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.MessageDetailBean;

/**
 * 描述：消息详情回调
 * author:cyq
 * 2018-04-16
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface MessageDetailView extends BaseViewModel {

    /**
     * 成功
     * @param bean
     */
    void getMessageDetailSuccess(MessageDetailBean bean);

    /**
     * 失败
     * @param code
     * @param msg
     */
    void getMessageDetailFailed(int code,String msg);
}
