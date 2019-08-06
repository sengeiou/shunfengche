package com.windmillsteward.jukutech.activity.home.insurance.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.BigHealthListBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/18/018
 * 作者：xjh
 */
public interface BigHealthListFragmentView extends BaseViewModel {
    /**
     * 初始化数据
     */
    void initDataSuccess(BigHealthListBean bean);

    /**
     * 刷新
     * @param bean
     */
    void refreshDataSuccess(BigHealthListBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页
     * @param bean
     */
    void loadNextDataSuccess(BigHealthListBean bean);

    /**
     * 加载下一页失败
     */
    void loadNextFailure();

    /**
     * 加载地区
     */
    void loadAreaDataSuccess(List<ThirdAreaBean> list);
}
