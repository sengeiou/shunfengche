package com.windmillsteward.jukutech.activity.home.family.adapter;

import android.support.annotation.Nullable;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.RequireClassBean;

import java.util.List;

/**
 * @date: on 2018/10/1
 * @author: cretin
 * @email: mxnzp_life@163.com
 * @desc: 添加描述
 */
public class HeaderRecyclerViewAdapter extends BaseQuickAdapter<RequireClassBean, BaseViewHolder> {
    public HeaderRecyclerViewAdapter(@Nullable List<RequireClassBean> data) {
        super(R.layout.header_item_intelligent_family_list, data, false);
    }

    @Override
    protected void convert(BaseViewHolder helper, RequireClassBean item) {
        helper.setText(R.id.tv_text, item.getClass_name());
    }
}
