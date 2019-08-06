package com.windmillsteward.jukutech.activity.mine.activity;


import com.windmillsteward.jukutech.base.BaseViewModel;

import java.util.List;
import java.util.Map;

/**
 * 编辑我的个人信息回调
 */

public interface EditMyInfoView extends BaseViewModel {


    /**
     * 编辑成功
     */
    void editSuccess();

    /**
     * 编辑失败
     */
    void  editFailed(int code, String msg);


    /**
     * 性别回调 1男2女
     * @param maps
     */
    void loadSexModuleSuccess(List<Map<String, Object>> maps);
}