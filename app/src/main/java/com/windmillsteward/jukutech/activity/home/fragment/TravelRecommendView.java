package com.windmillsteward.jukutech.activity.home.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.TravelRecommendBean;

/**
 * 描述：旅游推荐回调
 * author:cyq
 * 2018-02-06
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface TravelRecommendView extends BaseViewModel {
    /**
     * 成功
     * @param data
     */
    void getTravelRecommendDataSuccess(TravelRecommendBean data);

    /**
     * 失败
     * @param code
     * @param msg
     */
    void getTravelRecommendDataFailed(int code, String msg);

}
