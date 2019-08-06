package com.windmillsteward.jukutech.activity.home.personnel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.MyPublishBean;
import com.windmillsteward.jukutech.bean.PositionDetailBean;

/**
 * 描述：
 * 时间：2018/1/15
 * 作者：xjh
 */

public interface PositionDetailView extends BaseViewModel {

    /**
     * 初始化数据
     */
    void initDataSuccess(PositionDetailBean bean);

    /**
     * 申请
     */
    void applySuccess();

    /**
     * 收藏
     */
    void collectionSuccess();

    /**
     * 取消收藏
     */
    void cancelCollectionSuccess();

    /**
     * 获取我的简历界面
     * @param bean 数据
     */
    void showResumeListSuccess(MyPublishBean bean);

    /**
     * 判断该职位的电话是否收费
     * @param bean 返回结果
     */
    void isChargeResult(ChargeResultBean bean);
}
