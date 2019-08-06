package com.windmillsteward.jukutech.activity.home.houselease.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.HouseDetailBeam;
import com.windmillsteward.jukutech.bean.HouseMoreBean;

/**
 * 描述：
 * 时间：2018/2/6
 * 作者：xjh
 */

public interface HouseDetailView extends BaseViewModel {

    /**
     * 初始化
     * @param beam
     */
    void initDataSuccess(HouseDetailBeam beam);

    void collectSuccess();
    void cancelCollectSuccess();

    /**
     * 判断是否收费
     * @param bean
     */
    void isChargeResult(ChargeResultBean bean);

    void loadRentTypeDataSuccessF(HouseMoreBean bean);
}
