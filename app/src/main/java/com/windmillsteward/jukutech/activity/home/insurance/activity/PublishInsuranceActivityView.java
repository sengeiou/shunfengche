package com.windmillsteward.jukutech.activity.home.insurance.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/3/20/020
 * 作者：xjh
 */
public interface PublishInsuranceActivityView extends BaseViewModel {

    /**
     * 加载险种
     */
    void loadInsuranceListTypeSuccess(List<Map<String, Object>> maps);

    void loadPublishAreaDataSuccess(List<Map<String,Object>> maps);

    void uploadPicSuccess(List<String> img_urls);

    void isChargeResult(ChargeResultBean bean);

    void publishSuccess();
}
