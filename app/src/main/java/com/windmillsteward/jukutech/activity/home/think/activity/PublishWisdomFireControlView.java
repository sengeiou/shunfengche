package com.windmillsteward.jukutech.activity.home.think.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.ChargeResultBean;
import com.windmillsteward.jukutech.bean.PublishIdeaThinkResultBean;
import com.windmillsteward.jukutech.bean.TestItemsListBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/2/5/005
 * 作者：xjh
 */
public interface PublishWisdomFireControlView extends BaseViewModel {

    /**
     * 加载发布地区成功
     * @param maps
     */
    void loadPublishAreaDataSuccess(List<Map<String, Object>> maps);

    /**
     * 发布成功
     * @param bean
     */
    void publishSuccess(PublishIdeaThinkResultBean bean);

    /**
     * 判断发布是否需要支付费用
     * @param bean
     */
    void isCharge(ChargeResultBean bean);

    /**
     * 获取检测项目列表
     * @param testitemslistbean
     */
    void loadTestItemsListSuccess(List<TestItemsListBean> testitemslistbean);
}
