package com.windmillsteward.jukutech.activity.home.capitalmanager.adapter;

import android.support.annotation.Nullable;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.CapitalListBean;

import java.util.List;

/**
 *
 * Created by Administrator on 2018/4/8 0008.
 */

public class FinancingListFragmentAdapter  extends BaseQuickAdapter<CapitalListBean.ListBean,BaseViewHolder>{

    public FinancingListFragmentAdapter(@Nullable List<CapitalListBean.ListBean> data) {
        super(R.layout.item_financing_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CapitalListBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_product_type_name,item.getProduct_type_name())
                    .setText(R.id.tv_title,item.getTitle())
                    .setText(R.id.tv_yield_rate,item.getYield_rate()+"%")
                    .setText(R.id.tv_deadline,item.getDeadline()+"天");
        }

    }
}
