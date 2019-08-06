package com.windmillsteward.jukutech.activity.home.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.HouseKeeperDataQuickBean;

/**
 * 描述：轮播图回调
 * author:cyq
 * 2018-02-06
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface HouseKeeperDataQuickView extends BaseViewModel {
    /**
     * 成功
     */
    void getHouseKeeperDataQuickListSuccess(HouseKeeperDataQuickBean houseKeeperDataQuickBean);

    /**
     * 失败
     * @param code
     * @param msg
     */
    void getHouseKeeperDataQuickListFailed(int code, String msg);

    /**
     * 初始化数据
     */
    void getHouseKeeperInitDataSuccess(HouseKeeperDataQuickBean houseKeeperDataQuickBean);

    /**
     * 刷新
     * @param bean
     */
    void getHouseKeeperRefreshDataSuccess(HouseKeeperDataQuickBean bean);

    /**
     * 刷新失败
     */
    void getHouseKeeperRefreshDataFailure();

    /**
     * 加载下一页
     * @param bean
     */
    void getHouseKeeperLoadNextDataSuccess(HouseKeeperDataQuickBean bean);

    /**
     * 加载下一页失败
     */
    void getHouseKeeperLoadNextFailure();

}
