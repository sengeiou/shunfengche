package com.windmillsteward.jukutech.activity.home.specialty.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.GoodsClassListBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public interface GoodsClassActivityView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param list
     */
    void initDataSuccess(List<GoodsClassListBean> list);
}
