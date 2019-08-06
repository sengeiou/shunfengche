package com.windmillsteward.jukutech.activity.home.commons.quickindex;

import android.support.annotation.Nullable;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.ThirdAreaBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/2/2
 * 作者：xjh
 */

public class QuickIndexAreaAdapter extends BaseQuickAdapter<ThirdAreaBean,BaseViewHolder> {

    public QuickIndexAreaAdapter( @Nullable List<ThirdAreaBean> data) {
        super(R.layout.item_quick_index_area, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ThirdAreaBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_area,item.getThird_area_name());
        }
    }
}
