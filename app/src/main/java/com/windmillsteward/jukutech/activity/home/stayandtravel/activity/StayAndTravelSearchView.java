package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.StayAndTravelSearchResultBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/7/007
 * 作者：xjh
 */
public interface StayAndTravelSearchView extends BaseViewModel {

    /**
     * 搜索结果
     * @param bean
     */
    void searchResult(StayAndTravelSearchResultBean bean);
}
