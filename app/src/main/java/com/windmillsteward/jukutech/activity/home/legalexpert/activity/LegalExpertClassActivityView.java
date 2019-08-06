package com.windmillsteward.jukutech.activity.home.legalexpert.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.NameAndIdBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public interface LegalExpertClassActivityView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param list
     */
    void initDataSuccess(List<NameAndIdBean> list);
}
