package com.windmillsteward.jukutech.activity.home.specialty.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.HouseKeeperDataQuickBean;
import com.windmillsteward.jukutech.bean.SeckillListBean;
import com.windmillsteward.jukutech.bean.ShoppingCarListBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;

import java.util.List;

/**
 * 描述：轮播图回调
 * author:cyq
 * 2018-02-06
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface SeckillListView extends BaseViewModel {
    /**
     * 成功
     */
    void getSeckillListSuccess(List<SeckillListBean>  seckillListBean);

    /**
     * 失败
     * @param code
     * @param msg
     */
    void getSeckillFailed(int code, String msg);

    /**
     * 初始化数据
     */
    void getSeckillInitDataSuccess(List<SeckillListBean>  seckillListBean);

    /**
     * 刷新
     * @param seckillListBean
     */
    void getSeckillRefreshDataSuccess(List<SeckillListBean>  seckillListBean);

    /**
     * 刷新失败
     */
    void getSeckillRefreshDataFailure();

    /**
     * 加载下一页
     * @param seckillListBean
     */
    void getSeckillLoadNextDataSuccess(List<SeckillListBean>  seckillListBean);

    /**
     * 加载下一页失败
     */
    void getSeckillLoadNextFailure();

}
