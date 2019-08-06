package com.windmillsteward.jukutech.activity.home.specialty.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.GoodsListBean;
import com.windmillsteward.jukutech.bean.StoreInfoBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public interface StoreGoodsListActivityView extends BaseViewModel {


    /**
     * 初始化数据
     */
    void initDataSuccess(GoodsListBean bean);

    /**
     * 初始化店铺信息
     * @param bean
     */
    void initHeaderDataSuccess(StoreInfoBean bean);

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
}
