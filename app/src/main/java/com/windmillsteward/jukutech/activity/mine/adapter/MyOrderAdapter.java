package com.windmillsteward.jukutech.activity.mine.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.TextView;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.MyOrderBean;

import java.util.List;

/**
 * 描述：
 * 时间：2018/2/21/021
 * 作者：xjh
 */
public class MyOrderAdapter extends BaseQuickAdapter<MyOrderBean.ListBean,BaseViewHolder> {

    private Context context;
    public MyOrderAdapter(Context context, @Nullable List<MyOrderBean.ListBean> data) {
        super(R.layout.item_myorder, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyOrderBean.ListBean item) {
        if (item!=null) {
            helper.setText(R.id.tv_title,item.getType_name())
                    .setText(R.id.tv_desc,item.getOrder_name())
                    .setText(R.id.tv_status,item.getDetail_status_name());

            TextView tv_status = (TextView) helper.getView(R.id.tv_status);
            String detail_status_name = item.getDetail_status_name();

            if (detail_status_name.contains("未") || detail_status_name.contains("待")) {
                tv_status.setBackground(ContextCompat.getDrawable(context,R.drawable.shape_myorder_drz));
                tv_status.setTextColor(ContextCompat.getColor(context,R.color.color_ffa800));
            } else if (TextUtils.equals(detail_status_name,"已入住")||TextUtils.equals(detail_status_name,"已完成") ||TextUtils.equals(detail_status_name,"已确认")) {
                tv_status.setBackground(ContextCompat.getDrawable(context,R.drawable.shape_myorder_yrz));
                tv_status.setTextColor(ContextCompat.getColor(context,R.color.color_23abac));
            } else if (TextUtils.equals(detail_status_name,"已取消")) {
                tv_status.setBackground(ContextCompat.getDrawable(context,R.drawable.shape_myorder_yqx));
                tv_status.setTextColor(ContextCompat.getColor(context,R.color.color_text_999));
            } else {
                tv_status.setBackground(ContextCompat.getDrawable(context,R.drawable.shape_myorder_yqx));
                tv_status.setTextColor(ContextCompat.getColor(context,R.color.color_text_999));
            }

        }
    }
}
