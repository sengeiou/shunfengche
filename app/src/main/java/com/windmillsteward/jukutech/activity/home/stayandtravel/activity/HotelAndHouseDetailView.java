package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.HotelAndHouseDetailBean;

/**
 * 描述：
 * 时间：2018/1/28/028
 * 作者：xjh
 */
public interface HotelAndHouseDetailView extends BaseViewModel {

    /**
     * 初始化数据
     * @param bean
     */
    void initDataSuccess(HotelAndHouseDetailBean bean);

    void collectionSuccess();
    void cancelCollectionSuccess();
}
