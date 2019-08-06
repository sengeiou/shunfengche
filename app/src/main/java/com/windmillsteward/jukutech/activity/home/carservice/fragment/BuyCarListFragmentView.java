package com.windmillsteward.jukutech.activity.home.carservice.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.bean.BuyCarListBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/3/25/025
 * 作者：xjh
 */
public interface BuyCarListFragmentView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean
     */
    void initDataSuccess(BuyCarListBean bean);

    /**
     * 刷新数据成功
     * @param bean
     */
    void refreshDataSuccess(BuyCarListBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页
     * @param bean
     */
    void loadNextDataSuccess(BuyCarListBean bean);

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
     * 加载价格选项
     * @param list
     */
    void loadPriceDataSuccess(List<Map<String, Object>> list);

    /**
     * 是否授权
     * @param bean
     */
    void isAuthen(AuthenResultBean bean);
}
