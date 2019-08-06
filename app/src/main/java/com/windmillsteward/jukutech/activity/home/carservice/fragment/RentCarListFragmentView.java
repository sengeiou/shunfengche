package com.windmillsteward.jukutech.activity.home.carservice.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.RentCarListBean;

/**
 * Created by Administrator on 2018/4/3.
 */

public interface RentCarListFragmentView extends BaseViewModel {

    /**
     * 初始化数据成功
     * @param bean
     */
    void initDataSuccess(RentCarListBean bean);

    /**
     * 刷新数据成功
     * @param bean
     */
    void refreshDataSuccess(RentCarListBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页
     * @param bean
     */
    void loadNextDataSuccess(RentCarListBean bean);

    /**
     * 加载下一页失败
     */
    void loadNextDataFailure();


    void isChargeResult(ChargeResultBean bean);

    void isAuthen(AuthenResultBean bean);
}
