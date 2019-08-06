package com.windmillsteward.jukutech.activity.home.personnel.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.HomePositionClassBean;
import com.windmillsteward.jukutech.bean.MoreBean;
import com.windmillsteward.jukutech.bean.PositionListBean;
import com.windmillsteward.jukutech.bean.SalaryBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/1/8
 * 作者：xjh
 */

public interface PositionView extends BaseViewModel{

    /**
     * 初始化数据
     */
    void initDataSuccess(PositionListBean bean);

    /**
     * 加载区域数据
     */
    void loadAreaDataSuccess(List<ThirdAreaBean> areaBeans);

    /**
     * 加载薪资数据
     */
    void loadSalaryDataSuccess(List<SalaryBean> list);

    /**
     * 下载更多的选择数据
     */
    void loadMoreDataSuccess(MoreBean bean);

    /**
     * 刷新数据
     */
    void refreshDataSuccess(PositionListBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页数据
     */
    void loadNextDataSuccess(PositionListBean bean);


    /**
     * 加载下一页失败
     */
    void loadNextDataError();

    /**
     * 加载职业分类
     * @param classBeanList
     */
    void loadPositionClassSuccess(List<HomePositionClassBean> classBeanList);
    void loadPositionClassFailed(int code, String msg);

}
