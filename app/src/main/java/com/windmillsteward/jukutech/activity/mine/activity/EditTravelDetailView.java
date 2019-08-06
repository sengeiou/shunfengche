package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.TravelDetailBean;

/**
 * 描述：
 * 时间：2018/1/25
 * 作者：xjh
 */

public interface EditTravelDetailView extends BaseViewModel{

    /**
     * 初始化数据成功
     * @param bean 返回数据
     */
    void initDataSuccess(TravelDetailBean bean);

    /**
     * 删除
     */
    void deleteIdeaThinkSuccess();
}
