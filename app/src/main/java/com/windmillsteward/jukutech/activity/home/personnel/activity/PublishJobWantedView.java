package com.windmillsteward.jukutech.activity.home.personnel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.MoreBean;
import com.windmillsteward.jukutech.bean.PostResultBean;
import com.windmillsteward.jukutech.bean.SalaryBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;
import com.windmillsteward.jukutech.bean.UploadResultBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/13/013
 * 作者：xjh
 */
public interface PublishJobWantedView extends BaseViewModel {

    /**
     * 加载学历
     */
    void loadEduDataSuccess(List<MoreBean.EducationListBean> bean);

    /**
     * 加载工作
     */
    void loadWorkDataSuccess(List<MoreBean.WordYearListBean> bean);

    /**
     * 加载薪资
     */
    void loadSalaryDataSuccess(List<SalaryBean> list);

    /**
     * 加载区域
     */
    void loadAreaDataSuccess(List<ThirdAreaBean> list);

    /**
     * 加载发布地区
     */
    void loadPublishAreaDataSuccess(List<ThirdAreaBean> list);

    /**
     * 提交
     */
    void postResult(PostResultBean bean);

    /**
     * 上传图片成功
     */
    void uploadSuccess(UploadResultBean bean);

    /**
     * 发布是否收费
     * @param bean
     */
    void isChargeResult(ChargeResultBean bean);
}
