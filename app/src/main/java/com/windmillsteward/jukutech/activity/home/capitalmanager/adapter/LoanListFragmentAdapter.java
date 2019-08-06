package com.windmillsteward.jukutech.activity.home.capitalmanager.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.CapitalListBean;
import com.windmillsteward.jukutech.utils.GlideUtil;

import java.util.List;

/**
 *
 * Created by Administrator on 2018/4/8 0008.
 */

public class LoanListFragmentAdapter extends BaseQuickAdapter<CapitalListBean.ListBean,BaseViewHolder>{

    public LoanListFragmentAdapter(@Nullable List<CapitalListBean.ListBean> data) {
        super(R.layout.item_loan_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CapitalListBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_title,item.getTitle())
                    .setText(R.id.tv_title2,"最高"+item.getMinimum_amount()+"，月利率低至"+item.getYield_rate()+"%");
            GlideUtil.show(mContext,item.getCapital_logo(),(ImageView) helper.getView(R.id.iv_pic),R.mipmap.icon_default_pic,R.mipmap.icon_default_pic);
        }
    }
}
