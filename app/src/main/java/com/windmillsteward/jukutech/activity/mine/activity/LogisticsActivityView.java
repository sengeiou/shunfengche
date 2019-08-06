package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.LogisticsInfoListBean;

/**
 * Created by Administrator on 2018/4/21 0021.
 */

public interface LogisticsActivityView extends BaseViewModel {

    /**
     * 物流
     */
    void initDataSuccess(LogisticsInfoListBean bean);
}
