package com.windmillsteward.jukutech.activity.home.legalexpert.adapter;

import android.support.annotation.Nullable;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.NameAndIdBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class LegalExpertClassActivityAdapter extends BaseQuickAdapter<NameAndIdBean,BaseViewHolder> {

    public LegalExpertClassActivityAdapter(@Nullable List<NameAndIdBean> data) {
        super(R.layout.item_legalexpert_class, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NameAndIdBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_item,item.getName());
        }
    }
}
