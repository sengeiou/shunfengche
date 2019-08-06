package com.windmillsteward.jukutech.activity.mine.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.SpecialtyOrderListBean;

/**
 * Created by Administrator on 2018/4/18 0018.
 */

public interface SpecialtyOrderListFragmentView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean
     */
    void initDataSuccess(SpecialtyOrderListBean bean);

    /**
     * 刷新数据
     * @param bean
     */
    void refreshDataSuccess(SpecialtyOrderListBean bean);

    void refreshDataFailure();

    /**
     * 加载下一页
     * @param bean
     */
    void loadNextDataSuccess(SpecialtyOrderListBean bean);

    /**
     * 加载下一页失败
     */
    void loadNextDataFailure();

    /**
     * 删除订单成功
     */
    void deleteOrderSuccess();

    /**
     * 关闭订单
     */
    void closeOrderSuccess();

    /**
     * 确认收货
     */
    void confirmOrderSuccess();
}
