package com.windmillsteward.jukutech.activity.home.houselease.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.HouseTypeBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/2/2
 * 作者：xjh
 */

public interface PublishSellHouseView extends BaseViewModel {

    /**
     * 加载朝向
     * @param maps
     */
    void loadCXDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载装修
     * @param maps
     */
    void loadFixtureDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载房屋类型成功
     * @param maps
     */
    void loadHouseTypeDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载产权
     * @param maps
     */
    void loadPropertyRightDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载学位成功
     * @param maps
     */
    void loadDegreeDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载发布地区成功
     * @param maps
     */
    void loadPublishAreaDataSuccess(List<Map<String, Object>> maps);

    /**
     * 上传图片成功
     * @param pic_urls
     */
    void uploadPicSuccess(List<String> pic_urls);

    /**
     * 上传房产证成功
     * @param cert_url
     */
    void uploadCertPicSuccess(String cert_url);

    /**
     * 发布成功
     * @param msg
     */
    void publishSuccess(String msg);

    /**
     * 判断发布是否需要收费
     * @param bean
     */
    void isChargeResult(ChargeResultBean bean);

    /**
     * 对比
     * @param list
     */
    void loadHouseTypeDataSuccessF(List<HouseTypeBean> list);
}
