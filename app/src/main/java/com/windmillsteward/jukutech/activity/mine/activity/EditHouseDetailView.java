package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.HouseDetailBeam;
import com.windmillsteward.jukutech.bean.HouseMoreBean;

/**
 * 描述：
 * 时间：2018/2/6
 * 作者：xjh
 */

public interface EditHouseDetailView extends BaseViewModel {

    /**
     * 初始化
     * @param beam
     */
    void initDataSuccess(HouseDetailBeam beam);


    void loadRentTypeDataSuccessF(HouseMoreBean bean);

    /**
     * 删除成功
     */
    void deleteSuccess();
}
