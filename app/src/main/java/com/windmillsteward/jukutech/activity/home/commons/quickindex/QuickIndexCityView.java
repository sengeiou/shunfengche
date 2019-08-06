package com.windmillsteward.jukutech.activity.home.commons.quickindex;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.AreaBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/26
 * 作者：xjh
 */

public interface QuickIndexCityView extends BaseViewModel {

    /**
     * 加载数据成功
     * @param list 地区数据
     */
    void initData(List<AreaBean> list);
}
