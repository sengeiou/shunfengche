package com.windmillsteward.jukutech.activity.home.capitalmanager.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.CapitalListBean;
import com.windmillsteward.jukutech.bean.HouseMoreBean;
import com.windmillsteward.jukutech.bean.HouseSealListBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/1/19
 * 作者：xjh
 */

public interface FinancingListFragmentView extends BaseViewModel {

    /**
     * 初始化数据
     * @param bean
     */
    void initDataSuccess(CapitalListBean bean);
    /**
     * 初始化数据
     * @param bean
     */
    void refreshDataSuccess(CapitalListBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();
    /**
     * 初始化数据
     * @param bean
     */
    void loadNextDataSuccess(CapitalListBean bean);

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
     * 加载排序
     * @param maps
     */
    void loadSortingDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载产品
     * @param maps
     */
    void loadProductTypeDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载期限
     * @param maps
     */
    void loadDeadlineDataSuccess(List<Map<String, Object>> maps);
}
