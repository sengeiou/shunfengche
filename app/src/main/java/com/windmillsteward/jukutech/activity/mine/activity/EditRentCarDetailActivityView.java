package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.RentCarDetailBean;

/**
 * 描述：
 * 时间：2018/3/29/029
 * 作者：xjh
 */
public interface EditRentCarDetailActivityView extends BaseViewModel {

    /**
     * 初始化数据成功
     */
    void initDataSuccess(RentCarDetailBean bean);

    /**
     * 收藏
     */
    void deleteSuccess();
}
