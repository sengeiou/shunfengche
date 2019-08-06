package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by Administrator on 2018/4/21 0021.
 */

public interface ApplySaleRefundActivityView extends BaseViewModel {

    /**
     * 加载申请原因
     */
    void loadAplyReasonDataSuccess(List<Map<String,Object>> maps);

    /**
     * 加载数量
     */
    void loadNumberDataSuccess(List<Map<String,Object>> maps);

    /**
     * 退款方式
     */
    void loadRefundWayDataSuccess(List<Map<String,Object>> maps);

    /**
     * 上传图片成功
     * @param pic_url
     */
    void uploadPicSuccess(String pic_url);

    void applySuccess();
}
