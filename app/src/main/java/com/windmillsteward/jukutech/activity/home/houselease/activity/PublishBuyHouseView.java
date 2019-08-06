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

public interface PublishBuyHouseView extends BaseViewModel {

    /**
     * 加载朝向
     * @param maps
     */
    void loadCXDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载装修
     * @param maps
     */
    void loadFixtureDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载产权
     * @param maps
     */
    void loadPropertyRightDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载发布地区成功
     * @param maps
     */
    void loadPublishAreaDataSuccess(List<Map<String, Object>> maps);

    void publishSuccess(String data);

    /**
     * 判断是否收费
     * @param bean
     */
    void isChargeResult(ChargeResultBean bean);
}
