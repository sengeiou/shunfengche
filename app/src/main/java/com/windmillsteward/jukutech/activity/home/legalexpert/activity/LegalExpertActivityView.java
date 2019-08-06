package com.windmillsteward.jukutech.activity.home.legalexpert.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;

import java.util.List;

/**
 *
 * Created by Administrator on 2018/4/10 0010.
 */

public interface LegalExpertActivityView extends BaseViewModel {


    void getTopBannerListSuccess(List<SliderPictureInfo> list);

    /**
     * 判断是否认证
     */
    void isAuthen(AuthenResultBean bean);


}
