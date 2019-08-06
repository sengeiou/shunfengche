package com.windmillsteward.jukutech.activity.home.personnel.activity;

import com.windmillsteward.jukutech.activity.home.personnel.model.TalentInnListClassicModel;
import com.windmillsteward.jukutech.activity.home.personnel.model.TalentInnListModel;
import com.windmillsteward.jukutech.base.BaseViewModel;

import java.util.List;

/**
 * @date: on 2018/10/5
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public interface TalentInnListNewView extends BaseViewModel {
    /**
     * 获取头部数据
     */
    void loadHeaderViewData(List<TalentInnListClassicModel> list);

    /**
     * 获取列表数据
     * @param data
     */
    void loadListData(TalentInnListModel data);

    /**
     * 加载失败
     */
    void loadFail();
}
