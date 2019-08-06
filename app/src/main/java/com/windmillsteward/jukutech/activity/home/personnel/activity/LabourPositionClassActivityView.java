package com.windmillsteward.jukutech.activity.home.personnel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.LabourPositionClassBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/8/1
 * 作者：cyq
 */
public interface LabourPositionClassActivityView extends BaseViewModel {

    void initDataSuccess(List<LabourPositionClassBean> list);

    void refreshDataSuccess(List<LabourPositionClassBean> list);
}
