package com.windmillsteward.jukutech.activity.home.personnel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.ResumeDetailBean;

/**
 * 描述：
 * 时间：2018/1/15
 * 作者：xjh
 */

public interface ResumeDetailView extends BaseViewModel {

    /**
     * 初始化数据
     */
    void initDataSuccess(ResumeDetailBean bean);

    /**
     * 收藏
     */
    void collectionSuccess();

    /**
     * 取消收藏
     */
    void cancelCollectSuccess();

    /**
     * 是否收费
     * @param bean
     */
    void isChargeResult(ChargeResultBean bean);
}
