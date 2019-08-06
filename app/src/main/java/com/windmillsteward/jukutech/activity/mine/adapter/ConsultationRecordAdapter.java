package com.windmillsteward.jukutech.activity.mine.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.FundsTrusteeshipDetailBean;
import com.windmillsteward.jukutech.customview.CircleImageView;
import com.windmillsteward.jukutech.utils.GlideUtil;
import com.windmillsteward.jukutech.utils.http.JsonUtil;

import java.util.List;

/**
 * 描述：协商记录列表适配器
 * 时间：2018/3/9
 * 作者：cyq
 */
public class ConsultationRecordAdapter extends BaseQuickAdapter<FundsTrusteeshipDetailBean.ListBean, BaseViewHolder> {

    private Context context;
    private List<FundsTrusteeshipDetailBean.ListBean> data;

    public ConsultationRecordAdapter(Context context, @Nullable List<FundsTrusteeshipDetailBean.ListBean> data) {
        super(R.layout.item_consultation_record, data);
        this.context = context;
        this.data = data;
    }

    /**
     * 刷新列表
     * @param data
     */
    public void refreshList(List<FundsTrusteeshipDetailBean.ListBean> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, FundsTrusteeshipDetailBean.ListBean item) {
        if (item != null) {
            CircleImageView iv_head = (CircleImageView)helper.getView(R.id.iv_user_pic);
            GlideUtil.show(context,item.getUser_avatar_url(),iv_head);
            helper.setText(R.id.tv_name,item.getUser_name());
            helper.setText(R.id.tv_date, item.getAdd_time());
            helper.setText(R.id.tv_reason,item.getConsultation_reason());
            String report_voucher = item.getReport_voucher();
            List<String> picList = (List<String>) JsonUtil.fromJson(report_voucher, new TypeReference<List<String>>() {
            }.getType());
            helper.setAdapter(R.id.gv_content,new OnePictureAdapter(context,picList));
        }
    }
}
