package com.windmillsteward.jukutech.activity.home.family.activity;

import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.IntelligentFamilyDetailBean;

/**
 * 描述：
 * 时间：2018/1/17
 * 作者：xjh
 */

public interface IntelligentFamilyDetailView extends BaseViewModel {

    /**
     * 需求详情
     */
    void initData(IntelligentFamilyDetailBean bean);

    /**
     * 收藏成功
     */
    void collectionRequireSuccess();

    /**
     * 抢单成功
     */
    void getOrderSuccess(PublicResultModel resultModel,String msg);

    /**
     * 取消收藏
     */
    void cancelCollectionRequireSuccess();
}
