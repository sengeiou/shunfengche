package com.windmillsteward.jukutech.activity.home.insurance.adapter;

import android.support.annotation.Nullable;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.InsuranceListBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/18/018
 * 作者：xjh
 */
public class InsuranceListFragmentAdapter extends BaseQuickAdapter<InsuranceListBean.ListBean,BaseViewHolder> {

    public InsuranceListFragmentAdapter(@Nullable List<InsuranceListBean.ListBean> data) {
        super(R.layout.item_insurance_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, InsuranceListBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_title,item.getTitle())
                    .setText(R.id.tv_company_name,item.getCompany_name())
                    .setText(R.id.tv_insurance_type,item.getInsurance_type_name());
        }

    }
}
