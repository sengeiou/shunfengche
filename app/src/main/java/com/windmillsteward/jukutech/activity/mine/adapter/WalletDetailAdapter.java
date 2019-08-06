package com.windmillsteward.jukutech.activity.mine.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.activity.mine.activity.WalletListDetailActivity;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.WalletDetailBean;
import com.windmillsteward.jukutech.utils.DateUtil;

import java.util.List;

/**
 * 描述：
 * author:cyq
 * 2018-03-29
 * Created by 2018 广州聚酷软件科技有限公司 All Right Reserved
 */

public class WalletDetailAdapter extends BaseQuickAdapter<WalletDetailBean.GroupListBean,BaseViewHolder> {


    private Context context;
    private List<WalletDetailBean.GroupListBean> list;

    public WalletDetailAdapter(Context context, List<WalletDetailBean.GroupListBean> list) {
        super(R.layout.item_wallet_detail_group,list);
        this.context = context;
        this.list = list;
    }

    @Override
    protected void convert(BaseViewHolder helper, WalletDetailBean.GroupListBean item) {
        String month_name = item.getMonth_name();
        List<WalletDetailBean.ListBean> wallet_list = item.getWallet_list();
        helper.setText(R.id.tv_month,month_name);
//        TextView tv_month = (TextView) helper.getView(R.id.tv_month);
//        int position = helper.getPosition();
//        WalletDetailBean.ListBean item1 = wallet_list.get(position);
//        int tag = wallet_list.get(position).getTag();
//        if (tag == 0){
//            tv_month.setVisibility(View.VISIBLE);
//        }else{
//            tv_month.setVisibility(View.GONE);
//        }
//        helper.setText(R.id.tv_title,item1.getTitle());
//        helper.setText(R.id.tv_date, DateUtil.StampTimeToDate(item1.getAdd_time()+"","yyyy-MM-dd HH:mm"));
//        helper.setText(R.id.tv_date, item1.getTime());
//        int detail_type = item1.getDetail_type();
//        if (detail_type == 1){
//            helper.setText(R.id.tv_money,"+ "+item1.getPrice());
//            helper.setTextColor(R.id.tv_money,context.getResources().getColor(R.color.color_23abac));
//        }else if (detail_type == 2){
//            helper.setText(R.id.tv_money,"- "+item1.getPrice());
//            helper.setTextColor(R.id.tv_money,context.getResources().getColor(R.color.text_color_black));
//        }

        WalletDetailChildAdapter adapter = new WalletDetailChildAdapter(context, wallet_list);
        RecyclerView recyclerView = (RecyclerView) helper.getView(R.id.mRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
                adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                List<WalletDetailBean.ListBean> data = adapter.getData();
                WalletDetailBean.ListBean listBean = data.get(position);
                int detail_id = listBean.getDetail_id();
                int detail_type = listBean.getDetail_type();
                WalletListDetailActivity.go(context,detail_id,detail_type);
            }
        });

    }


}
