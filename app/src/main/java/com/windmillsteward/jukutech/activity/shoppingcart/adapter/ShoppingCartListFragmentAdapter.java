package com.windmillsteward.jukutech.activity.shoppingcart.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.ShoppingCarListBean;

import java.util.List;

/**
 *
 * Created by Administrator on 2018/4/12 0012.
 */

public class ShoppingCartListFragmentAdapter extends BaseQuickAdapter<ShoppingCarListBean.StoreCommodityListBean.ListBean, BaseViewHolder> {

    private OnSecondItemChildClickListener onSecondItemChildClickListener;

    public void setOnSecondItemChildClickListener(OnSecondItemChildClickListener onSecondItemChildClickListener) {
        this.onSecondItemChildClickListener = onSecondItemChildClickListener;
    }

    public ShoppingCartListFragmentAdapter(@Nullable List<ShoppingCarListBean.StoreCommodityListBean.ListBean> data) {
        super(R.layout.item_shopping_cart_list, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ShoppingCarListBean.StoreCommodityListBean.ListBean item) {
        if (item != null) {
            helper.setText(R.id.tv_title,item.getStore_name())
                .addOnClickListener(R.id.tv_title)
                .addOnClickListener(R.id.tv_edit)
                .addOnClickListener(R.id.iv_select);
            ImageView iv_select = helper.getView(R.id.iv_select);
            TextView tv_edit = helper.getView(R.id.tv_edit);
            tv_edit.setText(item.isEdit()?"完成":"编辑");
            boolean select = item.isSelect();
            if (select) {
                iv_select.setImageResource(R.mipmap.icon_select);
            } else {
                iv_select.setImageResource(R.mipmap.icon_select_n);
            }
            RecyclerView mRecyclerView = helper.getView(R.id.mRecyclerView);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            final List<ShoppingCarListBean.StoreCommodityListBean.ListBean.CommodityListBean> commodity_list = item.getCommodity_list();
            if (commodity_list!=null) {
                final ShoppingCartGridViewAdapter adapter = new ShoppingCartGridViewAdapter(commodity_list);
                mRecyclerView.setAdapter(adapter);
                adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.tv_commodity_model:
                            case R.id.iv_choice_model:
                                if (onSecondItemChildClickListener!=null) {
                                    onSecondItemChildClickListener.clickChoice(helper.getAdapterPosition(),position);
                                }
                                Log.e(TAG, "onItemChildClick: " + "点击了吗" );
                                break;
                            case R.id.tv_delete:
                                if (onSecondItemChildClickListener!=null) {
                                    onSecondItemChildClickListener.clickDelete(helper.getAdapterPosition(),position);
                                }
                                break;
                            case R.id.iv_select:
                                if (onSecondItemChildClickListener!=null) {
                                    onSecondItemChildClickListener.clickSelect(helper.getAdapterPosition(),position);
                                }
                                break;
                            case R.id.tv_reduce:
                                if (onSecondItemChildClickListener!=null) {
                                    onSecondItemChildClickListener.clickReduce(helper.getAdapterPosition(),position);
                                }
                                break;
                            case R.id.tv_add:
                                if (onSecondItemChildClickListener!=null) {
                                    onSecondItemChildClickListener.clickAdd(helper.getAdapterPosition(),position);
                                }
                                break;
                        }
                    }
                });
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        if (onSecondItemChildClickListener!=null) {
                            onSecondItemChildClickListener.onItemClick(helper.getAdapterPosition(),position);
                        }
                    }
                });
            }
        }
    }

    public interface OnSecondItemChildClickListener {
        // 点击了选择
        void clickSelect(int storePosition,int shoppingPosition);
        // 点击了删除
        void clickDelete(int storePosition, int position);
        // 点击了累心
        void clickChoice(int storePosition,int shoppingPosition);
        void clickReduce(int storePosition,int shoppingPosition);

        void clickAdd(int storePosition,int shoppingPosition);
        // 点击了子item
        void onItemClick(int storePosition,int shoppingPosition);
    }
}
