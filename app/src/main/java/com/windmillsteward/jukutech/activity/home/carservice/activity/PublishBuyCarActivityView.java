package com.windmillsteward.jukutech.activity.home.carservice.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/3.
 */

public interface PublishBuyCarActivityView extends BaseViewModel {

    /**
     * 加载交易地区成功
     * @param maps
     */
    void loadDealAreaDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载发布地区成功
     * @param maps
     */
    void loadPublishAreaDataSuccess(List<Map<String, Object>> maps);

    void publishSuccess();

    void isCharge(ChargeResultBean bean);
}
