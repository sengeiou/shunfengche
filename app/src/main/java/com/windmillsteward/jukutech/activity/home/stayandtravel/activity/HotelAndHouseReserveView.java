package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.HotelPayBeforeBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/3/6/006
 * 作者：xjh
 */
public interface HotelAndHouseReserveView extends BaseViewModel {

    /**
     * 加载房间数成功
     * @param maps
     */
    void loadRoomNumSuccess(List<Map<String,Object>> maps);

    /**
     * 调用接口计算价格
     * @param bean
     */
    void payBeforeResult(HotelPayBeforeBean bean);

    /**
     *发起支付
     * @param bean
     */
    void pay(HotelPayBeforeBean bean);
}
