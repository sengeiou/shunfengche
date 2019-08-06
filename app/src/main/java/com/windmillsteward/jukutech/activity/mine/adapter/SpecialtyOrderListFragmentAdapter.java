package com.windmillsteward.jukutech.activity.mine.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.activity.SpecialtyOrderDetailActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.SpecialtyOrderListBean;
import com.windmillsteward.jukutech.interfaces.Define;

import java.util.List;

/**
 *
 * Created by Administrator on 2018/4/12 0012.
 */

public class SpecialtyOrderListFragmentAdapter extends BaseQuickAdapter<SpecialtyOrderListBean.ListBean,BaseViewHolder> {


    public SpecialtyOrderListFragmentAdapter(@Nullable List<SpecialtyOrderListBean.ListBean> data) {
        super(R.layout.item_specialty_order_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final SpecialtyOrderListBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_title,item.getStore_name())
                    .setText(R.id.order_status,item.getStatus_name())
                    .setText(R.id.tv_number,"共"+item.getTotal_commodity_num()+"件商品")
                    .setText(R.id.tv_price,"总价:  ￥"+item.getTotal_pay_fee()+"  (含运费"+item.getFreight_fee()+")")
                    .addOnClickListener(R.id.tv_delete)
                    .addOnClickListener(R.id.tv_close)
                    .addOnClickListener(R.id.tv_look)
                    .addOnClickListener(R.id.tv_continue)
                    .addOnClickListener(R.id.tv_sure);
            RecyclerView mRecyclerView = helper.getView(R.id.mRecyclerView);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            SpecialtyOrderGridViewAdapter adapter = new SpecialtyOrderGridViewAdapter(item.getCommodity_list());
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Define.INTENT_DATA,item.getOrder_id());
                    Intent intent = new Intent(mContext,SpecialtyOrderDetailActivity.class);
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
            mRecyclerView.setAdapter(adapter);
            TextView tv_delete = helper.getView(R.id.tv_delete);
            TextView tv_close = helper.getView(R.id.tv_close);
            TextView tv_look = helper.getView(R.id.tv_look);
            TextView tv_continue = helper.getView(R.id.tv_continue);
            TextView tv_sure = helper.getView(R.id.tv_sure);
            // 【1：待付款，2：待发货，3：待收货，4：已完成，5：已完成，不能申请售后，6：已取消】
            switch (item.getOrder_status()) {
                case 1:
                    tv_delete.setVisibility(View.GONE);
                    tv_close.setVisibility(View.VISIBLE);
                    tv_look.setVisibility(View.GONE);
                    tv_continue.setVisibility(View.VISIBLE);
                    tv_sure.setVisibility(View.GONE);

                    break;
                case 2:
                    tv_delete.setVisibility(View.GONE);
                    tv_close.setVisibility(View.GONE);
                    tv_look.setVisibility(View.GONE);
                    tv_continue.setVisibility(View.GONE);
                    tv_sure.setVisibility(View.GONE);
                    break;
                case 3:
                    tv_delete.setVisibility(View.GONE);
                    tv_close.setVisibility(View.GONE);
                    tv_look.setVisibility(View.VISIBLE);
                    tv_continue.setVisibility(View.GONE);
                    tv_sure.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    tv_delete.setVisibility(View.VISIBLE);
                    tv_close.setVisibility(View.GONE);
                    tv_look.setVisibility(View.GONE);
                    tv_continue.setVisibility(View.GONE);
                    tv_sure.setVisibility(View.GONE);
                    break;
                case 5:
                    tv_delete.setVisibility(View.VISIBLE);
                    tv_close.setVisibility(View.GONE);
                    tv_look.setVisibility(View.GONE);
                    tv_continue.setVisibility(View.GONE);
                    tv_sure.setVisibility(View.GONE);
                    break;
                case 6:
                    tv_delete.setVisibility(View.VISIBLE);
                    tv_close.setVisibility(View.GONE);
                    tv_look.setVisibility(View.GONE);
                    tv_continue.setVisibility(View.GONE);
                    tv_sure.setVisibility(View.GONE);
                    break;
            }
        }
    }
}
