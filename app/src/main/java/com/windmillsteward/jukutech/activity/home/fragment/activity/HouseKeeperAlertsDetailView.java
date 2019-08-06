package com.windmillsteward.jukutech.activity.home.fragment.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.HouseKeeperDataQuickBean;

/**
 * 描述：管家快讯详情回调
 * author:cyq
 * 2018-02-06
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface HouseKeeperAlertsDetailView extends BaseViewModel {
    /**
     * 成功
     * @param bean
     */
    void getDataSuccess(HouseKeeperDataQuickBean bean);

    /**
     * 失败
     * @param code
     * @param msg
     */
    void getDataFailed(int code, String msg);


}
