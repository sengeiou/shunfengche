package com.windmillsteward.jukutech.activity.home.fragment.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;
import com.windmillsteward.jukutech.bean.SearchModuleBean;

import java.util.List;

/**
 * 描述：搜索模块数据回调
 * author:cyq
 * 2018-02-06
 * Created by 2017 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface SearchModuleView extends BaseViewModel {

    /**
     * 成功
     * @param bean
     */
    void getSearchModuleListSuccess(List<SearchModuleBean> bean);

    /**
     * 失败
     * @param code
     * @param msg
     */
    void getSearchModuleListFailed(int code, String msg);
}
