package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.BuyCarDetailBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;

/**
 * 描述：
 * 时间：2018/3/29/029
 * 作者：xjh
 */
public interface EditBuyCarDetailActivityView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean
     */
    void initDataSuccess(BuyCarDetailBean bean);

    /**
     * 删除
     */
    void deleteSuccess();

}
