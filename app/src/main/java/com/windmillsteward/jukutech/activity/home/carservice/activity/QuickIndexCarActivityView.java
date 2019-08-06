package com.windmillsteward.jukutech.activity.home.carservice.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.CarClassListBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/28/028
 * 作者：xjh
 */
public interface QuickIndexCarActivityView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param list
     */
    void initDataSuccess(List<CarClassListBean> list);
}
