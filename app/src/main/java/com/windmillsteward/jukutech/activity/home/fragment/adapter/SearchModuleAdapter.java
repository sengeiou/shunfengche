package com.windmillsteward.jukutech.activity.home.fragment.adapter;

import android.support.annotation.Nullable;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.SearchModuleBean;

import java.util.List;

/**
 * 描述：搜索模块模块适配器
 * 时间：2018/3/12/
 * 作者：cyq
 */
public class SearchModuleAdapter extends BaseQuickAdapter<SearchModuleBean, BaseViewHolder> {


    public SearchModuleAdapter(@Nullable List<SearchModuleBean> data) {
        super(R.layout.item_search_module_list, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, SearchModuleBean item) {
        if (item != null) {

            helper.setText(R.id.tv_module_name, item.getModule_name());
            //模块类型：【0：全部，1：人才驿站，2：思想智库，3：智慧家庭，4：房屋租售，5：住宿旅行】
           helper.setText(R.id.tv_number,item.getNum()+"条信息");

        }
    }
}
