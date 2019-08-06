package com.windmillsteward.jukutech.activity.home.commons.search;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.SearchHistoryBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/17
 * 作者：xjh
 */

public interface SearchItemView extends BaseViewModel {

    /**
     * 加载历史搜索记录
     */
    void onLoadHistory(List<SearchHistoryBean> list);
}
