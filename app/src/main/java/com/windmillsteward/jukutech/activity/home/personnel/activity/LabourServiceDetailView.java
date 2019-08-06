package com.windmillsteward.jukutech.activity.home.personnel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.LabourServiceDetailBean;

/**
 * 描述：
 * 时间：2018/7/31
 * 作者：xjh
 */

public interface LabourServiceDetailView extends BaseViewModel {

    /**
     * 初始化数据
     */
    void initDataSuccess(LabourServiceDetailBean bean);

    /**
     * 收藏
     */
    void collectionSuccess();

    /**
     * 取消收藏
     */
    void cancelCollectionSuccess();


    /**
     * 判断该职位的电话是否收费
     * @param bean 返回结果
     */
    void isChargeResult(ChargeResultBean bean);
}
