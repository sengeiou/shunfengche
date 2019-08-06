package com.windmillsteward.jukutech.activity.home.personnel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.PositionClassBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/5/16/016
 * 作者：xjh
 */
public interface PositionClassActivityView extends BaseViewModel {

    void initDataSuccess(List<PositionClassBean> list);

    void refreshDataSuccess(List<PositionClassBean> list);
}
