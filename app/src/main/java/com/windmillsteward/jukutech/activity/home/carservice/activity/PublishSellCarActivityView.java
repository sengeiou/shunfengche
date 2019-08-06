package com.windmillsteward.jukutech.activity.home.carservice.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/4/2/002
 * 作者：xjh
 */
public interface PublishSellCarActivityView extends BaseViewModel {

    /**
     * 加载变速箱成功
     * @param maps
     */
    void loadGearboxDataSuccess(List<Map<String,Object>> maps);

    /**
     * 加载车辆颜色成功
     * @param maps
     */
    void loadCarColorDataSuccess(List<Map<String,Object>> maps);

    /**
     * 加载交易地区成功
     * @param maps
     */
    void loadDealAreaDataSuccess(List<Map<String, Object>> maps);

    /**
     * 上传图片成功
     * @param pic_url
     */
    void uploadPicSuccess(String pic_url);

    /**
     * 上传图片成功
     * @param pic_urls
     */
    void uploadPicSuccess(List<String> pic_urls);

    /**
     * 加载发布地区成功
     * @param maps
     */
    void loadPublishAreaDataSuccess(List<Map<String, Object>> maps);

    void publishSuccess();

    void isCharge(ChargeResultBean bean);
}
