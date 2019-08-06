package com.windmillsteward.jukutech.activity.home.personnel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.EmployResumeListBean;

/**
 * 描述：
 * 时间：2018/1/13/013
 * 作者：xjh
 */
public interface ApplyListView extends BaseViewModel{

    /**
     * 初始化
     * @param bean
     */
    void initDataSuccess(EmployResumeListBean bean);

    /**
     * 刷新
     * @param bean
     */
    void refreshDataSuccess(EmployResumeListBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页
     * @param bean
     */
    void loadNextDataSuccess(EmployResumeListBean bean);

    /**
     * 加载下一页失败
     * @param msg
     */
    void loadNextError(String msg);


}
