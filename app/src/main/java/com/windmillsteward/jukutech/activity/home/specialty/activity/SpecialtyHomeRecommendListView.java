package com.windmillsteward.jukutech.activity.home.specialty.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.GuessYouLikeBean;
import com.windmillsteward.jukutech.bean.SeckillBean;
import com.windmillsteward.jukutech.bean.SliderPictureInfo;
import com.windmillsteward.jukutech.bean.SpecialtyClassBean;
import com.windmillsteward.jukutech.bean.SpecialtyHomeRecommendBean;

import java.util.List;

/**
 * Created by cyq on 2018/6/8
 */

public interface SpecialtyHomeRecommendListView extends BaseViewModel {

    /**
     * 初始化
     */
    void initDataSuccess(List<SpecialtyHomeRecommendBean> bean);

    void initDataFailed(int code, String msg);


    /**
     * 成功
     *
     * @param list
     */
    void getBannerListSuccess(List<SliderPictureInfo> list);

    /**
     * 失败
     *
     * @param code
     * @param msg
     */
    void getBannerListFailed(int code, String msg);

    /**
     * 猜你喜欢列表
     */
    void getGuessYouLikeListSuccess(GuessYouLikeBean bean);

    void getGuessYouLikeListFailed(int code, String msg);

    /**
     * 商品分类
     *
     * @param beanList
     */
    void initSpecialtyClassSuccess(List<SpecialtyClassBean> beanList);

    void initSpecialtyClassFailed(int code, String msg);

    /**
     * 获取秒杀数据
     * @param bean
     */
    void getSeckillDataSuccess(SeckillBean bean);

    void getSeckillDataFaild(int code, String msg);
}
