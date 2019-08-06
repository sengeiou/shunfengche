package com.windmillsteward.jukutech.activity.mine.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.WalletDetailBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：钱包列表明细
 * author:cyq
 * 2018-03-29
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface WalletListView extends BaseViewModel {

    /**
     * 初始化数据
     */
    void initDataSuccess(WalletDetailBean bean);

    /**
     * 刷新
     * @param bean
     */
    void refreshDataSuccess(WalletDetailBean bean);

    /**
     * 刷新失败
     */
    void refreshDataFailure();

    /**
     * 加载下一页
     * @param bean
     */
    void loadNextDataSuccess(WalletDetailBean bean);

    /**
     * 加载下一页失败
     */
    void loadNextFailure();

    void loadWalletCostTypeDataSuccess(List<Map<String,Object>> maps);
}
