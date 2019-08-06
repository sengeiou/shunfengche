package com.windmillsteward.jukutech.activity.home.specialty.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.SpecialtyClassListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public interface SpecialtyClassListActivityView extends BaseViewModel {

    /**
     * 初始化
     */
    void initDataSuccess(List<SpecialtyClassListBean> bean);
}
