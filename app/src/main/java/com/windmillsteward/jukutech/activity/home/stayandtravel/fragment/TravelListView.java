package com.windmillsteward.jukutech.activity.home.stayandtravel.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.TravelListBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/1/25
 * 作者：xjh
 */

public interface TravelListView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean 数据
     */
    void initDataSuccess(TravelListBean bean);

    /**
     * 刷新成功
     * @param bean data
     */
    void refreshDataSuccess(TravelListBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页数据成功
     * @param bean 数据
     */
    void loadNextDataSuccess(TravelListBean bean);

    /**
     * 加载下一页失败
     */
    void loadNextDataFailure();

    /**
     * 加载地区数据成功
     * @param maps 数据
     */
    void loadAreaDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载旅游分类成功
     * @param maps
     */
    void loadTravelAreaDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载旅游价格数据成功
     */
    void loadTravelPriceSuccess(List<Map<String, Object>> maps);
}
