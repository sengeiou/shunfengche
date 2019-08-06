package com.windmillsteward.jukutech.activity.home.fragment.activity;

import com.windmillsteward.jukutech.base.BaseViewModel;

import java.util.List;
import java.util.Map;

/**
 * 描述：首页搜索回调
 * author:cyq
 * 2018-03-30
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public interface HomeSearchView extends BaseViewModel {

    /**
     * 历史搜索成功
     */
    void getHistorySearchListDataSuccess();

    /**
     * 历史搜索失败
     */
    void getHistorySearchListDataFailed();

    /**
     * 获取所选模块类型：【0：全部，1：人才驿站，2：思想智库，3：智慧家庭，4：房屋租售，5：住宿旅行】
     * @param maps
     */
    void getSelectTypeDataSuccess(List<Map<String,Object>> maps);
}
