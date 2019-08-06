package com.windmillsteward.jukutech.activity.home.capitalmanager.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public interface PublishView extends BaseViewModel {

    /**
     * 加载产品
     * @param maps
     */
    void loadProductTypeDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载发布地区成功
     * @param maps
     */
    void loadPublishAreaDataSuccess(List<Map<String, Object>> maps);

    /**
     * 上传图片成功
     * @param pic_url
     */
    void uploadPicSuccess(String pic_url);

    void uploadPicSuccess(List<String> img_urls);

    void isChargeResult(ChargeResultBean bean);

    void publishSuccess();
}
