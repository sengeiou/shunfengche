package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;

import java.util.List;

/**
 * 描述：个人/企业认证回调
 * 时间：2018/3/6
 * 作者：cyq
 */
public interface AuthenticationView extends BaseViewModel {


    /**
     * 个人/企业认证成功
     */
    void authenticationSuccess();

    /**
     * 个人/企业认证失败
     * @param code
     * @param msg
     */
    void authenticationFailed(int code, String msg);

    /**
     * 上传图片成功
     *
     * @param pic_urls
     */
    void uploadPicSuccess(List<String> pic_urls);
}
