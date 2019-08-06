package com.windmillsteward.jukutech.activity.home.carservice.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.CarListBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/3/28/028
 * 作者：xjh
 */
public interface CarListFragmentView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean
     */
    void initDataSuccess(CarListBean bean);

    /**
     * 刷新数据成功
     * @param bean
     */
    void refreshDataSuccess(CarListBean bean);

    /**
     * 刷新数据失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页成功
     * @param bean
     */
    void loadNextDataSuccess(CarListBean bean);

    /**
     * 加载下一页失败
     */
    void loadNextDataFailure();

    /**
     * 加载地区
     * @param list
     */
    void loadAreaDataSuccess(List<ThirdAreaBean> list);

    /**
     * 加载价格数据成功
     * @param list
     */
    void loadPriceDataSuccess(List<Map<String, Object>> list);

    /**
     * 加载更多筛选成功
     * @param list1
     */
    void loadMoreClassDataSuccess(List<Map<String, Object>> list1, List<Map<String, Object>> list2, List<Map<String, Object>> list3);
}
