package com.windmillsteward.jukutech.activity.home.insurance.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.InsuranceListBean;
import com.windmillsteward.jukutech.bean.IntelligentFamilyBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/3/18/018
 * 作者：xjh
 */
public interface InsuranceListFragmentView extends BaseViewModel {
    /**
     * 初始化数据
     */
    void initDataSuccess(InsuranceListBean bean);

    /**
     * 刷新
     * @param bean
     */
    void refreshDataSuccess(InsuranceListBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页
     * @param bean
     */
    void loadNextDataSuccess(InsuranceListBean bean);

    /**
     * 加载下一页失败
     */
    void loadNextFailure();

    /**
     * 加载地区
     */
    void loadAreaDataSuccess(List<ThirdAreaBean> list);

    /**
     * 加载险种
     * @param list
     */
    void loadInsuranceListTypeSuccess(List<Map<String, Object>> list);
}
