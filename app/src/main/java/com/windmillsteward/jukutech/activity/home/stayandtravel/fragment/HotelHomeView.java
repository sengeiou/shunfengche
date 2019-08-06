package com.windmillsteward.jukutech.activity.home.stayandtravel.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.HotelAndHouseBean;
import com.windmillsteward.jukutech.bean.HotelAndHouseHomeBean;
import com.windmillsteward.jukutech.bean.HotelTypeBean;
import com.windmillsteward.jukutech.bean.HouseMoreBean;
import com.windmillsteward.jukutech.bean.PriceBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/30
 * 作者：xjh
 */

public interface HotelHomeView extends BaseViewModel {

    /**
     * 初始化数据成功
     */
    void initDataSuccess(List<HotelAndHouseHomeBean> bean);

    /**
     * 加载价格数据成功
     * @param list
     */
    void loadPriceDataSuccess(List<PriceBean> list);

    /**
     * 架子酒店分类数据成功
     * @param list
     */
    void loadHouseTypeDataSuccess(List<HotelTypeBean> list);
}
