package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.IdeaThinkDetailBean;


/**
 * 描述：
 * 时间：2018/2/5
 * 作者：xjh
 */

public interface EditIdeaThinkDetailView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean
     */
    void initDataSuccess(IdeaThinkDetailBean bean);

    /**
     * 删除
     */
    void deleteIdeaThinkSuccess();
}
