package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.IntelligentFamilyDetailBean;

/**
 * 描述：
 * 时间：2018/1/17
 * 作者：xjh
 */

public interface EditIntelligentFamilyDetailView extends BaseViewModel {

    /**
     * 需求详情
     */
    void initData(IntelligentFamilyDetailBean bean);

    /**
     * 删除
     */
    void deleteRequireSuccess();

    /**
     * 取消订单成功
     */
    void cancelRequireSuccess();

    /**
     * 确认订单
     */
    void sureRequireSuccess();

    /**
     * 将需求标记成未完成
     */
    void onFinishRequireSuccess();

    /**
     * 标记完成
     */
    void finishRequireSuccess();
}
