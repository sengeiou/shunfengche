package com.windmillsteward.jukutech.activity.home.commons.quickindex;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.FourthAreaBean;
import com.windmillsteward.jukutech.bean.ThirdAndFourthAreaBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/2/2
 * 作者：xjh
 */

public interface QuickIndexStreetView extends BaseViewModel {

    void initData(List<ThirdAndFourthAreaBean> list);
}
