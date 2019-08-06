package com.windmillsteward.jukutech.activity.mine.adapter;

import android.support.annotation.Nullable;

import com.windmillsteward.jukutech.R;
import com.windmillsteward.jukutech.base.baseadapter.BaseQuickAdapter;
import com.windmillsteward.jukutech.base.baseadapter.BaseViewHolder;
import com.windmillsteward.jukutech.bean.MyCouponBean;
import com.windmillsteward.jukutech.utils.DateUtil;

import java.util.List;

/**
 * 描述：优惠券列表适配器
 * 时间：2018/3/5
 * 作者：cyq
 */
public class MyCouponAdapter extends BaseQuickAdapter<MyCouponBean.ListBean, BaseViewHolder> {

    public MyCouponAdapter(@Nullable List<MyCouponBean.ListBean> data) {
        super(R.layout.item_my_coupon, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCouponBean.ListBean item) {
        if (item != null) {
            helper.setText(R.id.tv_ticket_money, item.getCoupon_money()+"");
            helper.setText(R.id.tv_ticket_date, "·" + DateUtil.StampTimeToDate(item.getStart_time() + "", "yyyy-MM-dd")
                    + "至" +
                    DateUtil.StampTimeToDate(item.getEnd_time() + "", "yyyy-MM-dd")
            );
            helper.setText(R.id.tv_ticket_name, item.getCoupon_name());

        }
    }
}
