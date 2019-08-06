package com.windmillsteward.jukutech.activity.home.stayandtravel.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.HotelAndHouseBean;
import com.windmillsteward.jukutech.bean.ThirdAndFourthAreaBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/1/27/027
 * 作者：xjh
 */
public interface HotelAndHouseListView extends BaseViewModel {

    /**
     * 初始化数据成功
     */
    void initDataSuccess(HotelAndHouseBean bean);

    /**
     * 刷新数据成功
     * @param bean
     */
    void refreshDataSuccess(HotelAndHouseBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页数据成功
     * @param bean
     */
    void loadNextDataSuccess(HotelAndHouseBean bean);

    /**
     * 加载下一页失败
     */
    void loadNextDataFailure();

    /**
     * 加载地区数据成功
     * @param maps
     */
    void loadAreaDataSuccess(List<ThirdAndFourthAreaBean> maps);

    /**
     * 加载星级数据成功
     * @param maps
     */
    void loadHouseTypeDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载价格数据成功
     * @param maps
     */
    void loadPriceDataSuccess(List<Map<String, Object>> maps);
}
