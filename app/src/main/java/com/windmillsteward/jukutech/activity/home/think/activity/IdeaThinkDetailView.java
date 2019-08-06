package com.windmillsteward.jukutech.activity.home.think.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.IdeaThinkDetailBean;


/**
 * 描述：
 * 时间：2018/2/5
 * 作者：xjh
 */

public interface IdeaThinkDetailView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean
     */
    void initDataSuccess(IdeaThinkDetailBean bean);

    void collectSuccess();

    void cancelCollectSuccess();

    /**
     * 判断电话是否收费
     * @param bean 返回结果
     */
    void isChargeResult(ChargeResultBean bean);
}
