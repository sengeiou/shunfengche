package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ResumeDetailBean;

/**
 * 描述：
 * 时间：2018/1/15
 * 作者：xjh
 */

public interface EditResumeDetailView extends BaseViewModel {

    /**
     * 初始化数据
     */
    void initDataSuccess(ResumeDetailBean bean);

    /**
     * 收藏
     */
    void collectionSuccess();

    /**
     * 取消收藏
     */
    void cancelCollectSuccess();

    /**
     * 删除简历成功
     */
    void deleteResumeSuccess();
}
