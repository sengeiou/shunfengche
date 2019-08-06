package com.windmillsteward.jukutech.activity.newpage.customerservice.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.HomeCustomerClassicModel;
import com.windmillsteward.jukutech.bean.HomeCustomerModel;

import java.util.List;

/**
 * @date: on 2018/10/2
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public interface HomeCustomerView extends BaseViewModel {
    /**
     * 显示头部分类数据
     */
    void showHeaderData(List<HomeCustomerClassicModel> list);

    /**
     * 显示列表数据
     */
    void showListData(List<HomeCustomerModel> list);
}
