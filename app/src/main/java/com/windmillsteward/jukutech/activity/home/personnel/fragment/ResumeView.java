package com.windmillsteward.jukutech.activity.home.personnel.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.MoreBean;
import com.windmillsteward.jukutech.bean.ResumeListBean;
import com.windmillsteward.jukutech.bean.SalaryBean;
import com.windmillsteward.jukutech.bean.SexBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/10
 * 作者：xjh
 */

public interface ResumeView extends BaseViewModel {

    /**
     * 初始化
     * @param bean
     */
    void initDataSuccess(ResumeListBean bean);

    /**
     * 刷新
     * @param bean
     */
    void refreshDataSuccess(ResumeListBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页
     * @param bean
     */
    void loadNextDataSuccess(ResumeListBean bean);

    /**
     * 加载下一页失败
     * @param msg
     */
    void loadNextError(String msg);

    /**
     * 加载地区
     * @param list
     */
    void loadAreaDataSuccess(List<ThirdAreaBean> list);

    void loadSexDataSuccess(List<SexBean> list);

    /**
     * 加载薪资
     * @param list
     */
    void loadSalaryDataSuccess(List<SalaryBean> list);

    /**
     * 加载更多的配置
     * @param bean
     */
    void loadMoreDataSuccess(MoreBean bean);
}
