package com.windmillsteward.jukutech.activity.home.family.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.AddRequireResultBean;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.FileUploadResultBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/1/19
 * 作者：xjh
 */

public interface PublishRequireView extends BaseViewModel {

    /**
     * 加载地区
     * @param maps 数据，由于多个列表样式公用，字段只有id更名称，重新构造
     */
    void loadAreaDataSuccess(List<Map<String, Object>> maps);

    /**
     * 文件上传成功回调
     * @param urls 返回url集合
     */
    void uploadFileSuccess(List<String> urls);

    /**
     * 发布成功回调
     * @param bean 返回数据
     */
    void addRequireSuccess(AddRequireResultBean bean);

    /**
     * 是否收费
     * @param bean
     */
    void isCharge(ChargeResultBean bean);
}
