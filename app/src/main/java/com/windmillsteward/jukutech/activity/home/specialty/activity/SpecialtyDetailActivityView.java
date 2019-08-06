package com.windmillsteward.jukutech.activity.home.specialty.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.SpecialtyDetailBean;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public interface SpecialtyDetailActivityView extends BaseViewModel {

    /**
     * 初始化数据
     * @param bean
     */
    void initDataSuccess(SpecialtyDetailBean bean);

    /**
     * 收藏成功
     */
    void collectSuccess();

    /**
     * 取消收藏成功
     */
    void cancelCollectSuccess();

    /**
     * 收藏店铺
     */
    void collectStoreSuccess();

    /**
     * 取消店铺收藏
     */
    void cancelcollectStoreSuccess();

    void addToCarSuccess();
}
