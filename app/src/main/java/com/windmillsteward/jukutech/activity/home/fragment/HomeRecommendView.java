package com.windmillsteward.jukutech.activity.home.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.GuessYouLikeBean;
import com.windmillsteward.jukutech.bean.RecommendGridBean;

import java.util.List;

/**
 * 描述：首页推荐回调
 * author:cyq
 * 2018-02-06
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface HomeRecommendView extends BaseViewModel {
    /**
     * 成功
     * @param data
     */
    void getHomeRecommendDataSuccess(List<RecommendGridBean> data);

    /**
     * 失败
     * @param code
     * @param msg
     */
    void getHomeRecommendDataFailed(int code, String msg);

    /**
     * 猜你喜欢列表
     */
    void getGuessYouLikeListSuccess(GuessYouLikeBean bean);

    void getGuessYouLikeListFailed(int code, String msg);

}
