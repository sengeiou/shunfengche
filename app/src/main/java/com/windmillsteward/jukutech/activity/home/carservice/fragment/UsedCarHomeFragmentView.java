package com.windmillsteward.jukutech.activity.home.carservice.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.bean.UsedCarHomeBean;

/**
 * 描述：
 * 时间：2018/3/22/022
 * 作者：xjh
 */
public interface UsedCarHomeFragmentView extends BaseViewModel {

    /**
     * 初始化数据成功
     */
    void initDataSuccess(UsedCarHomeBean bean);

    void isAuthen(AuthenResultBean bean);
}
