package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ModuleIntroduceBean;

import java.util.List;

/**
 * 描述：客服电话回调
 * author:cyq
 * 2018-03-12
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface ServicePhoneView extends BaseViewModel {

    /**
     * 成功
     */
    void Success(String tel);

    /**
     * 失败
     * @param code
     * @param msg
     */
    void Failed(int code, String msg);


    void getModuleListSuccess(List<ModuleIntroduceBean> beanList);
}
