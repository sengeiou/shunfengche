package com.windmillsteward.jukutech.activity.home.family.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.AuthenResultBean;

/**
 * 描述：
 * 时间：2018/1/17
 * 作者：xjh
 */

public interface IntelligentFamilyListView extends BaseViewModel {

    /**
     * 判断是否认证
     */
    void isAuthen(AuthenResultBean bean);
}
