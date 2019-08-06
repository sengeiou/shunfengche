package com.windmillsteward.jukutech.activity.home.think.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.IdeaThinkClassBean;
import com.windmillsteward.jukutech.bean.IdeaThinkClassBeanTwo;
import com.windmillsteward.jukutech.bean.IdeaThinkListBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/2/5
 * 作者：xjh
 */

public interface IdeaThinkListView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean 数据
     */
    void initDataSuccess(IdeaThinkListBean bean);

    /**
     * 刷新数据成功
     * @param bean
     */
    void refreshDataSuccess(IdeaThinkListBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页成功
     * @param bean
     */
    void loadNextDataSuccess(IdeaThinkListBean bean);

    /**
     * 加载下一页失败
     */
    void loadNextDataFailure();

    /**
     * 加载地区数据成功
     * @param maps 数据
     */
    void loadAreaDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载分类
     * @param list
     */
    void loadClassDataSuccess(List<IdeaThinkClassBean> list);

    /**
     * 加载分类
     * @param list
     */
    void loadClassDataTwoSuccess(List<IdeaThinkClassBeanTwo> list);
}
