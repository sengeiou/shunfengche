package com.windmillsteward.jukutech.activity.home.fragment.model;

import com.windmillsteward.jukutech.base.baseadapter.entity.MultiItemEntity;

import java.util.List;

/**
 * @date: on 2018/10/7
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class HomeRecyclerViewModel implements MultiItemEntity {
    public static final int TYPE_PIC = 1;
    public static final int TYPE_SINGLE = 2;
    public static final int TYPE_BANNER = 3;

    public static final int TYPE_RENCAI = 4;
    public static final int TYPE_SMART = 5;
    public static final int TYPE_FANGWU = 6;
    public static final int TYPE_FOOTER = 7;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }

    private List<HomeRootModel.BottomBannerBean> bottom_banner;

    private HomeRootModel.RecommendModuleDataBean recommend_module_data;

    private HomeRootModel.ClassListBean module_class;
    private HomeRootModel.TitleListBean titleRecord;

    public HomeRootModel.TitleListBean getTitleRecord() {
        return titleRecord;
    }

    public void setTitleRecord(HomeRootModel.TitleListBean titleRecord) {
        this.titleRecord = titleRecord;
    }

    public HomeRootModel.ClassListBean getModule_class() {
        return module_class;
    }

    public void setModule_class(HomeRootModel.ClassListBean module_class) {
        this.module_class = module_class;
    }

    public List<HomeRootModel.BottomBannerBean> getBottom_banner() {
        return bottom_banner;
    }

    public void setBottom_banner(List<HomeRootModel.BottomBannerBean> bottom_banner) {
        this.bottom_banner = bottom_banner;
    }

    public HomeRootModel.RecommendModuleDataBean getRecommend_module_data() {
        return recommend_module_data;
    }

    public void setRecommend_module_data(HomeRootModel.RecommendModuleDataBean recommend_module_data) {
        this.recommend_module_data = recommend_module_data;
    }
}
