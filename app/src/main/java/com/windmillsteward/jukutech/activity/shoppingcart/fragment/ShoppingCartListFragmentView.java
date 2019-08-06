package com.windmillsteward.jukutech.activity.shoppingcart.fragment;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.GoodsListBean;
import com.windmillsteward.jukutech.bean.ShoppingCarListBean;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public interface ShoppingCartListFragmentView extends BaseViewModel {


    /**
     * 初始化数据
     */
    void initDataSuccess(ShoppingCarListBean bean);

    /**
     * 刷新
     * @param bean
     */
    void refreshDataSuccess(ShoppingCarListBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页
     * @param bean
     */
    void loadNextDataSuccess(ShoppingCarListBean bean);

    /**
     * 加载下一页失败
     */
    void loadNextFailure();

    void editShoppingCartSuccess();

    void deleteShoppingCartSuccess();

}
