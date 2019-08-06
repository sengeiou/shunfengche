package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.PositionDetailBean;

/**
 * 描述：
 * 时间：2018/1/15
 * 作者：xjh
 */

public interface MyPositionDetailView extends BaseViewModel {

    /**
     * 初始化数据
     */
    void initDataSuccess(PositionDetailBean bean);


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

    /**
     * 删除成功
     */
    void deletePositionSuccess();
}
