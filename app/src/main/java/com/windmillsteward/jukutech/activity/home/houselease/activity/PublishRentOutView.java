package com.windmillsteward.jukutech.activity.home.houselease.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.HouseConfigBean;
import com.windmillsteward.jukutech.bean.HouseMoreBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/2/6
 * 作者：xjh
 */

public interface PublishRentOutView extends BaseViewModel{

    /**
     * 加载房屋配置成功
     * @param beans
     */
    void loadHouseConfigSuccess(List<HouseConfigBean> beans);
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
     * 加载押金
     * @param maps
     */
    void loadRentDepositDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载租房类型
     * @param maps
     */
//    void loadRentTypeDataSuccess(List<Map<String, Object>> maps);

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
     * @param data
     */
    void publishSuccess(String data);

    /**
     * 判断发布是否需要收费
     * @param bean
     */
    void isChargeResult(ChargeResultBean bean);

//    void loadRentTypeDataSuccessF(HouseMoreBean bean);
}
