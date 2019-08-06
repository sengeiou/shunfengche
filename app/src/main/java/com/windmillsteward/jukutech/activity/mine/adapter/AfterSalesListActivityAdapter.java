package com.windmillsteward.jukutech.activity.mine.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.AfterSealsListBean;
import com.windmillsteward.jukutech.utils.GlideUtil;

import java.util.List;

/**
 * Created by Administrator on 2018/4/21 0021.
 */

public class AfterSalesListActivityAdapter extends BaseQuickAdapter<AfterSealsListBean.ListBean, BaseViewHolder> {


    public AfterSalesListActivityAdapter(@Nullable List<AfterSealsListBean.ListBean> data) {
        super(R.layout.item_after_sales_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AfterSealsListBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_title,item.getCommodity_title())
                    .setText(R.id.tv_order_sn,item.getOrder_sn())
                    .setText(R.id.tv_model,"型号："+item.getCommodity_model_name())
                    .setText(R.id.tv_price,"￥"+item.getCommodity_price())
                    .setText(R.id.tv_number,"X"+item.getCommodity_num())
                    .setText(R.id.tv_order_total_fee,"￥"+item.getOrder_total_fee())
                    .setText(R.id.tv_back_price,"￥"+item.getPrice());
            ImageView view = helper.getView(R.id.iv_pic);
            GlideUtil.show(mContext,item.getCommodity_cover_picture(),view);
            // 状态 1：退款中，2：退款成功，3：拒绝退款，4：退款完成，5：申请退货中，6：待用户发货，7：用户已发货，8：退货完成，9：拒绝退货，10：取消
            TextView tv_status = helper.getView(R.id.tv_status);
            TextView tv_refund = helper.getView(R.id.tv_refund);
            TextView tv_look = helper.getView(R.id.tv_look);
            LinearLayout linear_bottom = helper.getView(R.id.linear_bottom);
            switch (item.getStatus()) {
                case 1:
                    tv_status.setText("退款中");
                    linear_bottom.setVisibility(View.GONE);
                    tv_refund.setVisibility(View.GONE);
                    tv_look.setVisibility(View.GONE);
                    break;
                case 2:
                    tv_status.setText("退款成功");
                    linear_bottom.setVisibility(View.GONE);
                    tv_refund.setVisibility(View.GONE);
                    tv_look.setVisibility(View.GONE);
                    break;
                case 3:
                    tv_status.setText("拒绝退款");
                    linear_bottom.setVisibility(View.GONE);
                    tv_refund.setVisibility(View.GONE);
                    tv_look.setVisibility(View.GONE);
                    break;
                case 4:
                    tv_status.setText("退款完成");
                    linear_bottom.setVisibility(View.GONE);
                    tv_refund.setVisibility(View.GONE);
                    tv_look.setVisibility(View.GONE);
                    break;
                case 5:
                    tv_status.setText("退货中");
                    linear_bottom.setVisibility(View.GONE);
                    tv_refund.setVisibility(View.GONE);
                    tv_look.setVisibility(View.GONE);
                    break;
                case 6:
                    tv_status.setText("待用户发货");
                    linear_bottom.setVisibility(View.VISIBLE);
                    tv_refund.setVisibility(View.VISIBLE);
                    tv_look.setVisibility(View.GONE);
                    break;
                case 7:
                    tv_status.setText("用户已发货");
                    linear_bottom.setVisibility(View.VISIBLE);
                    tv_refund.setVisibility(View.GONE);
                    tv_look.setVisibility(View.VISIBLE);
                    break;
                case 8:
                    tv_status.setText("退款完成");
                    linear_bottom.setVisibility(View.GONE);
                    tv_refund.setVisibility(View.GONE);
                    tv_look.setVisibility(View.GONE);
                    break;
                case 9:
                    tv_status.setText("拒绝退货");
                    linear_bottom.setVisibility(View.GONE);
                    tv_refund.setVisibility(View.GONE);
                    tv_look.setVisibility(View.GONE);
                    break;
                case 10:
                    tv_status.setText("取消");
                    linear_bottom.setVisibility(View.GONE);
                    tv_refund.setVisibility(View.GONE);
                    tv_look.setVisibility(View.GONE);
                    break;
            }
        }
    }

}
