package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.HotelConfigsBean;
import com.windmillsteward.jukutech.bean.HotelTypeBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/2/1
 * 作者：xjh
 */

public interface PublishHotelAndHouseView extends BaseViewModel {

    /**
     * 加载房屋分类数据成功
     * @param maps
     */
    void loadHouseTypeDataSuccess(List<Map<String, Object>> maps);


    /**
     * 加载区域数据成功
     * @param maps maps
     */
    void loadAreaDataSuccess(List<Map<String, Object>> maps);


    /**
     * 加载膳食安排数据成功
     * @param maps
     */
    void loadFoodSupplyDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载是否可以携带宠物数据成功
     * @param maps
     */
    void loadTakePetDataSuccess(List<Map<String, Object>> maps);

    /**
     * 预定房间是否收定金
     * @param maps
     */
    void loadCollectionDepositDataSucces(List<Map<String, Object>> maps);

    /**
     * 加载发布地区数据成功
     * @param maps
     */
    void loadPublishAreaDataSuccess(List<Map<String, Object>> maps);

    /**
     * 上传图片成功
     * @param pic_urls
     */
    void uploadPicSuccess(List<String> pic_urls);

    /**
     * 上传执照成功
     * @param license_url
     */
    void uploadLicenseSuccess(List<String> license_url);

    /**
     * 上传身份证成功
     * @param idCard_url
     */
    void uploadIdCardSuccess(List<String> idCard_url);

    /**
     * 上传身份证反面成功
     * @param idCard_url_b
     */
    void uploadIdCardSuccess_b(List<String> idCard_url_b);
    /**
     * 发布成功
     */
    void publishSuccess(String msg);

    /**
     * 判断是否收费
     * @param bean
     */
    void isChargeResult(ChargeResultBean bean);

    /**
     * 加载酒店配置
     * @param bean
     */
    void loadHotelConfigsResuccess(HotelConfigsBean bean);
}
