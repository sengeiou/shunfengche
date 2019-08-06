package com.windmillsteward.jukutech.activity.home.family.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.RequireClassBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/19
 * 作者：xjh
 */

public interface ChoiceCLassView extends BaseViewModel {

    /**
     * 初始化数据
     * @param list
     */
    void initData(List<RequireClassBean> list);
}
