package com.windmillsteward.jukutech.activity.mine.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.SpecialtyOrderDetailBean;
import com.windmillsteward.jukutech.bean.SpecialtyOrderListBean;
import com.windmillsteward.jukutech.utils.GlideUtil;

import java.util.List;

/**
 * 描述：
 * 时间：2018/4/15/015
 * 作者：xjh
 */
public class SpecialtyOrderDetailGridViewAdapter extends BaseQuickAdapter<SpecialtyOrderDetailBean.CommodityListBean,BaseViewHolder> {

    private int order_status;
    public SpecialtyOrderDetailGridViewAdapter(List<SpecialtyOrderDetailBean.CommodityListBean> list,int order_status) {
        super(R.layout.item_specialty_order_detail_gridview,list);
        this.order_status = order_status;
    }

    @Override
    protected void convert(BaseViewHolder helper, SpecialtyOrderDetailBean.CommodityListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_title,item.getCommodity_title())
                    .setText(R.id.tv_price,"￥"+item.getCommodity_price())
                    .setText(R.id.tv_number,"X"+item.getCommodity_num())
                    .setText(R.id.tv_model,"型号:  "+item.getCommodity_model_name())
                    .addOnClickListener(R.id.tv_refund)
                    .addOnClickListener(R.id.tv_title);

            TextView tv_order_status = helper.getView(R.id.tv_order_status);
            TextView tv_refund = helper.getView(R.id.tv_refund);
            tv_refund.setText("退货");
            if (item.getIs_apply_refund()==1) {
                tv_refund.setVisibility(View.GONE);
                tv_order_status.setVisibility(View.VISIBLE);
                tv_order_status.setText(item.getApply_status());
            } else {
                tv_refund.setVisibility(View.VISIBLE);
                tv_order_status.setVisibility(View.INVISIBLE);
            }

            ImageView iv_pic =  helper.getView(R.id.iv_pic);
            LinearLayout linear_bottom =  helper.getView(R.id.linear_bottom);
            GlideUtil.show(mContext,item.getCommodity_cover_picture(),iv_pic);
            //  【1：待付款，2：待发货，3：待收货，4：已完成，5：已完成，不能申请售后，6：已取消】
            switch (order_status) {
                case 1:
                    linear_bottom.setVisibility(View.GONE);
                    break;
                case 2:
                    linear_bottom.setVisibility(View.VISIBLE);
                    tv_refund.setText("退款");
                    break;
                case 3:
                    linear_bottom.setVisibility(View.VISIBLE);
                    tv_refund.setText("退货");
                    break;
                case 4:
                    linear_bottom.setVisibility(View.VISIBLE);
                    tv_refund.setText("退货");
                    break;
                case 5:
                    linear_bottom.setVisibility(View.GONE);
                    break;
                case 6:
                    linear_bottom.setVisibility(View.GONE);
                    break;
            }
        }
    }
}
