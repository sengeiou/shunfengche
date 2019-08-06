package com.windmillsteward.jukutech.activity.home.houselease.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.HouseMoreBean;
import com.windmillsteward.jukutech.bean.HouseSealListBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/1/19
 * 作者：xjh
 */

public interface BuyHouseView extends BaseViewModel {

    /**
     * 初始化数据
     * @param bean
     */
    void initDataSuccess(HouseSealListBean bean);
    /**
     * 初始化数据
     * @param bean
     */
    void refreshDataSuccess(HouseSealListBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();
    /**
     * 初始化数据
     * @param bean
     */
    void loadNextDataSuccess(HouseSealListBean bean);

    /**
     * 加载下一页失败
     */
    void loadNextDataFailure();

    /**
     * 加载区域成功
     * @param maps
     */
    void loadAreaDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载分类数据成功
     * @param maps
     */
    void loadClassDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载价格数据成功
     * @param maps
     */
    void loadPriceDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载时间数据成功
     * @param maps
     */
    void loadTimeDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载更多成功
     * @param bean
     */
    void loadMoreDataSuccess(HouseMoreBean bean);
}
