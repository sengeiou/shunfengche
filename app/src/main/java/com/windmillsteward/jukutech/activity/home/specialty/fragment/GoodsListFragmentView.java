package com.windmillsteward.jukutech.activity.home.specialty.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.GoodsListBean;
import com.windmillsteward.jukutech.bean.SpecialtyListBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public interface GoodsListFragmentView extends BaseViewModel {


    /**
     * 初始化数据
     */
    void initDataSuccess(GoodsListBean bean);

    /**
     * 刷新
     * @param bean
     */
    void refreshDataSuccess(GoodsListBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页
     * @param bean
     */
    void loadNextDataSuccess(GoodsListBean bean);

    /**
     * 加载下一页失败
     */
    void loadNextFailure();

    /**
     * 加载地区
     */
    void loadAreaDataSuccess(List<ThirdAreaBean> list);

    /**
     * 加载排序
     * @param list
     */
    void loadSortDataSuccess(List<Map<String, Object>> list);
}
