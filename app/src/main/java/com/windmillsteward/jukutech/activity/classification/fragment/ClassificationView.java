package com.windmillsteward.jukutech.activity.classification.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ClassificationMenuBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/2/4/004
 * 作者：xjh
 */
public interface ClassificationView extends BaseViewModel {

    /**
     * 初始化分类数据成功
     * @param menus
     */
    void initMenuDataSuccess(List<ClassificationMenuBean> menus);

    void initMenuDetailDataSuccess();
}
