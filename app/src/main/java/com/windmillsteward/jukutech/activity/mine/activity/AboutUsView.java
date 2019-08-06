package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.AboutUsBean;

import java.util.List;

/**
 * 描述：关于我们回调
 * author:cyq
 * 2018-03-13
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface AboutUsView extends BaseViewModel {

    /**
     * 获取关于我们数据成功
     */
    void getAboutUsDataSuccess(List<AboutUsBean> beanList);
    /**
     * 获取关于我们数据失败
     */
    void getAboutUsDataFailed(int code,String msg);
}
