package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.LegalDetailBean;

/**
 * 描述：
 * 时间：2018/4/22/022
 * 作者：xjh
 */
public interface EditLegalExpertActivityView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean 返回数据
     */
    void initDataSuccess(LegalDetailBean bean);

    /**
     * 删除
     */
    void deleteIdeaThinkSuccess();
}
