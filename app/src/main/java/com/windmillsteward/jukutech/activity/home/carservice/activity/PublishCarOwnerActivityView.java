package com.windmillsteward.jukutech.activity.home.carservice.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/4/3/003
 * 作者：xjh
 */
public interface PublishCarOwnerActivityView extends BaseViewModel {

    /**
     * 加载空余座位
     */
    void loadUnoccupiedNumDataSuccess(List<Map<String,Object>> maps);

    /**
     * 加载车型数据
     * @param maps
     */
    void loadVehicleModuleNameSuccess(List<Map<String,Object>> maps);

    void isCharge(ChargeResultBean bean);

    /**
     * 加载发布地区成功
     * @param maps
     */
    void loadPublishAreaDataSuccess(List<Map<String, Object>> maps);

    void publishSuccess();
}
