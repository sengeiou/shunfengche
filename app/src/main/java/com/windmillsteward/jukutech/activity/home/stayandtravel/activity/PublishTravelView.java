package com.windmillsteward.jukutech.activity.home.stayandtravel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.FileUploadResultBean;
import com.windmillsteward.jukutech.bean.PublishTravelResultBean;
import com.windmillsteward.jukutech.bean.TravelClassBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/1/26
 * 作者：xjh
 */

public interface PublishTravelView extends BaseViewModel {

    /**
     * 加载旅游分类成功 弹窗显示
     * @param maps 数据
     */
    void loadTravelClassSuccess(List<Map<String, Object>> maps);

    /**
     * 加载旅游地区 弹窗显示
     * @param maps 数据
     */
    void loadTravelAreaDataSuccess(List<Map<String, Object>> maps);

    /**
     * 加载发布地区数据成功
     * @param maps
     */
    void loadPublishAreaDataSuccess(List<Map<String, Object>> maps);

    /**
     * 图片上传成功
     * @param bean 返回结果
     */
    void uploadFileSuccess(FileUploadResultBean bean);

    /**
     * 发布成功
     */
    void publishSuccess(PublishTravelResultBean bean);

    /**
     * 判断是否收费
     */
    void isChargeResult(ChargeResultBean bean);
}
