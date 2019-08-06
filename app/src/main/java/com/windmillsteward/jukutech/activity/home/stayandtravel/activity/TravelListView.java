package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.AuthenResultBean;

/**
 * 描述：
 * 时间：2018/3/11/011
 * 作者：xjh
 */
public interface TravelListView extends BaseViewModel {

    /**
     * 判断是否认证
     */
    void isAuthen(AuthenResultBean bean);
}
