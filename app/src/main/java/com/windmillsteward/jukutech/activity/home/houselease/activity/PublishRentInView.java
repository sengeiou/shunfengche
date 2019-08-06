package com.windmillsteward.jukutech.activity.home.houselease.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/2/6
 * 作者：xjh
 */

public interface PublishRentInView extends BaseViewModel {


    /**
     * 加载发布地区成功
     * @param maps
     */
    void loadPublishAreaDataSuccess(List<Map<String, Object>> maps);

    /**
     * 发布成功
     * @param data
     */
    void publishSuccess(String data);

    /**
     * 判断是否需要收费
     * @param bean
     */
    void isChargeResult(ChargeResultBean bean);
}
