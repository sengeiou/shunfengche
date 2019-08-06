package com.windmillsteward.jukutech.activity.home.fragment.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.WelcomeBean;

/**
 * 描述：欢迎页回调
 * author:cyq
 * 2019-3-7
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface WelcomeView extends BaseViewModel {

    void getWelcomePicSuccess(WelcomeBean welcomeBean);

    void getWelcomePicFailed(int code, String msg);

}
