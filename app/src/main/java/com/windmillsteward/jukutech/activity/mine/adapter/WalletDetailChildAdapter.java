package com.windmillsteward.jukutech.activity.mine.adapter;

import android.content.Context;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.WalletDetailBean;
import com.windmillsteward.jukutech.utils.DateUtil;

import java.util.List;

/**
 * 描述：钱包明细子item
 * author:cyq
 * 2018-03-29
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class WalletDetailChildAdapter extends BaseQuickAdapter<WalletDetailBean.ListBean,BaseViewHolder> {


    private Context context;
    private List<WalletDetailBean.ListBean> list;

    public WalletDetailChildAdapter(Context context, List<WalletDetailBean.ListBean> list) {
        super(R.layout.item_wallet_detail_child,list);
        this.context = context;
        this.list = list;
    }

    @Override
    protected void convert(BaseViewHolder helper, WalletDetailBean.ListBean item) {
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_date, DateUtil.StampTimeToDate(item.getAdd_time()+"","yyyy-MM-dd HH:mm"));
        helper.setText(R.id.tv_date, item.getTime());
        int detail_type = item.getDetail_type();
        int transaction_type = item.getTransaction_type();

        if (detail_type == 1){
            if (transaction_type == 1 || transaction_type == 3){
                helper.setText(R.id.tv_money,"+ "+item.getPrice());
                helper.setTextColor(R.id.tv_money,context.getResources().getColor(R.color.text_blue));
            }else {
                helper.setText(R.id.tv_money,"- "+item.getPrice());
                helper.setTextColor(R.id.tv_money,context.getResources().getColor(R.color.text_color_black));
            }
        }else if (detail_type == 2){
            helper.setText(R.id.tv_money,"- "+item.getPrice());
            helper.setTextColor(R.id.tv_money,context.getResources().getColor(R.color.text_color_black));
        }
        helper.addOnClickListener(R.id.lay_ll_root);
    }
}
