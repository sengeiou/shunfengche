package com.windmillsteward.jukutech.activity.home.carservice.adapter;

import android.support.annotation.Nullable;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.BuyCarListBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/3/25/025
 * 作者：xjh
 */
public class BuyCarListAdapter extends BaseQuickAdapter<BuyCarListBean.ListBean,BaseViewHolder> {

    public BuyCarListAdapter(@Nullable List<BuyCarListBean.ListBean> data) {
        super(R.layout.item_buycar_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BuyCarListBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_title,item.getTitle())
                    .setText(R.id.tv_area,item.getArea_name())
                    .setText(R.id.tv_price,item.getPrice());
        }
    }
}
