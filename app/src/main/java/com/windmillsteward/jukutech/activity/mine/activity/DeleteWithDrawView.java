package com.windmillsteward.jukutech.activity.mine.activity;


import com.windmillsteward.jukutech.base.BaseViewModel;

/**
 * 描述：删除提现账号
 * author:cyq
 * 2017/6/21
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface DeleteWithDrawView extends BaseViewModel {

    /**
     * 删除成功
     */
    void deleteSuccess();

    /**
     * 删除失败
     */
    void deleteFailed(String msg);
}
