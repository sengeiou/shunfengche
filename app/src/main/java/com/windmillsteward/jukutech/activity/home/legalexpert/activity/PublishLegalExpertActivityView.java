package com.windmillsteward.jukutech.activity.home.legalexpert.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public interface PublishLegalExpertActivityView extends BaseViewModel {

    /**
     * 加载发布地区成功
     * @param maps
     */
    void loadPublishAreaDataSuccess(List<Map<String, Object>> maps);
    void isChargeResult(ChargeResultBean bean);

    void publishSuccess();
}
