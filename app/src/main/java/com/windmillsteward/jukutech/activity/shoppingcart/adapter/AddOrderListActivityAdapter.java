package com.windmillsteward.jukutech.activity.shoppingcart.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.AddOrderListBean;

import java.util.List;

/**
 *
 * Created by Administrator on 2018/4/17 0017.
 */

public class AddOrderListActivityAdapter extends BaseQuickAdapter<AddOrderListBean.OrderListBean, BaseViewHolder> {


    public AddOrderListActivityAdapter(@Nullable List<AddOrderListBean.OrderListBean> data) {
        super(R.layout.item_add_order_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddOrderListBean.OrderListBean item) {
        if (item != null) {
            helper.setText(R.id.tv_title,item.getStore_name())
                    .setText(R.id.tv_freight_fee,item.getFreight_fee())
                    .setText(R.id.tv_total_pay_fee,item.getTotal_pay_fee());
            RecyclerView mRecyclerView = helper.getView(R.id.mRecyclerView);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setAdapter(new AddOrderGridViewAdapter(item.getCommodity_list()));
        }
    }
}
