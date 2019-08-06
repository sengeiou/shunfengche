package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;

/**
 * 描述：模块介绍H5回调
 * author:cyq
 * 2018-03-12
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface ModuleInstructionView extends BaseViewModel {

    /**
     * 获取成功
     * @param html_url
     */
    void getHtmlUrlSuccess(String html_url);

    /**
     * 获取失败
     * @param code
     * @param msg
     */
    void getHtmlUrlFailed(int code,String msg);
}
