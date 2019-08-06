package com.windmillsteward.jukutech.activity.mine.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.FundsTrusteeshipBean;

/**
 * 描述：资金托管列表回调
 * author:cyq
 * 2018-03-07
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface FundsTrusteeshipView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean
     */
    void initDataSuccess(FundsTrusteeshipBean bean);

    /**
     * 刷新数据成功
     * @param bean
     */
    void refreshDataSuccess(FundsTrusteeshipBean bean);

    void refreshDataFailure();

    /**
     * 加载下一页数据成功
     * @param bean
     */
    void loadNextDataSuccess(FundsTrusteeshipBean bean);

    void loadNextDataFailure();
}
