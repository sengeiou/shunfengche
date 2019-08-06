package com.windmillsteward.jukutech.bean;

import com.windmillsteward.jukutech.base.BaseData;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/22/022
 * 作者：xjh
 */
public class UsedCarHomeBean extends BaseData {

    private List<RecommendCarListBean> recommendRarListBeans;
    private List<CarClassListBean> carClassListBeans;
    private List<CarPriceListBean> carPriceListBeans;
    private SliderPictureInfo topBanner;

    public SliderPictureInfo getTopBanner() {
        return topBanner;
    }

    public List<RecommendCarListBean> getRecommendRarListBeans() {
        return recommendRarListBeans;
    }

    public void setRecommendRarListBeans(List<RecommendCarListBean> recommendRarListBeans) {
        this.recommendRarListBeans = recommendRarListBeans;
    }

    public List<CarClassListBean> getCarClassListBeans() {
        return carClassListBeans;
    }

    public void setCarClassListBeans(List<CarClassListBean> carClassListBeans) {
        this.carClassListBeans = carClassListBeans;
    }

    public List<CarPriceListBean> getCarPriceListBeans() {
        return carPriceListBeans;
    }

    public void setCarPriceListBeans(List<CarPriceListBean> carPriceListBeans) {
        this.carPriceListBeans = carPriceListBeans;
    }

    public void setTopBanner(SliderPictureInfo topBanner) {
        this.topBanner = topBanner;
    }
}
