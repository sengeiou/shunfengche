package com.windmillsteward.jukutech.activity.home.think.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.IdeaThinkClassBean;
import com.windmillsteward.jukutech.bean.SpecialtyHomeRecommendBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/2/5/005
 * 作者：xjh
 */
public interface ChoiceWisdomFireControlView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param data
     */
    void initDataSuccess(List<SpecialtyHomeRecommendBean> data);
}
