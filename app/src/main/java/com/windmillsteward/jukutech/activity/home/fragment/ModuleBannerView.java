package com.windmillsteward.jukutech.activity.home.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;

import java.util.List;

/**
 * 描述：模块的轮播图回调
 * author:cyq
 * 2018-02-06
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface ModuleBannerView extends BaseViewModel {
    /**
     * 成功
     * @param list
     */
    void getBannerListSuccess(List<SliderPictureInfo> list);



    /**
     * 失败
     * @param code
     * @param msg
     */
    void getBannerListFailed(int code, String msg);
}
