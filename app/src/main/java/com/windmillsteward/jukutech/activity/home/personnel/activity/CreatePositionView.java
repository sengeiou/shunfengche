package com.windmillsteward.jukutech.activity.home.personnel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.JobClassBean;
import com.windmillsteward.jukutech.bean.MoreBean;
import com.windmillsteward.jukutech.bean.PostPositionResultBean;
import com.windmillsteward.jukutech.bean.SalaryBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.bean.WelfareBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/14/014
 * 作者：xjh
 */
public interface CreatePositionView extends BaseViewModel {

    /**
     * 加载职业分类
     */
    void loadPositionClassDataSuccess(List<JobClassBean> list);
    /**
     * 加载薪资
     */
    void loadSalaryDataSuccess(List<SalaryBean> list);

    /**
     * 加载区域
     */
    void loadAreaDataSuccess(List<ThirdAreaBean> list);

    /**
     * 加载工作
     */
    void loadWorkDataSuccess(List<MoreBean.WordYearListBean> bean);

    /**
     * 加载学历
     */
    void loadEduDataSuccess(List<MoreBean.EducationListBean> bean);

    /**
     * 加载福利
     */
    void loadWelfareDataSuccess(List<WelfareBean> list);

    /**
     * 加载发布地区
     */
    void loadPostAreaDataSuccess(List<ThirdAreaBean> list);

    /**
     * 提交
     */
    void postResult(PostPositionResultBean bean);

    /**
     * 是否收费
     */
    void isCharge(ChargeResultBean bean);
}
