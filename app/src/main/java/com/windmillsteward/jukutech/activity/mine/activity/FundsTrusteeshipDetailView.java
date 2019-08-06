package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.FundsTrusteeshipDetailBean;

import java.util.List;

/**
 * 描述：资金托管详情回调
 * author:cyq
 * 2018-03-08
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface FundsTrusteeshipDetailView extends BaseViewModel {
    /**
     * 获取资金托管详情成功
     */
    void getFundsTrusteeshipDetailSuccess(FundsTrusteeshipDetailBean fundsTrusteeshipDetailBean);

    /**
     * 获取资金托管详情失败
     * @param code
     * @param msg
     */
    void getFundsTrusteeshipDetailFailed(int code,String msg);

    /**
     * 操作成功
     */
    void operateSuccess();

    /**
     * 操作失败
     */
    void operateFailed(int code,String msg);

    /**
     * 上传图片成功
     *
     * @param pic_urls
     */
    void uploadPicSuccess(List<String> pic_urls);


    /**
     * 上传图片失败
     *
     */
    void uploadPicFailed(int code,String msg);
}
