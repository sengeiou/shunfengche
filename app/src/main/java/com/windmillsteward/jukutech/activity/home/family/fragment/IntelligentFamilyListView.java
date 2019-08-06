package com.windmillsteward.jukutech.activity.home.family.fragment;

import com.windmillsteward.jukutech.activity.newpage.model.PublicResultModel;
import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.AuthenResultBean;
import com.windmillsteward.jukutech.bean.IntelligentFamilyBean;
import com.windmillsteward.jukutech.bean.RequireClassBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：
 * 时间：2018/1/17
 * 作者：xjh
 */

public interface IntelligentFamilyListView extends BaseViewModel {

    /**
     * 初始化数据
     */
    void initDataSuccess(IntelligentFamilyBean bean);

    /**
     * 刷新
     *
     * @param bean
     */
    void refreshDataSuccess(IntelligentFamilyBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页
     *
     * @param bean
     */
    void loadNextDataSuccess(IntelligentFamilyBean bean);

    /**
     * 加载下一页失败
     */
    void loadNextFailure();

    /**
     * 加载地区
     *
     * @param list
     */
    void loadAreaDataSuccess(List<ThirdAreaBean> list);

    /**
     * 加载发布时间选项
     *
     * @param list
     */
    void loadPushTimeDataSuccess(List<Map<String, Object>> list);

    /**
     * 加载价格选项
     *
     * @param list
     */
    void loadPriceDataSuccess(List<Map<String, Object>> list);

    /**
     * 抢单成功
     */
    void getOrderSuccess(PublicResultModel publicResultModel,String msg);

    /**
     * 获取头部分类数据
     */
    void loadRequireClassListSuccess(List<RequireClassBean> list);

    /**
     * 获取头部数据失败
     */
    void loadRequireClassListFail();

    /**
     * 判断是否认证
     */
    void isAuthen(AuthenResultBean authenData);
}
