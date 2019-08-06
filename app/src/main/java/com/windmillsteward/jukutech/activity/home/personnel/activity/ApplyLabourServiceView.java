package com.windmillsteward.jukutech.activity.home.personnel.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.LabourLastApplyBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：劳务申请回调
 * 时间：2018/1/13/013
 * 作者：xjh
 */
public interface ApplyLabourServiceView extends BaseViewModel{

    /**
     * 初始化
     * @param bean
     */
    void initLastDataSuccess(LabourLastApplyBean bean);

    /**
     * 选择男女
     * @param maps
     */
    void loadSexModuleSuccess(List<Map<String, Object>> maps);

    /**
     * 提交申请成功
     */
    void applyCommitSuccess();

}
