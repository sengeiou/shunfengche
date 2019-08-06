package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;

import java.util.List;
import java.util.Map;

/**
 * 描述：添加资金托管
 * 时间：2018/3/7
 * 作者：cyq
 */

public interface AddFundsTrusteeshipView extends BaseViewModel {
    /**
     * 添加资金托管成功
     */
    void addAddFundsTrusteeshipSuccess();

    /**
     * 添加资金托管失败
     * @param code
     * @param msg
     */
    void addAddFundsTrusteeshipFailed(int code, String msg);

    /**
     * 上传图片成功
     *
     * @param pic_urls
     */
    void uploadPicSuccess(List<String> pic_urls);


    /**
     * 上传图片失败
     *
     */
    void uploadPicFailed(int code,String msg);

    /**
     * 加载托管模块 1人才驿站 2智慧家庭 3思想智库 4房屋租售 5住宿旅游
     * @param maps
     */
    void loadTrusteeshipModuleSuccess(List<Map<String, Object>> maps);
}
