package com.windmillsteward.jukutech.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.CheckVersionUpdateBean;

/**
 * 描述：检查更新回调
 * author:cyq
 * 2018-04-11
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface CheckVersionUpdateView extends BaseViewModel {
    /**
     * 成功
     * @param bean
     */
    void getCheckVersionDataSuccess(CheckVersionUpdateBean bean);

    /**
     * 失败
     * @param code
     * @param msg
     */
    void getCheckVersionDataFailed(int code,String msg);
}
