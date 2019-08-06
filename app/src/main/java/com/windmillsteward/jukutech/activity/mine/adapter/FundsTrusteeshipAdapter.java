package com.windmillsteward.jukutech.activity.mine.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.FundsTrusteeshipBean;
import com.windmillsteward.jukutech.utils.StateButton;

import java.util.List;

/**
 * 描述：资金托管列表适配器
 * 时间：2018/3/7
 * 作者：cyq
 */
public class FundsTrusteeshipAdapter extends BaseQuickAdapter<FundsTrusteeshipBean.ListBean, BaseViewHolder> {

    private Context context;
    private List<FundsTrusteeshipBean.ListBean> data;

    public FundsTrusteeshipAdapter(Context context, @Nullable List<FundsTrusteeshipBean.ListBean> data) {
        super(R.layout.item_funds_trusteeship, data);
        this.context = context;
        this.data = data;
    }



    @Override
    protected void convert(BaseViewHolder helper, FundsTrusteeshipBean.ListBean item) {
        if (item != null) {
            helper.setText(R.id.tv_trusteeship_module_name, item.getTrusteeship_module_name());
            helper.setText(R.id.tv_trusteeship_title, item.getTrusteeship_title());
            String status_name = item.getStatus_name();
            helper.setText(R.id.btn_status, status_name);
            StateButton button = (StateButton) helper.getView(R.id.btn_status);
            if (status_name.equals("待审核") ||status_name.equals("提交申请") ||status_name.equals("待处理") || status_name.equals("托管中") ){
            helper.setBackgroundColor(R.id.btn_status, context.getResources().getColor(R.color.color_fff3e5));
            helper.setTextColor(R.id.btn_status,context.getResources().getColor(R.color.color_ffa800));
            }else if(status_name.equals("已拒绝")||status_name.equals("已取消")){
                helper.setBackgroundColor(R.id.btn_status,context.getResources().getColor(R.color.color_dedede));
                helper.setTextColor(R.id.btn_status,context.getResources().getColor(R.color.color_text_b3));
            }else if(status_name.equals("举证协商")){
                helper.setBackgroundColor(R.id.btn_status,context.getResources().getColor(R.color.color_d1e9fe));
                helper.setTextColor(R.id.btn_status,context.getResources().getColor(R.color.color_2370ac));
            }else if(status_name.equals("已完成")){
                helper.setBackgroundColor(R.id.btn_status,context.getResources().getColor(R.color.color_d1fefe));
                helper.setTextColor(R.id.btn_status,context.getResources().getColor(R.color.color_23abac));
            }

        }
    }
}
