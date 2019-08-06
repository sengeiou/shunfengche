package com.windmillsteward.jukutech.activity.home.commons.quickindex;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/2/2
 * 作者：xjh
 */

public interface QuickIndexAreaView extends BaseViewModel {

    /**
     * 区列表数据成功
     * @param list
     */
    void initDataSuccess(List<ThirdAreaBean> list);
}
