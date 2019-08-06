package com.windmillsteward.jukutech.activity.classification.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ClassificationPersonalBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/14/014
 * 作者：xjh
 */
public interface ClassificationDetailView extends BaseViewModel {

    /**
     * 加载人才驿站分类成功
     * @param list
     */
    void onLoadClassSuccess(List<ClassificationPersonalBean> list);
}
