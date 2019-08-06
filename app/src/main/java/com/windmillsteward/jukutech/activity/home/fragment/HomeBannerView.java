package com.windmillsteward.jukutech.activity.home.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;

import java.util.List;

/**
 * 描述：轮播图回调
 * author:cyq
 * 2018-02-06
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface HomeBannerView extends BaseViewModel {
    /**
     * 成功
     * @param list
     */
    void getTopBannerListSuccess(List<SliderPictureInfo> list);

    /**
     * 失败
     * @param code
     * @param msg
     */
    void getTopBannerListFailed(int code, String msg);

    /**
     * 成功
     * @param list
     */
    void getMiddleBannerListSuccess(List<SliderPictureInfo> list);

    /**
     * 失败
     * @param code
     * @param msg
     */
    void getMiddleBannerListFailed(int code, String msg);
}
