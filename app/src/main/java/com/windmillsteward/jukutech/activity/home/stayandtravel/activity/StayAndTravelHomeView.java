package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.BannerBean;
import com.windmillsteward.jukutech.bean.TravelListBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/25
 * 作者：xjh
 */

public interface StayAndTravelHomeView extends BaseViewModel {

    /**
     * 初始化banner数据成功
     */
    void initBannerDataSuccess(List<BannerBean> list);
    /**
     * 初始化旅游推荐数据成功
     */
    void initDataSuccess(TravelListBean bean);
}
